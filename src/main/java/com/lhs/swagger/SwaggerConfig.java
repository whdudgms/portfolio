package com.lhs.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig extends WebMvcConfigurationSupport {

    @Bean
    public Docket api() {
        // Docket Bean을 생성하여 API의 정보를 설정할 수 있습니다.
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()
          .apis(RequestHandlerSelectors.basePackage("com.lhs.rest.controller"))      
          // 여기서는 모든 API를 문서화하도록 설정했습니다. 특정 패키지에 대해서만 적용하려면 RequestHandlerSelectors.basePackage("패키지명")을 사용하세요.
          .apis(RequestHandlerSelectors.any())
          // 모든 경로에 대해 문서화를 진행합니다. 특정 경로를 제외하려면 PathSelectors.regex("/특정경로.*")와 같이 설정할 수 있습니다.
          .paths(PathSelectors.any())
          .build();
    }
}