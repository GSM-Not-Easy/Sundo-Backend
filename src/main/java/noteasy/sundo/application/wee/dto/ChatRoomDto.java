package noteasy.sundo.application.wee.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import noteasy.sundo.queryfactory.persistmodel.wee.ChatRoom;

import java.util.List;
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

    public static List<Response> listOf(List<ChatRoom> chatRooms, String studentName) {
        return chatRooms.stream().map(c -> of(c, studentName))
                .collect(Collectors.toList());
    }
}
