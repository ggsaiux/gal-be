package com.asociatialocatari.gal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//// https://www.bezkoder.com/spring-boot-security-postgresql-jwt-authentication/
@SpringBootApplication
public class GestiuneApplication {

	private static final Logger logger = LoggerFactory.getLogger(GestiuneApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(GestiuneApplication.class, args);
		logger.trace("@TRACE - :)");
		logger.debug("@DEBUG - ;)");
		logger.info("@INFO - :)");
		logger.warn("@WARN - :|");
		logger.error("@ERROR - :(");
	}

}
