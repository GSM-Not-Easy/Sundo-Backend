package noteasy.sundo.queryfactory.persistmodel.wee.repository;

import noteasy.sundo.queryfactory.persistmodel.wee.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    boolean existsByStudentIdAndWeeId(Long studentId, Long weeId);
    Optional<ChatRoom> findByStudentId(Long studentId);
}
