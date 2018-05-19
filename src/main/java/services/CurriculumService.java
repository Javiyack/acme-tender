
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Commercial;
import domain.Curriculum;
import domain.SubSection;
import repositories.CurriculumRepository;

@Service
@Transactional
public class CurriculumService {

	/*
	 * TODO
	 * restricci�n1 --> LISTAR: puede listar curr�culums a partir del id de una subsecci�n cualquier comercial que haya creado una subsecci�n en la oferta a la que pertenece
	 * la subsecci�n que me pasan y el comercial que haya creado la oferta a la que pertenece la subsecci�n que me pasan.
	 * 
	 * restricci�n2 --> MOSTRAR: puede mostrar un curr�culum cualquier comercial que haya creado una subsecci�n en la oferta a la que pertenece
	 * la subsecci�n a la que pertenece el curr�culum y el comercial que haya creado la oferta a la que pertenece la subsecci�n a la que pertenece el curr�culum.
	 * 
	 * restricci�n3 --> CREAR: puede crear un curr�culum el comercial que haya creado la subsecci�n a la que pertenece el curr�culum.
	 * 
	 * restricci�n4 --> EDITAR: puede editar un curr�culum el comercial que lo haya creado.
	 * 
	 * restricci�n5 --> BORRAR: puede borrar un curr�culum el comercial que haya creado la subsecci�n a la que pertenece el curr�culum.
	 * 
	 */

	// Managed repository ------------------------------------------------
	@Autowired
	private CurriculumRepository	curriculumRepository;

	//Supporting services
	@Autowired
	private ActorService			actorService;

	@Autowired
	private SubSectionService		subSectionService;

	@Autowired
	private CommercialService		commercialService;


	//CRUD methods

	public Curriculum create() {

		Curriculum result;

		result = new Curriculum();

		return result;

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

	//Other methods

	public Collection<Curriculum> getCurriculumsFromSubSectionId(int subSectionId) {
		checkListAndDisplay(subSectionId);
		return this.curriculumRepository.getCurriculumsFromSubSectionId(subSectionId);

	}

	public void checkListAndDisplay(int subSectionId) {

		Actor principal;
		principal = this.actorService.findByPrincipal();
		Assert.isTrue(principal instanceof Commercial);

		SubSection subSection;
		subSection = this.subSectionService.findOne(subSectionId);

		Collection<Commercial> subSectionCreators = this.commercialService.getSubSectionCreatorsFromOfferId(subSection.getOffer().getId());
		Assert.isTrue(subSection.getOffer().getCommercial().equals(principal) || subSectionCreators.contains(principal));

	}

}
