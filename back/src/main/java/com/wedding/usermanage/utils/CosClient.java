package com.wedding.usermanage.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.model.StorageClass;
import com.qcloud.cos.region.Region;

import java.io.File;

public class CosClient {

    private static final String SafeACCESSKEY="QUtJREhZZ3lHVkxXUUVoMUlTdTdleDk3ck5ZYlZEaEM0eHZl";

    private static final String SafeSECRETKEY="R09UVUs2ZzNHS0V3TXVGT09FSUN4VHIyNjhoQWExSjE=";

    private static final String BUCKETNAME="weddingceremony-1301700911";

    private static final String APPID="1301700911";

    private static final String REGIONID = "ap-nanjing";

    public static final String bucket_url="https://weddingceremony-1301700911.cos.ap-nanjing.myqcloud.com";

    public static COSClient getCosClient() {
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(Base64Converter.decode(SafeACCESSKEY), Base64Converter.decode(SafeSECRETKEY));
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig中包含了设置region, https(默认http), 超时, 代理等set方法, 使用可参见源码或者接口文档FAQ中说明
        ClientConfig clientConfig = new ClientConfig(new Region(REGIONID));
        // 3 生成cos客户端
        COSClient cosClient = new COSClient(cred, clientConfig);
        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        //String bucketName = BUCKETNAME;
        return cosClient;
    }

    public static String uploadFile(String KEY,File file) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKETNAME, KEY, file);

        // 设置存储类型, 默认是标准(Standard), 低频(standard_ia),一般为标准的
        putObjectRequest.setStorageClass(StorageClass.Standard);

        COSClient cc = getCosClient();
        try {
            PutObjectResult putObjectResult = cc.putObject(putObjectRequest);
            // putobjectResult会返回文件的etag
            String etag = putObjectResult.getETag();
            System.out.println(etag);
        } catch (CosServiceException e) {
            e.printStackTrace();
        } catch (CosClientException e) {
            e.printStackTrace();
        }
        // 关闭客户端
        cc.shutdown();
        return null;
    }

    public static String deleteFile(String key){

        COSClient cc=getCosClient();
        cc.deleteObject(BUCKETNAME,key);
        return null;
    }

}
