
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
public class SubsectionEvaluationCriteria extends DomainEntity {

	private String	comments;


	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getComments() {
		return this.comments;
	}

	public void setComments(final String comments) {
		this.comments = comments;
	}
	
	//Relationships
	
	private SubSection subsection;
	private EvaluationCriteria evaluationCriteria;

	@ManyToOne(optional = false)
	public SubSection getSubsection() {
		return subsection;
	}

	public void setSubsection(SubSection subsection) {
		this.subsection = subsection;
	}

	@ManyToOne(optional = false)
	public EvaluationCriteria getEvaluationCriteria() {
		return evaluationCriteria;
	}

	public void setEvaluationCriteria(EvaluationCriteria evaluationCriteria) {
		this.evaluationCriteria = evaluationCriteria;
	}
	
	

}
