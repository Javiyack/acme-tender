
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.TabooWordService;
import services.TenderService;
import controllers.AbstractController;
import domain.Tender;

@Controller
@RequestMapping("/tender/administrator")
public class TenderAdminController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private TabooWordService	tabooWordService;
	@Autowired
	private TenderService		tenderService;


	// Constructor -----------------------------------------------------------
	public TenderAdminController() {
		super();
	}

	// List Tenders ---------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listTender() {

		final ModelAndView result;

		final Collection<Tender> tenders = this.tenderService.findAllTenderWithTabooWords();

		result = new ModelAndView("tender/administrator/list");
		result.addObject("tenders", tenders);

		return result;
	}

	// Delete ---------------------------------------------------------------
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam(required = false) final Integer tenderId) {
		ModelAndView result;

		Assert.notNull(tenderId);
		final Tender tender = this.tenderService.findOne(tenderId);

		try {
			this.tenderService.deleteByAdmin(tender);
			result = new ModelAndView("redirect: /list.do");

		} catch (final Throwable ooops) {
			result = new ModelAndView("redirect: /list.do");
			result.addObject("message", "tenderResult.commit.error");
		}
		return result;
	}

}
