
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Administrative;
import domain.Commercial;
import domain.Curriculum;
import domain.SubSection;
import repositories.CurriculumRepository;

@Service
@Transactional
public class CurriculumService {

	/*
	 * TODO
	 * restricción1 --> LISTAR: puede listar currículums a partir del id de una subsección cualquier comercial o administrativo que haya creado una subsección en la oferta a la que pertenece
	 * la subsección que me pasan y el comercial que haya creado la oferta a la que pertenece la subsección que me pasan.
	 * 
	 * restricción2 --> MOSTRAR: puede mostrar un currículum cualquier comercial o administrativo que haya creado una subsección en la oferta a la que pertenece
	 * la subsección a la que pertenece el currículum y el comercial que haya creado la oferta a la que pertenece la subsección a la que pertenece el currículum.
	 * 
	 * restricción3 --> CREAR: puede crear un currículum el comercial que haya creado la subsección a la que pertenece el currículum.
	 * 
	 * restricción4 --> EDITAR: puede editar un currículum el comercial que lo haya creado.
	 * 
	 * restricción5 --> BORRAR: puede borrar un currículum el comercial que haya creado la subsección a la que pertenece el currículum.
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

	@Autowired
	private AdministrativeService	administrativeService;


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

	public Curriculum findOneToEdit(int curriculumId) {
		Curriculum result;

		result = this.curriculumRepository.findOne(curriculumId);
		Assert.notNull(result);
		checkPrincipal(result);

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
		Assert.isTrue(!curriculum.getSubSection().getSection().equals("ADMINISTRATIVE_ACREDITATION"));

		if (curriculum.getId() != 0) {
			checkPrincipal(curriculum);
		}

		final Curriculum saved = this.curriculumRepository.save(curriculum);

		return saved;
	}

	public void delete(final Curriculum curriculum) {
		Assert.notNull(curriculum);
		checkPrincipal(curriculum);
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

	public Collection<Curriculum> getCurriculumsFromCommercialId(int commercialId) {
		return this.curriculumRepository.getCurriculumsFromCommercialId(commercialId);
	}

	public void checkListAndDisplay(int subSectionId) {

		Actor principal;
		principal = this.actorService.findByPrincipal();
		Assert.isTrue(principal instanceof Commercial || principal instanceof Administrative);

		SubSection subSection;
		subSection = this.subSectionService.findOne(subSectionId);
		Assert.isTrue(!subSection.getSection().equals("ADMINISTRATIVE_ACREDITATION"));

		Collection<Actor> subSectionCreators = new ArrayList<Actor>();
		Collection<Commercial> subSectionCommercials = this.commercialService.getSubSectionCommercialsFromOfferId(subSection.getOffer().getId());
		Collection<Commercial> subSectionAdministratives = this.administrativeService.getSubSectionAdministrativesFromOfferId(subSection.getOffer().getId());
		subSectionCreators.addAll(subSectionCommercials);
		subSectionCreators.addAll(subSectionAdministratives);
		Assert.isTrue(subSection.getOffer().getCommercial().equals(principal) || subSectionCreators.contains(principal));

	}

	public void checkPrincipal(Curriculum curriculum) {
		final Actor principal = this.actorService.findByPrincipal();
		Assert.isTrue(principal instanceof Commercial);
		Assert.isTrue(curriculumRepository.getCurriculumsFromCommercialId(principal.getId()).contains(curriculum));
	}

}
