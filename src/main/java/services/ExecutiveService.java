
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.ExecutiveRepository;

@Service
@Transactional
public class ExecutiveService {

	// Managed repositories ------------------------------------------------
	@Autowired
	private ExecutiveRepository	executiveRepository;


	// Constructor ----------------------------------------------------------
	public ExecutiveService() {
		super();
	}

	// Methods CRUD ---------------------------------------------------------

	// Dashboard -------------------------------------------------------------------
	public Collection<Object> numberTenderByAdministrative() {
		final Collection<Object> numbers = this.executiveRepository.numberTenderByUser();

		return numbers;
	}

	public Collection<Object> tendersByInterestLevel() {
		final Collection<Object> numbers = this.executiveRepository.tendersByInterestLevel();

		return numbers;
	}

	public Collection<Object> offersByState() {
		final Collection<Object> numbers = this.executiveRepository.offersByState();

		return numbers;
	}

	public Collection<Object> offersByStateAndCommercial() {
		final Collection<Object> numbers = this.executiveRepository.offersByStateAndCommercial();

		return numbers;
	}

	public Collection<Object> offersByStateRatio() {
		final Collection<Object> numbers = this.executiveRepository.offersByStateRatio();

		return numbers;
	}

	public Collection<Object> offersByStateAndCommercialRatio() {
		final Collection<Object> numbers = this.executiveRepository.offersByStateAndCommercialRatio();

		return numbers;
	}
}
