package controllers.commercial;

import java.util.ArrayList;
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
import domain.File;
import services.FileService;

@Controller
@RequestMapping("/file/commercial")
public class FileCommercialController extends AbstractController{
	
	// Services ---------------------------------------------------------------
	@Autowired
	private FileService fileService;
	

	//Create
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int id, @RequestParam final String type) {
		ModelAndView result;
		File file = null;
		switch (type) {
		case "tender":
			file = this.fileService.createForTender(id);
			break;
			
		case "tenderResult":
			file = this.fileService.createForTenderResult(id);
			break;
			
		case "curriculum":
			file = this.fileService.createForCurriculum(id);
			break;
			
		case "subSection":
			file = this.fileService.createForSubSection(id);
			break;
		}		
		
		result = this.createEditModelAndView(file);
		return result;
	}
	
	// List 
	@RequestMapping(value = "/list")
	public ModelAndView list(@RequestParam final int id, @RequestParam final String type) {
		ModelAndView result;

		Collection<File> files = new ArrayList<File>();
		switch (type) {
		case "tender":
			files = this.fileService.findAllByTender(id);
			break;
			
		case "tenderResult":
			files = this.fileService.findAllByTenderResult(id);
			break;
			
		case "curriculum":
			files = this.fileService.findAllByCurriculum(id);
			break;
			
		case "subSection":
			files = this.fileService.findAllBySubSection(id);
			break;
		}

		result = new ModelAndView("file/list");
		result.addObject("files", files);
		result.addObject("parentId", id);
		result.addObject("parentType", type);
		
		result.addObject("requestUri", "file/list.do");

		return result;
	}

	//Save
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid File file, BindingResult binding) {
		
		ModelAndView result;

		if (binding.hasErrors()) {
			result = this.createEditModelAndView(file);

		} else {
			try {
				fileService.save(file);
				result = new ModelAndView("redirect:/");

			} catch (Throwable oops) {
				result = createEditModelAndView(file, "file.commit.error");
			}
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
		result = new ModelAndView("file/edit");
		result.addObject("file", file);
		result.addObject("message", messageCode);
		result.addObject("requestUri", "file/edit.do");

		return result;
	}
	

}
