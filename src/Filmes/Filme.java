package Filmes;

import java.util.ArrayList;

/**
 *
 * @author miche
 */
public class Filme {
    private int idFilme;
    private ArrayList <Double> avaliacao;
    private double avaliacaoMedia;
    private ArrayList <Integer> listaUsuarios;
   

    public Filme(int id, int idFilme, double avaliacao) {
        
        this.idFilme = idFilme;
        this.avaliacao = new ArrayList<>();
        this.avaliacao.add(avaliacao);
        this.listaUsuarios = new ArrayList<>();
        this.listaUsuarios.add(id);
    }
        
    public void addUsuario(int valor){
        this.listaUsuarios.add(valor);
    }
    
    public int getQuantidadeUsuarios(){
        return listaUsuarios.size();
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


    public void addAvaliacao(double avaliacao){
        this.avaliacao.add(avaliacao);
    }
    
 
    public double getAvaliacao(){
        this.avaliacaoMedia = 0;
        for(int i=0;i<avaliacao.size();i++){
            this.avaliacaoMedia = this.avaliacao.get(i);
        }
        
        this.avaliacaoMedia = this.avaliacaoMedia/this.avaliacao.size();
        
        return this.avaliacaoMedia;
    }
    
    
    
}