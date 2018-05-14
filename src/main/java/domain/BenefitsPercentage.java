
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class BenefitsPercentage extends DomainEntity {

	// Attributes
	private Double	lowerLimmit;
	private Double	upperLimmit;
	private Double	percentage;


	@Min(0)
	public Double getLowerLimmit() {
		return this.lowerLimmit;
	}

	public void setLowerLimmit(final Double lowerLimmit) {
		this.lowerLimmit = lowerLimmit;
	}

	@Range(min = 0, max = 100)
	public Double getPercentage() {
		return this.percentage;
	}

	public void setPercentage(final Double percentage) {
		this.percentage = percentage;
	}

	@Min(0)
	public Double getUpperLimmit() {
		return this.upperLimmit;
	}

	public void setUpperLimmit(final Double upperLimmit) {
		this.upperLimmit = upperLimmit;
	}

}
