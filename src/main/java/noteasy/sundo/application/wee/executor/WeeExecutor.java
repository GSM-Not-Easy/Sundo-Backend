package noteasy.sundo.application.wee.executor;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.application.wee.dto.ChatDto;
import noteasy.sundo.application.wee.dto.ChatRoomDto;
import noteasy.sundo.application.wee.support.WeeChatSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class WeeExecutor {

    private final WeeChatSupport weeChatSupport;

    @Transactional(rollbackFor = Exception.class)
    public void executeCreateChatRoom() {
        weeChatSupport.createChatRoom();
    }

    public Mono<ChatDto.Response> executeSendMessage(Long roomId, ChatDto.Request request) {
        return weeChatSupport.sendMessage(roomId, request);
    }

    public Flux<ChatDto.Response> executeQueryMessage(Long roomId) {
        return weeChatSupport.queryMessage(roomId);
    }

    public ChatRoomDto.Response executeQueryMyChatRoom() {
        return weeChatSupport.queryMyChatRoom();
    }

    public ChatRoomDto.Responses executeQueryAllChatRoom() {
        return weeChatSupport.queryAllChatRoom();
    }
}
