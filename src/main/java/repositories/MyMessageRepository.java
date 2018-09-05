package repositories;

import domain.MyMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MyMessageRepository extends JpaRepository<MyMessage, Integer> {

    @Query("select m from Actor a join a.folders f join f.mymessages m where a.id=?1")
    Collection<MyMessage> messagesFromActorId(int actorId);

}


