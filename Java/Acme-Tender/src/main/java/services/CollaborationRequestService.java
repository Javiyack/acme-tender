
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.CollaborationRequest;
import domain.Commercial;
import domain.Constant;
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
	@Autowired
	private ActorService					actorService;


	//CRUD methods

	public CollaborationRequest create(int offerId) {
		CollaborationRequest result;
		result = new CollaborationRequest();

		Offer offer;
		offer = this.offerService.findOne(offerId);
		//Reutilizo este método para comprobar que la oferta para la que se quiere crear una solicitud de colaboración pertenece al principal
		offerService.canEditOffer(offerId);
		Assert.isTrue(offer.getState().equals(Constant.OFFER_CREATED) || offer.getState().equals(Constant.OFFER_IN_DEVELOPMENT));
		result.setOffer(offer);

		return result;
	}

	public CollaborationRequest save(CollaborationRequest collaborationRequest) {

		CollaborationRequest result;

		Assert.notNull(collaborationRequest);
		offerService.canEditOffer(collaborationRequest.getOffer().getId());
		Assert.isTrue(collaborationRequest.getOffer().getState().equals(Constant.OFFER_CREATED) || collaborationRequest.getOffer().getState().equals(Constant.OFFER_IN_DEVELOPMENT));

		result = this.collaborationRequestRepository.save(collaborationRequest);

		return result;

	}

	public CollaborationRequest findOne(int collaborationRequestId) {
		CollaborationRequest result;
		result = this.collaborationRequestRepository.findOne(collaborationRequestId);
		Assert.notNull(result);
		checkPrincipal(result);
		return result;
	}

	//Other methods

	public Collection<CollaborationRequest> getSentCollaborationRequestsFromCommercialId(int commercialId) {
		return this.collaborationRequestRepository.getSentCollaborationRequestsFromCommercialId(commercialId);
	}

	public Collection<CollaborationRequest> getReceivedCollaborationRequestsFromCommercialId(int commercialId) {
		return this.collaborationRequestRepository.getReceivedCollaborationRequestsFromCommercialId(commercialId);
	}

	private void checkPrincipal(CollaborationRequest collaborationRequest) {

		Actor principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal instanceof Commercial);
		Collection<CollaborationRequest> sent = this.collaborationRequestRepository.getSentCollaborationRequestsFromCommercialId(principal.getId());
		Collection<CollaborationRequest> received = this.collaborationRequestRepository.getReceivedCollaborationRequestsFromCommercialId(principal.getId());
		Assert.isTrue(sent.contains(collaborationRequest) || received.contains(collaborationRequest));

	}

}
