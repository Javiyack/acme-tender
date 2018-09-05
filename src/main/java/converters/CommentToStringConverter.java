package converters;

import domain.Comment;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class CommentToStringConverter implements Converter<Comment, String> {

    @Override
    public String convert(final Comment data) {
        String result;

        if (data == null)
            result = null;
        else
            result = String.valueOf(data.getId());

        return result;
    }

}
