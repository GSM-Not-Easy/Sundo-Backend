package noteasy.sundo.application.wee.executor;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.application.wee.support.WeeSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class WeeExecutor {

    private final WeeSupport weeSupport;

    @Transactional(rollbackFor = Exception.class)
    public void createChatRoom() {
        weeSupport.createChatRoom();
    }
}
