package noteasy.sundo.application.wee.executor;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.application.wee.dto.ChatDto;
import noteasy.sundo.application.wee.support.WeeSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class WeeExecutor {

    private final WeeSupport weeSupport;

    @Transactional(rollbackFor = Exception.class)
    public void createChatRoom() {
        weeSupport.createChatRoom();
    }

    public Mono<ChatDto.Response> sendMessage(Long roomId, ChatDto.Request request) {
        return weeSupport.sendMessage(roomId, request);
    }
}
