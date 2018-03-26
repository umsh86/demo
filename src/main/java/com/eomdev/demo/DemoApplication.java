package com.eomdev.demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.ModelMap;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@SpringBootApplication
@EnableSwagger2
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.eomdev.demo"))
				.build()
				.apiInfo(metaData());
	}

	private ApiInfo metaData() {
		return new ApiInfo("Demo Api", "Api Documentation", "1.0", "urn:tos", new Contact("", "", ""),
				"Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


}
