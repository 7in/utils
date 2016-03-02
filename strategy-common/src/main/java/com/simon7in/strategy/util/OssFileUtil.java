package com.simon7in.strategy.util;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * OSS 文件存储服务
 * Created by Asha on 15/10/14.
 */
public class OssFileUtil {

    private  final Logger logger = LoggerFactory.getLogger(this.getClass());

    private  String accessKey;
    private  String accessSecret;
    private  String bucketName;
    private  String endpoint ;
    private OSSClient client;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getAccessSecret() {
        return accessSecret;
    }

    public void setAccessSecret(String accessSecret) {
        this.accessSecret = accessSecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public OSSClient getClient() {
        return client;
    }

    public void setClient(OSSClient client) {
        this.client = client;
    }

    public void initOssClient(){
        client = new OSSClient(endpoint, accessKey, accessSecret);
        logger.info("=== OSSFile Client setup ok ===");
    }


    /**
     * 上传String数据到oss
     * @param key
     * @return
     */
    public Map<String, Object> uploadContentToOss(String content, String key){
        Map<String, Object> result = new HashMap<String, Object>();
        PutObjectResult ossObject;
        try {
            ossObject = client.putObject(bucketName, key, new ByteArrayInputStream(content.getBytes()));
            result.put("success",true);
            result.put("ETag",ossObject.getETag());
        }catch (RuntimeException ex){
            result.put("success",false);
            logger.error("上传失败:",ex);
        }
        return result;
    }

    /**
     * 根据key和bucket获取内容,返回String格式数据
     * @param key
     * @return
     */
    public String getContentFromOss(String key) {
        OSSObject ossObject = client.getObject(bucketName, key);
        InputStream objectContent = ossObject.getObjectContent();
        String result = null;
        try {
            result = IOUtils.toString(objectContent);
        }catch (IOException ex) {
            logger.error("获取内容失败:", ex);
        }
        return result;
    }

    /**
     * 上传文件到oss，返回图片访问url地址
     * @param fileName
     * @return
     */
    public Map uploadFileToOss(String fileName,InputStream in, long length){
        Map result = new HashMap<String,String>();
        PutObjectResult ossObject;
        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentLength(length);
        try {
            ossObject = client.putObject(bucketName, fileName, in, objectMeta);
            result.put("success",true);
            result.put("ETag",ossObject.getETag());
            result.put("fileUrl",getSignedUrl(fileName).toString());
        }catch (RuntimeException ex){
            result.put("success",false);
            result.put("reason","请检查图片文件名称");
            logger.error("上传图片到oss失败.",ex);
        } finally{
            try{
                in.close();
            }catch (IOException e) {
                logger.error("Close InputStream 失败.",e);
            }
        }
        return result;
    }

    /**
     * 上传文件到oss
     * @param
     * @return
     */
    public String uploadFileToOss(String fileName,String localFilePath) throws FileNotFoundException {
        String eTag = "";
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(new File(localFilePath));
            PutObjectResult puResult= client.putObject(bucketName, fileName, fin);
            eTag=puResult.getETag();
        } catch (FileNotFoundException e) {
            logger.error("上传文件到oss失败!fileName="+fileName+";localFilePath="+localFilePath);
            throw  e;
        }finally {
            if (fin!= null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    logger.error("uploadFileToOss.FileInputStream#close error!",e);
                }
            }
        }
        return eTag;
    }
    /**
     * 获得oss文件带有访问签名的url，可访问时间固定为1小时
     * @param fileName
     * @return
     */
    public URL getSignedUrl(String fileName){
        Date expiration = new Date(new Date().getTime() + OssFileParas.accessTime);
        //服务器端生成url签名字串
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, fileName, HttpMethod.GET);
        request.setExpiration(expiration);
        // 生成URL签名(HTTP GET请求)
        URL signedUrl = client .generatePresignedUrl(request);
        if(signedUrl!=null&&!signedUrl.getFile().isEmpty())
            return signedUrl;
        else {
            logger.error("获取oss文件访问签名失败.fielName="+fileName);
            return null;
        }
    }

    /**
     * 获得oss文件带有访问签名的url，设置可访问时间
     * @param fileName
     * @param accessTime
     * @return
     */

    public String getSignedUrl(String fileName,Long accessTime){
        Date expiration;
        if(accessTime==null||accessTime<=0)
            expiration = new Date(new Date().getTime() + OssFileParas.accessTime);
        else
            expiration = new Date(new Date().getTime() + accessTime);

        //服务器端生成url签名字串
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, fileName, HttpMethod.GET);
        request.setExpiration(expiration);
        // 生成URL签名(HTTP GET请求)
        URL signedUrl = client .generatePresignedUrl(request);
        if(signedUrl!=null&&!signedUrl.getFile().isEmpty())
            return signedUrl.toString();
        else {
            logger.error("获取oss文件访问签名失败.fileName= "+fileName);
            return "";
        }
    }
}
