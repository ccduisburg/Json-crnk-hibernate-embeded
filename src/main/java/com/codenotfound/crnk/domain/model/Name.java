package com.codenotfound.crnk.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
@Getter
@Setter
public class Name {
    @Size(max=4)
    private String prefix;

    @NotNull
    @Size(max = 40)
    private String lastname;

    private String vorname;
    private String mittlename;
    private String vorzeichen;

    public Name() {
    }

    public Name(String prefix, @NotNull String lastname, String vorname, String mittlename, String vorzeichen) {
        this.prefix = prefix;
        this.lastname = lastname;
        this.vorname = vorname;
        this.mittlename = mittlename;
        this.vorzeichen = vorzeichen;
    }

    public Name(@NotNull String lastname, String vorname) {
        this.lastname = lastname;
        this.vorname = vorname;
    }
}
