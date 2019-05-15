/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filmes;

/**
 *
 * @author Carcara
 */
public class Avaliacao {
    
    private int idFilme;
    private double nota;

    public Avaliacao(int idFilme, double nota) {
        this.idFilme = idFilme;
        this.nota = nota;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public int getIdUsuario() {
        return idFilme;
    }

    public void setIdUsuario(int idFilme) {
        this.idFilme = idFilme;
    }
    
    
    
}
