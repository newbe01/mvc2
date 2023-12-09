package itemservice.validation.web.validation;

import itemservice.validation.web.validation.form.ItemSaveForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/validation/api/items")
public class ValidationItemApiController {

    @PostMapping("add")
    public Object addItem(@RequestBody @Validated ItemSaveForm form, BindingResult bindingResult) {

        log.info("API REQUEST");

        if (bindingResult.hasErrors()) {
            log.error(" error : {}", bindingResult);
            return bindingResult.getAllErrors();
        }

        log.info("access");
        return form;
    }

}
