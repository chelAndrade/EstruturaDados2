/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import LeituraArquivo.AnaliseDados;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 * @author RODRIGO
 */
public class TrabalhoEd2 {

    /**
     * @param args the command line arguments
     */
    private static File arquivo;

    public static void main(String[] args) {
        // TODO code application logic here
        // menuPrincipal();

        menuPrincipal();

        //Ordenacao
        /*
  

        //Hash
  
         */
        System.out.println("Execução Terminada");
    }

    private static void lerArquivo() {
        String caminho;
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o caminho e o nome do arquivo: ");
        caminho = sc.nextLine();
        arquivo = new File(caminho);
    }

    private static void menuPrincipal() {
        int x = 1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("------------Menu------------");
        System.out.println("1 - Abrir arquivo");
        System.out.println("");
        System.out.println("0 - Sair");
        System.out.println("----------------------------");
        while (x != 0) {
            try {
                x = scanner.nextInt();
                switch (x) {
                    case 1:
                        lerArquivo();
                        menu2();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Digite um valor válido!");
                        x = -1;
                        System.out.println("------------Menu------------");
                        System.out.println("1 - Abrir arquivo");
                        System.out.println("");
                        System.out.println("0 - Sair");
                        System.out.println("----------------------------");
                    // break;
                }
            } catch (Exception ex) {

                System.out.println("Digite um valor válido!");
                scanner = new Scanner(System.in);
                //x = scanner.nextInt();
            }

        }
    }

    private static void menu2() {
        int x = 1;
        Scanner scanner = new Scanner(System.in);
        imprimeMenu2();
        while (x != 0) {
            try {
                x = scanner.nextInt();
                switch (x) {
                    case 1:
                        lerArquivo();
                        imprimeMenu2();
                        break;
                    case 2:
                        executarOrdenacao();
                        break;
                    case 3:
                        executarHash();
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Digite um valor válido!");
                        x = -1;
                        break;
                }
            } catch (Exception ex) {

                System.out.println("Digite um valor válido!");
                scanner = new Scanner(System.in);
                //x = scanner.nextInt();
            }

        }
    }

    private static void imprimeMenu2() {
        System.out.println("------------Menu------------");
        System.out.println("Arquivo: " + arquivo.getAbsolutePath());
        System.out.println("1 - Abrir outro arquivo");
        System.out.println("2 - Gerar Ordenações para Relatório");
        System.out.println("3 - Gerar Hash para Relatório");
        System.out.println();
        System.out.println("0 - Sair");
        System.out.println("----------------------------");
    }

    public static void executarOrdenacao() {
        AnaliseDados ad;
        System.out.println("Iniciando Ordenações");
        for (int i = 0; i < 5; i++) {
            System.out.println("Rodando semente " + (i + 1));
            
            ad = new AnaliseDados(1000, arquivo, i + 1);
            ad.executaOrdenacao();
            ad = new AnaliseDados(5000, arquivo, i + 1);
            ad.executaOrdenacao();
            ad = new AnaliseDados(10000, arquivo, i + 1);
            ad.executaOrdenacao();
            ad = new AnaliseDados(50000, arquivo, i + 1);
            ad.executaOrdenacao();  
            ad = new AnaliseDados(100000, arquivo, i + 1);
            ad.executaOrdenacao();
            ad = new AnaliseDados(500000, arquivo, i + 1);
            ad.executaOrdenacao();
            ad = new AnaliseDados(1000000, arquivo, i + 1);
            ad.executaOrdenacao();           
        }
        System.out.println("Ordenações Terminadas");
    }

    public static void executarHash() {
        AnaliseDados ad;
        System.out.println("Iniciando Hash");
        for (int i = 0; i < 5; i++) {
            System.out.println("Rodando semente " + (i + 1));
            ad = new AnaliseDados(1000, arquivo, i + 1);
            ad.executaHash();
            ad = new AnaliseDados(5000, arquivo, i + 1);
            ad.executaHash();
            ad = new AnaliseDados(10000, arquivo, i + 1);
            ad.executaHash();
            ad = new AnaliseDados(50000, arquivo, i + 1);
            ad.executaHash();
            ad = new AnaliseDados(100000, arquivo, i + 1);
            ad.executaHash();
            ad = new AnaliseDados(500000, arquivo, i + 1);
            ad.executaHash();
            ad = new AnaliseDados(1000000, arquivo, i + 1);
            ad.executaHash();

        }
        System.out.println("Hash Terminado");
    }
}
