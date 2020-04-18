package com.cadastro.cliente.api.domain.user;

import com.cadastro.cliente.api.domain.machine.Machine;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "User")
@EnableAutoConfiguration
public class User implements Serializable {

    private static final long serialVersionUID = 3096302027142979826L;

    @Id
    @NotNull
    private String login;

    @Column(name="name")
    @NotNull
    private String name;

    @Column(name="user_is_admin")
    @NotNull
    private boolean userIsAdmin;

    @ManyToMany(mappedBy = "userAcess")
    private Set<Machine> machines = new HashSet<>();

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isUserIsAdmin() {
        return userIsAdmin;
    }

    public void setUserIsAdmin(boolean userIsAdmin) {
        this.userIsAdmin = userIsAdmin;
    }

    public Set<Machine> getMachines() {
        return machines;
    }

    public void setMachines(Set<Machine> machines) {
        this.machines = machines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return login.equals(user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }
}
