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
import leilao.Produto;

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
            byte[] message = new byte[4096];
            
            
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

                byte[] buffer = new byte[4096];
                DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length); 
                
    
                s.receive(messageIn);
                buffer = messageIn.getData();                
                
                //impressao da mensagem apenas para debug
                //for (int i = 0; i < message.length; i++)
                //    System.out.print(message[i]);
                
                if (buffer[0] == '0') {
                    mensagem_novoUser(buffer);
                }
                else if (buffer[0] == '~'){
                    //sessao.limpaLista_usuarios();
                    //mensagem_novoBD(message);
                }
                else if (buffer[0] == '1'){                    
                    mensagemNovoProduto(buffer);
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
            //enviamos a lista de usuarios para o novo processo da rede
            
            byte[] lista_usuarios =  processo.lista_usuariosTobyte();           
                       
            new UDPClient(lista_usuarios, "localhost", porta_novo_usuario);
            
            if(!processo.getLista_produtos().isEmpty());
                new UDPClient(processo.lista_produtosToByte(), "localhost", porta_novo_usuario);
            
        }
    }
    
    private void mensagemNovoProduto(byte[] m){
        String mensagem = new String(m);
        for(String s : mensagem.split("\\|"))
            System.out.println(""+s);
    }
    
    private void mensagem_novoProduto(byte[] m){
               
        int p_nome = 0, p_cod = 0, p_desc = 0, p_valor = 0, p_time = 0;
        int i;   
        
        //Primeiramente encontramos a posição de cada dado no vetor da mensagem      
        for (i = 1; i < m.length; i++ ){
            if(m[i] == '|'){
                p_nome = i;
                break;
            }
        }
        for (i = p_nome+1; i < m.length; i++){
             if(m[i] == '|'){
                p_cod = i;
                break;
            }
        }
        for (i = p_cod+1; i < m.length; i++ ){
            if(m[i] == '|'){
                p_desc = i;
                break;
            }
        }
        for (i = p_desc+1; i < m.length; i++ ){
            if(m[i] == '|'){
                p_valor = i;
                break;
            }
        }
        for (i = p_valor+1; i < m.length; i++ ){
            if(m[i] == '|'){
                p_time = i;
                break;
            }
        }
        //System.out.println("Nome: "+p_nome+" Cod: "+p_cod+" Desc: "+p_desc+" Valor: "+p_valor+" Tempo: "+p_time);
        byte[] bnome = new byte[p_nome-1];
        //System.out.println(bnome.length);
        byte[] bcod = new byte[p_cod - p_nome];
        //System.out.println(bcod.length);
        byte[] bdesc = new byte[p_desc - p_cod];
        //System.out.println(bdesc.length);
        byte[] bvalor = new byte[p_valor - p_desc];
        //System.out.println(bvalor.length);
        byte[] btime = new byte[p_time - p_valor];
        //System.out.println(btime.length);
        
        bnome = Arrays.copyOfRange(m, 1, p_nome);
        bcod = Arrays.copyOfRange(m, p_nome+1, p_cod);
        bdesc = Arrays.copyOfRange(m, p_cod+1, p_desc);
        bvalor = Arrays.copyOfRange(m, p_desc+1, p_valor);
        btime = Arrays.copyOfRange(m, p_valor+1, p_time);
        
        int porta = Integer.parseInt(new String(Arrays.copyOfRange(m, p_nome+1, p_cod-1)));
        Produto novoP = new Produto();
            
        if(porta != this.processo.getPorta_usuario()){
            novoP.setCodigo(new String(bcod));
            novoP.setNome(new String (bnome));
            novoP.setDescricao(new String(bdesc));
            novoP.setValor(Float.parseFloat(new String(bvalor)));
            novoP.setTempofinal(Float.parseFloat(new String(btime)));
            this.processo.adicionaProdutoRecebido(novoP);
        }
        
               
    }    
}