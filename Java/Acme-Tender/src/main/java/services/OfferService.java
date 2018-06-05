
package services;

import java.util.Collection;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Administrative;
import domain.AdministrativeRequest;
import domain.Administrator;
import domain.CollaborationRequest;
import domain.Commercial;
import domain.Constant;
import domain.EvaluationCriteria;
import domain.Executive;
import domain.Offer;
import domain.SubSection;
import domain.SubSectionEvaluationCriteria;
import domain.Tender;
import repositories.OfferRepository;

@Service
@Transactional
public class OfferService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private OfferRepository			offerRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	AdministrativeService			administrativeService;
	@Autowired
	AdministrativeRequestService	administrativeRequestService;
	@Autowired
	CollaborationRequestService		collaborationRequestService;
	@Autowired
	AdministratorService			administratorService;
	@Autowired
	ActorService					actorService;
	@Autowired
	CommercialService				commercialService;
	@Autowired
	TenderService					tenderService;
	@Autowired
	SubSectionService				subSectionService;
	@Autowired
	EvaluationCriteriaService evaluationCriteriaService;
	@Autowired
	SubSectionEvaluationCriteriaService subSectionEvaluationCriteriaService; 
	


	// Constructors -----------------------------------------------------------
	public OfferService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Offer create(final int tenderId) {

		final Commercial commercial = this.commercialService.findByPrincipal();
		Assert.notNull(commercial);

		final Offer offer = new Offer();
		offer.setCommercial(commercial);

		final Tender tender = this.tenderService.findOne(tenderId);
		Assert.isNull(tender.getOffer(), "offer.cannot.create.already.exists");
		offer.setTender(tender);

		return offer;
	}

	public Collection<Offer> findAll() {
		Collection<Offer> result;

		result = this.offerRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Collection<Offer> findAllPublished() {
		Collection<Offer> result;

		result = this.offerRepository.findAllPublished();
		Assert.notNull(result);

		return result;
	}

	public Collection<Offer> findAllNotPublished() {
		Collection<Offer> result;

		result = this.offerRepository.findAllNotPublished();
		Assert.notNull(result);

		return result;
	}

	public Collection<Offer> findAllByCommercialPropietary() {
		Collection<Offer> result;

		final Commercial commercial = this.commercialService.findByPrincipal();

		result = this.offerRepository.findAllByCommercialPropietary(commercial.getId());
		Assert.notNull(result);

		return result;
	}

	public Collection<Offer> findAllByCommercialColaboration() {
		Collection<Offer> result;

		final Commercial commercial = this.commercialService.findByPrincipal();

		result = this.offerRepository.findAllByCommercialCollaboration(commercial.getId());
		Assert.notNull(result);

		return result;
	}

	public Collection<Offer> findAllByAdministrativeColaboration() {
		Collection<Offer> result;

		final Administrative administrative = this.administrativeService.findByPrincipal();

		result = this.offerRepository.findAllByAdministrativeCollaboration(administrative.getId());
		Assert.notNull(result);

		return result;
	}

	public Offer findOne(final int offerId) {
		Assert.isTrue(offerId != 0);

		Offer result;

		result = this.offerRepository.findOne(offerId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Offer> findOfferByKeyWord(final String word) {
		final Collection<Offer> offers;
		if (word.isEmpty())
			offers = this.findAllPublished();
		else
			offers = this.offerRepository.findOfferByKeyword(word);

		return offers;
	}

	public Offer save(final Offer offer) {
		Assert.notNull(offer);

		if (offer.getId() != 0)
			Assert.isTrue(this.canEditOffer(offer.getId()));

		final Commercial commercial = this.commercialService.findByPrincipal();
		Assert.isTrue(offer.getCommercial().getId() == commercial.getId());

		if (offer.getPresentationDate() != null)
			Assert.isTrue(offer.getPresentationDate().before(offer.getTender().getMaxPresentationDate()), "offer.error.presentationDate.not.before.tender.maxPresentationDate");
		
		//Si presentamos la oferta, forzamos a que existe un SubSectionEvaluationCriteria en la oferta
		//por cada EvaluationCriteria del concurso
		Offer oldOffer = this.offerRepository.findOne(offer.getId());
		if (!oldOffer.getState().equals(Constant.OFFER_PRESENTED) && offer.getState().equals(Constant.OFFER_PRESENTED)) {
			Collection<EvaluationCriteria> evaluationCriterias = this.evaluationCriteriaService.findAllByTender(offer.getTender().getId());
			for (EvaluationCriteria ec : evaluationCriterias) {
				Collection<SubSectionEvaluationCriteria> ssec = this.subSectionEvaluationCriteriaService.findByOfferAndEvaluationCriteria(offer.getId(), ec.getId()); 
				Assert.isTrue(ssec.size() > 0, "offer.error.need.subSectionEvaluationCriteria.for.every.evaluationCriteria");
			}
		}
		
		final Offer result = this.offerRepository.save(offer);

		if (offer.getId() == 0) {
			// Guardamos la oferta en el concurso
			final Tender tender = result.getTender();
			tender.setOffer(result);
			this.tenderService.save(tender);
		}
		return result;
	}
	
	public Offer saveToDeny(final Offer offer) {
		Assert.notNull(offer);
		Actor actor = this.actorService.findByPrincipal();
		
		Assert.isTrue(actor instanceof Executive);
		
		offer.setState(Constant.OFFER_DENIED);
		final Offer result = this.offerRepository.save(offer);

		return result;
	}	

	//Visibilidad de una oferta:
	//Si esta presentada, es visible por todos los autenticados
	//Si no esta presentada, es visible solo por comercial de la oferta, colaboradores o directivos
	public boolean canViewOffer(final int offerId) {
		final Actor actor = this.actorService.findByPrincipal();
		final Offer offer = this.offerRepository.findOne(offerId);

		if (offer.isPublished())
			return actor != null;

		if (!offer.isPublished()) {
			if (actor instanceof Commercial) {
				final Commercial commercial = this.commercialService.findByPrincipal();

				if (offer.getCommercial().getId() == commercial.getId())
					return true;

				return this.offerRepository.findCommercialCollaboratorsByOffer(offerId).contains(commercial);
			}

			if (actor instanceof Executive)
				return true;
			
			if (actor instanceof Administrator)
				return true;			

			if (actor instanceof Administrative) {
				final Administrative administrative = this.administrativeService.findByPrincipal();
				return this.offerRepository.findAdministrativeCollaboratorsByOffer(offerId).contains(administrative);
			}
		}

		return false;
	}

	//Una oferta solo puede ser editada por el comercial que la cre�
	//Si esta publicada, tambi�n la puede modificar (pero las subsecciones no)
	public boolean canEditOffer(final int offerId) {
		final Actor actor = this.actorService.findByPrincipal();
		final Offer offer = this.offerRepository.findOne(offerId);

		if (actor instanceof Commercial) {
			if (actor.getId() == offer.getCommercial().getId())
				return true;
		}

		return false;
	}

	public Collection<Offer> findAllOfferWithTabooWords() {
		final Administrator admin = this.administratorService.findByPrincipal();
		Assert.notNull(admin);

		final Collection<Offer> ret = new LinkedList<Offer>();

		final Collection<SubSection> subSections = this.subSectionService.findAllSubSectionsWithTabooWord();

		for (final SubSection ss : subSections)
			if (!ret.contains(ss.getOffer()))
				ret.add(ss.getOffer());
		return ret;
	}

	public void deleteByAdmin(final Offer offer) {
		final Administrator admin = this.administratorService.findByPrincipal();
		Assert.notNull(admin);

		final Collection<AdministrativeRequest> administrativeRequests = this.administrativeRequestService.findAllByOffer(offer.getId());
		this.administrativeRequestService.deleteByAdmin(administrativeRequests);

		final Collection<CollaborationRequest> collaborationRequests = this.collaborationRequestService.findAllByOffer(offer.getId());
		this.collaborationRequestService.deleteByAdmin(collaborationRequests);

		final Collection<SubSection> subSections = this.subSectionService.findAllByOffer(offer.getId());
		this.subSectionService.deleteInBatchByAdmin(subSections);

		final Tender tender = this.tenderService.findOne(offer.getTender().getId());
		tender.setOffer(null);

		this.offerRepository.delete(offer);

	}

	public Offer findByTender(final Integer tenderId) {
		final Offer offer = this.offerRepository.findByTender(tenderId);

		return offer;
	}

	// Other business methods -------------------------------------------------

}
