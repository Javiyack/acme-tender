
package services;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Administrative;
import domain.Commercial;
import domain.Offer;
import domain.Tender;
import repositories.OfferRepository;

@Service
@Transactional
public class OfferService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private OfferRepository offerRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	AdministrativeService administrativeService;
	@Autowired
	CommercialService commercialService;
	@Autowired
	TenderService tenderService;

	// Constructors -----------------------------------------------------------
	public OfferService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Offer create(int tenderId) {
		
		Commercial commercial = this.commercialService.findByPrincipal();
		Assert.notNull(commercial);

		final Offer offer = new Offer();
		offer.setCommercial(commercial);
		
		Tender tender = this.tenderService.findOne(tenderId);
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
	

	public Collection<Offer> findAllByCommercialPropietary() {
		Collection<Offer> result;
		
		Commercial commercial = this.commercialService.findByPrincipal();

		result = this.offerRepository.findAllByCommercialPropietary(commercial.getId());
		Assert.notNull(result);

		return result;
	}

	public Collection<Offer> findAllByCommercialColaboration() {
		Collection<Offer> result;
		
		Commercial commercial = this.commercialService.findByPrincipal();

		result = this.offerRepository.findAllByCommercialCollaboration(commercial.getId());
		Assert.notNull(result);

		return result;
	}

	public Collection<Offer> findAllByAdministrativeColaboration() {
		Collection<Offer> result;
		
		Administrative administrative = this.administrativeService.findByPrincipal();

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

	public Offer save(final Offer offer) {
		Assert.notNull(offer);

		Commercial commercial = this.commercialService.findByPrincipal();
		Assert.isTrue(offer.getCommercial().getId() == commercial.getId());
		
		Offer result;
		result = this.offerRepository.save(offer);
		
		return result;
	}


	// Other business methods -------------------------------------------------

}
