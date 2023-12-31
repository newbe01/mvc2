package itemservice.typeconverter.controller;

import itemservice.typeconverter.type.IpPort;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TmpController {

    @GetMapping("/tmp-v1")
    public String tmpV1(HttpServletRequest request) {
        String data = request.getParameter("data"); // String type
        Integer intValue = Integer.valueOf(data);
        System.out.println("intValue = " + intValue);
        return "OK";
    }

    @GetMapping("/tmp-v2")
    public String tmpV2(@RequestParam Integer data) {   // String => int converting
        System.out.println("data = " + data);
        return "OK";
    }

    @GetMapping("ip-port")
    public String ipPort(@RequestParam IpPort ipPort) {
        System.out.println("ipPort.getIp() = " + ipPort.getIp());
        System.out.println("ipPort.getPort() = " + ipPort.getPort());

        return "ok";
    }

}
