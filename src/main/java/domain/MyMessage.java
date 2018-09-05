package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {
        @Index(columnList = "subject, body")
})
public class MyMessage extends DomainEntity {

    private Date moment;
    private String subject;
    private String body;
    private String priority;
    private boolean broadcast;
    //Relationships
    private Actor recipient;
    private Actor sender;

    //Constructor
    public MyMessage() {
        super();
    }

    public MyMessage(final MyMessage copy) {
        this.subject = copy.getSubject();
        this.moment = copy.getMoment();
        this.body = copy.getBody();
        this.priority = copy.getPriority();
        this.recipient = copy.getRecipient();
        this.sender = copy.getSender();
    }

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    public Date getMoment() {
        return this.moment;
    }

    public void setMoment(final Date moment) {
        this.moment = moment;
    }

    @NotBlank
    @SafeHtml
    public String getSubject() {
        return this.subject;
    }

    public void setSubject(final String subject) {
        this.subject = subject;
    }

    @NotBlank
    @SafeHtml
    public String getBody() {
        return this.body;
    }

    public void setBody(final String body) {
        this.body = body;
    }

    @NotBlank
    @SafeHtml
    @Pattern(regexp = "HIGH|NEUTRAL|LOW")
    public String getPriority() {
        return this.priority;
    }

    public void setPriority(final String priority) {
        this.priority = priority;
    }

    @Valid
    @NotNull
    @ManyToOne(optional = false)
    public Actor getRecipient() {
        return this.recipient;
    }

    public void setRecipient(final Actor recipient) {
        this.recipient = recipient;
    }

    @Valid
    @NotNull
    @ManyToOne(optional = false)
    public Actor getSender() {
        return this.sender;
    }

    public void setSender(final Actor sender) {
        this.sender = sender;
    }

    public boolean getBroadcast() {
        return this.broadcast;
    }

    public void setBroadcast(final boolean broadcast) {
        this.broadcast = broadcast;
    }
}
