package com.example.demo;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "task_categories",
            joinColumns = {
                    @JoinColumn(name = "task_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "category_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Set<Category> categories = new HashSet<>();

    public Task() {
    }

    //public Element(Long id, String name, Category category) {
    public Task(Long id, String name, Set<Category> categories) {
        this.id = id;
        this.name = name;
        this.categories = categories;
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

    public Set<Category> getCategories() {
        return categories;
    }

    //public void setCategory(Category category) {
    public void setCategory(Set<Category> categories) {
        this.categories = categories;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.categories);
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
        if (!Objects.equals(this.categories, other.categories)) {
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
        sb.append(", categories=");
        if (this.categories.isEmpty()) {
            sb.append("null");
        } else {
            int i = 0;
            for (Category category : this.categories) {
                if (i > 0) {
                    sb.append(",");
                }
                sb.append(category.toString());
                ++i;
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public boolean hasCategory(Category category) {
        if (this.categories.isEmpty()) {
            return false;
        }

        for (Category itCategory : this.categories) {
            if (category.getName().equals(itCategory.getName())) {
                return true;
            }
        }
        return false;
    }
}
