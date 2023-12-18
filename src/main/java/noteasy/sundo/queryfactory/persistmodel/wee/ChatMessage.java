package noteasy.sundo.queryfactory.persistmodel.wee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Getter
@Builder
@Document(collation = "chat_message")
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {

    @Id
    private Long id;

    private String message;

    private Long senderId;

    private Long receiverId;

    private LocalDateTime createdAt;
}