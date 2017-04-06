package leilao;

import java.io.Serializable;
import java.util.ArrayList;
import leilao.pn.MulticastPeer;
        
public class Processo implements Serializable{
    private int porta_usuario;
    private String nome_usuario;
    private byte[] chave_publica;
    private ArrayList<Produto> lista_produtos;
    private ArrayList<Processo> usuarios;
    public MulticastPeer conexao_multi;

    public void adicionaUsuario(int porta, String nome, byte[]chave){
        
        if(usuarios == null)
            usuarios = new ArrayList<Processo>();
        
        Processo novo_usuario = new Processo();
        
        novo_usuario.setPorta_usuario(porta);
        novo_usuario.setNome_usuario(nome);
        novo_usuario.setChave_publica(chave);
        
        this.usuarios.add(novo_usuario);
    }
    
    /*Método que transforma o banco de dados em um vetor de bytes*/
    public byte[] lista_usuariosTobyte(){
        byte[] lista_usuarios = new byte[4096];
        int tamanho_lista = 0;
                
        for(Processo p : this.usuarios){
            String dados_processo = new String("~" + p.getPorta_usuario() + "|" + p.getNome_usuario()+ "|");
            byte[] chave_pub = new byte[p.getChave_publica().length+"|".getBytes().length];
            
            //Concatenamos a chave pública com o pipe
            System.arraycopy(p.getChave_publica(),0,chave_pub,0,p.getChave_publica().length);
            System.arraycopy("|".getBytes(), 0, chave_pub, p.getChave_publica().length, "|".getBytes().length);
            
            //Primeiro colocamos os dados do processo no vetor lista_usuarios e
            //em seguida inserimos a chave publica no vetor lista_usuarios
            System.arraycopy(dados_processo.getBytes(), 0, lista_usuarios, tamanho_lista, dados_processo.getBytes().length);
            System.arraycopy(chave_pub, 0, lista_usuarios, tamanho_lista+dados_processo.length() , chave_pub.length);
            
            tamanho_lista+=dados_processo.getBytes().length + chave_pub.length;
        }        
        
        return lista_usuarios;
    }
    
    
    
    public int getPorta_usuario() {
        return porta_usuario;
    }

    public void setPorta_usuario(int id_usuario) {
        this.porta_usuario = id_usuario;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public byte[] getChave_publica() {
        return chave_publica;
    }

    public void setChave_publica(byte[] chave_publica) {
        this.chave_publica = chave_publica;
    }

    public ArrayList<Produto> getLista_produtos() {
        return lista_produtos;
    }

    public void setLista_produtos(ArrayList<Produto> lista_produtos) {
        this.lista_produtos = lista_produtos;
    }

    public ArrayList<Processo> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Processo> usuarios) {
        this.usuarios = usuarios;
    }
    
    public void limpaBD(){
        this.usuarios = null;
    }
}