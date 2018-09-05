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
public class Comment extends DomainEntity {

    private String text;
    private Date writingDate;
    private Tender tender;
    private Commercial commercial;

    @SafeHtml(whitelistType = WhiteListType.NONE)
    @NotBlank
    public String getText() {
        return this.text;
    }

    public void setText(final String text) {
        this.text = text;
    }


    //Relationships

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @Past
    public Date getWritingDate() {
        return this.writingDate;
    }

    public void setWritingDate(final Date writingDate) {
        this.writingDate = writingDate;
    }

    @NotNull
    @ManyToOne(optional = false)
    @Valid
    public Tender getTender() {
        return this.tender;
    }

    public void setTender(final Tender tender) {
        this.tender = tender;
    }

    @NotNull
    @ManyToOne(optional = false)
    @Valid
    public Commercial getCommercial() {
        return this.commercial;
    }

    public void setCommercial(final Commercial commercial) {
        this.commercial = commercial;
    }

}
