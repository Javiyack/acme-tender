
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class File extends DomainEntity {

	private String	name;
	private Date	uploadDate;


	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@NotNull
	public Date getUploadDate() {
		return this.uploadDate;
	}

	public void setUploadDate(final Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	
	//Relationships
	
	private Curriculum curriculum;
	private SubSection subsection;
	private Tender tender;
	private TenderResult tenderResult;

	@ManyToOne(optional = true)
	public Curriculum getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}

	@ManyToOne(optional = true)	
	public SubSection getSubsection() {
		return subsection;
	}

	public void setSubsection(SubSection subsection) {
		this.subsection = subsection;
	}

	@ManyToOne(optional = true)	
	public Tender getTender() {
		return tender;
	}

	public void setTender(Tender tender) {
		this.tender = tender;
	}

	@ManyToOne(optional = true)	
	public TenderResult getTenderResult() {
		return tenderResult;
	}

	public void setTenderResult(TenderResult tenderResult) {
		this.tenderResult = tenderResult;
	}
	
	

}
