package form.message.message;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class MessageSourceTest {

    @Autowired
    MessageSource messageSource;

    @Test
    void getMessage() {
        String result = messageSource.getMessage("hello", null, null);
        assertThat(result).isEqualTo("hello");
    }

    @Test
    void notFoundMessageCode() {

        assertThatThrownBy(() -> messageSource.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void notFoundMessageCodeDefaultMessage() {

        String ms = messageSource.getMessage("no_code", null, "default message", null);
        assertThat(ms).isEqualTo("default message");
    }

    @Test
    void argumentMessage() {

        String ms = messageSource.getMessage("hello.name", new Object[]{"test code"}, null);
        assertThat(ms).isEqualTo("hello test code");
    }

    @Test
    void defaultLang() {

        assertThat(messageSource.getMessage("hello", null, Locale.KOREAN)).isEqualTo("안녕");
        assertThat(messageSource.getMessage("hello", null, Locale.KOREA)).isEqualTo("안녕");
    }

    @Test
    void enLang() {

        assertThat(messageSource.getMessage("hello", null, null)).isEqualTo("hello");
    }
}
