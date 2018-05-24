
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.OfferService;
import services.TabooWordService;
import controllers.AbstractController;
import domain.Offer;

@Controller
@RequestMapping("/offer/administrator")
public class OfferAdminController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private TabooWordService	tabooWordService;
	@Autowired
	private OfferService		offerService;


	// Constructor -----------------------------------------------------------
	public OfferAdminController() {
		super();
	}

	// List Tenders ---------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listOffer() {

		final ModelAndView result;

		final Collection<Offer> offers = this.offerService.findAllOfferWithTabooWords();

		result = new ModelAndView("offer/administrator/list");
		result.addObject("offers", offers);

		return result;
	}

}
