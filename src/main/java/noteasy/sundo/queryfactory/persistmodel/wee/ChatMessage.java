package noteasy.sundo.queryfactory.persistmodel.wee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;


@Getter
@Builder
@Document(collation = "chat_message")
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {

    @Id
    private Long id;

    private Long roomId;

    private String message;

    @Enumerated(EnumType.STRING)
    private MessageDirection messageDirection;

    private LocalDateTime createdAt;
}