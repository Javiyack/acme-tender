
package services;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Administrative;
import domain.Administrator;
import domain.CollaborationRequest;
import domain.Commercial;
import domain.Constant;
import domain.Curriculum;
import domain.File;
import domain.Offer;
import domain.SubSection;
import domain.SubSectionEvaluationCriteria;
import repositories.SubSectionRepository;

@Service
@Transactional
public class SubSectionService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private SubSectionRepository		subSectionRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	AdministrativeService				administrativeService;
	@Autowired
	CommercialService					commercialService;
	@Autowired
	ActorService						actorService;
	@Autowired
	OfferService						offerService;
	@Autowired
	CurriculumService					curriculumService;
	@Autowired
	SubSectionEvaluationCriteriaService	subSectionEvaluationCriteriaService;
	@Autowired
	FileService							fileService;
	@Autowired
	AdministratorService				administratorService;


	// Constructors -----------------------------------------------------------
	public SubSectionService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public SubSection createByCommercialPropietary(int offerId) {

		final Commercial commercial = this.commercialService.findByPrincipal();
		Assert.notNull(commercial);

		Offer offer = this.offerService.findOne(offerId);
		Assert.notNull(offer);
		Assert.isTrue(offer.getCommercial().getId() == commercial.getId());

		final SubSection subSection = new SubSection();
		subSection.setCommercial(commercial);
		subSection.setOffer(offer);

		return subSection;
	}

	public SubSection createByCommercialCollaborationAcceptation(CollaborationRequest collaborationRequest) {

		final Commercial commercial = this.commercialService.findByPrincipal();
		Assert.notNull(commercial);
		Assert.isTrue(collaborationRequest.getCommercial() == commercial);

		Offer offer = this.offerService.findOne(collaborationRequest.getOffer().getId());
		Assert.notNull(offer);

		final SubSection subSection = new SubSection();
		subSection.setCommercial(commercial);
		subSection.setOffer(offer);
		subSection.setTitle(collaborationRequest.getSubSectionTitle());
		subSection.setSection(collaborationRequest.getSection());

		return subSection;
	}

	public SubSection createByAdministrativeCollaborationAcceptation(int offerId) {

		final Administrative administrative = this.administrativeService.findByPrincipal();
		Assert.notNull(administrative);

		Offer offer = this.offerService.findOne(offerId);
		Assert.notNull(offer);

		final SubSection subSection = new SubSection();
		subSection.setAdministrative(administrative);
		subSection.setSection(Constant.SECTION_ADMINISTRATIVE_ACREDITATION);
		subSection.setOffer(offer);

		return subSection;
	}

	public Collection<SubSection> findAll() {
		Collection<SubSection> result;

		result = this.subSectionRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Collection<SubSection> findAllByOffer(int offerId) {
		Collection<SubSection> result;

		result = this.subSectionRepository.findAllByOffer(offerId);
		Assert.notNull(result);

		return result;
	}

	public SubSection findOne(final int subSectionId) {
		Assert.isTrue(subSectionId != 0);

		SubSection result;

		result = this.subSectionRepository.findOne(subSectionId);
		Assert.notNull(result);

		return result;
	}

	public SubSection save(final SubSection subSection) {
		Assert.notNull(subSection);

		if (subSection.getId() != 0)
			Assert.isTrue(this.canEditSubSection(subSection.getId()));

		if (subSection.getAdministrative() != null) {
			Administrative administrative = this.administrativeService.findByPrincipal();
			Assert.isTrue(subSection.getAdministrative().getId() == administrative.getId());
		}

		if (subSection.getCommercial() != null) {
			Commercial commercial = this.commercialService.findByPrincipal();
			Assert.isTrue(subSection.getCommercial().getId() == commercial.getId());
		}

		SubSection result;

		subSection.setLastReviewDate(new Date());
		result = this.subSectionRepository.save(subSection);

		if (subSection.getOffer().getState().equals(Constant.OFFER_CREATED)) {
			subSection.getOffer().setState(Constant.OFFER_IN_DEVELOPMENT);
			this.offerService.save(subSection.getOffer());
		}

		return result;
	}

	public void delete(final SubSection subSection) {
		Assert.notNull(subSection);
		Assert.isTrue(subSection.getId() != 0);
		Assert.isTrue(this.subSectionRepository.exists(subSection.getId()));
		Assert.isTrue(this.canEditSubSection(subSection.getId()));

		if (subSection.getAdministrative() != null) {
			Administrative administrative = this.administrativeService.findByPrincipal();
			Assert.isTrue(subSection.getAdministrative().getId() == administrative.getId());
		}

		if (subSection.getCommercial() != null) {
			Commercial commercial = this.commercialService.findByPrincipal();
			Assert.isTrue(subSection.getCommercial().getId() == commercial.getId());
		}

		//Eliminar curriculums, files y SubSectionEvaluationCriteria asociadas.
		Collection<Curriculum> curriculums = this.curriculumService.getCurriculumsFromSubSectionId(subSection.getId());
		this.curriculumService.deleteInBatch(curriculums);

		Collection<File> files = this.fileService.findAllBySubSection(subSection.getId());
		this.fileService.deleteInBatch(files);

		Collection<SubSectionEvaluationCriteria> subSectionEvaluationCriterias = this.subSectionEvaluationCriteriaService.findAllBySubSection(subSection.getId());
		this.subSectionEvaluationCriteriaService.deleteInBatch(subSectionEvaluationCriterias);

		this.subSectionRepository.delete(subSection);
	}

	// Other business methods -------------------------------------------------

	//Visibilidad de una subseccion = Visibilidad de la oferta asociada
	public boolean canViewSubSection(int subSectionId) {

		SubSection subSection = this.subSectionRepository.findOne(subSectionId);
		return this.offerService.canViewOffer(subSection.getOffer().getId());

	}

	//Una subseccion solo puede ser editada por el administrativo o comercial que tiene asignado.
	public boolean canEditSubSection(int subSectionId) {

		Actor principal = actorService.findByPrincipal();
		SubSection subSection = this.subSectionRepository.findOne(subSectionId);

		//Si la oferta está presentada, no puede editar la subseccion.
		if (subSection.getOffer().isPublished())
			return false;

		if (principal instanceof Commercial) {
			Commercial commercial = this.commercialService.findByPrincipal();

			if (commercial.getId() == subSection.getCommercial().getId())
				return true;
		}

		if (principal instanceof Administrative) {
			Administrative administrative = this.administrativeService.findByPrincipal();

			if (administrative.getId() == subSection.getAdministrative().getId())
				return true;
		}

		return false;
	}

	public Collection<SubSection> findAllSubSectionsWithTabooWord() {
		final Administrator admin = this.administratorService.findByPrincipal();
		Assert.notNull(admin);

		final Collection<SubSection> ret = new LinkedList<SubSection>();

		final Collection<Object[]> source = this.subSectionRepository.findAllSubSectionsWithTabooWord();

		for (final Object obj[] : source) {
			final SubSection ss = this.subSectionRepository.findOne((Integer) obj[0]);

			ret.add(ss);
		}

		return ret;
	}

}
