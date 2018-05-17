
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.Validator;

import repositories.ActorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Administrator;

@Service
@Transactional
public class ActorService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private ActorRepository			actorRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private Validator				validator;

	@Autowired
	private AdministratorService	administratorService;


	// Constructors -----------------------------------------------------------

	public ActorService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Collection<Actor> findAll() {
		Collection<Actor> result;

		result = this.actorRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Actor findOne(final int actorId) {
		Assert.isTrue(actorId != 0);

		Actor result;

		result = this.actorRepository.findOne(actorId);
		Assert.notNull(result);

		return result;
	}

	public Actor save(final Actor actor) {
		Assert.notNull(actor);
		Actor result;
		Assert.isTrue(actor.equals(this.findByPrincipal()));

		result = this.actorRepository.save(actor);
		return result;
	}

	public void delete(final Actor actor) {
		Assert.notNull(actor);
		Assert.isTrue(actor.getId() != 0);
		Assert.isTrue(this.actorRepository.exists(actor.getId()));
		Assert.isTrue(actor.equals(this.findByPrincipal()));

		this.actorRepository.delete(actor);
	}

	// Other business methods -------------------------------------------------

	public void ActivateOrDesactivate(final Integer id) {
		final Actor res = this.actorRepository.findOne(id);
		final Administrator admin = this.administratorService.findByPrincipal();
		Assert.notNull(admin);
		Assert.notNull(res);
		if (res.getUserAccount().getActive())
			res.getUserAccount().setActive(false);
		else
			res.getUserAccount().setActive(true);
	}
	public UserAccount findUserAccount(final Actor actor) {
		Assert.notNull(actor);

		UserAccount result;

		result = actor.getUserAccount();

		return result;
	}

	public Actor findByPrincipal() {
		Actor result = null;
		UserAccount userAccount;

		try {
			userAccount = LoginService.getPrincipal();
			Assert.notNull(userAccount);
			result = this.findByUserAccount(userAccount);
			Assert.notNull(result);

		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public Actor findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);

		Actor result;

		result = this.actorRepository.findByUserAccountId(userAccount.getId());
		Assert.notNull(result);

		return result;
	}

	public String getType(final UserAccount userAccount) {

		final List<Authority> authorities = new ArrayList<Authority>(userAccount.getAuthorities());

		return authorities.get(0).getAuthority();
	}
}
