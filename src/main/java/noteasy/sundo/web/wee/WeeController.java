package noteasy.sundo.web.wee;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.application.wee.dto.ChatDto;
import noteasy.sundo.application.wee.executor.WeeExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wee")
public class WeeController {

    private final WeeExecutor weeExecutor;


    @PostMapping("/room")
    public ResponseEntity<Void> createChatRoom() {
        weeExecutor.executeCreateChatRoom();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/room/{room_id}")
    public ResponseEntity<Mono<ChatDto.Response>> sendMessage(@PathVariable("room_id") Long roomId, @RequestBody ChatDto.Request request) {
        Mono<ChatDto.Response> response = weeExecutor.executeSendMessage(roomId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
