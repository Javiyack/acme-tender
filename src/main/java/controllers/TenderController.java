
package controllers;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Actor;
import domain.Comment;
import domain.CompanyResult;
import domain.Constant;
import domain.EvaluationCriteria;
import domain.Offer;
import domain.SubSection;
import domain.Tender;
import domain.TenderResult;
import forms.SearchForm;
import services.ActorService;
import services.CommentService;
import services.CompanyResultService;
import services.ConfigurationService;
import services.EvaluationCriteriaService;
import services.TenderResultService;
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
	@Autowired
	private EvaluationCriteriaService evaluationCriteriaService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private TenderResultService tenderResultService;
	@Autowired
	private CompanyResultService companyResultService;


	// Constructors -----------------------------------------------------------

	public TenderController() {
		super();
	}
	
	//Display
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int tenderId) {

		ModelAndView result;

		Tender tender = tenderService.findOne(tenderId);
		Double benefitsPercentage = this.configurationService.findBenefitsPercentage();
		Actor actor = this.actorService.findByPrincipal();
		
		Collection<EvaluationCriteria> evaluationCriterias = this.evaluationCriteriaService.findAllByTender(tender.getId());

		Collection<Comment> comments = this.commentService.findAllByTender(tenderId);
		TenderResult tenderResult = this.tenderResultService.findOneByTenderAnonymous(tenderId);
		
		Collection<CompanyResult> companyResults = new ArrayList<CompanyResult>();
		if (tenderResult != null)
			companyResults = this.companyResultService.findAllByTenderResultAnonymous(tenderResult.getId());

		result = new ModelAndView("tender/display");
		result.addObject("tender", tender);
		result.addObject("benefitsPercentage", benefitsPercentage);
		result.addObject("actor", actor);
		result.addObject("evaluationCriterias", evaluationCriterias);
		result.addObject("comments", comments);
		result.addObject("tenderResult", tenderResult);
		result.addObject("companyResults", companyResults);

		return result;

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

	
	@RequestMapping(value = "/search")
	public ModelAndView create() {
		ModelAndView result;
		final SearchForm searchForm = new SearchForm();

		result = this.createEditModelAndView(searchForm);

		return result;
	}
	@RequestMapping(value = "/search", method = RequestMethod.POST, params = "searchButton")
	public ModelAndView search(final HttpServletRequest request, @Valid final SearchForm searchForm, final BindingResult binding) {
		ModelAndView result;
		final String word = request.getParameter("word");
		result = this.searchResult(word);

		return result;
	}

	// searchResult ---------------------------------------------------------------
	@RequestMapping(value = "/searchResult", method = RequestMethod.GET)
	public ModelAndView searchResult(@RequestParam final String word) {
		ModelAndView result;
		
		final Collection<Tender> tenders = this.tenderService.findTenderByKeyWord(word);
		final Double benefitsPercentaje = this.configurationService.findBenefitsPercentage();
		final Actor actor = this.actorService.findByPrincipal();

		result = new ModelAndView("tender/searchResult");
		result.addObject("requestUri", "tender/searchResult.do");
		result.addObject("tenders", tenders);
		result.addObject("benefitsPercentaje", benefitsPercentaje);
		result.addObject("actorId", actor.getId());		
		result.addObject("backSearch", true);

		return result;
	}
	
	
	protected ModelAndView createEditModelAndView(final SearchForm searchForm) {
		final ModelAndView result;
		result = this.createEditModelAndView(searchForm, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final SearchForm searchForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("tender/search");
		result.addObject("searchForm", searchForm);

		return result;
	}
	

}
