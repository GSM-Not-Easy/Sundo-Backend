package noteasy.sundo.queryfactory.food.repository;

import noteasy.sundo.queryfactory.food.Food;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FoodRepository extends MongoRepository<Food, Long> {
}
