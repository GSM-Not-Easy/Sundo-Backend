package noteasy.sundo.queryfactory.persistmodel.classroom.factory;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import noteasy.sundo.queryfactory.BaseQueryFactory;
import noteasy.sundo.queryfactory.persistmodel.classroom.ClassRoom;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static noteasy.sundo.queryfactory.persistmodel.classroom.QClassRoom.classRoom;

@Component
@RequiredArgsConstructor
public class ClassRoomQueryFactory implements BaseQueryFactory<ClassRoom, Long>, ClassRoomQueryFactoryNeed {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<ClassRoom> queryById(Long id) {
        var result = queryFactory.selectFrom(classRoom)
                .where(classRoom.isDeleted.isFalse().and(classRoom.id.eq(id)))
                .fetchOne();

        return Optional.ofNullable(result);

    }

    @Override
    public void softDelete(ClassRoom entity) {
        queryFactory.update(classRoom)
                .where(classRoom.isDeleted.isFalse().and(classRoom.id.eq(entity.getId())))
                .execute();
    }

    @Override
    public void softDeleteById(Long id) {
        queryFactory.update(classRoom)
                .where(classRoom.isDeleted.isFalse().and(classRoom.id.eq(id)))
                .execute();
    }

    @Override
    public Optional<ClassRoom> findByGradeAndClassNum(Integer grade, Integer classNum) {
        var result = queryFactory.selectFrom(classRoom)
                .where(classRoom.isDeleted.isFalse()
                        .and(classRoom.grade.eq(grade).and(classRoom.classNum.eq(classNum))))
                .fetchOne();

        return Optional.ofNullable(result);
    }
}
