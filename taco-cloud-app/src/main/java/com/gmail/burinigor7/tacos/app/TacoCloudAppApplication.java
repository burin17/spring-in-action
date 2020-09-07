package com.gmail.burinigor7.tacos.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

@SpringBootApplication(scanBasePackages = {"com.gmail.burinigor7.tacos"})
@EntityScan(basePackages = {"com.gmail.burinigor7.tacos"})
@EnableJpaRepositories(basePackages = {"com.gmail.burinigor7.tacos"})
public class TacoCloudAppApplication {

  public static void main(String[] args) {
    SpringApplication.run(TacoCloudAppApplication.class, args);
  }

  @Bean
  ErrorViewResolver supportPathBasedLocationStrategyWithoutHashes() {
      return new ErrorViewResolver() {
          @Override
          public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
              return status == HttpStatus.NOT_FOUND
                      ? new ModelAndView("index.html", Collections.<String, Object>emptyMap(), HttpStatus.OK)
                      : null;
          }
      };
  }
  
}
