/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmos;

import Filmes.Usuario;
import Relatorio.RelatorioHash;

/**
 *
 * @author Carcara
 */
public class AlgoritmosHash {
    
    
    private static Usuario[] tabela(int tam) {
        Usuario[] tab = new Usuario[tam];
        for (int i = 0; i < tam; i++) {
            tab[i] = null;
        }
        return tab;
    }

    private static ListaEncadeada<Usuario>[] tabelaEncadeada(int tam) {
        ListaEncadeada[] tab = new ListaEncadeada[tam];//falta tipo deputado na lista?
        for (int i = 0; i < tam; i++) {
            tab[i] = new ListaEncadeada();
        }
        return tab;
    }

    private static Usuario[][] tabelaCoalescida(int tam) {
        Usuario[][] tab = new Usuario[tam][2];
        for (int i = 0; i < tam; i++) {
            tab[i][0] = null;
            tab[i][1] = null;
        }
        return tab;
    }

    private static int hash(int k, int m) {
        return k % m;
    }

    private static int hash2(int k, int j) {
        int aux = k * j + 1;
        return aux;
    }

    private static int primo(int k) {
        for (int i = k; k > 0; i--) {
            if (ehPrimo(i)) {
                return i;
            }
        }
        return -1;
    }

    private static boolean ehPrimo(int k) {
        for (int i = 2; i < k; i++) {
            if (k % i == 0) {
                return false;
            }
        }
        return true;
    }

    // Funcoes de hashing

    public static Usuario[] sondagemLinear(ListaEncadeada<Usuario> listaUsuarios, RelatorioHash relatorio) {
        int pos;
        int h = primo(listaUsuarios.getTamanho());
        Usuario[] tabela = tabela(listaUsuarios.getTamanho());
        for (int i = 0; i < listaUsuarios.getTamanho(); i++) {
            relatorio.incrementaComparacao();
            pos = (hash(listaUsuarios.retornaInfo(i).getIdUsuario(), h));

            //Colisões
            while (tabela[pos] != null) {
                relatorio.incrementaComparacao();
                pos = hash(pos + 1, listaUsuarios.getTamanho());
                relatorio.incrementaColisoes();

            }

            relatorio.incrementaComparacao();
            tabela[pos] = listaUsuarios.retornaInfo(i);
        }
        relatorio.incrementaComparacao();
        
        relatorio.finaliza();
        return tabela;
    }

    public static Usuario[] sondagemQuadratica(ListaEncadeada<Usuario> listaUsuarios, RelatorioHash relatorio) {
        int pos;
        int h = primo(listaUsuarios.getTamanho());
        Usuario[] tabela = tabela(listaUsuarios.getTamanho());
        for (int i = 0; i < listaUsuarios.getTamanho(); i++) {
            relatorio.incrementaComparacao();
            pos = (hash(listaUsuarios.retornaInfo(i).getIdUsuario(), h));
            int j = 0;
            if (pos < 0) {
                pos += tabela.length;
            }
            while (tabela[pos] != null) {
                relatorio.incrementaComparacao();
                j++;
                pos = hash(pos + (j * j), listaUsuarios.getTamanho());
                relatorio.incrementaColisoes();
                if (pos < 0) {
                    pos += tabela.length;
                }
            }
            if (pos < 0) {
                pos += tabela.length;
            }
            relatorio.incrementaComparacao();
            tabela[pos] = listaUsuarios.retornaInfo(i);
        }
        relatorio.incrementaComparacao();
                
        relatorio.finaliza();
        return tabela;
    }

    public static Usuario[] duploHashing(ListaEncadeada<Usuario> listaUsuarios, RelatorioHash relatorio) {
        int pos = 0;
        int h = primo(listaUsuarios.getTamanho());
        Usuario[] tabela = tabela(listaUsuarios.getTamanho());
        for (int i = 0; i < listaUsuarios.getTamanho(); i++) {
            relatorio.incrementaComparacao();
            pos = (hash(listaUsuarios.retornaInfo(i).getIdUsuario(), h));
            if (pos < 0) {
                pos = pos + tabela.length;
                relatorio.incrementaColisoes();
            }
            int j = 0;
            while (tabela[pos] != null && pos < 0) {
                relatorio.incrementaComparacao();
                j++;
                pos = hash(pos + hash2(pos, j), listaUsuarios.getTamanho());
                //pos=hash(pos+1,tabela.length);
                relatorio.incrementaColisoes();
                if (pos < 0) {
                    pos = pos + tabela.length;
                   relatorio.incrementaColisoes();
                }
            }
            if (pos < 0) {
                pos = pos + tabela.length;
                relatorio.incrementaColisoes();
            }
            relatorio.incrementaComparacao();
            tabela[pos] = listaUsuarios.retornaInfo(i);
        }
        relatorio.incrementaComparacao();
                
        relatorio.finaliza();
        return null;
    }

    public static ListaEncadeada[] encadeamentoSeparado(ListaEncadeada<Usuario> listaUsuarios, RelatorioHash relatorio) {
        int pos;
        ListaEncadeada<Usuario>[] tabela = tabelaEncadeada(listaUsuarios.getTamanho() / 2);
        for (int i = 0; i < listaUsuarios.getTamanho(); i++) {
            relatorio.incrementaComparacao();
            pos = (hash(listaUsuarios.retornaInfo(i).getIdUsuario(), listaUsuarios.getTamanho() / 2));
            tabela[pos].insereFinal(listaUsuarios.retornaInfo(i));
            if (tabela[pos].retornaFim() != null) {
                relatorio.incrementaColisoes();
            }
            relatorio.incrementaComparacao();
        }
        relatorio.incrementaComparacao();
                
        relatorio.finaliza();
        return tabela;
    }

    public static Usuario[][] encadeamentoCoalescido(ListaEncadeada<Usuario> listaUsuarios, RelatorioHash relatorio) {
        int pos;
        int h = primo(listaUsuarios.getTamanho());
        Usuario[][] tabela = tabelaCoalescida(listaUsuarios.getTamanho());
        for (int i = 0; i < listaUsuarios.getTamanho(); i++) {
            relatorio.incrementaComparacao();
            pos = (hash(listaUsuarios.retornaInfo(i).getIdUsuario(), h));
            if (tabela[pos][0] != null) {
                pos = listaUsuarios.getTamanho() - 1;
                while (tabela[pos][0] != null) {
                    relatorio.incrementaComparacao();
                    pos = pos - 1;
                    relatorio.incrementaColisoes();
                }
                relatorio.incrementaComparacao();
                tabela[pos][0] = listaUsuarios.retornaInfo(i);
                //Cria um dep aux que usa o id como posicao para o proximo
                Usuario aux = new Usuario();
                aux.setIdUsuario(pos);
                tabela[hash(listaUsuarios.retornaInfo(i).getIdUsuario(), h)][1] = aux;
            } else {
                tabela[pos][0] = listaUsuarios.retornaInfo(i);
            }
            relatorio.incrementaComparacao();
        }
        relatorio.incrementaComparacao();
                
        relatorio.finaliza();
        return tabela;
    }
     // Funcoes de busca de hashing //
    private static Usuario buscaSondagemLinear(Usuario deputado, Usuario[] tabela, RelatorioHash relatorio) {
        int h = primo(tabela.length);
        int pos = (hash(deputado.getIdUsuario(), h));
        if (tabela[pos].getIdUsuario() == deputado.getIdUsuario()) {
            relatorio.incrementaComparacao();
            return tabela[pos];//Encontrou o Usuario requerido na primeira iteração(não há colisões)
        } else {
            relatorio.incrementaComparacao();
            int cont = 0; //Contador para verificar se o dep nao foi encontrado
            while (tabela[pos].getIdUsuario() != deputado.getIdUsuario() && cont < tabela.length) {
                relatorio.incrementaComparacao();
                pos = hash(pos + 1, tabela.length);
                cont++;
            }
            relatorio.incrementaComparacao();
            if (cont < tabela.length) {
                relatorio.incrementaComparacao();
                return tabela[pos];//Encontrou o Usuario Requerido(há colisões)

            } else {
                relatorio.incrementaComparacao();
                return null;//Não Encontrou o Usuario Requerido
            }
        }
    }

    private static Usuario buscaSondagemQuadratica(Usuario deputado, Usuario[] tabela, RelatorioHash relatorio) {
        int h = primo(tabela.length);
        int pos = (hash(deputado.getIdUsuario(), h));
        if (tabela[pos].getIdUsuario() == deputado.getIdUsuario()) {
            relatorio.incrementaComparacao();
            return tabela[pos];
        } else {
            relatorio.incrementaComparacao();
            int cont = 0; //contador para verificar se o dep nao foi encontrado
            int j = 0;
            while (tabela[pos].getIdUsuario() != deputado.getIdUsuario() && cont < tabela.length) { //TODO Revisar a condicao de parada (cont)
                relatorio.incrementaComparacao();
                j++;
                pos = hash(pos + j * j, tabela.length);
                cont++;
            }
            relatorio.incrementaComparacao();
            if (cont < tabela.length) {
                relatorio.incrementaComparacao();
                return tabela[pos];
            } else {
                relatorio.incrementaComparacao();
                return null;
            }
        }
    }

    private static Usuario buscaDuploHashing(Usuario deputado, Usuario[] tabela, RelatorioHash relatorio) {
        int h = primo(tabela.length);
        int pos = (hash(deputado.getIdUsuario(), h));
        if (tabela[pos].getIdUsuario() == deputado.getIdUsuario()) {
            relatorio.incrementaComparacao();
            return tabela[pos];
        } else {
            relatorio.incrementaComparacao();
            int cont = 0; //contador para verificar se o dep nao foi encontrado
            int j = 0;
            while (tabela[pos].getIdUsuario() != deputado.getIdUsuario() && cont < tabela.length) { //TODO Revisar a condicao de parada (cont)

                relatorio.incrementaComparacao();
                j++;
                cont++;
                //pos = hash(hash(deputado.getIdUsuario(), h) + j * hash2(deputado.getIdUsuario(), j), tabela.length);
            }
            relatorio.incrementaComparacao();
            if (cont < tabela.length) {
                relatorio.incrementaComparacao();
                return tabela[pos];
            } else {
                relatorio.incrementaComparacao();
                return null;
            }
        }
    }

    private static Usuario buscaEncadeamentoSeparado(Usuario deputado, ListaEncadeada<Usuario>[] tabela, RelatorioHash relatorio) {
        int pos = (hash(deputado.getIdUsuario(), tabela.length));
        for (int j = 0; j < tabela[pos].getTamanho(); j++) {
            relatorio.incrementaComparacao();
            if (tabela[pos].retornaInfo(j).getIdUsuario() == deputado.getIdUsuario()) {
                relatorio.incrementaComparacao();
                return tabela[pos].retornaInfo(j);
            }
        }
        relatorio.incrementaComparacao();
        return null;
    }

    private static Usuario buscaEncadeamentoCoalescido(Usuario deputado, Usuario[][] tabela, RelatorioHash relatorio) {
        int h = primo(tabela.length);
        int pos = (hash(deputado.getIdUsuario(), h));
        while (tabela[pos][0].getIdUsuario() != deputado.getIdUsuario()) {
            relatorio.incrementaComparacao();
            if (tabela[pos][1] != null) {
                relatorio.incrementaComparacao();
                pos = tabela[pos][1].getIdUsuario();
            } else {
                relatorio.incrementaComparacao();
                return null;
            }
        }
        relatorio.incrementaComparacao();
        return tabela[pos][0];
    }

    private static int hashASCII(String k, int m) {
        int soma = 0;
        try {
            String texto = k;
            // translating text String to 7 bit ASCII encoding
            byte[] bytes = texto.getBytes("US-ASCII");
            for (int i = 0; i < bytes.length; i++) {
                soma = soma + bytes[i];
            }
        } catch (java.io.UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return soma % m;
    }

    public static ListaEncadeada[] encadeamentoSeparadoASCII(ListaEncadeada<Usuario> listaUsuarios, RelatorioHash relatorio) {
        int pos;
        ListaEncadeada<Usuario>[] tabela = tabelaEncadeada(listaUsuarios.getTamanho() / 2);
        for (int i = 0; i < listaUsuarios.getTamanho(); i++) {
            relatorio.incrementaComparacao();
            pos = (hashASCII(Integer.toString(listaUsuarios.retornaInfo(i).getIdUsuario()), listaUsuarios.getTamanho() / 2));
            tabela[pos].insereFinal(listaUsuarios.retornaInfo(i));
            if (tabela[pos].retornaFim() != null) {
                relatorio.incrementaColisoes();
            }
            relatorio.incrementaComparacao();
        }
        relatorio.incrementaComparacao();
        return tabela;
    }
}


