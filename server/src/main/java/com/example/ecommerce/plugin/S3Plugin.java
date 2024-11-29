package com.example.ecommerce.plugin;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.regions.Regions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.UUID;

@Component
@Slf4j
public class S3Plugin {
    @Value("${aws.accessKeyId}")
    private String accessKeyId;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Value("${aws.bucketName}")
    private String bucketName;
    public void uploadObject(MultipartFile file) {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKeyId, secretKey);
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.EU_NORTH_1)
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();

        try {
            // Prepare metadata for the file
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());

            // Upload the file

            s3.putObject(bucketName, getUniqueNameForS3(file.getOriginalFilename()), file.getInputStream(), metadata);
            log.debug("File uploaded successfully to S3 bucket: {}", bucketName);

        } catch (AmazonServiceException e) {
            log.error("Amazon S3 couldn't process the request: {}", e.getErrorMessage());
        } catch (IOException e) {
            log.error("IOException: Unable to upload file to S3: {}", e.getMessage());
        }
    }

    private String getUniqueNameForS3(String fileName) {
        String uniqueId = UUID.randomUUID().toString();
        String fileExtension = "";
        if (fileName != null && fileName.contains(".")) {
            fileExtension = fileName.substring(fileName.lastIndexOf("."));
        }

        return uniqueId + fileExtension;
    }

    private boolean isImage(MultipartFile file) {
        // Check MIME type
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            return false;
        }

        // Check extension
        String originalFileName = file.getOriginalFilename();
        if (originalFileName != null) {
            String lowerCaseFileName = originalFileName.toLowerCase();
            return lowerCaseFileName.endsWith(".jpg") ||
                    lowerCaseFileName.endsWith(".jpeg") ||
                    lowerCaseFileName.endsWith(".png") ||
                    lowerCaseFileName.endsWith(".gif") ||
                    lowerCaseFileName.endsWith(".bmp");
        }

        return false;
    }
}

