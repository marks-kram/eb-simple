package de.test.beanstalk.simple.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

@Service
public class FileService {

    @Value("${s3.bucketName}")
    private String bucketName;

    private static final Region REGION = Region.EU_CENTRAL_1;
    private static final S3Client CLIENT = S3Client.builder().region(REGION).build();

    public void addFile(String name, String content) {
        CLIENT.putObject(PutObjectRequest.builder().bucket(bucketName).key(name)
                .build(), RequestBody.fromByteBuffer(getByteBuffer(content)));
    }

    private static ByteBuffer getByteBuffer(String content) {
        byte[] b = content.getBytes(StandardCharsets.UTF_8);
        return ByteBuffer.wrap(b);
    }

}
