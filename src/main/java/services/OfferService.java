
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Administrative;
import domain.Commercial;
import domain.Executive;
import domain.Offer;
import domain.Tender;
import repositories.OfferRepository;

@Service
@Transactional
public class OfferService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private OfferRepository	offerRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	AdministrativeService	administrativeService;
	@Autowired
	ActorService			actorService;
	@Autowired
	CommercialService		commercialService;
	@Autowired
	TenderService			tenderService;


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

	public Offer save(final Offer offer) {
		Assert.notNull(offer);

		if (offer.getId() != 0)
			Assert.isTrue(this.canEditOffer(offer.getId()));

		final Commercial commercial = this.commercialService.findByPrincipal();
		Assert.isTrue(offer.getCommercial().getId() == commercial.getId());

		Offer result;
		result = this.offerRepository.save(offer);

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

			if (actor instanceof Administrative) {
				final Administrative administrative = this.administrativeService.findByPrincipal();
				return this.offerRepository.findAdministrativeCollaboratorsByOffer(offerId).contains(administrative);
			}
		}

		return false;
	}

	//Una oferta solo puede ser editada por el comercial que la creó
	public boolean canEditOffer(final int offerId) {
		final Actor principal = this.actorService.findByPrincipal();
		final Offer offer = this.offerRepository.findOne(offerId);

		if (principal instanceof Commercial) {
			final Commercial commercial = this.commercialService.findByPrincipal();

			if (commercial.getId() == offer.getCommercial().getId())
				return true;
		}

		return false;
	}

	// Other business methods -------------------------------------------------

}
