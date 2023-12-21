package noteasy.sundo.application.food.executor;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.application.food.dto.FoodDto;
import noteasy.sundo.application.food.support.FoodSupport;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FoodExecutor {

    private final FoodSupport foodSupport;

    // 비동기로 급식 등록을 처리한다.
    @Async
    public void executeCreateFood(FoodDto.Request request) {
        foodSupport.saveFood(request);
    }

}
