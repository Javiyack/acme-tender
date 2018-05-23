
package controllers;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Actor;
import domain.Offer;
import domain.SubSection;
import forms.SearchForm;
import services.ActorService;
import services.ConfigurationService;
import services.OfferService;
import services.SubSectionService;

@Controller
@RequestMapping("/offer")
public class OfferController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private OfferService			offerService;
	@Autowired
	private ActorService			actorService;
	@Autowired
	private SubSectionService		subSectionService;
	@Autowired
	private ConfigurationService	configurationService;


	// Constructor -----------------------------------------------------------
	public OfferController() {
		super();
	}

	//Display
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int offerId) {

		ModelAndView result;

		Offer offer = offerService.findOne(offerId);
		Double benefitsPercentage = this.configurationService.findBenefitsPercentage();
		Actor actor = this.actorService.findByPrincipal();

		Collection<SubSection> subSections = this.subSectionService.findAllByOffer(offerId);

		Collection<SubSection> subSectionsAcreditation = new ArrayList<SubSection>();
		Collection<SubSection> subSectionsTechnical = new ArrayList<SubSection>();
		Collection<SubSection> subSectionsEconomical = new ArrayList<SubSection>();

		for (SubSection ss : subSections) {
			if (ss.getSection().equals("ADMINISTRATIVE_ACREDITATION"))
				subSectionsAcreditation.add(ss);
			if (ss.getSection().equals("TECHNICAL_OFFER"))
				subSectionsTechnical.add(ss);
			if (ss.getSection().equals("ECONOMICAL_OFFER"))
				subSectionsEconomical.add(ss);
		}

		result = new ModelAndView("offer/display");
		result.addObject("offer", offer);
		result.addObject("subSectionsAcreditation", subSectionsAcreditation);
		result.addObject("subSectionsTechnical", subSectionsTechnical);
		result.addObject("subSectionsEconomical", subSectionsEconomical);
		result.addObject("benefitsPercentage", benefitsPercentage);
		result.addObject("actorId", actor.getId());

		return result;

	}

	// List ---------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;

		final Collection<Offer> offers = this.offerService.findAllPublished();
		Double benefitsPercentaje = this.configurationService.findBenefitsPercentage();
		Actor actor = this.actorService.findByPrincipal();

		result = new ModelAndView("offer/list");
		result.addObject("offers", offers);
		result.addObject("benefitsPercentaje", benefitsPercentaje);
		result.addObject("actorId", actor.getId());
		result.addObject("requestUri", "offer/list.do");

		return result;
	}
	
	// Search ---------------------------------------------------------------
	@RequestMapping(value = "/search")
	public ModelAndView create() {
		ModelAndView result;
		final SearchForm searchForm = new SearchForm();

		result = new ModelAndView("offer/search");
		result.addObject("searchForm", searchForm);

		return result;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST, params = "searchButton")
	public ModelAndView search(@Valid final SearchForm searchForm, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = new ModelAndView("offer/search");
			result.addObject("searchForm", searchForm);
		} else {
			result = this.searchResult(searchForm.getWord());
		}
		return result;
	}
	

	// searchResult ---------------------------------------------------------------
	@RequestMapping(value = "/searchResult", method = RequestMethod.GET)
	public ModelAndView searchResult(@RequestParam final String word) {
		ModelAndView result;

		Collection<Offer> offers = this.offerService.findOfferByKeyWord(word);
		Double benefitsPercentaje = this.configurationService.findBenefitsPercentage();
		Actor actor = this.actorService.findByPrincipal();		

		result = new ModelAndView("offer/listSearch");
		result.addObject("offers", offers);
		result.addObject("benefitsPercentaje", benefitsPercentaje);
		result.addObject("actorId", actor.getId());
		result.addObject("requestUri", "offer/list.do");

		return result;
	}
	

	

}
