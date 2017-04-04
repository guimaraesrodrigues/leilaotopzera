package leilao;

import leilao.gui.TelaLogin;
import leilao.pn.Criptografia;
import leilao.pn.MulticastPeer;

public class Main {
    public static void main(String[] args) {
        
        Processo processo = new Processo();
        Criptografia cripto = new Criptografia(processo);
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin(processo);
            }
        });
        processo.conexao_multi = new MulticastPeer(processo);
        
    }
}
