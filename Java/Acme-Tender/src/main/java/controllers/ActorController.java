
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import domain.Actor;

@Controller
@RequestMapping("/actor")
public class ActorController extends AbstractController {

	// Supporting services -----------------------------------------------------

	@Autowired
	ActorService	actorService;


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

}
