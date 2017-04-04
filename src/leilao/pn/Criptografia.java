package leilao.pn;

import java.security.*;
import java.security.spec.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import leilao.Processo;

public class Criptografia {

    public PublicKey chavePublica;
    public PrivateKey chavePrivada;     

    private Processo processo;

    public Criptografia(Processo processo) {
        this.processo = processo;
        this.geraChave();
    }

    public void geraChave() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(System.currentTimeMillis());
            keyGen.initialize(1024,random);

            KeyPair key = keyGen.generateKeyPair();

            //salvamos as chaves em suas variaveis    
            this.chavePublica = key.getPublic();
            this.chavePrivada = key.getPrivate();            
                     
            processo.setChave_publica(key.getPublic().getEncoded());
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Criptografa o texto puro usando chave pública.
     */
    public static byte[] criptografa(String texto, PublicKey chave) {
        byte[] cipherText = null;

        try {
            final Cipher cipher = Cipher.getInstance("RSA");
            // Criptografa o texto puro usando a chave Púlica
            cipher.init(Cipher.ENCRYPT_MODE, chave);
            cipherText = cipher.doFinal(texto.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cipherText;
    }

    /**
     * Descriptografa o texto puro usando chave privada.
     */
    public static String descriptografa(byte[] texto, PrivateKey chave) {

        byte[] dectyptedText = null;
        try {
            final Cipher cipher = Cipher.getInstance("RSA");
            // Decriptografa o texto puro usando a chave Privada
            cipher.init(Cipher.DECRYPT_MODE, chave);
            dectyptedText = cipher.doFinal(texto);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new String(dectyptedText);
    }

    public void func() throws InvalidKeySpecException {
        try {            
            byte[] publicKeyBytes = chavePublica.getEncoded();
            
            KeyFactory keyFactory;
            keyFactory = KeyFactory.getInstance("RSA");
                                    
            EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
            PublicKey publicKey2 = keyFactory.generatePublic(publicKeySpec);
            
            // The orginal and new keys are the same
            
            boolean same = chavePrivada.equals(publicKey2);
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Criptografia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Testa o Algoritmo
     */
    public void main(String[] args) {

        try {
            // Método responsável por gerar um par de chaves usando o algoritmo RSA e
            // armazena as chaves nas suas respectivas variáveis.
            geraChave();

            String msgOriginal = "Exemplo de mensagem";

            // Criptografa a Mensagem usando a Chave Pública
            byte[] textoCriptografado = criptografa(msgOriginal, chavePublica);

            // Decriptografa a Mensagem usando a Chave Pirvada
            String textoPuro = descriptografa(textoCriptografado, chavePrivada);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
