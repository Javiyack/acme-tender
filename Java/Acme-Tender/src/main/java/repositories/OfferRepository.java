
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {

	@Query("select o from Offer o where o.commercial.id = ?1")
	Collection<Offer> findAllByCommercialPropietary(int commercialId);

	@Query("select o from Offer o where o.commercial.id != ?1 and o.id in (select sb.offer.id from SubSection sb where sb.commercial.id = ?1)")
	Collection<Offer> findAllByCommercialCollaboration(int evaluationCriteriaId);

	@Query("select o from Offer o where o.id in (select sb.offer.id from SubSection sb where sb.administrative.id = ?1)")
	Collection<Offer> findAllByAdministrativeCollaboration(int evaluationCriteriaId);
	
	@Query("select o from Offer o where o.state IN ('PRESENTED', 'WINED', 'LOSED', 'CHALLENGED') ")
	Collection<Offer> findAllPublished();
	
	
	
}
