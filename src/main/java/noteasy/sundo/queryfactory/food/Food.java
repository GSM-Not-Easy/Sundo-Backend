package noteasy.sundo.queryfactory.food;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Food {

    private String id;
}
