/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barrosfilhos.controle.process;


import barrosfilhos.controle.dao.PdfDaeSefazDAO;
import barrosfilhos.controle.model.PdfDaeSefaz;
import java.util.List;

/**
 *
 * @author Michel
 */
public class ProcessarDaeSefaz {

    public static boolean pdfDaeSefaz2imp49linhas(List<String> conteudoPDF) {
        /*
        linhas para ler
        Nome da documentação    2
        estadualDaeSefaz        4    
        documentoDaeSefaz       6
        validadeDaeSefaz        8
        imp01DaeSefaz           25
        imp02DaeSefaz           31
        valorDaeSefaz           10
         */
        PdfDaeSefaz p = new PdfDaeSefaz();
        
        p.setEstadualDaeSefaz(conteudoPDF.get(4));
        p.setDocDaeSefaz(conteudoPDF.get(6));
        p.setValidadeDaeSefaz(conteudoPDF.get(8));
        p.setImp01DaeSefaz(conteudoPDF.get(25));
        p.setImp02DaeSefaz(conteudoPDF.get(31));
        p.setValorDaeSefaz(conteudoPDF.get(10));
        
        boolean itsOK = verificarTabelaPdfDas(p);

        if (itsOK) {
            PdfDaeSefazDAO dao = new PdfDaeSefazDAO();
            dao.inserirPdfDaeSefaz(p);
        } else {
            System.out.println("Já tem.");
        }
        return itsOK;
   
    }
    
    public static boolean pdfDaeSefaz2imp48linhas(List<String> conteudoPDF) {
        /*
        linhas para ler
        Nome da documentação    4
        estadualDaeSefaz        6    
        documentoDaeSefaz       8
        validadeDaeSefaz        10
        imp01DaeSefaz           27
        imp02DaeSefaz           33
        valorDaeSefaz           12
         */
        PdfDaeSefaz p = new PdfDaeSefaz();
        p.setEstadualDaeSefaz(conteudoPDF.get(6));
        p.setDocDaeSefaz(conteudoPDF.get(8));
        p.setValidadeDaeSefaz(conteudoPDF.get(10));
        p.setImp01DaeSefaz(conteudoPDF.get(27));
        p.setImp02DaeSefaz(conteudoPDF.get(33));
        p.setValorDaeSefaz(conteudoPDF.get(12));
        
        boolean itsOK = verificarTabelaPdfDas(p);

        if (itsOK) {
            PdfDaeSefazDAO dao = new PdfDaeSefazDAO();
            dao.inserirPdfDaeSefaz(p);
        } else {
            System.out.println("Já tem.");
        }
        return itsOK;
    
    }
     public static boolean pdfDaeSefaz1imp(List<String> conteudoPDF) {
        /*
        linhas para ler
        Nome da documentação    2
        estadualDaeSefaz        4    
        documentoDaeSefaz       6
        validadeDaeSefaz        8
        imp01DaeSefaz           25
        imp02DaeSefaz           null
        valorDaeSefaz           10
         */
        PdfDaeSefaz p = new PdfDaeSefaz();
         p.setEstadualDaeSefaz(conteudoPDF.get(4));
        p.setDocDaeSefaz(conteudoPDF.get(6));
        p.setValidadeDaeSefaz(conteudoPDF.get(8));
        p.setImp01DaeSefaz(conteudoPDF.get(25));
        p.setValorDaeSefaz(conteudoPDF.get(10));
        
        boolean itsOK = verificarTabelaPdfDas(p);

        if (itsOK) {
            PdfDaeSefazDAO dao = new PdfDaeSefazDAO();
            dao.inserirPdfDaeSefaz(p);
        } else {
            System.out.println("Já tem.");
        }
        return itsOK;
    }
      
    
    
    
    private static boolean verificarTabelaPdfDas(PdfDaeSefaz p) {
        boolean itsOK = false;
        PdfDaeSefazDAO dao = new PdfDaeSefazDAO();
        List<PdfDaeSefaz> pdfsdaesefaz = dao.verificarTabelaPdfDaeSefaz(p);
        if (pdfsdaesefaz.isEmpty()) {
            itsOK = true;
        }
        return itsOK;
    }

   
}
