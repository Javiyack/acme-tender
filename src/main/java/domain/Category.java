package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Category extends DomainEntity {

    private String name;
    private String description;
    private Category fatherCategory;

    @SafeHtml(whitelistType = WhiteListType.NONE)
    @NotBlank
    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    //Relationships
    @SafeHtml(whitelistType = WhiteListType.NONE)
    @NotBlank
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Valid
    @ManyToOne(optional = true)
    public Category getFatherCategory() {
        return fatherCategory;
    }

    public void setFatherCategory(Category fatherCategory) {
        this.fatherCategory = fatherCategory;
    }


}
