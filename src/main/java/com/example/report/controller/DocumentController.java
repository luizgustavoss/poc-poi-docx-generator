package com.example.report.controller;


import com.example.report.service.GerarDocumentoService;
import com.example.report.service.SetupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DocumentController {

    private final GerarDocumentoService gerarDocumentoService;
    private final SetupService setupService;

    @PostMapping("/setup")
    @ResponseStatus(HttpStatus.OK)
    public void criarDocumentos() {
        setupService.setup();
    }

    @PostMapping("/documentos")
    @ResponseStatus(HttpStatus.OK)
    public void gerarLivros() {
        gerarDocumentoService.gerarDocumentos();
    }

    @PostMapping("/documento")
    @ResponseStatus(HttpStatus.OK)
    public void gerarLivro() {
        gerarDocumentoService.gerarDocumento();
    }
}
