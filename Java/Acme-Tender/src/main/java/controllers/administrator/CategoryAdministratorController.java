
package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryService;
import controllers.AbstractController;
import domain.Category;

@Controller
@RequestMapping("/category/administrator")
public class CategoryAdministratorController extends AbstractController {

	
	//Services
	@Autowired
	private CategoryService	categoryService;
	
	public CategoryAdministratorController() {
		super();
	}

	//Create
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		Category category;

		category = this.categoryService.create();
		result = this.createEditModelAndView(category);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int categoryId) {
		ModelAndView result;
		Category category;

		category = this.categoryService.findOne(categoryId);

		result = this.createEditModelAndView(category);

		return result;
	}

	//Save
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Category category, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(category);
		else
			try {
				this.categoryService.save(category);
				result = new ModelAndView("redirect:/category/administrator/list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(category, "category.commit.error");
			}

		return result;
	}

	//Delete
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Category category, final BindingResult binding) {
		ModelAndView result;

		try {
			this.categoryService.delete(category);
			result = new ModelAndView("redirect:/category/administrator/list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(category, "category.delete.error");
		}

		return result;
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) final Integer parentCategoryId) {
		ModelAndView result;
		Collection<Category> categories;
		Category parent = null;
		final boolean admin = true;

		if (parentCategoryId == null) {
			categories = this.categoryService.getFirstLevelCategories();
			parent = null;
		} else {
			categories = this.categoryService.getChildCategories(parentCategoryId);
			parent= this.categoryService.findOne(parentCategoryId);
		}

		result = new ModelAndView("category/administrator/list");
		
		result.addObject("requestUri", "category/administrator/list.do");
		result.addObject("categories", categories);
		result.addObject("parent", parent);
		result.addObject("admin", admin);
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Category category) {
		ModelAndView result;

		result = this.createEditModelAndView(category, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Category category, final String message) {
		ModelAndView result;
		Collection<Category> categories;

		result = new ModelAndView("category/administrator/edit");
		categories = this.categoryService.findAll();
		categories.remove(category);
		
		Boolean haveChilds = !this.categoryService.getChildCategories(category.getId()).isEmpty();
		result.addObject("haveChilds", haveChilds);
		
		result.addObject("category", category);
		result.addObject("message", message);
		result.addObject("categories", categories);

		return result;
	}

}
