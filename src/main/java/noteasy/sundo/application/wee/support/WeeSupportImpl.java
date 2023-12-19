package noteasy.sundo.application.wee.support;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.application.wee.dto.ChatDto;
import noteasy.sundo.global.error.GlobalException;
import noteasy.sundo.global.library.security.SecurityContextUtil;
import noteasy.sundo.queryfactory.persistmodel.student.Student;
import noteasy.sundo.queryfactory.persistmodel.student.manager.StudentRepository;
import noteasy.sundo.queryfactory.persistmodel.teacher.Subject;
import noteasy.sundo.queryfactory.persistmodel.teacher.Teacher;
import noteasy.sundo.queryfactory.persistmodel.teacher.manager.TeacherRepository;
import noteasy.sundo.queryfactory.persistmodel.user.User;
import noteasy.sundo.queryfactory.persistmodel.user.manager.UserRepository;
import noteasy.sundo.queryfactory.persistmodel.wee.ChatMessage;
import noteasy.sundo.queryfactory.persistmodel.wee.ChatRoom;
import noteasy.sundo.queryfactory.persistmodel.wee.MessageDirection;
import noteasy.sundo.queryfactory.persistmodel.wee.repository.ChatMessageRepository;
import noteasy.sundo.queryfactory.persistmodel.wee.repository.ChatRoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class WeeSupportImpl implements WeeSupport {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final SecurityContextUtil contextUtil;
    private final UserRepository userRepository;

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

    @Override
    public Mono<ChatDto.Response> sendMessage(Long roomId, ChatDto.Request request) {
        User currentUser = contextUtil.currentUser();
        Long toUserId = request.getTo();

        ChatRoom chatRoom = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new GlobalException("Not Found Wee Chat Room..", HttpStatus.NOT_FOUND));

        User toUser = userRepository.queryById(toUserId)
                .orElseThrow(() -> new GlobalException("ToUser Not Found Exception", HttpStatus.NOT_FOUND));

        if(currentUser.getId().equals(toUserId)) {
            throw new GlobalException("Sender And Receiver is same user", HttpStatus.BAD_REQUEST);
        }

        /**
         * MessageDirection을 통해서 메세지 sender, reciever를 결정한다.
         */
        if(request.getMessageDirection() == MessageDirection.ToStudent) {
            Student student = studentRepository.findByUser(toUser)
                    .orElseThrow(() -> new GlobalException("Student Not Found Exception", HttpStatus.NOT_FOUND));

            validateChatRoomAndStudent(chatRoom, toUserId);

            ChatMessage chatMessage = createChatMessage(
                    roomId,
                    request.getMessage(),
                    MessageDirection.ToStudent
                    );

            chatMessageRepository.save(chatMessage);

            return Mono.just(ChatDto.of(chatMessage, toUserId, currentUser.getId()));
        } else {
            Teacher wee = teacherRepository.findBySubject(Subject.WEE)
                    .orElseThrow(() -> new GlobalException("Not Found Wee Class Teacher..", HttpStatus.NOT_FOUND));

            validateChatRoomAndStudent(chatRoom, currentUser.getId());

            ChatMessage chatMessage = createChatMessage(
                    roomId,
                    request.getMessage(),
                    MessageDirection.ToWee
            );

            chatMessageRepository.save(chatMessage);

            return Mono.just(ChatDto.of(chatMessage, toUserId, currentUser.getId()));
        }

    }

    @Override
    public Flux<ChatDto.Response> queryMessage(Long roomId) {

        User currentUser = contextUtil.currentUser();

        ChatRoom chatRoom = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new GlobalException("Not Found ChatRoom Repository", HttpStatus.NOT_FOUND));


        if(!(chatRoom.getStudentId().equals(currentUser.getId()) || chatRoom.getWeeId().equals(currentUser.getId()))) {
            throw new GlobalException("Forbidden to Access ChatRoom", HttpStatus.FORBIDDEN);
        }

        Flux<ChatDto.Response> chatFlux = chatMessageRepository.findByRoomIdSortedByCreatedAt(roomId)
                .map(message -> convertChatMessage(message, chatRoom));

        return chatFlux;
    }

    private ChatDto.Response convertChatMessage(ChatMessage chatMessage, ChatRoom chatRoom) {
        if(chatMessage.getMessageDirection() == MessageDirection.ToStudent) {
            return ChatDto.of(chatMessage, chatRoom.getStudentId(), chatRoom.getWeeId());
        } else {
            return ChatDto.of(chatMessage, chatRoom.getWeeId(), chatRoom.getStudentId());
        }
    }

    private ChatMessage createChatMessage(Long roomId, String message, MessageDirection messageDirection) {
        return ChatMessage.builder()
                .roomId(roomId)
                .message(message)
                .messageDirection(messageDirection)
                .createdAt(LocalDateTime.now())
                .build();
    }

    private void validateChatRoomAndStudent(ChatRoom chatRoom, Long studentId) {
        if(!chatRoom.getStudentId().equals(studentId)) {
            throw new GlobalException("ChatRoom's Student is not matched Student who send a request in Wee", HttpStatus.FORBIDDEN);
        }
    }

}
