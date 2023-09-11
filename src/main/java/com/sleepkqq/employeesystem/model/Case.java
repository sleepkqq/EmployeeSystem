package com.sleepkqq.employeesystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "case_table")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private Date deadline;
    private int budget;
    private boolean active;
    @ManyToMany(mappedBy = "activeCases")
    private final Set<Developer> developers = new HashSet<>();

}
