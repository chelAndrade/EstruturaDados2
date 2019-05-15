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
        AnaliseDados ad;
        lerArquivo();
        
        for (int i = 0; i < 1; i++) {
            System.out.println("Rodando semente " + (i + 1));
            ad = new AnaliseDados(1000,arquivo, i+1 );
            ad = new AnaliseDados(5000,arquivo, i+1 );
            ad = new AnaliseDados(10000,arquivo, i+1 );
            ad = new AnaliseDados(50000,arquivo, i+1 );
            ad = new AnaliseDados(100000,arquivo, i+1 );
            ad = new AnaliseDados(500000,arquivo, i+1 );
        }

        System.out.println("Execução Terminada");
    }
    
    private static void lerArquivo(){
        String caminho;
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o caminho e o nome do arquivo: ");
        caminho = sc.nextLine();
        arquivo = new File(caminho);
    }  
    
    private static void menuPrincipal(){
        int x = 1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("------------Menu------------");
        System.out.println("1 - Abrir arquivo");
        System.out.println("2 - Sair");
        System.out.println("----------------------------");
        while (x!=0){         
            try{
                x = scanner.nextInt();
            } catch (Exception ex){
                x = -1;
                System.out.println("Digite um valor válido!");
                
            }
            
            switch(x){
                case 1:
                    break;
                default:
                    System.out.println("Digite um valor válido!");
                   // break;
            }
            
        }
    }
}
