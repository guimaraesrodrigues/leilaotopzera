package leilao;

import java.io.Serializable;
import java.util.ArrayList;
import leilaotopzera.pn.MulticastPeer;
        
public class Processo implements Serializable{
    private int id_usuario;
    private String nome_usuario;
    private byte[] chave_publica;
    private ArrayList<Produto> lista_produtos;
    private ArrayList<Processo> usuarios;
    public MulticastPeer conexao_multi;

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
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
}
