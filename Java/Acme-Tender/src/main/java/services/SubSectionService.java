
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		return subSectionRepository.findOne(subSectionId);
	}
}
