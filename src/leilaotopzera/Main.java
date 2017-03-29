package leilaotopzera;

import leilaotopzera.gui.TelaLogin;
import leilaotopzera.pn.MulticastPeer;

public class Main {
    public static void main(String[] args) {
        Processo processo = new Processo();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin(processo);
            }
        });
        
        processo.conexao_multi = new MulticastPeer(processo);
        
    }
}
