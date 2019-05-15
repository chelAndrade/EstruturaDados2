/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Relatorio;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Carcara
 */
public class RelatorioHash {

    private int semente;
    private int n;
    private String nomeOrdenacao;
    private Calendar dataInicio;
    private long tempoInicio;
    private long tempoFim;
    private long comparacao;
    private long colisoes;

    public RelatorioHash(int semente, int n, String nomeOrdenacao) {
        this.semente = semente;
        this.n = n;
        this.nomeOrdenacao = nomeOrdenacao;
        this.dataInicio = Calendar.getInstance();
        this.tempoInicio = System.nanoTime();
        this.comparacao = 0;
        this.colisoes = 0;
    }

    public void incrementaColisoes() {
        this.colisoes++;
    }
    
    public void incrementaComparacao(){
        this.comparacao++;
    }

    public long getComparacao() {
        return comparacao;
    }

    public void setComparacao(long comparacao) {
        this.comparacao = comparacao;
    }

    public void finaliza() {
        this.tempoFim = System.nanoTime();
        geraTexto();
    }

    private void geraTexto() {
        String caminho = "ArquivosGeradosParaRelatorio/";
        try {
            //Parametros da criacao do arquivo texto
            new File(caminho).mkdirs();
            File arquivo = new File(caminho + "SaidaHash" + ".csv");
            FileWriter arq = new FileWriter(arquivo, true);
            PrintWriter gravarArq = new PrintWriter(arq);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            //Calcula a quantidade de linhas
            LineNumberReader linhaLeitura = new LineNumberReader(new FileReader(arquivo));
            linhaLeitura.skip(arquivo.length());
            int totalLinhas = linhaLeitura.getLineNumber();

            if (totalLinhas == 0) {//Cria o cabecalho do texto
                gravarArq.println("Semente;Valor de N;Algoritmo;Data Inicio;Tempo de Execucao(ns);Memoria Gasta;Comparacoes;Colisoes");
            }

            gravarArq.print(this.semente + ";");
            gravarArq.print(this.n + ";");
            gravarArq.print(this.nomeOrdenacao + ";");
            gravarArq.print(sdf.format(this.dataInicio.getTime()) + ";");
            long aux = this.tempoFim - this.tempoInicio;
            gravarArq.print(aux + ";");
            Runtime rt = Runtime.getRuntime();
            long usoMemoria = rt.maxMemory() - rt.freeMemory();
            
            gravarArq.print(usoMemoria + ";");
            gravarArq.print(this.comparacao + ";");
            gravarArq.print(this.colisoes);
            gravarArq.println();
            arq.close();
        } catch (Exception e) {
            System.err.println("Erro");
            System.err.println(e.toString());
        }
    }

}
