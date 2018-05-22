
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.ColaborationRequest;
import repositories.ColaborationRequestRepository;

@Component
@Transactional
public class StringToColaborationRequestConverter implements Converter<String, ColaborationRequest> {

	@Autowired
	private ColaborationRequestRepository colaborationRequestRepository;


	@Override
	public ColaborationRequest convert(final String str) {
		ColaborationRequest result;
		Integer id;
		try {
			if (StringUtils.isEmpty(str))
				result = null;
			else {
				id = Integer.valueOf(str);
				result = this.colaborationRequestRepository.findOne(id);
			}

		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
