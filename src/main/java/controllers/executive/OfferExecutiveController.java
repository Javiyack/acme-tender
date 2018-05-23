
package controllers.executive;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Offer;
import services.OfferService;

@Controller
@RequestMapping("/offer/executive")
public class OfferExecutiveController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private OfferService offerService;


	// Constructor -----------------------------------------------------------
	public OfferExecutiveController() {
		super();
	}



	// Edit ---------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int offerId) {
		ModelAndView result;
		final Offer offer = this.offerService.findOne(offerId);

		result = this.createEditModelAndView(offer);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("offer") @Valid final Offer offer, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(offer);
		else
			try {
				Offer saved = this.offerService.save(offer);
				result = new ModelAndView("redirect:/offer/display.do?offerId=" + saved.getId());

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(offer, "offer.commit.error");
			}
		return result;
	}

	// List ---------------------------------------------------------------
	@RequestMapping(value = "/listNotPublished", method = RequestMethod.GET)
	public ModelAndView listOffersByPropietary() {

		ModelAndView result;

		final Collection<Offer> offers = this.offerService.findAllNotPublished();

		result = new ModelAndView("offer/executive/listNotPublished");
		result.addObject("offers", offers);
		result.addObject("requestUri", "offer/executive/listNotPublished.do");

		return result;
	}

	// Auxiliary methods ----------------------------------------------------
	protected ModelAndView createEditModelAndView(final Offer offer) {
		final ModelAndView result;
		result = this.createEditModelAndView(offer, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Offer offer, final String message) {

		final ModelAndView result = new ModelAndView("offer/executive/edit");
		result.addObject("offer", offer);

		result.addObject("message", message);
		return result;
	}
}
