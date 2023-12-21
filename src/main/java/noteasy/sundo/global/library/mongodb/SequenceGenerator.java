package noteasy.sundo.global.library.mongodb;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@RequiredArgsConstructor
public class SequenceGenerator {

    /**
     * MongoDB에 Auto Increment 옵션을 주기위한 Sequence Generator
     */

    private final MongoOperations mongoOperations;

    public Long generateSequence(String seqName) {
        DatabaseSequence databaseSequence = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq", 1), options().returnNew(true).upsert(true), DatabaseSequence.class);

        return !Objects.isNull(databaseSequence) ? databaseSequence.getSeq() : 1;
    }

}
