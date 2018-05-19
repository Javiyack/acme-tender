
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.SubSection;
import repositories.SubSectionRepository;

@Service
@Transactional
public class SubSectionService {

	// Managed repositories ------------------------------------------------
	@Autowired
	private SubSectionRepository subSectionRepository;


	//CRUD methods
	public SubSection findOne(int subSectionId) {
		SubSection result;
		result = subSectionRepository.findOne(subSectionId);
		Assert.isTrue(result != null);
		return result;
	}
}
