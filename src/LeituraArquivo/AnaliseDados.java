/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeituraArquivo;

import Filmes.Filme;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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
    private List<Filme> listaFilmes;
    private File arquivo;

    public AnaliseDados(int quantidade) {
        this.quantidade = quantidade;
        this.listaFilmes = new ArrayList<>();
        lerArquivo();
    }

    public int getQuantidade() {
        return quantidade;
    }
    
    private void lerArquivo(){
        this.arquivo = new File("ratingsTRATADO.csv");
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
            
            Collections.sort(numAleatorios);
            
            int valorLinha = 1;
            String partes[];           
            while((linha = reader.readLine())!=null){
                if(numAleatorios.size()==0){
                    break;
                } else if(valorLinha==numAleatorios.get(0)){
                    partes = linha.split(";");
                    int id = Integer.parseInt(partes[0]);
                    int idFilme = Integer.parseInt(partes[0]);;
                    double avaliacao = Double.parseDouble(partes[0]);;
                    Filme filme = new Filme(id,idFilme,avaliacao);
                    int index = verificaFilme(filme);
                    if(index==-1){
                        this.listaFilmes.add(filme);
                    } else {
                        this.listaFilmes.get(index).addAvaliacao(avaliacao);
                        this.listaFilmes.get(index).addUsuario(id);
                    }
                    
                    numAleatorios.remove(0);
                }
            }
            
            for(int i=0;i<quantidade;i++){
                System.out.println(listaFilmes.get(i).getIdFilme());
            }
            
            
            /*
            while((linha = reader.readLine())!=null){
                System.out.println(linha);
            }
*/
        }catch(IOException ex){
            System.err.println("Erro ao ler o arquivo!");
        } 
        catch (Exception ex){
            System.err.println("Erro");
        }
    }
    
    
    private int verificaFilme(Filme filme){
        for(int i=0;i<listaFilmes.size();i++){
            if(filme.getIdFilme()==listaFilmes.get(i).getIdFilme()){
                return i;
            }
        }
        return -1;
    }
    
}
