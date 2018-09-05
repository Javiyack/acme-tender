package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Entity
@Access(AccessType.PROPERTY)
public class TenderResult extends DomainEntity {

    private Date tenderDate;
    private String description;
    //Relationships
    private Tender tender;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @NotNull
    @Past
    public Date getTenderDate() {
        return this.tenderDate;
    }

    public void setTenderDate(final Date tenderDate) {
        this.tenderDate = tenderDate;
    }

    @SafeHtml(whitelistType = WhiteListType.NONE)
    @NotBlank
    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
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


}
