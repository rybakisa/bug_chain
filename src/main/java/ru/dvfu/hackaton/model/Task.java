package ru.dvfu.hackaton.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by anton on 07.04.17.
 */
@Entity
@Table(name = "task")
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int count;

    @Getter
    @Setter
    private String ip;

    @Getter
    @Setter
    private int port;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Getter
    @Setter
    private float price;

    @Getter
    @Setter
    private String flag;
    @Getter
    @Setter
    private String address;
    @Getter
    @Setter
    @Column(columnDefinition = "TEXT")
    private String contractJson;

    @Getter
    @Setter
    private LocalDate timeFrom;

    @Getter
    @Setter
    private LocalDate timeTo;


    @Getter
    @Setter
    private int tries;

    @Getter
    @Setter
    private int successes;


    public Task(String name, String ip, int port, String description, float price, String flag, LocalDate from, LocalDate to, String address, String contractJson) {
        this.name = name;
        this.count = count;
        this.ip = ip;
        this.port = port;
        this.description = description;
        this.price = price;
        this.flag = flag;
        this.timeFrom = from;
        this.timeTo = to;
        this.address = address;
        this.contractJson = contractJson;
    }
}
