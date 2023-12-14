package itemservice.exception.api;

import itemservice.exception.exception.UserException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ApiExceptionController {

    @GetMapping("/api/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id, HttpServletRequest request) {

        if (id.equals("ex")) {
            throw new RuntimeException("wrong member");
        }

        if (id.equals("bad")) {
            throw new IllegalArgumentException("wrong args");
        }

        if (id.equals("user-ex")) {
            throw new UserException("user exception");
        }

        return new MemberDto(id, "tmp " + id);
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String memberId;
        private String name;
    }

}
