                                                                        /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leilaotopzera.pn;

import leilaotopzera.Processo;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * Classe que cria um servidor UDP. Herda de Thread pois é um servidor que fica
 * em constante operação esperando que clientes requisitem alguma transação de
 * bitcoins, por exemplo*
 */
public class UDPServer extends Thread {

    DatagramSocket aSocket = null;
    int porta;
    Processo sessao;

    /*construtor*/
    public UDPServer(int porta, Processo b) {
        this.porta = porta;//inicializamos o este campo com a porta recebida no parametro
        sessao = b;
        this.start();//iniciamos a thread do servidor UDP
    }

    /*Método que executa a thread do servidor*/
    public void run() {
        try {
            aSocket = new DatagramSocket(this.porta);//cria um socket com a porta recebida no construtor
            byte[] buffer = new byte[1000];//buffer para armazenar as mensagens
           // aSocket.setSoTimeout(1000);
            /*looping infinito para receber mensagens dos demais clientes*/
            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(),
                        request.getAddress(), request.getPort());
               
                String message = new String(request.getData());

//                if (message.codePointAt(0) == '~') {
//                    sessao.limpaLista_usuarios();
//                    separaMensagem(message);
//                }
                aSocket.send(reply);
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSocket != null) {
                aSocket.close();
            }
        }
    }

    private void separaMensagem(String m) {

        String nome = null;
        float moedas = 0, preco = 0;
        int porta = 0, versao_bd;

        StringTokenizer st = new StringTokenizer(m, "~");        
        String[] lista_dados;        
        String s = new String();
        
        while (st.hasMoreElements()) {
            s = st.nextToken();            
            lista_dados = s.split("\\|");
            
            nome = lista_dados[0];
            moedas = Float.parseFloat(lista_dados[1]);
            preco = Float.parseFloat(lista_dados[2]);
            porta = Integer.parseUnsignedInt(lista_dados[3].trim());           
            versao_bd = Integer.parseUnsignedInt(lista_dados[4].trim());
//            sessao.adicionaUsuario(nome, moedas, preco, porta, versao_bd);
        }
    }
}
