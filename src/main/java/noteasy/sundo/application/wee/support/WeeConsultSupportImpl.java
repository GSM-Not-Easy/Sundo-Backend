package noteasy.sundo.application.wee.support;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.application.wee.dto.ConsultDto;
import noteasy.sundo.global.library.security.SecurityContextUtil;
import noteasy.sundo.queryfactory.student.Student;
import noteasy.sundo.queryfactory.student.manager.StudentRepository;
import noteasy.sundo.queryfactory.teacher.manager.TeacherRepository;
import noteasy.sundo.queryfactory.user.User;
import noteasy.sundo.queryfactory.wee.repository.ConsultRepository;
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


    }
}
