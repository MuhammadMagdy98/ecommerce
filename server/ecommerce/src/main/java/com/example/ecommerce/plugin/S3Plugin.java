package com.example.ecommerce.plugin;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.regions.Regions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Component
@Slf4j
public class S3Plugin {

    public void uploadObject(MultipartFile file) {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("", "");
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.EU_NORTH_1)
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
        final String bucketName = "ecommerce-magdy";

        try {
            // Prepare metadata for the file
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());

            // Upload the file
            s3.putObject(bucketName, file.getOriginalFilename(), file.getInputStream(), metadata);
            log.debug("File uploaded successfully to S3 bucket: " + bucketName);

        } catch (AmazonServiceException e) {
            log.error("Amazon S3 couldn't process the request: " + e.getErrorMessage());
        } catch (IOException e) {
            log.error("IOException: Unable to upload file to S3: " + e.getMessage());
        }
    }
}

