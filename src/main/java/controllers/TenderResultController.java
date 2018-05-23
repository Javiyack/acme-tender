
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CompanyResultService;
import services.TenderResultService;
import services.TenderService;
import domain.CompanyResult;
import domain.TenderResult;

@Controller
@RequestMapping("/tenderResult")
public class TenderResultController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private TenderResultService		tenderResultService;
	@Autowired
	private TenderService			tenderService;
	@Autowired
	private CompanyResultService	companyResultService;


	// Constructor -----------------------------------------------------------
	public TenderResultController() {
		super();
	}

	// Display ---------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int tenderId) {
		ModelAndView result;
		final TenderResult tenderResult;

		tenderResult = this.tenderResultService.findOneByTenderAnonymous(tenderId);
		if (tenderResult != null) {
			final Collection<CompanyResult> companyResults = this.companyResultService.findAllByTenderResultAnonymous(tenderResult.getId());
			result = new ModelAndView("tenderResult/display");
			result.addObject("tenderResult", tenderResult);
			result.addObject("companyResults", companyResults);
			result.addObject("tenderId", tenderId);
			result.addObject("anonymous", true);
		} else {
			result = new ModelAndView("tenderResult/display");
			result.addObject("tenderResult", tenderResult);
			result.addObject("tenderId", tenderId);
		}

		return result;
	}

}
