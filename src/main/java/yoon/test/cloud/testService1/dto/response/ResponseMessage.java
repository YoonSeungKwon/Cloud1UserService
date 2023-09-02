package yoon.test.cloud.testService1.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ResponseMessage {

    private HttpStatus status;

    private String message;

    private Object data;

    public ResponseMessage(){
        this.status = HttpStatus.BAD_REQUEST;
        this.message = null;
        this.data = null;
    }
}
