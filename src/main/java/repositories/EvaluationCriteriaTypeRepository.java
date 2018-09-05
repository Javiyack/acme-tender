package repositories;

import domain.EvaluationCriteriaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationCriteriaTypeRepository extends JpaRepository<EvaluationCriteriaType, Integer> {

}
