package repositories;

import domain.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

    @Query("select a from Actor a where a.userAccount.id = ?1")
    Actor findByUserAccountId(int userAccountId);

    @Query("select a from Actor a join a.userAccount ua where ua.active = true")
    Collection<Actor> findAllActivated();

}
