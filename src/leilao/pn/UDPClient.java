package leilao.pn;

import java.net.*;
import java.io.*;

public class UDPClient {

    DatagramSocket aSocket;
    byte[] mensagem;
    String ip_addr;
    int porta;    
    
    public UDPClient(byte[] mensagem, String ip_addr, int porta) {
        this.mensagem = mensagem;
        this.ip_addr = ip_addr;
        this.porta = porta;
        this.enviaMensagem();
    }    
    
    public void enviaMensagem(){
        try {
            aSocket = new DatagramSocket();
            InetAddress aHost = InetAddress.getByName(ip_addr);
            int serverPort = porta;
            DatagramPacket request
                    = new DatagramPacket(mensagem, mensagem.length, aHost, serverPort);
            aSocket.send(request);
            byte[] buffer = new byte[4096];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);
            //System.out.println("Reply: " + new String(reply.getData()));
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
}