package com.project.magang2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
    public static void main( String[] args )
    {
//        System.out.println( "Hello World!" );
    	SpringApplication.run(App.class, args);
    }
}
