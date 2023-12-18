package noteasy.sundo.web.wee;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.application.wee.executor.WeeExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wee")
public class WeeController {

    private final WeeExecutor weeExecutor;


    @PostMapping("/room")
    public ResponseEntity<Void> createChatRoom() {
        weeExecutor.createChatRoom();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
