
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Actor;
import forms.RegisterForm;
import security.Authority;
import services.ActorService;

@Controller
@RequestMapping("/actor")
public class ActorController extends AbstractController {

	// Supporting services -----------------------------------------------------

	@Autowired
	private ActorService	actorService;


	// Constructors -----------------------------------------------------------

	public ActorController() {
		super();
	}

	// List ------------------------------------------------------------------
	@RequestMapping(value = "/list")
	public ModelAndView list() {
		ModelAndView result;

		final Collection<Actor> actors = this.actorService.findAll();

		result = new ModelAndView("actor/list");
		result.addObject("actors", actors);
		result.addObject("requestUri", "actor/list.do");

		return result;
	}

	// Display user -----------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int actorId) {
		final ModelAndView result;

		final Actor actor;

		actor = this.actorService.findOne(actorId);

		result = new ModelAndView("actor/display");
		result.addObject("actor", actor);

		return result;
	}
	
	// Create ---------------------------------------------------------------

		@RequestMapping("/create")
		public ModelAndView action1() {
			ModelAndView result;
			RegisterForm registerForm = new RegisterForm();
			result = this.createEditModelAndView(registerForm, null);
			return result;
		}

		// Save mediante Post ---------------------------------------------------

		@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
		public ModelAndView save(RegisterForm registerForm, BindingResult binding) {
			ModelAndView result;

			Actor actor = actorService.recontruct(registerForm, binding);
			if (binding.hasErrors())
				result = this.createEditModelAndView(registerForm);
			else
				try {
					this.actorService.save(actor);
					result = new ModelAndView("redirect:/");
				} catch (final Throwable oops) {
					result = this.createEditModelAndView(registerForm, "manager.commit.error");
				}
			return result;
		}

		// Auxiliary methods -----------------------------------------------------
		protected ModelAndView createEditModelAndView(RegisterForm inidencia) {
			final ModelAndView result;
			result = this.createEditModelAndView(inidencia, null);
			return result;
		}

		protected ModelAndView createEditModelAndView(RegisterForm actor, String message) {
			final ModelAndView result;
			Collection<Authority> permisos = Authority.listAuthorities();
			Authority authority = new Authority();
			authority.setAuthority(Authority.ADMIN);
			permisos.remove(authority);
			
			result = new ModelAndView("actor/create");
			result.addObject("registerForm", actor);
			result.addObject("requestUri", "actor/create.do");
			result.addObject("permisos", permisos);
			result.addObject("message", message);

			return result;

		}


}
