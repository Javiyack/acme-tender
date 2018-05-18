
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CurriculumRepository;
import domain.Curriculum;

@Service
@Transactional
public class CurriculumService {

	// Managed repositories ------------------------------------------------
	@Autowired
	private CurriculumRepository		curriculumRepository;

	//Services

	// Constructor ----------------------------------------------------------
	public CurriculumService() {
		super();
	}

	// Methods CRUD ---------------------------------------------------------

	public Curriculum create() {

		final Curriculum curriculum = new Curriculum();

		return curriculum;
	}

	public Curriculum findOne(final int curriculumId) {
		Curriculum result;

		result = this.curriculumRepository.findOne(curriculumId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Curriculum> findAll() {

		Collection<Curriculum> result;

		result = this.curriculumRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Curriculum save(final Curriculum curriculum) {

		Assert.notNull(curriculum);

		final Curriculum saved = this.curriculumRepository.save(curriculum);

		return saved;
	}

	public void delete(final Curriculum curriculum) {
		Assert.notNull(curriculum);

		this.curriculumRepository.delete(curriculum);
	}

	public void flush() {
		this.curriculumRepository.flush();

	}
	

}
