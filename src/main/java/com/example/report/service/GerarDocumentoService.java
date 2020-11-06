package com.example.report.service;

import com.example.report.model.Documento;
import com.example.report.repository.DocumentoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GerarDocumentoService {

    private final DocumentoRepository documentoRepository;
    private final DocumentoService documentoService;

    public void gerarDocumento() {

        List<Documento> documentos = documentoRepository.findAll();

        File file = new File( "/Users/luiz.souza/temp/poc/" + new Date().getTime() + ".docx");

        XWPFDocument document = new XWPFDocument();

        documentos.stream().forEach(documento -> {
            try {
                documentoService.criarDocumento(document, documento);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);

            document.write(out);
            out.close();
            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gerarDocumentos() {

        List<Documento> documentos = documentoRepository.findAll();
        documentos.stream().forEach(documento -> {
            try {
                documentoService.criarDocumento(documento);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
