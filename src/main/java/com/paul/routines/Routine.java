package com.paul.routines;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "routines")
public class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long clientId;              // relación lógica con clients-service

    @NotBlank
    private String name;

    @Column(length = 4000)
    private String plan;                // descripción/JSON del plan

    public Routine() {}

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getClientId() { return clientId; }
    public void setClientId(Long clientId) { this.clientId = clientId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPlan() { return plan; }
    public void setPlan(String plan) { this.plan = plan; }
}
