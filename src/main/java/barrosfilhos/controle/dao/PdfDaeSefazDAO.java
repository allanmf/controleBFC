/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barrosfilhos.controle.dao;

import barrosfilhos.controle.conf.Conexao;
import barrosfilhos.controle.conf.TratamentoConexao;
import barrosfilhos.controle.model.PdfDaeSefaz;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Michel
 */
public class PdfDaeSefazDAO {

    Connection con = null;

    public PdfDaeSefazDAO() {
        con = Conexao.conectar();
    }

    public List<PdfDaeSefaz> verificarTabelaPdfDaeSefaz(PdfDaeSefaz p) {
        List<PdfDaeSefaz> pdfsdaesefaz = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `pdf_daesefaz` "
                    + "where estadualDaeSefaz = ? "
                    + "and docDaeSefaz = ? "
                    + "and validadeDaeSefaz = ? "
                    + "and valorDaeSefaz = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getEstadualDaeSefaz());
            ps.setString(2, p.getDocDaeSefaz());
            ps.setString(3, p.getValidadeDaeSefaz());
            ps.setString(4, p.getValorDaeSefaz());
            ResultSet rs = ps.executeQuery();
            pdfsdaesefaz = correrTabela(rs);
        } catch (SQLException e) {
            System.out.println("Erro loadEmpresas " + e.getMessage());
        }
        return pdfsdaesefaz;
    }

    private List<PdfDaeSefaz> correrTabela(ResultSet rs) {
        List<PdfDaeSefaz> pdfsdaesefaz = new ArrayList<>();
        try {
            while (rs.next()) {

                int idDaeSefaz = rs.getInt("idDaeSefaz");
                String estadualDaeSefaz = rs.getString("estadualDaeSefaz");
                String docDaeSefaz = rs.getString("docDaeSefaz");
                String validadeDaeSefaz = rs.getString("validadeDaeSefaz");
                String imp01DaeSefaz = rs.getString("imp01DaeSefaz");
                String imp02DaeSefaz = rs.getString("imp02DaeSefaz");
                String valorDaeSefaz = rs.getString("valorDaeSefaz");
                
                PdfDaeSefaz p = new PdfDaeSefaz();
                p.setIdDaeSefaz(idDaeSefaz);
                p.setEstadualDaeSefaz(estadualDaeSefaz);
                p.setDocDaeSefaz(docDaeSefaz);
                p.setValidadeDaeSefaz(validadeDaeSefaz);
                p.setImp01DaeSefaz(imp01DaeSefaz);
                p.setImp02DaeSefaz(imp02DaeSefaz);
                p.setValorDaeSefaz(valorDaeSefaz);
                
                pdfsdaesefaz.add(p);

            }
        } catch (SQLException e) {
            System.out.println("Erro correrTabela pdfsdaesefaz " + e.getMessage());
        } finally {
            TratamentoConexao.fecharConexaoEResultSet(con, rs);
        }
        return pdfsdaesefaz;
    }//correr_tabela

    public boolean inserirPdfDaeSefaz(PdfDaeSefaz pdfdaesefaz) {
        boolean itsOK = false;
        try {
            String sql = "insert into pdf_daesefaz ( estadualDaeSefaz, docDaeSefaz, "
                    + "validadeDaeSefaz, imp01DaeSefaz,imp02DaeSefaz,valorDaeSefaz,) "
                    + "values (?, ?, ?, ?,?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, pdfdaesefaz.getEstadualDaeSefaz());
            ps.setString(2, pdfdaesefaz.getDocDaeSefaz());
            ps.setString(3, pdfdaesefaz.getValidadeDaeSefaz());
            ps.setString(4, pdfdaesefaz.getImp01DaeSefaz());
            if(pdfdaesefaz.getImp02DaeSefaz()!=null){
            ps.setString(5, pdfdaesefaz.getImp02DaeSefaz());    
            }else{
             ps.setString(5,null);
            }
            ps.setString(6, pdfdaesefaz.getValorDaeSefaz());
            ps.execute();
            itsOK = true;
        } catch (SQLException e) {
            System.out.println("Erro salvar inserirPdfDaeSefaz " + e.getMessage());
        } finally {
            TratamentoConexao.fecharConexao(con);
        }
        return itsOK;
    }
}
