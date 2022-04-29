package com.casalibertad;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class CasaLibertadApplication extends SpringBootServletInitializer  implements WebApplicationInitializer{

	public static void main(String[] args) {
		SpringApplication.run(CasaLibertadApplication.class, args);
	}


}
