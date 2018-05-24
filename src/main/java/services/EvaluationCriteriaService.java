
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EvaluationCriteriaRepository;
import domain.EvaluationCriteria;
import domain.SubSectionEvaluationCriteria;
import domain.Tender;

@Service
@Transactional
public class EvaluationCriteriaService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private EvaluationCriteriaRepository	evaluationCriteriaRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	AdministrativeService					administrativeService;
	@Autowired
	TenderService							tenderService;
	@Autowired
	SubSectionEvaluationCriteriaService		subSectionEvaluationCriteriaService;


	// Constructors -----------------------------------------------------------
	public EvaluationCriteriaService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public EvaluationCriteria create(final int tenderId) {
		final Tender tender = this.tenderService.findOneToEdit(tenderId);

		final EvaluationCriteria evaluationCriteria = new EvaluationCriteria();
		evaluationCriteria.setTender(tender);

		return evaluationCriteria;
	}

	public Collection<EvaluationCriteria> findAll() {
		Collection<EvaluationCriteria> result;

		result = this.evaluationCriteriaRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Collection<EvaluationCriteria> findAllWithType(final int evaluationCriteriaTypeId) {
		Collection<EvaluationCriteria> result;

		result = this.evaluationCriteriaRepository.findAllWithType(evaluationCriteriaTypeId);
		Assert.notNull(result);

		return result;
	}

	public Collection<EvaluationCriteria> findAllByTender(final int tenderId) {
		Collection<EvaluationCriteria> result;

		result = this.evaluationCriteriaRepository.findAllByTender(tenderId);
		Assert.notNull(result);

		return result;
	}

	public EvaluationCriteria findOne(final int evaluationCriteriaId) {
		Assert.isTrue(evaluationCriteriaId != 0);

		EvaluationCriteria result;

		result = this.evaluationCriteriaRepository.findOne(evaluationCriteriaId);
		Assert.notNull(result);

		return result;
	}

	public EvaluationCriteria save(final EvaluationCriteria evaluationCriteria) {
		Assert.notNull(evaluationCriteria);

		EvaluationCriteria result;

		result = this.evaluationCriteriaRepository.save(evaluationCriteria);

		return result;
	}

	public void delete(final EvaluationCriteria evaluationCriteria) {
		Assert.notNull(evaluationCriteria);
		Assert.isTrue(evaluationCriteria.getId() != 0);
		Assert.isTrue(this.evaluationCriteriaRepository.exists(evaluationCriteria.getId()));

		final Collection<SubSectionEvaluationCriteria> subSectionEvaluationCriterias = this.subSectionEvaluationCriteriaService.findAllWithEvaluationCriteria(evaluationCriteria.getId());

		Assert.isTrue(subSectionEvaluationCriterias.size() == 0, "evaluationCriteria.cannot.delete.in.use");

		this.evaluationCriteriaRepository.delete(evaluationCriteria);
	}

	public void deleteByAdmin(final Collection<EvaluationCriteria> evaluationCriterias) {

		for (final EvaluationCriteria ev : evaluationCriterias) {
			final Collection<SubSectionEvaluationCriteria> subSectionEvaluationCriterias = this.subSectionEvaluationCriteriaService.findAllWithEvaluationCriteria(ev.getId());
			this.subSectionEvaluationCriteriaService.deleteInBatch(subSectionEvaluationCriterias);

			this.evaluationCriteriaRepository.delete(ev);
		}

	}
	// Other business methods -------------------------------------------------

}
