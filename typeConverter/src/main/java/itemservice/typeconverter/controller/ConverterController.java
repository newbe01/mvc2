package itemservice.typeconverter.controller;

import itemservice.typeconverter.type.IpPort;
import lombok.Data;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ConverterController {

    @GetMapping("/converter-view")
    public String convertView(Model model) {
        model.addAttribute("number", 10000);
        model.addAttribute("ipPort", new IpPort("123.4.5.6", 7890));
        return "converter-view";
    }

    @GetMapping("/converter/edit")
    public String converterForm(Model model) {
        IpPort ipPort = new IpPort("123.4.5.6", 8901);

        Form form = new Form(ipPort);
        model.addAttribute("form", form);

        return "converter-form";
    }

    @PostMapping("/converter/edit")
    public String convertEdit(@ModelAttribute Form form, Model model) {
        IpPort ipPort = form.getIpPort();
        model.addAttribute("ipPort", ipPort);

        return "converter-view";
    }

    @Data
    static class Form {
        private IpPort ipPort;

        public Form(IpPort ipPort) {
            this.ipPort = ipPort;
        }
    }

}
