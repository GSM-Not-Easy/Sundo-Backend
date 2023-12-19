package noteasy.sundo.application.wee.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import noteasy.sundo.queryfactory.wee.ChatRoom;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChatRoomDto {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class Response {
        private final Long roomId;

        private final String roomName;

        private final Long studentId;

        private final String studentName;
    }

    @Getter
    @RequiredArgsConstructor
    public static class Responses {
        private final List<Response> rooms;
    }

    public static Response of(ChatRoom chatRoom, String studentName) {
        return Response.builder()
                .roomId(chatRoom.getId())
                .roomName(chatRoom.getChatRoomName())
                .studentId(chatRoom.getStudentId())
                .studentName(studentName)
                .build();
    }

    public static Responses listOf(Map<ChatRoom, String> chatRoomMap) {
        // key: chatRoom, value: studentName
        List<Response> responses = chatRoomMap.entrySet().stream()
                .map(entry -> ChatRoomDto.of((entry.getKey()), entry.getValue()))
                .collect(Collectors.toList());

        return new Responses(responses);
    }
}
