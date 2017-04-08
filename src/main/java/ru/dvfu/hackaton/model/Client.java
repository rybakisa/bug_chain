package ru.dvfu.hackaton.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by anton on 07.04.17.
 */
@Entity
@Table(name = "client")
@AllArgsConstructor
public class Client {

    @Getter
    @Id
    @Setter
    private String email;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Task> tasks;


    public Client() {
        this.email = "";
        this.password = "";
        this.tasks = new HashSet<Task>();
    }

    public Client(String email, String password) {
        this.email = email;
        this.password = password;
        this.tasks = new HashSet<Task>();
    }

    public Client(String email, String password, String description, String name) {
        this.email = email;
        this.password = password;
        this.description = description;
        this.name = name;
        this.tasks = new HashSet<Task>();

    }
}
