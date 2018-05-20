
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.CompanyResult;

@Repository
public interface CompanyResultRepository extends JpaRepository<CompanyResult, Integer> {

	@Query("select cr from CompanyResult cr where cr.tenderResult.id = ?1")
	Collection<CompanyResult> findAllByTenderResult(int tenderResultId);

}
