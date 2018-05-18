
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import domain.Comment;

@Controller
@RequestMapping("/comment")
public class CommentController {

	// Services ---------------------------------------------------------------
	@Autowired
	private CommentService	commentService;


	// Constructor -----------------------------------------------------------
	public CommentController() {
		super();
	}

	//List -------------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int tenderId) {

		ModelAndView result;

		final Collection<Comment> comments = this.commentService.findAllByTender(tenderId);

		result = new ModelAndView("comment/list");
		result.addObject("comments", comments);

		return result;
	}

}
