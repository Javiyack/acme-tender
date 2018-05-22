package controllers.administrative;

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
import domain.File;
import services.FileService;

@Controller
@RequestMapping("/file/administrative")
public class FileAdministrativeController extends AbstractController{
	
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
		
		case "subSection":
			file = this.fileService.createForSubSection(id);
			break;
			
		default:
			Assert.isTrue(false);
		}		
		
		result = this.createEditModelAndView(file);
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
