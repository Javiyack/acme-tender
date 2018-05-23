
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.CollaborationRequest;
import domain.Offer;
import repositories.CollaborationRequestRepository;

@Service
@Transactional
public class CollaborationRequestService {

	// Managed repository ------------------------------------------------
	@Autowired
	private CollaborationRequestRepository	collaborationRequestRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private OfferService					offerService;


	//CRUD methods

	public CollaborationRequest create(int offerId) {
		CollaborationRequest result;
		result = new CollaborationRequest();

		Offer offer;
		offer = this.offerService.findOne(offerId);
		//Reutilizo este método para comprobar que la oferta para la que se quiere crear una solicitud de colaboración pertenece al principal
		offerService.canEditOffer(offerId);
		Assert.isTrue(offer.getState().equals("CREATED") || offer.getState().equals("IN_DEVELOPMENT"));
		result.setOffer(offer);

		return result;
	}

	public CollaborationRequest save(CollaborationRequest collaborationRequest) {

		CollaborationRequest result;

		Assert.notNull(collaborationRequest);
		offerService.canEditOffer(collaborationRequest.getOffer().getId());
		Assert.isTrue(collaborationRequest.getOffer().getState().equals("CREATED") || collaborationRequest.getOffer().getState().equals("IN_DEVELOPMENT"));

		result = this.collaborationRequestRepository.save(collaborationRequest);

		return result;

	}

	//Other methods

	public Collection<CollaborationRequest> getCollaborationRequestsFromCommercialId(int commercialId) {
		return this.collaborationRequestRepository.getCollaborationRequestsFromCommercialId(commercialId);
	}

}
