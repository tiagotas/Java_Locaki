/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Cliente.java
 *
 * Created on 21/04/2010, 18:18:15
 */

package visualizacao;

import controle.ClienteCRUD;
import controle.EnderecoCRUD;
import controle.TelefoneCRUD;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;
import modelo.ClienteContadores;
import modelo.ClienteLocacoes;
import modelo.Endereco;
import modelo.Telefone;
import visualizacao.Relatorios.*;

/**
 *
 * @author Tiago
 */
public class EdiCliente extends javax.swing.JFrame {

       ////// CLIENTES //////
    CadCliente cc = null;

    ////// FILMES //////

    CadFilme cf = null;

    ////// GENEROS //////

    CadGenero cg = null;

    ////// PROMOCOES //////

    CadPromocao cp = null;

    ////// LOCACOES //////

    CadLocacao cl = null;
    /** Creates new form Cliente */

    private int idCliente;

    public EdiCliente() {
        initComponents();
        this.setBounds(420, 250, 577, 371);
    }

    public void setIdCliente(int idCliente)
    {
        this.idCliente = idCliente;
        this.initMetodos();
    }

    public void initMetodos()
    {
        this.setDadosCliente();
        this.setContadores();

        this.carregarTebelaEnderecos();
        this.carregarTebelaTelefones();
        this.carregarTebelaLocacoes();
    }



    public void setDadosCliente()
    {
        try
        {
            ClienteCRUD cCrud = new ClienteCRUD();

            Cliente cDados = cCrud.getDadosCliente(this.idCliente);

            codigo.setText(String.valueOf(cDados.getIdCliente()));
            dataCadastro.setValue(cDados.getDataCadastro());
            nome.setText(cDados.getNome());
            rg.setText(cDados.getRg());
            cpf.setText(cDados.getCpf());
            cObservacoes.setText(cDados.getObservacoes());

        } catch(SQLException e)
        {
             JOptionPane.showMessageDialog(this, "Desculpe, ocorreu um erro: \n "  + e.getMessage(), "Locaki ~ A Sua Locadora!", 0);
        }
    }

    public void setContadores()
    {
        try
        {
            ClienteCRUD cCrud = new ClienteCRUD();

            ClienteContadores cDados = cCrud.getContadores(this.idCliente);
            DecimalFormat df = new DecimalFormat("#,###0.00");

            totalLocacoesPendentes.setText(String.valueOf(cDados.getTotalLocacoesPendentes()));
            totalLocacoesDevolvidasPrazo.setText(String.valueOf(cDados.getTotalLocacoesDevolvidasNoPrazo()));
            totalLocacoesAtrasadas.setText(String.valueOf(cDados.getTotalLocacoesAtradas()));

            totalReceitaGerada.setText("R$ " + String.valueOf(df.format(cDados.getReceitaGerada())));
            totalReceitaEstimada.setText("R$ " + String.valueOf(df.format(cDados.getReceitaEstimada())));

        } catch(SQLException e)
        {
             JOptionPane.showMessageDialog(this, "Desculpe, ocorreu um erro: \n "  + e.getMessage(), "Locaki ~ A Sua Locadora!", 0);
        }
    }

    
    public void carregarTebelaEnderecos()
    {
        try
        {
            EnderecoCRUD eCrud = new EnderecoCRUD();

            List<Endereco> lista = (List<Endereco>) eCrud.listar(this.idCliente);

            DefaultTableModel modelo = (DefaultTableModel) tabelaEnderecos.getModel();
            modelo.setRowCount(0);

            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy - HH:mm");

            for (Endereco e : lista)
            {
                modelo.addRow(new Object[]{
                            e.getIdEndereco(),
                            e.getLagradouro(),
                            e.getCidade(),
                            e.getEstado(),
                            formatador.format(e.getDataCadastro())
                        });
            }
            
            if(tabelaEnderecos.getRowCount() > 0)
            {
                if(tabelaEnderecos.getRowCount() == 1)
                    eExcluir.setVisible(false);
                else
                    eExcluir.setVisible(true);

                this.preencherFormularioEndereco(Integer.parseInt(String.valueOf(tabelaEnderecos.getValueAt(0, 0))));
                eNovo.setEnabled(true);
            } else
            {
                eNovo.setEnabled(true);
                eExcluir.setVisible(false);
            }
        } catch(SQLException e)
        {
             JOptionPane.showMessageDialog(this, "Desculpe, ocorreu um erro: "  + e.getMessage(), "Locaki ~ A Sua Locadora!", 0);
        }
    }


    public void carregarTebelaTelefones()
    {
        try
        {
            TelefoneCRUD tCrud = new TelefoneCRUD();

            List<Telefone> lista = (List<Telefone>) tCrud.listar(this.idCliente);

            DefaultTableModel modelo = (DefaultTableModel) tabelaTelefones.getModel();
            modelo.setRowCount(0);

            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy - HH:mm");

            for (Telefone t : lista)
            {
                modelo.addRow(new Object[]{
                            t.getIdTelefone(),
                            t.getNumero(),
                            t.getTipoTelefone().replace("Re", "Residencial").replace("Co", "Comercial").replace("Ce", "Celular"),
                            formatador.format(t.getDataCadastro())
                        });
            }

            if(tabelaTelefones.getRowCount() > 0)
            {
                if(tabelaTelefones.getRowCount() == 1)
                    tExcluir.setVisible(false);
                else
                    tExcluir.setVisible(true);

                this.preencherFormularioTelefone(Integer.parseInt(String.valueOf(tabelaTelefones.getValueAt(0, 0))));
                tNovo.setEnabled(true);
            } else
            {
                tNovo.setEnabled(true);
                tExcluir.setVisible(false);
            }
        } catch(SQLException e)
        {
             JOptionPane.showMessageDialog(this, "Desculpe, ocorreu um erro: "  + e.getMessage(), "Locaki ~ A Sua Locadora!", 0);
        }
    }

    public void carregarTebelaLocacoes()
    {
        try
        {
            ClienteCRUD cCrud = new ClienteCRUD();

            List<ClienteLocacoes> lista = (List<ClienteLocacoes>) cCrud.getLocacoes(this.idCliente);

            DefaultTableModel modelo = (DefaultTableModel) tabelaLocacoes.getModel();
            modelo.setRowCount(0);

            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
            DecimalFormat df = new DecimalFormat("#,###0.00");

            for (ClienteLocacoes l : lista)
            {
                modelo.addRow(new Object[]{
                    l.getIdLocacao(),
                    l.getQntFilmes(),
                    l.getPromocao(),
                    formatador.format(l.getDataLocacao()),
                    "R$ " + df.format(l.getValorLocacao())
                });
            }

        } catch(SQLException e)
        {
             JOptionPane.showMessageDialog(this, "Desculpe, ocorreu um erro: "  + e.getMessage(), "Locaki ~ A Sua Locadora!", 0);
        }
    }


    public void preencherFormularioEndereco(int idEndereco)
    {
        try
        {
            EnderecoCRUD eCrud = new EnderecoCRUD();

            Endereco eDados = eCrud.getEndereco(idEndereco);

            codigoEndereco.setText(String.valueOf(eDados.getIdEndereco()));
            lagradouro.setText(eDados.getLagradouro());
            numero.setText(eDados.getNumero());
            bairro.setText(eDados.getBairro());
            cep.setText(eDados.getCep());
            cidade.setText(eDados.getCidade());
            estado.setSelectedItem(eDados.getEstado());
        } catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, "Desculpe, ocorreu um erro: "  + e.getMessage(), "Locaki ~ A Sua Locadora!", 0);
        }
    }


    public void preencherFormularioTelefone(int idTelefone)
    {
        try
        {
            TelefoneCRUD tCrud = new TelefoneCRUD();

            Telefone tDados = tCrud.getTelefone(idTelefone);

            codigoTelefone.setText(String.valueOf(tDados.getIdTelefone()));
            telefone.setText(tDados.getNumero());
            tipoTelefone.setSelectedItem(tDados.getTipoTelefone().replace("Re", "Residencial").replace("Ce", "Celular").replace("Co", "Comercial"));
            observacoes.setText(tDados.getObservacoes());
        } catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, "Desculpe, ocorreu um erro: "  + e.getMessage(), "Locaki ~ A Sua Locadora!", 0);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JTabbedPane jTabbedPane1 = new javax.swing.JTabbedPane();
        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        codigo = new javax.swing.JTextField();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        nome = new javax.swing.JTextField();
        dataCadastro = new javax.swing.JFormattedTextField();
        cpf = new javax.swing.JFormattedTextField();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        rg = new javax.swing.JTextField();
        javax.swing.JButton jButton3 = new javax.swing.JButton();
        javax.swing.JButton jButton4 = new javax.swing.JButton();
        javax.swing.JButton jButton5 = new javax.swing.JButton();
        javax.swing.JLabel jLabel22 = new javax.swing.JLabel();
        cObservacoes = new javax.swing.JTextField();
        javax.swing.JPanel jPanel11 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel15 = new javax.swing.JLabel();
        codigoTelefone = new javax.swing.JTextField();
        javax.swing.JLabel jLabel16 = new javax.swing.JLabel();
        telefone = new javax.swing.JFormattedTextField();
        javax.swing.JLabel jLabel17 = new javax.swing.JLabel();
        tipoTelefone = new javax.swing.JComboBox();
        javax.swing.JLabel jLabel18 = new javax.swing.JLabel();
        observacoes = new javax.swing.JTextField();
        javax.swing.JButton jButton2 = new javax.swing.JButton();
        tNovo = new javax.swing.JButton();
        tExcluir = new javax.swing.JButton();
        javax.swing.JPanel jPanel12 = new javax.swing.JPanel();
        javax.swing.JScrollPane jScrollPane3 = new javax.swing.JScrollPane();
        tabelaTelefones = new javax.swing.JTable();
        javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        codigoEndereco = new javax.swing.JTextField();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        lagradouro = new javax.swing.JTextField();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        numero = new javax.swing.JTextField();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        bairro = new javax.swing.JTextField();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        cep = new javax.swing.JFormattedTextField();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        cidade = new javax.swing.JTextField();
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        estado = new javax.swing.JComboBox();
        javax.swing.JButton jButton1 = new javax.swing.JButton();
        eNovo = new javax.swing.JButton();
        eExcluir = new javax.swing.JButton();
        javax.swing.JPanel jPanel5 = new javax.swing.JPanel();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        tabelaEnderecos = new javax.swing.JTable();
        javax.swing.JPanel jPanel3 = new javax.swing.JPanel();
        javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        tabelaLocacoes = new javax.swing.JTable();
        javax.swing.JPanel jPanel4 = new javax.swing.JPanel();
        javax.swing.JPanel jPanel6 = new javax.swing.JPanel();
        totalLocacoesPendentes = new javax.swing.JLabel();
        javax.swing.JPanel jPanel7 = new javax.swing.JPanel();
        totalLocacoesDevolvidasPrazo = new javax.swing.JLabel();
        javax.swing.JPanel jPanel8 = new javax.swing.JPanel();
        totalLocacoesAtrasadas = new javax.swing.JLabel();
        javax.swing.JPanel jPanel9 = new javax.swing.JPanel();
        totalReceitaGerada = new javax.swing.JLabel();
        javax.swing.JPanel jPanel10 = new javax.swing.JPanel();
        totalReceitaEstimada = new javax.swing.JLabel();
        javax.swing.JMenuBar jMenuBar1 = new javax.swing.JMenuBar();
        javax.swing.JMenu jMenu1 = new javax.swing.JMenu();
        javax.swing.JMenu jMenu10 = new javax.swing.JMenu();
        javax.swing.JMenuItem jMenuItem23 = new javax.swing.JMenuItem();
        javax.swing.JMenuItem jMenuItem21 = new javax.swing.JMenuItem();
        javax.swing.JMenuItem jMenuItem22 = new javax.swing.JMenuItem();
        javax.swing.JMenu jMenu11 = new javax.swing.JMenu();
        javax.swing.JMenuItem jMenuItem26 = new javax.swing.JMenuItem();
        javax.swing.JMenuItem jMenuItem24 = new javax.swing.JMenuItem();
        javax.swing.JMenuItem jMenuItem25 = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator2 = new javax.swing.JPopupMenu.Separator();
        javax.swing.JMenu jMenu12 = new javax.swing.JMenu();
        javax.swing.JMenuItem jMenuItem29 = new javax.swing.JMenuItem();
        javax.swing.JMenuItem jMenuItem27 = new javax.swing.JMenuItem();
        javax.swing.JMenuItem jMenuItem28 = new javax.swing.JMenuItem();
        javax.swing.JMenu jMenu13 = new javax.swing.JMenu();
        javax.swing.JMenuItem jMenuItem32 = new javax.swing.JMenuItem();
        javax.swing.JMenuItem jMenuItem30 = new javax.swing.JMenuItem();
        javax.swing.JMenuItem jMenuItem31 = new javax.swing.JMenuItem();
        javax.swing.JMenu jMenu14 = new javax.swing.JMenu();
        javax.swing.JMenuItem jMenuItem34 = new javax.swing.JMenuItem();
        javax.swing.JMenuItem jMenuItem33 = new javax.swing.JMenuItem();
        javax.swing.JMenuItem jMenuItem4 = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator3 = new javax.swing.JPopupMenu.Separator();
        javax.swing.JMenuItem jMenuItem5 = new javax.swing.JMenuItem();
        javax.swing.JMenuItem jMenuItem11 = new javax.swing.JMenuItem();
        javax.swing.JMenu jMenu3 = new javax.swing.JMenu();
        javax.swing.JMenu jMenu4 = new javax.swing.JMenu();
        javax.swing.JMenuItem jMenuItem12 = new javax.swing.JMenuItem();
        javax.swing.JMenuItem jMenuItem13 = new javax.swing.JMenuItem();
        javax.swing.JMenuItem jMenuItem8 = new javax.swing.JMenuItem();
        javax.swing.JMenu jMenu5 = new javax.swing.JMenu();
        javax.swing.JMenuItem jMenuItem6 = new javax.swing.JMenuItem();
        javax.swing.JMenuItem jMenuItem7 = new javax.swing.JMenuItem();
        javax.swing.JMenuItem jMenuItem15 = new javax.swing.JMenuItem();
        javax.swing.JMenu jMenu6 = new javax.swing.JMenu();
        javax.swing.JMenuItem jMenuItem16 = new javax.swing.JMenuItem();
        javax.swing.JMenuItem jMenuItem18 = new javax.swing.JMenuItem();
        javax.swing.JMenuItem jMenuItem17 = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator4 = new javax.swing.JPopupMenu.Separator();
        javax.swing.JMenu jMenu7 = new javax.swing.JMenu();
        javax.swing.JMenuItem jMenuItem14 = new javax.swing.JMenuItem();
        javax.swing.JMenuItem jMenuItem19 = new javax.swing.JMenuItem();
        javax.swing.JMenuItem jMenuItem20 = new javax.swing.JMenuItem();
        javax.swing.JMenu jMenu2 = new javax.swing.JMenu();
        javax.swing.JMenuItem jMenuItem10 = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator1 = new javax.swing.JPopupMenu.Separator();
        javax.swing.JMenuItem jMenuItem9 = new javax.swing.JMenuItem();

        setTitle("Locaki ~ A Sua Locadora! : Cadastro de Cliente");
        setAlwaysOnTop(true);
        setResizable(false);

        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setText("Código:");

        codigo.setEditable(false);

        jLabel2.setText("Data Cadastro:");

        jLabel4.setText("CPF:");

        jLabel5.setText("Nome:");

        dataCadastro.setEditable(false);
        dataCadastro.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy - HH:mm"))));

        try {
            cpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel6.setText("RG:");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/group_edit.png"))); // NOI18N
        jButton3.setText("Editar!");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/group_delete.png"))); // NOI18N
        jButton4.setText("Excluir!");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/cross.png"))); // NOI18N
        jButton5.setText("Cancelar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel22.setText("Observações:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cObservacoes, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cpf, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(codigo)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(dataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2))))
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(nome, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                            .addComponent(jLabel5)
                            .addComponent(rg, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)))
                    .addComponent(jLabel22)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cObservacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap(88, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Dados do Cliente", jPanel1);

        jLabel15.setText("Código:");

        codigoTelefone.setEditable(false);

        jLabel16.setText("Número:");

        try {
            telefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel17.setText("Tipo Telefone:");

        tipoTelefone.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione...", "Residencial", "Celular", "Comercial" }));

        jLabel18.setText("Observações:");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/script_save.png"))); // NOI18N
        jButton2.setText("Gravar!");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        tNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/new.png"))); // NOI18N
        tNovo.setText("Novo!");
        tNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tNovoActionPerformed(evt);
            }
        });

        tExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/delete.png"))); // NOI18N
        tExcluir.setText("Excluir!");
        tExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tExcluirActionPerformed(evt);
            }
        });

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Telefones Adicionados:"));

        tabelaTelefones.setAutoCreateRowSorter(true);
        tabelaTelefones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código:", "Telefone:", "Tipo Telefone:", "Data Cadastro:"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaTelefones.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabelaTelefones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selecionaTelefone(evt);
            }
        });
        jScrollPane3.setViewportView(tabelaTelefones);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(observacoes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(codigoTelefone)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(telefone, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(tipoTelefone, 0, 266, Short.MAX_VALUE)))
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(tExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codigoTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(telefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipoTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(observacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(tNovo)
                    .addComponent(tExcluir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Telefones", jPanel11);

        jLabel3.setText("Código:");

        codigoEndereco.setEditable(false);

        jLabel7.setText("Lagradouro:");

        jLabel8.setText("Número:");

        jLabel9.setText("Bairro:");

        jLabel10.setText("CEP:");

        try {
            cep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel11.setText("Cidade:");

        jLabel12.setText("Estado:");

        estado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione...", "AC", "AL", "AM", "AP", "BA  ", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO  ", "RR", "RS", "SC ", "SE", "SP", "TO" }));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/script_save.png"))); // NOI18N
        jButton1.setText("Gravar!");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        eNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/new.png"))); // NOI18N
        eNovo.setText("Novo!");
        eNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eNovoActionPerformed(evt);
            }
        });

        eExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/delete.png"))); // NOI18N
        eExcluir.setText("Excluir!");
        eExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eExcluirActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Endereços Adicionados:"));

        tabelaEnderecos.setAutoCreateRowSorter(true);
        tabelaEnderecos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código:", "Lagradouro:", "CEP:", "Cidade", "Data Cadastro:"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaEnderecos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabelaEnderecos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sselecionaEndereco(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaEnderecos);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(codigoEndereco)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lagradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(numero)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cep, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(cidade))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(bairro, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                            .addComponent(jLabel12)
                            .addComponent(estado, 0, 199, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(eExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codigoEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lagradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(eNovo)
                    .addComponent(eExcluir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Endereços", jPanel2);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Relação dos Títulos:"));

        tabelaLocacoes.setAutoCreateRowSorter(true);
        tabelaLocacoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código Locação:", "Qnt de Filmes:", "Promoção:", "Data Locação:", "Valor Locação:"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaLocacoes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tabelaLocacoes);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Locações", jPanel3);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Locações Pendentes:"));
        jPanel6.setPreferredSize(new java.awt.Dimension(170, 100));

        totalLocacoesPendentes.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        totalLocacoesPendentes.setForeground(new java.awt.Color(51, 153, 0));
        totalLocacoesPendentes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalLocacoesPendentes.setText("0");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(totalLocacoesPendentes, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(totalLocacoesPendentes, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Locações Devolvidas no Prazo:"));
        jPanel7.setPreferredSize(new java.awt.Dimension(170, 100));

        totalLocacoesDevolvidasPrazo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        totalLocacoesDevolvidasPrazo.setForeground(new java.awt.Color(0, 102, 255));
        totalLocacoesDevolvidasPrazo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalLocacoesDevolvidasPrazo.setText("0");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(totalLocacoesDevolvidasPrazo, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(totalLocacoesDevolvidasPrazo, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Locações Atrasadas:"));
        jPanel8.setPreferredSize(new java.awt.Dimension(170, 100));

        totalLocacoesAtrasadas.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        totalLocacoesAtrasadas.setForeground(new java.awt.Color(204, 0, 0));
        totalLocacoesAtrasadas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalLocacoesAtrasadas.setText("0");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(totalLocacoesAtrasadas, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(totalLocacoesAtrasadas, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Receita Gerada:"));
        jPanel9.setPreferredSize(new java.awt.Dimension(170, 100));

        totalReceitaGerada.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        totalReceitaGerada.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalReceitaGerada.setText("R$ 0,00");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(totalReceitaGerada, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(totalReceitaGerada)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Receita Estimada para o Próximo Mês:"));
        jPanel10.setPreferredSize(new java.awt.Dimension(346, 100));

        totalReceitaEstimada.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        totalReceitaEstimada.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalReceitaEstimada.setText("R$ 0,00");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(totalReceitaEstimada, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(totalReceitaEstimada)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Estatísticas", jPanel4);

        jMenu1.setText("Cadastros");

        jMenu10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/group.png"))); // NOI18N
        jMenu10.setText("Clientes");

        jMenuItem23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/group_go.png"))); // NOI18N
        jMenuItem23.setText("Relação de Clientes");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem23);

        jMenuItem21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/group_add.png"))); // NOI18N
        jMenuItem21.setText("Novo Cliente...");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem21);

        jMenuItem22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/group_edit.png"))); // NOI18N
        jMenuItem22.setText("Editar Cliente");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem22);

        jMenu1.add(jMenu10);

        jMenu11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/film.png"))); // NOI18N
        jMenu11.setText("Filmes");

        jMenuItem26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/film_go.png"))); // NOI18N
        jMenuItem26.setText("Relação de Filmes");
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem26);

        jMenuItem24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/film_add.png"))); // NOI18N
        jMenuItem24.setText("Novo Filme...");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem24);

        jMenuItem25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/film_edit.png"))); // NOI18N
        jMenuItem25.setText("Editar Filme");
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem25);

        jMenu1.add(jMenu11);
        jMenu1.add(jSeparator2);

        jMenu12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/comments.png"))); // NOI18N
        jMenu12.setText("Gêneros");

        jMenuItem29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/comments.png"))); // NOI18N
        jMenuItem29.setText("Relação de Gêneros");
        jMenuItem29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem29ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem29);

        jMenuItem27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/comments_add.png"))); // NOI18N
        jMenuItem27.setText("Novo Gênero...");
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem27ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem27);

        jMenuItem28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/comment_edit.png"))); // NOI18N
        jMenuItem28.setText("Editar Gênero");
        jMenuItem28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem28ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem28);

        jMenu1.add(jMenu12);

        jMenu13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/heart.png"))); // NOI18N
        jMenu13.setText("Promoções");

        jMenuItem32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/heart.png"))); // NOI18N
        jMenuItem32.setText("Relação de Promoções");
        jMenuItem32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem32ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem32);

        jMenuItem30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/heart_add.png"))); // NOI18N
        jMenuItem30.setText("Nova Promoção...");
        jMenuItem30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem30ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem30);

        jMenuItem31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/pencil.png"))); // NOI18N
        jMenuItem31.setText("Editar Promoção");
        jMenuItem31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem31ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem31);

        jMenu1.add(jMenu13);

        jMenu14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/report.png"))); // NOI18N
        jMenu14.setText("Locações");

        jMenuItem34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/report_go.png"))); // NOI18N
        jMenuItem34.setText("Relação de Locações");
        jMenuItem34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem34ActionPerformed(evt);
            }
        });
        jMenu14.add(jMenuItem34);

        jMenuItem33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/report_add.png"))); // NOI18N
        jMenuItem33.setText("Lançar Locação!");
        jMenuItem33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem33ActionPerformed(evt);
            }
        });
        jMenu14.add(jMenuItem33);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/report_delete.png"))); // NOI18N
        jMenuItem4.setText("Remover Locação");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu14.add(jMenuItem4);

        jMenu1.add(jMenu14);
        jMenu1.add(jSeparator3);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/arrow_rotate_clockwise.png"))); // NOI18N
        jMenuItem5.setText("Voltar Menu Principal");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/door_in.png"))); // NOI18N
        jMenuItem11.setText("Sair");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem11);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Relatórios");

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/group_go.png"))); // NOI18N
        jMenu4.setText("Cliente...");

        jMenuItem12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/house_go.png"))); // NOI18N
        jMenuItem12.setText("Clientes com Filmes");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem12);

        jMenuItem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/arrow_up.png"))); // NOI18N
        jMenuItem13.setText("Clientes Mais Ativos");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem13);

        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/arrow_down.png"))); // NOI18N
        jMenuItem8.setText("Clientes Menos Ativos");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem8);

        jMenu3.add(jMenu4);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/film_go.png"))); // NOI18N
        jMenu5.setText("Filmes...");

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/add.png"))); // NOI18N
        jMenuItem6.setText("Filmes Mais Procurados");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem6);

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/delete.png"))); // NOI18N
        jMenuItem7.setText("Filmes Menos Procurados");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem7);

        jMenuItem15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/star.png"))); // NOI18N
        jMenuItem15.setText("Top 10 do Mês");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem15);

        jMenu3.add(jMenu5);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/report_go.png"))); // NOI18N
        jMenu6.setText("Locações...");

        jMenuItem16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/exclamation.png"))); // NOI18N
        jMenuItem16.setText("Devoluções Atrasadas");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem16);

        jMenuItem18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/accept.png"))); // NOI18N
        jMenuItem18.setText("Filmes Devolvidos Hoje");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem18);

        jMenuItem17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/coins.png"))); // NOI18N
        jMenuItem17.setText("Total de Lacações no Mês");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem17);

        jMenu3.add(jMenu6);
        jMenu3.add(jSeparator4);

        jMenu7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/money_dollar.png"))); // NOI18N
        jMenu7.setText("Financeiro...");

        jMenuItem14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/date.png"))); // NOI18N
        jMenuItem14.setText("Receita por Período");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem14);

        jMenuItem19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/film.png"))); // NOI18N
        jMenuItem19.setText("Receita por Filme");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem19);

        jMenuItem20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/comments.png"))); // NOI18N
        jMenuItem20.setText("Receita por Gênero");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem20);

        jMenu3.add(jMenu7);

        jMenuBar1.add(jMenu3);

        jMenu2.setText("Ajuda");

        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/lightbulb.png"))); // NOI18N
        jMenuItem10.setText("Dicas...");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem10);
        jMenu2.add(jSeparator1);

        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/emotion_wink.png"))); // NOI18N
        jMenuItem9.setText("Sobre o Sistema");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                .addContainerGap())
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-577)/2, (screenSize.height-371)/2, 577, 371);
    }// </editor-fold>//GEN-END:initComponents

    private void sselecionaEndereco(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sselecionaEndereco

        if (evt.getClickCount() == 2) {
            try {

                if(tabelaEnderecos.getRowCount() == 1)
                    eExcluir.setVisible(false);
                else
                    eExcluir.setVisible(true);

                eNovo.setEnabled(true);

                EnderecoCRUD eCrud = new EnderecoCRUD();

                Endereco eDados = eCrud.getEndereco(Integer.parseInt(String.valueOf(tabelaEnderecos.getValueAt(tabelaEnderecos.getSelectedRow(), 0))));

                codigoEndereco.setText(String.valueOf(eDados.getIdEndereco()));
                lagradouro.setText(eDados.getLagradouro());
                numero.setText(eDados.getNumero());
                bairro.setText(eDados.getBairro());
                cep.setText(eDados.getCep());
                cidade.setText(eDados.getCidade());
                estado.setSelectedItem(eDados.getEstado());
            } catch(SQLException e) {
                JOptionPane.showMessageDialog(this, "Desculpe, ocorreu um erro: \n "  + e.getMessage(), "Locaki ~ A Sua Locadora!", 0);
            }
        }
}//GEN-LAST:event_sselecionaEndereco

    private void eExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eExcluirActionPerformed

        if(!codigoEndereco.getText().equals("")) {
            if(JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir este endereço?", "Locaki ~ A Sua Locadora!", 2, 2) == 0) {
                try {
                    EnderecoCRUD e = new EnderecoCRUD();
                    e.excluir(Integer.parseInt(codigoEndereco.getText()));

                    JOptionPane.showMessageDialog(this, "Endereço Excluído com Sucesso!", "Locaki ~ A Sua Locadora!", 1);

                    codigoEndereco.setText(null);
                    lagradouro.setText(null);
                    numero.setText(null);
                    bairro.setText(null);
                    cep.setText(null);
                    cidade.setText(null);
                    estado.setSelectedIndex(0);

                    lagradouro.requestFocus();

                    this.carregarTebelaEnderecos();

                } catch(SQLException e) {
                    JOptionPane.showMessageDialog(this, "Desculpe, ocorreu um erro: \n "  + e.getMessage(), "Locaki ~ A Sua Locadora!", 0);
                }
            }
        }
}//GEN-LAST:event_eExcluirActionPerformed

    private void eNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eNovoActionPerformed

        eExcluir.setVisible(false);
        eNovo.setEnabled(false);

        codigoEndereco.setText(null);
        lagradouro.setText(null);
        numero.setText(null);
        bairro.setText(null);
        cep.setText(null);
        cidade.setText(null);
        estado.setSelectedIndex(0);

        lagradouro.requestFocus();
}//GEN-LAST:event_eNovoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            EnderecoCRUD eCrud = new EnderecoCRUD();

            Endereco e = new Endereco();

            if(codigoEndereco.getText().equals("")) {
                try {
                    e.setIdCliente(Integer.parseInt(codigo.getText()));
                    e.setLagradouro(lagradouro.getText());
                    e.setNumero(numero.getText());
                    e.setBairro(bairro.getText());
                    e.setCep(cep.getText().replaceAll("[/./[-]/]", ""));
                    e.setCidade(cidade.getText());
                    e.setEstado(estado.getSelectedItem().toString());


                    eCrud.cadastrar(e);

                    JOptionPane.showMessageDialog(this, "Endereço Cadastrado com Sucesso!", "Locaki ~ A Sua Locadora!", 1);
                    this.carregarTebelaEnderecos();

                } catch(SQLException exc) {
                    JOptionPane.showMessageDialog(this, "Desculpe, ocorreu um erro: \n "  + exc.getMessage(), "Locaki ~ A Sua Locadora!", 0);
                }
            } else {
                try {
                    e.setIdEndereco(Integer.parseInt(codigoEndereco.getText()));
                    e.setIdCliente(Integer.parseInt(codigo.getText()));
                    e.setLagradouro(lagradouro.getText());
                    e.setNumero(numero.getText());
                    e.setBairro(bairro.getText());
                    e.setCep(cep.getText());
                    e.setCidade(cidade.getText());
                    e.setEstado(estado.getSelectedItem().toString());

                    eCrud.editar(e);

                    JOptionPane.showMessageDialog(this, "Endereço Editado com Sucesso!", "Locaki ~ A Sua Locadora!", 1);
                    this.carregarTebelaEnderecos();

                } catch(SQLException exc) {
                    JOptionPane.showMessageDialog(this, "Desculpe, ocorreu um erro: \n "  + exc.getMessage(), "Locaki ~ A Sua Locadora!", 0);
                }
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Desculpe, ocorreu um erro: \n "  + e.getMessage(), "Locaki ~ A Sua Locadora!", 0);
        }
}//GEN-LAST:event_jButton1ActionPerformed

    private void selecionaTelefone(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selecionaTelefone

        if (evt.getClickCount() == 2) {
            try {

                if(tabelaTelefones.getRowCount() == 1)
                    tExcluir.setVisible(false);
                else
                    tExcluir.setVisible(true);

                tNovo.setEnabled(true);

                TelefoneCRUD tCrud = new TelefoneCRUD();

                Telefone tDados = tCrud.getTelefone(Integer.parseInt(String.valueOf(tabelaTelefones.getValueAt(tabelaTelefones.getSelectedRow(), 0))));

                codigoTelefone.setText(String.valueOf(tDados.getIdTelefone()));
                telefone.setText(tDados.getNumero());
                tipoTelefone.setSelectedItem(tDados.getTipoTelefone().replace("Re", "Residencial").replace("Ce", "Celular").replace("Co", "Comercial"));
                observacoes.setText(tDados.getObservacoes());
            } catch(SQLException e) {
                JOptionPane.showMessageDialog(this, "Desculpe, ocorreu um erro: \n "  + e.getMessage(), "Locaki ~ A Sua Locadora!", 0);
            }
        }
}//GEN-LAST:event_selecionaTelefone

    private void tExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tExcluirActionPerformed

        if(!codigoTelefone.getText().equals("")) {
            if(JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir este telefone?", "Locaki ~ A Sua Locadora!", 2, 2) == 0) {
                try {
                    TelefoneCRUD t = new TelefoneCRUD();
                    t.excluir(Integer.parseInt(codigoTelefone.getText()));

                    JOptionPane.showMessageDialog(this, "Telefone Excluído com Sucesso!", "Locaki ~ A Sua Locadora!", 1);

                    codigoTelefone.setText(null);
                    telefone.setText(null);
                    tipoTelefone.setSelectedItem(0);
                    observacoes.setText(null);

                    telefone.requestFocus();

                    this.carregarTebelaTelefones();

                } catch(SQLException e) {
                    JOptionPane.showMessageDialog(this, "Desculpe, ocorreu um erro: \n "  + e.getMessage(), "Locaki ~ A Sua Locadora!", 0);
                }
            }
        }
}//GEN-LAST:event_tExcluirActionPerformed

    private void tNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tNovoActionPerformed

        codigoTelefone.setText(null);
        telefone.setText(null);
        tipoTelefone.setSelectedItem(0);
        observacoes.setText(null);

        telefone.requestFocus();

        tExcluir.setVisible(false);
        tNovo.setEnabled(false);
}//GEN-LAST:event_tNovoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        try {
            TelefoneCRUD tCrud = new TelefoneCRUD();

            Telefone t = new Telefone();

            if(codigoTelefone.getText().equals("")) {
                try {
                    t.setIdCliente(Integer.parseInt(codigo.getText()));
                    t.setNumero(telefone.getText().replaceAll("[/(/)/ /[-]/]", ""));
                    t.setTipoTelefone(tipoTelefone.getSelectedItem().toString().replace("Residencial", "Re").replace("Celular", "Ce").replace("Comercial", "Co"));
                    t.setObservacoes(observacoes.getText());

                    tCrud.cadastrar(t);

                    JOptionPane.showMessageDialog(this, "Telefone Cadastrado com Sucesso!", "Locaki ~ A Sua Locadora!", 1);
                    this.carregarTebelaTelefones();

                } catch(SQLException exc) {
                    JOptionPane.showMessageDialog(this, "Desculpe, ocorreu um erro: \n "  + exc.getMessage(), "Locaki ~ A Sua Locadora!", 0);
                }
            } else {
                try {
                    t.setIdTelefone(Integer.parseInt(codigoTelefone.getText()));
                    t.setIdCliente(Integer.parseInt(codigo.getText()));
                    t.setNumero(telefone.getText().replaceAll("[/(/)/ /[-]/]", ""));
                    t.setTipoTelefone(tipoTelefone.getSelectedItem().toString().replace("Residencial", "Re").replace("Celular", "Ce").replace("Comercial", "Co"));
                    t.setObservacoes(observacoes.getText());

                    tCrud.editar(t);

                    JOptionPane.showMessageDialog(this, "Telefone Editado com Sucesso!", "Locaki ~ A Sua Locadora!", 1);
                    this.carregarTebelaTelefones();
                } catch(SQLException exc) {
                    JOptionPane.showMessageDialog(this, "Desculpe, ocorreu um erro: \n "  + exc.getMessage(), "Locaki ~ A Sua Locadora!", 0);
                }
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Desculpe, ocorreu um erro: \n "  + e.getMessage(), "Locaki ~ A Sua Locadora!", 0);
        }
}//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:

        this.setVisible(false);
}//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:

        if(JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir este cliente?", "Locaki ~ A Sua Locadora!", 2, 2) == 0)
        {
            try {
                ClienteCRUD cCrud = new ClienteCRUD();

                cCrud.excluir(Integer.parseInt(codigo.getText()));

                JOptionPane.showMessageDialog(this, "Cliente Excluído com Sucesso!", "Locaki ~ A Sua Locadora!", 1);

                this.setVisible(false);

            } catch(Exception e) {
                JOptionPane.showMessageDialog(this, "Desculpe, ocorreu um erro: \n"  + e.getMessage(), "Locaki ~ A Sua Locadora!", 0);
            }
        }
}//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        try {
            ClienteCRUD cCrud = new ClienteCRUD();

            Cliente c = new Cliente();

            c.setIdCliente(Integer.parseInt(codigo.getText()));
            c.setNome(nome.getText().toUpperCase());
            c.setCpf(cpf.getText().replaceAll("[/./[-]/]", ""));
            c.setRg(rg.getText().toUpperCase());
            c.setObservacoes(cObservacoes.getText().toUpperCase());

            cCrud.editar(c);

            JOptionPane.showMessageDialog(this, "Cliente Editado com Sucesso!", "Locaki ~ A Sua Locadora!", 1);

            this.setVisible(false);

        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Desculpe, ocorreu um erro: \n"  + e.getMessage(), "Locaki ~ A Sua Locadora!", 0);
        }
}//GEN-LAST:event_jButton3ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        // TODO add your handling code here:

        RelCliente rc = new RelCliente();
        rc.setVisible(true);
}//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        // TODO add your handling code here:
        if(cc == null)
            cc = new CadCliente();

        cc.setVisible(true);
}//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        // TODO add your handling code here:
        RelCliente ec = new RelCliente();
        ec.setVisible(true);
}//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
        // TODO add your handling code here:

        RelFilme rf = new RelFilme();
        rf.setVisible(true);
}//GEN-LAST:event_jMenuItem26ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        // TODO add your handling code here:
        if(cf == null)
            cf = new CadFilme();

        cf.setVisible(true);
}//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
        // TODO add your handling code here:

        RelFilme rf = new RelFilme();
        rf.setVisible(true);
}//GEN-LAST:event_jMenuItem25ActionPerformed

    private void jMenuItem29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem29ActionPerformed
        // TODO add your handling code here:

        RelGenero rg = new RelGenero();
        rg.setVisible(true);
}//GEN-LAST:event_jMenuItem29ActionPerformed

    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed
        // TODO add your handling code here:
        if(cg == null)
            cg = new CadGenero();

        cg.setVisible(true);
}//GEN-LAST:event_jMenuItem27ActionPerformed

    private void jMenuItem28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem28ActionPerformed
        // TODO add your handling code here:
        RelGenero rg = new RelGenero();
        rg.setVisible(true);
}//GEN-LAST:event_jMenuItem28ActionPerformed

    private void jMenuItem32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem32ActionPerformed
        // TODO add your handling code here:
        RelPromocao rp = new RelPromocao();
        rp.setVisible(true);
}//GEN-LAST:event_jMenuItem32ActionPerformed

    private void jMenuItem30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem30ActionPerformed
        // TODO add your handling code here:
        if(cp == null)
            cp = new CadPromocao();

        cp.setVisible(true);
}//GEN-LAST:event_jMenuItem30ActionPerformed

    private void jMenuItem31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem31ActionPerformed
        // TODO add your handling code here:
        RelPromocao rp = new RelPromocao();
        rp.setVisible(true);
}//GEN-LAST:event_jMenuItem31ActionPerformed

    private void jMenuItem34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem34ActionPerformed
        // TODO add your handling code here:

        RelLocacao rl = new RelLocacao();
        rl.setVisible(true);
}//GEN-LAST:event_jMenuItem34ActionPerformed

    private void jMenuItem33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem33ActionPerformed
        // TODO add your handling code here:
        if(cl == null)
            cl = new CadLocacao();

        cl.setVisible(true);
}//GEN-LAST:event_jMenuItem33ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        RelLocacao rl = new RelLocacao();
        rl.setVisible(true);
}//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:

        MenuPrincipal.mp.setVisible(true);
        this.setVisible(false);
}//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:

        if(JOptionPane.showConfirmDialog(this, "Tem certeza que deseja sair do sistema?", "Locaki ~ A Sua Locadora!", 2, 2) == 0)
            System.exit(0);
}//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // TODO add your handling code here:

        ClientesComFilmes ccf = new ClientesComFilmes();
        ccf.setVisible(true);
}//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        // TODO add your handling code here:

        ClientesMaisAtivos cma = new ClientesMaisAtivos();
        cma.setVisible(true);
}//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        ClientesMenosAtivos cma = new ClientesMenosAtivos();
        cma.setVisible(true);
}//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:

        FilmesMaisProcurados fmp = new FilmesMaisProcurados();
        fmp.setVisible(true);
}//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:

        FilmesMenosProcurados fmp = new FilmesMenosProcurados();
        fmp.setVisible(true);
}//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        // TODO add your handling code here:

        FilmesTopDez ftd = new FilmesTopDez();
        ftd.setVisible(true);
}//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        // TODO add your handling code here:

        LocacoesDevolucoesAtrasadas lda = new LocacoesDevolucoesAtrasadas();
        lda.setVisible(true);
}//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        // TODO add your handling code here:

        LocacoesFilmesDevolvidosHoje lfdh = new LocacoesFilmesDevolvidosHoje();
        lfdh.setVisible(true);
}//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        // TODO add your handling code here:

        LocacoesTotalLocacoesMes ltlm = new LocacoesTotalLocacoesMes();
        ltlm.setVisible(true);
}//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        // TODO add your handling code here:

        FinanceiroReceitaPorPeriodo frpp = new FinanceiroReceitaPorPeriodo();
        frpp.setVisible(true);
}//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        // TODO add your handling code here:

        FinanceiroReceitaPorFilme frpf = new FinanceiroReceitaPorFilme();
        frpf.setVisible(true);
}//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        // TODO add your handling code here:

        FinanceiroReceitaPorGenero frpg = new FinanceiroReceitaPorGenero();
        frpg.setVisible(true);
}//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:

        Dicas d = new Dicas();
        d.setVisible(true);
}//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:

        Sobre s = new Sobre();
        s.setVisible(true);
}//GEN-LAST:event_jMenuItem9ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EdiCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JTextField bairro;
    javax.swing.JTextField cObservacoes;
    javax.swing.JFormattedTextField cep;
    javax.swing.JTextField cidade;
    javax.swing.JTextField codigo;
    javax.swing.JTextField codigoEndereco;
    javax.swing.JTextField codigoTelefone;
    javax.swing.JFormattedTextField cpf;
    javax.swing.JFormattedTextField dataCadastro;
    javax.swing.JButton eExcluir;
    javax.swing.JButton eNovo;
    javax.swing.JComboBox estado;
    javax.swing.JTextField lagradouro;
    javax.swing.JTextField nome;
    javax.swing.JTextField numero;
    javax.swing.JTextField observacoes;
    javax.swing.JTextField rg;
    javax.swing.JButton tExcluir;
    javax.swing.JButton tNovo;
    javax.swing.JTable tabelaEnderecos;
    javax.swing.JTable tabelaLocacoes;
    javax.swing.JTable tabelaTelefones;
    javax.swing.JFormattedTextField telefone;
    javax.swing.JComboBox tipoTelefone;
    javax.swing.JLabel totalLocacoesAtrasadas;
    javax.swing.JLabel totalLocacoesDevolvidasPrazo;
    javax.swing.JLabel totalLocacoesPendentes;
    javax.swing.JLabel totalReceitaEstimada;
    javax.swing.JLabel totalReceitaGerada;
    // End of variables declaration//GEN-END:variables

}
