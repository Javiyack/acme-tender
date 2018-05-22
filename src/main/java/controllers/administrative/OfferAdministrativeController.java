
package controllers.administrative;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Offer;
import services.OfferService;

@Controller
@RequestMapping("/offer/administrative")
public class OfferAdministrativeController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private OfferService offerService;


	// Constructor -----------------------------------------------------------
	public OfferAdministrativeController() {
		super();
	}

	@RequestMapping(value = "/listOffersByCollaboration", method = RequestMethod.GET)
	public ModelAndView listOffersByCollaboration() {

		ModelAndView result;

		final Collection<Offer> offers = this.offerService.findAllByAdministrativeColaboration();

		result = new ModelAndView("offer/administrative/listOffersByCollaboration");
		result.addObject("offers", offers);
		result.addObject("requestUri", "offer/administrative/listOffersByCollaboration.do");

		return result;
	}

}
