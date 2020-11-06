package com.example.report.service;

import com.example.report.model.Documento;
import com.example.report.model.Secao;
import com.example.report.model.Template;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SecaoService {

    private final ParagrafoService paragrafoService;

    public List<Secao> gerarSecoes(Documento documento, List<Template> templates){
        List<Secao> secoes = new ArrayList<>();
        long sessionsCount = 1;
        while(sessionsCount <= 9){
            secoes.add(Secao.builder()
                    .id((documento.getId()*10)+sessionsCount)
                    .documento(documento)
                    .titulo("Título Seção Número " + sessionsCount)
                    .texto(paragrafoService.gerarParagrafos(templates))
                    .build());
            sessionsCount++;
        }
        return secoes;
    }

    public void preencherSecao(XWPFDocument document, Secao secao){
        XWPFParagraph tituloSecao = document.createParagraph();
        tituloSecao.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun subTitleRun = tituloSecao.createRun();
        subTitleRun.setText(secao.getTitulo());
        subTitleRun.setFontFamily("Courier");
        subTitleRun.setFontSize(16);
        subTitleRun.setTextPosition(20);
        subTitleRun.setUnderline(UnderlinePatterns.DOT_DOT_DASH);

        XWPFParagraph paragrafoSecao = document.createParagraph();
        paragrafoSecao.setAlignment(ParagraphAlignment.BOTH);
        XWPFRun para1Run = paragrafoSecao.createRun();
        para1Run.setText(secao.getTexto());
    }
}
