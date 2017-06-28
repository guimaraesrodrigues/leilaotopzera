/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.DefaultListModel;
import leilao.Processo;
import leilao.Produto;

/**
 *
 * @author Guilherme
 */
public class TelaPrincipal_old extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal
     */
    private Processo processo;
    DefaultListModel listaTodosLeiloes = new DefaultListModel();
    DefaultListModel listaMLeiloes = new DefaultListModel();
    
    public TelaPrincipal_old(Processo processo) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        initComponents();
        usuario.setText("Usuário: "+processo.getNome_usuario());
        this.setVisible(true);
        this.processo = processo;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titulo = new javax.swing.JLabel();
        tituloUltimosLeiloes = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaUltimosLeiloes = new javax.swing.JList<>();
        TituloMeusLeiloes = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaMeusLeiloes = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaMeusLances = new javax.swing.JList<>();
        tituloMeusLances = new javax.swing.JLabel();
        botaoNovoProduto = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        listarButton = new javax.swing.JButton();
        buttonProdutos = new javax.swing.JButton();
        usuario = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titulo.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        titulo.setText("Leilão TOPzera!");

        tituloUltimosLeiloes.setText("Últimos Leilões:");

        listaUltimosLeiloes.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jScrollPane1.setViewportView(listaUltimosLeiloes);

        TituloMeusLeiloes.setText("Meus Leilões:");

        listaMeusLeiloes.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jScrollPane2.setViewportView(listaMeusLeiloes);

        listaMeusLances.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jScrollPane3.setViewportView(listaMeusLances);

        tituloMeusLances.setText("Meus Lances:");

        botaoNovoProduto.setBackground(new java.awt.Color(204, 204, 204));
        botaoNovoProduto.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        botaoNovoProduto.setText("Novo Produto TOP!");
        botaoNovoProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoNovoProdutoActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jButton1.setText("Dar lance em um produto TOP!");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        listarButton.setText("Listar users");
        listarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarButtonActionPerformed(evt);
            }
        });

        buttonProdutos.setText("Meus produtos");
        buttonProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonProdutosActionPerformed(evt);
            }
        });

        usuario.setText("USUARIO");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tituloUltimosLeiloes)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jButton1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TituloMeusLeiloes)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(botaoNovoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tituloMeusLances)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(listarButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonProdutos))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(titulo)
                        .addGap(18, 18, 18)
                        .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titulo)
                    .addComponent(usuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tituloMeusLances)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tituloUltimosLeiloes)
                            .addComponent(TituloMeusLeiloes, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoNovoProduto)
                    .addComponent(jButton1)
                    .addComponent(listarButton)
                    .addComponent(buttonProdutos))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoNovoProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNovoProdutoActionPerformed
        NovoProduto np = new NovoProduto(processo);
    }//GEN-LAST:event_botaoNovoProdutoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //TelaLance tl = new TelaLance(processo);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void listarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarButtonActionPerformed
        for (Processo p : processo.getLista_usuarios())
            System.out.println("Nome: " + p.getNome_usuario() + " - Porta: " + p.getPorta_usuario());
    }//GEN-LAST:event_listarButtonActionPerformed

    private void buttonProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonProdutosActionPerformed
        for (Produto p : processo.getLista_produtos()){
            System.out.println("Código: "+ p.getCodigo()+ " - " + "Nome: "+ p.getNome());
        }
    }//GEN-LAST:event_buttonProdutosActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
        //</editor-fold>
////
//        /* Create and display the form */
////        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new TelaPrincipal().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TituloMeusLeiloes;
    private javax.swing.JButton botaoNovoProduto;
    private javax.swing.JButton buttonProdutos;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> listaMeusLances;
    private javax.swing.JList<String> listaMeusLeiloes;
    private javax.swing.JList<String> listaUltimosLeiloes;
    private javax.swing.JButton listarButton;
    private javax.swing.JLabel titulo;
    private javax.swing.JLabel tituloMeusLances;
    private javax.swing.JLabel tituloUltimosLeiloes;
    private javax.swing.JLabel usuario;
    // End of variables declaration//GEN-END:variables
    
    public void addListaUltimosLeiloes(){
        listaTodosLeiloes.clear();
        for(Produto p : processo.getLista_produtos()){
            String s = "COD: "+ p.getCodigo()+" Produto: "+p.getNome()+" Desc.: "+p.getDescricao()+" R$: "+ p.getValor();
            if(!p.getCodigo().substring(0, 4).equals(""+processo.getPorta_usuario())){
                listaTodosLeiloes.addElement(s);
                listaUltimosLeiloes.setModel(listaTodosLeiloes);
            }
        }
    }
    
    public void addListaMeusLeiloes(){
        listaMLeiloes.clear();
        for(Produto p : processo.getLista_produtos()){
            String s = "COD: "+ p.getCodigo()+" Produto: "+p.getNome()+" Desc.: "+p.getDescricao()+" R$: "+ p.getValor();
            if(p.getCodigo().substring(0, 4).equals(""+processo.getPorta_usuario())){
                listaMLeiloes.addElement(s);
                listaMeusLeiloes.setModel(listaMLeiloes);
            }
        }
    }
}
