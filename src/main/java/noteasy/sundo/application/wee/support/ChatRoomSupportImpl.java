package noteasy.sundo.application.wee.support;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.global.error.GlobalException;
import noteasy.sundo.global.library.security.SecurityContextUtil;
import noteasy.sundo.queryfactory.persistmodel.student.manager.StudentRepository;
import noteasy.sundo.queryfactory.persistmodel.teacher.Subject;
import noteasy.sundo.queryfactory.persistmodel.teacher.Teacher;
import noteasy.sundo.queryfactory.persistmodel.teacher.manager.TeacherRepository;
import noteasy.sundo.queryfactory.persistmodel.user.User;
import noteasy.sundo.queryfactory.persistmodel.wee.ChatRoom;
import noteasy.sundo.queryfactory.persistmodel.wee.repository.ChatRoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatRoomSupportImpl implements ChatRoomSupport {

    private final ChatRoomRepository chatRoomRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final SecurityContextUtil contextUtil;

    @Override
    public void createChatRoom() {
        User currentUser = contextUtil.currentUser();
        var student = studentRepository.findByUser(currentUser)
                .orElseThrow(() -> new GlobalException("Not Found Student..", HttpStatus.NOT_FOUND));;

        var wee = teacherRepository.findByStudentAndSubject(Subject.WEE)
                .orElseThrow(() -> new GlobalException("Not Found Wee Class Teacher..", HttpStatus.NOT_FOUND));

        ChatRoom.builder()
                .roomName

    }
}
