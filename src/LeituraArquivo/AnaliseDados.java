/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeituraArquivo;

import Algoritmos.AlgoritmosOrdenacao;
import Algoritmos.ListaEncadeada;
import Filmes.Avaliacao;
import Filmes.Usuario;
import Relatorio.RelatorioOrdenacao;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author ice
 */
public class AnaliseDados {
    private int quantidade;
    private int semente;
    private ListaEncadeada<Usuario> listaUsuarios;
    private File arquivo;

    public AnaliseDados(int quantidade, File arquivo, int semente) {
        System.out.println("Criando Analise de dados de " + quantidade + " linhas");
        this.quantidade = quantidade;
        this.listaUsuarios = new ListaEncadeada<>();
        this.semente = semente;
        lerArquivo(arquivo);
    }

    public int getQuantidade() {
        return quantidade;
    }

    private void lerArquivo(File arquivo) {
        this.arquivo = arquivo;
        try (FileInputStream fi = new FileInputStream(this.arquivo)) {
            BufferedInputStream bis = new BufferedInputStream(fi);
            BufferedReader reader = new BufferedReader(new InputStreamReader(bis));
            String linha = reader.readLine();

            LineNumberReader linhaLeitura = new LineNumberReader(new FileReader(arquivo));
            linhaLeitura.skip(arquivo.length());
            int totalLinhas = linhaLeitura.getLineNumber();

            List<Integer> numAleatorios = new ArrayList<>();

            for (int i = 1; i < totalLinhas; i++) {
                numAleatorios.add(i);
            }

            Collections.shuffle(numAleatorios);
            numAleatorios = numAleatorios.subList(0, quantidade);

            AlgoritmosOrdenacao.bubbleSortArrayListInteiro(numAleatorios); //apagar e usar metodos de ordenacao

            int valorLinha = 1;
            String partes[];
            while ((linha = reader.readLine()) != null) {
                if (numAleatorios.size() == 0) {
                    break;
                } else if (valorLinha == numAleatorios.get(0)) {
                    partes = linha.split(";");
                    if (partes.length < 2) {
                        partes = linha.split(",");

                    }
                    int id = Integer.parseInt(partes[0]);
                    int idFilme = Integer.parseInt(partes[1]);;
                    double avaliacao = Double.parseDouble(partes[2]);
                    double tempo = Double.parseDouble(partes[3]);
                    Usuario usuario = new Usuario(id, idFilme, avaliacao, tempo);
                    int index = verificaFilme(usuario);
                    if (index == -1) {
                        this.listaUsuarios.insereFinal(usuario);
                    } else {
                        Avaliacao av = new Avaliacao(id, avaliacao, tempo);
                        this.listaUsuarios.retornaInfo(index).addAvaliacao(av);
                    }
                    numAleatorios.remove(0);
                }
                valorLinha++;
            }

            /*
            while((linha = reader.readLine())!=null){
                System.out.println(linha);
            }
             */
            executaOrdenacoes();
        } catch (FileNotFoundException ex) {
            System.err.println("Erro ao ler o arquivo!");
        } catch (Exception ex) {
            System.err.println("Erro");
            System.err.println(ex.toString());
        }
    }

    private int verificaFilme(Usuario usuario) {
        for (int i = 0; i < listaUsuarios.getTamanho(); i++) {
            if (usuario.getIdUsuario() == listaUsuarios.retornaInfo(i).getIdUsuario()) {
                return i;
            }
        }
        return -1;
    }

    public void imprimeLista() {

        for (int i = 0; i < quantidade; i++) {
            System.out.println(listaUsuarios.retornaInfo(i).getIdUsuario() + " " + listaUsuarios.retornaInfo(i).getAvaliacaoMedia());
        }

    }

    private void executaOrdenacoes() {
        
        ListaEncadeada<Usuario> aux = new ListaEncadeada<>();
        
        for (int i = 0; i < listaUsuarios.getTamanho(); i++) {
            aux.insereFinal(listaUsuarios.retornaInfo(i));
        }
        AlgoritmosOrdenacao.bubbleSort(listaUsuarios, new RelatorioOrdenacao(semente, quantidade, "BubbleSort"));
        
        aux = new ListaEncadeada<>();
        for (int i = 0; i < listaUsuarios.getTamanho(); i++) {
            aux.insereFinal(listaUsuarios.retornaInfo(i));
        }
        AlgoritmosOrdenacao.quickSortRec(listaUsuarios, new RelatorioOrdenacao(semente, quantidade, "QuickSort Recursivo"));
        

        aux = new ListaEncadeada<>();
        for (int i = 0; i < listaUsuarios.getTamanho(); i++) {
            aux.insereFinal(listaUsuarios.retornaInfo(i));
        }
        AlgoritmosOrdenacao.quicksortMedianaK(listaUsuarios, 3, new RelatorioOrdenacao(semente, quantidade, "QuickSort Mediana 3"));
        
        aux = new ListaEncadeada<>();
        for (int i = 0; i < listaUsuarios.getTamanho(); i++) {
            aux.insereFinal(listaUsuarios.retornaInfo(i));
        }
        AlgoritmosOrdenacao.quicksortMedianaK(listaUsuarios, 5, new RelatorioOrdenacao(semente, quantidade, "QuickSort Mediana 5"));

        aux = new ListaEncadeada<>();
        for (int i = 0; i < listaUsuarios.getTamanho(); i++) {
            aux.insereFinal(listaUsuarios.retornaInfo(i));
        }
        AlgoritmosOrdenacao.quickSortHibrido(listaUsuarios, 10, new RelatorioOrdenacao(semente, quantidade, "QuickSort Hibrido 10"));

        aux = new ListaEncadeada<>();
        for (int i = 0; i < listaUsuarios.getTamanho(); i++) {
            aux.insereFinal(listaUsuarios.retornaInfo(i));
        }
        AlgoritmosOrdenacao.quickSortHibrido(listaUsuarios, 100, new RelatorioOrdenacao(semente, quantidade, "QuickSort Hibrido 100"));

        aux = new ListaEncadeada<>();
        for (int i = 0; i < listaUsuarios.getTamanho(); i++) {
            aux.insereFinal(listaUsuarios.retornaInfo(i));
        }
        AlgoritmosOrdenacao.insertionSort(listaUsuarios,  new RelatorioOrdenacao(semente, quantidade, "InsertionSort"));

        aux = new ListaEncadeada<>();
        for (int i = 0; i < listaUsuarios.getTamanho(); i++) {
            aux.insereFinal(listaUsuarios.retornaInfo(i));
        }
        AlgoritmosOrdenacao.mergeSort(listaUsuarios,  new RelatorioOrdenacao(semente, quantidade, "MergeSort"));

        aux = new ListaEncadeada<>();
        for (int i = 0; i < listaUsuarios.getTamanho(); i++) {
            aux.insereFinal(listaUsuarios.retornaInfo(i));
        }
        AlgoritmosOrdenacao.heapSort(listaUsuarios,  new RelatorioOrdenacao(semente, quantidade, "HeapSort"));

        aux = new ListaEncadeada<>();
        for (int i = 0; i < listaUsuarios.getTamanho(); i++) {
            aux.insereFinal(listaUsuarios.retornaInfo(i));
        }
        AlgoritmosOrdenacao.shellSort(listaUsuarios, new RelatorioOrdenacao(semente, quantidade, "ShellSort"));

    }
}
