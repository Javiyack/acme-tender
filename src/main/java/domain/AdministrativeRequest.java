package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Access(AccessType.PROPERTY)
public class AdministrativeRequest extends DomainEntity {

    private String subSectionTitle;
    private String subSectionDescription;
    private Date maxAcceptanceDate;
    private Date maxDeliveryDate;
    private Boolean accepted;
    private String rejectedReason;
    // Relationships
    private Administrative administrative;
    private Offer offer;

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

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull
    @Future
    public Date getMaxAcceptanceDate() {
        return this.maxAcceptanceDate;
    }

    public void setMaxAcceptanceDate(final Date maxAcceptanceDate) {
        this.maxAcceptanceDate = maxAcceptanceDate;
    }

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull
    @Future
    public Date getMaxDeliveryDate() {
        return this.maxDeliveryDate;
    }

    public void setMaxDeliveryDate(final Date maxDeliveryDate) {
        this.maxDeliveryDate = maxDeliveryDate;
    }

    public Boolean getAccepted() {
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

    @Valid
    @NotNull
    @ManyToOne(optional = false)
    public Administrative getAdministrative() {
        return administrative;
    }

    public void setAdministrative(Administrative administrative) {
        this.administrative = administrative;
    }

    @Valid
    @NotNull
    @ManyToOne(optional = false)
    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }


    @Transient
    public boolean isAnswerable() {

        if (this.getMaxAcceptanceDate() == null)
            return false;

        return this.getMaxAcceptanceDate().after(new Date());
    }

    public void setAnswerable(final boolean answerable) {

    }

}
