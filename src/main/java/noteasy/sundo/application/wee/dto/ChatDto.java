package noteasy.sundo.application.wee.dto;

import lombok.*;
import noteasy.sundo.queryfactory.persistmodel.wee.ChatMessage;
import noteasy.sundo.queryfactory.persistmodel.wee.MessageDirection;

import java.time.LocalDateTime;

public class ChatDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private Long to;
        private String message;
        private MessageDirection messageDirection;
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static  class Response {
        private final Long chatMessageId;
        private final Long to;
        private final Long from;
        private final String message;
        private final LocalDateTime createdAt;
        private final MessageDirection messageDirection;
    }

    public static Response of(ChatMessage message, Long to, Long from) {
        return Response.builder()
                .chatMessageId(message.getId())
                .to(to)
                .from(from)
                .message(message.getMessage())
                .createdAt(message.getCreatedAt())
                .messageDirection(message.getMessageDirection())
                .build();
    }
}
