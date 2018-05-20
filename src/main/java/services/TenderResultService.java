
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.TenderResultRepository;
import domain.Administrative;
import domain.Administrator;
import domain.Tender;
import domain.TenderResult;

@Service
@Transactional
public class TenderResultService {

	// Managed repositories ------------------------------------------------
	@Autowired
	private TenderResultRepository	tenderResultRepository;

	// Managed services ------------------------------------------------
	@Autowired
	private AdministratorService	administratorService;
	@Autowired
	private TenderService			tenderService;
	@Autowired
	private AdministrativeService	administrativeService;


	// Constructor ----------------------------------------------------------
	public TenderResultService() {
		super();
	}

	// Methods CRUD ---------------------------------------------------------

	public TenderResult create(final int tenderId) {

		final Administrative administrative = this.administrativeService.findByPrincipal();
		Assert.notNull(administrative);

		final Tender tender = this.tenderService.findOne(tenderId);
		Assert.notNull(tender);
		Assert.isTrue(tender.getAdministrative().equals(administrative));

		final TenderResult tenderResult = new TenderResult();
		tenderResult.setTender(tender);

		return tenderResult;
	}

	public TenderResult findOneToDisplay(final int tenderId) {
		TenderResult result;

		result = this.tenderResultRepository.findOne(tenderId);
		Assert.notNull(result);

		return result;
	}

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

	public TenderResult save(final TenderResult tenderResult) {
		final TenderResult saved;
		final Administrative administrative = this.administrativeService.findByPrincipal();
		Assert.notNull(administrative);
		Assert.isTrue(tenderResult.getTender().getAdministrative().equals(administrative));
		Assert.isTrue(!this.hasTenderResult(tenderResult.getTender()), "Has a result");

		if (tenderResult.getId() == 0) {
			final Date moment = new Date(System.currentTimeMillis() - 1);
			tenderResult.setTenderDate(moment);
		}

		saved = this.tenderResultRepository.save(tenderResult);

		return saved;
	}

	public Boolean hasTenderResult(final Tender tender) {
		Boolean res = false;
		final TenderResult tenderResult = this.tenderResultRepository.findOneByTender(tender.getId());
		if (tenderResult != null)
			res = true;
		return res;

	}

	public TenderResult findOneByTender(final int tenderId) {
		final TenderResult tenderResult = this.tenderResultRepository.findOneByTender(tenderId);

		return tenderResult;
	}
}
