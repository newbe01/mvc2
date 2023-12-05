package form.itemservice.domain.item;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * FAST, NORMAL, SLOW
 */
@AllArgsConstructor
@Data
public class DeliveryCode {

    private String code;

    private String displayName;

}
