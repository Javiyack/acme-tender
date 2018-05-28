
package controllers.administrative;

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
import domain.Administrative;
import domain.AdministrativeRequest;
import domain.SubSection;
import services.ActorService;
import services.AdministrativeRequestService;
import services.AdministrativeService;
import services.MyMessageService;
import services.SubSectionService;

@Controller
@RequestMapping("/subSection/administrative")
public class SubSectionAdministrativeController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private SubSectionService				subSectionService;
	@Autowired
	private AdministrativeService			administrativeService;
	@Autowired
	private AdministrativeRequestService	administrativeRequestService;
	@Autowired
	private MyMessageService				myMessageService;
	@Autowired
	private ActorService					actorService;


	// Constructor -----------------------------------------------------------
	public SubSectionAdministrativeController() {
		super();
	}

	// Edit ---------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int subSectionId) {
		ModelAndView result;
		SubSection subSection = this.subSectionService.findOne(subSectionId);

		Administrative administrative = this.administrativeService.findByPrincipal();

		if (subSection.getAdministrative() != null)
			Assert.isTrue(administrative.getId() == subSection.getAdministrative().getId());

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

		} else
			try {
				this.subSectionService.save(subSection);
				if (request == true) {
					AdministrativeRequest administrativeRequest = administrativeRequestService.findOne(requestId);
					Actor principal = actorService.findByPrincipal();
					Assert.notNull(principal);
					Assert.isTrue(principal.equals(administrativeRequest.getAdministrative()) && administrativeRequest.getAccepted() == null);
					administrativeRequest.setAccepted(true);
					AdministrativeRequest saved = this.administrativeRequestService.save(administrativeRequest);
					this.myMessageService.administrativeRequestNotification(saved, true);
				}
				result = new ModelAndView("redirect:/offer/display.do?offerId=" + subSection.getOffer().getId());

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(subSection, "subSection.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final SubSection subSection, final BindingResult binding) {
		ModelAndView result;

		Administrative administrative = this.administrativeService.findByPrincipal();

		if (subSection.getAdministrative() != null)
			Assert.isTrue(administrative.getId() == subSection.getAdministrative().getId());

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

		final ModelAndView result = new ModelAndView("subSection/administrative/edit");
		result.addObject("subSection", subSection);
		result.addObject("requestUri", "subSection/administrative/edit.do");
		result.addObject("message", message);
		return result;
	}

}
