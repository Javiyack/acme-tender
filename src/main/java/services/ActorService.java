
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

import repositories.ActorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;
import domain.Actor;
import domain.Administrative;
import domain.Administrator;
import domain.Commercial;
import domain.Executive;
import forms.RegisterForm;

@Service
@Transactional
public class ActorService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private ActorRepository			actorRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private UserAccountService		userAccountService;
	@Autowired
	private AdministratorService	administratorService;
	@Autowired
	private FolderService			folderService;

	@Autowired
	private Validator				validator;


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
	
	public Collection<Actor> findAllActivated() {
		Collection<Actor> result;

		result = this.actorRepository.findAllActivated();
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
			Assert.isTrue(actor.equals(this.findByPrincipal()), "not.allowed.action");

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

	public Actor recontruct(final RegisterForm actorForm, final BindingResult binding) {
		Actor logedActor = null;
		UserAccount useraccount = null;
		Md5PasswordEncoder encoder;
		encoder = new Md5PasswordEncoder();
		if (actorForm.getId() == 0) {
			actorForm.setNewPassword(actorForm.getPassword());
			useraccount = new UserAccount();
			switch (actorForm.getAuthority()) {
			case Authority.ADMINISTRATIVE:
				logedActor = new Administrative();
				useraccount = this.userAccountService.createAsAdministrative();
				logedActor.setUserAccount(useraccount);
				break;

			case Authority.COMMERCIAL:
				logedActor = new Commercial();
				useraccount = this.userAccountService.createAsCommercial();
				logedActor.setUserAccount(useraccount);
				break;

			case Authority.EXECUTIVE:
				logedActor = new Executive();
				useraccount = this.userAccountService.createAsExecutive();
				logedActor.setUserAccount(useraccount);
				break;

			default:
				break;
			}
			logedActor.getUserAccount().setUsername(actorForm.getUsername());
			logedActor.getUserAccount().setPassword(actorForm.getPassword());
			logedActor.setName(actorForm.getName());
			logedActor.setSurname(actorForm.getSurname());
			logedActor.setEmail(actorForm.getEmail());
			logedActor.setAddress(actorForm.getAddress());
			logedActor.setPhone(actorForm.getPhone());
			this.validator.validate(actorForm, binding);
			Assert.isTrue(actorForm.getPassword().equals(actorForm.getConfirmPassword()), "profile.userAccount.repeatPassword.mismatch");
			logedActor.getUserAccount().setPassword(encoder.encodePassword(actorForm.getPassword(), null));

			//Al registrarse, el usuario esta desactivado. El admin debe de activarlo.
			useraccount.setActive(false);
			this.folderService.createSystemFolders(logedActor);

		} else {
			final String formPass = encoder.encodePassword(actorForm.getPassword(), null);
			logedActor = this.findByPrincipal();
			logedActor.getUserAccount().setUsername(actorForm.getUsername());
			logedActor.setName(actorForm.getName());
			logedActor.setSurname(actorForm.getSurname());
			logedActor.setEmail(actorForm.getEmail());
			logedActor.setAddress(actorForm.getAddress());
			logedActor.setPhone(actorForm.getPhone());
			if (actorForm.getPassword().isEmpty()) {
				actorForm.setNewPassword("XXXXX");
				actorForm.setConfirmPassword("XXXXX");
				this.validator.validate(actorForm, binding);
			} else if (!actorForm.getNewPassword().isEmpty()) {
				this.validator.validate(actorForm, binding);
				Assert.isTrue(actorForm.getNewPassword().equals(actorForm.getConfirmPassword()), "profile.userAccount.repeatPassword.mismatch");
				actorForm.setPassword(actorForm.getNewPassword());
			}
			Assert.isTrue(formPass.equals(logedActor.getUserAccount().getPassword()), "profile.wrong.password");
			logedActor.getUserAccount().setPassword(encoder.encodePassword(actorForm.getPassword(), null));

		}

		return logedActor;
	}

	public void flush() {
		this.actorRepository.flush();

	}
}
