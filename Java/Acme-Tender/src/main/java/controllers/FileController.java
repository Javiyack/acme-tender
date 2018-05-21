package controllers;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.File;
import services.FileService;

@Controller
@RequestMapping("/file")
public class FileController extends AbstractController{
	
	// Services ---------------------------------------------------------------
	@Autowired
	private FileService fileService;
	

	
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

	// Display 
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int fileId) {
		final ModelAndView result;

		File file = this.fileService.findOne(fileId);

		result = new ModelAndView("file/display");
		result.addObject("file", file);

		return result;
	}	
	

}
