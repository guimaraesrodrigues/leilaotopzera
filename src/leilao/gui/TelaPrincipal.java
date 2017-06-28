package leilao.gui;

import java.awt.HeadlessException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import leilao.Processo;
import leilao.Produto;

public class TelaPrincipal extends javax.swing.JFrame {
    String user;    
    DefaultListModel modelListaLeiloes = new DefaultListModel();
    DefaultTableModel modeloTable;
    private Processo processo;
    
    public TelaPrincipal() {
    }
    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal(Processo processo) {
        
        initComponents();
        modeloTable = (DefaultTableModel)jTableLeiloes.getModel();
        usuario.setText("Usuário: "+processo.getNome_usuario());
        this.statusLabel.setText("Pronto!");
        this.processo = processo;
        setVisible(true);
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
        TituloMeusLeiloes = new javax.swing.JLabel();
        usuario = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaMeusLeiloes = new javax.swing.JList<>();
        jButtonSair = new javax.swing.JButton();
        botaoNovoProduto = new javax.swing.JButton();
        jButtonAtualiza = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableLeiloes = new javax.swing.JTable();
        jButtonLance = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextLance = new javax.swing.JTextField();
        botaoCancelarLeilao = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        buttonMeusLances = new javax.swing.JButton();
        jButtonAtt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titulo.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        titulo.setText("Leilão TOPzera!");

        tituloUltimosLeiloes.setText("Meus Leilões TOPzera:");

        TituloMeusLeiloes.setText("Leilões ativos:");

        usuario.setText("USUARIO: "+user);

        jScrollPane3.setViewportView(listaMeusLeiloes);

        jButtonSair.setText("SAIR");
        jButtonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairActionPerformed(evt);
            }
        });

        botaoNovoProduto.setText("Novo Produto TOP");
        botaoNovoProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoNovoProdutoActionPerformed(evt);
            }
        });

        jButtonAtualiza.setText("ATUALIZAR LEILÕES TOPS");
        jButtonAtualiza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAtualizaActionPerformed(evt);
            }
        });

        jTableLeiloes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Descrição", "Preço", "Tempo final"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableLeiloes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableLeiloesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableLeiloes);

        jButtonLance.setText("Novo Lance TOP");
        jButtonLance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLanceActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Valor:");

        jTextLance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextLanceActionPerformed(evt);
            }
        });

        botaoCancelarLeilao.setText("Cancelar Leilão TOP");
        botaoCancelarLeilao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarLeilaoActionPerformed(evt);
            }
        });

        jLabel4.setText("Status: ");

        statusLabel.setText("status");

        buttonMeusLances.setText("Meus Lances");
        buttonMeusLances.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMeusLancesActionPerformed(evt);
            }
        });

        jButtonAtt.setText("Atualizar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonSair))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tituloUltimosLeiloes)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButtonAtt, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(titulo)
                                        .addGap(49, 49, 49)))
                                .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(botaoNovoProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(botaoCancelarLeilao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(TituloMeusLeiloes)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButtonAtualiza, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(3, 3, 3)
                                        .addComponent(jTextLance, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonLance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonMeusLances, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButtonSair)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(usuario)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonAtt))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TituloMeusLeiloes)
                    .addComponent(tituloUltimosLeiloes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoNovoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonLance, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextLance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoCancelarLeilao, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonAtualiza, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonMeusLances)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                            .addComponent(statusLabel))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        
        Object item;
        if(this.listaMeusLeiloes.getSelectedValue() != null){            
          
            int size = listaMeusLeiloes.getModel().getSize(); 
            // Get all item objects
            for (int i = 0; i < size; i++){
                item = listaMeusLeiloes.getModel().getElementAt(i);
                //server.cancelarLeilao(new String(item.toString().substring(0, 5)));
            }
            
        }        
        System.exit(0);        
    }//GEN-LAST:event_jButtonSairActionPerformed

    private void botaoNovoProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNovoProdutoActionPerformed
        // TODO add your handling code here:
        NovoProduto tnp = new NovoProduto(processo);
    }//GEN-LAST:event_botaoNovoProdutoActionPerformed

    private void jButtonAtualizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAtualizaActionPerformed
        //novo metodo para preencher jtable
        atualizaJtable();
        
        atualizaMeusLeiloes();
        for(Processo p : processo.getLista_usuarios())            
            System.out.println(""+p.getNome_usuario() + " "+p.getPorta_usuario());
    }//GEN-LAST:event_jButtonAtualizaActionPerformed

    private void jButtonLanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLanceActionPerformed
        float valor_lance;        
        
        valor_lance = Float.valueOf(jTextLance.getText());
        
        if(jTableLeiloes.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null, "Selecione um produto!");
            return;
        }
        if(new String(""+modeloTable.getValueAt(jTableLeiloes.getSelectedRow(), 0)).substring(0, 3).equals(user.substring(0, 3))){
            JOptionPane.showMessageDialog(null, "Você não pode dar lances em seus produtos!");
            return;
        }
        
        //String msg_retorno = server.registarLance("" + modeloTable.getValueAt(jTableLeiloes.getSelectedRow(), 0), valor_lance, this.client);
        //this.statusLabel.setText(msg_retorno);          
        
        this.atualizaJtable();             
        
    }//GEN-LAST:event_jButtonLanceActionPerformed

    private void jTextLanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextLanceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextLanceActionPerformed

    private void jTableLeiloesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableLeiloesMouseClicked
        jTextLance.setText(""+modeloTable.getValueAt(jTableLeiloes.getSelectedRow(), 3));
    }//GEN-LAST:event_jTableLeiloesMouseClicked

    private void botaoCancelarLeilaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarLeilaoActionPerformed
        if(this.listaMeusLeiloes.getSelectedValue() == null)
            this.statusLabel.setText("Selecione um produto primeiro!");            
        else{                       
            //server.cancelarLeilao(listaMeusLeiloes.getSelectedValue().substring(0, 5));
           
        }
            
    }//GEN-LAST:event_botaoCancelarLeilaoActionPerformed

    private void buttonMeusLancesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMeusLancesActionPerformed
        if(jTableLeiloes.getSelectedRow() != -1)
           new TelaLance();
        else
            this.statusLabel.setText("Selecione um produto primeiro!");
    }//GEN-LAST:event_buttonMeusLancesActionPerformed

    /*TODO: Reescrever esse método para atualizar meus leiloes*/
    public void atualizaMeusLeiloes(){
        modelListaLeiloes.clear();
       
        /*for(Leilao l : server.leiloes_ativos()){
            String s = l.getCodigo()+" : "+l.getNome();

            if(this.user.equals(l.getNome_usuario())){
                modelListaLeiloes.addElement(s);
                listaMeusLeiloes.setModel(modelListaLeiloes);
            }
        }*/
        
    }
    
    //esse metodo atualiza os leiloes ativos
    private void atualizaJtable(){
        modeloTable.setRowCount(0);//limpamos a tabela 
        /*try {
            for(Leilao p : server.leiloes_ativos()){
                if(p.getTempofinal() > 0)
                    modeloTable.insertRow(modeloTable.getRowCount(), new Object[]{p.getCodigo(), p.getNome(), p.getDescricao(), p.getValor(), p.getTempofinal()});       
            }
        } catch (RemoteException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }*/
       
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TituloMeusLeiloes;
    private javax.swing.JButton botaoCancelarLeilao;
    private javax.swing.JButton botaoNovoProduto;
    private javax.swing.JButton buttonMeusLances;
    private javax.swing.JButton jButtonAtt;
    private javax.swing.JButton jButtonAtualiza;
    private javax.swing.JButton jButtonLance;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableLeiloes;
    private javax.swing.JTextField jTextLance;
    private javax.swing.JList<String> listaMeusLeiloes;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JLabel titulo;
    private javax.swing.JLabel tituloUltimosLeiloes;
    private javax.swing.JLabel usuario;
    // End of variables declaration//GEN-END:variables
}
