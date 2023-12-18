package noteasy.sundo.queryfactory.persistmodel.wee;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Table(name = "tbl_chat_room")
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_id", nullable = false)
    private Long id;

    @Column(name = "chat_room_name", nullable = false)
    private String chatRoomName;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "wee_id", nullable = false)
    private Long weeId;
}
