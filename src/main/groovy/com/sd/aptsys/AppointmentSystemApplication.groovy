package com.sd.aptsys


import com.sd.aptsys.processor.CommandProcessor
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean

@SpringBootApplication
class AppointmentSystemApplication {

	static void main(String[] args) {
		SpringApplication.run(AppointmentSystemApplication, args)
	}

/*
	@Bean
	ServletRegistrationBean myServletRegistration () {
		ServletRegistrationBean srb = new ServletRegistrationBean();
		//srb.setServlet(new CommandProcessor());
		//srb.setUrlMappings(Arrays.asList("/*"));
		return srb;
	}
*/

}
