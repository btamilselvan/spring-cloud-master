package com.success;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner{
  
  @Autowired
  private JdbcTemplate jt;
  
  @Value("${apiKey}")
  private String apiKey;
  
  @Value("${jwtKey}")
  private String jwtKey;

  @Override
  public void run(String... args) throws Exception {
    System.out.println("run...."+jwtKey+" "+apiKey);
    
    List<Map<String, Object>> result = 
    jt.queryForList("select * from Countries");
    
    result.stream().forEach(System.out::println);
  }

}
