package com.sleepkqq.employeesystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    @Column(unique = true)
    private String email;
    private int salary;
    private Position position;
    @ManyToMany
    @JoinTable(name = "developer_case",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "case_id"))
    private final Set<Case> activeCases = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "developer_case",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "case_id"))
    private final Set<Case> finishedCases = new HashSet<>();

    public Developer(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        position = Position.JUNIOR;
        salary = 0;
    }

}
