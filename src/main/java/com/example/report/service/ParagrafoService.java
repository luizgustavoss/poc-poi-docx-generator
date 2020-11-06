package com.example.report.service;

import com.example.report.model.Template;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParagrafoService {

    public String gerarParagrafos(List<Template> templates){

        long paragraphsCount = 1;
        StringBuilder textoParagrafos = new StringBuilder();

        while(paragraphsCount <= 20){
            templates.stream().forEach( template -> textoParagrafos.append(template.getParagrafo()).append("\n") );
            paragraphsCount++;
        }
        return textoParagrafos.toString();
    }
}
