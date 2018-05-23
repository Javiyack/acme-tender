
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Tender;

@Repository
public interface TenderRepository extends JpaRepository<Tender, Integer> {

	@Query("select t from Tender t where t.administrative.id = ?1")
	Collection<Tender> findAllByAdministrative(int id);

	@Query("select t from Tender t "
			+ " where (t.title like %?1% or t.expedient like %?1% or t.organism like %?1% or t.bulletin like %?1% or t.observations like %?1% or t.informationPage like %?1% or t.interestComment like %?1%) ")
	Collection<Tender> findTenderByKeyword(String word);	
	
}
