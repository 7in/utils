package com.simon7in.strategy.util;

import com.simon7in.strategy.bo.StrSrcJavaObject;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.tools.*;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Created by jiayu.shenjy on 2016/2/26.
 */
public class JavaFileCUtil {
   private static Logger logger = LoggerFactory.getLogger(JavaFileCUtil.class);

    public static String exeJavaClass(String className,String javaContent) throws Exception {

        /**
         * 编译文件
         */
        Object clazzObj = compile(className,javaContent);
        /**
         * 注入bean
         */
        SpringContextUtil.injectFieldBean(Resource.class, clazzObj);
        /**
         * 执行
         */
        if (clazzObj instanceof ScriptInvoker) {
            logger.error("JavaFileCUtil.exeJavaClass execute run!class=" + clazzObj);
            ScriptInvoker script = (ScriptInvoker) clazzObj;
            script.callScript();
            return clazzObj.getClass().getName();
        }
        logger.error("JavaFileCUtil.exeJavaClass execute not run!class=" + clazzObj);
        return clazzObj.getClass().getName();
    }

    /**
     * 编译文件
     * 返回实例
     *
     * @param content
     * @return
     */
    private static Object compile(String className,String content) throws Exception {
        /**
         * 将名字换了
         * java字符串文本中,用"_fileName_"作为class name 占位符
         * 这里讲name换掉，以防止重复
         */
//        String fileName = "TmpJavaFile" + System.currentTimeMillis();
//        String javaContent = content.replaceAll("_fileName_", fileName);
        File classPath = new File(Thread.currentThread().getContextClassLoader().getResource("").toURI());
        try {
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();

            StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
            StrSrcJavaObject srcObject = new StrSrcJavaObject(className, content);
            Iterable<? extends JavaFileObject> fileObjects = Arrays.asList(srcObject);
                /**
            * 获取工程中的jar
            */
            StringBuilder pathBuilder = new StringBuilder();
            URL[] clsPath = ((URLClassLoader) (URLClassLoader) JavaFileCUtil.class.getClassLoader()).getURLs();
            for(URL u : clsPath){
                pathBuilder.append(u.getPath());
                pathBuilder.append(System.getProperty("path.separator"));
            }
            /**
             * 获取额外（页面主动）添加的jar路径
             */
//            String jarPath = System.getProperty("user.dir") + System.getProperty("file.separator") + paramList.get().get(ParamConstant.jarPath);
//            pathBuilder.append(jarPath);
//            pathBuilder.append(System.getProperty("path.separator"));

            List<String> optionList = new ArrayList<String>();
            /**
             * classpath路径
             */
            optionList.add("-classpath");
            optionList.add(pathBuilder.toString());
            /**
             * 保存.class文件
             * 不保存的话,后面通过classloader加载不到类,无法实例化
             */
            String flag = "-d";
            String outDir = classPath.getAbsolutePath() + File.separator;
            optionList.add(flag);
            optionList.add(outDir);
//            optionList.add("-J");
//            optionList.add("-Xms512m");
//            optionList.add("-J");
//            optionList.add("-Xmx512m");
//            optionList.add("-J");
//            optionList.add("-XX:PermSize=512m");
//            optionList.add("-J");
//            optionList.add("-XX:MaxPermSize=512m");

            JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, optionList, null, fileObjects);
            if (task.call()) {
                logger.error("JavaFileCUtil.compile successfully.className=" + className);
                /**
                 *将保存的.class文件加载到classloader
                 */
                URLClassLoader classLoader = new URLClassLoader(
                        new URL[]{classPath.toURL() /*add external jar URL here*/}, JavaFileCUtil.class.getClassLoader());

                // Load the class from the classloader by name....
                Class<?> loadedClass = classLoader.loadClass("tmp." + className);
                // Create a new instance...
                return loadedClass.newInstance();
            } else {
                List<String> javaError = new ArrayList<String>();
                for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics
                        .getDiagnostics()) {
                    javaError.add(String.format("Error on line %d in %s, %s", diagnostic
                            .getLineNumber(), diagnostic.getSource().toUri(), diagnostic.getMessage(Locale.CHINA)));
                    System.out.format("Error on line %d in %s, %s", diagnostic
                            .getLineNumber(), diagnostic.getSource().toUri(), diagnostic.getMessage(Locale.CHINA));
                }
                throw new Exception("JavaFileCUtil.compile fail!className=" + className
                        +";errorMsg="+new Gson().toJson(javaError));
            }
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            try {
                File tmpJavaBinary = new File(classPath.getPath() + System.getProperty("file.separator") + "tmp");
                if (tmpJavaBinary != null && tmpJavaBinary.listFiles() != null) {
                    for (File f : tmpJavaBinary.listFiles()) {
                        if (f.exists()) {
                            f.delete();
                        }
                    }
                }
            } catch (Exception ioex) {
                logger.error("JavaFileCUtil.compile error!className=" + className, ioex);
            }
        }
    }
}
