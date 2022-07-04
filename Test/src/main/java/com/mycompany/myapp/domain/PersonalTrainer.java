package com.mycompany.myapp.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * A PersonalTrainer.
 */
@Entity
@Table(name = "personal_trainer")
public class PersonalTrainer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "profile_id")
    private Integer profile_id;

    @Column(name = "year_experience")
    private Float year_experience;

    @Column(name = "description")
    private String description;

    @Column(name = "facebook_url")
    private String facebook_url;

    @Column(name = "instagram_url")
    private String instagram_url;

    @Column(name = "join_date")
    private LocalDate join_date;

    @Column(name = "zalo_url")
    private String zalo_url;

    @Column(name = "hire_date")
    private LocalDate hire_date;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public PersonalTrainer id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProfile_id() {
        return this.profile_id;
    }

    public PersonalTrainer profile_id(Integer profile_id) {
        this.setProfile_id(profile_id);
        return this;
    }

    public void setProfile_id(Integer profile_id) {
        this.profile_id = profile_id;
    }

    public Float getYear_experience() {
        return this.year_experience;
    }

    public PersonalTrainer year_experience(Float year_experience) {
        this.setYear_experience(year_experience);
        return this;
    }

    public void setYear_experience(Float year_experience) {
        this.year_experience = year_experience;
    }

    public String getDescription() {
        return this.description;
    }

    public PersonalTrainer description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFacebook_url() {
        return this.facebook_url;
    }

    public PersonalTrainer facebook_url(String facebook_url) {
        this.setFacebook_url(facebook_url);
        return this;
    }

    public void setFacebook_url(String facebook_url) {
        this.facebook_url = facebook_url;
    }

    public String getInstagram_url() {
        return this.instagram_url;
    }

    public PersonalTrainer instagram_url(String instagram_url) {
        this.setInstagram_url(instagram_url);
        return this;
    }

    public void setInstagram_url(String instagram_url) {
        this.instagram_url = instagram_url;
    }

    public LocalDate getJoin_date() {
        return this.join_date;
    }

    public PersonalTrainer join_date(LocalDate join_date) {
        this.setJoin_date(join_date);
        return this;
    }

    public void setJoin_date(LocalDate join_date) {
        this.join_date = join_date;
    }

    public String getZalo_url() {
        return this.zalo_url;
    }

    public PersonalTrainer zalo_url(String zalo_url) {
        this.setZalo_url(zalo_url);
        return this;
    }

    public void setZalo_url(String zalo_url) {
        this.zalo_url = zalo_url;
    }

    public LocalDate getHire_date() {
        return this.hire_date;
    }

    public PersonalTrainer hire_date(LocalDate hire_date) {
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
        if (!(o instanceof PersonalTrainer)) {
            return false;
        }
        return id != null && id.equals(((PersonalTrainer) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PersonalTrainer{" +
            "id=" + getId() +
            ", profile_id=" + getProfile_id() +
            ", year_experience=" + getYear_experience() +
            ", description='" + getDescription() + "'" +
            ", facebook_url='" + getFacebook_url() + "'" +
            ", instagram_url='" + getInstagram_url() + "'" +
            ", join_date='" + getJoin_date() + "'" +
            ", zalo_url='" + getZalo_url() + "'" +
            ", hire_date='" + getHire_date() + "'" +
            "}";
    }
}
