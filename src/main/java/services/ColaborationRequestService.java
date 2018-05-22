
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.ColaborationRequest;
import domain.Offer;
import repositories.ColaborationRequestRepository;

@Service
@Transactional
public class ColaborationRequestService {

	// Managed repository ------------------------------------------------
	@Autowired
	private ColaborationRequestRepository	colaborationRequestRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private OfferService					offerService;


	//CRUD methods

	public ColaborationRequest create(int offerId) {
		ColaborationRequest result;
		result = new ColaborationRequest();

		Offer offer;
		offer = this.offerService.findOne(offerId);
		//Reutilizo este método para comprobar que la oferta para la que se quiere crear una solicitud de colaboración pertenece al principal
		offerService.canEditOffer(offerId);
		Assert.isTrue(offer.getState().equals("CREATED") || offer.getState().equals("IN_DEVELOPMENT"));
		result.setOffer(offer);

		return result;
	}

	public ColaborationRequest save(ColaborationRequest colaborationRequest) {
		//TODO comprobaciones con las fechas
		ColaborationRequest result;

		Assert.notNull(colaborationRequest);
		offerService.canEditOffer(colaborationRequest.getOffer().getId());
		Assert.isTrue(colaborationRequest.getOffer().getState().equals("CREATED") || colaborationRequest.getOffer().getState().equals("IN_DEVELOPMENT"));

		result = this.colaborationRequestRepository.save(colaborationRequest);

		return result;

	}

	//Other methods

}
