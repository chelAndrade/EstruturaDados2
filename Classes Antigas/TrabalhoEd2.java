/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.ed2;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author miche
 */
public class TrabalhoEd2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File arquivo = new File("ratingsTRATADO.xml");
        try (FileReader fr = new FileReader(arquivo)) {
            int c = fr.read();
            while (c != -1) {
                System.out.print((char) c);
                c = fr.read();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    
}
