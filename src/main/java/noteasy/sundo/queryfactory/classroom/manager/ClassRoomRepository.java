package noteasy.sundo.queryfactory.classroom.manager;

import noteasy.sundo.queryfactory.BaseQueryFactory;
import noteasy.sundo.queryfactory.classroom.ClassRoom;
import noteasy.sundo.queryfactory.classroom.factory.ClassRoomRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long>, BaseQueryFactory<ClassRoom, Long>, ClassRoomRepositoryCustom {
}
