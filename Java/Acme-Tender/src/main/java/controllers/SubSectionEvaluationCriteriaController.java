
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Actor;
import domain.SubSectionEvaluationCriteria;
import services.ActorService;
import services.SubSectionEvaluationCriteriaService;

@Controller
@RequestMapping("/subSectionEvaluationCriteria")
public class SubSectionEvaluationCriteriaController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private SubSectionEvaluationCriteriaService	subSectionEvaluationCriteriaService;
	@Autowired
	private ActorService			actorService;


	// Constructor -----------------------------------------------------------
	public SubSectionEvaluationCriteriaController() {
		super();
	}

	//Display
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int subSectionEvaluationCriteriaId) {

		ModelAndView result;

		Actor actor = this.actorService.findByPrincipal();
		SubSectionEvaluationCriteria subSectionEvaluationCriteria = subSectionEvaluationCriteriaService.findOne(subSectionEvaluationCriteriaId);

		result = new ModelAndView("subSectionEvaluationCriteria/display");
		result.addObject("subSectionEvaluationCriteria", subSectionEvaluationCriteria);
		result.addObject("actorId", actor.getId());
		return result;

	}
}
