
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Offer extends DomainEntity {

	private String	state;
	private String	denialReason;
	private Date	presentationDate;
	private Double	amount;


	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Pattern(regexp = "CREATED|IN_DEVELOPMENT|ABANDONED|PRESENTED|WON|LOSED|CHALLENGED|DENIED$")
	public String getState() {
		return this.state;
	}

	public void setState(final String state) {
		this.state = state;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getDenialReason() {
		return this.denialReason;
	}

	public void setDenialReason(final String denialReason) {
		this.denialReason = denialReason;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getPresentationDate() {
		return this.presentationDate;
	}

	public void setPresentationDate(final Date presentationDate) {
		this.presentationDate = presentationDate;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Min(0)
	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(final Double amount) {
		this.amount = amount;
	}

}
