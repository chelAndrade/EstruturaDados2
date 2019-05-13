/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmos;

import Relatorio.Filme;
import Relatorio.Relatorio;

/**
 *
 * @author miche
 */
public class Ordenacao {
    
    /**
     * Esta funcao contem o codigo para execucao do algoritmo do Insertion Sort
     *
     * @param filmes Lista com os Filmes a serem ordenados
     * @param relatorio Acesso ao relatorio para gravar os dados de analise
     */
    public static void insertionSort(ListaEncadeada<Filme> filmes, Relatorio relatorio) {
        insertionSort(filmes, 0, filmes.getTamanho(), relatorio);
    }

    private static void insertionSort(ListaEncadeada<Filme> filmes, int min, int max, Relatorio relatorio) {
        int contIteracao = 1;

        for (int i = min + 1; i < max; i++) {
            contIteracao++;
            Filme chave = filmes.retornaInfo(i);
            int j = i - 1;

            while (j >= 0 && filmes.retornaInfo(j).getTotalGasto() > chave.getTotalGasto()) {
                contIteracao++;
                filmes.altera(j + 1, filmes.retornaInfo(j));
                j = j - 1;
            }
            contIteracao++;
            filmes.altera((j + 1), chave);
            relatorio.incrementaTrocaColisaoCopia();
        }
        contIteracao++;
        relatorio.setInteracao((relatorio.getInteracao() + contIteracao));
    }
     /**
     * Esta funcao contem o codigo para execucao do algoritmo Merge Sort
     *
     * @param filmes Lista com os Filmes a serem ordenados
     * @param relatorio Acesso ao relatorio para gravar os dados de analise
     */
    //Chamada da funcao, calcula os parametros necessarios para execucao
    public static void mergeSort(ListaEncadeada<Filme> filmes, Relatorio relatorio) {
        mergeSort(filmes, 0, filmes.getTamanho() - 1, relatorio);
    }

    //Funcao de chamada recursiva do algoritmo Merge Sort
    private static void mergeSort(ListaEncadeada<Filme> filmes, int esq, int dir, Relatorio relatorio) {
        int contInteracao = 1;
        if (esq < dir) {
            // Encontra o termo do meio
            int meio = (esq + dir) / 2;
            // Ordena primeira e segunda metade
            mergeSort(filmes, esq, meio, relatorio);
            mergeSort(filmes, meio + 1, dir, relatorio);
            // Junta as metades
            merge(filmes, esq, meio, dir, relatorio);
        }
        contInteracao++;
        relatorio.setInteracao((relatorio.getInteracao() + contInteracao));
    }

    //Funcao principal do algoritmo Merge Sort
    private static void merge(ListaEncadeada<Filme> filmes, int esq, int meio, int dir, Relatorio relatorio) {
        // Encontra os tamanhos dos dois sub arrays para serem mesclados
        int n1 = meio - esq + 1;
        int n2 = dir - meio;
        int contInteracao = 1;

        // Cria os arrays temporarios
        ListaEncadeada<Filme> esqArray = new ListaEncadeada();
        ListaEncadeada<Filme> dirArray = new ListaEncadeada();
        // Copia os dados para o novo array
        for (int i = 0; i < n1; ++i) {
            contInteracao++;
            esqArray.insereFinal(filmes.retornaInfo(esq + i));
        }
        contInteracao++;
        for (int j = 0; j < n2; ++j) {
            contInteracao++;
            dirArray.insereFinal(filmes.retornaInfo(meio + 1 + j));
        }
        contInteracao++;
        // Junta os arrays temporarios
        // Indice inicial dos sub arrays
        int i = 0, j = 0;
        // Indice inicial dos sub arrays
        int k = esq;
        while (i < n1 && j < n2) {
            contInteracao++;
            if (esqArray.retornaInfo(i).getTotalGasto() <= dirArray.retornaInfo(j).getTotalGasto()) {
                filmes.altera(k, esqArray.retornaInfo(i));
                contInteracao++;
                i++;
            } else {
                filmes.altera(k, dirArray.retornaInfo(j));
                contInteracao++;
                j++;
            }
            relatorio.incrementaTrocaColisaoCopia();
            contInteracao++;
            k++;
        }
        contInteracao++;
        // Copia os elementos do vetor esq se houver
        while (i < n1) {
            contInteracao++;
            filmes.altera(k, esqArray.retornaInfo(i));
            contInteracao++;
            //deputados[k] = esqVet[i];
            i++;
            k++;
        }
        contInteracao++;
        // Copia os elementos do vetor dir se houver
        while (j < n2) {
            contInteracao++;
            filmes.altera(k, dirArray.retornaInfo(j));
            contInteracao++;
            j++;
            k++;
        }
        relatorio.incrementaTrocaColisaoCopia();
        contInteracao++;
        relatorio.setInteracao((relatorio.getInteracao() + contInteracao));
    }
    /**
     * Esta funcao contem o codigo para execucao do algoritmo de Heap Sort
     *
     * @param filmes Lista com os Filmes a serem ordenados
     * @param relatorio Acesso ao relatorio para gravar os dados de analise
     */
    //Chamada da funcao principal
    public static void heapSort(ListaEncadeada<Filme> filmes, Relatorio relatorio) {
        int n = filmes.getTamanho();
        // Constroi a Heap
        int contInteracao = 0;
        for (int i = n / 2 - 1; i >= 0; i--) {
            contInteracao++;
            maxHeap(filmes, n, i, relatorio);
        }
        contInteracao++;
        // Extrai um elemento por vez da heap
        for (int i = n - 1; i >= 0; i--) {
            contInteracao++;
            // Move a raiz para o fim
            filmes.troca(0, i, relatorio);
            // MaxHeap a heap reduzida
            maxHeap(filmes, i, 0, relatorio);
        }
        contInteracao++;
        relatorio.setInteracao((relatorio.getInteracao() + contInteracao));
    }

    //Funcao principal do algoritmo de Heap Sort
    private static void maxHeap(ListaEncadeada<Filme> filmes, int n, int i, Relatorio relatorio) {
        int maior = i;  // Inicializa o maior como raiz
        int esq = 2 * i + 1;
        int dir = 2 * i + 2;
        int contInteracao = 0;

        // Se filho da esq é maior que a raiz
        if (esq < n && ((filmes.retornaInfo(esq).getTotalGasto() > filmes.retornaInfo(maior).getTotalGasto()))) {
            maior = esq;
        }
        contInteracao++;
        // Se filho da dir é maior que a raiz
        if (dir < n && ((filmes.retornaInfo(dir).getTotalGasto() > filmes.retornaInfo(maior).getTotalGasto()))) {
            maior = dir;
        }
        contInteracao++;
        // Se maior nao e raiz
        if (maior != i) {
            filmes.troca(i, maior, relatorio);
            // MaxHeap recursivamente a sub arvore afetada
            maxHeap(filmes, n, maior, relatorio);
        }
        contInteracao++;
        relatorio.setInteracao((relatorio.getInteracao() + contInteracao));
    }
    
}
