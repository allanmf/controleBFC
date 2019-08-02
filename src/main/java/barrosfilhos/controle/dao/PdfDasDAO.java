/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barrosfilhos.controle.dao;

import barrosfilhos.controle.conf.Conexao;
import barrosfilhos.controle.conf.TratamentoConexao;
import barrosfilhos.controle.model.PdfDas;
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
public class PdfDasDAO {

    Connection con = null;

    public PdfDasDAO() {
        con = Conexao.conectar();
    }

    public List<PdfDas> verificarTabelaPdfDas(PdfDas p) {
        List<PdfDas> pdfsdas = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `pdf_das` "
                    + "where cnpjDas = ? "
                    + "and apuracaoDas = ? "
                    + "and vencimentoDas = ? "
                    + "and valorDas = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getCnpjDas());
            ps.setString(2, p.getApuracaoDas());
            ps.setString(3, p.getVencimentoDas());
            ps.setString(4, p.getValorDas());
            ResultSet rs = ps.executeQuery();
            pdfsdas = correrTabela(rs);
        } catch (SQLException e) {
            System.out.println("Erro loadEmpresas " + e.getMessage());
        }
        return pdfsdas;
    }

    private List<PdfDas> correrTabela(ResultSet rs) {
        List<PdfDas> pdfsdas = new ArrayList<>();
        try {
            while (rs.next()) {

                int idDas = rs.getInt("idDas");
                String cnpjDas = rs.getString("cnpjDas");
                String apuracaoDas = rs.getString("apuracaoDas");
                String vencimentoDas = rs.getString("vencimentoDas");
                String valorDas = rs.getString("valorDas");
                
                PdfDas p = new PdfDas();
                p.setIdDas(idDas);
                p.setCnpjDas(cnpjDas);
                p.setApuracaoDas(apuracaoDas);
                p.setVencimentoDas(vencimentoDas);
                p.setValorDas(valorDas);
                
                pdfsdas.add(p);

            }
        } catch (SQLException e) {
            System.out.println("Erro correrTabela pdfsdas " + e.getMessage());
        } finally {
            TratamentoConexao.fecharConexaoEResultSet(con, rs);
        }
        return pdfsdas;
    }//correr_tabela

    public boolean inserirPdfDas(PdfDas pdfdas) {
        boolean itsOK = false;
        try {
            String sql = "insert into pdf_das ( cnpjDas, apuracaoDas, "
                    + "vencimentoDas,valorDas) "
                    + "values (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, pdfdas.getCnpjDas());
            ps.setString(2, pdfdas.getApuracaoDas());
            ps.setString(3, pdfdas.getVencimentoDas());
            ps.setString(4, pdfdas.getValorDas());
            ps.execute();
            itsOK = true;
        } catch (SQLException e) {
            System.out.println("Erro salvar inserirPdfDas " + e.getMessage());
        } finally {
            TratamentoConexao.fecharConexao(con);
        }
        return itsOK;
    }
}
