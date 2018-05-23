/*
 * ProfileController.java
 *
 * Copyright (C) 2017 Universidad de Sevilla
 *
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.executive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Actor;
import domain.Executive;
import exceptions.HackingException;
import services.ActorService;
import services.DashboardService;

@Controller
@RequestMapping("/dashboard/executive")
public class DashboardExecutiveController extends AbstractController {

	@Autowired
	DashboardService					dashboardService;

	// Services ---------------------------------------------------------------
	@Autowired
	ActorService actorService;

	// Constructors -----------------------------------------------------------

	public DashboardExecutiveController() {
		super();
	}

	@RequestMapping(value = "/display")
	public ModelAndView create() throws HackingException {
		ModelAndView result;
		result = new ModelAndView("dashboard/display");

		Actor actor = this.actorService.findByPrincipal();
		Assert.isTrue(actor instanceof Executive);



		return result;
	}


}
