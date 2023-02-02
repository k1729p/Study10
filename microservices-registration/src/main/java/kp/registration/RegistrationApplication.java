package kp.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * This application runs the <b>Eureka</b> registration server.
 * 
 */
@SpringBootApplication
@EnableEurekaServer
public class RegistrationApplication {

	/**
	 * The constructor.
	 */
	public RegistrationApplication() {
		super();
	}

	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(RegistrationApplication.class, args);
	}
}