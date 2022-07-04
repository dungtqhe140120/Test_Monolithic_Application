package com.mycompany.myapp.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Employee.
 */
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "profile_id", nullable = false)
    private Integer profile_id;

    @NotNull
    @Column(name = "hire_date", nullable = false)
    private LocalDate hire_date;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Employee id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProfile_id() {
        return this.profile_id;
    }

    public Employee profile_id(Integer profile_id) {
        this.setProfile_id(profile_id);
        return this;
    }

    public void setProfile_id(Integer profile_id) {
        this.profile_id = profile_id;
    }

    public LocalDate getHire_date() {
        return this.hire_date;
    }

    public Employee hire_date(LocalDate hire_date) {
        this.setHire_date(hire_date);
        return this;
    }

    public void setHire_date(LocalDate hire_date) {
        this.hire_date = hire_date;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        return id != null && id.equals(((Employee) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Employee{" +
            "id=" + getId() +
            ", profile_id=" + getProfile_id() +
            ", hire_date='" + getHire_date() + "'" +
            "}";
    }
}
