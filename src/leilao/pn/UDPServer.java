package leilao.pn;

import leilao.Processo;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * Classe que cria um servidor UDP. Herda de Thread pois é um servidor que fica
 * em constante operação esperando que clientes requisitem alguma transação de
 * leilão, por exemplo*
 */
public class UDPServer extends Thread {

    DatagramSocket aSocket = null;
    int porta;
    Processo processo;

    /*construtor*/
    public UDPServer(int porta, Processo p) {
        this.porta = porta;//inicializamos o este campo com a porta recebida no parametro
        processo = p;
        this.start();//iniciamos a thread do servidor UDP
    }

    /*Método que executa a thread do servidor*/
    public void run() {
        try {
            aSocket = new DatagramSocket(this.porta);//cria um socket com a porta recebida no construtor
            byte[] buffer = new byte[4096];//buffer para armazenar as mensagens
           // aSocket.setSoTimeout(1000);
            /*looping infinito para receber mensagens dos demais clientes*/
            boolean bd_new = true;
            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(),
                        request.getAddress(), request.getPort());
               
                byte[] message = new byte[4096];
                message = request.getData();
                if (message[0] == '~' && bd_new) {
                    this.processo.limpaBD();
                    separaNovoBD(message);
                    bd_new = false;
                }
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

/*O método abaixo realiza o tratamento da mensagem de novo BD e envia os dados para
o método Processo.adicionaUsuário para a criação de novo usuário e inserção do mesmo no BD*/
    private void separaNovoBD(byte[] m) {

        String nome = null;
        byte[] chave;
        int i, elementos = 0, posicao_porta = 0, posicao_nome = 0, posicao_chave = 0;
        
        //Percorremos a mensagem inteira até que encontremos o último '~'
        for(int j = posicao_porta+1 ; m[j-1] == '~' ; j = posicao_chave+3){
            
            //a cada iteração do 'for' superior percorremos um 'subvetor' de m para localizar
            //cada um dos dados de cada processo. Aqui o procedimento é o mesmo que é utilizado
            //na classe multicast
            for (i = j; m[i] != '|'; i++ ){
                if(m[i+1] == '|'){
                    posicao_porta = i;
                }
            }
            for (i = posicao_porta+2; m[i] != '|'; i++){
                 if(m[i+1] == '|'){
                    posicao_nome = i;
                    break;
                }
            }
            for (i = posicao_nome+2; m[i] != '|' ; i++ ){
                if(m[i+1] == '|')
                    posicao_chave = i;
            }
            
            byte[] p = new byte[posicao_porta-(j-1)];
            byte[] n = new byte[posicao_nome-posicao_porta];        
            byte[] c = new byte[posicao_chave-posicao_nome];
            
            p = Arrays.copyOfRange(m, j, posicao_porta+1);
            n = Arrays.copyOfRange(m, posicao_porta+2, posicao_nome+1);
            c = Arrays.copyOfRange(m, posicao_nome+2, posicao_chave+1);
            
            
            System.out.println(new Integer(new String(p)));
            for(int k = 0; k<n.length ; k++)
                System.out.print((char)n[k]);
            System.out.println("");
            for(int k = 0; k<c.length ; k++)
                System.out.print(c[k]);
            System.out.println("");
            processo.adicionaUsuario(new Integer(new String(p)),new String(n),c);
        }
    }

}