
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import domain.Actor;
import domain.Administrative;
import domain.Administrator;
import domain.Commercial;
import domain.Executive;
import forms.RegisterForm;
import repositories.ActorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;

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
	private UserAccountService		userAccountService;
	@Autowired
	private AdministratorService	administratorService;
	@Autowired
	private FolderService			folderService;


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
		if (actor.getId() != 0)
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

	public void ActivateOrDeactivate(final Integer id) {
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

	public Actor recontruct(final RegisterForm registerForm, final BindingResult bind) {
		Actor result = null;
		UserAccount useraccount = null;
		if (registerForm.getId() == 0) {
			useraccount = new UserAccount();
			switch (registerForm.getAuthority()) {
			case Authority.ADMINISTRATIVE:
				result = new Administrative();
				useraccount = this.userAccountService.createAsAdministrative();
				result.setUserAccount(useraccount);
				break;

			case Authority.COMMERCIAL:
				result = new Commercial();
				useraccount = this.userAccountService.createAsCommercial();
				result.setUserAccount(useraccount);
				break;

			case Authority.EXECUTIVE:
				result = new Executive();
				useraccount = this.userAccountService.createAsExecutive();
				result.setUserAccount(useraccount);
				break;

			default:
				break;
			}
			this.folderService.createSystemFolders(result);
		} else {
			result = this.findByPrincipal();
			useraccount = result.getUserAccount();
		}
		result.setName(registerForm.getName());
		result.setSurname(registerForm.getSurname());
		result.setEmail(registerForm.getEmail());
		result.setAddress(registerForm.getAddress());
		result.setPhone(registerForm.getPhone());
		this.validator.validate(result, bind);

		useraccount.setUsername(registerForm.getUserName());
		Md5PasswordEncoder encoder;
		encoder = new Md5PasswordEncoder();
		useraccount.setPassword(encoder.encodePassword(registerForm.getPassword(), null));
		useraccount.setActive(true);

		this.validator.validate(useraccount, bind);
		return result;
	}
}
