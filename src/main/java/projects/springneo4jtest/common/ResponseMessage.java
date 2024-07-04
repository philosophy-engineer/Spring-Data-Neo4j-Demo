package projects.springneo4jtest.common;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder @Getter
public class ResponseMessage {

    private Status http;
    private Object content;


    @Getter
    public static class Status {

        private final HttpStatus status;
        private final int code;

        public Status(HttpStatus status) {
            this.status = status;
            this.code = status.value();
        }
    }

}
