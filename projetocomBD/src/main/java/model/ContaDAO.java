/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContaDAO {

    Connection conexao = null;

    public void transferencia(Conta conta1, Conta conta2, double valor) throws SQLException {
        try {
            conecta();
            try {
                //inicia o gerenciamento de transação
                conexao.setAutoCommit(false);
                //A partir deste ponto qualquer alteração no banco não
                //será mantida, até que o usuário de fato o commit
                String sql = "Update Conta set saldo = ? where idConta = ?";
                PreparedStatement comando = conexao.prepareStatement(sql);
                comando.setDouble(1, conta1.getSaldo() - 1500);
                comando.setInt(2, conta1.getIdConta());
                comando.executeUpdate();
                comando.setDouble(1, conta1.getSaldo() + 1500);
                comando.setInt(2, conta1.getIdConta());
                comando.executeUpdate();
                conexao.commit(); //passo todas as alterações temporarias para  o banco de fato
            } catch (SQLException e) {
                conexao.rollback(); //se alguma coisa foi feita temporariamente no banco deve ser desfeito
            } finally {
                conexao.setAutoCommit(true);

            }

        } catch (SQLException e) {
                throw e;
        }
    }

    public List<Conta> lista() throws SQLException {
        conecta();
        String sql = "Select * from Conta";

        PreparedStatement comando = conexao.prepareStatement(sql);

        ResultSet resultados = comando.executeQuery();

        //andar pelos resultados e formar o List
        //de contas
        List<Conta> contas = new ArrayList<Conta>();

        while (resultados.next()) {
            Conta conta = new Conta(resultados.getString(2), resultados.getString(3), resultados.getDouble(4));
            conta.setIdConta(resultados.getInt(1));
            contas.add(conta);

        }
        return contas;

    }

    public void carregaDriver() throws ClassNotFoundException {
        //instanciar o driver do sgbd que você usara
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    public void conecta() throws SQLException {
        conexao = null;
        conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/poo3", "root", "3443");
    }

    public void desconect() throws SQLException {
        conexao.close();
    }

    public void criaTabelaConta() throws ClassNotFoundException, SQLException {
        carregaDriver();
        conecta();
        String sql
                = "create table if not exists Conta( idConta int primary key auto_increment, "
                + "numConta varchar(255), "
                + "numAg varchar(255), "
                + "saldo double)";

        Statement comando = conexao.createStatement();
        comando.executeUpdate(sql);
        desconect();
    }

    public static void main(String[] args) {
        /* ContaDAO dao = new ContaDAO();
        try {
            dao.criaTabelaConta();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }*/

        ContaDAO dao = new ContaDAO();
        try {
            dao.carregaDriver();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            dao.cadastraConta(new Conta("001", "0001", 123456));
            System.out.println(dao.lista() + "asdasd");
        } catch (SQLException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cadastraConta(Conta conta) throws SQLException {
        conecta(); //abrir conexão
        String dados = " ?,?,? ";
        String sql = ""
                + "insert into Conta(numAg, numConta, saldo) "
                + "values (" + dados + ") ";
        PreparedStatement comando = conexao.prepareStatement(sql);
        comando.setString(1, conta.getNumAg());
        comando.setString(2, conta.getNumConta());
        comando.setDouble(3, conta.getSaldo());

        // executar a operação
        comando.executeUpdate();
        desconect();
    }
}
