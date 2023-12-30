package noteasy.sundo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class SundoApplication {

	public static void main(String[] args) {

		SpringApplication.run(SundoApplication.class, args);
	}

}
