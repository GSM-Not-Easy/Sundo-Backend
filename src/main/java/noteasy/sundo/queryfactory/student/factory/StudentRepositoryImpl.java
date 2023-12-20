package noteasy.sundo.queryfactory.student.factory;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import noteasy.sundo.queryfactory.BaseQueryFactory;
import noteasy.sundo.queryfactory.student.Student;
import noteasy.sundo.queryfactory.user.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static noteasy.sundo.queryfactory.student.QStudent.*;


@Component
@RequiredArgsConstructor
public class StudentRepositoryImpl implements BaseQueryFactory<Student, Long>, StudentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Student> queryById(Long id) {
        var result = queryFactory.selectFrom(student)
                .where(student.isDeleted.isFalse().and(student.id.eq(id)))
                .fetchOne();

        return Optional.ofNullable(result);
    }

    @Override
    public void delete(Student entity) {
        queryFactory.update(student)
                .where(student.isDeleted.isFalse().and(student.id.eq(entity.getId())))
                .set(student.isDeleted, true)
                .execute();
    }

    @Override
    public void deleteById(Long id) {
        queryFactory.update(student)
                .where(student.isDeleted.isFalse().and(student.id.eq(id)))
                .set(student.isDeleted, true)
                .execute();
    }

    @Override
    public Optional<Student> findByUser(User user) {
        var result = queryFactory.selectFrom(student)
                .where(student.isDeleted.isFalse().and(student.user.id.eq(user.getId())))
                .fetchOne();

        return Optional.ofNullable(result);
    }
}
