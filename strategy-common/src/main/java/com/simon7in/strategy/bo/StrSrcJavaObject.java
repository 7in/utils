package com.simon7in.strategy.bo;

import javax.tools.SimpleJavaFileObject;
import java.net.URI;

/**
 * Created by jiayu.shenjy on 2016/2/26.
 */
public class StrSrcJavaObject extends SimpleJavaFileObject {
        private String content;

        public StrSrcJavaObject(String name, String content) {
            super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.content = content;
        }

        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return content;
        }
}
