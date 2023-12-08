package itemservice.validation.web.validation;

import itemservice.validation.domain.item.Item;
import itemservice.validation.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/validation/v2/items")
@RequiredArgsConstructor
public class ValidationItemControllerV2 {

    private final ItemRepository itemRepository;
    private final ItemValidator itemValidator;

//    @InitBinder
//    public void init(WebDataBinder binder) {
//        binder.addValidators(itemValidator);
//    }

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "validation/v2/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "validation/v2/addForm";
    }

//    @PostMapping("/add")
    public String addItemV1(
            @ModelAttribute Item item,
            BindingResult bindingResult,    // bindingResult 는 검증객체 바로 다음에 위치해야한다
            RedirectAttributes redirectAttributes,
            Model model
    ) {

        if (!StringUtils.hasText(item.getItemName())) {
            bindingResult.addError(new FieldError("item", "itemName", "이름 필수"));
        }

        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            bindingResult.addError(
                    new FieldError("item", "price", "가격은 1000 ~ 100000000 까지")
            );
        }

        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
            bindingResult.addError(
                    new FieldError("item", "quantity", "수량은 최대 9999까지")
            );
        }

        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.addError(
                        new ObjectError("item", "가격 * 수량은 10000 이상, : " + resultPrice)
                );
            }
        }

        // 실패시 입력폼으로
        if (bindingResult.hasErrors()) {
            log.info("bindingResult : {}", bindingResult);
            return "validation/v2/addForm";
        }

        // 성공로직

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

//    @PostMapping("/add")
    public String addItemV2(
            @ModelAttribute Item item,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model
    ) {

        if (!StringUtils.hasText(item.getItemName())) {
            bindingResult.addError(new FieldError(
                    "item", "itemName", item.getItemName(),
                    false, null, null,  "이름 필수")
            );
        }

        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            bindingResult.addError(
                    new FieldError("item", "price", item.getPrice(),
                            false, null, null, "가격은 1000 ~ 100000000 까지")
            );
        }

        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
            bindingResult.addError(
                    new FieldError("item", "quantity", item.getQuantity(),
                            false, null, null, "수량은 최대 9999까지")
            );
        }

        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.addError(
                        new ObjectError("item", "가격 * 수량은 10000 이상, : " + resultPrice)
                );
            }
        }

        // 실패시 입력폼으로
        if (bindingResult.hasErrors()) {
            log.info("bindingResult : {}", bindingResult);
            return "validation/v2/addForm";
        }

        // 성공로직

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

//    @PostMapping("/add")
    public String addItemV3(
            @ModelAttribute Item item,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model
    ) {

        log.info("objectName : {}", bindingResult.getObjectName());
        log.info("target : {}", bindingResult.getTarget());

        if (!StringUtils.hasText(item.getItemName())) {
            bindingResult.addError(new FieldError(
                    "item", "itemName", item.getItemName(),
                    false, new String[]{"required.item.itemName", "default-message"},
                    null,  "이름 필수")
            );
        }

        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            bindingResult.addError(
                    new FieldError("item", "price", item.getPrice(),
                            false,
                            new String[]{"range.item.price"},
                            new Object[]{1000, 10000000}, "가격은 1000 ~ 100000000 까지")
            );
        }

        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
            bindingResult.addError(
                    new FieldError("item", "quantity", item.getQuantity(),
                            false,
                            new String[]{"max.item.quantity"},
                            new Object[]{9999}, "수량은 최대 9999까지")
            );
        }

        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.addError(
                        new ObjectError("item",
                                new String[]{"totalPriceMin"},
                                new Object[]{10000, resultPrice},
                                "가격 * 수량은 10000 이상, : " + resultPrice)
                );
            }
        }

        // 실패시 입력폼으로
        if (bindingResult.hasErrors()) {
            log.info("bindingResult : {}", bindingResult);
            return "validation/v2/addForm";
        }

        // 성공로직

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

//    @PostMapping("/add")
    public String addItemV4(
            @ModelAttribute Item item,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model
    ) {

        log.info("objectName : {}", bindingResult.getObjectName());
        log.info("target : {}", bindingResult.getTarget());

//        ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "itemName", "required"); 아래랑 같음
        if (!StringUtils.hasText(item.getItemName())) {
            bindingResult.rejectValue("itemName", "required");
        }

        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            bindingResult.rejectValue("price", "range", new Object[]{1000, 1000000}, null);
        }

        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
            bindingResult.rejectValue("quantity", "max", new Object[]{9999}, null);
        }

        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }

        // 실패시 입력폼으로
        if (bindingResult.hasErrors()) {
            log.info("bindingResult : {}", bindingResult);
            return "validation/v2/addForm";
        }

        // 성공로직

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

//    @PostMapping("/add")
    public String addItemV5(
            @ModelAttribute Item item,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model
    ) {

        itemValidator.validate(item, bindingResult);

        // 실패시 입력폼으로
        if (bindingResult.hasErrors()) {
            log.info("bindingResult : {}", bindingResult);
            return "validation/v2/addForm";
        }

        // 성공로직

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

    @PostMapping("/add")
    public String addItemV6(
            @Validated
            @ModelAttribute Item item,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model
    ) {

        // 실패시 입력폼으로
        if (bindingResult.hasErrors()) {
            log.info("bindingResult : {}", bindingResult);
            return "validation/v2/addForm";
        }

        // 성공로직

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/validation/v2/items/{itemId}";
    }

}
