package leilao;

import java.io.Serializable;
import java.util.ArrayList;
import leilao.gui.TelaLance;
import leilao.pn.MulticastPeer;
import leilao.pn.UDPClient;
        
public class Processo implements Serializable{
    private int porta_usuario;
    private String nome_usuario;
    private byte[] chave_publica;
    private ArrayList<Produto> lista_produtos;
    private ArrayList<Processo> lista_usuarios;
    public MulticastPeer conexao_multi;
    private int contador_produtos;
    
    public Processo() {
        this.contador_produtos = 0;
        lista_produtos = new ArrayList<Produto>();
    }   
    /* Adiciona um novo usuario na lista de usuarios */
    public void adicionaUsuario(int porta, String nome, byte[]chave){
        
        if(lista_usuarios == null)
            lista_usuarios = new ArrayList<Processo>();
        
        Processo novo_usuario = new Processo();
        
        novo_usuario.setPorta_usuario(porta);
        novo_usuario.setNome_usuario(nome);
        novo_usuario.setChave_publica(chave);
        
        this.lista_usuarios.add(novo_usuario);       
        
    }
    //adiciona um novo produto na lista de produtos
    public void adicionaNovoProduto(Produto prod){
        if(lista_produtos == null)
            lista_produtos = new ArrayList<Produto>();
        lista_produtos.add(prod);
        //tp.addListaMeusLeiloes();
        contador_produtos++;        
    }
       //transforma a lista de produtos em um vetor de bytes para ser enviado pela rede
    public byte[] lista_produtosToByte(){       
                
         String dados_produtos = new String();
         //concatenamos todos os dados na string dados_produtos
        for(Produto p : this.lista_produtos){
            
            dados_produtos = dados_produtos.concat(new String("&" +p.getNome()+"|"+p.getCodigo()+"|"
                    +p.getDescricao()+"|"+p.getValor()+"|"+p.getTempofinal()));                 
        }                
        
        return dados_produtos.getBytes();//retornamos a string em formato de vetor de bytes
    }
    
    public String lista_produtosToString(){
        String lista_produtos = new String();
        
        return lista_produtos;
    }
    
    /*Método que transforma o banco de dados em um vetor de bytes*/
    public byte[] lista_usuariosTobyte(){
        byte[] lista_usuarios = new byte[4096];
        int tamanho_lista = 0;
        
        for(Processo p : this.lista_usuarios){
            //System.out.println("OI "+p.getPorta_usuario());
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

    public ArrayList<Processo> getLista_usuarios() {
        return lista_usuarios;
    }

    public void setLista_usuarios(ArrayList<Processo> lista_usuarios) {
        this.lista_usuarios = lista_usuarios;
    }
    
    public void limpaBD(){
        this.lista_usuarios = new ArrayList<Processo>();
    }
    
    public int getContador_produtos() {
        return contador_produtos;
    }
}