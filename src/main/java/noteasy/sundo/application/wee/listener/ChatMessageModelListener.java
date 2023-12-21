package noteasy.sundo.application.wee.listener;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.global.library.mongo.SequenceGenerator;
import noteasy.sundo.queryfactory.wee.ChatMessage;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatMessageModelListener extends AbstractMongoEventListener<ChatMessage> {

    private final SequenceGenerator sequenceGenerator;

    /**
     * chat message 생성 이벤트를 받아 sequence를 증가시켜주는 핸들러입니다.
     * @param event never {@literal null}.
     */
    @Override
    public void onBeforeConvert(BeforeConvertEvent<ChatMessage> event) {
        event.getSource().setId(sequenceGenerator.generateSequence(ChatMessage.SEQUENCE_NAME));
    }
}
