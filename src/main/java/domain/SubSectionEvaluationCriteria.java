package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class SubSectionEvaluationCriteria extends DomainEntity {

    private String comments;
    private SubSection subSection;
    private EvaluationCriteria evaluationCriteria;

    //Relationships

    @SafeHtml(whitelistType = WhiteListType.NONE)
    @NotBlank
    public String getComments() {
        return this.comments;
    }

    public void setComments(final String comments) {
        this.comments = comments;
    }

    @Valid
    @NotNull
    @ManyToOne(optional = false)
    public SubSection getSubSection() {
        return subSection;
    }

    public void setSubSection(SubSection subSection) {
        this.subSection = subSection;
    }

    @Valid
    @NotNull
    @ManyToOne(optional = false)
    public EvaluationCriteria getEvaluationCriteria() {
        return evaluationCriteria;
    }

    public void setEvaluationCriteria(EvaluationCriteria evaluationCriteria) {
        this.evaluationCriteria = evaluationCriteria;
    }


}
