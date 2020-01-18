package cdu.lincnaqiang.bookshop.util;

import cdu.lincnaqiang.bookshop.config.AliYunOssConfig;
import com.aliyun.oss.*;
import com.aliyun.oss.model.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;


@Component
public class AliYunOssUtil {
    private static String bucketName = AliYunOssConfig.BUCKET_NAME;
    private static String endpoint = AliYunOssConfig.END_POINT;
    private static String accessKeyId = AliYunOssConfig.AccessKey_ID;
    private static String accessKeySecret = AliYunOssConfig.AccessKey_Secret;

    private OSS ossClient;

    private OSS getInstance() {
        if(ossClient==null){
            synchronized(AliYunOssUtil.class){
                if(ossClient==null){
                    ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
                }
            }
        }
        return ossClient;
    }

    /**
     * 上传
     */
    public String upload(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            String uuid = UUID.randomUUID().toString();
            fileName = uuid + suffix;
            String fileUrl = "https://" + bucketName + "." + endpoint + "/" + fileName;
            getInstance().putObject(bucketName,fileName,file.getInputStream());
            ossClient.setBucketAcl(bucketName,CannedAccessControlList.PublicRead);
            return fileUrl;
        } catch (Exception e) {
            return "failed";
        }
    }
}
