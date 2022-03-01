package br.com.caio.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JwtAuthenticationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtAuthenticationApiApplication.class, args);
	}

	/*@Bean
	public PasswordEncoder getPasswordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}*/
}
