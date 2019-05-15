/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeituraArquivo;

import Algoritmos.AlgoritmosHash;
import Algoritmos.AlgoritmosOrdenacao;
import Algoritmos.AlgoritmosOrdenacaoVetor;
import Algoritmos.ListaCont;
import Algoritmos.ListaEncadeada;
import Algoritmos.No;
import Filmes.Avaliacao;
import Filmes.Usuario;
import Relatorio.RelatorioHash;
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
        this.arquivo = arquivo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    private void lerArquivo() {
        //this.arquivo = arquivo;
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
            while ((linha = reader.readLine()) != null && numAleatorios.size() != 0) {
                if (valorLinha == numAleatorios.get(0)) {
                    partes = linha.split(";");
                    if (partes.length < 2) {
                        partes = linha.split(",");

                    }
                    int id = Integer.parseInt(partes[0]);
                    int idFilme = Integer.parseInt(partes[1]);;
                    double avaliacao = Double.parseDouble(partes[2]);
                    double tempo = Double.parseDouble(partes[3]);
                    Usuario usuario = new Usuario(id, idFilme, avaliacao, tempo);
                    int index = verificaUsuario(usuario);
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
            //executaOrdenacoes();
            reader.close();
            bis.close();
            fi.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Erro ao ler o arquivo!");
        } catch (Exception ex) {
            System.err.println("Erro");
            System.err.println(ex.toString());
        }
    }

    private int verificaUsuario(Usuario usuario) {
        if (listaUsuarios.getTamanho() == 0) {
            return -1;
        } else if (usuario.getIdUsuario() == listaUsuarios.retornaFim().getIdUsuario()) {
            return (listaUsuarios.getTamanho() - 1);
        } else {

            /*
            for (int i = 0; i < listaUsuarios.getTamanho(); i++) {
                if (usuario.getIdUsuario() == listaUsuarios.retornaInfo(i).getIdUsuario()) {
                    return i;
                }
            }
            */
            return -1;
        }
    }

    public void imprimeLista() {

        for (int i = 0; i < quantidade; i++) {
            System.out.println(listaUsuarios.retornaInfo(i).getIdUsuario() + " " + listaUsuarios.retornaInfo(i).getAvaliacaoMedia());
        }

    }

    public void executaOrdenacao() {
        lerArquivo();
        executaAlgoritmoOrdenacao();
    }

    public void executaHash() {
        lerArquivo();
        executaAlgoritmoHash();
    }

    private void executaAlgoritmoHash() {
        System.out.println("Executando Algoritmo de Hash");
        System.out.println("Executando Sondagem Linear");
        AlgoritmosHash.sondagemLinear(listaUsuarios, new RelatorioHash(semente, quantidade, "Sondagem Linear"));
        System.out.println("Executando Sondagem Quadratica");
        AlgoritmosHash.sondagemQuadratica(listaUsuarios, new RelatorioHash(semente, quantidade, "Sondagem Quadratica"));
        System.out.println("Executando Duplo Hashing");
        AlgoritmosHash.duploHashing(listaUsuarios, new RelatorioHash(semente, quantidade, "Duplo Hashing"));
        System.out.println("Executando Encadeamento Separado");
        AlgoritmosHash.encadeamentoSeparado(listaUsuarios, new RelatorioHash(semente, quantidade, "Encadeamento Separado"));
        System.out.println("Executando Encadeamento Coalescido");
        AlgoritmosHash.encadeamentoCoalescido(listaUsuarios, new RelatorioHash(semente, quantidade, "Encadeamento Coalescido"));
        /*
        ListaEncadeada<Usuario> aux = new ListaEncadeada<>();

        No no = listaUsuarios.getInicio();
        System.out.println("Rodando Algoritmos de Hash");

        for (int i = 0; i < listaUsuarios.getTamanho(); i++) {
            aux.insereFinal((Usuario) no.getObjeto());
            no = no.getProximo();
        }
        AlgoritmosHash.sondagemLinear(listaUsuarios, new RelatorioHash(semente, quantidade, "Sondagem Linear"));
        aux.deletarLista();

        aux = new ListaEncadeada<>();
        no = listaUsuarios.getInicio();
        for (int i = 0; i < listaUsuarios.getTamanho(); i++) {
            aux.insereFinal((Usuario) no.getObjeto());
            no = no.getProximo();
        }
        AlgoritmosHash.sondagemQuadratica(listaUsuarios, new RelatorioHash(semente, quantidade, "Sondagem Quadratica"));

        aux.deletarLista();
        aux = new ListaEncadeada<>();
        no = listaUsuarios.getInicio();
        for (int i = 0; i < listaUsuarios.getTamanho(); i++) {
            aux.insereFinal((Usuario) no.getObjeto());
            no = no.getProximo();
        }
        AlgoritmosHash.duploHashing(listaUsuarios, new RelatorioHash(semente, quantidade, "Duplo Hashing"));

        aux.deletarLista();
        aux = new ListaEncadeada<>();
        no = listaUsuarios.getInicio();
        for (int i = 0; i < listaUsuarios.getTamanho(); i++) {
            aux.insereFinal((Usuario) no.getObjeto());
            no = no.getProximo();
        }
        AlgoritmosHash.encadeamentoSeparado(listaUsuarios, new RelatorioHash(semente, quantidade, "Encadeamento Separado"));

        aux.deletarLista();
        aux = new ListaEncadeada<>();
        no = listaUsuarios.getInicio();
        for (int i = 0; i < listaUsuarios.getTamanho(); i++) {
            aux.insereFinal((Usuario) no.getObjeto());
            no = no.getProximo();
        }
        AlgoritmosHash.encadeamentoCoalescido(listaUsuarios, new RelatorioHash(semente, quantidade, "Encadeamento Coalescido"));
         */
    }

    private void executaAlgoritmoOrdenacao() {

        try {
            System.out.println("Executando Ordenação");
            ListaCont aux = new ListaCont(listaUsuarios.getTamanho());
            No no = listaUsuarios.getInicio();

            for (int i = 0; i < listaUsuarios.getTamanho(); i++) {
                aux.insereFinal((Usuario) no.getObjeto());
                no = no.getProximo();
            }
            AlgoritmosOrdenacaoVetor.bubbleSort(aux, new RelatorioOrdenacao(semente, quantidade, "BubbleSort"));

            aux.deletarLista();
            no = listaUsuarios.getInicio();
            for (int i = 0; i < listaUsuarios.getTamanho(); i++) {
                aux.insereFinal((Usuario) no.getObjeto());
                no = no.getProximo();
            }
            AlgoritmosOrdenacaoVetor.bubbleSort(aux, new RelatorioOrdenacao(semente, quantidade, "BubbleSort"));

            aux.deletarLista();
            no = listaUsuarios.getInicio();
            for (int i = 0; i < listaUsuarios.getTamanho(); i++) {
                aux.insereFinal((Usuario) no.getObjeto());
                no = no.getProximo();
            }
            AlgoritmosOrdenacaoVetor.quickSortRec(aux, new RelatorioOrdenacao(semente, quantidade, "QuickSort Recursivo"));

            aux.deletarLista();

            no = listaUsuarios.getInicio();
            for (int i = 0; i < listaUsuarios.getTamanho(); i++) {
                aux.insereFinal((Usuario) no.getObjeto());
                no = no.getProximo();
            }
            AlgoritmosOrdenacaoVetor.quicksortMedianaK(aux, 3, new RelatorioOrdenacao(semente, quantidade, "QuickSort Mediana 3"));

            aux.deletarLista();

            no = listaUsuarios.getInicio();
            for (int i = 0; i < listaUsuarios.getTamanho(); i++) {
                aux.insereFinal((Usuario) no.getObjeto());
                no = no.getProximo();
            }
            AlgoritmosOrdenacaoVetor.quicksortMedianaK(aux, 5, new RelatorioOrdenacao(semente, quantidade, "QuickSort Mediana 5"));

            aux.deletarLista();

            no = listaUsuarios.getInicio();
            for (int i = 0; i < listaUsuarios.getTamanho(); i++) {
                aux.insereFinal((Usuario) no.getObjeto());
                no = no.getProximo();
            }
            AlgoritmosOrdenacaoVetor.quickSortHibrido(aux, 10, new RelatorioOrdenacao(semente, quantidade, "QuickSort Hibrido 10"));

            aux.deletarLista();

            no = listaUsuarios.getInicio();
            for (int i = 0; i < listaUsuarios.getTamanho(); i++) {
                aux.insereFinal((Usuario) no.getObjeto());
                no = no.getProximo();
            }
            AlgoritmosOrdenacaoVetor.quickSortHibrido(aux, 100, new RelatorioOrdenacao(semente, quantidade, "QuickSort Hibrido 100"));

            aux.deletarLista();

            no = listaUsuarios.getInicio();
            for (int i = 0; i < listaUsuarios.getTamanho(); i++) {
                aux.insereFinal((Usuario) no.getObjeto());
                no = no.getProximo();
            }
            AlgoritmosOrdenacaoVetor.insertionSort(aux, new RelatorioOrdenacao(semente, quantidade, "InsertionSort"));

            aux.deletarLista();

            no = listaUsuarios.getInicio();
            for (int i = 0; i < listaUsuarios.getTamanho(); i++) {
                aux.insereFinal((Usuario) no.getObjeto());
                no = no.getProximo();
            }
            AlgoritmosOrdenacaoVetor.mergeSort(aux, new RelatorioOrdenacao(semente, quantidade, "MergeSort"));

            aux.deletarLista();

            no = listaUsuarios.getInicio();
            for (int i = 0; i < listaUsuarios.getTamanho(); i++) {
                aux.insereFinal((Usuario) no.getObjeto());
                no = no.getProximo();
            }
            AlgoritmosOrdenacaoVetor.heapSort(aux, new RelatorioOrdenacao(semente, quantidade, "HeapSort"));

            aux.deletarLista();

            no = listaUsuarios.getInicio();
            for (int i = 0; i < listaUsuarios.getTamanho(); i++) {
                aux.insereFinal((Usuario) no.getObjeto());
                no = no.getProximo();
            }
            AlgoritmosOrdenacaoVetor.shellSort(aux, new RelatorioOrdenacao(semente, quantidade, "ShellSort"));

        } catch (Exception e) {
            System.err.println(e.toString());
        }

    }
}
