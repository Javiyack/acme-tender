
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
import domain.Comment;
import domain.EvaluationCriteria;
import domain.File;
import domain.Offer;
import domain.Tender;
import domain.TenderResult;

@Service
@Transactional
public class TenderService {

	// Managed repositories ------------------------------------------------
	@Autowired
	private TenderRepository			tenderRepository;

	// Managed services ------------------------------------------------
	@Autowired
	private ActorService				actorService;
	@Autowired
	private AdministrativeService		administrativeService;
	@Autowired
	private AdministratorService		administratorService;
	@Autowired
	private TenderResultService			tenderResultService;
	@Autowired
	private FileService					fileService;
	@Autowired
	private OfferService				offerService;
	@Autowired
	private CommentService				commentService;
	@Autowired
	private EvaluationCriteriaService	evaluationCriteriaService;


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

	public void deleteByAdmin(final Tender tender) {
		final Administrator admin = this.administratorService.findByPrincipal();
		Assert.notNull(admin);

		final TenderResult tenderResult = this.tenderResultService.findOneByTenderAnonymous(tender.getId());
		if (tenderResult != null)
			this.tenderResultService.deleteByAdmin(tenderResult);

		final Collection<File> files = this.fileService.findAllByTender(tender.getId());
		this.fileService.deleteInBatch(files);

		final Offer offer = this.offerService.findByTender(tender.getId());
		if (offer != null)
			this.offerService.deleteByAdmin(offer);

		final Collection<Comment> comment = this.commentService.findAllByTender(tender.getId());
		this.commentService.deleteByAdmin(comment);

		final Collection<EvaluationCriteria> evaluationCriterias = this.evaluationCriteriaService.findAllByTender(tender.getId());
		this.evaluationCriteriaService.deleteByAdmin(evaluationCriterias);

		this.tenderRepository.delete(tender);

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
