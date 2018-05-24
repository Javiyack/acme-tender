
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

	// Create ---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		final Tender tender = this.tenderService.create();

		result = this.createEditModelAndView(tender);
		return result;
	}

	// Edit -----------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int tenderId) {
		ModelAndView result;
		final Tender tender = this.tenderService.findOneToEdit(tenderId);
		Assert.notNull(tender);

		result = this.createEditModelAndView(tender);

		return result;
	}

	// Edit ---------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView edit(@Valid final Tender tender, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(tender);
		else
			try {
				this.tenderService.save(tender);
				result = new ModelAndView("redirect:/tender/administrative/list.do");
				result.addObject("requestUri", "tender/administrative/list.do");
			} catch (final Throwable ooops) {
				result = this.createEditModelAndView(tender, "tender.commit.error");
			}
		return result;
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

	// Auxiliary methods ----------------------------------------------------
	protected ModelAndView createEditModelAndView(final Tender tender) {
		final ModelAndView result;
		result = this.createEditModelAndView(tender, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Tender tender, final String message) {
		ModelAndView result;
		if (tender.getId() != 0)
			result = new ModelAndView("tender/edit");
		else
			result = new ModelAndView("tender/create");

		result.addObject("tender", tender);
		result.addObject("message", message);
		result.addObject("requestUri", "tender/administrative/edit.do");

		return result;
	}
}
