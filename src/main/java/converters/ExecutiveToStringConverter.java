package converters;

import domain.Executive;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ExecutiveToStringConverter implements Converter<Executive, String> {

    @Override
    public String convert(final Executive data) {
        String result;

        if (data == null)
            result = null;
        else
            result = String.valueOf(data.getId());

        return result;
    }

}
