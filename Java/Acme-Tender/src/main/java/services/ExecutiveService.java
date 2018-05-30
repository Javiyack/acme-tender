
package services;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Offer;
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

	public Collection<Offer> findTopOffersOnMonth(Date fecha, int pageSize) {
		return this.executiveRepository.findTopOffersOnMonth(fecha, new PageRequest(0, pageSize));
	}

	public List<Offer> findTopWinedOffersOnThreeMonthsFrom(Date fecha, int pageSize) {
		return this.executiveRepository.findTopWinedOffersOnThreeMonths(fecha, new PageRequest(0, pageSize));
	}

	public Collection<Object> findAvgRatioAmounOfferOverTender() {
		return this.executiveRepository.findAvgRatioAmounOfferOverTender();
	}

	public Collection<Object> findTopFrecuentsCompanies(int pageSize) {
		return this.executiveRepository.findTopFrecuentsCompanies();
	}

	public Collection<Object> findTopFrecuentsWinnersCompanies(int pageSize) {
		return this.executiveRepository.findTopFrecuentsWinnersCompanies();
	}
}
