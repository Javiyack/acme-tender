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

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.DashboardService;
import services.ExecutiveService;
import controllers.AbstractController;
import domain.Actor;
import domain.Executive;
import exceptions.HackingException;

@Controller
@RequestMapping("/dashboard/executive")
public class DashboardExecutiveController extends AbstractController {

	@Autowired
	DashboardService	dashboardService;

	// Services ---------------------------------------------------------------
	@Autowired
	ActorService		actorService;
	@Autowired
	ExecutiveService	executiveService;


	// Constructors -----------------------------------------------------------

	public DashboardExecutiveController() {
		super();
	}

	@RequestMapping(value = "/display")
	public ModelAndView create() throws HackingException {
		ModelAndView result;
		result = new ModelAndView("dashboard/executive/display");

		final Actor actor = this.actorService.findByPrincipal();
		Assert.isTrue(actor instanceof Executive);

		final Collection<Object> consultaC1 = this.executiveService.numberTenderByAdministrative();
		result.addObject("c1Datos", consultaC1);

		final Collection<Object> consultaC2 = this.executiveService.tendersByInterestLevel();
		result.addObject("c2Datos", consultaC2);

		final Collection<Object> consultaC3 = this.executiveService.offersByState();
		result.addObject("c3Datos", consultaC3);

		final Collection<Object> consultaC4 = this.executiveService.offersByStateAndCommercial();
		result.addObject("c4Datos", consultaC4);

		final Collection<Object> consultaC5 = this.executiveService.offersByStateRatio();
		result.addObject("c5Datos", consultaC5);

		final Collection<Object> consultaC6 = this.executiveService.offersByStateAndCommercialRatio();
		result.addObject("c6Datos", consultaC6);

		return result;
	}
}
