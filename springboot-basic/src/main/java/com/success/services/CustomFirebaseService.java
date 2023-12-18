package com.success.services;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomFirebaseService {
  @PostConstruct
  void init() {
    try (FileInputStream serviceAccount =
        new FileInputStream("./tradnalist-firebase-adminsdk.json")) {
      log.info("init firebaseapp");

      FirebaseOptions options = FirebaseOptions.builder()
          .setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

      FirebaseApp.initializeApp(options);

    } catch (Exception e) {
      log.error("unable to init firebase", e);
      throw new RuntimeException("unable to init firebase");
    }
  }

  public void setUserClaims(String userid) throws FirebaseAuthException {
    List<String> permissions = Arrays.asList("USER", "ADMIN");
    Map<String, Object> claims = Map.of("custom_claims", permissions);
    FirebaseAuth.getInstance().setCustomUserClaims(userid, claims);
  }
}
