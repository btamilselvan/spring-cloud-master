package com.success.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.threeten.bp.Instant;
import com.google.firebase.auth.FirebaseAuthException;
import com.success.services.CustomFirebaseService;

@RestController
public class HelloSecuredRestController {

  @Autowired
  private CustomFirebaseService service;

  @Secured({"USER"})
  @GetMapping
  public String ping() {
    return Instant.now().toString();
  }
  
  @Secured({"ADMIN"})
  @GetMapping("/health")
  public String health() {
    return Instant.now().toString();
  }

  @PostMapping("/update-role/{userid}")
  public String updateUserRole(@PathVariable String userid) throws FirebaseAuthException {
    service.setUserClaims(userid);
    return "success";
  }

}
