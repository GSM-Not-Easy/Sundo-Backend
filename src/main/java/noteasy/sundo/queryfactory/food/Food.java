package noteasy.sundo.queryfactory.food;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Transient;
import java.time.LocalDateTime;


@Document
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Food {

    @Transient
    public static  final String SEQUENCE_NAME = "chat_message_sequence";

    @Id
    @Setter
    private Long id;

    private String foodName;

    private LocalDateTime createdAt;

    private Long studentId;
}
