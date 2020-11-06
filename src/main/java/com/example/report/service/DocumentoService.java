package com.example.report.service;

import com.example.report.model.Documento;
import com.example.report.model.Template;
import com.example.report.repository.DocumentoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentoService {

    private final SecaoService secaoService;
    private final DocumentoRepository documentoRepository;

    public void gerarDocumentos(long id, List<Template> templates){
        Documento documento = Documento.builder()
                .id(id)
                .titulo("Título Documento Número " + id).build();
        documento.adicionarSecoes(secaoService.gerarSecoes(documento, templates));

        documentoRepository.save(documento);
    }

    public void criarDocumento(XWPFDocument document, Documento documento) throws IOException {

        XWPFParagraph title = document.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);

        XWPFRun titleRun = title.createRun();
        titleRun.setText(documento.getTitulo());
        titleRun.setBold(true);
        titleRun.setFontFamily("Courier");
        titleRun.setFontSize(20);

        documento.getSecoes().stream().forEach( secao -> secaoService.preencherSecao(document, secao));
    }

    public void criarDocumento(Documento documento) throws IOException {

        File file = new File( "/Users/luiz.souza/temp/poc/" + documento.getId() + "-" + new Date().getTime() + ".docx");

        XWPFDocument document = new XWPFDocument();

        XWPFParagraph title = document.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);

        XWPFRun titleRun = title.createRun();
        titleRun.setText(documento.getTitulo());
        titleRun.setBold(true);
        titleRun.setFontFamily("Courier");
        titleRun.setFontSize(20);

        documento.getSecoes().stream().forEach( secao -> secaoService.preencherSecao(document, secao));

        FileOutputStream out = new FileOutputStream(file);

        document.write(out);
        out.close();
        document.close();
    }
}
