
package controllers.administrative;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.CompanyResult;
import domain.Tender;
import domain.TenderResult;
import services.CompanyResultService;
import services.TenderResultService;
import services.TenderService;
import exceptions.HackingException;

@Controller
@RequestMapping("/tenderResult/administrative")
public class TenderResultAdministrativeController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private TenderResultService		tenderResultService;
	@Autowired
	private TenderService			tenderService;
	@Autowired
	private CompanyResultService	companyResultService;


	// Constructor -----------------------------------------------------------
	public TenderResultAdministrativeController() {
		super();
	}

	// Create ---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int tenderId) {
		ModelAndView result;
		final TenderResult tenderResult;

		tenderResult = this.tenderResultService.create(tenderId);
		result = this.createEditModelAndView(tenderResult);

		return result;
	}

	// Save ---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final TenderResult tenderResult, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(tenderResult);
		else
			try {
				this.tenderResultService.save(tenderResult);
				result = new ModelAndView("redirect:/tender/administrative/list.do");
				result.addObject("myTender", true);
				result.addObject("requestUri", "tender/administrative/list.do");
			} catch (final Throwable oops) {
				if (oops.getMessage() == "Has a result")
					result = this.createEditModelAndView(tenderResult, "tenderResult.has.result");
				else
					result = this.createEditModelAndView(tenderResult, "tenderResult.commit.error");
			}
		return result;
	}

	// Delete ---------------------------------------------------------------
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam(required = false) final Integer tenderResultId) {
		ModelAndView result;

		Assert.notNull(tenderResultId);
		final TenderResult tenderResult = this.tenderResultService.findOneToDisplay(tenderResultId);
		final Integer tenderId = tenderResult.getTender().getId();

		try {
			this.tenderResultService.delete(tenderResult);
			result = new ModelAndView("tender/administrative/list");
			result.addObject("myTender", true);
			result.addObject("requestUri", "tender/administrative/list.do");
		} catch (final Throwable ooops) {
			final Collection<CompanyResult> companyResults = this.companyResultService.findAllByTenderResult(tenderResult.getId());
			result = new ModelAndView("tenderResult/administrative/display");
			result.addObject("tenderResult", tenderResult);
			result.addObject("companyResultCreate", true);
			result.addObject("companyResults", companyResults);
			result.addObject("tenderId", tenderId);
			result.addObject("message", "tenderResult.commit.error");
		}
		return result;
	}

	// Display ---------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int tenderId) throws HackingException {
		ModelAndView result;
		final TenderResult tenderResult;

		try {
			tenderResult = this.tenderResultService.findOneByTender(tenderId);
			if (tenderResult != null) {
				final Collection<CompanyResult> companyResults = this.companyResultService.findAllByTenderResult(tenderResult.getId());
				result = new ModelAndView("tenderResult/administrative/display");
				result.addObject("tenderResult", tenderResult);
				result.addObject("companyResultCreate", true);
				result.addObject("companyResults", companyResults);
				result.addObject("tenderId", tenderId);
			} else {
				result = new ModelAndView("tenderResult/administrative/display");
				result.addObject("tenderResult", tenderResult);
				result.addObject("tenderId", tenderId);
			}
		} catch (final Throwable ooops) {
			throw new HackingException(ooops);
		}
		return result;
	}
	// Auxiliary methods ----------------------------------------------------
	protected ModelAndView createEditModelAndView(final TenderResult tenderResult) {
		final ModelAndView result;
		result = this.createEditModelAndView(tenderResult, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final TenderResult tenderResult, final String message) {

		final ModelAndView result = new ModelAndView("tenderResult/administrative/create");
		result.addObject("tenderResult", tenderResult);
		result.addObject("message", message);
		return result;
	}

	protected ModelAndView createEditModelAndView2(final Collection<Tender> tenders) {
		final ModelAndView result;
		result = this.createEditModelAndView2(tenders, null);
		return result;
	}

	protected ModelAndView createEditModelAndView2(final Collection<Tender> tenders, final String message) {

		final ModelAndView result = new ModelAndView("tender/administrative/list");
		result.addObject("tenders", tenders);
		result.addObject("message", message);
		return result;
	}
}
