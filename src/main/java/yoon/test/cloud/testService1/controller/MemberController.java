package yoon.test.cloud.testService1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yoon.test.cloud.testService1.dto.request.LoginDto;
import yoon.test.cloud.testService1.dto.request.RegisterDto;
import yoon.test.cloud.testService1.dto.response.MemberResponse;
import yoon.test.cloud.testService1.dto.response.ResponseMessage;
import yoon.test.cloud.testService1.service.MemberService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/")
    public ResponseEntity<?> register(@RequestBody RegisterDto dto){
        ResponseMessage message = new ResponseMessage();
        MemberResponse result = memberService.register(dto);

        message.setStatus(HttpStatus.OK);
        message.setMessage(result.getName()+ "님 회원가입 성공");
        message.setData(result);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> mainPage(@RequestBody LoginDto dto){
        ResponseMessage message = new ResponseMessage();
        MemberResponse result = memberService.login(dto);

        message.setStatus(HttpStatus.OK);
        message.setMessage("로그인 성공");
        message.setData(result);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
