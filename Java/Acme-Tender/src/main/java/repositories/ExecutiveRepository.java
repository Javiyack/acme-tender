
package repositories;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Executive;
import domain.Offer;

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
	
	@Query("select o from Offer o where year(o.presentationDate)*100+month(o.presentationDate) = year(?1) * 100 + month(?1) - 1 order by o.amount desc")
	List<Offer> findTopOffersOnMonth(Date month, Pageable pageSize);
	
	@Query("select o from Offer o where year(o.presentationDate)*100+month(o.presentationDate) < year(?1) * 100 + month(?1) and year(o.presentationDate)*100+month(o.presentationDate) > year(?1) * 100 + month(?1) - 4 and o.state = 'WINNED' order by o.amount desc")
	List<Offer> findTopWinedOffersOnThreeMonths(Date fecha, Pageable pageSize);
	
	@Query("select cr.name, avg(cr.economicalOffer / t.estimatedAmount) from CompanyResult cr join cr.tenderResult tr join tr.tender t where t.estimatedAmount != 0 group by cr.name")
	Collection<Object> findAvgRatioAmounOfferOverTender();

	@Query("select cr.name, count(cr.name) from CompanyResult cr group by cr.name order by count(cr.name) desc")
	Collection<Object> findTopFrecuentsCompanies();

	@Query("select cr.name, count(cr.name) from CompanyResult cr where cr.state = 'WINNER' group by cr.name order by count(cr.name) desc")
	Collection<Object> findTopFrecuentsWinnersCompanies();
}
