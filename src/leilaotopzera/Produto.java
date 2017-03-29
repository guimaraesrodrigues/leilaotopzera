package leilaotopzera;
import java.util.ArrayList;


public class Produto {
    private int codigo;
    private String nome;
    private String descricao;
    private float precoin;
    private float tempofinal;
    private ArrayList<Lance> lances;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
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

    public float getPrecoin() {
        return precoin;
    }

    public void setPrecoin(float precoin) {
        this.precoin = precoin;
    }

    public float getTempofinal() {
        return tempofinal;
    }

    public void setTempofinal(float tempofinal) {
        this.tempofinal = tempofinal;
    }
}
