
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.TenderRepository;
import domain.Administrator;
import domain.Tender;

@Service
@Transactional
public class TenderService {

	// Managed repositories ------------------------------------------------
	@Autowired
	private TenderRepository		tenderRepository;

	// Managed services ------------------------------------------------
	@Autowired
	private AdministratorService	administratorService;


	// Constructor ----------------------------------------------------------
	public TenderService() {
		super();
	}

	// Methods CRUD ---------------------------------------------------------

	public Tender findOne(final int tenderId) {
		Tender result;
		final Administrator admin = this.administratorService.findByPrincipal();
		Assert.notNull(admin);

		result = this.tenderRepository.findOne(tenderId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Tender> findAll() {

		Collection<Tender> result;

		result = this.tenderRepository.findAll();
		Assert.notNull(result);

		return result;
	}
}
