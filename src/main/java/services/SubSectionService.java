
package services;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Administrative;
import domain.Commercial;
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
	private SubSectionRepository subSectionRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	AdministrativeService administrativeService;
	@Autowired
	CommercialService commercialService;
	@Autowired
	OfferService offerService;
	@Autowired
	CurriculumService curriculumService;
	@Autowired
	SubSectionEvaluationCriteriaService subSectionEvaluationCriteriaService;
	@Autowired
	FileService fileService;
	

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
	
	public SubSection createByCommercialCollaborationAcceptation(int offerId) {

		final Commercial commercial = this.commercialService.findByPrincipal();
		Assert.notNull(commercial);
		
		Offer offer = this.offerService.findOne(offerId);
		Assert.notNull(offer);

		final SubSection subSection = new SubSection();
		subSection.setCommercial(commercial);
		subSection.setOffer(offer);
		
		return subSection;
	}
	
	public SubSection createByAdministrativeCollaborationAcceptation(int offerId) {

		final Administrative administrative = this.administrativeService.findByPrincipal();
		Assert.notNull(administrative);
		
		Offer offer = this.offerService.findOne(offerId);
		Assert.notNull(offer);

		final SubSection subSection = new SubSection();
		subSection.setAdministrative(administrative);
		subSection.setSection("ADMINISTRATIVE_ACREDITATION");
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
		
		if (subSection.getAdministrative() != null) {
			Administrative administrative = this.administrativeService.findByPrincipal();
			Assert.isTrue(subSection.getAdministrative().getId() == administrative.getId());
		}

		if (subSection.getCommercial() != null) {
			Commercial commercial = this.commercialService.findByPrincipal();
			Assert.isTrue(subSection.getCommercial().getId() == commercial.getId());
		}
		
		SubSection result;
		
		result = this.subSectionRepository.save(subSection);
		
		return result;
	}

	public void delete(final SubSection subSection) {
		Assert.notNull(subSection);
		Assert.isTrue(subSection.getId() != 0);
		Assert.isTrue(this.subSectionRepository.exists(subSection.getId()));
		
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

}
