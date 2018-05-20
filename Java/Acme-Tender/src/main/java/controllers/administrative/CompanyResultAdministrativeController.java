
package controllers.administrative;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CompanyResultService;
import services.TenderResultService;
import services.TenderService;
import domain.CompanyResult;

@Controller
@RequestMapping("/companyResult/administrative")
public class CompanyResultAdministrativeController {

	// Services ---------------------------------------------------------------
	@Autowired
	private TenderResultService		tenderResultService;
	@Autowired
	private TenderService			tenderService;
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

				result = this.createEditModelAndView(companyResult, "companyResult.commit.error");
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
		result.addObject("companyResult", companyResult);
		result.addObject("message", message);
		return result;
	}

}
