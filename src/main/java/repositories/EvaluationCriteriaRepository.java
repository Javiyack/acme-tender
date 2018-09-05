package repositories;

import domain.EvaluationCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface EvaluationCriteriaRepository extends JpaRepository<EvaluationCriteria, Integer> {

    @Query("select ec from EvaluationCriteria ec where ec.evaluationCriteriaType.id = ?1")
    Collection<EvaluationCriteria> findAllWithType(int evaluationCriteriaTypeId);

    @Query("select ec from EvaluationCriteria ec where ec.tender.id = ?1")
    Collection<EvaluationCriteria> findAllByTender(int tenderId);

}
