package noteasy.sundo.queryfactory.wee.repository;

import noteasy.sundo.queryfactory.wee.ChatMessage;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;

public interface ChatMessageRepository extends ReactiveMongoRepository<ChatMessage, Long> {

    @Tailable
    @Query("{ roomId : ?0 }")
    Flux<ChatMessage> findByRoomIdSortedByCreatedAt(Long roomId);

}
