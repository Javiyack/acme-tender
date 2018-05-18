
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.SubSection;

@Repository
public interface SubSectionRepository extends JpaRepository<SubSection, Integer> {

}
