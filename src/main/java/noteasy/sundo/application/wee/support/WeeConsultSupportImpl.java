package noteasy.sundo.application.wee.support;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.application.wee.dto.ConsultDto;
import noteasy.sundo.global.error.GlobalException;
import noteasy.sundo.global.library.security.SecurityContextUtil;
import noteasy.sundo.queryfactory.student.Student;
import noteasy.sundo.queryfactory.student.manager.StudentRepository;
import noteasy.sundo.queryfactory.teacher.Subject;
import noteasy.sundo.queryfactory.teacher.Teacher;
import noteasy.sundo.queryfactory.teacher.manager.TeacherRepository;
import noteasy.sundo.queryfactory.wee.Consult;
import noteasy.sundo.queryfactory.wee.repository.ConsultRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WeeConsultSupportImpl implements WeeConsultSupport {

    private final ConsultRepository consultRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final SecurityContextUtil securityContextUtil;


    // student
    @Override
    public void createConsultPlan(ConsultDto.CreateConsultRequest request) {
        Student student = securityContextUtil.currentStudent();

        Boolean isExistConsult = consultRepository.existsByStudentIdAndConsultDate(student.getId(), request.getConsultDate());

        if(isExistConsult) {
            throw new GlobalException("Already Exist Consult..", HttpStatus.ALREADY_REPORTED);
        }

        Teacher wee = teacherRepository.findBySubject(Subject.WEE)
                .orElseThrow(() -> new GlobalException("Not Found Wee Teacher", HttpStatus.NOT_FOUND));

        Consult consult = Consult.builder()
                .consultDate(request.getConsultDate())
                .period(request.getPeriod())
                .student(student)
                .teacher(wee)
                .isDeleted(false)
                .build();

        consultRepository.save(consult);
    }
}
