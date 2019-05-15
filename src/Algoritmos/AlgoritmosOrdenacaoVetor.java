/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmos;

import Filmes.Usuario;
import Relatorio.RelatorioOrdenacao;
import java.util.ArrayList;
import java.util.List;


public class AlgoritmosOrdenacaoVetor {
    
    public static void bubbleSort(ListaCont <Usuario> listaUsuarios, RelatorioOrdenacao relatorio) {
        int contComparacao = 0;

        for (int i = 0; i < listaUsuarios.getTamanho(); i++) {
            contComparacao++;
            for (int j = 1; j < listaUsuarios.getTamanho() - i; j++) {
                contComparacao++;
                if (listaUsuarios.get(j).getIdUsuario() < listaUsuarios.get(j-1).getIdUsuario()) {
                    listaUsuarios.troca(j, j - 1, relatorio);

                    contComparacao++;
                }
                contComparacao++;
            }
            contComparacao++;
        }
        contComparacao++;
        relatorio.setComparacao((relatorio.getComparacao() + contComparacao));
        relatorio.finaliza();
    }

    public static void insertionSort(ListaCont<Usuario> listaUsuarios, RelatorioOrdenacao relatorio) {
        insertionSort(listaUsuarios, 0, listaUsuarios.getTamanho(), relatorio);
        relatorio.finaliza();
    }

    private static void insertionSort(ListaCont<Usuario> listaUsuarios, int min, int max, RelatorioOrdenacao relatorio) {
        int contComparacao = 1;

        for (int i = min + 1; i < max; i++) {
            contComparacao++;
            Usuario chave = listaUsuarios.get(i);
            int j = i - 1;

            while (j >= 0 && listaUsuarios.get(j).getIdUsuario() > chave.getIdUsuario()) {
                contComparacao++;
                listaUsuarios.set(j + 1, listaUsuarios.get(j));
                j = j - 1;
            }
            contComparacao++;
            listaUsuarios.set((j + 1), chave);
            relatorio.incrementaCopia();
        }
        contComparacao++;
        relatorio.setComparacao((relatorio.getComparacao() + contComparacao));
    }

    public static void mergeSort(ListaCont<Usuario> listaUsuarios, RelatorioOrdenacao relatorio) {
        mergeSort(listaUsuarios, 0, listaUsuarios.getTamanho() - 1, relatorio);
        relatorio.finaliza();
    }

    private static void mergeSort(ListaCont<Usuario> listaUsuarios, int esq, int dir, RelatorioOrdenacao relatorio) {
        long contInteracao = 1;
        if (esq < dir) {
            // Encontra o termo do meio
            int meio = (esq + dir) / 2;
            // Ordena primeira e segunda metade
            mergeSort(listaUsuarios, esq, meio, relatorio);
            mergeSort(listaUsuarios, meio + 1, dir, relatorio);
            // Junta as metades
            merge(listaUsuarios, esq, meio, dir, relatorio);
        }
        contInteracao++;
        relatorio.setComparacao((relatorio.getComparacao() + contInteracao));
    }

    private static void merge(ListaCont<Usuario> listaUsuarios, int esq, int meio, int dir, RelatorioOrdenacao relatorio) {
        // Encontra os tamanhos dos dois sub arrays para serem mesclados
        int n1 = meio - esq + 1;
        int n2 = dir - meio;
        int contInteracao = 1;

        // Cria os arrays temporarios
        ListaCont<Usuario> esqArray = new ListaCont(listaUsuarios.getTamanho()/2 +1);
        ListaCont<Usuario> dirArray = new ListaCont(listaUsuarios.getTamanho()/2 +1);
        // Copia os dados para o novo array
        for (int i = 0; i < n1; ++i) {
            contInteracao++;
            esqArray.insereFinal(listaUsuarios.get(esq + i));
        }
        contInteracao++;
        for (int j = 0; j < n2; ++j) {
            contInteracao++;
            dirArray.insereFinal(listaUsuarios.get(meio + 1 + j));
        }
        contInteracao++;
        // Junta os arrays temporarios
        // Indice inicial dos sub arrays
        int i = 0, j = 0;
        // Indice inicial dos sub arrays
        int k = esq;
        while (i < n1 && j < n2) {
            contInteracao++;
            if (esqArray.get(i).getIdUsuario() <= dirArray.get(j).getIdUsuario()) {
                listaUsuarios.set(k, esqArray.get(i));
                contInteracao++;
                i++;
            } else {
                listaUsuarios.set(k, dirArray.get(j));
                contInteracao++;
                j++;
            }
            relatorio.incrementaCopia();
            contInteracao++;
            k++;
        }
        contInteracao++;
        // Copia os elementos do vetor esq se houver
        while (i < n1) {
            contInteracao++;
            listaUsuarios.set(k, esqArray.get(i));
            contInteracao++;
            //listaUsuarios[k] = esqVet[i];
            i++;
            k++;
        }
        contInteracao++;
        // Copia os elementos do vetor dir se houver
        while (j < n2) {
            contInteracao++;
            listaUsuarios.set(k, dirArray.get(j));
            contInteracao++;
            j++;
            k++;
        }
        relatorio.incrementaCopia();
        contInteracao++;
        relatorio.setComparacao((relatorio.getComparacao() + contInteracao));
    }

    public static void quickSortRec(ListaCont<Usuario> listaUsuarios, RelatorioOrdenacao relatorio) {
        quickSortRec(listaUsuarios, 0, listaUsuarios.getTamanho() - 1, relatorio);
        relatorio.finaliza();
    }

    private static void quickSortRec(ListaCont<Usuario> listaUsuarios, int min, int max, RelatorioOrdenacao relatorio) {
        int contInteracao = 1;
        if (min < max) {
            contInteracao++;
            //define o indice da particao (ip)
            int ip = particionaQuickSortRec(listaUsuarios, min, max, relatorio);
            // Recusividade para ordenar os elementos antes e depois da particao
            quickSortRec(listaUsuarios, min, ip - 1, relatorio);
            quickSortRec(listaUsuarios, ip + 1, max, relatorio);
        }
        contInteracao++;
        relatorio.setComparacao((relatorio.getComparacao() + contInteracao));
    }

    //Cria as particoes para o algoritmo
    private static int particionaQuickSortRec(ListaCont<Usuario> listaUsuarios, int min, int max, RelatorioOrdenacao relatorio) {
        int contInteracao = 1;
        //Define o pivo como o maior da lista
        Usuario pivo = listaUsuarios.get(max);
        int i = (min - 1); // indice do menor elemento
        for (int j = min; j < max; j++) {
            contInteracao++;
            //Compara as strings
            if (listaUsuarios.get(j).getIdUsuario() < pivo.getIdUsuario()) {
                i++;//avanca o menor
                listaUsuarios.troca(i, j, relatorio);
            }
            contInteracao++;
        }
        contInteracao++;
        //Filme temp = listaUsuarios.get(i + 1);
        listaUsuarios.troca(i + 1, max, relatorio);
        relatorio.setComparacao((relatorio.getComparacao() + contInteracao));
        return i + 1;
    }


    public static void quicksortMedianaK(ListaCont<Usuario> listaUsuarios, int k, RelatorioOrdenacao relatorio) {
        quicksortMedianaK(listaUsuarios, 0, listaUsuarios.getTamanho() - 1, k, relatorio);
        relatorio.finaliza();
    }

    //Método de partição do Quick Sort Mediana de 3
    private static int particionaMedianaK(ListaCont<Usuario> listaUsuarios, int inicio, int fim, int k, RelatorioOrdenacao relatorio) {
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
                if (listaUsuarios.get(indice[j - 1]).getIdUsuario() > listaUsuarios.get(indice[j]).getIdUsuario()) {
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
        listaUsuarios.troca(medianaIndice, fim, relatorio);

        //o pivo é o elemento final
        Usuario pivo = listaUsuarios.get(fim);
        int i = inicio - 1;

        for (int j = inicio; j <= fim - 1; j++) {
            contInteracao++;
            if (listaUsuarios.get(j).getIdUsuario() <= pivo.getIdUsuario()) {
                i = i + 1;
                listaUsuarios.troca(i, j, relatorio);
                contInteracao++;

            }
            contInteracao++;
        }
        contInteracao++;
        //coloca o pivô na posição de ordenação
        listaUsuarios.troca(i + 1, fim, relatorio);
        relatorio.setComparacao((relatorio.getComparacao() + contInteracao));
        return i + 1; //retorna a posição do pivô
    }

    //Funcao principal do algoritmo, executa a recursao
    private static void quicksortMedianaK(ListaCont<Usuario> listaUsuarios, int inicio, int fim, int k, RelatorioOrdenacao relatorio) {
        int contInteracao = 1;
        if (inicio < fim) {
            //realiza a partição
            int q = particionaMedianaK(listaUsuarios, inicio, fim, k, relatorio);
            //ordena a partição esquerda
            quicksortMedianaK(listaUsuarios, inicio, q - 1, k, relatorio);
            //ordena a partição direita
            quicksortMedianaK(listaUsuarios, q + 1, fim, k, relatorio);
        }
        contInteracao++;
        relatorio.setComparacao((relatorio.getComparacao() + contInteracao));

    }

    public static void quickSortHibrido(ListaCont<Usuario> listaUsuarios, int k, RelatorioOrdenacao relatorio) {
        quickSortHibrido(listaUsuarios, 0, listaUsuarios.getTamanho() - 1, k, relatorio);
        relatorio.finaliza();
    }

    private static void quickSortHibrido(ListaCont<Usuario> listaUsuarios, int min, int max, int k, RelatorioOrdenacao relatorio) {
        int size = (max + 1) - min;
        int contInteracao = 1;
        if (size <= k) { // inserion sort se o tamanho for menor que 10
            insertionSort(listaUsuarios, min, size, relatorio);
        } else // quicksort se for maior
        {
            int pivo = particionaQuickSortRec(listaUsuarios, min, max, relatorio);
            quickSortRec(listaUsuarios, min, pivo - 1, relatorio);
            quickSortRec(listaUsuarios, pivo + 1, max, relatorio);
        }
        contInteracao++;
        relatorio.setComparacao((relatorio.getComparacao() + contInteracao));
    }


    public static void heapSort(ListaCont<Usuario> listaUsuarios, RelatorioOrdenacao relatorio) {
        int n = listaUsuarios.getTamanho();
        // Constroi a Heap
        int contInteracao = 0;
        for (int i = n / 2 - 1; i >= 0; i--) {
            contInteracao++;
            heapify(listaUsuarios, n, i, relatorio);
        }
        contInteracao++;
        // Extrai um elemento por vez da heap
        for (int i = n - 1; i >= 0; i--) {
            contInteracao++;
            // Move a raiz para o fim
            listaUsuarios.troca(0, i, relatorio);
            // heapify a heap reduzida
            heapify(listaUsuarios, i, 0, relatorio);
        }
        contInteracao++;
        relatorio.setComparacao((relatorio.getComparacao() + contInteracao));
        relatorio.finaliza();
    }

    //Funcao principal do algoritmo de Heap Sort
    private static void heapify(ListaCont<Usuario> listaUsuarios, int n, int i, RelatorioOrdenacao relatorio) {
        int maior = i;  // Inicializa o maior como raiz
        int esq = 2 * i + 1;
        int dir = 2 * i + 2;
        int contInteracao = 0;

        // Se filho da esq é maior que a raiz
        if (esq < n && ((listaUsuarios.get(esq).getIdUsuario() > listaUsuarios.get(maior).getIdUsuario()))) {
            maior = esq;
        }
        contInteracao++;
        // Se filho da dir é maior que a raiz
        if (dir < n && ((listaUsuarios.get(dir).getIdUsuario() > listaUsuarios.get(maior).getIdUsuario()))) {
            maior = dir;
        }
        contInteracao++;
        // Se maior nao e raiz
        if (maior != i) {
            listaUsuarios.troca(i, maior, relatorio);
            // Heapify recursivamente a sub arvore afetada
            heapify(listaUsuarios, n, maior, relatorio);
        }
        contInteracao++;
        relatorio.setComparacao((relatorio.getComparacao() + contInteracao));
    }


    public static void shellSort(ListaCont<Usuario> listaUsuarios, RelatorioOrdenacao relatorio) {
        int h = 1;
        int n = listaUsuarios.getTamanho();
        int contInteracao = 0;
        // Define o h para o while
        while (h < n) {
            contInteracao++;
            h = h * 3 + 1;
        }
        contInteracao++;

        h = h / 3;
        Usuario c;
        int j;

        while (h > 0) {
            contInteracao++;
            for (int i = h; i < n; i++) {
                contInteracao++;
                c = listaUsuarios.get(i);
                j = i;
                while (j >= h && (listaUsuarios.get(j - h).getIdUsuario() >= c.getIdUsuario())) {
                    contInteracao++;
                    listaUsuarios.set(j, listaUsuarios.get(j - h));
                    contInteracao++;
                    j = j - h;
                }
                contInteracao++;
                listaUsuarios.set(j, c);
                contInteracao++;
                relatorio.incrementaCopia();
            }
            contInteracao++;
            h = h / 2;
        }
        contInteracao++;
        relatorio.setComparacao((relatorio.getComparacao() + contInteracao));
        relatorio.finaliza();
    }


    public static void bubbleSortArrayListInteiro(List<Integer> lista) {
        for (int i = 0; i < lista.size(); i++) {
            for (int j = 1; j < lista.size() - i; j++) {
                if (lista.get(j) < lista.get(j - 1)) {
                    int aux = lista.get(j);
                    lista.set(j, lista.get(j - 1));
                    lista.set(j - 1, aux);
                }
            }
        }
    }


    private static void mergeInteiro(List<Integer> lista, int esq, int meio, int dir) {

        // Encontra os tamanhos dos dois sub arrays para serem mesclados
        int n1 = meio - esq + 1;
        int n2 = dir - meio;
        // Cria os arrays temporarios
        List<Integer> esqArray = new ArrayList<>();
        List<Integer> dirArray = new ArrayList<>();
        // Copia os dados para o novo array
        for (int i = 0; i < n1; ++i) {
            esqArray.add(lista.get(esq + i));
        }
        for (int j = 0; j < n2; ++j) {
            dirArray.add(lista.get(meio + 1 + j));
        }
        // Junta os arrays temporarios
        // Indice inicial dos sub arrays
        int i = 0, j = 0;
        // Indice inicial dos sub arrays
        int k = esq;
        while (i < n1 && j < n2) {
            if (esqArray.get(i) <= dirArray.get(j)) {
                lista.set(k, esqArray.get(i));
                i++;
            } else {
                lista.set(k, dirArray.get(j));
                j++;
            }
            k++;
        }
        // Copia os elementos do vetor esq se houver
        while (i < n1) {
            lista.set(k, esqArray.get(i));
            //listaUsuarios[k] = esqVet[i];
            i++;
            k++;
        }
        // Copia os elementos do vetor dir se houver
        while (j < n2) {
            lista.set(k, dirArray.get(j));
            j++;
            k++;
        }
    }

    public static void mergeSortInteiro(List<Integer> lista, int esq, int dir) {
        if (esq < dir) {
            // Encontra o termo do meio
            int meio = (esq + dir) / 2;
            // Ordena primeira e segunda metade
            mergeSortInteiro(lista, esq, meio);
            mergeSortInteiro(lista, meio + 1, dir);
            // Junta as metades
            mergeInteiro(lista, esq, meio, dir);
        }
    }
}
