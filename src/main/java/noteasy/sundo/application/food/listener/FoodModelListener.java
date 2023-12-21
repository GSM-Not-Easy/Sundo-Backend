package noteasy.sundo.application.food.listener;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.global.library.mongo.SequenceGenerator;
import noteasy.sundo.queryfactory.food.Food;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FoodModelListener extends AbstractMongoEventListener<Food> {

    private final SequenceGenerator sequenceGenerator;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Food> event) {
        event.getSource().setId(sequenceGenerator.generateSequence(Food.SEQUENCE_NAME));
    }

}
