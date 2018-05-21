
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.OfferService;
import services.SubSectionService;
import domain.Offer;
import domain.SubSection;

@Controller
@RequestMapping("/offer")
public class OfferController {

	// Services ---------------------------------------------------------------
	@Autowired
	private OfferService	offerService;
	@Autowired
	private SubSectionService	subSectionService;	
	
	// Constructor -----------------------------------------------------------
	public OfferController() {
		super();
	}

	//Display
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int offerId) {

		ModelAndView result;

		Offer offer = offerService.findOne(offerId);
		Collection<SubSection> subSections = this.subSectionService.findAllByOffer(offerId);

		result = new ModelAndView("offer/display");
		result.addObject("offer", offer);
		result.addObject("subSections", subSections);

		return result;

	}
	
	// List ---------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;

		final Collection<Offer> offers = this.offerService.findAllPublished();

		result = new ModelAndView("offer/list");
		result.addObject("offers", offers);
		result.addObject("requestUri", "offer/list.do");

		return result;
	}	

}
