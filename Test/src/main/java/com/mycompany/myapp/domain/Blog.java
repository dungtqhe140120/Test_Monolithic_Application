package com.mycompany.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Blog.
 */
@Entity
@Table(name = "blog")
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "short_description")
    private String short_description;

    @Column(name = "description")
    private String description;

    @Column(name = "context")
    private String context;

    @Column(name = "image_url")
    private String image_url;

    @Column(name = "created_by")
    private Integer created_by;

    @Column(name = "updated_by")
    private Integer updated_by;

    @Column(name = "display_order")
    private Integer display_order;

    @Column(name = "is_active")
    private Boolean is_active;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Blog id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public Blog title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShort_description() {
        return this.short_description;
    }

    public Blog short_description(String short_description) {
        this.setShort_description(short_description);
        return this;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getDescription() {
        return this.description;
    }

    public Blog description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContext() {
        return this.context;
    }

    public Blog context(String context) {
        this.setContext(context);
        return this;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getImage_url() {
        return this.image_url;
    }

    public Blog image_url(String image_url) {
        this.setImage_url(image_url);
        return this;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Integer getCreated_by() {
        return this.created_by;
    }

    public Blog created_by(Integer created_by) {
        this.setCreated_by(created_by);
        return this;
    }

    public void setCreated_by(Integer created_by) {
        this.created_by = created_by;
    }

    public Integer getUpdated_by() {
        return this.updated_by;
    }

    public Blog updated_by(Integer updated_by) {
        this.setUpdated_by(updated_by);
        return this;
    }

    public void setUpdated_by(Integer updated_by) {
        this.updated_by = updated_by;
    }

    public Integer getDisplay_order() {
        return this.display_order;
    }

    public Blog display_order(Integer display_order) {
        this.setDisplay_order(display_order);
        return this;
    }

    public void setDisplay_order(Integer display_order) {
        this.display_order = display_order;
    }

    public Boolean getIs_active() {
        return this.is_active;
    }

    public Blog is_active(Boolean is_active) {
        this.setIs_active(is_active);
        return this;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Blog)) {
            return false;
        }
        return id != null && id.equals(((Blog) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Blog{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", short_description='" + getShort_description() + "'" +
            ", description='" + getDescription() + "'" +
            ", context='" + getContext() + "'" +
            ", image_url='" + getImage_url() + "'" +
            ", created_by=" + getCreated_by() +
            ", updated_by=" + getUpdated_by() +
            ", display_order=" + getDisplay_order() +
            ", is_active='" + getIs_active() + "'" +
            "}";
    }
}
