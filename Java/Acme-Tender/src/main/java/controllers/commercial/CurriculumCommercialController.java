
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
import domain.Commercial;
import domain.Curriculum;
import domain.SubSection;
import services.ActorService;
import services.CurriculumService;
import services.SubSectionService;

@Controller
@RequestMapping("/curriculum/commercial")
public class CurriculumCommercialController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private CurriculumService	curriculumService;
	@Autowired
	private SubSectionService	subSectionService;
	@Autowired
	private ActorService		actorService;


	//Display
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int curriculumId) {

		ModelAndView result;

		Curriculum curriculum;
		curriculum = curriculumService.findOne(curriculumId);
		curriculumService.checkListAndDisplay(curriculum.getSubSection().getId());

		result = new ModelAndView("curriculum/display");
		result.addObject("curriculum", curriculum);

		return result;

	}

	//Create
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int subSectionId) {

		ModelAndView result;

		Actor principal = actorService.findByPrincipal();
		Assert.isTrue(principal instanceof Commercial);

		SubSection subSection;
		subSection = subSectionService.findOne(subSectionId);
		Assert.isTrue(subSection.getCommercial() == principal);

		Curriculum curriculum;
		curriculum = curriculumService.create();
		curriculum.setSubSection(subSection);

		result = this.createEditModelAndView(curriculum);

		return result;
	}

	//List curriculums from subSection

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int subSectionId) {

		ModelAndView result;
		Collection<Curriculum> curriculums;

		curriculums = curriculumService.getCurriculumsFromSubSectionId(subSectionId);

		SubSection subSection;
		subSection = subSectionService.findOne(subSectionId);

		result = new ModelAndView("curriculum/list");
		result.addObject("curriculums", curriculums);
		result.addObject("subSection", subSection);

		return result;

	}

	//Save

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Curriculum curriculum, final BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(curriculum);
		} else
			try {
				Curriculum saved = curriculumService.save(curriculum);
				result = new ModelAndView("redirect:list.do?subSectionId=" + saved.getSubSection().getId());
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(curriculum, "curriculum.commit.error");
			}
		return result;
	}

	//Ancillary Methods

	protected ModelAndView createEditModelAndView(final Curriculum curriculum) {
		ModelAndView result;

		result = this.createEditModelAndView(curriculum, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Curriculum curriculum, final String messageCode) {
		ModelAndView result;
		result = new ModelAndView("curriculum/edit");
		result.addObject("curriculum", curriculum);
		result.addObject("message", messageCode);

		return result;
	}

}
