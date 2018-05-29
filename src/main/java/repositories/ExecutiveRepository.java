
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Executive;

@Repository
public interface ExecutiveRepository extends JpaRepository<Executive, Integer> {

	@Query("select t.administrative.id, t.administrative.userAccount.username, count(t.id) from Tender t group by t.administrative.id, t.administrative.name")
	Collection<Object> numberTenderByUser();

	@Query("select sum(case when t.interest = 'UNDEFINED' then 1 else 0 end)*100/count(t), sum(case when t.interest = 'HIGH' then 1 else 0 end)*100/count(t), sum(case when t.interest = 'MEDIUM' then 1 else 0 end)*100/count(t), sum(case when t.interest = 'LOW' then 1 else 0 end)*100/count(t) from Tender t")
	Collection<Object> tendersByInterestLevel();

	@Query("select sum(case when o.state='CREATED' then 1 else 0 end),  sum(case when o.state='IN_DEVELOPMENT' then 1 else 0 end),  sum(case when o.state='ABANDONED' then 1 else 0 end), sum(case when o.state='PRESENTED' then 1 else 0 end), sum(case when o.state='WINNED' then 1 else 0 end), sum(case when o.state='LOSSED' then 1 else 0 end),  sum(case when o.state='CHALLENGED' then 1 else 0 end),  sum(case when o.state='DENIED' then 1 else 0 end) from Offer o")
	Collection<Object> offersByState();

	@Query("select o.commercial.name, sum(case when o.state='CREATED' then 1 else 0 end),  sum(case when o.state='IN_DEVELOPMENT' then 1 else 0 end),  sum(case when o.state='ABANDONED' then 1 else 0 end), sum(case when o.state='PRESENTED' then 1 else 0 end), sum(case when o.state='WINNED' then 1 else 0 end), sum(case when o.state='LOSSED' then 1 else 0 end),  sum(case when o.state='CHALLENGED' then 1 else 0 end),  sum(case when o.state='DENIED' then 1 else 0 end) from Offer o group by o.commercial.name")
	Collection<Object> offersByStateAndCommercial();

	@Query("select sum(case when o.state='CREATED' then 1 else 0 end)*100/count(o),  sum(case when o.state='IN_DEVELOPMENT' then 1 else 0 end)*100/count(o),  sum(case when o.state='ABANDONED' then 1 else 0 end)*100/count(o), sum(case when o.state='PRESENTED' then 1 else 0 end)*100/count(o), sum(case when o.state='WINNED' then 1 else 0 end)*100/count(o), sum(case when o.state='LOSSED' then 1 else 0 end)*100/count(o),  sum(case when o.state='CHALLENGED' then 1 else 0 end)*100/count(o),  sum(case when o.state='DENIED' then 1 else 0 end)*100/count(o)  from Offer o")
	Collection<Object> offersByStateRatio();

	@Query("select o.commercial.name, sum(case when o.state='CREATED' then 1 else 0 end)*100/count(o),  sum(case when o.state='IN_DEVELOPMENT' then 1 else 0 end)*100/count(o),  sum(case when o.state='ABANDONED' then 1 else 0 end)*100/count(o), sum(case when o.state='PRESENTED' then 1 else 0 end)*100/count(o), sum(case when o.state='WINNED' then 1 else 0 end)*100/count(o), sum(case when o.state='LOSSED' then 1 else 0 end)*100/count(o),  sum(case when o.state='CHALLENGED' then 1 else 0 end)*100/count(o),  sum(case when o.state='DENIED' then 1 else 0 end)*100/count(o) from Offer o group by o.commercial.name")
	Collection<Object> offersByStateAndCommercialRatio();
}
