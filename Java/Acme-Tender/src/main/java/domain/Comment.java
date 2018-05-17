
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Comment extends DomainEntity {

	private String	text;
	private Date	writingDate;


	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getText() {
		return this.text;
	}

	public void setText(final String text) {
		this.text = text;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getWritingDate() {
		return this.writingDate;
	}

	public void setWritingDate(final Date writingDate) {
		this.writingDate = writingDate;
	}
	
	//Relationships
	
	private Tender tender;
	private Commercial commercial;


	@ManyToOne(optional = false)
	public Tender getTender() {
		return tender;
	}

	public void setTender(Tender tender) {
		this.tender = tender;
	}
	
	@ManyToOne(optional = false)
	public Commercial getCommercial() {
		return commercial;
	}

	public void setCommercial(Commercial commercial) {
		this.commercial = commercial;
	}
	
	

}
