package com.success;

import java.util.Collections;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.google.firebase.auth.ErrorInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.ImportUserRecord;
import com.google.firebase.auth.UserImportOptions;
import com.google.firebase.auth.UserImportResult;
import com.google.firebase.auth.hash.Bcrypt;
import lombok.extern.slf4j.Slf4j;

//@Component
@Slf4j
public class MyRunner implements CommandLineRunner {

  @Override
  public void run(String... args) throws Exception {
    log.info("hello");
    verifyFirebaseToken();
    importUserToFirebase();
  }

  private void verifyFirebaseToken() {
    String idTokenString =
        "eyJhbGciOiJSUzI1NiIsImtpZCI6IjAzMmNjMWNiMjg5ZGQ0NjI2YTQzNWQ3Mjk4OWFlNDMyMTJkZWZlNzgiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vdHJhZG5hbGlzdCIsImF1ZCI6InRyYWRuYWxpc3QiLCJhdXRoX3RpbWUiOjE3MDI4NjExMTMsInVzZXJfaWQiOiIyNjYiLCJzdWIiOiIyNjYiLCJpYXQiOjE3MDI4NjExMTMsImV4cCI6MTcwMjg2NDcxMywiZW1haWwiOiJtaXNjQHRhbWlscy5yb2NrcyIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJmaXJlYmFzZSI6eyJpZGVudGl0aWVzIjp7ImVtYWlsIjpbIm1pc2NAdGFtaWxzLnJvY2tzIl19LCJzaWduX2luX3Byb3ZpZGVyIjoicGFzc3dvcmQifX0.jGbzTD-vDy-WDMKrKL_TKdFG66Twuhovwm2FmSV_TitJqU_2KG44hsqdMSMlpxvgb-_XGQLsEtu1DGxyXvyA3aVPgm4Iqm1BliTXhGqLOLPuw4SJHqRE-CaSRpg1QOl14Trg3SJlDD7qR0cLC_i4NbFgXjRXMyQelFQGt_v5SjyN7PC8Rdcy7uyIPRXiQvj2NG2SNAPZU7Phs_KPgX8A8pSF6IBF8n2avvUht5RTIMRfCbaoD6PrS7eglX1HBxrYJd-6h8buS4JsmhsIvhLC5Mjd8G72YtqLSuCjzdn3eKMmywN9jMOnuCoa6xmVGSA92KoYrYeC-a7Ac3xzfNAjkA";
    try {
      FirebaseToken token = FirebaseAuth.getInstance().verifyIdToken(idTokenString);
      log.info(token.getEmail());
    } catch (FirebaseAuthException e) {
      log.error("erro ", e);
    }
  }

  private void importUserToFirebase() {

    log.info("import users");

    String passHash = "$2a$10$LTnKVIJWwpzilHrI8kC9I.hf0Ep4nW5vTyvvdoD69mddUjJTM43Ly";

    List<ImportUserRecord> users = Collections
        .singletonList(ImportUserRecord.builder().setUid("266").setEmail("misc@tamils.rocks")
            .setEmailVerified(true).setPasswordHash(passHash.getBytes()).build());

    UserImportOptions options = UserImportOptions.withHash(Bcrypt.getInstance());
    try {
      UserImportResult result = FirebaseAuth.getInstance().importUsers(users, options);
      for (ErrorInfo indexedError : result.getErrors()) {
        log.info("Failed to import user: {}", indexedError.getReason());
      }
      log.info("succss count {}", result.getSuccessCount());

    } catch (FirebaseAuthException e) {
      log.error("erro ", e);
    }
  }

}
