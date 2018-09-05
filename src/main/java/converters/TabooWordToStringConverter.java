package converters;

import domain.TabooWord;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class TabooWordToStringConverter implements Converter<TabooWord, String> {

    @Override
    public String convert(final TabooWord data) {
        String result;

        if (data == null)
            result = null;
        else
            result = String.valueOf(data.getId());

        return result;
    }

}
