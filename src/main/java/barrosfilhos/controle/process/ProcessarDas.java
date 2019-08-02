/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barrosfilhos.controle.process;


import barrosfilhos.controle.dao.PdfDasDAO;
import barrosfilhos.controle.model.PdfDas;
import java.util.List;

/**
 *
 * @author Michel
 */
public class ProcessarDas {
public static boolean pdfDas6Impostos(List<String> conteudoPDF) {
        /*
        linhas para ler
        Nome da documentação    29
        cnpjDas                 2   
        apuracaoDas             15
        vencimentoDas           6
        valorDas                9
      
         */
        PdfDas p = new PdfDas();
        p.setCompararLinha(conteudoPDF.get(29));
        if (p.getCompararLinha().contains("Documento de Arrecadação do Simples Nacional")) {
        p.setCnpjDas(conteudoPDF.get(2).substring(0, 18));
        p.setApuracaoDas(conteudoPDF.get(15));
        p.setVencimentoDas(conteudoPDF.get(6));
        p.setValorDas(conteudoPDF.get(9));
        
        boolean itsOK = verificarTabelaPdfDas(p);

        if (itsOK) {
            PdfDasDAO dao = new PdfDasDAO();
            dao.inserirPdfDas(p);
        } else {
            System.out.println("Já tem.");
        }
        return itsOK;
    }
        return false;
        
    }
    public static boolean pdfDas5Impostos(List<String> conteudoPDF) {
        /*
        linhas para ler
        Nome da documentação    27
        cnpjDas                 2    
        apuracaoDas             15
        vencimentoDas           6
        valorDas                9
      
         */
        PdfDas p = new PdfDas();
        p.setCompararLinha(conteudoPDF.get(27));
        if (p.getCompararLinha().contains("Documento de Arrecadação do Simples Nacional")) {
        p.setCnpjDas(conteudoPDF.get(2).substring(0, 18));
        p.setApuracaoDas(conteudoPDF.get(15));
        p.setVencimentoDas(conteudoPDF.get(6));
        p.setValorDas(conteudoPDF.get(9));
        
        boolean itsOK = verificarTabelaPdfDas(p);

        if (itsOK) {
            PdfDasDAO dao = new PdfDasDAO();
            dao.inserirPdfDas(p);
        } else {
            System.out.println("Já tem.");
        }
        return itsOK;
    }
        return false;
        
    }
    
    
    
    private static boolean verificarTabelaPdfDas(PdfDas p) {
        boolean itsOK = false;
        PdfDasDAO dao = new PdfDasDAO();
        List<PdfDas> pdfsdas = dao.verificarTabelaPdfDas(p);
        if (pdfsdas.isEmpty()) {
            itsOK = true;
        }
        return itsOK;
    }

   
}
