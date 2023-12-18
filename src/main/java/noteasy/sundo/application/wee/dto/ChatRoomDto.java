package noteasy.sundo.application.wee.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

public class ChatRoomDto {

    @Getter
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
}
