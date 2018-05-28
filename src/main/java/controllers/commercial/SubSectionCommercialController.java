
package controllers.commercial;

import java.util.Collection;

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
import domain.Actor;
import domain.CollaborationRequest;
import domain.Commercial;
import domain.SubSection;
import services.ActorService;
import services.CollaborationRequestService;
import services.ComboService;
import services.CommercialService;
import services.MyMessageService;
import services.SubSectionService;

@Controller
@RequestMapping("/subSection/commercial")
public class SubSectionCommercialController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private SubSectionService			subSectionService;
	@Autowired
	private CommercialService			commercialService;
	@Autowired
	private ComboService				comboService;
	@Autowired
	private CollaborationRequestService	collaborationRequestService;
	@Autowired
	private MyMessageService			myMessageService;
	@Autowired
	private ActorService				actorService;


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
		result.addObject("requestURI", "subSection/commercial/edit.do");

		Collection<String> subSectionSectionsCombo = this.comboService.subSectionSections();
		result.addObject("subSectionSectionsCombo", subSectionSectionsCombo);
		result.addObject("request", false);
		result.addObject("requestId", 0);

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
		result.addObject("request", false);
		result.addObject("requestId", 0);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("subSection") @Valid final SubSection subSection, final BindingResult binding, @ModelAttribute("request") Boolean request, @ModelAttribute("requestId") int requestId) {
		ModelAndView result;

		if (binding.hasErrors()) {

			result = this.createEditModelAndView(subSection);
			result.addObject("request", request);
			result.addObject("requestId", requestId);

		} else {
			try {
				this.subSectionService.save(subSection);
				if (request == true) {
					CollaborationRequest collaborationRequest = collaborationRequestService.findOne(requestId);
					Actor principal = actorService.findByPrincipal();
					Assert.notNull(principal);
					Assert.isTrue(principal.equals(collaborationRequest.getCommercial()) && collaborationRequest.getAccepted() == null);
					collaborationRequest.setAccepted(true);
					CollaborationRequest saved = this.collaborationRequestService.save(collaborationRequest);
					this.myMessageService.collaborationRequestNotification(saved, true);
				}
				result = new ModelAndView("redirect:/offer/display.do?offerId=" + subSection.getOffer().getId());

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(subSection, "subSection.commit.error");
			}

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

		ModelAndView result = null;
		if (subSection.getId() == 0)
			result = new ModelAndView("subSection/commercial/create");
		else
			result = new ModelAndView("subSection/commercial/edit");

		result.addObject("subSection", subSection);
		result.addObject("requestURI", "subSection/commercial/edit.do");
		result.addObject("message", message);
		Collection<String> subSectionSectionsCombo = this.comboService.subSectionSections();
		result.addObject("subSectionSectionsCombo", subSectionSectionsCombo);
		return result;
	}

}
