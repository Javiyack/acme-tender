/**
 * 
 */

package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.AdministrativeRequest;

@Repository
public interface AdministrativeRequestRepository extends JpaRepository<AdministrativeRequest, Integer> {

	@Query("select ar from AdministrativeRequest ar where ar.offer.id= ?1")
	Collection<AdministrativeRequest> findAllByOffer(int offerId);

}
