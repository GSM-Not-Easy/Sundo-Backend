package noteasy.sundo.queryfactory.persistmodel.wee.repository;

import noteasy.sundo.queryfactory.persistmodel.wee.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    boolean existsByStudentIdAndWeeId(Long studentId, Long weeId);
}
