
package controllers.commercial;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.ColaborationRequest;
import domain.Commercial;
import services.ColaborationRequestService;
import services.CommercialService;

@Controller
@RequestMapping("/colaborationRequest/commercial")
public class ColaborationRequestCommercialController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private ColaborationRequestService	colaborationRequestService;
	@Autowired
	private CommercialService			commercialService;


	// Create ---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int offerId) {
		ModelAndView result;
		ColaborationRequest colaborationRequest;

		colaborationRequest = this.colaborationRequestService.create(offerId);

		result = this.createEditModelAndView(colaborationRequest);

		return result;
	}

	//Save

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ColaborationRequest colaborationRequest, final BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(colaborationRequest);
		} else

		if (!colaborationRequest.getMaxDeliveryDate().after(colaborationRequest.getMaxAcceptanceDate())) {

			result = this.createEditModelAndView(colaborationRequest, "colaborationRequest.date.error");

		} else {

			try {
				colaborationRequestService.save(colaborationRequest);
				result = new ModelAndView("redirect:/");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(colaborationRequest, "colaborationRequest.commit.error");

			}
		}
		return result;
	}

	// Ancillary methods ----------------------------------------------------
	protected ModelAndView createEditModelAndView(ColaborationRequest colaborationRequest) {
		final ModelAndView result;
		result = this.createEditModelAndView(colaborationRequest, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(ColaborationRequest colaborationRequest, String messageCode) {

		final ModelAndView result = new ModelAndView("colaborationRequest/edit");

		Collection<Commercial> commercials = commercialService.findAll();
		commercials.remove(colaborationRequest.getOffer().getCommercial());

		result.addObject("colaborationRequest", colaborationRequest);
		result.addObject("message", messageCode);
		result.addObject("commercials", commercials);
		return result;
	}
}
