package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.PersonalTrainer} entity.
 */
public class PersonalTrainerDTO implements Serializable {

    private Long id;

    private Integer profile_id;

    private Float year_experience;

    private String description;

    private String facebook_url;

    private String instagram_url;

    private LocalDate join_date;

    private String zalo_url;

    private LocalDate hire_date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(Integer profile_id) {
        this.profile_id = profile_id;
    }

    public Float getYear_experience() {
        return year_experience;
    }

    public void setYear_experience(Float year_experience) {
        this.year_experience = year_experience;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFacebook_url() {
        return facebook_url;
    }

    public void setFacebook_url(String facebook_url) {
        this.facebook_url = facebook_url;
    }

    public String getInstagram_url() {
        return instagram_url;
    }

    public void setInstagram_url(String instagram_url) {
        this.instagram_url = instagram_url;
    }

    public LocalDate getJoin_date() {
        return join_date;
    }

    public void setJoin_date(LocalDate join_date) {
        this.join_date = join_date;
    }

    public String getZalo_url() {
        return zalo_url;
    }

    public void setZalo_url(String zalo_url) {
        this.zalo_url = zalo_url;
    }

    public LocalDate getHire_date() {
        return hire_date;
    }

    public void setHire_date(LocalDate hire_date) {
        this.hire_date = hire_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PersonalTrainerDTO)) {
            return false;
        }

        PersonalTrainerDTO personalTrainerDTO = (PersonalTrainerDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, personalTrainerDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PersonalTrainerDTO{" +
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
