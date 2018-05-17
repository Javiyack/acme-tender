
package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import controllers.AbstractController;

@Controller
@RequestMapping("/actor/administrator")
public class ActorAdminController extends AbstractController {

	// Supporting services -----------------------------------------------------

	@Autowired
	ActorService	actorService;


	// Constructors -----------------------------------------------------------

	public ActorAdminController() {
		super();
	}

	// ActivateOrDesactivate ------------------------------------------------------------------
	@RequestMapping(value = "/activeOrDesactive", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int actorId) {
		ModelAndView result;

		this.actorService.ActivateOrDesactivate(actorId);

		result = new ModelAndView("redirect:/actor/list.do");

		return result;
	}

}
