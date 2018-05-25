
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Executive;

@Repository
public interface ExecutiveRepository extends JpaRepository<Executive, Integer> {

	@Query("select t.administrative.id, t.administrative.userAccount.username, count(t.id) from Tender t group by t.administrative.id, t.administrative.name")
	Collection<Object> numberTenderByUser();
}
