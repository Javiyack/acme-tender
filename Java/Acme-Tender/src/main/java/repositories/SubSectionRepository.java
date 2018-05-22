
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.SubSection;

@Repository
public interface SubSectionRepository extends JpaRepository<SubSection, Integer> {


	@Query("select sb from SubSection sb where sb.offer.id = ?1 order by sb.subsectionOrder asc")
	Collection<SubSection> findAllByOffer(int offerId);
	
	
}
