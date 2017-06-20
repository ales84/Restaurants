package org.alesapps.votingsystem.to;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Anatoliy Kozhayev on 13.06.2017.
 */
public class UserTo extends BaseTo {

    @NotBlank
    private String name;

    @NotBlank
    @Length(min = 5, max = 32)
    private String password;

    public UserTo() {
    }

    public UserTo(Integer id, String name, String password) {
        super(id);
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserTo{" +
                "id=" + getId() +
                ", name='" + name +
                '}';
    }
}
