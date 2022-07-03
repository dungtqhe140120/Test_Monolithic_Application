package com.mycompany.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Exercise.
 */
@Entity
@Table(name = "exercise")
public class Exercise implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "short_description", length = 100, nullable = false)
    private String short_description;

    @Size(min = 1, max = 2000)
    @Column(name = "full_description", length = 2000)
    private String full_description;

    @NotNull
    @Column(name = "create_by", nullable = false)
    private Integer create_by;

    @Column(name = "data_url")
    private String data_url;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Exercise id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShort_description() {
        return this.short_description;
    }

    public Exercise short_description(String short_description) {
        this.setShort_description(short_description);
        return this;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getFull_description() {
        return this.full_description;
    }

    public Exercise full_description(String full_description) {
        this.setFull_description(full_description);
        return this;
    }

    public void setFull_description(String full_description) {
        this.full_description = full_description;
    }

    public Integer getCreate_by() {
        return this.create_by;
    }

    public Exercise create_by(Integer create_by) {
        this.setCreate_by(create_by);
        return this;
    }

    public void setCreate_by(Integer create_by) {
        this.create_by = create_by;
    }

    public String getData_url() {
        return this.data_url;
    }

    public Exercise data_url(String data_url) {
        this.setData_url(data_url);
        return this;
    }

    public void setData_url(String data_url) {
        this.data_url = data_url;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Exercise)) {
            return false;
        }
        return id != null && id.equals(((Exercise) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Exercise{" +
            "id=" + getId() +
            ", short_description='" + getShort_description() + "'" +
            ", full_description='" + getFull_description() + "'" +
            ", create_by=" + getCreate_by() +
            ", data_url='" + getData_url() + "'" +
            "}";
    }
}
