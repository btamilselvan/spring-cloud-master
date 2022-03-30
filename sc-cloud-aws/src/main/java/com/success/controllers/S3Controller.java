package com.success.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.success.services.S3Service;

@RestController
@RequestMapping("/s3")
public class S3Controller {

  @Autowired private S3Service service;

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public String upload(@RequestPart(name = "data") MultipartFile file) {
    return service.upload(file);
  }

  @GetMapping(produces = MediaType.APPLICATION_OCTET_STREAM_VALUE, path = "/{key}")
  public ResponseEntity<byte[]> download(@PathVariable String key, HttpServletResponse response) {
    byte[] data = service.download(key);
    response.setHeader("Content-Disposition", "attachment; filename=" + key);
    return ResponseEntity.ok().body(data);
  }

  @GetMapping("/list/{bucket}")
  public String downloadMultiple(@PathVariable String bucket) {
    return service.downloadMultiple(bucket);
  }
}
