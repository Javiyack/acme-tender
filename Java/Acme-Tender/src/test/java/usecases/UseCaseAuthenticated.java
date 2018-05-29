
package usecases;

import java.util.Collection;

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
import services.FolderService;
import utilities.AbstractTest;
import domain.Actor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class UseCaseAuthenticated extends AbstractTest {

	@Autowired
	private ActorService		actorService;
	@Autowired
	private FolderService		folderService;
	@Autowired
	private UserAccountService	userAccountService;


	/*
	 * Caso de uso:
	 * Auth-> Ver la información de contacto de cualquier otro usuario.(CU02)
	 */
	@Test
	public void contactInformationTest() {

		final Object testingData[][] = {
			{// Positive
				"executive1", "administrative1", null
			}, {//Positive
				"administrative1", "executive1", null
			}, {// Negative: without name
				"executive1", "papayarroz", NumberFormatException.class
			}, {// Negative: with name null
				"", "administrative1", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateContactInformationTest(i, (String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	protected void templateContactInformationTest(final Integer i, final String principal, final String user, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(principal);
			final Collection<Actor> actors = this.actorService.findAll();

			for (final Actor actor : actors)
				if (actor.getId() == this.getEntityId(user)) {
					final Actor actorBD = this.actorService.findOne(actor.getId());
					Assert.notNull(actorBD);
				}

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
}
