
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class SubSection extends DomainEntity {

	private String	title;
	private String	section;
	private Integer	subsectionOrder;
	private String	shortDescription;
	private String	body;
	private Date	lastReviewDate;
	private String	comments;


	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Pattern(regexp = "^(ADMINISTRATIVE_ACREDITATION|TECHNICAL_OFFER|ECONOMICAL_OFFER)$")
	public String getSection() {
		return this.section;
	}

	public void setSection(final String section) {
		this.section = section;
	}

	@Min(1)
	public Integer getSubsectionOrder() {
		return this.subsectionOrder;
	}

	public void setSubsectionOrder(final Integer subsectionOrder) {
		this.subsectionOrder = subsectionOrder;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getShortDescription() {
		return this.shortDescription;
	}

	public void setShortDescription(final String shortDescription) {
		this.shortDescription = shortDescription;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getBody() {
		return this.body;
	}

	public void setBody(final String body) {
		this.body = body;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Past
	public Date getLastReviewDate() {
		return this.lastReviewDate;
	}

	public void setLastReviewDate(final Date lastReviewDate) {
		this.lastReviewDate = lastReviewDate;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getComments() {
		return this.comments;
	}

	public void setComments(final String comments) {
		this.comments = comments;
	}


	//Relationships
	private Offer			offer;
	private Commercial		commercial;
	private Administrative	administrative;


	@Valid
	@ManyToOne(optional = false)
	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	@Valid
	@OneToOne(optional = true)
	public Commercial getCommercial() {
		return commercial;
	}

	public void setCommercial(Commercial commercial) {
		this.commercial = commercial;
	}

	@Valid
	@OneToOne(optional = true)
	public Administrative getAdministrative() {
		return administrative;
	}

	public void setAdministrative(Administrative administrative) {
		this.administrative = administrative;
	}

}
