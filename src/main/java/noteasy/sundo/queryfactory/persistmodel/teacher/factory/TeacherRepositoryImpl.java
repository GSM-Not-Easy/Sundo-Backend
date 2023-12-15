package noteasy.sundo.queryfactory.persistmodel.teacher.factory;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import noteasy.sundo.queryfactory.BaseQueryFactory;
import noteasy.sundo.queryfactory.persistmodel.teacher.Teacher;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static noteasy.sundo.queryfactory.persistmodel.teacher.QTeacher.teacher;

@Component
@RequiredArgsConstructor
public class TeacherRepositoryImpl implements BaseQueryFactory<Teacher, Long> {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Teacher> queryById(Long id) {
        var result = queryFactory.selectFrom(teacher)
                .where(teacher.isDeleted.isFalse().and(teacher.id.eq(id)))
                .fetchOne();

        return Optional.ofNullable(result);
    }

    @Override
    public void delete(Teacher entity) {
        queryFactory.update(teacher)
                .where(teacher.isDeleted.isFalse().and(teacher.id.eq(entity.getId())))
                .set(teacher.isDeleted, false)
                .execute();
    }

    @Override
    public void deleteById(Long id) {
        queryFactory.update(teacher)
                .where(teacher.isDeleted.isFalse().and(teacher.id.eq(id)))
                .set(teacher.isDeleted, false)
                .execute();
    }

}
