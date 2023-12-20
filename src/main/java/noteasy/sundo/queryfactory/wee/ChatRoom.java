package noteasy.sundo.queryfactory.wee;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_chat_room")
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_id", nullable = false)
    private Long id;

    @Column(name = "chat_room_name", nullable = false)
    private String chatRoomName;

    /**
     * student와 wee유저의 id는 User의 id로 식별합니다. (Student, Teacher id X)
     */
    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "wee_id", nullable = false)
    private Long weeId;
}
