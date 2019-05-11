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
}