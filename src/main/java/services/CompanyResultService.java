
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CompanyResultRepository;
import domain.Administrative;
import domain.CompanyResult;
import domain.TenderResult;

@Service
@Transactional
public class CompanyResultService {

	// Managed repositories ------------------------------------------------
	@Autowired
	private CompanyResultRepository	companyResultRepository;

	// Managed services ------------------------------------------------
	@Autowired
	private AdministratorService	administratorService;
	@Autowired
	private TenderService			tenderService;
	@Autowired
	private TenderResultService		tenderResultService;
	@Autowired
	private AdministrativeService	administrativeService;


	// Constructor ----------------------------------------------------------
	public CompanyResultService() {
		super();
	}

	// Methods CRUD ---------------------------------------------------------

	public CompanyResult create(final int tenderResultId) {

		final Administrative administrative = this.administrativeService.findByPrincipal();
		Assert.notNull(administrative);

		final TenderResult tenderResult = this.tenderResultService.findOneToDisplay(tenderResultId);
		Assert.notNull(tenderResult);
		Assert.isTrue(tenderResult.getTender().getAdministrative().equals(administrative));

		final CompanyResult companyResult = new CompanyResult();
		companyResult.setTenderResult(tenderResult);

		return companyResult;
	}

	public CompanyResult findOne(final int companyResultId) {
		CompanyResult result;

		result = this.companyResultRepository.findOne(companyResultId);
		Assert.notNull(result);

		return result;
	}

	public Collection<CompanyResult> findAll() {

		Collection<CompanyResult> result;

		result = this.companyResultRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public CompanyResult save(final CompanyResult companyResult) {
		final CompanyResult saved;
		final Administrative administrative = this.administrativeService.findByPrincipal();
		Assert.notNull(administrative);
		Assert.isTrue(companyResult.getTenderResult().getTender().getAdministrative().equals(administrative));

		saved = this.companyResultRepository.save(companyResult);

		return saved;
	}

	public void delete(final CompanyResult companyResult) {
		Assert.notNull(companyResult);
		final Administrative administrative = this.administrativeService.findByPrincipal();
		Assert.notNull(administrative);
		Assert.isTrue(companyResult.getTenderResult().getTender().getAdministrative().equals(administrative));

		this.companyResultRepository.delete(companyResult);

	}

	public Collection<CompanyResult> findAllByTenderResult(final int tenderResultId) {
		final Collection<CompanyResult> companyResults = this.companyResultRepository.findAllByTenderResult(tenderResultId);

		return companyResults;
	}

	public void deleteInBatch(final Collection<CompanyResult> companyResults) {
		Assert.notNull(companyResults);
		this.companyResultRepository.deleteInBatch(companyResults);

	}
}
