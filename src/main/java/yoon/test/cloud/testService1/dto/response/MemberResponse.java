package yoon.test.cloud.testService1.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponse {

    private String email;

    private String name;

    private String role;

    private String regdate;
}
