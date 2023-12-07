package noteasy.sundo.queryfactory.persistmodel.classroom.factory;

import noteasy.sundo.queryfactory.persistmodel.classroom.ClassRoom;

import java.util.Optional;

public interface ClassRoomQueryFactoryNeed {
    Optional<ClassRoom> findByGradeAndClassNum(Integer grade, Integer classNum);
}
