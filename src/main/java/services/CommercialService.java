
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Commercial;
import repositories.CommercialRepository;

@Service
@Transactional
public class CommercialService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private CommercialRepository commercialRepository;


	//Other methods

	public Collection<Commercial> getSubSectionCreatorsFromOfferId(int offerId) {

		return this.commercialRepository.getSubSectionCreatorsFromOfferId(offerId);

	}

}
