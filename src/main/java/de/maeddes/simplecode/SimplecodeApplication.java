package de.maeddes.simplecode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SimplecodeApplication {

	@Value("${CF_INSTANCE_GUID:not_set}")
	String cfInstance;

	@Value("${HOSTNAME:not_set}")
	String hostname;

	@Value("${spring.profiles.active: none}")
	String profile;

	private String getInstanceId(){

		if(!hostname.equals("not_set")) return hostname;
		if(!cfInstance.equals("not_set")) return cfInstance;
		return "probably not set";

	}

	@GetMapping("/hello")
	String hello(){

		return getInstanceId()+" Hello, OSSummit ! ";

	}

	@GetMapping("/fail")
	String fail() {

		System.exit(1);
		return "fixed!";
	}

	public static void main(final String[] args) {
		SpringApplication.run(SimplecodeApplication.class, args);
	}

}
