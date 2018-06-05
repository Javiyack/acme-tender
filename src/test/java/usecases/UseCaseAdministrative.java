
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

import domain.Category;
import domain.EvaluationCriteria;
import domain.EvaluationCriteriaType;
import domain.Tender;
import security.UserAccountService;
import services.ActorService;
import services.CategoryService;
import services.EvaluationCriteriaService;
import services.EvaluationCriteriaTypeService;
import services.TenderService;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class UseCaseAdministrative extends AbstractTest {

	@Autowired
	private ActorService					actorService;
	@Autowired
	private TenderService					tenderService;
	@Autowired
	private EvaluationCriteriaService		evaluationCriteriaService;
	@Autowired
	private EvaluationCriteriaTypeService	evaluationCriteriaTypeService;
	@Autowired
	private UserAccountService				userAccountService;
	@Autowired
	private CategoryService					categoryService;


	/* SUIT DE TESTS FUNCIONALES: CREAR CONCURSO */
	/*
	 * 10. Un usuario autenticado como administrativo podr�:
	 * a. Administrar sus concursos, lo que incluye crearlos, editarlos o eliminarlos.
	 */
	/* Autenticado como administrativo --> crear un concurso */
	@Test(expected = Exception.class)
	public void createTenderTest() {
		Object testingData[][] = {
			//Positivo(sin observaciones ni comentario de inter�s)
			{
				"administrative1", "title", "category1", "expedient", 50000., "organism", "bulletin", "03/06/2018 12:00", "01/07/2018 12:00", "03/07/2018 12:00", 90, "", "http://www.juntadeandalucia.es/index.html", "HIGH", "", null
			},
			//Positivo(con observaciones)
			{
				"administrative1", "title", "category1", "expedient", 50000., "organism", "bulletin", "03/06/2018 12:00", "01/07/2018 12:00", "03/07/2018 12:00", 90, "observaciones", "http://www.juntadeandalucia.es/index.html", "HIGH", "", null
			},
			//Positivo(con comentario de inter�s)
			{
				"administrative1", "title", "category1", "expedient", 50000., "organism", "bulletin", "03/06/2018 12:00", "01/07/2018 12:00", "03/07/2018 12:00", 90, "", "http://www.juntadeandalucia.es/index.html", "HIGH", "comentario de inter�s", null
			},
			//Negativo(autenticado como comercial)
			{
				"commercial1", "title", "category1", "expedient", 50000., "organism", "bulletin", "03/06/2018 12:00", "01/07/2018 12:00", "03/07/2018 12:00", 90, "", "http://www.juntadeandalucia.es/index.html", "HIGH", "comentario de inter�s",
				IllegalArgumentException.class
			},
			//Negativo(sin t�tulo)
			{
				"administrative1", "", "category1", "expedient", 50000., "organism", "bulletin", "03/06/2018 12:00", "01/07/2018 12:00", "03/07/2018 12:00", 90, "", "http://www.juntadeandalucia.es/index.html", "HIGH", "", ConstraintViolationException.class
			},
			//Negativo(con fecha del bolet�n futura)
			{
				"administrative1", "title", "category1", "expedient", 50000., "organism", "bulletin", "03/07/2018 12:00", "01/07/2018 12:00", "03/07/2018 12:00", 90, "", "http://www.juntadeandalucia.es/index.html", "HIGH", "",
				ConstraintViolationException.class
			},
			//Negativo(con fecha de apertura pasada)
			{
				"administrative1", "title", "category1", "expedient", 50000., "organism", "bulletin", "03/06/2018 12:00", "01/06/2018 12:00", "03/07/2018 12:00", 90, "", "http://www.juntadeandalucia.es/index.html", "HIGH", "", AssertionError.class
			},

			//Negativo(con fecha m�xima de presentaci�n anterior a la fecha de apertura)
			{
				"administrative1", "title", "category1", "expedient", 50000., "organism", "bulletin", "03/06/2018 12:00", "03/07/2018 12:00", "01/07/2018 12:00", 90, "", "http://www.juntadeandalucia.es/index.html", "HIGH", "", AssertionError.class
			},

			//Negativo(con cantidad estimada menor que 0)
			{
				"administrative1", "title", "category1", "expedient", -20000., "organism", "bulletin", "03/06/2018 12:00", "01/07/2018 12:00", "03/07/2018 12:00", 90, "", "http://www.juntadeandalucia.es/index.html", "HIGH", "",
				ConstraintViolationException.class
			},
			//Negativo(con tiempo de ejecuci�n menor que 0)
			{
				"administrative1", "title", "category1", "expedient", 50000., "organism", "bulletin", "03/06/2018 12:00", "01/07/2018 12:00", "03/07/2018 12:00", -90, "", "http://www.juntadeandalucia.es/index.html", "HIGH", "",
				ConstraintViolationException.class
			},
			//Negativo(con p�gina de informaci�n incorrecta)
			{
				"administrative1", "title", "category1", "expedient", 50000., "organism", "bulletin", "03/06/2018 12:00", "01/07/2018 12:00", "03/07/2018 12:00", 90, "", "juntadeandalucia", "HIGH", "", ConstraintViolationException.class
			},
			//Negativo(sin categor�a)
			{
				"administrative1", "title", "", "expedient", 50000., "organism", "bulletin", "03/06/2018 12:00", "01/07/2018 12:00", "03/07/2018 12:00", 90, "", "http://www.juntadeandalucia.es/index.html", "HIGH", "", ConstraintViolationException.class
			},
			//Negativo(sin expediente)
			{
				"administrative1", "title", "category1", "", 50000., "organism", "bulletin", "03/06/2018 12:00", "01/07/2018 12:00", "03/07/2018 12:00", 90, "", "http://www.juntadeandalucia.es/index.html", "HIGH", "", ConstraintViolationException.class
			},
			//Negativo(sin organismo)
			{
				"administrative1", "title", "category1", "expedient", 50000., "", "bulletin", "03/06/2018 12:00", "01/07/2018 12:00", "03/07/2018 12:00", 90, "", "http://www.juntadeandalucia.es/index.html", "HIGH", "", ConstraintViolationException.class
			},
			//Negativo(sin bolet�n)
			{
				"administrative1", "title", "category1", "expedient", 50000., "organism", "", "03/06/2018 12:00", "01/07/2018 12:00", "03/07/2018 12:00", 90, "", "http://www.juntadeandalucia.es/index.html", "HIGH", "", ConstraintViolationException.class
			},
			//Negativo(sin inter�s)
			{
				"administrative1", "title", "category1", "expedient", 50000., "organism", "bulletin", "03/06/2018 12:00", "01/07/2018 12:00", "03/07/2018 12:00", 90, "", "http://www.juntadeandalucia.es/index.html", "", "",
				ConstraintViolationException.class
			},

		};
		for (int i = 0; i < testingData.length; i++) {
			templateCreateTender((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (double) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6], (String) testingData[i][7],
				(String) testingData[i][8], (String) testingData[i][9], (Integer) testingData[i][10], (String) testingData[i][11], (String) testingData[i][12], (String) testingData[i][13], (String) testingData[i][14], (Class<?>) testingData[i][15]);
		}
	}

	private void templateCreateTender(String principal, String title, String category, String expedient, Double estimatedAmount, String organism, String bulletin, String bulletinDate, String openingDate, String maxPresentationDate, Integer executionTime,
		String observations, String informationPage, String interest, String interestComment, Class<?> expected) {

		Class<?> caught;

		caught = null;
		try {
			authenticate(principal);
			Collection<Tender> tenders = this.tenderService.findAllByAdministrative();
			Tender tender = this.tenderService.create();
			tender.setTitle(title);
			Category c;
			if (!category.isEmpty()) {
				Integer categoryId = super.getEntityId(category);
				c = this.categoryService.findOne(categoryId);
			} else {
				c = null;
			}
			tender.setCategory(c);
			tender.setExpedient(expedient);
			tender.setEstimatedAmount(estimatedAmount);
			tender.setOrganism(organism);
			tender.setBulletin(bulletin);
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			Date d1 = format.parse(bulletinDate);
			tender.setBulletinDate(d1);
			Date d2 = format.parse(openingDate);
			tender.setOpeningDate(d2);
			Date d3 = format.parse(maxPresentationDate);
			tender.setMaxPresentationDate(d3);
			tender.setExecutionTime(executionTime);
			tender.setObservations(observations);
			tender.setInformationPage(informationPage);
			tender.setInterest(interest);
			tender.setInterestComment(interestComment);

			Tender saved = this.tenderService.save(tender);
			this.tenderService.flush();
			Assert.isTrue(tenders.contains(saved));

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	/**
	 * Rol: Administrativo
	 * 25.a.3. Editar criterios de valoraci�n asociados a sus concursos.(CU29)
	 */
	@Test
	public void editTenderCriteriaTest() {

		final Object testingData[][] = {
			{// Positive
				"administrative1", "tender1", "criterio1", "criterio1 descipci�n", "10", "evaluationcriteriatype2", null
			}, {//Positive
				"administrative1", "tender3", "criterio2", "criterio2 descipci�n", "8", "evaluationcriteriatype1", null
			}, {// Negative: wrong roll
				"executive1", "tender1", "criterio2", "criterio2 descipci�n", "8", "evaluationcriteriatype1", IllegalArgumentException.class
			}, {// Negative:  tender3 is not administrative1's property
				"administrative1", "tender2", "criterio2", "criterio2 descipci�n", "8", "evaluationcriteriatype1", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateEditTenderCriteria((String) testingData[i][0], // Username login
				(Integer) super.getEntityId((String) testingData[i][1]), // tenderId
				(String) testingData[i][2], // titleCriteria
				(String) testingData[i][3], // descriptionCriteria
				(Integer) Integer.parseInt((String) testingData[i][4]), // maxScoreCriteria
				(Integer) super.getEntityId((String) testingData[i][5]), // typeCriteriaId
				(Class<?>) testingData[i][6]);
	}
	protected void templateEditTenderCriteria(final String principal, final Integer tenderId, final String titleCriteria, final String descriptionCriteria, final Integer maxScoreCriteria, final Integer typeCriteriaId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(principal);
			final EvaluationCriteria evCriteria = this.evaluationCriteriaService.create(tenderId);
			final EvaluationCriteriaType evTypeCriteria = this.evaluationCriteriaTypeService.findOne(typeCriteriaId);

			evCriteria.setTitle(titleCriteria);
			evCriteria.setDescription(descriptionCriteria);
			evCriteria.setMaxScore(maxScoreCriteria);
			evCriteria.setEvaluationCriteriaType(evTypeCriteria);

			final EvaluationCriteria evCriteriaSaved = this.evaluationCriteriaService.save(evCriteria);
			this.evaluationCriteriaService.flush();

			Assert.isTrue(this.evaluationCriteriaService.findAllByTender(tenderId).contains(evCriteriaSaved));

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
}
