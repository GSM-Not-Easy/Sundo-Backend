package noteasy.sundo.queryfactory.persistmodel.classroom.manager;

import noteasy.sundo.queryfactory.BaseQueryFactory;
import noteasy.sundo.queryfactory.persistmodel.classroom.ClassRoom;
import noteasy.sundo.queryfactory.persistmodel.classroom.factory.ClassRoomRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRoomPersistenceManager extends JpaRepository<ClassRoom, Long>, BaseQueryFactory<ClassRoom, Long>, ClassRoomRepositoryCustom {
}
