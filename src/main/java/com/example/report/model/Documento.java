package com.example.report.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="documento")
public class Documento {

    @Id
    private Long id;

    private String titulo;

    @OneToMany(mappedBy = "documento", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("documento")
    private List<Secao> secoes;


    public void adicionarSecoes(List<Secao> secoes){
        secoes.stream().forEach(this::adicionarSecao);
    }

    public void adicionarSecao(Secao secao){
        if(secoes == null)
            secoes = new ArrayList<>();
        secoes.add(secao);
    }
}
