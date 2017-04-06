package leilao.pn;

import leilao.Processo;
import java.net.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MulticastPeer extends Thread {

    private InetAddress group;//ip para o grupo multicast
    private MulticastSocket socket; // O mesmo socket para send e receive ...   
    private PeerReceive p;
    private Processo processo;

    public MulticastPeer(Processo p) {

        this.socket = null;
        this.processo = p;
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
            this.p = new PeerReceive(this.socket, this.processo); // Iniciando thread do receive (socket como parâmetro) ...
            byte[] message = new byte[1000];
            
            
            /*TODO: Remover a entrada de dados pelo console */
            Scanner scan = new Scanner(System.in);//um scanner para entrada de dados no console            
            
            /*Enviamos uma mensagem teste apenas para ter certeza que o processo entrou na rede*/
            //message = "Multicast iniciado na porta 6789!".getBytes();
            //messageOut = new DatagramPacket(message, message.length, this.group, 6789);
            //this.socket.send(messageOut);
            
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
    private Processo processo;

    public PeerReceive(MulticastSocket s, Processo processo) {

        this.processo = processo;
        this.s = s;
        this.start(); // Inicia thread ...
    }

    public void run() {

        try {
            byte[] message;            
            while (true) { // Loop para ficar recebendo mensagens ...

                byte[] buffer = new byte[1000];
                DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length); 
                
    
                s.receive(messageIn);
                message = messageIn.getData();                
                
                //impressao da mensagem apenas para debug
                //for (int i = 0; i < message.length; i++)
                //    System.out.print(message[i]);
                
                if (message[0] == '0') {
                    mensagem_novoUser(message);
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
    
    
    private void mensagem_novoUser(byte[] m) {
                
        int posicao_nome = 0, posicao_porta = 0, posicao_chave = 0;
        int i;   
        
        //Primeiramente encontramos a posição de cada dado no vetor da mensagem      
        for (i = 2; i < m.length; i++ ){
            if(m[i] == '|'){
                posicao_porta = i;
                break;
            }
        }
        for (i = posicao_porta+1; i < m.length; i++){
             if(m[i] == '|'){
                posicao_nome = i;
                break;
            }
        }
        for (i = posicao_nome+1; i < m.length; i++ ){
            if(m[i] == '|')
                posicao_chave = i;
        }        
        
        //inicializamos vetores de byte para cada dado com seus respectivos tamanhos
        byte[] porta = new byte[posicao_porta-2];
        byte[] nome = new byte[posicao_nome-posicao_porta];        
        byte[] chave_pub = new byte[posicao_chave-posicao_nome];
        
        //copiamos cada um dos dados do vetor de origem 'm' para seus respectivos
        //vetores
        porta = Arrays.copyOfRange(m, 2, posicao_porta);
        nome = Arrays.copyOfRange(m, posicao_porta+1, posicao_nome);
        chave_pub = Arrays.copyOfRange(m, posicao_nome+1, posicao_chave);       
        
                
        int porta_novo_usuario = new Integer(new String(porta));
        
        
        if (porta_novo_usuario != this.processo.getPorta_usuario()) {
            processo.adicionaUsuario(porta_novo_usuario, new String(nome), chave_pub);
            
            try {
                this.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(PeerReceive.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            new UDPClient(processo.lista_usuariosTobyte(), "localhost", porta_novo_usuario);
        }
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