package noteasy.sundo.queryfactory.persistmodel.wee.repository;

import noteasy.sundo.queryfactory.persistmodel.wee.ChatRoom;
import org.springframework.data.repository.CrudRepository;

public interface ChatRoomRepository extends CrudRepository<ChatRoom, Long> {
    boolean existsByStudentIdAndWeeId(Long studentId, Long weeId);
}
