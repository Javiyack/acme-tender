
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class File extends DomainEntity {

	private String	name;
	private Date	uploadDate;
	private String mimeType;
	private byte[] data;


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
	@Past
	public Date getUploadDate() {
		return this.uploadDate;
	}

	public void setUploadDate(final Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	
	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	@Column(length=10000000)
	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	
	//Relationships
	private Curriculum curriculum;
	private SubSection subSection;
	private Tender tender;
	private TenderResult tenderResult;

	@Valid
	@ManyToOne(optional = true)
	public Curriculum getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}

	@Valid
	@ManyToOne(optional = true)	
	public SubSection getSubSection() {
		return subSection;
	}

	public void setSubSection(SubSection subSection) {
		this.subSection = subSection;
	}

	@Valid
	@ManyToOne(optional = true)	
	public Tender getTender() {
		return tender;
	}

	public void setTender(Tender tender) {
		this.tender = tender;
	}

	@Valid
	@ManyToOne(optional = true)	
	public TenderResult getTenderResult() {
		return tenderResult;
	}

	public void setTenderResult(TenderResult tenderResult) {
		this.tenderResult = tenderResult;
	}
	
	

}
