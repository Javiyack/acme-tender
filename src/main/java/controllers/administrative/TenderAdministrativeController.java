
package controllers.administrative;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Tender;
import services.TenderService;

@Controller
@RequestMapping("/tender/administrative")
public class TenderAdministrativeController extends AbstractController {

	// Supporting services -----------------------------------------------------

	@Autowired
	TenderService tenderService;


	// Constructors -----------------------------------------------------------

	public TenderAdministrativeController() {
		super();
	}

	// List ------------------------------------------------------------------
	@RequestMapping(value = "/list")
	public ModelAndView list() {
		ModelAndView result;

		final Collection<Tender> tenders = this.tenderService.findAllByAdministrative();

		result = new ModelAndView("tender/administrative/list");
		result.addObject("tenders", tenders);
		result.addObject("myTender", true);
		result.addObject("requestUri", "tender/administrative/list.do");

		return result;
	}
}
