package com.success.services;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;

import io.awspring.cloud.core.io.s3.PathMatchingSimpleStorageResourcePatternResolver;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class S3Service {
  @Autowired private ResourceLoader resourceLoader;
  private ResourcePatternResolver resourcePatternResolver;

  @Autowired
  public S3Service(AmazonS3 s3, ApplicationContext context) {
    this.resourcePatternResolver =
        new PathMatchingSimpleStorageResourcePatternResolver(s3, context);
  }

  public String upload(MultipartFile file) {
    Resource resource =
        resourceLoader.getResource("s3://mytrocksbucket/" + file.getOriginalFilename());
    WritableResource writableResource = (WritableResource) resource;
    try (OutputStream os = writableResource.getOutputStream()) {
      os.write(file.getBytes());
    } catch (Exception e) {
      log.error("", e);
      throw new IllegalArgumentException(e);
    }
    return "sucess";
  }

  public byte[] download(String key) {
    Resource resource = resourceLoader.getResource("s3://mytrocksbucket/" + key);
    try {
      return IOUtils.toByteArray(resource.getInputStream());
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public String downloadMultiple(String bucketName) {
    try {
      Resource[] resources = this.resourcePatternResolver.getResources("s3://" + bucketName + "/*");
      return Arrays.asList(resources)
          .stream()
          .map(Resource::getFilename)
          .collect(Collectors.joining("\n"));
    } catch (IOException e) {
      log.error("", e);
      throw new IllegalArgumentException(e);
    }
  }
}
