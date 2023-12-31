package noteasy.sundo.web.wee;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.application.wee.dto.ChatDto;
import noteasy.sundo.application.wee.dto.ChatRoomDto;
import noteasy.sundo.application.wee.dto.ConsultDto;
import noteasy.sundo.application.wee.executor.WeeExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

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

    @GetMapping(value = "/room/{room_id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Flux<ChatDto.Response>> queryMessage(@PathVariable("room_id") Long roomId) {
        Flux<ChatDto.Response> flux = weeExecutor.executeQueryMessage(roomId);
        return ResponseEntity.ok(flux.subscribeOn(Schedulers.boundedElastic()));
    }

    @GetMapping("/room/my")
    public ResponseEntity<ChatRoomDto.Response> queryMyChatRoom() {
        ChatRoomDto.Response response = weeExecutor.executeQueryMyChatRoom();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/room/all")
    public ResponseEntity<ChatRoomDto.Responses> queryAllChatRoom() {
        ChatRoomDto.Responses responses = weeExecutor.executeQueryAllChatRoom();
        return ResponseEntity.ok(responses);
    }

    @PostMapping("/consult")
    public ResponseEntity<Void> createConsultPlan(@Validated @RequestBody ConsultDto.CreateConsultRequest request) {
        weeExecutor.executeCreateConsultPlan(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
