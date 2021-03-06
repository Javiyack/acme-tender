package converters;

import domain.Commercial;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class CommercialToStringConverter implements Converter<Commercial, String> {

    @Override
    public String convert(final Commercial data) {
        String result;

        if (data == null)
            result = null;
        else
            result = String.valueOf(data.getId());

        return result;
    }

}
