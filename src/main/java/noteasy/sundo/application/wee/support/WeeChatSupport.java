package noteasy.sundo.application.wee.support;

import noteasy.sundo.application.wee.dto.ChatDto;
import noteasy.sundo.application.wee.dto.ChatRoomDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WeeChatSupport {
    void createChatRoom();
    Mono<ChatDto.Response> sendMessage(Long roomId, ChatDto.Request request);
    Flux<ChatDto.Response> queryMessage(Long roomId);
    ChatRoomDto.Response queryMyChatRoom();
    ChatRoomDto.Responses queryAllChatRoom();
}
