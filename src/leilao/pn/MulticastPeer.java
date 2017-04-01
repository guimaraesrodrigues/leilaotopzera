package leilao.pn;

import leilao.Processo;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MulticastPeer extends Thread {

    private InetAddress group;//ip para o grupo multicast
    private MulticastSocket socket; // O mesmo socket para send e receive ...   
    private PeerReceive p;
    private Processo sessao;

    public MulticastPeer(Processo s) {

        this.socket = null;
        this.sessao = s;
        Properties props = System.getProperties();
        props.setProperty("java.net.preferIPv4Stack","true");
        System.setProperties(props);
        this.start();
    }

    public void run() {
        try {
            this.group = InetAddress.getByName("224.1.2.3");
            this.socket = new MulticastSocket(6789);
            this.socket.joinGroup(group);
            DatagramPacket messageOut;
            //socket.setTimeToLive(5);
            this.p = new PeerReceive(this.socket, this.sessao); // Iniciando thread do receive (socket como parâmetro) ...

            Scanner scan = new Scanner(System.in);
            byte[] message = new byte[1000];
            message = "Multicast iniciado na porta 6789!".getBytes();
            messageOut = new DatagramPacket(message, message.length, this.group, 6789);
            this.socket.send(messageOut);
            
            while (!message.equals("sair")) { // Enquanto não digitar 'sair' fica no loop ...

                message = scan.nextLine().getBytes();
                messageOut = new DatagramPacket(message, message.length, this.group, 6789);

                socket.send(messageOut);                
            }
            
            this.socket.leaveGroup(this.group);            

        } catch (SocketException e) {

            System.out.println("Socket: " + e.getMessage());

        } catch (IOException e) {

            System.out.println("IO: " + e.getMessage());

        } finally {

            if (socket != null) {
                socket.close();
            }
        }
    }

    /*Método que enviará mensagens pela rede p2p*/
    public void enviaMensagem(byte[] message) {

        try {
            DatagramPacket messageOut;

            messageOut = new DatagramPacket(message, message.length, this.group, 6789);

            this.socket.send(messageOut);//com o socket já instanciado, envamos o datagrampacket    

        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }
}

//Classe que cria uma thread para receber as mensagens multicast
class PeerReceive extends Thread {

    private final MulticastSocket s;
    private Processo sessao;

    public PeerReceive(MulticastSocket s, Processo sessao) {

        this.sessao = sessao;
        this.s = s;
        this.start(); // Inicia thread ...
    }

    public void run() {

        try {
            byte[] message = new byte[1000];            
            while (true) { // Loop para ficar recebendo mensagens ...

                byte[] buffer = new byte[1000];
                DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);                

                s.receive(messageIn);
                message = messageIn.getData();
                System.out.println(""+ new String(message));
                if (message[0] == '0') {
                    //mensagem_novoUser(message);
                }
                else if (message[0] == '~'){
                    //sessao.limpaLista_usuarios();
                    //mensagem_novoBD(message);
                }
            }
        } catch (IOException ex) {
            System.out.println("Erro: "+ex.toString());
        }
    }
    
    private void mensagem_novoUser(String m) {
        String nome = null;
        float moedas = 0, preco = 0;
        int porta = 0, versao_bd;
        byte[] chave_pub;
        
        String[] lista_dados = m.split("\\|");  
        
        nome = lista_dados[1];        
        moedas = Float.parseFloat(lista_dados[2]);                
        preco = Float.parseFloat(lista_dados[3]);      
        porta = Integer.parseUnsignedInt(lista_dados[4].trim());
        
        chave_pub = new byte[lista_dados[5].trim().length()];
        chave_pub = (lista_dados[5].getBytes());      
        
        System.out.println("");
//        if (porta != sessao.getPortaUDP()) {
//            sessao.adicionaUsuario(nome, moedas, preco, porta, 5);
//            new UDPClient(sessao.lista_usuariosToString(), "localhost", porta);
//        }
    }
    
    private void mensagem_novoBD(String m) {

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