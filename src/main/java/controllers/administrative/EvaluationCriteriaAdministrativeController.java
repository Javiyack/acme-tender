
package controllers.administrative;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.EvaluationCriteriaService;
import services.EvaluationCriteriaTypeService;
import domain.EvaluationCriteria;
import domain.EvaluationCriteriaType;

@Controller
@RequestMapping("/evaluationCriteria/administrative")
public class EvaluationCriteriaAdministrativeController {

	// Services ---------------------------------------------------------------
	@Autowired
	private EvaluationCriteriaService	evaluationCriteriaService;
	@Autowired
	private EvaluationCriteriaTypeService	evaluationCriteriaTypeService;


	// Constructor -----------------------------------------------------------
	public EvaluationCriteriaAdministrativeController() {
		super();
	}

	// Create ---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int tenderId) {
		
		final ModelAndView result = new ModelAndView("evaluationCriteria/administrative/create");
		
		final EvaluationCriteria evaluationCriteria = this.evaluationCriteriaService.create(tenderId);
		result.addObject("evaluationCriteria", evaluationCriteria);
		
		Collection<EvaluationCriteriaType> evaluationCriteriaTypes = this.evaluationCriteriaTypeService.findAll();
		result.addObject("evaluationCriteriaTypes", evaluationCriteriaTypes);
		
		result.addObject("tenderId", tenderId);
		
		return result;
	}

	// Edit ---------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int evaluationCriteriaId) {
		ModelAndView result;
		final EvaluationCriteria evaluationCriteria = 
				this.evaluationCriteriaService.findOne(evaluationCriteriaId);

		result = this.createEditModelAndView(evaluationCriteria);
		return result;
	}

	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("evaluationCriteria") @Valid final EvaluationCriteria evaluationCriteria, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(evaluationCriteria);
		else
			try {
				this.evaluationCriteriaService.save(evaluationCriteria);
				result = new ModelAndView("redirect:/evaluationCriteria/administrative/list.do?tenderId=" + evaluationCriteria.getTender().getId());

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(evaluationCriteria, "evaluationCriteria.commit.error");
			}
		return result;
	}
	
	// Delete ---------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final EvaluationCriteria evaluationCriteria, final BindingResult binding) {
		ModelAndView result;

		try {
			this.evaluationCriteriaService.delete(evaluationCriteria);
			result = new ModelAndView("redirect:/evaluationCriteria/administrative/list.do?tenderId=" + evaluationCriteria.getTender().getId());
			
		} catch (final Throwable ooops) {
			if (ooops.getMessage().equals("evaluationCriteria.cannot.delete.in.use")) 
				result = this.createEditModelAndView(evaluationCriteria, "evaluationCriteria.cannot.delete.in.use");
			else
				result = this.createEditModelAndView(evaluationCriteria, "evaluationCriteria.commit.error");
		}
		return result;
	}		
	
	// List ---------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int tenderId) {

		ModelAndView result;

		final Collection<EvaluationCriteria> evaluationCriterias = this.evaluationCriteriaService.findAllByTender(tenderId);

		result = new ModelAndView("evaluationCriteria/administrative/list");
		result.addObject("evaluationCriterias", evaluationCriterias);
		result.addObject("tenderId", tenderId);

		return result;
	}		

	// Auxiliary methods ----------------------------------------------------
	protected ModelAndView createEditModelAndView(final EvaluationCriteria evaluationCriteria) {
		final ModelAndView result;
		result = this.createEditModelAndView(evaluationCriteria, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final EvaluationCriteria evaluationCriteria, final String message) {

		final ModelAndView result = new ModelAndView("evaluationCriteria/administrative/edit");
		result.addObject("evaluationCriteria", evaluationCriteria);
		
		Collection<EvaluationCriteriaType> evaluationCriteriaTypes = this.evaluationCriteriaTypeService.findAll();
		result.addObject("evaluationCriteriaTypes", evaluationCriteriaTypes);
		
		result.addObject("tenderId", evaluationCriteria.getTender().getId());
		
		result.addObject("message", message);
		return result;
	}
}
