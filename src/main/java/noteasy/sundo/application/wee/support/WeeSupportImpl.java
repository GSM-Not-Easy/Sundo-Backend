package noteasy.sundo.application.wee.support;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.global.error.GlobalException;
import noteasy.sundo.global.library.security.SecurityContextUtil;
import noteasy.sundo.queryfactory.persistmodel.student.manager.StudentRepository;
import noteasy.sundo.queryfactory.persistmodel.teacher.Subject;
import noteasy.sundo.queryfactory.persistmodel.teacher.manager.TeacherRepository;
import noteasy.sundo.queryfactory.persistmodel.user.User;
import noteasy.sundo.queryfactory.persistmodel.wee.ChatRoom;
import noteasy.sundo.queryfactory.persistmodel.wee.repository.ChatRoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WeeSupportImpl implements WeeSupport {

    private final ChatRoomRepository chatRoomRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final SecurityContextUtil contextUtil;

    @Override
    public void createChatRoom() {
        User currentUser = contextUtil.currentUser();
        var student = studentRepository.findByUser(currentUser)
                .orElseThrow(() -> new GlobalException("Not Found Student..", HttpStatus.NOT_FOUND));;

        var wee = teacherRepository.findBySubject(Subject.WEE)
                .orElseThrow(() -> new GlobalException("Not Found Wee Class Teacher..", HttpStatus.NOT_FOUND));

        if(chatRoomRepository.existsByStudentIdAndWeeId(student.getId(), wee.getId())) {
            throw new GlobalException("Already Exists Chat Room..", HttpStatus.ALREADY_REPORTED);
        }

        ChatRoom chatRoom = ChatRoom.builder()
                .chatRoomName(String.format("%s학생과 위클래스 선생님의 채팅방", student.getUser().getName()))
                .studentId(student.getId())
                .weeId(wee.getId())
                .build();

        chatRoomRepository.save(chatRoom);

    }
}
