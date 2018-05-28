
package controllers;

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

import domain.Actor;
import domain.Administrative;
import domain.AdministrativeRequest;
import domain.Commercial;
import domain.SubSection;
import services.ActorService;
import services.AdministrativeRequestService;
import services.AdministrativeService;
import services.ComboService;
import services.MyMessageService;
import services.SubSectionService;

@Controller
@RequestMapping("/administrativeRequest")
public class AdministrativeRequestController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private AdministrativeRequestService	administrativeRequestService;
	@Autowired
	private AdministrativeService			administrativeService;
	@Autowired
	private MyMessageService				myMessageService;
	@Autowired
	private ActorService					actorService;
	@Autowired
	private SubSectionService				subSectionService;
	@Autowired
	private ComboService					comboService;


	// Create ---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int offerId) {
		ModelAndView result;
		AdministrativeRequest administrativeRequest;

		administrativeRequest = this.administrativeRequestService.create(offerId);

		result = this.createEditModelAndView(administrativeRequest);
		result.addObject("reject", false);

		return result;
	}

	//Display
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int administrativeRequestId) {

		ModelAndView result;

		AdministrativeRequest administrativeRequest = administrativeRequestService.findOne(administrativeRequestId);
		Actor principal = this.actorService.findByPrincipal();

		result = new ModelAndView("administrativeRequest/display");
		result.addObject("administrativeRequest", administrativeRequest);
		result.addObject("principal", principal);
		return result;

	}

	//Save
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final AdministrativeRequest administrativeRequest, final BindingResult binding, @ModelAttribute("reject") Boolean reject) {

		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(administrativeRequest);
		} else

		if (!administrativeRequest.getMaxDeliveryDate().after(administrativeRequest.getMaxAcceptanceDate())) {

			result = this.createEditModelAndView(administrativeRequest, "administrativeRequest.date.error");

		} else {

			if (administrativeRequest.getId() != 0 && administrativeRequest.getRejectedReason().isEmpty()) {

				result = this.createEditModelAndView(administrativeRequest, "administrativeRequest.rejection.error");

			} else {

				try {
					boolean sendNotification = false;
					if (reject == true) {
						sendNotification = true;
						administrativeRequest.setAccepted(false);
					}
					AdministrativeRequest saved = administrativeRequestService.save(administrativeRequest);
					if (sendNotification == true) {
						this.myMessageService.administrativeRequestNotification(saved, false);
					}
					if (administrativeRequest.getId() == 0) {
						result = new ModelAndView("redirect:/offer/display.do?offerId=" + administrativeRequest.getOffer().getId());
					} else {
						result = new ModelAndView("redirect:display.do?administrativeRequestId=" + administrativeRequest.getId());
					}
				} catch (final Throwable oops) {
					result = this.createEditModelAndView(administrativeRequest, "administrativeRequest.commit.error");

				}

			}
		}
		return result;
	}

	//List sent 

	@RequestMapping(value = "/listSent", method = RequestMethod.GET)
	public ModelAndView listSent() {

		ModelAndView result;
		Collection<AdministrativeRequest> administrativeRequests;

		Actor principal;
		principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal instanceof Commercial);

		administrativeRequests = administrativeRequestService.getSentAdministrativeRequestsFromCommercialId(principal.getId());

		result = new ModelAndView("administrativeRequest/listSent");
		result.addObject("administrativeRequests", administrativeRequests);
		result.addObject("requestURI", "administrativeRequest/listSent.do");

		return result;

	}

	//List received 

	@RequestMapping(value = "/listReceived", method = RequestMethod.GET)
	public ModelAndView listReceived() {

		ModelAndView result;
		Collection<AdministrativeRequest> administrativeRequests;

		Actor principal;
		principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal instanceof Administrative);

		administrativeRequests = administrativeRequestService.getReceivedAdministrativeRequestsFromAdministrativeId(principal.getId());

		result = new ModelAndView("administrativeRequest/listReceived");
		result.addObject("administrativeRequests", administrativeRequests);
		result.addObject("requestURI", "administrativeRequest/listReceived.do");

		return result;

	}

	//Reject
	@RequestMapping(value = "/reject", method = RequestMethod.GET)
	public ModelAndView reject(@RequestParam int requestId) {

		ModelAndView result;

		AdministrativeRequest administrativeRequest;
		administrativeRequest = this.administrativeRequestService.findOneToEdit(requestId);

		result = this.createEditModelAndView(administrativeRequest);
		result.addObject("reject", true);

		return result;

	}

	//Accept
	@RequestMapping(value = "/accept", method = RequestMethod.GET)
	public ModelAndView accept(@RequestParam int requestId) {

		ModelAndView result;

		AdministrativeRequest administrativeRequest;
		administrativeRequest = this.administrativeRequestService.findOneToEdit(requestId);

		SubSection subSection;
		subSection = this.subSectionService.createByAdministrativeCollaborationAcceptation(administrativeRequest);

		result = new ModelAndView("subSection/commercial/create");
		result.addObject("subSection", subSection);
		result.addObject("requestURI", "subSection/administrative/edit.do");
		result.addObject("request", true);
		result.addObject("requestId", requestId);

		Collection<String> subSectionSectionsCombo = this.comboService.subSectionSections();
		result.addObject("subSectionSectionsCombo", subSectionSectionsCombo);

		return result;

	}

	// Ancillary methods ----------------------------------------------------
	protected ModelAndView createEditModelAndView(AdministrativeRequest administrativeRequest) {
		final ModelAndView result;
		result = this.createEditModelAndView(administrativeRequest, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(AdministrativeRequest administrativeRequest, String messageCode) {

		final ModelAndView result = new ModelAndView("administrativeRequest/edit");

		Collection<Administrative> administratives = administrativeService.simpleFindAll();

		result.addObject("administrativeRequest", administrativeRequest);
		result.addObject("message", messageCode);
		result.addObject("administratives", administratives);

		return result;
	}

}
