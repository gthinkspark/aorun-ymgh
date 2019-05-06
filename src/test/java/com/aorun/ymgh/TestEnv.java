package com.aorun.ymgh;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestEnv {

      @Value("${profile}")
      private String profile;
      @Autowired
     private Environment env;

     @Test
      public void testCoreConfig() {
        System.out.println("profile---->"+profile);
        }
      @Test
      public void testCoreConfig2() {
          System.out.println("profile---->"+env.getProperty("profile"));
    }
}