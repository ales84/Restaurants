package org.alesapps.votingsystem.model;

import org.hibernate.Hibernate;

import javax.persistence.*;

/**
 * Created by Anatoliy Kozhayev on 17.04.2017.
 */
@MappedSuperclass
@Access(AccessType.FIELD)
public class BaseEntity {
    public static final int START_SEQ = 1;

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @Access(AccessType.PROPERTY)
    private Integer id;

    protected BaseEntity() {
    }

    protected BaseEntity(Integer id) {
        this.id = id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public boolean isNew() {
        return (getId() == null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(Hibernate.getClass(o))) {
            return false;
        }
        BaseEntity that = (BaseEntity) o;
        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId() : 0;
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s)", getClass().getName(), getId());
    }
}
