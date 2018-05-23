
package controllers.administrative;

import java.util.Collection;
import java.util.LinkedList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ComboService;
import services.CompanyResultService;
import services.TenderResultService;
import services.TenderService;
import controllers.AbstractController;
import domain.CompanyResult;
import domain.TenderResult;

@Controller
@RequestMapping("/companyResult/administrative")
public class CompanyResultAdministrativeController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private TenderResultService		tenderResultService;
	@Autowired
	private ComboService			comboService;
	@Autowired
	private CompanyResultService	companyResultService;


	// Constructor -----------------------------------------------------------
	public CompanyResultAdministrativeController() {
		super();
	}

	// Create ---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int tenderResultId) {
		ModelAndView result;
		final CompanyResult companyResult;

		companyResult = this.companyResultService.create(tenderResultId);
		result = this.createEditModelAndView(companyResult);

		return result;
	}

	// Save ---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final CompanyResult companyResult, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(companyResult);
		else
			try {
				this.companyResultService.save(companyResult);
				final Collection<CompanyResult> companyResults = this.companyResultService.findAllByTenderResult(companyResult.getTenderResult().getId());
				result = new ModelAndView("redirect:/tenderResult/administrative/display.do?tenderId=" + companyResult.getTenderResult().getTender().getId());
				result.addObject("tenderResult", companyResult.getTenderResult());
				result.addObject("companyResultCreate", true);
				result.addObject("companyResults", companyResults);
				result.addObject("tenderId", companyResult.getTenderResult().getTender().getId());
			} catch (final Throwable oops) {
				if (oops.getMessage() == "Only one Winner")
					result = this.createEditModelAndView(companyResult, "companyResult.only.winner");
				else if (oops.getMessage() == "Can not repeat position")
					result = this.createEditModelAndView(companyResult, "companyResult.not.repeat.position");
				else
					result = this.createEditModelAndView(companyResult, "companyResult.commit.error");
			}
		return result;
	}

	// Delete ---------------------------------------------------------------
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam(required = false) final Integer companyResultId) {
		ModelAndView result;
		final CompanyResult companyResult = this.companyResultService.findOne(companyResultId);
		final TenderResult tenderResult = companyResult.getTenderResult();
		final Integer tenderResultId = companyResult.getTenderResult().getId();
		final Integer tenderId = companyResult.getTenderResult().getTender().getId();

		try {
			this.companyResultService.delete(companyResult);
			final Collection<CompanyResult> companyResults = this.companyResultService.findAllByTenderResult(tenderResultId);
			result = new ModelAndView("redirect:/tenderResult/administrative/display.do?tenderId=" + tenderId);
			result.addObject("tenderResult", tenderResult);
			result.addObject("companyResultCreate", true);
			result.addObject("companyResults", companyResults);
			result.addObject("tenderId", tenderId);
		} catch (final Throwable ooops) {
			final Collection<CompanyResult> companyResults = this.companyResultService.findAllByTenderResult(tenderResultId);
			result = new ModelAndView("redirect:/tenderResult/administrative/display.do?tenderId=" + tenderId);
			result.addObject("tenderResult", tenderResult);
			result.addObject("companyResultCreate", true);
			result.addObject("companyResults", companyResults);
			result.addObject("tenderId", tenderId);
		}
		return result;
	}
	// Auxiliary methods ----------------------------------------------------
	protected ModelAndView createEditModelAndView(final CompanyResult companyResult) {
		final ModelAndView result;
		result = this.createEditModelAndView(companyResult, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final CompanyResult companyResult, final String message) {

		final ModelAndView result = new ModelAndView("companyResult/administrative/create");
		
		Collection<String> companyResultStatesCombo = this.comboService.companyResultStates(companyResult);
		result.addObject("companyResultStatesCombo", companyResultStatesCombo);
		
		result.addObject("companyResult", companyResult);
		result.addObject("message", message);
		return result;
	}

}
