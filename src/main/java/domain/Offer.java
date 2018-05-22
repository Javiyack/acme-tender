
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Offer extends DomainEntity {

	private String	state;
	private String	denialReason;
	private Date	presentationDate;
	private double	amount;


	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Pattern(regexp = "^(CREATED|IN_DEVELOPMENT|ABANDONED|PRESENTED|WINNED|LOSED|CHALLENGED|DENIED)$")
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

	@Min(0)
	public double getAmount() {
		return this.amount;
	}

	public void setAmount(final double amount) {
		this.amount = amount;
	}
	
	//Relationships
	private Commercial commercial;
	private Tender tender;

	@Valid
	@NotNull	
	@ManyToOne(optional = false)
	public Commercial getCommercial() {
		return commercial;
	}

	public void setCommercial(Commercial commercial) {
		this.commercial = commercial;
	}

	@Valid
	@NotNull	
	@OneToOne(optional = false)
	public Tender getTender() {
		return tender;
	}

	public void setTender(Tender tender) {
		this.tender = tender;
	}
	
	
	@Transient
	public boolean isPublished() {
		switch  (this.getState()) {		
			case "PRESENTED": case "WINNED": case "LOSED": case "CHALLENGED":
				return true;
			case "ABANDONED": case "DENIED": case "CREATED": case "IN_DEVELOPMENT":
				return false;
		}
		
		return true;
	}
	
	public void setPublished(boolean published) {
		
	}
	

}
