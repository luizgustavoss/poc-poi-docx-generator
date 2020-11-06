package com.example.report.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity(name="template")
public class Template {

    @Id
    private Long id;

    private String paragrafo;
}
