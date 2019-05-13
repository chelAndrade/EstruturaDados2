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

/**
 *
 * @author RODRIGO
 */
public class TrabalhoEd2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        ////////////////// Criar arquivo ///////////////////////
        File arquivo = new File("teste.txt");
        try(FileWriter fw = new FileWriter(arquivo)){
            fw.write("34");            
            fw.flush();            
        }catch(IOException ex){
            ex.printStackTrace();
        }   
        ///////////////////////////////////////////////////////
       
        
        AnaliseDados ad ;
                for(int i=0;i<5;i++){
                    System.out.println("Rodando semente "+ (i+1));                
                    ad= new AnaliseDados(1000);
                    ad= new AnaliseDados(5000);
                    ad= new AnaliseDados(10000);
                    ad= new AnaliseDados(50000);
                    ad= new AnaliseDados(100000);
                    ad= new AnaliseDados(500000);
                    
                }
                
        
        
        /*
        try(FileReader fr = new FileReader(arquivo2)){
            int c = fr.read();
            while(c != -1){
                System.out.println((char) c);
                c = fr.read();
            }
        }catch(IOException ex){
            ex.printStackTrace();
        } */
       
        
    }
}
    

