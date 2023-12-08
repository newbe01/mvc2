package itemservice.validation.message.validation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.ObjectError;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageCodesResolverTest {

    MessageCodesResolver resolver = new DefaultMessageCodesResolver();

    @Test
    void messageCodesResolverObject() {

        String[] codes = resolver.resolveMessageCodes("required", "item");

        for (String code : codes) {
            System.out.println("code = " + code);
//        new ObjectError("item", new String[]{"required.item", "required"})
        }
        assertThat(codes).containsExactly("required.item", "required");
    }

    @Test
    void messageCodesResolverField() {

        String[] codes = resolver.resolveMessageCodes("required", "item", "itemName", String.class);
        for (String code : codes) {
            System.out.println("code = " + code);
        }

        assertThat(codes).containsExactly(
                "required.item.itemName",
                "required.itemName",
                "required.java.lang.String",
                "required"
        );
    }


}
