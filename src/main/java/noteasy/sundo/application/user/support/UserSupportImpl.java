package noteasy.sundo.application.user.support;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.application.auth.dto.TokenDto;
import noteasy.sundo.application.user.dto.UserDto;
import noteasy.sundo.global.error.GlobalException;
import noteasy.sundo.global.library.security.token.JwtTokenGenerator;
import noteasy.sundo.queryfactory.persistmodel.classroom.ClassRoom;
import noteasy.sundo.queryfactory.persistmodel.classroom.manager.ClassRoomPersistenceManager;
import noteasy.sundo.queryfactory.persistmodel.student.Student;
import noteasy.sundo.queryfactory.persistmodel.student.manager.StudentRepository;
import noteasy.sundo.queryfactory.persistmodel.teacher.Teacher;
import noteasy.sundo.queryfactory.persistmodel.teacher.manager.TeacherRepository;
import noteasy.sundo.queryfactory.persistmodel.user.ApproveStatus;
import noteasy.sundo.queryfactory.persistmodel.user.Authority;
import noteasy.sundo.queryfactory.persistmodel.user.User;
import noteasy.sundo.queryfactory.persistmodel.user.manager.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSupportImpl implements UserSupport {

    private final UserRepository userPm;
    private final StudentRepository studentPm;
    private final TeacherRepository teacherPm;
    private final ClassRoomPersistenceManager classRoomPm;
    private final PasswordEncoder encoder;

    private final JwtTokenGenerator jwtTokenGenerator;

    @Override
    public void save(User user) {
        userPm.save(user);
    }

    @Override
    public User findUserById(Long id) {
        var user = userPm.findById(id)
                .orElseThrow(() -> new GlobalException("Not Found User", HttpStatus.NOT_FOUND));
        return user;
    }

    @Override
    public void deleteById(Long id) {
        userPm.deleteById(id);
    }

    @Override
    public void delete(User user) {
        userPm.delete(user);
    }

    @Override
    public void signUpValidate(String email) {
        var isExist = userPm.existsByEmail(email);

        if(isExist) {
            throw new GlobalException("Already Exist User", HttpStatus.ALREADY_REPORTED);
        }
    }

    @Override
    public void signUpTeacher(UserDto.TeacherSignUpRequest request) {

        ClassRoom classRoom = null;

        if(request.getIsHomeroom()) {
             classRoom = classRoomPm.findByGradeAndClassNum(request.getGrade(), request.getClassNum())
                    .orElseThrow(() -> new GlobalException("Not Found ClassRoom by grade and classNum", HttpStatus.NOT_FOUND));
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .name(request.getName())
                .authority(Authority.TEACHER)
                .approveStatus(ApproveStatus.PENDING)
                .isDeleted(false)
                .build();

        Teacher teacher = Teacher.builder()
                .user(user)
                .subject(request.getSubject())
                .classRoom(classRoom)
                .build();

        teacherPm.save(teacher);
    }

    @Override
    public void signUpStudent(UserDto.StudentSignUpRequest request) {

        ClassRoom classRoom = classRoomPm.findByGradeAndClassNum(request.getGrade(), request.getClassNum())
                .orElseThrow(() -> new GlobalException("Not Found ClassRoom by grade and classNum", HttpStatus.NOT_FOUND));

        User user = User.builder()
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .name(request.getName())
                .authority(Authority.STUDENT)
                .approveStatus(ApproveStatus.PENDING)
                .isDeleted(false)
                .build();

        Student student = Student.builder()
                .user(user)
                .number(request.getNumber())
                .classRoom(classRoom)
                .isDeleted(false)
                .build();

        studentPm.save(student);
    }

    @Override
    public TokenDto.Response login(UserDto.LoginRequest request) {
        User user = userPm.findByEmail(request.getEmail())
                .orElseThrow(() -> new GlobalException("Not Found User In Login", HttpStatus.NOT_FOUND));

        if(!encoder.matches(user.getPassword(), request.getPassword())) {
            throw new GlobalException("Password is not matched", HttpStatus.UNAUTHORIZED);
        }

        TokenDto.Response tokenResult = jwtTokenGenerator.generate(user.getId(), user.getAuthority());
        return tokenResult;
    }

}
