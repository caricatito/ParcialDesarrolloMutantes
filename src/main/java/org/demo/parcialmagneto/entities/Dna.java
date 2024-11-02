package org.demo.parcialmagneto.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table (name = "ADN")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Dna implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(name="dna")
    private String dna;

    private boolean isMutant;
}
