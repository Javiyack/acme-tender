package repositories;

import domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    @Query("select a from Answer a where a.comment.id = ?1")
    Collection<Answer> findAllByCommentId(Integer commentId);

}
