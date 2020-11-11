package com.example.demo;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
//import javax.persistence.ManyToOne;
//import javax.persistence.JoinColumn;

@Entity
//@Table(name = "list_elements")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name="element_id")
    @Column(nullable = false)
    private Long id;

    //@Column(name="element_name")
    @Column(nullable = false)
    private String name;

    //@ManyToOne @JoinColumn(name="category_id", referencedColumnName = "category_id", nullable = true)
    //private Category category;

    //@Column(name="category_name")
    @Column(nullable = false)
    private String category;

    public Task() {
    }

    //public Element(Long id, String name, Category category) {
    public Task(Long id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //public Category getCategory() {
    public String getCategory() {
        return category;
    }

    //public void setCategory(Category category) {
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.name);
        /*
        if (this.category != null)
        {
            hash = 79 * hash + this.category.hashCode();
        }
        */
        hash = 79 * hash + Objects.hashCode(this.category);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Task other = (Task) obj;
        /*
        if (this.category == null) {
            if (other.getCategory() != null) {
                return false;
            }
        }
        else if (!this.category.equals(other.getCategory())) {
            return false;
        }
        */
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Task{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", category='").append(category).append('\'');
        /*
        sb.append(", category=");
        if (category == null) {
            sb.append("null");
        }
        else {
            sb.append(category.toString());
        }
        */
        sb.append('}');
        return sb.toString();
    }
}
