
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
public class Curriculum extends DomainEntity {

	private String	name;
	private String	surname;
	private String	identificationNumber;
	private String	phone;
	private String	email;
	private Date	dateOfBirth;
	private String	text;
	private Double	minSalaryExpectation;


	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Pattern(regexp = "")
	public String getIdentificationNumber() {
		return this.identificationNumber;
	}

	public void setIdentificationNumber(final String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	@Email
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	@Past
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(final Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getText() {
		return this.text;
	}

	public void setText(final String text) {
		this.text = text;
	}

	@Min(0)
	public Double getMinSalaryExpectation() {
		return this.minSalaryExpectation;
	}

	public void setMinSalaryExpectation(final Double minSalaryExpectation) {
		this.minSalaryExpectation = minSalaryExpectation;
	}
	
	//Relationships
	
	private SubSection subsection;

	@ManyToOne(optional = false)
	public SubSection getSubSection() {
		return subsection;
	}

	public void setSubSection(SubSection subsection) {
		this.subsection = subsection;
	}
	

}
