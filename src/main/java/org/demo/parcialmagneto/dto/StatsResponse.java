package org.demo.parcialmagneto.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class StatsResponse {
    @JsonProperty("contador_mutante")
    private long countMutantDna;

    @JsonProperty("contador_humano")
    private long countHumanDna;

    private double ratio;
}
