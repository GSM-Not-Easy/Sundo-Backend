package noteasy.sundo.web.food;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.application.food.dto.FoodDto;
import noteasy.sundo.application.food.executor.FoodExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/food")
public class FoodController {

    private final FoodExecutor foodExecutor;

    @PostMapping
    public ResponseEntity<Void> createFood(@RequestBody @Validated FoodDto.Request request) {
        foodExecutor.executeCreateFood(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
