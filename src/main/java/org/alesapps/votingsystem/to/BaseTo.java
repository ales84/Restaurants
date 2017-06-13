package org.alesapps.votingsystem.to;

/**
 * Created by Anatoliy Kozhayev on 13.06.2017.
 */
public class BaseTo {

    private Integer id;

    protected BaseTo() {
    }

    protected BaseTo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return (getId() == null);
    }
}
