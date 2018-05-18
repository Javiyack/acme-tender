
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Actor;
import domain.Comment;
import domain.Commercial;
import domain.Tender;

@Service
@Transactional
public class CommentService {

	// Managed repositories ------------------------------------------------
	@Autowired
	private CommentRepository	commentRepository;

	//Services
	@Autowired
	private ActorService		actorService;
	@Autowired
	private TenderService		tenderService;


	// Constructor ----------------------------------------------------------
	public CommentService() {
		super();
	}

	// Methods CRUD ---------------------------------------------------------

	public Comment create(final int tenderId) {
		final Tender tender = this.tenderService.findOneToComment(tenderId);
		Assert.notNull(tenderId);

		final Actor actor = this.actorService.findByPrincipal();
		Assert.isTrue(actor instanceof Commercial);

		final Comment comment;

		comment = new Comment();
		comment.setCommercial((Commercial) actor);
		comment.setTender(tender);

		return comment;
	}

	public Comment findOne(final int commentId) {
		Comment result;

		result = this.commentRepository.findOne(commentId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Comment> findAll() {

		Collection<Comment> result;

		result = this.commentRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Comment save(final Comment comment) {
		Assert.notNull(comment);
		Comment saved;

		final Actor actor = this.actorService.findByPrincipal();
		Assert.isTrue(actor instanceof Commercial);
		Assert.isTrue(comment.getCommercial().equals(actor));

		if (comment.getId() == 0) {
			final Date moment = new Date(System.currentTimeMillis() - 1);
			comment.setWritingDate(moment);
		}

		saved = this.commentRepository.save(comment);

		return saved;
	}

	public void delete(final Comment comment) {
		Assert.notNull(comment);

		this.commentRepository.delete(comment);
	}

	public void flush() {
		this.commentRepository.flush();

	}

	// Other methods ---------------------------------------------------------------

	public Collection<Comment> findAllByTender(final Integer tenderId) {

		final Collection<Comment> comments = this.commentRepository.findAllByTenderId(tenderId);
		Assert.notNull(comments);

		return comments;
	}

}
