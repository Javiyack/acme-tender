
package controllers.commercial;

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
import domain.Actor;
import domain.CollaborationRequest;
import domain.Commercial;
import services.ActorService;
import services.CollaborationRequestService;
import services.CommercialService;

@Controller
@RequestMapping("/collaborationRequest/commercial")
public class CollaborationRequestCommercialController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private CollaborationRequestService	collaborationRequestService;
	@Autowired
	private CommercialService			commercialService;
	@Autowired
	private ActorService				actorService;


	// Create ---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int offerId) {
		ModelAndView result;
		CollaborationRequest collaborationRequest;

		collaborationRequest = this.collaborationRequestService.create(offerId);

		result = this.createEditModelAndView(collaborationRequest);

		return result;
	}

	//List sent 

	@RequestMapping(value = "/listSent", method = RequestMethod.GET)
	public ModelAndView listSent() {

		ModelAndView result;
		Collection<CollaborationRequest> collaborationRequests;

		Actor principal;
		principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal instanceof Commercial);

		collaborationRequests = collaborationRequestService.getSentCollaborationRequestsFromCommercialId(principal.getId());

		result = new ModelAndView("collaborationRequest/listSent");
		result.addObject("collaborationRequests", collaborationRequests);
		result.addObject("requestURI", "collaborationRequest/commercial/listSent.do");

		return result;

	}

	//List received 

	@RequestMapping(value = "/listReceived", method = RequestMethod.GET)
	public ModelAndView listReceived() {

		ModelAndView result;
		Collection<CollaborationRequest> collaborationRequests;

		Actor principal;
		principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal instanceof Commercial);

		collaborationRequests = collaborationRequestService.getReceivedCollaborationRequestsFromCommercialId(principal.getId());

		result = new ModelAndView("collaborationRequest/listReceived");
		result.addObject("collaborationRequests", collaborationRequests);
		result.addObject("requestURI", "collaborationRequest/commercial/listReceived.do");

		return result;

	}

	//Save

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final CollaborationRequest collaborationRequest, final BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(collaborationRequest);
		} else

		if (!collaborationRequest.getMaxDeliveryDate().after(collaborationRequest.getMaxAcceptanceDate())) {

			result = this.createEditModelAndView(collaborationRequest, "collaborationRequest.date.error");

		} else {

			try {
				collaborationRequestService.save(collaborationRequest);
				result = new ModelAndView("redirect:/");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(collaborationRequest, "collaborationRequest.commit.error");

			}
		}
		return result;
	}

	// Ancillary methods ----------------------------------------------------
	protected ModelAndView createEditModelAndView(CollaborationRequest collaborationRequest) {
		final ModelAndView result;
		result = this.createEditModelAndView(collaborationRequest, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(CollaborationRequest collaborationRequest, String messageCode) {

		final ModelAndView result = new ModelAndView("collaborationRequest/edit");

		Collection<Commercial> commercials = commercialService.findAll();
		commercials.remove(collaborationRequest.getOffer().getCommercial());

		result.addObject("collaborationRequest", collaborationRequest);
		result.addObject("message", messageCode);
		result.addObject("commercials", commercials);
		return result;
	}
}
