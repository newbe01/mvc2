package itemservice.typeconverter;

import itemservice.typeconverter.converter.IntegerToStringConverter;
import itemservice.typeconverter.converter.IpPortToStringConverter;
import itemservice.typeconverter.converter.StringToIntegerConverter;
import itemservice.typeconverter.converter.StringToIpPortConverter;
import itemservice.typeconverter.formatter.TmpNumberFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 등록한 converter가 우선순위
//        registry.addConverter(new StringToIntegerConverter()); formatter 보다 우선순위

//        registry.addConverter(new IntegerToStringConverter());
        registry.addConverter(new StringToIpPortConverter());
        registry.addConverter(new IpPortToStringConverter());

        registry.addFormatter(new TmpNumberFormatter());
    }

}
