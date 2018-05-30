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
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Actor;
import domain.Executive;
import domain.Offer;
import exceptions.HackingException;
import services.ActorService;
import services.DashboardService;
import services.ExecutiveService;

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
		/*
		====================================
		B
		====================================
		i.	Top 10 por importe de oferta económica de ofertas presentadas el último mes.
			(meter año*100 + mes anterior (2 digitos) al actual mediante java (integer):
			select o from Offer o where year(o.presentationDate)*100+month(o.presentationDate) = 201802 order by o.amount desc

		Para tomar solo las 10 primeras:
						
			En el repositorio (tiene que devolver List<>):
				
				@Query("from User u where ...")
				List<User> findAllUsersWhereFoo(Pageable pageable);
		
		  	En el servicio:
				
				Collection<User> findTop10UsersWhereFoo() {
					return findAllUsersWhereFoo(new PageRequest(0,10));
			}
*/
		Date now = new Date();
		final Collection<Offer> consultaB1 = this.executiveService.findTopOffersOnMonth(now, 10);
		
		result.addObject("consultaB1", consultaB1);
		
		
/*
		ii.	Top 10 por importe de oferta económica de ofertas ganadas los últimos tres meses.
			(meter año*100 + mes(2 digitos) de hace 3 meses mediante java (integer))
		select o from Offer o where year(o.presentationDate)*100+month(o.presentationDate) >= 201801 and o.status = 'WINNED' order by o.amount desc
*/
		final List<Offer> consultaB2 = this.executiveService.findTopWinedOffersOnThreeMonthsFrom(now, 10);
		
		result.addObject("consultaB2", consultaB2);
		
		
/* 		iii. Ratio medio de la oferta económica sobre el importe estimado de licitación, agrupando por empresa en resultado de concurso.

		select cr.name, avg(cr.economicalOffer / t.estimatedAmount) from CompanyResult cr join cr.tenderResult tr join tr.tender t where t.estimatedAmount != 0 group by cr.name

*/
		final Collection<Object> consultaB3 = this.executiveService.findAvgRatioAmounOfferOverTender();
		
		result.addObject("consultaB3", consultaB3);
		
		
/*		iv.	Top 10 de empresas existentes en resultados de concurso.

		select cr.name, count(cr.name) from CompanyResult cr group by cr.name order by count(cr.name) desc

*/
		final Collection<Object> consultaB4 = this.executiveService.findTopFrecuentsCompanies(10);
		
		result.addObject("consultaB4", consultaB4);
		
		
/*
		v. Top 10 de empresas ganadoras en resultados de concurso.

		select cr.name, count(cr.name) from CompanyResult cr where cr.state = 'WINNER' group by cr.name order by count(cr.name) desc
*/
		final Collection<Object> consultaB5 = this.executiveService.findTopFrecuentsWinnersCompanies(10);
		
		result.addObject("consultaB5", consultaB5);
		


		return result;
	}
}
