package Filmes;

import Algoritmos.ListaEncadeada;
import java.util.ArrayList;

/**
 *
 * @author miche
 */
public class Usuario {
    private int idFilme;
    private double avaliacaoMedia = 0;
    private ListaEncadeada <Avaliacao> listaAvaliacao;
   
    public Usuario(int id, int idFilme, double avaliacao) {      
        this.idFilme = idFilme;
        Avaliacao av = new Avaliacao(id,avaliacao);
        this.listaAvaliacao = new ListaEncadeada<>();
        this.listaAvaliacao.insereFinal(av);     
    }
        
    public void addAvaliacao(Avaliacao avaliacao){
        this.listaAvaliacao.insereFinal(avaliacao);
    }
    
    public int getQuantidadeUsuarios(){
        return listaAvaliacao.getTamanho();
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
    
    public double getAvaliacaoMedia(){
        if(avaliacaoMedia==0){
            return getAvaliacaoMediaCalc();
        } else {
            return avaliacaoMedia;
        }
    }
    
    private double getAvaliacaoMediaCalc(){
        this.avaliacaoMedia = 0;
        for(int i=0;i<this.listaAvaliacao.getTamanho();i++){
            this.avaliacaoMedia = this.listaAvaliacao.retornaInfo(i).getNota();
        }
        
        this.avaliacaoMedia = this.avaliacaoMedia/this.listaAvaliacao.getTamanho();
        
        return this.avaliacaoMedia;
    }

    public ListaEncadeada <Avaliacao> getListaAvaliacao() {
        return listaAvaliacao;
    }

    public void setListaAvaliacao(ListaEncadeada <Avaliacao> listaAvaliacao) {
        this.listaAvaliacao = listaAvaliacao;
    }
    
    
    
}