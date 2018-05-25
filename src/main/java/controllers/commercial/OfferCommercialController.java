
package controllers.commercial;

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
import domain.Actor;
import domain.Offer;
import services.ActorService;
import services.ComboService;
import services.OfferService;

@Controller
@RequestMapping("/offer/commercial")
public class OfferCommercialController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private OfferService offerService;
	@Autowired
	private ComboService comboService;
	@Autowired
	private ActorService			actorService;	


	// Constructor -----------------------------------------------------------
	public OfferCommercialController() {
		super();
	}

	// Create ---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int tenderId) {

		final ModelAndView result = new ModelAndView("offer/commercial/create");

		final Offer offer = this.offerService.create(tenderId);
		result.addObject("offer", offer);
		
		Collection<String> statesCombo = this.comboService.offerStates(offer.getId());
		result.addObject("statesCombo", statesCombo);
		
		
		return result;
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
	@RequestMapping(value = "/listOffersByPropietary", method = RequestMethod.GET)
	public ModelAndView listOffersByPropietary() {

		ModelAndView result;

		final Collection<Offer> offers = this.offerService.findAllByCommercialPropietary();

		result = new ModelAndView("offer/commercial/listOffersByPropietary");
		result.addObject("offers", offers);
		result.addObject("requestUri", "offer/commercial/listOffersByPropietary.do");
		
		Actor actor = this.actorService.findByPrincipal();
		result.addObject("actorId", actor.getId());

		return result;
	}

	@RequestMapping(value = "/listOffersByCollaboration", method = RequestMethod.GET)
	public ModelAndView listOffersByCollaboration() {

		ModelAndView result;

		final Collection<Offer> offers = this.offerService.findAllByCommercialColaboration();

		result = new ModelAndView("offer/commercial/listOffersByCollaboration");
		result.addObject("offers", offers);
		result.addObject("requestUri", "offer/commercial/listOffersByCollaboration.do");
		
		Actor actor = this.actorService.findByPrincipal();
		result.addObject("actorId", actor.getId());		

		return result;
	}

	// Auxiliary methods ----------------------------------------------------
	protected ModelAndView createEditModelAndView(final Offer offer) {
		final ModelAndView result;
		result = this.createEditModelAndView(offer, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Offer offer, final String message) {

		ModelAndView result = null;
		if (offer.getId()==0)
			result = new ModelAndView("offer/commercial/create");
		else
			result = new ModelAndView("offer/commercial/edit");

		result.addObject("offer", offer);
		
		Collection<String> statesCombo = this.comboService.offerStates(offer.getId());
		result.addObject("statesCombo", statesCombo);		

		result.addObject("message", message);
		return result;
	}
}
