
package usecases;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.EvaluationCriteria;
import domain.EvaluationCriteriaType;
import security.UserAccountService;
import services.ActorService;
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


	/**
	 * Rol: Administrativo
	 * 25.a.3. Editar criterios de valoración asociados a sus concursos.(CU29)
	 */
	@Test
	public void editTenderCriteriaTest() {

		final Object testingData[][] = {
			{// Positive
				"administrative1", "tender1", "criterio1", "criterio1 descipción", "10", "evaluationcriteriatype2", null
			}, {//Positive
				"administrative1", "tender3", "criterio2", "criterio2 descipción", "8", "evaluationcriteriatype1", null
			}, {// Negative: wrong roll
				"executive1", "tender1", "criterio2", "criterio2 descipción", "8", "evaluationcriteriatype1", IllegalArgumentException.class
			}, {// Negative:  tender3 is not administrative1's property
				"administrative1", "tender2", "criterio2", "criterio2 descipción", "8", "evaluationcriteriatype1", IllegalArgumentException.class
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
