/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmos;

import Relatorio.RelatorioOrdenacao;
import java.io.Serializable;

/**
 *
 * @author Carcara
 */
public class ListaCont<Class> implements Serializable {

    private int max;
    private int tamanho;
    private Class[] vetor;

    public ListaCont(int max) {
        this.max = max;
        this.tamanho = 0;
        this.vetor = (Class[]) new Object[max];
    }

    public void adicionar(Class elemento) {
        if (tamanho() == vetor.length) {
            redimensionar();
        }
        vetor[tamanho()] = elemento;
        tamanho++;
    }

    public void insereFinal(Class valor){
        this.vetor[tamanho]=valor;
        tamanho++;
    }
    
    public Class get(int posicao) {
        if (vazio() || posicao < 0 || posicao >= tamanho()) {
            System.out.println("Posição fora da faixa permitida");
        }
        return vetor[posicao];
    }

    public void set(int index, Class valor) {
        if (vazio() || index < 0 || index >= tamanho()) {
            System.out.println("Posição fora da faixa permitida");
        }
        this.vetor[index] = valor;
    }

    public int procurar(Class elemento) {
        for (int i = 0; i < tamanho; i++) {
            if (vetor[i].equals(elemento)) {
                return i;
            }
        }
        return -1;
    }

    public boolean removerElemento(Class elemento) {
        return remover(procurar(elemento));
    }

    public boolean remover(int posicao) {
        if (!vazio() && posicao >= 0 && posicao < tamanho()) {
            for (int i = posicao; i < tamanho; i++) {
                vetor[i] = vetor[i + 1];
            }
            vetor[tamanho()] = null;
            tamanho--;
            return true;
        } else {
            return false;
        }
    }

    public boolean removerInicio() {
        if (vazio()) {
            return false;
        }
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = vetor[i + 1];
        }
        tamanho--;
        return true;
    }

    public boolean removerFim() {
        if (vazio()) {
            return false;
        }
        vetor[tamanho()] = null;
        tamanho--;
        return true;
    }

    public int tamanho() {
        return tamanho;
    }

    public boolean vazio() {
        return tamanho() == 0;
    }

    public void limpar() {
        vetor = (Class[]) new Comparable[4];
    }

    private void redimensionar() {
        Class[] novoVetor = (Class[]) new Comparable[vetor.length * 2];
        if (vetor.length == tamanho()) {
            for (int i = 0; i < vetor.length; i++) {
                novoVetor[i] = vetor[i];
            }
            vetor = novoVetor;
        }
    }

    public int getMax() {
        return max;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public Class[] getVetor() {
        return vetor;
    }

    public void setVetor(Class[] vetor) {
        this.vetor = vetor;
    }

    public void troca(int index1, int index2, RelatorioOrdenacao relatorio) {
        Class aux = vetor[index1];
        vetor[index1] = vetor[index2];
        vetor[index2] = aux;

        relatorio.incrementaCopia();
        relatorio.incrementaCopia();
        relatorio.incrementaCopia();
    }
    
    public void deletarLista(){
        this.tamanho = 0;
    }
}
