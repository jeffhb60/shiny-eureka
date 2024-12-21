package com.jeffhb60.backendshoppingcart.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "state")
@Getter
@Setter
@NoArgsConstructor
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "state_name")
    private String stateName;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

}
