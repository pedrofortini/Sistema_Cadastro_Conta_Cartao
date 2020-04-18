package com.cadastro.cliente.api.domain.machine;


import com.cadastro.cliente.api.domain.user.User;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Machine")
@EnableAutoConfiguration
public class Machine implements Serializable {

    private static final long serialVersionUID = 2787308952488235560L;

    @Id
    @GeneratedValue
    @NotNull
    private Long id;

    @Column(name="name")
    @NotNull
    private String name;

    @Column(name="machine_is_up")
    @NotNull
    private boolean machineIsUp;

    @Column(name="ip_address")
    @NotNull
    private String ipAddress;

    @Column(name="last_downtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastDownTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="admin_user", nullable = false)
    @NotNull
    private User adminUser;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_acess",
            joinColumns = {@JoinColumn(name = "machine_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<User> userAcess = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMachineIsUp() {
        return machineIsUp;
    }

    public void setMachineIsUp(boolean machineIsUp) {
        this.machineIsUp = machineIsUp;
    }

    public Date getLastDownTime() {
        return lastDownTime;
    }

    public void setLastDownTime(Date lastDownTime) {
        this.lastDownTime = lastDownTime;
    }

    public User getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(User adminUser) {
        this.adminUser = adminUser;
    }

    public Set<User> getUserAcess() {
        return userAcess;
    }

    public void setUserAcess(Set<User> userAcess) {
        this.userAcess = userAcess;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
