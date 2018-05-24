
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.AdministrativeRequestRepository;
import domain.AdministrativeRequest;

@Service
@Transactional
public class AdministrativeRequestService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private AdministrativeRequestRepository	administrativeRequestRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public AdministrativeRequestService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public void deleteByAdmin(final Collection<AdministrativeRequest> administrativeRequests) {
		this.administrativeRequestRepository.deleteInBatch(administrativeRequests);
	}

	//Other -------------------------------------------------------------------
	public Collection<AdministrativeRequest> findAllByOffer(final int offerId) {

		return this.administrativeRequestRepository.findAllByOffer(offerId);
	}

}
