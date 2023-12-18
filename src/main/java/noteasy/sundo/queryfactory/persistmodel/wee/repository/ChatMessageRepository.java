package noteasy.sundo.queryfactory.persistmodel.wee.repository;

import noteasy.sundo.queryfactory.persistmodel.wee.ChatMessage;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ChatMessageRepository extends ReactiveMongoRepository<ChatMessage, Long> {
}
