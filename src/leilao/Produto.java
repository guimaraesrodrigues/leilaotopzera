package leilao;
import java.util.ArrayList;


public class Produto {
    private String codigo;
    private String nome;
    private String descricao;
    private float valor;
    private Float tempofinal;
    private boolean ativo;
    private ArrayList<Lance> lances;
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float precoin) {
        this.valor = precoin;
    }

    public float getTempofinal() {
        return tempofinal;
    }

    public void setTempofinal(float tempofinal) {
        this.tempofinal = tempofinal;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
