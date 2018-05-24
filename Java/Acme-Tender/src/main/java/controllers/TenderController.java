
package controllers;

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
import domain.Tender;
import forms.SearchForm;
import services.ActorService;
import services.ConfigurationService;
import services.TenderService;

@Controller
@RequestMapping("/tender")
public class TenderController extends AbstractController {

	// Supporting services -----------------------------------------------------

	@Autowired
	TenderService					tenderService;
	@Autowired
	private ActorService			actorService;
	@Autowired
	private ConfigurationService	configurationService;


	// Constructors -----------------------------------------------------------

	public TenderController() {
		super();
	}

	// List ------------------------------------------------------------------
	@RequestMapping(value = "/list")
	public ModelAndView list() {
		ModelAndView result;

		final Collection<Tender> tenders = this.tenderService.findAll();

		result = new ModelAndView("tender/list");
		result.addObject("tenders", tenders);
		result.addObject("requestUri", "tender/list.do");
		result.addObject("anonymous", true);

		return result;
	}

	// Search ---------------------------------------------------------------
	@RequestMapping(value = "/search")
	public ModelAndView create() {
		ModelAndView result;
		final SearchForm searchForm = new SearchForm();

		result = new ModelAndView("tender/search");
		result.addObject("searchForm", searchForm);

		return result;
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST, params = "searchButton")
	public ModelAndView search(@Valid final SearchForm searchForm, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = new ModelAndView("tender/search");
			result.addObject("searchForm", searchForm);
		} else
			result = this.searchResult(searchForm.getWord());
		return result;
	}

	// searchResult ---------------------------------------------------------------
	@RequestMapping(value = "/searchResult", method = RequestMethod.GET)
	public ModelAndView searchResult(@RequestParam final String word) {
		ModelAndView result;

		final Collection<Tender> tenders = this.tenderService.findTenderByKeyWord(word);
		final Double benefitsPercentaje = this.configurationService.findBenefitsPercentage();
		final Actor actor = this.actorService.findByPrincipal();

		result = new ModelAndView("tender/listSearch");
		result.addObject("tenders", tenders);
		result.addObject("benefitsPercentaje", benefitsPercentaje);
		result.addObject("actorId", actor.getId());
		result.addObject("requestUri", "tender/list.do");

		return result;
	}

}
