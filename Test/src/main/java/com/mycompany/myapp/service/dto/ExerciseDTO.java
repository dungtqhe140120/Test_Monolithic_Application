package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Exercise} entity.
 */
public class ExerciseDTO implements Serializable {

    private Long id;

    private String title;

    private String short_description;

    private String description;

    private Integer status;

    private Integer created_by;

    private Integer updated_by;

    private LocalDate start_time;

    private LocalDate end_time;

    private String image_url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreated_by() {
        return created_by;
    }

    public void setCreated_by(Integer created_by) {
        this.created_by = created_by;
    }

    public Integer getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(Integer updated_by) {
        this.updated_by = updated_by;
    }

    public LocalDate getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalDate start_time) {
        this.start_time = start_time;
    }

    public LocalDate getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalDate end_time) {
        this.end_time = end_time;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExerciseDTO)) {
            return false;
        }

        ExerciseDTO exerciseDTO = (ExerciseDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, exerciseDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ExerciseDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", short_description='" + getShort_description() + "'" +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            ", created_by=" + getCreated_by() +
            ", updated_by=" + getUpdated_by() +
            ", start_time='" + getStart_time() + "'" +
            ", end_time='" + getEnd_time() + "'" +
            ", image_url='" + getImage_url() + "'" +
            "}";
    }
}
