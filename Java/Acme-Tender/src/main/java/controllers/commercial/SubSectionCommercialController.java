
package controllers.commercial;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Commercial;
import domain.SubSection;
import services.CommercialService;
import services.SubSectionService;

@Controller
@RequestMapping("/subSection/commercial")
public class SubSectionCommercialController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private SubSectionService	subSectionService;
	@Autowired
	private CommercialService	commercialService;


	// Constructor -----------------------------------------------------------
	public SubSectionCommercialController() {
		super();
	}

	// Create ---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int offerId) {

		final ModelAndView result = new ModelAndView("subSection/commercial/create");

		final SubSection subSection = this.subSectionService.createByCommercialPropietary(offerId);
		result.addObject("subSection", subSection);
		result.addObject("requestUri", "subSection/commercial/edit.do");

		return result;
	}

	// Edit ---------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int subSectionId) {
		ModelAndView result;
		SubSection subSection = this.subSectionService.findOne(subSectionId);
		Commercial commercial = this.commercialService.findByPrincipal();

		if (subSection.getCommercial() != null)
			Assert.isTrue(commercial.getId() == subSection.getCommercial().getId());

		result = this.createEditModelAndView(subSection);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("subSection") @Valid final SubSection subSection, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(subSection);
		else
			try {
				this.subSectionService.save(subSection);
				result = new ModelAndView("redirect:/offer/display.do?offerId=" + subSection.getOffer().getId());

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(subSection, "subSection.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final SubSection subSection, final BindingResult binding) {
		ModelAndView result;
		Commercial commercial = this.commercialService.findByPrincipal();

		if (subSection.getCommercial() != null)
			Assert.isTrue(commercial.getId() == subSection.getCommercial().getId());

		try {
			this.subSectionService.delete(subSection);
			result = new ModelAndView("redirect:/offer/display.do?offerId=" + subSection.getOffer().getId());

		} catch (final Throwable oops) {
			result = this.createEditModelAndView(subSection, "subSection.commit.error");
		}
		return result;
	}

	// Auxiliary methods ----------------------------------------------------
	protected ModelAndView createEditModelAndView(final SubSection subSection) {
		final ModelAndView result;
		result = this.createEditModelAndView(subSection, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final SubSection subSection, final String message) {

		final ModelAndView result = new ModelAndView("subSection/commercial/edit");
		result.addObject("subSection", subSection);
		result.addObject("requestUri", "subSection/commercial/edit.do");
		result.addObject("message", message);
		return result;
	}

}
