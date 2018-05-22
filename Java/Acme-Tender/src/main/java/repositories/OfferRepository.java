
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrative;
import domain.Commercial;
import domain.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {

	@Query("select o from Offer o where o.commercial.id = ?1")
	Collection<Offer> findAllByCommercialPropietary(int commercialId);

	@Query("select o from Offer o where o.commercial.id != ?1 and o.id in (select sb.offer.id from SubSection sb where sb.commercial.id = ?1)")
	Collection<Offer> findAllByCommercialCollaboration(int evaluationCriteriaId);

	@Query("select o from Offer o where o.id in (select sb.offer.id from SubSection sb where sb.administrative.id = ?1)")
	Collection<Offer> findAllByAdministrativeCollaboration(int evaluationCriteriaId);
	
	@Query("select sb.commercial from SubSection sb where sb.offer.id = ?1 and sb.commercial != null group by sb.commercial")
	Collection<Commercial> findCommercialCollaboratorsByOffer(int offerId);
	
	@Query("select sb.administrative from SubSection sb where sb.offer.id = ?1 and sb.administrative != null group by sb.administrative")
	Collection<Administrative> findAdministrativeCollaboratorsByOffer(int offerId);	
	
	@Query("select o from Offer o where o.published = true ")
	Collection<Offer> findAllPublished();
	
	
	
}
