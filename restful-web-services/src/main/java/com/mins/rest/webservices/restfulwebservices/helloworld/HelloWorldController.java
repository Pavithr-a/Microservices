package com.mins.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	private MessageSource messageSource;
	
	
	public HelloWorldController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized() {
		Locale locale=LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null,"Default Message",locale);
	}

	@GetMapping(path = "/hello-world")
	private String helloWorld() {
		return "Hello world";
	}
	
	@GetMapping("/hello-world-bean")
	private HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello world");
		
	}
	
	@GetMapping("/hello-world-bean/path-variable/{name}")
	private HelloWorldBean helloWorldBeanPathvariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello world , %s", name));
		
		
	}

}
