package noteasy.sundo.queryfactory.wee.repository;

import noteasy.sundo.queryfactory.wee.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    boolean existsByStudentIdAndWeeId(Long studentId, Long weeId);
    Optional<ChatRoom> findByStudentId(Long studentId);
}
