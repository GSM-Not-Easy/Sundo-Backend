package noteasy.sundo.queryfactory.classroom.factory;

import noteasy.sundo.queryfactory.classroom.ClassRoom;

import java.util.Optional;

public interface ClassRoomRepositoryCustom {
    Optional<ClassRoom> findByGradeAndClassNum(Integer grade, Integer classNum);
}
