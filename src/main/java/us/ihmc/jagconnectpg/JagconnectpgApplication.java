package us.ihmc.jagconnectpg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JagconnectpgApplication {

	public static void main(String[] args) {

		SpringApplication.run(JagconnectpgApplication.class, args);
	}

}
