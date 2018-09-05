/**
 *
 */

package repositories;

import domain.AdministrativeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AdministrativeRequestRepository extends JpaRepository<AdministrativeRequest, Integer> {

    @Query("select a from AdministrativeRequest a where a.offer in (select o from Offer o where o.commercial.id = ?1)")
    Collection<AdministrativeRequest> getSentAdministrativeRequestsFromCommercialId(int commercialId);

    @Query("select a from AdministrativeRequest a where a.administrative.id = ?1")
    Collection<AdministrativeRequest> getReceivedAdministrativeRequestsFromAdministrativeId(int administrativeId);

    @Query("select ar from AdministrativeRequest ar where ar.offer.id= ?1")
    Collection<AdministrativeRequest> findAllByOffer(int offerId);

}
