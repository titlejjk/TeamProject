package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

import java.io.File;

@SpringBootApplication(exclude =  {UserDetailsServiceAutoConfiguration.class})
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
		File file = new File("example.txt");
		String absolutePath = file.getAbsolutePath();
		System.out.println("Absolute Path: " + absolutePath);

		Package aPackage = Package.getPackage("org.springframework.security");
		if (aPackage != null) {
			System.out.println("Spring Security version: " + aPackage.getImplementationVersion());
		} else {
			System.out.println("Spring Security is not available.");
		}
	}

}
