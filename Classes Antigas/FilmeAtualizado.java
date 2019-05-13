/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Relatorio;

import java.util.ArrayList;

/**
 *
 * @author miche
 */
public class FilmeAtualizado {
    private int id;
    private int idFilme;
    private String avaliacao;
    private ArrayList<Recibo> recibos;
    

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the filme
     */
    public int getIdFilme() {
        return idFilme;
    }

    /**
     * @param filme the filme to set
     */
    public void setIdFilme(int idFilme) {
        this.idFilme = idFilme;
    }

    /**
     * @return the avaliacao
     */
    public String getAvaliacao() {
        return avaliacao;
    }

    /**
     * @param avaliacao the avaliacao to set
     */
    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }
    
    public void imprimeFilme() {
        System.out.println("Id: " + getId()+ " Filme: " + this.idFilme + "Avaliacao " + this.avaliacao);
    }
    public ArrayList<Recibo> getRecibos() {
        return recibos;
    }

    public void setRecibos(ArrayList<Recibo> recibos) {
        this.recibos = recibos;
    }

    /**
     * Adiciona um novo Recibo ao Filme em questao
     *
     * @param recibo Recibo do gasto realizado pelo deputado
     */
    public void addRecibo(Recibo recibo) {
        this.recibos.add(recibo);
    }

    /**
     * Imprime todos os Recibos de um determinado Filme
     */
   public void imprimeRecibos() {
        for (int i = 0; i < recibos.size(); i++) {
            System.out.println(recibos.get(i).getGasto());
        }
    }
    
}
