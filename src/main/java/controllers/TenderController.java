package controllers;

import domain.*;
import forms.SearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/tender")
public class TenderController extends AbstractController {

    // Supporting services -----------------------------------------------------

    @Autowired
    TenderService tenderService;
    @Autowired
    private ActorService actorService;
    @Autowired
    private ConfigurationService configurationService;
    @Autowired
    private EvaluationCriteriaService evaluationCriteriaService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private TenderResultService tenderResultService;
    @Autowired
    private CompanyResultService companyResultService;
    @Autowired
    private FileService fileService;


    // Constructors -----------------------------------------------------------

    public TenderController() {
        super();
    }

    //Display
    @RequestMapping(value = "/display", method = RequestMethod.GET)
    public ModelAndView display(@RequestParam final int tenderId) {

        ModelAndView result;

        final Tender tender = this.tenderService.findOne(tenderId);
        final Double benefitsPercentage = this.configurationService.findBenefitsPercentage();
        final Actor actor = this.actorService.findByPrincipal();

        final Collection<File> files = this.fileService.findAllByTender(tenderId);

        final Collection<EvaluationCriteria> evaluationCriterias = this.evaluationCriteriaService.findAllByTender(tender.getId());

        final Collection<Comment> comments = this.commentService.findAllByTender(tenderId);
        final TenderResult tenderResult = this.tenderResultService.findOneByTenderAnonymous(tenderId);

        Collection<CompanyResult> companyResults = new ArrayList<CompanyResult>();
        if (tenderResult != null)
            companyResults = this.companyResultService.findAllByTenderResultAnonymous(tenderResult.getId());

        result = new ModelAndView("tender/display");
        result.addObject("tender", tender);
        result.addObject("benefitsPercentage", benefitsPercentage);
        result.addObject("actor", actor);
        result.addObject("evaluationCriterias", evaluationCriterias);
        result.addObject("comments", comments);
        result.addObject("files", files);
        result.addObject("tenderResult", tenderResult);
        result.addObject("companyResults", companyResults);

        return result;

    }

    // List ------------------------------------------------------------------
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(final Integer pageSize) {
        ModelAndView result;
        final Double benefitsPercentaje = this.configurationService.findBenefitsPercentage();

        final Collection<Tender> tenders = this.tenderService.findAll();
        final Actor actor = this.actorService.findByPrincipal();

        result = new ModelAndView("tender/list");
        result.addObject("tenders", tenders);
        result.addObject("actor", actor);
        result.addObject("benefitsPercentaje", benefitsPercentaje);
        result.addObject("requestUri", "tender/list.do");
        result.addObject("pageSize", (pageSize != null) ? pageSize : 5);

        return result;
    }

    // List ------------------------------------------------------------------
    @RequestMapping(value = "/listOffertable", method = RequestMethod.GET)
    public ModelAndView listWithoutOffer(final Integer pageSize) {
        ModelAndView result;
        final Double benefitsPercentaje = this.configurationService.findBenefitsPercentage();

        final Collection<Tender> tenders = this.tenderService.findAllOffertable();
        final Actor actor = this.actorService.findByPrincipal();

        result = new ModelAndView("tender/listOffertable");
        result.addObject("tenders", tenders);
        result.addObject("actor", actor);
        result.addObject("benefitsPercentaje", benefitsPercentaje);
        result.addObject("requestUri", "tender/listOffertable.do");
        result.addObject("pageSize", (pageSize != null) ? pageSize : 5);

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
        result = this.searchResult(word, 5);

        return result;
    }

    // searchResult ---------------------------------------------------------------
    @RequestMapping(value = "/searchResult", method = RequestMethod.GET)
    public ModelAndView searchResult(@RequestParam final String word, final Integer pageSize) {
        ModelAndView result;

        final Collection<Tender> tenders = this.tenderService.findTenderByKeyWord(word);
        final Double benefitsPercentaje = this.configurationService.findBenefitsPercentage();
        final Actor actor = this.actorService.findByPrincipal();

        result = new ModelAndView("tender/searchResult");
        result.addObject("requestUri", "tender/searchResult.do");
        result.addObject("tenders", tenders);
        result.addObject("actor", actor);
        result.addObject("benefitsPercentaje", benefitsPercentaje);
        result.addObject("actorId", actor.getId());
        result.addObject("backSearch", true);
        result.addObject("word", word);
        result.addObject("pageSize", (pageSize != null) ? pageSize : 5);

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
