package noteasy.sundo.global.library.mongo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * AUTO INCREMENT 연속성 정보를 저장하는 컬렉션
 */
@Getter
@Setter
@Document(collation = "database_sequence")
public class DatabaseSequence {

    @Id
    private String id;
    private Long seq;
}
