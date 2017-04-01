package leilao;

public class Lance {

    private final int usuario;
    private final float lance;
    
    public Lance(int usuario, float lance) {
        this.usuario = usuario;
        this.lance = lance;
    }
    
    public int getUsuario() {
        return usuario;
    }

    public float getLance() {
        return lance;
    }
}
