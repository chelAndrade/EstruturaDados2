/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmos;

import Relatorio.Filme;
import Relatorio.Relatorio;

/**
 * Implementacao dos algoritmos de ordenacao usados nos Filmes 
 */
public class QuickSort {
    
 public static void quickSortRec(ListaEncadeada<Filme> filmes, Relatorio relatorio) {
        quickSortRec(filmes, 0, filmes.getTamanho() - 1, relatorio);
    }

    //Executa a funcao recursivamente em todos os vetores apos as particoes
    private static void quickSortRec(ListaEncadeada<Filme> filmes, int min, int max, Relatorio relatorio) {
        int contInteracao = 1;
        if (min < max) {
            contInteracao++;
            //define o indice da particao (ip)
            int ip = particionaQuickSortRec(filmes, min, max, relatorio);
            // Recusividade para ordenar os elementos antes e depois da particao
            quickSortRec(filmes, min, ip - 1, relatorio);
            quickSortRec(filmes, ip + 1, max, relatorio);
        }
        contInteracao++;
        relatorio.setInteracao((relatorio.getInteracao() + contInteracao));
    }

    //Cria as particoes para o algoritmo
    private static int particionaQuickSortRec(ListaEncadeada<Filme> filmes, int min, int max, Relatorio relatorio) {
        int contInteracao = 1;
        //Define o pivo como o maior da lista
        Filme pivo = filmes.retornaInfo(max);
        int i = (min - 1); // indice do menor elemento
        for (int j = min; j < max; j++) {
            contInteracao++;
            //Compara as strings
            if (filmes.retornaInfo(j).getTotalGasto() < pivo.getTotalGasto()) {
                i++;//avanca o menor
                filmes.troca(i, j, relatorio);
            }
            contInteracao++;
        }
        contInteracao++;
        //Filme temp = filmes.retornaInfo(i + 1);
        filmes.troca(i + 1, max, relatorio);
        relatorio.setInteracao((relatorio.getInteracao() + contInteracao));
        return i + 1;
    }
    
    //Chamada da funcao, calcula os parametros necessarios para execucao
    public static void quicksortMedianaK(ListaEncadeada<Filme> filmes, int k, Relatorio relatorio) {
        quicksortMedianaK(filmes, 0, filmes.getTamanho() - 1, k, relatorio);
    }
    
    //Método de partição do Quick Sort Mediana de 3
    private static int particionaMedianaK(ListaEncadeada<Filme> filmes, int inicio, int fim, int k, Relatorio relatorio) {
        //procura a mediana entre inicio, meio e fim
        float div = ((float) (fim - inicio)) / (float) (k - 1);
        float aux = 0;
        int[] indice = new int[k];
        int contInteracao = 1;
        for (int i = 0; i < k; i++) {
            contInteracao++;
            indice[i] = (int) aux;
            aux = aux + div;
        }
        contInteracao++;

        int n = indice.length;
        int temp = 0;
        for (int i = 0; i < n; i++) {
            contInteracao++;
            for (int j = 1; j < (n - i); j++) {
                contInteracao++;
                if (filmes.retornaInfo(indice[j - 1]).getTotalGasto() > filmes.retornaInfo(indice[j]).getTotalGasto()) {
                    //swap elements  
                    temp = indice[j - 1];
                    indice[j - 1] = indice[j];
                    indice[j] = temp;

                }
                contInteracao++;
            }
            contInteracao++;
        }
        contInteracao++;
        int medianaIndice = (inicio + fim) / 2;
        filmes.troca(medianaIndice, fim, relatorio);

        //o pivo é o elemento final
        Filme pivo = filmes.retornaInfo(fim);
        int i = inicio - 1;

        for (int j = inicio; j <= fim - 1; j++) {
            contInteracao++;
            if (filmes.retornaInfo(j).getTotalGasto() <= pivo.getTotalGasto()) {
                i = i + 1;
                filmes.troca(i, j, relatorio);
                contInteracao++;
            }
            contInteracao++;
        }
        contInteracao++;
        //coloca o pivô na posição de ordenação
        filmes.troca(i + 1, fim, relatorio);
        relatorio.setInteracao((relatorio.getInteracao() + contInteracao));
        return i + 1; //retorna a posição do pivô
    }
    
     //Funcao principal do algoritmo, executa a recursao
    private static void quicksortMedianaK(ListaEncadeada<Filme> filmes, int inicio, int fim, int k, Relatorio relatorio) {
        int contInteracao = 1;
        if (inicio < fim) {
            //realiza a partição
            int q = particionaMedianaK(filmes, inicio, fim, k, relatorio);
            //ordena a partição esquerda
            quicksortMedianaK(filmes, inicio, q - 1, k, relatorio);
            //ordena a partição direita
            quicksortMedianaK(filmes, q + 1, fim, k, relatorio);
        }
        contInteracao++;
        relatorio.setInteracao((relatorio.getInteracao() + contInteracao));

    }
}