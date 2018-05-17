
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class ColaborationRequest extends DomainEntity {
	
	private double benefitsPercentaje;
	private String section;
	private String requirements;
	private Integer numberOfPages;

	private String	subSectionTitle;
	private String	subSectionDescription;
	private Date	maxAcceptanceDate;
	private Date	maxDeliveryDate;
	private Boolean	accepted;
	private String	rejectedReason;
	
	
	@Min(0)
	@Max(100)
	public double getBenefitsPercentaje() {
		return benefitsPercentaje;
	}

	public void setBenefitsPercentaje(double benefitsPercentaje) {
		this.benefitsPercentaje = benefitsPercentaje;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Pattern(regexp = "^(TECHNICAL_OFFER|ECONOMICAL_OFFER)$")
	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	@Min(1)
	public Integer getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(Integer numberOfPages) {
		this.numberOfPages = numberOfPages;
	}	


	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getSubSectionTitle() {
		return this.subSectionTitle;
	}

	public void setSubSectionTitle(final String subSectionTitle) {
		this.subSectionTitle = subSectionTitle;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getSubSectionDescription() {
		return this.subSectionDescription;
	}

	public void setSubSectionDescription(final String subSectionDescription) {
		this.subSectionDescription = subSectionDescription;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@NotNull
	public Date getMaxAcceptanceDate() {
		return this.maxAcceptanceDate;
	}

	public void setMaxAcceptanceDate(final Date maxAcceptanceDate) {
		this.maxAcceptanceDate = maxAcceptanceDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@NotNull
	public Date getMaxDeliveryDate() {
		return this.maxDeliveryDate;
	}

	public void setMaxDeliveryDate(final Date maxDeliveryDate) {
		this.maxDeliveryDate = maxDeliveryDate;
	}

	public Boolean isAccepted() {
		return this.accepted;
	}

	public void setAccepted(final Boolean accepted) {
		this.accepted = accepted;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getRejectedReason() {
		return this.rejectedReason;
	}

	public void setRejectedReason(final String rejectedReason) {
		this.rejectedReason = rejectedReason;
	}
	
	
	// Relationships
	private Commercial commercial;
	private Offer offer;

	@ManyToOne(optional = false)
	public Commercial getCommercial() {
		return commercial;
	}

	public void setCommercial(Commercial commercial) {
		this.commercial = commercial;
	}

	@ManyToOne(optional = false)
	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	

}
