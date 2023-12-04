package basic.thymeleaf.tmp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/basic")
@Controller
public class TmpController {

    @GetMapping("text-basic")
    public String textBasic(Model model) {
//        model.addAttribute("data", "Spring");
        model.addAttribute("data", "<b>Spring</b>"); // html 태그 사용불가
        return "basic/text-basic";
    }

    @GetMapping("text-unescaped")
    public String textUnescaped(Model model) {
        model.addAttribute("data", "<b>Spring</b>"); // html 태그 사용불가
        return "basic/text-unescaped";
    }

}
