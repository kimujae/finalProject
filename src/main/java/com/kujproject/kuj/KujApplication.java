package com.kujproject.kuj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
public class KujApplication {

	public static void main(String[] args) {
		SpringApplication.run(KujApplication.class, args);
	}
//	public ServletRegistrationBean<DispatcherServlet> dispatcherServletRegistration() {
//		DispatcherServlet dispatcherServlet = new DispatcherServlet();
//		ServletRegistrationBean<DispatcherServlet> registration = new ServletRegistrationBean<>(dispatcherServlet, "/");
//		registration.setName("dispatcherServlet");
//		return registration;
//	}
}
