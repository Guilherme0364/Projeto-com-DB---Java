/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Conta {
    private int idConta;
    private String numConta;
    private String numAg;
    private double saldo;

    public Conta(String numConta, String numAg, double saldo) {
        this.numConta = numConta;
        this.numAg = numAg;
        this.saldo = saldo;
    }
    
    public Conta(){
    }    

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }
        
    public String getNumConta() {
        return numConta;
    }

    public void setNumConta(String numConta) {
        this.numConta = numConta;
    }

    public String getNumAg() {
        return numAg;
    }

    public void setNumAg(String numAg) {
        this.numAg = numAg;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Conta{" + "numConta=" + numConta + ", numAg=" + numAg + ", saldo=" + saldo + '}';
    }
    
    
}
