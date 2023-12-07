package noteasy.sundo;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.queryfactory.persistmodel.classroom.manager.ClassRoomPersistenceManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class SundoApplication {

	public static void main(String[] args) {

		SpringApplication.run(SundoApplication.class, args);
	}

}
