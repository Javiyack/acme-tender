
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Actor;
import domain.Curriculum;
import domain.File;
import domain.SubSection;
import domain.SubSectionEvaluationCriteria;
import services.ActorService;
import services.CurriculumService;
import services.FileService;
import services.SubSectionEvaluationCriteriaService;
import services.SubSectionService;

@Controller
@RequestMapping("/subSection")
public class SubSectionController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private SubSectionService					subSectionService;
	@Autowired
	private ActorService						actorService;
	@Autowired
	private CurriculumService					curriculumService;
	@Autowired
	private SubSectionEvaluationCriteriaService	subSectionEvaluationCriteriaService;
	@Autowired
	private FileService							fileService;


	// Constructor -----------------------------------------------------------
	public SubSectionController() {
		super();
	}

	//Display
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int subSectionId) {

		ModelAndView result;

		SubSection subSection = subSectionService.findOne(subSectionId);

		Actor actor = this.actorService.findByPrincipal();
		Collection<Curriculum> curriculums = this.curriculumService.findAllBySubsection(subSectionId);
		Collection<File> files = this.fileService.findAllBySubSection(subSectionId);
		Collection<SubSectionEvaluationCriteria> subSectionEvaluationCriterias = this.subSectionEvaluationCriteriaService.findAllBySubSection(subSectionId);

		result = new ModelAndView("subSection/display");

		result.addObject("subSection", subSection);
		result.addObject("curriculums", curriculums);
		result.addObject("files", files);
		result.addObject("actorId", actor.getId());
		result.addObject("subSectionEvaluationCriterias", subSectionEvaluationCriterias);

		return result;

	}

}
