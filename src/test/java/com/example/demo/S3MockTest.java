/*************************************************************************
 * ADOBE CONFIDENTIAL
 * ___________________
 *
 * Copyright 2020 Adobe
 * All Rights Reserved.
 *
 * NOTICE: All information contained herein is, and remains
 * the property of Adobe and its suppliers, if any. The intellectual
 * and technical concepts contained herein are proprietary to Adobe
 * and its suppliers and are protected by all applicable intellectual
 * property laws, including trade secret and copyright laws.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Adobe.
 **************************************************************************/

package com.example.demo;

import com.adobe.testing.s3mock.junit5.S3MockExtension;
import java.util.Base64;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@ExtendWith(S3MockExtension.class)
public class S3MockTest {
  private static final String BUCKET_NAME = "mydemotestbucket";

  @Test
  void testS3Mock(final S3Client s3Client) {
    s3Client.createBucket(CreateBucketRequest.builder().bucket(BUCKET_NAME).build());
    PutObjectRequest request = PutObjectRequest.builder()
        .bucket(BUCKET_NAME)
        .key("foo/ab/cd/foo.json")
        .build();
    s3Client.putObject(request, RequestBody.fromBytes(Base64.getEncoder().encode(
        UUID.randomUUID().toString().getBytes())));
  }
}
