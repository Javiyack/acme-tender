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
public class Answer extends DomainEntity {

    private String text;
    private Date writingDate;
    private Comment comment;
    private Actor actor;

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

    @Valid
    @NotNull
    @ManyToOne(optional = false)
    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Valid
    @NotNull
    @ManyToOne(optional = false)
    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }


}
