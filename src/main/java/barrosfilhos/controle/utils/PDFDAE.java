/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barrosfilhos.controle.utils;





import barrosfilhos.controle.utilsArquivo.ArquivoDae;
import barrosfilhos.controle.model.PdfDae;
import barrosfilhos.controle.process.ProcessarDae;
import static barrosfilhos.controle.utilsArquivo.ArquivoDae.PATHSFOLDERS;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

/**
 *
 * @author Michel
 */
public class PDFDAE {

    public static List<PdfDae> LerDAE() throws IOException {
 List<PdfDae> lista = new ArrayList<>();
        try {
            List<File> listFiles = null;
            for (String pasta : PATHSFOLDERS) {
                listFiles = ArquivoDae.justPDFFiles(pasta);

                for (File f : listFiles) {
                    PDDocument document = PDDocument.load(f);

                    document.getClass();

                    if (!document.isEncrypted()) {

                        PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                        stripper.setSortByPosition(true);

                        PDFTextStripper tStripper = new PDFTextStripper();

                        String pdfFileInText = tStripper.getText(document);
                        //System.out.println("Text:" + st);

                        // split by whitespace
                        String lines[] = pdfFileInText.split("\\r?\\n");
                        int i = 0;
                        List<String> conteudoPDF = new ArrayList<>();
                        System.out.println("---------------" + f.getName() + "--------------------");
                        for (String line : lines) {
                            System.out.println(i + " - " + line);
                            conteudoPDF.add(line);
                            i++;
                        }
                        i--;
                        if (i == 36) {
                            System.out.println("36");
                            boolean itsOK = ProcessarDae.pdfDae36Linhas(conteudoPDF);
                            document.close();
                            if (itsOK) {
                                ArquivoDae.moveFile(f);
                            }
                        } 
                    }
                    System.out.println("-----------------------------------");
                }
            }
            // Arquivo.moveFile(listFiles);
        } catch (IOException e) {
            System.out.println("ERRO readRows " + e.getMessage());
        }
        return lista;
    }

}