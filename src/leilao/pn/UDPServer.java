package leilao.pn;

import leilao.Processo;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
import leilao.Produto;

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
           //buffer para armazenar as mensagens
           // aSocket.setSoTimeout(1000);
            /*looping infinito para receber mensagens dos demais clientes*/
            boolean bd_new = true;
            while (true) {
                byte[] buffer = new byte[4096];
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(),
                        request.getAddress(), request.getPort());
                
                if (buffer[0] == '~' && bd_new) {
                    this.processo.limpaBD();
                    separaNovoBD(buffer);
                    bd_new = false;
                }
                if (buffer[0] == '&') {
                    separaListaProdutos(buffer);
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
            ///adicionamos na lista de processos o novo usuario recebido
            processo.adicionaUsuario(new Integer(new String(p)),new String(n),c);
        }
    }
    /* metodo para tratar a mensagem com lista de produtos  */
    public void separaListaProdutos(byte[] m){
        String mensagem = new String(m);
        StringTokenizer st = new StringTokenizer(mensagem, "&");//esse objeto armazena uma coleção de vetor de strings
        
        String[] lista_dados;        
        String s = new String();
        Produto novo_produto = new Produto();
         //cada vetor de string representa um produto     
        while (st.hasMoreElements()) {
            s = st.nextToken();            
            lista_dados = s.split("\\|");
            novo_produto.setNome(lista_dados[0]);
            novo_produto.setCodigo(lista_dados[1]);
            novo_produto.setDescricao(lista_dados[2]);
            novo_produto.setValor(Float.parseFloat(lista_dados[3]));
            novo_produto.setTempofinal(Float.parseFloat(lista_dados[4]));
            
            for(Produto p :processo.getLista_produtos() )
                if(p.getCodigo() == novo_produto.getCodigo())
                   return;                                    
                else
                    processo.getLista_produtos().add(novo_produto);
        }
    }

}