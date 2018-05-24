
package services;

import java.util.Collection;
import java.util.LinkedList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.TenderRepository;
import domain.Actor;
import domain.Administrative;
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
	private ActorService			actorService;
	@Autowired
	private AdministrativeService	administrativeService;
	@Autowired
	private AdministratorService	administratorService;


	// Constructor ----------------------------------------------------------
	public TenderService() {
		super();
	}

	// Methods CRUD ---------------------------------------------------------

	public Tender create(final int tenderId) {
		final Administrative administrative = this.administrativeService.findByPrincipal();
		Assert.notNull(administrative);

		final Tender tender = new Tender();
		tender.setAdministrative(administrative);

		return tender;
	}

	public Tender findOneToEdit(final int tenderId) {
		final Tender result = this.tenderRepository.findOne(tenderId);

		Assert.notNull(result);
		this.checkPrincipal(result);

		return result;
	}

	public Tender findOne(final int tenderId) {
		Tender result;

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

	public Collection<Tender> findTenderByKeyWord(final String word) {
		final Collection<Tender> tenders;
		if (word.isEmpty())
			tenders = this.findAll();
		else
			tenders = this.tenderRepository.findTenderByKeyword(word);

		return tenders;
	}

	//Other methods ---------------------------------------------------------------

	public void checkPrincipal(final Tender tender) {
		final Actor principal = this.actorService.findByPrincipal();
		Administrative administrativePrincipal = null;
		if (principal instanceof Administrative) {
			administrativePrincipal = (Administrative) principal;
			Assert.isTrue(tender.getAdministrative().equals(administrativePrincipal));
		} else
			Assert.isTrue(Boolean.TRUE, "Usuario no válido.");
	}

	public Tender findOneToComment(final Integer tenderId) {

		Tender result;
		result = this.tenderRepository.findOne(tenderId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Tender> findAllByAdministrative() {
		final Administrative administrative = this.administrativeService.findByPrincipal();
		Assert.notNull(administrative);
		final Collection<Tender> tenders = this.tenderRepository.findAllByAdministrative(administrative.getId());
		Assert.notNull(tenders);
		return tenders;
	}

	public Collection<Tender> findAllTenderWithTabooWords() {
		final Administrator admin = this.administratorService.findByPrincipal();
		Assert.notNull(admin);

		final Collection<Tender> ret = new LinkedList<Tender>();

		final Collection<Object[]> source = this.tenderRepository.findAllTenderWithTabooWord();

		for (final Object obj[] : source) {
			final Tender t = this.tenderRepository.findOne((Integer) obj[0]);

			ret.add(t);
		}

		return ret;
	}
}
