/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projetocombd;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProjetocomBD {

    public static void main(String[] args){
        
        try {
            //iniciar o driver de conversao para o banco
            Class.forName("com.mysql.cj.jdbc.driver");
            DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "3443");
            //para acessar um banco de dados:
            //passos obrigatorios
            //definir qual o database:
            //definir qual o usuario:
            //definir qual a senha:
                
        System.out.println("Executando...");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProjetocomBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProjetocomBD.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
}
