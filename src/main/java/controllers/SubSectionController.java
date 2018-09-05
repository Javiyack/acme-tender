package controllers;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.*;

import java.util.Collection;

@Controller
@RequestMapping("/subSection")
public class SubSectionController extends AbstractController {

    // Services ---------------------------------------------------------------
    @Autowired
    private SubSectionService subSectionService;
    @Autowired
    private ActorService actorService;
    @Autowired
    private CurriculumService curriculumService;
    @Autowired
    private SubSectionEvaluationCriteriaService subSectionEvaluationCriteriaService;
    @Autowired
    private EvaluationCriteriaService evaluationCriteriaService;
    @Autowired
    private FileService fileService;


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

        Assert.isTrue(this.subSectionService.canViewSubSection(subSectionId));

        Collection<Curriculum> curriculums = this.curriculumService.findAllBySubsection(subSectionId);
        Collection<File> files = this.fileService.findAllBySubSection(subSectionId);
        Collection<SubSectionEvaluationCriteria> subSectionEvaluationCriterias = this.subSectionEvaluationCriteriaService.findAllBySubSection(subSectionId);

        //Para ver si podemos añadir SubSectionEvaluationCriterias
        Collection<EvaluationCriteria> evaluationCriterias = this.evaluationCriteriaService.findAllByTender(subSection.getOffer().getTender().getId());

        result = new ModelAndView("subSection/display");

        result.addObject("subSection", subSection);
        result.addObject("curriculums", curriculums);
        result.addObject("files", files);
        result.addObject("actorId", actor.getId());

        result.addObject("tenderHasEvaluationCriterias", evaluationCriterias.size() != 0);
        result.addObject("subSectionEvaluationCriterias", subSectionEvaluationCriterias);

        return result;

    }

}
