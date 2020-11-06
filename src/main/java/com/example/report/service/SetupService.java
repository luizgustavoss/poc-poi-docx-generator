package com.example.report.service;

import com.example.report.model.Template;
import com.example.report.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SetupService {

    private final TemplateRepository templateRepository;
    private final DocumentoService documentoService;

    public void setup(){
        long documentsCount = 1;

        List<Template> templates = templateRepository.findAll();

        while(documentsCount <= 20){
            documentoService.gerarDocumentos(documentsCount, templates);
            documentsCount++;
        }
    }
}
