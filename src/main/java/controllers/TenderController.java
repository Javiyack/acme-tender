
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.TenderService;
import domain.Tender;

@Controller
@RequestMapping("/tender")
public class TenderController extends AbstractController {

	// Supporting services -----------------------------------------------------

	@Autowired
	TenderService	tenderService;


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

		return result;
	}
}
