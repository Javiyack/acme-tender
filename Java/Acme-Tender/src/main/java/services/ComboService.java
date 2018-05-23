
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Actor;
import domain.CompanyResult;
import domain.Executive;
import domain.Offer;

@Service
@Transactional
public class ComboService {

	// Managed repositories ------------------------------------------------
	
	// Managed services
	@Autowired
	ActorService actorService;
	@Autowired
	OfferService offerService;
	@Autowired
	CompanyResultService companyResultService;
	

	// Constructor ----------------------------------------------------------
	public ComboService() {
		super();
	}

	public Collection<String> offerStates(int offerId) {
		
		//Obtenemos el estado desde el que partimos
		String actualState= "";
		Offer offer = this.offerService.findOne(offerId);
		if (offer == null)
			actualState = "";
		else
			actualState = offer.getState();
		
		
		Collection<String> result = new ArrayList<String>();
		
		//el combo tiene que contener el estado en el que estamos
		if (!actualState.equals(""))
			result.add(actualState);
		
		Actor actor = this.actorService.findByPrincipal();
		
		switch (actualState) {
		
		case "":
			result.add("CREATED");
			if (actor instanceof Executive) result.add("DENIED");
			break;
			
		case "CREATED":
			result.add("IN_DEVELOPMENT");
			result.add("ABANDONED");
			if (actor instanceof Executive) result.add("DENIED");
			break;
			
		case "IN_DEVELOPMENT":
			result.add("PRESENTED");
			result.add("ABANDONED");
			if (actor instanceof Executive) result.add("DENIED");
			break;

		case "ABANDONED":
			result.add("IN_DEVELOPMENT");
			break;
			
		case "PRESENTED":
			result.add("WINNED");
			result.add("LOSED");
			result.add("CHALLENGED");
			break;
			
		case "WINNED":
			break;
			
		case "LOSED":
			break;
			
		case "CHALLENGED":
			break;
			
		case "DENIED":
			break;
			
		}
		
		return result;
	}

	
	public Collection<String> collaborationRequestSections() {
		
		Collection<String> result = new ArrayList<String>();
		
		result.add("TECHNICAL_OFFER");
		result.add("ECONOMICAL_OFFER");
		
		return result;
	}	
	
	public Collection<String> subSectionSections() {
		
		Collection<String> result = new ArrayList<String>();
		
		result.add("ADMINISTRATIVE_ACREDITATION");
		result.add("TECHNICAL_OFFER");
		result.add("ECONOMICAL_OFFER");
		
		return result;
	}	
	
	public Collection<String> tenderInterests() {
		
		Collection<String> result = new ArrayList<String>();
		
		result.add("UNDEFINED");
		result.add("LOW");
		result.add("MEDIUM");
		result.add("HIGH");
		
		return result;
	}		
	
	public Collection<String> companyResultStates(CompanyResult companyResult) {
		
		Collection<String> result = new ArrayList<String>();
		
		Collection<CompanyResult> winners = this.companyResultService.findAllWinnerByTenderResult(companyResult.getTenderResult().getId());
		if (winners.size() == 0)
			result.add("WINNER");
		
		result.add("LOSER");
		result.add("RECKLESS_OFFER");
		
		return result;
	}		
	
}
