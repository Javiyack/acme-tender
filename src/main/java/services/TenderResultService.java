
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.TenderResultRepository;
import domain.Administrator;
import domain.TenderResult;

@Service
@Transactional
public class TenderResultService {

	// Managed repositories ------------------------------------------------
	@Autowired
	private TenderResultRepository		tenderResultRepository;

	// Managed services ------------------------------------------------
	@Autowired
	private AdministratorService	administratorService;


	// Constructor ----------------------------------------------------------
	public TenderResultService() {
		super();
	}

	// Methods CRUD ---------------------------------------------------------

	public TenderResult findOne(final int tenderId) {
		TenderResult result;
		final Administrator admin = this.administratorService.findByPrincipal();
		Assert.notNull(admin);

		result = this.tenderResultRepository.findOne(tenderId);
		Assert.notNull(result);

		return result;
	}

	public Collection<TenderResult> findAll() {

		Collection<TenderResult> result;

		result = this.tenderResultRepository.findAll();
		Assert.notNull(result);

		return result;
	}
}
