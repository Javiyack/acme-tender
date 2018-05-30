package controllers;

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
import domain.Constant;
import domain.File;
import services.ActorService;
import services.FileService;

@Controller
@RequestMapping("/file")
public class FileController extends AbstractController{
	
	// Services ---------------------------------------------------------------
	@Autowired
	private FileService fileService;
	@Autowired
	private ActorService actorService;
	

	// Display 
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int fileId) {
		final ModelAndView result;

		File file = this.fileService.findOne(fileId);
		Actor actor = this.actorService.findByPrincipal();

		result = new ModelAndView("file/display");
		result.addObject("file", file);
		result.addObject("actor", actor);
		return result;
	}	
	
	//Create
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int id, @RequestParam final String type) {
		ModelAndView result;
		File file = null;
		switch (type) {
		case Constant.FILE_CURRICULUM:
			file = this.fileService.createForCurriculum(id);
			break;
			
		case Constant.FILE_SUBSECTION:
			file = this.fileService.createForSubSection(id);
			break;
			
		case Constant.FILE_TENDER:
			file = this.fileService.createForTender(id);
			break;

		case Constant.FILE_TENDER_RESULT:
			file = this.fileService.createForTenderResult(id);
			break;
			
		default:
			Assert.isTrue(false);
		}		
		
		result = this.createEditModelAndView(file);
		return result;
	}
	
	//Edit
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int fileId) {
		ModelAndView result;

		File file = this.fileService.findOne(fileId);
			
		result = this.createEditModelAndView(file);
		return result;
	}
		
	
	//Save
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid File file, BindingResult binding) {
		
		ModelAndView result = null;

		if (binding.hasErrors()) {
			result = this.createEditModelAndView(file);

		} else {
			try {
				fileService.save(file);
				
				if (file.getTender() != null) {
					result = new ModelAndView("redirect:/tender/display.do?tenderId=" + file.getTender().getId());
				}
				if (file.getTenderResult() != null) {
					result = new ModelAndView("redirect:/tenderResult/display.do?tenderResultId=" + file.getTenderResult().getId());
				}	
				if (file.getSubSection() != null) {
					result = new ModelAndView("redirect:/subSection/display.do?subSectionId=" + file.getSubSection().getId());
				}		
				if (file.getCurriculum() != null) {
					result = new ModelAndView("redirect:/curriculum/display.do?curriculumId=" + file.getCurriculum().getId());
				}					
				

			} catch (Throwable oops) {
				result = createEditModelAndView(file, "file.commit.error");
			}
		}

		return result;
	}
	
	
	//Save
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(File file, BindingResult binding) {
		
		ModelAndView result = null;

		try {
			fileService.delete(file);
			
			if (file.getTender() != null) {
				result = new ModelAndView("redirect:/tender/display.do?tenderId=" + file.getTender().getId());
			}
			if (file.getTenderResult() != null) {
				result = new ModelAndView("redirect:/tenderResult/display.do?tenderResultId=" + file.getTenderResult().getId());
			}	
			if (file.getSubSection() != null) {
				result = new ModelAndView("redirect:/subSection/display.do?subSectionId=" + file.getSubSection().getId());
			}		
			if (file.getCurriculum() != null) {
				result = new ModelAndView("redirect:/curriculum/display.do?curriculumId=" + file.getCurriculum().getId());
			}					
			

		} catch (Throwable oops) {
			result = createEditModelAndView(file, "file.commit.error");
		}

		return result;
	}	
	
	
	//Ancillary Methods
	
	protected ModelAndView createEditModelAndView(final File file) {
		ModelAndView result;

		result = this.createEditModelAndView(file, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final File file, final String messageCode) {
		ModelAndView result;
		
		if (file.getId() != 0)
			result = new ModelAndView("file/edit");
		else
			result = new ModelAndView("file/create");
		
		result.addObject("file", file);
		result.addObject("message", messageCode);
		
		if (file.getTender() != null) {
			result.addObject("parentId", file.getTender().getId());
			result.addObject("parentType", Constant.FILE_TENDER);
		}
		if (file.getTenderResult() != null) {
			result.addObject("parentId", file.getTenderResult().getId());
			result.addObject("parentType", Constant.FILE_TENDER_RESULT);
		}	
		if (file.getSubSection() != null) {
			result.addObject("parentId", file.getSubSection().getId());
			result.addObject("parentType", Constant.FILE_SUBSECTION);
		}		
		if (file.getCurriculum() != null) {
			result.addObject("parentId", file.getCurriculum().getId());
			result.addObject("parentType", Constant.FILE_CURRICULUM);
		}		
		
		
		return result;
	}	
	
	
	

}
