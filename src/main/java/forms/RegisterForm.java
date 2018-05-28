
package forms;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import domain.Actor;

public class RegisterForm {

	private String		name;
	private String		surname;
	private String		email;
	private String		phone;			// Optional	
	private String		address;		// Optional	
	private String	    username;
	private String	    password;
	private String	    newPassword;
	private String	    confirmPassword;
	private String	    authority;
	
	private int id;
	private int version;


	//Constructors -------------------------

	public RegisterForm() {
		super();
		this.id = 0;
		this.version = 0;
		}


	public RegisterForm(Actor actor) {
		super();
		this.id = actor.getId();
		this.setName(actor.getName());
		this.setSurname(actor.getSurname());
		this.setEmail(actor.getEmail());
		this.setPhone(actor.getPhone());
		this.setAddress(actor.getAddress());
		this.setUsername(actor.getUserAccount().getUsername());
		this.setAuthority(actor.getUserAccount().getAuthorities().iterator().next().getAuthority());
	}


	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getAuthority() {
		return this.authority;
	}


	public void setAuthority(String authority) {
		this.authority = authority;
	}


	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	@Email
	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@Pattern(regexp = "()|^[6,7]\\d{8}$")
	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	@Size(min = 5, max = 32)
	public String getUsername() {
		return username;
	}


	public void setUsername(String userName) {
		this.username = userName;
	}


	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	@Size(min = 5, max = 32)
	public String getPassword() {
		return password;
	}


	public void setPassword(String pasword) {
		this.password = pasword;
	}
	
	
	/**
	 * @return the newPassword
	 */
	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Size(min = 5, max = 32)
	public String getNewPassword() {
		return newPassword;
	}


	/**
	 * @param newPassword the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * @return the confirmPassword
	 */
	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Size(min = 5, max = 32)
	public String getConfirmPassword() {
		return confirmPassword;
	}


	/**
	 * @param confirmPassword the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getVersion() {
		return version;
	}


	public void setVersion(int version) {
		this.version = version;
	}



	
	
	
}
