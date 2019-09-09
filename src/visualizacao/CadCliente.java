/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Cliente.java
 *
 * Created on 21/04/2010, 18:17:34
 */

package visualizacao;

import controle.ClienteCRUD;
import controle.arrays.EnderecoArrayCrud;
import controle.arrays.TelefoneArrayCrud;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;
import modelo.Endereco;
import modelo.Telefone;
import visualizacao.Relatorios.*;

/**
 *
 * @author Tiago
 */
public class CadCliente extends javax.swing.JFrame {

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
    public CadCliente() {
        initComponents();
        this.setBounds(420, 250, 577, 371);

        tRemover.setVisible(false);
        eRemover.setVisible(false);

        tNovo.setEnabled(false);
        eNovo.setEnabled(false);
    }

   public void carregarTebelaTelefones()
    {
        try
        {
            TelefoneArrayCrud tCrud = new TelefoneArrayCrud();

            List<Telefone> lista = (List<Telefone>) tCrud.listar();

            DefaultTableModel modelo = (DefaultTableModel) tabelaTelefone.getModel();
            modelo.setRowCount(0);

            for (Telefone t : lista)
            {
                modelo.addRow(new Object[]{
                            t.getNumero(),
                            t.getTipoTelefone().replace("Re", "Residencial").replace("Co", "Comercial").replace("Ce", "Celular"),
                            t.getObservacoes()
                });
            }

            if(tabelaTelefone.getRowCount() > 0)
            {
                this.preencherFormularioTelefone(String.valueOf(tabelaTelefone.getValueAt(0, 0)));
                tNovo.setEnabled(true);
                tRemover.setVisible(true);
            } else if(tabelaTelefone.getRowCount() == 0)
            {
                tNovo.setEnabled(false);
                tRemover.setVisible(false);
            }

        } catch(Exception e)
        {
             JOptionPane.showMessageDialog(this, "Desculpe, ocorreu um erro: "  + e.getMessage(), "Locaki ~ A Sua Locadora!", 0);
        }
    }

   public void carregarTebelaEnderecos()
    {
        try
        {
            EnderecoArrayCrud eCrud = new EnderecoArrayCrud();

            List<Endereco> lista = (List<Endereco>) eCrud.listar();

            DefaultTableModel modelo = (DefaultTableModel) tabelaEnderecos.getModel();
            modelo.setRowCount(0);

            for (Endereco e : lista)
            {
                modelo.addRow(new Object[]{
                    e.getLagradouro().concat(", ").concat(e.getNumero()),
                    e.getCep(),
                    e.getCidade(),
                    e.getEstado()
                });
            }

            if(tabelaEnderecos.getRowCount() > 0)
            {
                this.preencherFormularioEndereco(String.valueOf(tabelaEnderecos.getValueAt(0, 0)));
                eNovo.setEnabled(true);
                eRemover.setVisible(true);
            } else if(tabelaEnderecos.getRowCount() == 0)
            {
                eNovo.setEnabled(false);
                eRemover.setVisible(false);
            }

        } catch(Exception e)
        {
             JOptionPane.showMessageDialog(this, "Desculpe, ocorreu um erro: "  + e.getMessage(), "Locaki ~ A Sua Locadora!", 0);
        }
    }

    public void preencherFormularioTelefone(String tel)
    {
        try
        {
            TelefoneArrayCrud tCrud = new TelefoneArrayCrud();

            Telefone tDados = tCrud.getTelefone(tel);

            telefone.setText(tDados.getNumero());
            tipoTelefone.setSelectedItem(tDados.getTipoTelefone().replace("Re", "Residencial").replace("Ce", "Celular").replace("Co", "Comercial"));
            observacoes.setText(tDados.getObservacoes());
        } catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "Desculpe, ocorreu um erro: "  + e.getMessage(), "Locaki ~ A Sua Locadora!", 0);
        }
    }

    public void preencherFormularioEndereco(String end)
    {
        try
        {
            EnderecoArrayCrud eCrud = new EnderecoArrayCrud();

            Endereco eDados = eCrud.getEndereco(end);

            lagradouro.setText(eDados.getLagradouro());
            numero.setText(eDados.getNumero());
            bairro.setText(eDados.getBairro());
            cep.setText(eDados.getCep());
            cidade.setText(eDados.getCidade());
            estado.setSelectedItem(eDados.getEstado());
        } catch(Exception e)
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
        javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        nome = new javax.swing.JTextField();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        cpf = new javax.swing.JFormattedTextField();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        rg = new javax.swing.JTextField();
        javax.swing.JLabel jLabel13 = new javax.swing.JLabel();
        cObservacoes = new javax.swing.JTextField();
        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        telefone = new javax.swing.JFormattedTextField();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        tipoTelefone = new javax.swing.JComboBox();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        observacoes = new javax.swing.JTextField();
        javax.swing.JButton jButton2 = new javax.swing.JButton();
        tNovo = new javax.swing.JButton();
        tRemover = new javax.swing.JButton();
        javax.swing.JPanel jPanel4 = new javax.swing.JPanel();
        javax.swing.JScrollPane jScrollPane3 = new javax.swing.JScrollPane();
        tabelaTelefone = new javax.swing.JTable();
        javax.swing.JPanel jPanel3 = new javax.swing.JPanel();
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
        javax.swing.JButton jButton6 = new javax.swing.JButton();
        eNovo = new javax.swing.JButton();
        eRemover = new javax.swing.JButton();
        javax.swing.JPanel jPanel5 = new javax.swing.JPanel();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        tabelaEnderecos = new javax.swing.JTable();
        javax.swing.JButton jButton1 = new javax.swing.JButton();
        javax.swing.JButton jButton3 = new javax.swing.JButton();
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
        setBounds(new java.awt.Rectangle(100, 100, 100, 100));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jLabel1.setText("Nome:");

        jLabel2.setText("CPF:");

        try {
            cpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel3.setText("RG:");

        jLabel13.setText("Observações:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cObservacoes, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
                    .addComponent(nome, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cpf, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(rg, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)))
                    .addComponent(jLabel13))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cObservacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(88, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Dados do Cliente", jPanel2);

        jLabel4.setText("Número:");

        try {
            telefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel5.setText("Tipo Telefone:");

        tipoTelefone.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione...", "Residencial", "Celular", "Comercial" }));

        jLabel6.setText("Observações:");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/add.png"))); // NOI18N
        jButton2.setText("Adicionar!");
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

        tRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/delete.png"))); // NOI18N
        tRemover.setText("Remover!");
        tRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tRemoverActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Telefones Adicionados:"));

        tabelaTelefone.setAutoCreateRowSorter(true);
        tabelaTelefone.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Telefone:", "Tipo Telefone:", "Observações:"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaTelefone.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabelaTelefone.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selecionaTelefone(evt);
            }
        });
        jScrollPane3.setViewportView(tabelaTelefone);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(observacoes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(telefone, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(tipoTelefone, 0, 239, Short.MAX_VALUE)))
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tRemover)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(telefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipoTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(observacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(tNovo)
                    .addComponent(tRemover))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Telefones", jPanel1);

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

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/add.png"))); // NOI18N
        jButton6.setText("Adicionar!");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        eNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/new.png"))); // NOI18N
        eNovo.setText("Novo!");
        eNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eNovoActionPerformed(evt);
            }
        });

        eRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/delete.png"))); // NOI18N
        eRemover.setText("Remover!");
        eRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eRemoverActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Endereços Adicionados:"));

        tabelaEnderecos.setAutoCreateRowSorter(true);
        tabelaEnderecos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Lagradouro:", "CEP:", "Cidade", "Estado:"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        tabelaEnderecos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabelaEnderecos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selecionaEndereco(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaEnderecos);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(cep, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cidade))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lagradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(106, 106, 106)
                                .addComponent(jLabel11)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addContainerGap(117, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(122, 122, 122))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(estado, javax.swing.GroupLayout.Alignment.LEADING, 0, 144, Short.MAX_VALUE)
                                    .addComponent(bairro, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                                .addContainerGap())))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(eRemover)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lagradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(eNovo)
                    .addComponent(eRemover))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Endereços", jPanel3);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/group_add.png"))); // NOI18N
        jButton1.setText("Gravar!");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visualizacao/Icones/cross.png"))); // NOI18N
        jButton3.setText("Cancelar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-577)/2, (screenSize.height-371)/2, 577, 371);
    }// </editor-fold>//GEN-END:initComponents

    private void tRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tRemoverActionPerformed
        // TODO add your handling code here:

        if(JOptionPane.showConfirmDialog(this, "Tem certeza que deseja remover este telefone?", "Locaki ~ A Sua Locadora!", 2, 2) == 0) {
            try {

                TelefoneArrayCrud tCrud = new TelefoneArrayCrud();

                tCrud.remover(telefone.getText().replaceAll("[/(/)/ /[-]/]", ""));

                JOptionPane.showMessageDialog(this, "Telefone Excluído com Sucesso!", "Locaki ~ A Sua Locadora!", 1);

                telefone.setText(null);
                tipoTelefone.setSelectedIndex(0);
                observacoes.setText(null);

                telefone.requestFocus();

                this.carregarTebelaTelefones();

            } catch(Exception e) {
                JOptionPane.showMessageDialog(this, "Desculpe, ocorreu um erro: \n "  + e.getMessage(), "Locaki ~ A Sua Locadora!", 0);
            }
        }
    }//GEN-LAST:event_tRemoverActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        try
        {
            ClienteCRUD cCrud = new ClienteCRUD();

            EnderecoArrayCrud eCrud = new EnderecoArrayCrud();
            TelefoneArrayCrud tCrud = new TelefoneArrayCrud();


            Cliente  c = new Cliente();

            c.setNome(nome.getText().toUpperCase());
            c.setCpf(cpf.getText().replaceAll("[/./[-]/ /]", ""));
            c.setRg(rg.getText().toUpperCase());
            c.setObservacoes(cObservacoes.getText().toUpperCase());

            c.setTelefones(tCrud.listar());
            c.setEnderecos(eCrud.listar());

            cCrud.cadastrar(c);

            JOptionPane.showMessageDialog(this, "Cliente Cadastrado com Sucesso!", "Locaki ~ A Sua Locadora!", 1);

            this.setVisible(false);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Desculpe, ocorreu um erro: \n"  + e.getMessage(), "Locaki ~ A Sua Locadora!", 0);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        try
        {
            EnderecoArrayCrud eCrud = new EnderecoArrayCrud();
            Endereco e = new Endereco();

            e.setLagradouro(lagradouro.getText().toUpperCase());
            e.setNumero(numero.getText().toUpperCase());
            e.setBairro(bairro.getText().toUpperCase());
            e.setCep(cep.getText().replaceAll("[/./[-]/ /]", ""));
            e.setCidade(cidade.getText().toUpperCase());
            e.setEstado(estado.getSelectedItem().toString().replace("Selecione...", ""));

            eCrud.add(e);

            this.carregarTebelaEnderecos();

            lagradouro.setText(null);
            numero.setText(null);
            bairro.setText(null);
            cep.setText(null);
            cidade.setText(null);
            estado.setSelectedIndex(0);

            lagradouro.requestFocus();

        } catch (Exception e) {
             JOptionPane.showMessageDialog(this, "Desculpe, ocorreu um erro: \n"  + e.getMessage(), "Locaki ~ A Sua Locadora!", 0);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try
        {
            TelefoneArrayCrud tCrud = new TelefoneArrayCrud();

            Telefone t = new Telefone();

            t.setNumero(telefone.getText().replaceAll("[/(/)/ /[-]/]", ""));
            t.setTipoTelefone(tipoTelefone.getSelectedItem().toString().replace("Residencial", "Re").replace("Celular", "Ce").replace("Comercial", "Co").replace("Selecione...", ""));
            t.setObservacoes(observacoes.getText().toUpperCase());

            tCrud.add(t);

            this.carregarTebelaTelefones();

            telefone.setText(null);
            tipoTelefone.setSelectedIndex(0);
            observacoes.setText(null);

            telefone.requestFocus();

            tNovo.setEnabled(true);
            
        } catch (Exception e) {
             JOptionPane.showMessageDialog(this, "Desculpe, ocorreu um erro: \n"  + e.getMessage(), "Locaki ~ A Sua Locadora!", 0);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void selecionaTelefone(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selecionaTelefone

        if (evt.getClickCount() == 2) 
            this.preencherFormularioTelefone(String.valueOf(tabelaTelefone.getValueAt(tabelaTelefone.getSelectedRow(), 0)));
}//GEN-LAST:event_selecionaTelefone

    private void selecionaEndereco(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selecionaEndereco

        if (evt.getClickCount() == 2)
            this.preencherFormularioEndereco(String.valueOf(tabelaEnderecos.getValueAt(tabelaEnderecos.getSelectedRow(), 0)));
}//GEN-LAST:event_selecionaEndereco

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        this.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void eNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eNovoActionPerformed
        // TODO add your handling code here:

        eRemover.setVisible(false);
        eNovo.setEnabled(false);

        lagradouro.setText(null);
        numero.setText(null);
        bairro.setText(null);
        cep.setText(null);
        cidade.setText(null);
        estado.setSelectedIndex(0);

        lagradouro.requestFocus();
    }//GEN-LAST:event_eNovoActionPerformed

    private void tNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tNovoActionPerformed
        // TODO add your handling code here:

        telefone.setText(null);
        tipoTelefone.setSelectedIndex(0);
        observacoes.setText(null);

        telefone.requestFocus();

        tRemover.setVisible(false);
        tNovo.setEnabled(false);
    }//GEN-LAST:event_tNovoActionPerformed

    private void eRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eRemoverActionPerformed

        if(JOptionPane.showConfirmDialog(this, "Tem certeza que deseja remover este endereço?", "Locaki ~ A Sua Locadora!", 2, 2) == 0) {
                try {

                    EnderecoArrayCrud eCrud = new EnderecoArrayCrud();

                    eCrud.remover(lagradouro.getText() + ", " + numero.getText());

                    this.carregarTebelaEnderecos();

                    JOptionPane.showMessageDialog(this, "Endereço Removido com Sucesso!", "Locaki ~ A Sua Locadora!", 1);

                  
                    lagradouro.setText(null);
                    numero.setText(null);
                    bairro.setText(null);
                    cep.setText(null);
                    cidade.setText(null);
                    estado.setSelectedIndex(0);

                    lagradouro.requestFocus();

                } catch(Exception e) {
                    JOptionPane.showMessageDialog(this, "Desculpe, ocorreu um erro: \n "  + e.getMessage(), "Locaki ~ A Sua Locadora!", 0);
                }
            }
    }//GEN-LAST:event_eRemoverActionPerformed

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
                new  CadCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JTextField bairro;
    javax.swing.JTextField cObservacoes;
    javax.swing.JFormattedTextField cep;
    javax.swing.JTextField cidade;
    javax.swing.JFormattedTextField cpf;
    javax.swing.JButton eNovo;
    javax.swing.JButton eRemover;
    javax.swing.JComboBox estado;
    javax.swing.JTextField lagradouro;
    javax.swing.JTextField nome;
    javax.swing.JTextField numero;
    javax.swing.JTextField observacoes;
    javax.swing.JTextField rg;
    javax.swing.JButton tNovo;
    javax.swing.JButton tRemover;
    javax.swing.JTable tabelaEnderecos;
    javax.swing.JTable tabelaTelefone;
    javax.swing.JFormattedTextField telefone;
    javax.swing.JComboBox tipoTelefone;
    // End of variables declaration//GEN-END:variables

}
