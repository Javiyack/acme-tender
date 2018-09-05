package repositories;

import domain.CompanyResult;
import domain.Constant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CompanyResultRepository extends JpaRepository<CompanyResult, Integer> {

    @Query("select cr from CompanyResult cr where cr.tenderResult.id = ?1 order by cr.position asc")
    Collection<CompanyResult> findAllByTenderResult(int tenderResultId);

    @Query("select cr from CompanyResult cr where cr.tenderResult.id = ?1 and cr.state = '" + Constant.COMPANY_RESULT_WINNER + "' ")
    Collection<CompanyResult> findAllWinnerByTenderResult(int tenderResultId);
}
