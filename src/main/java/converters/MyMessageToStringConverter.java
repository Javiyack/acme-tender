package converters;

import domain.MyMessage;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class MyMessageToStringConverter implements Converter<MyMessage, String> {

    @Override
    public String convert(final MyMessage data) {
        String result;

        if (data == null)
            result = null;
        else
            result = String.valueOf(data.getId());

        return result;
    }

}