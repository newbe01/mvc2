package itemservice.typeconverter.formatter;

import itemservice.typeconverter.converter.IpPortToStringConverter;
import itemservice.typeconverter.converter.StringToIpPortConverter;
import itemservice.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.format.support.DefaultFormattingConversionService;

import static org.assertj.core.api.Assertions.*;

public class FormattingConversionServiceTest {

    @Test
    void formattingConversionService() {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();

        //converter
        conversionService.addConverter(new StringToIpPortConverter());
        conversionService.addConverter(new IpPortToStringConverter());

        IpPort ipPort = conversionService.convert("12.3.4.5:8080", IpPort.class);
        assertThat(ipPort).isEqualTo(new IpPort("12.3.4.5", 8080));

        //formatter
        conversionService.addFormatter(new TmpNumberFormatter());

        assertThat(conversionService.convert(1000, String.class)).isEqualTo("1,000");
        assertThat(conversionService.convert("1,000", Long.class)).isEqualTo(1000L);
    }
}
