package leilao;

import leilao.gui.TelaLogin;
import leilao.pn.Criptografia;
import leilao.pn.MulticastPeer;

public class Main {
    public static void main(String[] args) {
        
        Processo processo = new Processo();/// Instanciamos o objeto que contem as infos do processo atual e dos demais processos da rede
        Criptografia cripto = new Criptografia(processo);//classe que gera as chaves publicas e privadas e as armazena no objeto processo
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin(processo);//instanciamos a tela de login
            }
        });
        processo.conexao_multi = new MulticastPeer(processo);//instanciamos uma nova conexao multicast
        
    }
}
