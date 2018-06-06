
package usecases;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.CurriculumService;
import services.OfferService;
import services.SubSectionService;
import utilities.AbstractTest;
import domain.Curriculum;
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
	private CurriculumService	curriculumService;
	@Autowired
	private OfferService		offerService;
	@Autowired
	private SubSectionService	subsectionService;


	/**
	 * Rol: Commercial
	 * 11.a. Crear ofertas asociadas a un concurso.(CU34)
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
			this.templateCreateOffer((String) testingData[i][0], // Username login
				(Integer) super.getEntityId((String) testingData[i][1]), // tenderId
				(String) testingData[i][2], // state
				Double.parseDouble((String) testingData[i][3]), // amount
				(String) testingData[i][4], //PresentationDate
				(Class<?>) testingData[i][5]);
	}
	protected void templateCreateOffer(final String principal, final Integer tenderId, final String state, final double amount, final String presentationDate, final Class<?> expected) {
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
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	/**
	 * Rol: Commercial
	 * 11.b.1. Editar sus ofertas.(CU35)
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
			this.templateEditOffer((String) testingData[i][0], // Username login
				(Integer) super.getEntityId((String) testingData[i][1]), // offerId
				Double.parseDouble((String) testingData[i][2]), // amount
				(Class<?>) testingData[i][3]);
	}
	protected void templateEditOffer(final String principal, final Integer offerId, final double amount, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			super.authenticate(principal);
			final Offer offer = this.offerService.findOne(offerId);
			offer.setAmount(amount);
			final Offer offerSaved = this.offerService.save(offer);
			this.offerService.flush();
			Assert.isTrue(this.offerService.findAll().contains(offerSaved));
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	/**
	 * Rol: Commercial
	 * 11.c.1. Crear sub-apartados de sus ofertas.(CU37)
	 */

	@Test
	public void CreateSubSectionTest() {

		final Object testingData[][] = {
			{// Positive
				"commercial1", "offer1", "Title test", "ADMINISTRATIVE_ACREDITATION", "3", "Short Description", "Body test", "15/05/2018 00:00", null
			}, {// Negative: wrong roll
				"executer1", "offer1", "Title test", "ADMINISTRATIVE_ACREDITATION", "3", "Short Description", "Body test", "15/05/2018 00:00", IllegalArgumentException.class
			}, {// Negative: not her offer
				"commercial2", "offer1", "Title test", "ADMINISTRATIVE_ACREDITATION", "3", "Short Description", "Body test", "15/05/2018 00:00", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateSubSection((String) testingData[i][0], // Username login
				(Integer) super.getEntityId((String) testingData[i][1]), // offerId
				(String) testingData[i][2], // title
				(String) testingData[i][3], // section
				(Integer) Integer.parseInt((String) testingData[i][4]), //subsectionOrder
				(String) testingData[i][5], //shortDescription
				(String) testingData[i][6], //body
				(String) testingData[i][7],	//lastReviewDate
				(Class<?>) testingData[i][8]);
	}
	protected void templateSubSection(final String principal, final Integer offerId, final String title, final String section, final Integer subsectionOrder, final String shortDescription, final String body, final String lastReviewDate,
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
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	/**
	 * Rol: Commercial
	 * 11.c.2. Editar sub-apartados de sus ofertas.(CU38)
	 */

	@Test(expected = Exception.class)
	public void EditSubSectionTest() {

		final Object testingData[][] = {
			{// Positive
				"commercial1", "subsection1", null
			}, {// Negative: wrong subsectionOrder
				"commercial1", "subsection1", "Title test edit", "-2", ConstraintViolationException.class
			}, {// Negative: not her subsection
				"commercial2", "subsection1", "Title test edit", "2", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateEditSubSection((String) testingData[i][0], // Username login
				(Integer) super.getEntityId((String) testingData[i][1]), // subsectionId
				(String) testingData[i][2], // title
				(Integer) Integer.parseInt((String) testingData[i][3]), //subsectionOrder
				(Class<?>) testingData[i][4]);
	}
	protected void templateEditSubSection(final String principal, final Integer subsectionId, final String title, final Integer subsectionOrder, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			super.authenticate(principal);
			final SubSection subsection = this.subsectionService.findOne(subsectionId);

			subsection.setTitle(title);
			subsection.setSubsectionOrder(subsectionOrder);
			final SubSection subsectionSaved = this.subsectionService.save(subsection);
			this.subsectionService.flush();

			Assert.isTrue(this.subsectionService.findAll().contains(subsectionSaved));
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	/**
	 * Rol: Commercial
	 * 11.c.3. Eliminar sub-apartados de sus ofertas.(CU39)
	 */

	@Test(expected = Exception.class)
	public void DeleteSubSectionTest() {

		final Object testingData[][] = {
			{// Positive
				"commercial1", "subsection1", null
			}, {// Positive as admin:
				"admin", "subsection7", null
			}, {// Negative: not her subsection
				"commercial2", "subsection1", IllegalArgumentException.class
			}, {// Negative: Bad roll
				"executive1", "subsection1", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateDeleteSubSection((String) testingData[i][0], // Username login
				(Integer) super.getEntityId((String) testingData[i][1]), // subsectionId
				(Class<?>) testingData[i][2]);
	}
	protected void templateDeleteSubSection(final String principal, final Integer subsectionId, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			super.authenticate(principal);
			final SubSection subsection = this.subsectionService.findOne(subsectionId);
			this.subsectionService.delete(subsection);
			this.subsectionService.flush();

			Assert.isTrue(!this.subsectionService.findAll().contains(subsection));
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	/**
	 * Rol: Commercial
	 * 24.a.2. Listar las ofertas con fecha de presentación pasada, presentes en el sistema
	 * y realizar búsquedas en dichas ofertas filtrando por palabra clave.(CU40)
	 */

	@Test
	public void SearchSubSectionTest() {

		final Object testingData[][] = {
			{// Positive
				"commercial1", "Creo que nos hemos presentado", "offer2", null
			}, {// Positive
				"commercial2", "Creo que nos hemos presentado", "offer2", null
			}, {// Negative: Busqueda de una oferta que no tiene fecha pasada
				"commercial1", "Servicio de comedor de la Escuela Técnica", "offer2", IllegalArgumentException.class
			}, {// Negative: Busqueda de una oferta que no tiene fecha pasada
				"commercial2", "Servicio de comedor de la Escuela Técnica", "offer2", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateSearchSubSection((String) testingData[i][0], // Username login
				(String) testingData[i][1], // keyword
				(Integer) super.getEntityId((String) testingData[i][2]), (Class<?>) testingData[i][3]);
	}
	protected void templateSearchSubSection(final String principal, final String keyword, final Integer offerId, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			super.authenticate(principal);
			final Offer offer = this.offerService.findOne(offerId);
			final Collection<Offer> offers = this.offerService.findOfferByKeyWord(keyword);
			this.subsectionService.flush();
			Assert.isTrue(offers.contains(offer));
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	/**
	 * Rol: Commercial
	 * 26.a.1. Crear currículos asociados a los sub-apartados creados por él.(CU41)
	 */

	@Test(expected = Exception.class)
	public void CreateCurriculumTest() {

		final Object testingData[][] = {
			{// Positive
				"commercial1", "subsection4", "Jose", "Mena", "66453638T", "677588498", "mena@gmail.com", "15/05/1990 00:00", "La descripción del curriculum.", "21000", null
			}, {// Negative: wrong roll
				"executive1", "subsection4", "Jose", "Mena", "66453638T", "677588498", "mena@gmail.com", "15/05/1990 00:00", "La descripción del curriculum.", "21000", IllegalArgumentException.class
			}, {// Negative: bad phone number
				"commercial1", "subsection4", "Jose", "Mena", "66453638T", "6775884XX", "mena@gmail.com", "15/05/1990 00:00", "La descripción del curriculum.", "21000", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateCreateCurriculum((String) testingData[i][0], // Username login
				(Integer) super.getEntityId((String) testingData[i][1]), // subsection
				(String) testingData[i][2], // name
				(String) testingData[i][3], // surname
				(String) testingData[i][4], //identificationNumber
				(String) testingData[i][5], //phone
				(String) testingData[i][6], //email
				(String) testingData[i][7],	//dateOfBirth
				(String) testingData[i][8], //text
				Double.parseDouble((String) testingData[i][9]), //minSalaryExpectation
				(Class<?>) testingData[i][10]);
	}
	protected void templateCreateCurriculum(final String principal, final Integer subSectionId, final String name, final String surname, final String identificationNumber, final String phone, final String email, final String dateOfBirth,
		final String text, final Double minSalaryExpectation, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			super.authenticate(principal);
			final Curriculum curriculum = this.curriculumService.create();
			final SubSection subsection = this.subsectionService.findOne(subSectionId);
			curriculum.setText(text);
			curriculum.setSurname(surname);
			curriculum.setSubSection(subsection);
			curriculum.setPhone(phone);
			curriculum.setName(surname);
			curriculum.setMinSalaryExpectation(minSalaryExpectation);
			curriculum.setIdentificationNumber(identificationNumber);
			curriculum.setEmail(email);
			final DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			final Date d1 = format.parse(dateOfBirth);
			curriculum.setDateOfBirth(d1);

			final Curriculum curriculumSaved = this.curriculumService.save(curriculum);
			this.subsectionService.flush();

			Assert.isTrue(this.curriculumService.findAll().contains(curriculumSaved));
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

}
