package de.maeddes.simplecode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	Logger logger = LoggerFactory.getLogger(SimplecodeApplication.class);

	private String getInstanceId(){

		if(!hostname.equals("not_set")) return hostname;
		if(!cfInstance.equals("not_set")) return cfInstance;
		return "probably not set";

	}

	@GetMapping("/hello")
	String hello(){

		logger.info("Call to hello method on instance: " + getInstanceId());
		return getInstanceId()+" Hello, cf-for-k8s ! ";

	}

	@GetMapping("/fail")
	String fail() {

		logger.info("Call to failing method on instance: "+getInstanceId());
		System.exit(1);
		return "fixed!";
	}

	public static void main(final String[] args) {
		SpringApplication.run(SimplecodeApplication.class, args);
	}

}
