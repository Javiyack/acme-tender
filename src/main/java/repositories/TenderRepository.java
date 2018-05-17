
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Tender;

@Repository
public interface TenderRepository extends JpaRepository<Tender, Integer> {

}
