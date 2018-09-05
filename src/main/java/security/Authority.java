/*
 * Authority.java
 *
 * Copyright (C) 2017 Universidad de Sevilla
 *
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package security;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Collection;

@Embeddable
@Access(AccessType.PROPERTY)
public class Authority implements GrantedAuthority {

    // Constructors -----------------------------------------------------------

    public static final String ADMIN = "ADMIN";
    public static final String COMMERCIAL = "COMMERCIAL";


    // Values -----------------------------------------------------------------
    public static final String ADMINISTRATIVE = "ADMINISTRATIVE";
    public static final String EXECUTIVE = "EXECUTIVE";
    private static final long serialVersionUID = 1L;
    private String authority;

    // Attributes -------------------------------------------------------------

    public Authority() {
        super();
    }

    public static Collection<Authority> listAuthorities() {
        Collection<Authority> result;
        Authority authority;

        result = new ArrayList<Authority>();

        authority = new Authority();
        authority.setAuthority(Authority.ADMIN);
        result.add(authority);

        authority = new Authority();
        authority.setAuthority(Authority.COMMERCIAL);
        result.add(authority);

        authority = new Authority();
        authority.setAuthority(Authority.ADMINISTRATIVE);
        result.add(authority);

        authority = new Authority();
        authority.setAuthority(Authority.EXECUTIVE);
        result.add(authority);

        return result;
    }

    @NotBlank
    @Pattern(regexp = "^" + Authority.ADMIN + "|" + Authority.COMMERCIAL + "|" + Authority.ADMINISTRATIVE + "|" + Authority.EXECUTIVE + "$")
    @Override
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(final String authority) {
        this.authority = authority;
    }

    // Object interface -------------------------------------------------------

    @Override
    public int hashCode() {
        return this.getAuthority().hashCode();
    }

    @Override
    public boolean equals(final Object other) {
        boolean result;

        if (this == other)
            result = true;
        else if (other == null)
            result = false;
        else if (!this.getClass().isInstance(other))
            result = false;
        else
            result = (this.getAuthority().equals(((Authority) other).getAuthority()));

        return result;
    }

    @Override
    public String toString() {
        return this.authority;
    }

}
