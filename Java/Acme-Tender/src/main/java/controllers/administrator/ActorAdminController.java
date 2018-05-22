
package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import services.ActorService;

@Controller
@RequestMapping("/actor/administrator")
public class ActorAdminController extends AbstractController {

	// Supporting services -----------------------------------------------------

	@Autowired
	ActorService actorService;


	// Constructors -----------------------------------------------------------

	public ActorAdminController() {
		super();
	}

	// ActivateOrDeactivate ------------------------------------------------------------------
	@RequestMapping(value = "/activeOrDeactivate", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int actorId) {
		ModelAndView result;

		this.actorService.ActivateOrDeactivate(actorId);

		result = new ModelAndView("redirect:/actor/list.do");

		return result;
	}

}
