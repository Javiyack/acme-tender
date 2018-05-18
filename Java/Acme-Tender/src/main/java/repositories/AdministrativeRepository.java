
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrative;

@Repository
public interface AdministrativeRepository extends JpaRepository<Administrative, Integer> {

	
}
