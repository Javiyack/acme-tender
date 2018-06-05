
package usecases;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import security.UserAccountService;
import services.ActorService;
import services.OfferService;
import services.SubSectionService;
import services.TenderService;
import utilities.AbstractTest;
import domain.Offer;
import domain.SubSection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class UseCaseCommercial extends AbstractTest {

	@Autowired
	private ActorService		actorService;
	@Autowired
	private TenderService		tenderService;
	@Autowired
	private UserAccountService	userAccountService;
	@Autowired
	private OfferService		offerService;
	@Autowired
	private SubSectionService	subsectionService;


	/**
	 * Rol: Commercial
	 * 11.a. Crear ofertas asociadas a un concurso.(CU35)
	 */

	@Test
	public void CreateOfferTest() {

		final Object testingData[][] = {
			{// Positive
				"commercial1", "tender4", "IN_DEVELOPMENT", "19001", "05/07/2018 12:00", null
			}, {//Positive
				"commercial2", "tender5", "IN_DEVELOPMENT", "19001", "05/08/2018 12:00", null
			}, {// Negative: wrong roll
				"executive1", "tender4", "IN_DEVELOPMENT", "19001", "05/07/2018 12:00", IllegalArgumentException.class
			}, {// Negative: negative amount
				"commercial1", "tender5", "IN_DEVELOPMENT", "-19001", "05/08/2018 12:00", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateEditTenderCriteria((String) testingData[i][0], // Username login
				(Integer) super.getEntityId((String) testingData[i][1]), // tenderId
				(String) testingData[i][2], // state
				Double.parseDouble((String) testingData[i][3]), // amount
				(String) testingData[i][4], //PresentationDate
				(Class<?>) testingData[i][5]);
	}
	protected void templateEditTenderCriteria(final String principal, final Integer tenderId, final String state, final double amount, final String presentationDate, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			super.authenticate(principal);
			final Offer offer = this.offerService.create(tenderId);

			offer.setAmount(amount);
			offer.setState(state);
			final DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			final Date d1 = format.parse(presentationDate);
			offer.setPresentationDate(d1);

			final Offer offerSaved = this.offerService.save(offer);
			this.offerService.flush();

			Assert.isTrue(this.offerService.findAll().contains(offerSaved));
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	/**
	 * Rol: Commercial
	 * 11.b.1. Editar sus ofertas.(CU36)
	 */

	@Test
	public void EditOfferTest() {

		final Object testingData[][] = {
			{// Positive
				"commercial1", "offer1", "20000", null
			}, {// Negative: wrong roll
				"executive1", "offer1", "20000", IllegalArgumentException.class
			}, {// Negative: not her offer
				"commercial2", "offer1", "20000", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateEditTenderCriteria((String) testingData[i][0], // Username login
				(Integer) super.getEntityId((String) testingData[i][1]), // offerId
				Double.parseDouble((String) testingData[i][2]), // amount
				(Class<?>) testingData[i][3]);
	}
	protected void templateEditTenderCriteria(final String principal, final Integer offerId, final double amount, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			super.authenticate(principal);
			final Offer offer = this.offerService.findOne(offerId);
			offer.setAmount(amount);
			final Offer offerSaved = this.offerService.save(offer);
			this.offerService.flush();
			Assert.isTrue(this.offerService.findAll().contains(offerSaved));
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	/**
	 * Rol: Commercial
	 * 11.c.1. Crear sub-apartados de sus ofertas.(CU38)
	 */

	@Test
	public void CreateSubSectionTest() {

		final Object testingData[][] = {
			{// Positive
				"commercial1", "offer1", "Title test", "ADMINISTRATIVE_ACREDITATION", "3", "Short Description", "Body test", "15/05/2018 00:00", null
			}, {//Positive
				"commercial1", "offer1", "Title test", "ADMINISTRATIVE_ACREDITATION", "3", "Short Description", "Body test", "15/05/2018 00:00", null
			}, {// Negative: wrong roll
				"executer1", "offer1", "Title test", "ADMINISTRATIVE_ACREDITATION", "3", "Short Description", "Body test", "15/05/2018 00:00", IllegalArgumentException.class
			}, {// Negative: not her offer
				"commercial2", "offer1", "Title test", "ADMINISTRATIVE_ACREDITATION", "3", "Short Description", "Body test", "15/05/2018 00:00", IllegalArgumentException.class
			},
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateEditTenderCriteria((String) testingData[i][0], // Username login
				(Integer) super.getEntityId((String) testingData[i][1]), // offerId
				(String) testingData[i][2], // title
				(String) testingData[i][3], // section
				(Integer) Integer.parseInt((String) testingData[i][4]), //subsectionOrder
				(String) testingData[i][5], //shortDescription
				(String) testingData[i][6], //body
				(String) testingData[i][7],	//lastReviewDate
				(Class<?>) testingData[i][8]);
	}
	protected void templateEditTenderCriteria(final String principal, final Integer offerId, final String title, final String section, final Integer subsectionOrder, final String shortDescription, final String body, final String lastReviewDate,
		final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			super.authenticate(principal);
			final SubSection subsection = this.subsectionService.createByCommercialPropietary(offerId);

			subsection.setTitle(title);
			subsection.setSubsectionOrder(subsectionOrder);
			subsection.setShortDescription(shortDescription);
			subsection.setSection(section);
			subsection.setBody(body);
			final DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			final Date d1 = format.parse(lastReviewDate);
			subsection.setLastReviewDate(d1);

			final SubSection subsectionSaved = this.subsectionService.save(subsection);
			this.subsectionService.flush();

			Assert.isTrue(this.subsectionService.findAll().contains(subsectionSaved));
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

}
