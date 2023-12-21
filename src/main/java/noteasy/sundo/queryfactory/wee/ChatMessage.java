package noteasy.sundo.queryfactory.wee;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;
import java.time.LocalDateTime;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collation = "chat_message")
@CompoundIndex(def = "{'id': 1, 'createdAt': 1}")
public class ChatMessage {

    @Transient
    public static  final String SEQUENCE_NAME = "chat_message_sequence";

    @Id
    @Setter
    private Long id;

    private Long roomId;

    private String message;

    @Enumerated(EnumType.STRING)
    private MessageDirection messageDirection;

    private LocalDateTime createdAt;
}