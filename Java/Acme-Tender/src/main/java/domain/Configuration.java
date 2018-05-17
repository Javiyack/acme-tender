
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Configuration extends DomainEntity {

	private String	banner;
	private String	welcomeMessageEs;
	private String	wecolmeMessageEn;
	private double benefitsPercentaje;

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	@URL
	public String getBanner() {
		return this.banner;
	}

	public void setBanner(final String banner) {
		this.banner = banner;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getWelcomeMessageEs() {
		return this.welcomeMessageEs;
	}

	public void setWelcomeMessageEs(final String welcomeMessageEs) {
		this.welcomeMessageEs = welcomeMessageEs;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getWecolmeMessageEn() {
		return this.wecolmeMessageEn;
	}

	public void setWecolmeMessageEn(final String wecolmeMessageEn) {
		this.wecolmeMessageEn = wecolmeMessageEn;
	}

	@Min(0)
	@Max(100)
	public double getBenefitsPercentaje() {
		return benefitsPercentaje;
	}

	public void setBenefitsPercentaje(double benefitsPercentaje) {
		this.benefitsPercentaje = benefitsPercentaje;
	}
	
	


}
