/*
    Projeto CoffeeDaily - Desenvolvido por Michael Gabriel Ferreira Chioda durante a disciplina de Laboratório de Programação Orientada a Objetos
*/
package view;

// ---------- Importando as packages do projeto ----------
// Importando a package que contém a classe DAO para as operações SQL
import controller.CoffeeDailyDAO;
import java.awt.Color;
// Importando as classes que representam as tabelas do Banco de Dados DB_CoffeeDaily
import model.Usuario;
import model.Produto;
import model.Venda;
import model.Pagamento;
import model.ItemVenda;
// Importando algumas packages necessárias para que o sistema possa conversar com o Sistema Operacional
import java.awt.Desktop;
import java.net.URI;
import java.text.SimpleDateFormat;
// Importando algumas packages auxiliares no desenvolvimento
import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;
// -----------------------------------------------------------------------------

public class CoffeeDailyViewForm extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CoffeeDailyViewForm.class.getName());
    
    // Para utilizar as operações SQL implementadas em CoffeeDailyDAO
    private CoffeeDailyDAO cfdao = new CoffeeDailyDAO();
    
    // Para utilizar as operações com tabelas implementadas em CoffeeDailyTableModelUsuario3
    private CoffeeDailyTableModelUsuario3 cdtmu3 = new CoffeeDailyTableModelUsuario3();
    
    // Para utilizar as operações com tabelas implementadas em CoffeeDailyTableModelProduto5
    private CoffeeDailyTableModelProduto5 cdtmp5 = new CoffeeDailyTableModelProduto5();
    
    // Para utilizar as operações com tabelas implementadas em CoffeeDailyTableModelProduto6
    private CoffeeDailyTableModelProduto6 cdtmp6 = new CoffeeDailyTableModelProduto6();

    // Para utilizar as operações com tabelas implementadas em CoffeeDailyTableModelCarrinho6
    private CoffeeDailyTableModelCarrinho6 cdtmc6 = new CoffeeDailyTableModelCarrinho6();

    // Para utilizar as operações com tabelas implementadas em CoffeeDailyTableModelCarrinho7
    private CoffeeDailyTableModelCarrinho7 cdtmc7 = new CoffeeDailyTableModelCarrinho7();

    // Para utilizar as operações com tabelas implementadas em CoffeeDailyTableModelVenda8
    private CoffeeDailyTableModelVenda8 cdtmv8 = new CoffeeDailyTableModelVenda8();

    public CoffeeDailyViewForm() {
        initComponents();
        
        // ----- Definindo algumas configurações básicas -----
        
        // Título à janela
        setTitle("CoffeeDaily System");
        // Impedir o redimencionamento da tela
        setResizable(false);
        // Centralizar a janela
        setLocationRelativeTo(null);
        // Definir cor de fundo da janela
        getContentPane().setBackground(Color.decode("#2A1A1F"));
        
        // Configurando os ComboBox e RadioButtons da Interface 7: Vender
        // Agrupar os RadioButtons para que apenas um fique selecionado por vez
        buttonGroup1.add(rdbSim7);
        buttonGroup1.add(rdbNao7);
        rdbNao7.setSelected(true); // Deixa o "Não" selecionado por padrão

        // Configurar os itens do ComboBox de Forma de Pagamento
        cbxFormaPagamento7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione...", "Cartão Crédito", "Cartão Débito", "Pix", "Dinheiro" }));

        // Configurar os itens do ComboBox de Parcelas e começar desativado (já que o padrão é Não parcelar)
        cbxParcelas7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1x", "2x", "3x", "4x", "5x", "6x" }));
        cbxParcelas7.setEnabled(false);

        // Evento para ativar/desativar o ComboBox de parcelas dinamicamente
        rdbSim7.addActionListener(e -> cbxParcelas7.setEnabled(true));
        rdbNao7.addActionListener(e -> {
            cbxParcelas7.setEnabled(false);
            cbxParcelas7.setSelectedIndex(0); // Reseta para 1x se mudar para Não
        });
        
        // Garantir que somente a tela de Cadastro de Usuários esteja disponível ao iniciar o sistema
        // Bloqueando todas as telas inicialmente
        tbpInterfaces.setEnabledAt(1, false);
        tbpInterfaces.setEnabledAt(2, false);
        tbpInterfaces.setEnabledAt(3, false);
        tbpInterfaces.setEnabledAt(4, false);
        tbpInterfaces.setEnabledAt(5, false);
        tbpInterfaces.setEnabledAt(6, false);
        tbpInterfaces.setEnabledAt(7, false);
        // ---------------------------------------------------
        
        // ----- Configurando elementos da Interface com a package FlatLaf -----
        
        // ----- Interface 1: Cadastrar Usuários
        
        // Para deixar o campo Telefone limpo assim que iniciar o sistema
        ftxtTelefone1.setText("");
        
        // Para adicionar os textos de dica (placeholders) dentro das caixas (JTextField)
        txtNome1.putClientProperty("JTextField.placeholderText", "Insira o seu nome completo");
        txtEmail1.putClientProperty("JTextField.placeholderText", "exemplo@email.com");
        //ftxtTelefone1.putClientProperty("JFormattedText.placeholder", "(00) 000000000");
        //txtSenha1.putClientProperty("JPasswordText.placeholder", "Digite uma senha segura");
        
        // Para arrendondar os cantos dos JTextFields
        txtNome1.putClientProperty("JComponent.roundRect", true);
        txtEmail1.putClientProperty("JComponent.roundRect", true);
        ftxtTelefone1.putClientProperty("JComponent.roundRect", true);
        txtSenha1.putClientProperty("JComponent.roundRect", true);
        
        // Para arredondar os botões Limpar e Cadastrar
        btnLogin1.putClientProperty("JButton.roundRect", true);
        btnCadastrar1.putClientProperty("JButton.roundRect", true);
        
        // Para deixar o botão Cadastrar com preenchimento destacado estilo FlatLab
        btnCadastrar1.putClientProperty("JButton.buttonType", "roundRect");
        // -------------------------------------
        
        // ----- Interface 2: Acessar Sistema
        
        // Para arredondar os cantos dos JTextFields
        txtEmail2.putClientProperty("JComponent.roundRect", true);
        txtSenha2.putClientProperty("JComponent.roundRect", true);
        
        // Para arredondar os botões Cancelar e Entrar
        btnCancelar2.putClientProperty("JComponent.roubdRect", true);
        btnEntrar2.putClientProperty("JComponent.roundRect", true);
        
        // Para deixar o botão Cadastrar com preenchimento destacado estilo FlatLab
        btnEntrar2.putClientProperty("JButton.buttonType", "roundRect");
        // ----------------------------------
        
        // ----- Interface 3: Consultar/Editar Usuários
        
        // Para deixar o campo Telefone limpo assim que iniciar o sistema
        ftxtTelefone3.setText("");
        // Para arredondar os cantos dos JTextFields
        txtNome3.putClientProperty("JComponent.roundRect", true);
        txtEmail3.putClientProperty("JComponent.roundRect", true);
        ftxtTelefone3.putClientProperty("JComponent.roundRect", true);
        txtSenha3.putClientProperty("JComponent.roundRect", true);
        
        // Para arredondar o botão Editar Usuário
        btnEditarUsuario3.putClientProperty("JComponent.roundRect", true);
        
        // Para deixar o botão Editar Usuário com preenchimento destacado estilo FlatLab
        btnEditarUsuario3.putClientProperty("JButton.buttonType", "roundRect");
        // ----------------------------------
        
        // ----- Interface 4: Cadastrar Produtos
        
        // Para adicionar os textos de dica (placeholders) dentro das caixas JTextFields
        txtNomeProduto4.putClientProperty("JTextField.placeholderText", "Bolo de Cenoura");
        txtCategoria4.putClientProperty("JTextField.placeholderText", "Doce");
        txtDescricao4.putClientProperty("JTextField.placeholderText", "Ingredientes da Massa\n\n- Cenouras: 3 unidades médias picadas"
                                                                    + "\n- Ovos: 3 unidades inteiras\n- Óleo: 1/2 xícara de chá"
                                                                    + "\n- Açúcar: 2 xícaras de chá\n- Farinha de trigo: 2 e 1/2 xícaras de chá"
                                                                    + "\nFermento em pó: 1 colher de sopa\n- Sal: 1 pitada");
        txtPreco4.putClientProperty("JTextField.placeholderText", "R$");
        txtQuantidade4.putClientProperty("JTextField.placeholderText", "Ex: 2");
        txtMarca4.putClientProperty("JTextField.placeholderText", "Casa da Vovó");
        
        // Para arredondar as bordas dos JTextFields
        txtNomeProduto4.putClientProperty("JComponent.roundRect", true);
        txtCategoria4.putClientProperty("JComponent.roundRect", true);
        txtDescricao4.putClientProperty("JComponent.roundRect", true);
        txtPreco4.putClientProperty("JComponent.roundRect", true);
        txtQuantidade4.putClientProperty("JComponent.roundRect", true);
        ftxtDataDeFabricacao4.putClientProperty("JComponent.roundRect", true);
        ftxtDataDeValidade4.putClientProperty("JComponent.roundRect", true);
        txtMarca4.putClientProperty("JComponent.roundRect", true);
        
        // Para arredondar as bordas dos botões Limpar e Cadastrar
        btnLimpar4.putClientProperty("JComponent.roundRect", true);
        btnCadastrar4.putClientProperty("JComponent.roundRect", true);
        
        // Para deixar o botão Cadastrar com preenchimento destacado estilo FlatLab
        btnCadastrar4.putClientProperty("JButton.buttonType", "roundRect");
        // ----------------------------------        
        
        // ----- Interface 5: Consultar/Editar Produtos  
        
        // Para arredondar as bordas dos JTextFields
        txtNomeProduto5.putClientProperty("JComponent.roundRect", true);
        txtCategoria5.putClientProperty("JComponent.roundRect", true);
        txtDescricao5.putClientProperty("JComponent.roundRect", true);
        txtPreco5.putClientProperty("JComponent.roundRect", true);
        txtQuantidade5.putClientProperty("JComponent.roundRect", true);
        ftxtDataDeFabricacao5.putClientProperty("JComponent.roundRect", true);
        ftxtDataDeValidade5.putClientProperty("JComponent.roundRect", true);
        txtMarca5.putClientProperty("JComponent.roundRect", true);
        
        // Para arredondar as bordas dos botões Limpar e Cadastrar
        btnEditarProduto5.putClientProperty("JComponent.roundRect", true);
        
        // Para deixar o botão Cadastrar com preenchimento destacado estilo FlatLab
        btnEditarProduto5.putClientProperty("JButton.buttonType", "roundRect");
        // ----------------------------------        
        
        // ----- Interface 6: Carrinho
        
        // Para arredondar as bordas dos JTextFields
        txtNomeProduto6.putClientProperty("JComponent.roundRect", true);
        txtMarca6.putClientProperty("JComponent.roundRect", true);
        txtQuantidade6.putClientProperty("JComponent.roundRect", true);
        txtSubtotal6.putClientProperty("JComponent.roundRect", true);
        
        // Para arredondar as bordas dos botões Remover e Adicionar
        btnRemover6.putClientProperty("JComponent.roundRect", true);
        btnAdicionar6.putClientProperty("JComponent.roundRect", true);
        btnComprar6.putClientProperty("JComponent.roundRect", true);
                
        // Para deixar os botões Remover e Adicionar com preenchimento destacado estilo FlatLab
        btnRemover6.putClientProperty("JButton.buttonType", "roundRect");
        btnAdicionar6.putClientProperty("JButton.buttonType", "roundRect");
        btnComprar6.putClientProperty("JButton.buttonType", "roundRect");
        // ----------------------------------        
        
        // ----- Interface 7: Vender
        
        // Para arredondar as bordas dos JTextFields
        txtEmail7.putClientProperty("JComponent.roundRect", true);
        txtSenha7.putClientProperty("JComponent.roundRect", true);
        txtTotalPagar7.putClientProperty("JComponent.roundRect", true);
        
        // Para arredondar as bordas dos ComboBox
        cbxFormaPagamento7.putClientProperty("JComponent.roundRect", true);
        cbxParcelas7.putClientProperty("JComponent.roundRect", true);
        
        // Para arredondar as bordas dos botões Cancelar e Vender
        btnCancelar7.putClientProperty("JComponent.roundRect", true);
        btnVender7.putClientProperty("JComponent.roundRect", true);
        
        // Para deixar os botões Remover e Adicionar com preenchimento destacado estilo FlatLab
        btnCancelar7.putClientProperty("JButton.buttonType", "roundRect");
        btnVender7.putClientProperty("JButton.buttonType", "roundRect");
        // ----------------------------------        
        
        // ----- Interface 8: Consultar Vendas
        
        // Para arredondar as bordas do JTextField
        // txtConsultarVendas8.putClientProperty("JComponent.roundRect", true);
        // ----------------------------------        
    }
    
    // Método que liga CoffeeDailyDAO com os CoffeeDailyTableModel
    public void carregarDados() {
        
        // ----- Para consiltarUsuarios() -----
        
        // Para pegar a lista de usuários retornada do método consultarUsuarios() de CoffeeDailyDAO
        java.util.List<Usuario> listaDoBanco;
        // Receber o retorno do método (A lista completa de usuários do Banco de Dados)
        listaDoBanco = cfdao.consultarUsuarios();
        // Passar a lista de usuários para o TableModel da Interface para atualizar automaticamente
        cdtmu3.atualizarTabUsuarios3(listaDoBanco);
        
        // ----- Para consultarProdutos() -----
        
        // Para pegar a lista de produtos retornada do método consultarProdutos() de CoffeeDailyDAO
        java.util.List<Produto> listaDeProdutos;
        // Recever o retorno do método (A lista completa de produtos do Banco de Dados)
        listaDeProdutos = cfdao.consultarProdutos();
        // Passar a lista de produtos para o TableModel da Interface para atualizar automaticamente
        cdtmp5.atualizarTabProdutos5(listaDeProdutos);
        cdtmp6.atualizarTabProdutos6(listaDeProdutos);
        
        // ----- Para consultarCarrinho() -----
        /*
        // Para pegar a lista de produtos retornada do método consultarProdutos() de CoffeeDailyDAO
        java.util.List<Produto> listaDoCarrinho;
        // Recever o retorno do método (A lista completa de produtos do Banco de Dados)
        listaDoCarrinho = cfdao.consultarCarrinho();
        // Passar a lista de produtos para o TableModel da Interface para atualizar automaticamente
        cdtmc6.atualizarTabCarrinho6(listaDoCarrinho);
        */
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        pnlCoffeeDaily = new javax.swing.JPanel();
        lblCoffee = new javax.swing.JLabel();
        lblDaily = new javax.swing.JLabel();
        lblCoffeeIcon = new javax.swing.JLabel();
        btnGitHubIcon = new javax.swing.JButton();
        btnLinkedinIcon = new javax.swing.JButton();
        btnSobre = new javax.swing.JButton();
        tbpInterfaces = new javax.swing.JTabbedPane();
        pnlCadastarUsuarios = new javax.swing.JPanel();
        lblRealizeCadastro1 = new javax.swing.JLabel();
        lblNome1 = new javax.swing.JLabel();
        txtNome1 = new javax.swing.JTextField();
        lblEmail1 = new javax.swing.JLabel();
        txtEmail1 = new javax.swing.JTextField();
        lblTelefone1 = new javax.swing.JLabel();
        lblSenha1 = new javax.swing.JLabel();
        btnLogin1 = new javax.swing.JButton();
        btnCadastrar1 = new javax.swing.JButton();
        txtSenha1 = new javax.swing.JPasswordField();
        ftxtTelefone1 = new javax.swing.JFormattedTextField();
        pnlAcessarSistema = new javax.swing.JPanel();
        lblSejaBemVindo2 = new javax.swing.JLabel();
        lblEmail2 = new javax.swing.JLabel();
        txtEmail2 = new javax.swing.JTextField();
        lblSenha2 = new javax.swing.JLabel();
        btnCancelar2 = new javax.swing.JButton();
        btnEntrar2 = new javax.swing.JButton();
        txtSenha2 = new javax.swing.JPasswordField();
        pnlConsultarUsuarios = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabUsuarios3 = new javax.swing.JTable();
        lblNome3 = new javax.swing.JLabel();
        txtNome3 = new javax.swing.JTextField();
        lblEmail3 = new javax.swing.JLabel();
        txtEmail3 = new javax.swing.JTextField();
        lblTelefone3 = new javax.swing.JLabel();
        lblSenha3 = new javax.swing.JLabel();
        txtSenha3 = new javax.swing.JPasswordField();
        lblCliqueNoUsuario3 = new javax.swing.JLabel();
        ftxtTelefone3 = new javax.swing.JFormattedTextField();
        btnEditarUsuario3 = new javax.swing.JButton();
        pnlCadastrarProdutos = new javax.swing.JPanel();
        lblNomeProduto4 = new javax.swing.JLabel();
        txtNomeProduto4 = new javax.swing.JTextField();
        lblCategoria4 = new javax.swing.JLabel();
        txtCategoria4 = new javax.swing.JTextField();
        lblDescricao4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescricao4 = new javax.swing.JTextArea();
        lblPreco4 = new javax.swing.JLabel();
        txtPreco4 = new javax.swing.JTextField();
        lblQuantidade4 = new javax.swing.JLabel();
        txtQuantidade4 = new javax.swing.JTextField();
        lblDataDeFabricacao4 = new javax.swing.JLabel();
        lblDataDeValidade4 = new javax.swing.JLabel();
        lblMarca4 = new javax.swing.JLabel();
        txtMarca4 = new javax.swing.JTextField();
        btnLimpar4 = new javax.swing.JButton();
        btnCadastrar4 = new javax.swing.JButton();
        ftxtDataDeFabricacao4 = new javax.swing.JFormattedTextField();
        ftxtDataDeValidade4 = new javax.swing.JFormattedTextField();
        pnlConsultarProdutos = new javax.swing.JPanel();
        lblNomeProduto5 = new javax.swing.JLabel();
        txtNomeProduto5 = new javax.swing.JTextField();
        lblCategoria5 = new javax.swing.JLabel();
        txtCategoria5 = new javax.swing.JTextField();
        lblDescricao5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDescricao5 = new javax.swing.JTextArea();
        lblPreco5 = new javax.swing.JLabel();
        txtPreco5 = new javax.swing.JTextField();
        lblQuantidade5 = new javax.swing.JLabel();
        txtQuantidade5 = new javax.swing.JTextField();
        lblDataDeFabricacao5 = new javax.swing.JLabel();
        ftxtDataDeFabricacao5 = new javax.swing.JFormattedTextField();
        lblDataDeValidade5 = new javax.swing.JLabel();
        ftxtDataDeValidade5 = new javax.swing.JFormattedTextField();
        lblMarca5 = new javax.swing.JLabel();
        txtMarca5 = new javax.swing.JTextField();
        btnEditarProduto5 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabProdutos5 = new javax.swing.JTable();
        lblCliqueNoUsuario4 = new javax.swing.JLabel();
        pnlCarrinho = new javax.swing.JPanel();
        lblNomeProduto6 = new javax.swing.JLabel();
        txtNomeProduto6 = new javax.swing.JTextField();
        lblQuantidade6 = new javax.swing.JLabel();
        txtQuantidade6 = new javax.swing.JTextField();
        lblMarca6 = new javax.swing.JLabel();
        txtMarca6 = new javax.swing.JTextField();
        lblSubtotal6 = new javax.swing.JLabel();
        txtSubtotal6 = new javax.swing.JTextField();
        btnRemover6 = new javax.swing.JButton();
        btnAdicionar6 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabCarrinho6 = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        tabProdutos6 = new javax.swing.JTable();
        btnComprar6 = new javax.swing.JButton();
        pnlVender = new javax.swing.JPanel();
        lblEmail7 = new javax.swing.JLabel();
        txtEmail7 = new javax.swing.JTextField();
        lblSenha7 = new javax.swing.JLabel();
        txtSenha7 = new javax.swing.JPasswordField();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabCarrinho7 = new javax.swing.JTable();
        lblTotalPagar7 = new javax.swing.JLabel();
        txtTotalPagar7 = new javax.swing.JTextField();
        lblParcelas7 = new javax.swing.JLabel();
        rdbSim7 = new javax.swing.JRadioButton();
        rdbNao7 = new javax.swing.JRadioButton();
        lblFormaPagamento7 = new javax.swing.JLabel();
        cbxFormaPagamento7 = new javax.swing.JComboBox<>();
        cbxParcelas7 = new javax.swing.JComboBox<>();
        btnCancelar7 = new javax.swing.JButton();
        btnVender7 = new javax.swing.JButton();
        lblMeuCarrinho7 = new javax.swing.JLabel();
        pnlConsultarVendas = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tabVendas8 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlCoffeeDaily.setBackground(new java.awt.Color(173, 131, 80));
        pnlCoffeeDaily.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        lblCoffee.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblCoffee.setForeground(new java.awt.Color(134, 51, 20));
        lblCoffee.setText("Coffee");

        lblDaily.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblDaily.setForeground(new java.awt.Color(0, 145, 48));
        lblDaily.setText("Daily");

        lblCoffeeIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-coffee-48.png"))); // NOI18N

        btnGitHubIcon.setBackground(new java.awt.Color(173, 131, 80));
        btnGitHubIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-github-50.png"))); // NOI18N
        btnGitHubIcon.addActionListener(this::btnGitHubIconActionPerformed);

        btnLinkedinIcon.setBackground(new java.awt.Color(173, 131, 80));
        btnLinkedinIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-linkedin-48.png"))); // NOI18N
        btnLinkedinIcon.setPreferredSize(new java.awt.Dimension(48, 48));
        btnLinkedinIcon.addActionListener(this::btnLinkedinIconActionPerformed);

        btnSobre.setBackground(new java.awt.Color(173, 131, 80));
        btnSobre.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSobre.setForeground(new java.awt.Color(255, 255, 255));
        btnSobre.setText("Sobre");
        btnSobre.addActionListener(this::btnSobreActionPerformed);

        javax.swing.GroupLayout pnlCoffeeDailyLayout = new javax.swing.GroupLayout(pnlCoffeeDaily);
        pnlCoffeeDaily.setLayout(pnlCoffeeDailyLayout);
        pnlCoffeeDailyLayout.setHorizontalGroup(
            pnlCoffeeDailyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCoffeeDailyLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(lblCoffee)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDaily)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCoffeeIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSobre)
                .addGap(18, 18, 18)
                .addComponent(btnGitHubIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLinkedinIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        pnlCoffeeDailyLayout.setVerticalGroup(
            pnlCoffeeDailyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCoffeeDailyLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(pnlCoffeeDailyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCoffeeDailyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCoffee)
                        .addComponent(lblDaily))
                    .addComponent(lblCoffeeIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSobre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGitHubIcon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLinkedinIcon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        tbpInterfaces.setBackground(new java.awt.Color(118, 65, 52));
        tbpInterfaces.setForeground(new java.awt.Color(255, 255, 255));
        tbpInterfaces.setPreferredSize(new java.awt.Dimension(1440, 800));

        pnlCadastarUsuarios.setBackground(new java.awt.Color(118, 65, 52));
        pnlCadastarUsuarios.setPreferredSize(new java.awt.Dimension(1148, 1024));

        lblRealizeCadastro1.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        lblRealizeCadastro1.setForeground(new java.awt.Color(0, 145, 48));
        lblRealizeCadastro1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRealizeCadastro1.setText("Realize seu cadastro para acessar o sistema!");

        lblNome1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNome1.setForeground(new java.awt.Color(255, 255, 255));
        lblNome1.setText("Nome");

        txtNome1.setBackground(new java.awt.Color(255, 255, 255));
        txtNome1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblEmail1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblEmail1.setForeground(new java.awt.Color(255, 255, 255));
        lblEmail1.setText("Email");

        txtEmail1.setBackground(new java.awt.Color(255, 255, 255));
        txtEmail1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblTelefone1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTelefone1.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefone1.setText("Telefone");

        lblSenha1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSenha1.setForeground(new java.awt.Color(255, 255, 255));
        lblSenha1.setText("Senha");

        btnLogin1.setBackground(new java.awt.Color(118, 65, 52));
        btnLogin1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLogin1.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin1.setText("Login");
        btnLogin1.addActionListener(this::btnLogin1ActionPerformed);

        btnCadastrar1.setBackground(new java.awt.Color(0, 0, 0));
        btnCadastrar1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCadastrar1.setForeground(new java.awt.Color(255, 255, 255));
        btnCadastrar1.setText("Cadastrar");
        btnCadastrar1.addActionListener(this::btnCadastrar1ActionPerformed);

        txtSenha1.setBackground(new java.awt.Color(255, 255, 255));
        txtSenha1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        ftxtTelefone1.setBackground(new java.awt.Color(255, 255, 255));
        ftxtTelefone1.setForeground(new java.awt.Color(0, 0, 0));
        try {
            ftxtTelefone1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout pnlCadastarUsuariosLayout = new javax.swing.GroupLayout(pnlCadastarUsuarios);
        pnlCadastarUsuarios.setLayout(pnlCadastarUsuariosLayout);
        pnlCadastarUsuariosLayout.setHorizontalGroup(
            pnlCadastarUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCadastarUsuariosLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnLogin1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100)
                .addComponent(btnCadastrar1)
                .addGap(420, 420, 420))
            .addGroup(pnlCadastarUsuariosLayout.createSequentialGroup()
                .addGap(352, 352, 352)
                .addGroup(pnlCadastarUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNome1)
                    .addComponent(txtEmail1)
                    .addComponent(lblRealizeCadastro1, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                    .addComponent(lblNome1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTelefone1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSenha1)
                    .addComponent(ftxtTelefone1))
                .addContainerGap(323, Short.MAX_VALUE))
        );
        pnlCadastarUsuariosLayout.setVerticalGroup(
            pnlCadastarUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastarUsuariosLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(lblRealizeCadastro1)
                .addGap(56, 56, 56)
                .addComponent(lblNome1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNome1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEmail1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTelefone1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ftxtTelefone1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSenha1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(pnlCadastarUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogin1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(276, Short.MAX_VALUE))
        );

        tbpInterfaces.addTab("Cadastar Usuários", pnlCadastarUsuarios);

        pnlAcessarSistema.setBackground(new java.awt.Color(118, 65, 52));

        lblSejaBemVindo2.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        lblSejaBemVindo2.setForeground(new java.awt.Color(0, 145, 48));
        lblSejaBemVindo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSejaBemVindo2.setText("Seja bem-vindo(a)!");

        lblEmail2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblEmail2.setForeground(new java.awt.Color(255, 255, 255));
        lblEmail2.setText("Email");

        txtEmail2.setBackground(new java.awt.Color(255, 255, 255));
        txtEmail2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblSenha2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSenha2.setForeground(new java.awt.Color(255, 255, 255));
        lblSenha2.setText("Senha");

        btnCancelar2.setBackground(new java.awt.Color(118, 65, 52));
        btnCancelar2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancelar2.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar2.setText("Cancelar");
        btnCancelar2.addActionListener(this::btnCancelar2ActionPerformed);

        btnEntrar2.setBackground(new java.awt.Color(0, 0, 0));
        btnEntrar2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEntrar2.setForeground(new java.awt.Color(255, 255, 255));
        btnEntrar2.setText("Entrar");
        btnEntrar2.addActionListener(this::btnEntrar2ActionPerformed);

        txtSenha2.setBackground(new java.awt.Color(255, 255, 255));
        txtSenha2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout pnlAcessarSistemaLayout = new javax.swing.GroupLayout(pnlAcessarSistema);
        pnlAcessarSistema.setLayout(pnlAcessarSistemaLayout);
        pnlAcessarSistemaLayout.setHorizontalGroup(
            pnlAcessarSistemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAcessarSistemaLayout.createSequentialGroup()
                .addContainerGap(346, Short.MAX_VALUE)
                .addGroup(pnlAcessarSistemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAcessarSistemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtEmail2)
                        .addComponent(lblEmail2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSenha2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSenha2, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlAcessarSistemaLayout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(btnCancelar2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(btnEntrar2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(329, 329, 329))
            .addGroup(pnlAcessarSistemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlAcessarSistemaLayout.createSequentialGroup()
                    .addGap(336, 336, 336)
                    .addComponent(lblSejaBemVindo2, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(339, Short.MAX_VALUE)))
        );
        pnlAcessarSistemaLayout.setVerticalGroup(
            pnlAcessarSistemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAcessarSistemaLayout.createSequentialGroup()
                .addGap(196, 196, 196)
                .addComponent(lblEmail2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblSenha2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSenha2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addGroup(pnlAcessarSistemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEntrar2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(323, Short.MAX_VALUE))
            .addGroup(pnlAcessarSistemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlAcessarSistemaLayout.createSequentialGroup()
                    .addGap(106, 106, 106)
                    .addComponent(lblSejaBemVindo2)
                    .addContainerGap(630, Short.MAX_VALUE)))
        );

        tbpInterfaces.addTab("Acessar Sistema", pnlAcessarSistema);

        pnlConsultarUsuarios.setBackground(new java.awt.Color(118, 65, 52));

        tabUsuarios3.setForeground(new java.awt.Color(0, 0, 0));
        tabUsuarios3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Email", "Telefone", "Senha"
            }
        ));
        tabUsuarios3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabUsuarios3MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabUsuarios3);

        lblNome3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNome3.setForeground(new java.awt.Color(255, 255, 255));
        lblNome3.setText("Nome");

        txtNome3.setBackground(new java.awt.Color(255, 255, 255));
        txtNome3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblEmail3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblEmail3.setForeground(new java.awt.Color(255, 255, 255));
        lblEmail3.setText("Email");

        txtEmail3.setBackground(new java.awt.Color(255, 255, 255));
        txtEmail3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblTelefone3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTelefone3.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefone3.setText("Telefone");

        lblSenha3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSenha3.setForeground(new java.awt.Color(255, 255, 255));
        lblSenha3.setText("Senha");

        txtSenha3.setBackground(new java.awt.Color(255, 255, 255));
        txtSenha3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblCliqueNoUsuario3.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblCliqueNoUsuario3.setForeground(new java.awt.Color(0, 145, 48));
        lblCliqueNoUsuario3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCliqueNoUsuario3.setText("Clique no usuário desejado para realizar as alterações!");

        ftxtTelefone3.setBackground(new java.awt.Color(255, 255, 255));
        ftxtTelefone3.setForeground(new java.awt.Color(0, 0, 0));
        try {
            ftxtTelefone3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        btnEditarUsuario3.setBackground(new java.awt.Color(0, 0, 0));
        btnEditarUsuario3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEditarUsuario3.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarUsuario3.setText("Editar Usuário");
        btnEditarUsuario3.addActionListener(this::btnEditarUsuario3ActionPerformed);

        javax.swing.GroupLayout pnlConsultarUsuariosLayout = new javax.swing.GroupLayout(pnlConsultarUsuarios);
        pnlConsultarUsuarios.setLayout(pnlConsultarUsuariosLayout);
        pnlConsultarUsuariosLayout.setHorizontalGroup(
            pnlConsultarUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlConsultarUsuariosLayout.createSequentialGroup()
                .addGroup(pnlConsultarUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlConsultarUsuariosLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(pnlConsultarUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail3)
                            .addComponent(txtNome3)
                            .addComponent(txtSenha3)
                            .addComponent(ftxtTelefone3)
                            .addGroup(pnlConsultarUsuariosLayout.createSequentialGroup()
                                .addGroup(pnlConsultarUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNome3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblEmail3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTelefone3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblSenha3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 128, Short.MAX_VALUE))))
                    .addGroup(pnlConsultarUsuariosLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEditarUsuario3)))
                .addGap(47, 47, 47)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
            .addGroup(pnlConsultarUsuariosLayout.createSequentialGroup()
                .addGap(323, 323, 323)
                .addComponent(lblCliqueNoUsuario3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlConsultarUsuariosLayout.setVerticalGroup(
            pnlConsultarUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConsultarUsuariosLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(lblCliqueNoUsuario3)
                .addGroup(pnlConsultarUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlConsultarUsuariosLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlConsultarUsuariosLayout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(lblNome3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNome3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEmail3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmail3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTelefone3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ftxtTelefone3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSenha3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSenha3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnEditarUsuario3)))
                .addContainerGap(168, Short.MAX_VALUE))
        );

        tbpInterfaces.addTab("Consultar/Editar Usuários", pnlConsultarUsuarios);

        pnlCadastrarProdutos.setBackground(new java.awt.Color(118, 65, 52));

        lblNomeProduto4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNomeProduto4.setForeground(new java.awt.Color(255, 255, 255));
        lblNomeProduto4.setText("Nome do Produto");

        txtNomeProduto4.setBackground(new java.awt.Color(255, 255, 255));
        txtNomeProduto4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblCategoria4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCategoria4.setForeground(new java.awt.Color(255, 255, 255));
        lblCategoria4.setText("Categoria");

        txtCategoria4.setBackground(new java.awt.Color(255, 255, 255));
        txtCategoria4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblDescricao4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDescricao4.setForeground(new java.awt.Color(255, 255, 255));
        lblDescricao4.setText("Descrição");

        txtDescricao4.setBackground(new java.awt.Color(255, 255, 255));
        txtDescricao4.setColumns(20);
        txtDescricao4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDescricao4.setRows(5);
        jScrollPane2.setViewportView(txtDescricao4);

        lblPreco4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPreco4.setForeground(new java.awt.Color(255, 255, 255));
        lblPreco4.setText("Preço");

        txtPreco4.setBackground(new java.awt.Color(255, 255, 255));
        txtPreco4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblQuantidade4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblQuantidade4.setForeground(new java.awt.Color(255, 255, 255));
        lblQuantidade4.setText("Quantidade");

        txtQuantidade4.setBackground(new java.awt.Color(255, 255, 255));
        txtQuantidade4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblDataDeFabricacao4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeFabricacao4.setForeground(new java.awt.Color(255, 255, 255));
        lblDataDeFabricacao4.setText("Data de Fabricação");

        lblDataDeValidade4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeValidade4.setForeground(new java.awt.Color(255, 255, 255));
        lblDataDeValidade4.setText("Data de Validade");

        lblMarca4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMarca4.setForeground(new java.awt.Color(255, 255, 255));
        lblMarca4.setText("Marca");

        txtMarca4.setBackground(new java.awt.Color(255, 255, 255));
        txtMarca4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnLimpar4.setBackground(new java.awt.Color(118, 65, 52));
        btnLimpar4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLimpar4.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpar4.setText("Limpar");
        btnLimpar4.addActionListener(this::btnLimpar4ActionPerformed);

        btnCadastrar4.setBackground(new java.awt.Color(0, 0, 0));
        btnCadastrar4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCadastrar4.setForeground(new java.awt.Color(255, 255, 255));
        btnCadastrar4.setText("Cadastrar");
        btnCadastrar4.addActionListener(this::btnCadastrar4ActionPerformed);

        ftxtDataDeFabricacao4.setBackground(new java.awt.Color(255, 255, 255));
        ftxtDataDeFabricacao4.setForeground(new java.awt.Color(0, 0, 0));
        try {
            ftxtDataDeFabricacao4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        ftxtDataDeValidade4.setBackground(new java.awt.Color(255, 255, 255));
        ftxtDataDeValidade4.setForeground(new java.awt.Color(0, 0, 0));
        try {
            ftxtDataDeValidade4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout pnlCadastrarProdutosLayout = new javax.swing.GroupLayout(pnlCadastrarProdutos);
        pnlCadastrarProdutos.setLayout(pnlCadastrarProdutosLayout);
        pnlCadastrarProdutosLayout.setHorizontalGroup(
            pnlCadastrarProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastrarProdutosLayout.createSequentialGroup()
                .addGap(251, 251, 251)
                .addGroup(pnlCadastrarProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCadastrarProdutosLayout.createSequentialGroup()
                        .addComponent(lblPreco4)
                        .addGap(336, 336, 336))
                    .addGroup(pnlCadastrarProdutosLayout.createSequentialGroup()
                        .addGroup(pnlCadastrarProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCadastrarProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblNomeProduto4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblDescricao4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblCategoria4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtCategoria4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNomeProduto4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtPreco4, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(80, 80, 80)))
                .addGroup(pnlCadastrarProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtQuantidade4)
                    .addComponent(ftxtDataDeFabricacao4)
                    .addComponent(txtMarca4)
                    .addComponent(ftxtDataDeValidade4)
                    .addGroup(pnlCadastrarProdutosLayout.createSequentialGroup()
                        .addGroup(pnlCadastrarProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblQuantidade4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDataDeFabricacao4)
                            .addComponent(lblDataDeValidade4)
                            .addComponent(lblMarca4))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCadastrarProdutosLayout.createSequentialGroup()
                        .addComponent(btnLimpar4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                        .addComponent(btnCadastrar4)))
                .addGap(251, 251, 251))
        );
        pnlCadastrarProdutosLayout.setVerticalGroup(
            pnlCadastrarProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastrarProdutosLayout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addGroup(pnlCadastrarProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNomeProduto4)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCadastrarProdutosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblQuantidade4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCadastrarProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNomeProduto4)
                    .addComponent(txtQuantidade4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCadastrarProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCategoria4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblDataDeFabricacao4))
                .addGap(6, 6, 6)
                .addGroup(pnlCadastrarProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCategoria4, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(ftxtDataDeFabricacao4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCadastrarProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDescricao4)
                    .addComponent(lblDataDeValidade4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCadastrarProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlCadastrarProdutosLayout.createSequentialGroup()
                        .addComponent(ftxtDataDeValidade4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMarca4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtMarca4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlCadastrarProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCadastrarProdutosLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(pnlCadastrarProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCadastrar4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLimpar4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlCadastrarProdutosLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblPreco4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPreco4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(258, 258, 258))
        );

        tbpInterfaces.addTab("Cadastrar Produtos", pnlCadastrarProdutos);

        pnlConsultarProdutos.setBackground(new java.awt.Color(118, 65, 52));

        lblNomeProduto5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNomeProduto5.setForeground(new java.awt.Color(255, 255, 255));
        lblNomeProduto5.setText("Nome do Produto");

        txtNomeProduto5.setBackground(new java.awt.Color(255, 255, 255));
        txtNomeProduto5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblCategoria5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCategoria5.setForeground(new java.awt.Color(255, 255, 255));
        lblCategoria5.setText("Categoria");

        txtCategoria5.setBackground(new java.awt.Color(255, 255, 255));
        txtCategoria5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblDescricao5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDescricao5.setForeground(new java.awt.Color(255, 255, 255));
        lblDescricao5.setText("Descrição");

        txtDescricao5.setBackground(new java.awt.Color(255, 255, 255));
        txtDescricao5.setColumns(20);
        txtDescricao5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDescricao5.setRows(5);
        jScrollPane3.setViewportView(txtDescricao5);

        lblPreco5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPreco5.setForeground(new java.awt.Color(255, 255, 255));
        lblPreco5.setText("Preço");

        txtPreco5.setBackground(new java.awt.Color(255, 255, 255));
        txtPreco5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblQuantidade5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblQuantidade5.setForeground(new java.awt.Color(255, 255, 255));
        lblQuantidade5.setText("Quantidade");

        txtQuantidade5.setBackground(new java.awt.Color(255, 255, 255));
        txtQuantidade5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblDataDeFabricacao5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeFabricacao5.setForeground(new java.awt.Color(255, 255, 255));
        lblDataDeFabricacao5.setText("Data de Fabricação");

        ftxtDataDeFabricacao5.setBackground(new java.awt.Color(255, 255, 255));
        ftxtDataDeFabricacao5.setForeground(new java.awt.Color(0, 0, 0));
        try {
            ftxtDataDeFabricacao5.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lblDataDeValidade5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeValidade5.setForeground(new java.awt.Color(255, 255, 255));
        lblDataDeValidade5.setText("Data de Validade");

        ftxtDataDeValidade5.setBackground(new java.awt.Color(255, 255, 255));
        ftxtDataDeValidade5.setForeground(new java.awt.Color(0, 0, 0));
        try {
            ftxtDataDeValidade5.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lblMarca5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMarca5.setForeground(new java.awt.Color(255, 255, 255));
        lblMarca5.setText("Marca");

        txtMarca5.setBackground(new java.awt.Color(255, 255, 255));
        txtMarca5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnEditarProduto5.setBackground(new java.awt.Color(0, 0, 0));
        btnEditarProduto5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEditarProduto5.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarProduto5.setText("Editar Produto");
        btnEditarProduto5.addActionListener(this::btnEditarProduto5ActionPerformed);

        tabProdutos5.setForeground(new java.awt.Color(0, 0, 0));
        tabProdutos5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Categoria", "Descrição", "Preço", "Quantidade", "Fabricação", "Validade", "Marca"
            }
        ));
        tabProdutos5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabProdutos5MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tabProdutos5);

        lblCliqueNoUsuario4.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblCliqueNoUsuario4.setForeground(new java.awt.Color(0, 145, 48));
        lblCliqueNoUsuario4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCliqueNoUsuario4.setText("Clique no produto desejado para realizar as alterações!");

        javax.swing.GroupLayout pnlConsultarProdutosLayout = new javax.swing.GroupLayout(pnlConsultarProdutos);
        pnlConsultarProdutos.setLayout(pnlConsultarProdutosLayout);
        pnlConsultarProdutosLayout.setHorizontalGroup(
            pnlConsultarProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConsultarProdutosLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pnlConsultarProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNomeProduto5)
                    .addComponent(lblDescricao5)
                    .addComponent(lblCategoria5)
                    .addComponent(txtCategoria5, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtNomeProduto5))
                .addGroup(pnlConsultarProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlConsultarProdutosLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(pnlConsultarProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlConsultarProdutosLayout.createSequentialGroup()
                                .addGroup(pnlConsultarProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblQuantidade5, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblDataDeFabricacao5)
                                    .addComponent(lblDataDeValidade5)
                                    .addComponent(lblMarca5))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pnlConsultarProdutosLayout.createSequentialGroup()
                                .addGroup(pnlConsultarProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlConsultarProdutosLayout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(lblPreco5))
                                    .addComponent(txtPreco5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(pnlConsultarProdutosLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(pnlConsultarProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtQuantidade5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ftxtDataDeFabricacao5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ftxtDataDeValidade5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMarca5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditarProduto5, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlConsultarProdutosLayout.createSequentialGroup()
                .addContainerGap(315, Short.MAX_VALUE)
                .addComponent(lblCliqueNoUsuario4)
                .addGap(313, 313, 313))
        );
        pnlConsultarProdutosLayout.setVerticalGroup(
            pnlConsultarProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConsultarProdutosLayout.createSequentialGroup()
                .addGroup(pnlConsultarProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlConsultarProdutosLayout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addGroup(pnlConsultarProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlConsultarProdutosLayout.createSequentialGroup()
                                .addComponent(lblNomeProduto5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNomeProduto5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlConsultarProdutosLayout.createSequentialGroup()
                                .addComponent(lblPreco5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPreco5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(pnlConsultarProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlConsultarProdutosLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCategoria5)
                                .addGap(6, 6, 6)
                                .addComponent(txtCategoria5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDescricao5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3))
                            .addGroup(pnlConsultarProdutosLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(lblQuantidade5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtQuantidade5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDataDeFabricacao5)
                                .addGap(6, 6, 6)
                                .addComponent(ftxtDataDeFabricacao5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDataDeValidade5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ftxtDataDeValidade5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblMarca5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMarca5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70)))
                        .addGap(11, 11, 11)
                        .addComponent(btnEditarProduto5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlConsultarProdutosLayout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(lblCliqueNoUsuario4)
                        .addGap(37, 37, 37)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(157, Short.MAX_VALUE))
        );

        tbpInterfaces.addTab("Consultar/Editar Produtos", pnlConsultarProdutos);

        pnlCarrinho.setBackground(new java.awt.Color(118, 65, 52));

        lblNomeProduto6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNomeProduto6.setForeground(new java.awt.Color(255, 255, 255));
        lblNomeProduto6.setText("Nome do Produto");

        txtNomeProduto6.setBackground(new java.awt.Color(255, 255, 255));
        txtNomeProduto6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblQuantidade6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblQuantidade6.setForeground(new java.awt.Color(255, 255, 255));
        lblQuantidade6.setText("Quantidade");

        txtQuantidade6.setBackground(new java.awt.Color(255, 255, 255));
        txtQuantidade6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblMarca6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMarca6.setForeground(new java.awt.Color(255, 255, 255));
        lblMarca6.setText("Marca");

        txtMarca6.setBackground(new java.awt.Color(255, 255, 255));
        txtMarca6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblSubtotal6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSubtotal6.setForeground(new java.awt.Color(0, 145, 48));
        lblSubtotal6.setText("Subtotal");

        txtSubtotal6.setBackground(new java.awt.Color(255, 255, 255));
        txtSubtotal6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnRemover6.setBackground(new java.awt.Color(0, 0, 0));
        btnRemover6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRemover6.setForeground(new java.awt.Color(255, 255, 255));
        btnRemover6.setText("Remover");
        btnRemover6.addActionListener(this::btnRemover6ActionPerformed);

        btnAdicionar6.setBackground(new java.awt.Color(0, 0, 0));
        btnAdicionar6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAdicionar6.setForeground(new java.awt.Color(255, 255, 255));
        btnAdicionar6.setText("Adicionar");
        btnAdicionar6.addActionListener(this::btnAdicionar6ActionPerformed);

        tabCarrinho6.setForeground(new java.awt.Color(0, 0, 0));
        tabCarrinho6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Marca", "Quantidade", "Preço"
            }
        ));
        tabCarrinho6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabCarrinho6MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tabCarrinho6);

        tabProdutos6.setForeground(new java.awt.Color(0, 0, 0));
        tabProdutos6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Categoria", "Descrição", "Preço", "Quantidade", "Fabricação", "Validade", "Marca"
            }
        ));
        tabProdutos6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabProdutos6MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tabProdutos6);

        btnComprar6.setBackground(new java.awt.Color(0, 145, 48));
        btnComprar6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnComprar6.setForeground(new java.awt.Color(255, 255, 255));
        btnComprar6.setText("Comprar");
        btnComprar6.addActionListener(this::btnComprar6ActionPerformed);

        javax.swing.GroupLayout pnlCarrinhoLayout = new javax.swing.GroupLayout(pnlCarrinho);
        pnlCarrinho.setLayout(pnlCarrinhoLayout);
        pnlCarrinhoLayout.setHorizontalGroup(
            pnlCarrinhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCarrinhoLayout.createSequentialGroup()
                .addGroup(pnlCarrinhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCarrinhoLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(pnlCarrinhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblNomeProduto6)
                            .addComponent(lblMarca6)
                            .addComponent(txtNomeProduto6, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                            .addComponent(txtMarca6)
                            .addGroup(pnlCarrinhoLayout.createSequentialGroup()
                                .addGroup(pnlCarrinhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblQuantidade6, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtQuantidade6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlCarrinhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblSubtotal6)
                                    .addComponent(txtSubtotal6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(pnlCarrinhoLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(btnRemover6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAdicionar6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(btnComprar6)))
                .addGap(16, 16, 16)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );
        pnlCarrinhoLayout.setVerticalGroup(
            pnlCarrinhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCarrinhoLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(lblNomeProduto6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCarrinhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlCarrinhoLayout.createSequentialGroup()
                        .addComponent(txtNomeProduto6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblMarca6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMarca6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnlCarrinhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCarrinhoLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(pnlCarrinhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtQuantidade6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSubtotal6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(44, 44, 44)
                                .addGroup(pnlCarrinhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnRemover6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAdicionar6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnComprar6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlCarrinhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblSubtotal6)
                                .addComponent(lblQuantidade6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbpInterfaces.addTab("Carrinho", pnlCarrinho);

        pnlVender.setBackground(new java.awt.Color(118, 65, 52));

        lblEmail7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblEmail7.setForeground(new java.awt.Color(255, 255, 255));
        lblEmail7.setText("Email");

        txtEmail7.setBackground(new java.awt.Color(255, 255, 255));
        txtEmail7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblSenha7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSenha7.setForeground(new java.awt.Color(255, 255, 255));
        lblSenha7.setText("Senha");

        txtSenha7.setBackground(new java.awt.Color(255, 255, 255));
        txtSenha7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        tabCarrinho7.setForeground(new java.awt.Color(0, 0, 0));
        tabCarrinho7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Marca", "Quantidade", "Preço"
            }
        ));
        jScrollPane6.setViewportView(tabCarrinho7);

        lblTotalPagar7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotalPagar7.setForeground(new java.awt.Color(0, 145, 48));
        lblTotalPagar7.setText("Total a Pagar");

        txtTotalPagar7.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalPagar7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblParcelas7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblParcelas7.setForeground(new java.awt.Color(255, 255, 255));
        lblParcelas7.setText("Parcelas");

        rdbSim7.setForeground(new java.awt.Color(255, 255, 255));
        rdbSim7.setText("Sim");

        rdbNao7.setForeground(new java.awt.Color(255, 255, 255));
        rdbNao7.setText("Não");

        lblFormaPagamento7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblFormaPagamento7.setForeground(new java.awt.Color(255, 255, 255));
        lblFormaPagamento7.setText("Forma de Pagamento");

        cbxFormaPagamento7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxFormaPagamento7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbxParcelas7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxParcelas7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnCancelar7.setBackground(new java.awt.Color(0, 0, 0));
        btnCancelar7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancelar7.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar7.setText("Cancelar");
        btnCancelar7.addActionListener(this::btnCancelar7ActionPerformed);

        btnVender7.setBackground(new java.awt.Color(0, 0, 0));
        btnVender7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVender7.setForeground(new java.awt.Color(255, 255, 255));
        btnVender7.setText("Vender");
        btnVender7.addActionListener(this::btnVender7ActionPerformed);

        lblMeuCarrinho7.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblMeuCarrinho7.setForeground(new java.awt.Color(0, 145, 48));
        lblMeuCarrinho7.setText("Meu carrinho");

        javax.swing.GroupLayout pnlVenderLayout = new javax.swing.GroupLayout(pnlVender);
        pnlVender.setLayout(pnlVenderLayout);
        pnlVenderLayout.setHorizontalGroup(
            pnlVenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVenderLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(pnlVenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEmail7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSenha7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlVenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtSenha7, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtEmail7, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlVenderLayout.createSequentialGroup()
                            .addGroup(pnlVenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblTotalPagar7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblFormaPagamento7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbxFormaPagamento7, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTotalPagar7, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(80, 80, 80)
                            .addGroup(pnlVenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblParcelas7)
                                .addComponent(rdbSim7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(rdbNao7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbxParcelas7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlVenderLayout.createSequentialGroup()
                            .addComponent(btnCancelar7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnVender7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVenderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblMeuCarrinho7)
                .addGap(320, 320, 320))
        );
        pnlVenderLayout.setVerticalGroup(
            pnlVenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVenderLayout.createSequentialGroup()
                .addGroup(pnlVenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlVenderLayout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(lblEmail7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmail7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblSenha7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSenha7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlVenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlVenderLayout.createSequentialGroup()
                                .addComponent(lblTotalPagar7)
                                .addGap(2, 2, 2)
                                .addComponent(txtTotalPagar7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblFormaPagamento7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxFormaPagamento7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlVenderLayout.createSequentialGroup()
                                .addComponent(lblParcelas7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdbSim7)
                                .addGap(18, 18, 18)
                                .addComponent(rdbNao7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxParcelas7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(43, 43, 43)
                        .addGroup(pnlVenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCancelar7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVender7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlVenderLayout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(lblMeuCarrinho7)
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(173, Short.MAX_VALUE))
        );

        tbpInterfaces.addTab("Vender", pnlVender);

        pnlConsultarVendas.setBackground(new java.awt.Color(118, 65, 52));

        tabVendas8.setForeground(new java.awt.Color(0, 0, 0));
        tabVendas8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Email", "Forma de Pagamento", "Parcelas", "Valor pago"
            }
        ));
        jScrollPane8.setViewportView(tabVendas8);

        javax.swing.GroupLayout pnlConsultarVendasLayout = new javax.swing.GroupLayout(pnlConsultarVendas);
        pnlConsultarVendas.setLayout(pnlConsultarVendasLayout);
        pnlConsultarVendasLayout.setHorizontalGroup(
            pnlConsultarVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConsultarVendasLayout.createSequentialGroup()
                .addContainerGap(158, Short.MAX_VALUE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 836, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(156, 156, 156))
        );
        pnlConsultarVendasLayout.setVerticalGroup(
            pnlConsultarVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlConsultarVendasLayout.createSequentialGroup()
                .addContainerGap(77, Short.MAX_VALUE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );

        tbpInterfaces.addTab("Consultar Vendas", pnlConsultarVendas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCoffeeDaily, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(tbpInterfaces, javax.swing.GroupLayout.PREFERRED_SIZE, 1150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(148, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlCoffeeDaily, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tbpInterfaces, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Botão responsável por exibir o objetivo do projeto, meu nome e código de matrícula
    private void btnSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSobreActionPerformed
        
        String sobre = String.format("Sobre o Projeto:\n\nDurante a disciplina de Laboratório de Programação Orientada a Objetos, "
                                    + "entre diversos temas propostos pelo Prof. Dr. Rodrigo Plotze, "
                                    + "escolhi desenvolver um projeto em cima do tema Cafeteria e o nomeei de CoffeeDaily. "
                                    + "Com o NetBeans, criei um projeto Java Application para que pudesse utilizar a API da linguagem Java: JDBC, "
                                    + "e adicionei um Driver de conexão do SGBD PostgreSQL nas dependências do arquivo pom.xml para que fosse possível "
                                    + "integrar o Banco de Dados criado na aplicação Java. Além disso, "
                                    + "a arquitetura do projeto segue o padrão de projeto MVC (Model-View-Controller), "
                                    + "e segue o paradigma de programação orientada a objetos (POO) para garantir que o código seja eficaz, "
                                    + "organizado, manutenível e eficiênte, seguindo as boas práticas de desenvolvimento de software.\n"
                                    + "\nMeu objetivo com esse projeto é colocar em prática tudo àquilo que tive a oportunidade de aprender até a "
                                    + "5° etapa do curso, praticar bastante o desenvolvimento de software para adquirir cada vez mais experiência e "
                                    + "capacidade técnica para conseguir atingir minhas metas e objetivos. Este é o meu primeiro projeto prático completo, "
                                    + "com Interface planejada por prototipação no Figma, Diagrama Entidade-Relacionamento (DER) planejado para estruturar "
                                    + "da melhor forma o Banco de Dados da aplicação, Back-End e Front-End desenvolvido completamente com Java, entre diveras outras coisas.\n"
                                    + "\nDe modo geral, estou satisfeito com o quanto pude evoluir desenvolvendo esse primeiro projeto pessoal completo com Java, "
                                    + "PostgreSQL, aplicando os paradigmas de Programação Orientada a Objetos e o padrão de projeto MVC "
                                    + "(Model-View-Controller) que a disciplina me propôs. Continuarei dando o meu melhor para abraçar todas as oportunidades "
                                    + "que surgirem ao longo da minha jornada para me tornar o melhor profissional, com a melhor capacitação possível. Ainda é só o começo!\n"
                                    + "\nMichael Gabriel Ferreira Chioda - 843221");
        
        JOptionPane.showMessageDialog(null, sobre);
    }//GEN-LAST:event_btnSobreActionPerformed

    // ------- Método responsável por abrir o navegador padrão do usuário -------
    public void abrirNavegadorUsuario(String URL) {
        try {
            // Primeiramente, verificar se o Sistema Operacional suporta a operação de abrir o navegador padrão
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE))
                Desktop.getDesktop().browse(new URI(URL));
            else
                JOptionPane.showMessageDialog(this, "Navegador web não é suportado neste Sistema Operacional", "ERRO", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao tentat abrir o link: " + e.getMessage());
        }
    }
    // ----- Implementação dos botões: -----
    // Botão responsável por redirecionar o usuário ao meu GitHub
    private void btnGitHubIconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGitHubIconActionPerformed
        abrirNavegadorUsuario("https://github.com/michaelgfchioda/coffee-daily-project");
    }//GEN-LAST:event_btnGitHubIconActionPerformed

    // Botão responsável por redirecionar o usuário ao meu Linkedin
    private void btnLinkedinIconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLinkedinIconActionPerformed
        abrirNavegadorUsuario("https://www.linkedin.com/in/michael-chioda-25223b309/");
    }//GEN-LAST:event_btnLinkedinIconActionPerformed
    // -------------------------------------------------------------------------
    
    
    // ---------- Métodos da Interface 1: Cadastrar Usuários ----------
    
    // Botão responsável por cadastrar novos usuários do sistema
    private void btnCadastrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrar1ActionPerformed
        
        // Verificar se os campos estão vazios
        if (txtNome1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo 'Nome' deve ser informado!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else if (txtEmail1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo 'Email' deve ser informado!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else if (ftxtTelefone1.getText().replaceAll("[^0-9]", "").isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo 'Telefone' deve ser informado!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else if (txtSenha1.getPassword().length == 0) {
            JOptionPane.showMessageDialog(null, "O campo 'Senha' deve ser informado!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else {
            
            // Tratamento do campo SENHA atribuído a uma variável (Conversão correta para String)
            String senhaDigitada = String.valueOf(txtSenha1.getPassword()).trim();
            
            // Variável que receberá o retorno do método de Verificação de Existência de Usuários
            boolean result = cfdao.verificarExistenciaUsuario(new Usuario(
                0,                                  // O id_usuario não será passado por porâmetro, pois é SERIAL
                txtNome1.getText().trim(), 
                txtEmail1.getText().trim(), 
                ftxtTelefone1.getText().trim(), 
                senhaDigitada                       // ou simplesmente: String.valueOf(txtSenha1.getPassword()).trim()
            ));
            
            // Se o retorno do método for true, significa que o mesmo email e/ou telefone foi encontrado no Banco de Dados
            if (result == true) {
                // Exibir mensagem de fracasso
                JOptionPane.showMessageDialog(null, "Não foi possível realizar o cadastro... Email ou telefone já existem!", "ERRO", JOptionPane.ERROR_MESSAGE);
                
                // Apagar os campos email e telefone
                txtEmail1.setText("");
                ftxtTelefone1.setText("");
            } else {
                                
                // Chamada do método de Inserção de Usuários para inserir novos dados ao Banco de Dados
                cfdao.inserirUsuarios(new Usuario(
                    0,                              // O id_usuario não será passado por porâmetro, pois é SERIAL
                    txtNome1.getText().trim(), 
                    txtEmail1.getText().trim(), 
                    ftxtTelefone1.getText().trim(), 
                    senhaDigitada                   // ou simplesmente: String.valueOf(txtSenha1.getPassword()).trim()
                ));
                
                // Atualizar a tabela de Consultar/Editar Usuários sempre que novos usuários forem cadastrados
                // Carregar os dados para o TableModel
                tabUsuarios3.setModel(cdtmu3);
                // Atualizar a tabela
                carregarDados();

                // Exibir mensagem de sucesso
                JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);

                // Limpar todos os campos após a operação
                txtNome1.setText("");
                txtEmail1.setText("");
                ftxtTelefone1.setText("");
                txtSenha1.setText("");
                
                // Redirecionar o usuário para a tela de Acesso ao Sistema (Login)
                tbpInterfaces.setEnabledAt(1, true);    // Ativar acesso
                tbpInterfaces.setSelectedIndex(1);      // Redirecionar
                tbpInterfaces.setEnabledAt(0, false);   // Bloquear tela de Cadastro de Usuários
            }
        }
    }//GEN-LAST:event_btnCadastrar1ActionPerformed

    // Botão responsável por redirecionar o usuário direto para a interface de Login
    private void btnLogin1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogin1ActionPerformed
        
        // Redirecionar o usuário direto para a tela de Acesso ao Sistema (Login)
        tbpInterfaces.setEnabledAt(1, true);    // Ativar acesso
        tbpInterfaces.setSelectedIndex(1);      // Redirecionar
        // Bloquear a tela de Cadastro de Usuários
        tbpInterfaces.setEnabledAt(0, false);   // Bloquear tela de Cadastro de Usuários
    }//GEN-LAST:event_btnLogin1ActionPerformed

    
    // ---------- Métodos da Interface 2: Acessar Sistema ----------
    
    // Botão responsável por redirecionar o usuário direto para a interface de Cadastro de Usuários
    private void btnCancelar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar2ActionPerformed
        
        // Redirecionar o usuário de volta para a tela de Cadastro de Usuários
        tbpInterfaces.setEnabledAt(0, true);    // Ativar acesso
        tbpInterfaces.setSelectedIndex(0);      // Redirecionar
        // Bloquear novamente a tela de Acesso ao Sistema
        tbpInterfaces.setEnabledAt(1, false);   // Bloquear tela de Acesso ao Sistema
    }//GEN-LAST:event_btnCancelar2ActionPerformed

    // Botão responsável por permitir que o usuário tenha acesso ao CoffeeDaily System
    private void btnEntrar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrar2ActionPerformed
        
        // Variável que receberá o email digitado
        String emailDigitado = txtEmail2.getText().trim();
        // Tratamento do campo SENHA atribuído a uma variável (Conversão correta para String)
        String senhaDigitada = String.valueOf(txtSenha2.getPassword()).trim();
        
        // Variável para receber o retorno do método que acessa o sistema (acessarSistema())
        boolean result = cfdao.acessarSistema(new Usuario(
                0,              // Não importa
                "",             // Não importa
                emailDigitado,  // ou simplesmente: txtEmail2.getText().trim(),
                "",             // Não importa
                senhaDigitada   // ou simplesmente: String.valueOf(txtSenha2.getPassword()).trim()
        ));
        
        if (result == true) {
            // Exibir mensagem de sucesso e liberar o acesso ao sistema
            JOptionPane.showMessageDialog(null, "Seja bem-vindo(a) ao CoffeeDaily System!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
            
            // Apagar os campos antes de mudar de tela
            txtEmail2.setText("");
            txtSenha2.setText("");
            
            // Liberando as telas do sistema
            tbpInterfaces.setEnabledAt(2, true);    // Liberar Consultar/Editar Usuários    
            
            // ---------- Para carregar os dados do Banco de Dados para a tabela assim que a aba for liberada ----------
            // Carregar os dados para o TableModel de tabUsuarios3
            tabUsuarios3.setModel(cdtmu3);
            // Para chamar o método que cria uma List<Usuario> para receber o resultado
            // da operação SQL que foi retornado dentro de outra lista do método consultarUsuarios() em CoffeeDailyDAO
            carregarDados();    // Método implementado após o Construtor CoffeeDailyViewForm
            // ---------------------------------------------------------------------------------------------------------
            
            tbpInterfaces.setSelectedIndex(2);      // Redirecionar para Consultar/Editar Usuários            
            tbpInterfaces.setEnabledAt(1, false);   // Bloquear a tela Acessar Sistema
            
            tbpInterfaces.setEnabledAt(3, true);    // Liberar Cadastrar Produtos
            tbpInterfaces.setEnabledAt(4, true);    // Liberar Consultar/Editar Produtos
            
            // ---------- Para carregar os dados do Banco de Dados para a tabela assim que a aba for liberada ----------
            // Carregar os dados para o TableModel de tabProdutos5
            tabProdutos5.setModel(cdtmp5);
            // Para chamar o método que cria uma List<Produto> para receber o resultado
            // da operação SQL que foi retornado dentro de outra lista do método consultarProdutos() em CoffeeDailyDAO
            carregarDados();    // Método implementado após o Construtor CoffeeDailyViewForm
            // ---------------------------------------------------------------------------------------------------------
            
            tbpInterfaces.setEnabledAt(5, true);    // Liberar Carrinho
            
            // ---------- Para carregar os dados do Banco de Dados para a tabela assim que a aba for liberada ----------
            // Carregar os dados para o TableModel de tabProdutos6
            tabProdutos6.setModel(cdtmp6);
            // Para chamar o método que cria uma List<Produto> para receber o resultado
            // da operação SQL que foi retornado dentro de outra lista do método consultarProdutos() em CoffeeDailyDAO
            carregarDados();    // Método implementado após o Construtor CoffeeDailyViewForm
            // ---------------------------------------------------------------------------------------------------------
            
            tbpInterfaces.setEnabledAt(7, true);    // Liberar Consultar Vendas
            
        } else {
            // Exibir mensagem de fracasso
            JOptionPane.showMessageDialog(null, "Não foi possível acessar o sistema... Email ou senha incorretos!", "ERRO", JOptionPane.ERROR_MESSAGE);
            // Apagar os campos
            // txtEmail2.setText("");
            txtSenha2.setText("");
            // Levar o cursor para o primeiro campo
            txtEmail2.requestFocus();
        }
    }//GEN-LAST:event_btnEntrar2ActionPerformed
    
    
    // ---------- Métodos da Interface 4: Cadastrar Produtos ----------
    
    // Botão responsável por cadastrar novos produtos no Banco de Dados
    private void btnCadastrar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrar4ActionPerformed
        
        // Verificar se todos os campos foram informados corretamente
        if (txtNomeProduto4.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo 'Nome do Produto' deve ser informado!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else if (txtCategoria4.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo 'Categoria' deve ser informado!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else if (txtDescricao4.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo 'Descrição' deve ser informado!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else if (txtPreco4.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo 'Preço' deve ser informado!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else if (txtQuantidade4.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo 'Quantidade' deve ser informado!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else if (ftxtDataDeFabricacao4.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo 'Data de Fabricação' deve ser informado!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else if (ftxtDataDeValidade4.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo 'Data de Validade' deve ser informado!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else if (txtMarca4.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo 'Marca' deve ser informado!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else {
            
            // Primeiro, converter os dados de txtDataDeFabricacao4 e txtDataDeValidade4 para o tipo Date
            // Tradutor para o tipo Date
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
            // Para que o tradutor só aceite datas válidas, e não qualquer coisa
            sdf.setLenient(false);
            
            // Declarando e inicializando variáveis para receber os dados convertidos
            java.util.Date dtFabricacao = null;
            java.util.Date dtValidade = null;
            
            try {
                // Verificar se os dados informados estão no formato correto de data
                String txtFabricacao = ftxtDataDeFabricacao4.getText().trim();
                String txtValidade = ftxtDataDeValidade4.getText().trim();
                
                // Converter para o tipo Date caso esteja tudo nos conformes
                if (!txtFabricacao.equals("//") && !txtFabricacao.isEmpty())
                    dtFabricacao = sdf.parse(txtFabricacao);
                
                if (!txtValidade.equals("//") && !txtValidade.isEmpty())
                    dtValidade = sdf.parse(txtValidade);
                
            }  catch (java.text.ParseException e) {
                JOptionPane.showMessageDialog(this, "Formato de data inválido!", "ERRO", JOptionPane.ERROR_MESSAGE);
                
                // Limpar os campos de data errados
                ftxtDataDeFabricacao4.setText("");
                ftxtDataDeValidade4.setText("");
                
                // Retornar o erro e não permitir que o botão salve dados errados
                return;
            }
            
            // Carregar os dados inseridos na interface para o método de Cadastro de Produtos
            // Variável responsável por receber o retorno do método
            boolean result = cfdao.inserirProdutos(new Produto(
                                0,  // id_produto não importa
                                txtNomeProduto4.getText().trim(),
                                txtCategoria4.getText().trim(),
                                txtDescricao4.getText().trim(),
                                Double.parseDouble(txtPreco4.getText().trim()),
                                Integer.parseInt(txtQuantidade4.getText().trim()),
                                dtFabricacao,   // Variável convertida para Date
                                dtValidade,     // Variável convertida para Date
                                txtMarca4.getText().trim()
                            ));
            
            if (result == true) {
                
                // Atualizar os dados da tabela tabProdutos5
                carregarDados();
                // Exibir mensagem de sucesso
                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                
                // Limpar todos os campos após a operação
                txtNomeProduto4.setText("");
                txtCategoria4.setText("");
                txtDescricao4.setText("");
                txtPreco4.setText("");
                txtQuantidade4.setText("");
                ftxtDataDeFabricacao4.setText("");
                ftxtDataDeValidade4.setText("");
                txtMarca4.setText("");
                // Levar o cursor ao primeiro campo
                txtNomeProduto4.requestFocus();
                
            } else {
                // Exibir mensagem de fracasso
                JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o produto...", "ERRO", JOptionPane.ERROR_MESSAGE);
            }            
        }
    }//GEN-LAST:event_btnCadastrar4ActionPerformed

    // Botão responsável por limpar todos os campos voluntariamente
    private void btnLimpar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpar4ActionPerformed
        
        // Para limpar todos os campos voluntariamente
        txtNomeProduto4.setText("");
        txtCategoria4.setText("");
        txtDescricao4.setText("");
        txtPreco4.setText("");
        txtQuantidade4.setText("");
        ftxtDataDeFabricacao4.setText("");
        ftxtDataDeValidade4.setText("");
        txtMarca4.setText("");
        // Levar o cursor ao primeiro campo
        txtNomeProduto4.requestFocus();
    }//GEN-LAST:event_btnLimpar4ActionPerformed

    
    // ---------- Métodos da Interface 3: Consultar/Editar Usuários ----------
    
    // Variáveis de controle para a Interface 3: Consultar/Editar Usuários
    private int idUsuarioSelecionado = -1;
    private String emailAnterior = "";
    private String telefoneAnterior = "";
    
    // Método responsável por capturar os dados das tuplas da tabela tabUsuarios3
    private void tabUsuarios3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabUsuarios3MouseClicked
        
        // Retornar os dados da linha selecionada na tabela
        int linhaSelecionada = tabUsuarios3.getSelectedRow();
        
        // Verificar se alguma linha foi selecionada para começar a coleta dos dados
        if (linhaSelecionada != -1) {
            // Variáveis que receberão os dados da linha selecionada
            idUsuarioSelecionado = Integer.parseInt(tabUsuarios3.getValueAt(linhaSelecionada, 0).toString());   // Para o WHERE do UPDATE SQL em CoffeeDailyDAO
            
            String nome = tabUsuarios3.getValueAt(linhaSelecionada, 1).toString();
            String email = tabUsuarios3.getValueAt(linhaSelecionada, 2).toString();
            String telefone = tabUsuarios3.getValueAt(linhaSelecionada, 3).toString();
            String senha = tabUsuarios3.getValueAt(linhaSelecionada, 4).toString();
                        
            // Atribuir aos campos
            txtNome3.setText(nome);
            txtEmail3.setText(email);
            ftxtTelefone3.setText(telefone);
            txtSenha3.setText(senha);
            
            // Armazenar o email e o telefone que foram passado para os JTextFields após o clique na linha
            emailAnterior = email.trim();
            telefoneAnterior = telefone.trim();            
        }
    }//GEN-LAST:event_tabUsuarios3MouseClicked

    // Método responsável por confirmar as alterações dos dados
    private void btnEditarUsuario3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarUsuario3ActionPerformed
        
        // Verificar se algum usuário da tabela foi selecionado
        if (idUsuarioSelecionado == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione um usuário na tabela primeiro!", "AVISO", JOptionPane.WARNING_MESSAGE);
            return; // Impede que o programa continue
        }
                
        // Verificar se os campos estão vazios
        if (txtNome3.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo 'Nome' deve ser informado!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else if (txtEmail3.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo 'Email' deve ser informado!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else if (ftxtTelefone3.getText().replaceAll("[^0-9]", "").isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo 'Telefone' deve ser informado!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else if (txtSenha3.getPassword().length == 0) {
            JOptionPane.showMessageDialog(null, "O campo 'Senha' deve ser informado!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else {
            
            // Variáveis que receberão os campos JTextFields
            String nomeDigitado = txtNome3.getText().trim();
            String emailDigitado = txtEmail3.getText().trim();
            String telefoneDigitado = ftxtTelefone3.getText().trim();
            // Tratamento do campo SENHA atribuído a uma variável (Conversão correta para String)
            String senhaDigitada = String.valueOf(txtSenha3.getPassword()).trim();
                        
            // Variável que receberá o retorno do método de Edição dos Usuários (método que atualizada os dados do Banco de Dados)
            boolean result = cfdao.atualizarUsuarios(new Usuario(
                idUsuarioSelecionado,               // id_usuario será passado para que o UPDATE possa ser feito
                nomeDigitado,                       // ou simplesmente: txtNome3.getText().trim()
                emailDigitado,                      // ou simplesmente: txtEmail3.getText().trim()
                telefoneDigitado,                   // ou simplesmente: ftxtTelefone3.getText().trim()
                senhaDigitada                       // ou simplesmente: String.valueOf(txtSenha1.getPassword()).trim()
            ));
            
            // Se o retorno do método for false, significa que o mesmo email e/ou telefone foi encontrado no Banco de Dados
            if (result == false) {
                // Exibir mensagem de fracasso
                JOptionPane.showMessageDialog(null, "Não foi possível editar os dados do usuário... Email e/ou telefone já existem!", "ERRO", JOptionPane.ERROR_MESSAGE);
                
                // Apagar os campos email e telefone
                txtEmail3.setText("");
                ftxtTelefone3.setText("");
                // Levar o cursor ao email
                txtEmail3.requestFocus();
                
            } else {
                // Atualizar a tabela de Consultar/Editar Usuários sempre que os usuários forem editados
                tabUsuarios3.setModel(cdtmu3);    // Carregar os dados para o TableModel
                // Atualizar a tabela
                carregarDados();

                // Exibir mensagem de sucesso
                JOptionPane.showMessageDialog(null, "Atualização bem sucedida!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);

                // Limpar todos os campos após a operação
                txtNome3.setText("");
                txtEmail3.setText("");
                ftxtTelefone3.setText("");
                txtSenha3.setText("");
                
                // Resetar as variáveis de controle para a próxima edição
                idUsuarioSelecionado = -1;
                emailAnterior = "";
                telefoneAnterior = "";
            }
        }
    }//GEN-LAST:event_btnEditarUsuario3ActionPerformed

    
    // ---------- Métodos da Interface 5: Consultar/Editar Produtos ----------
    
    // Variáveis de controle para o método responsável por carregar os dados da linha selecionada para os campos
    int idProdutoSelecionado = -1;    
    
    // Método responsável por carregar os dados da linha selecionada da tabela tabProdutos5 para os campos
    private void tabProdutos5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabProdutos5MouseClicked
        
        // Variável que vai armazenar a linha selecionada
        int linhaSelecionada = tabProdutos5.getSelectedRow();
        
        // Verificar se alguma linha foi selecionada para iniciar a coleta de dados
        if (linhaSelecionada != -1) {
            // Tradutor para formatar as datas
            SimpleDateFormat formatoBD = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatoNovo = new SimpleDateFormat("dd/MM/yyyy");
                    
            // ----- Variáveis que receberão os dados de cada campo da linha -----
            idProdutoSelecionado = Integer.parseInt(tabProdutos5.getValueAt(linhaSelecionada, 0).toString());   // Para o WHERE do SELECT de consultarProdutos()
            
            String nm_produto = tabProdutos5.getValueAt(linhaSelecionada, 1).toString();
            String categoria = tabProdutos5.getValueAt(linhaSelecionada, 2).toString();
            String descricao = tabProdutos5.getValueAt(linhaSelecionada, 3).toString();
            String preco = tabProdutos5.getValueAt(linhaSelecionada, 4).toString().replace(",", ".");
            String qtd_estoque = tabProdutos5.getValueAt(linhaSelecionada, 5).toString();
            String marca = tabProdutos5.getValueAt(linhaSelecionada, 8).toString();
            
            // Variáveis que receberão as datas formatadas
            String dt_fabricacaoFormatada = "";
            String dt_validadeFormatada = "";
            
            try {
                // Captura o objeto puro da tabela (pode vir como java.sql.Date)
                Object objFabricacao = tabProdutos5.getValueAt(linhaSelecionada, 6);
                Object objValidade   = tabProdutos5.getValueAt(linhaSelecionada, 7);

                // Tratamento para a Data de Fabricação
                if (objFabricacao instanceof java.util.Date) {
                    dt_fabricacaoFormatada = formatoNovo.format((java.util.Date) objFabricacao);
                } else {
                    java.util.Date data = formatoBD.parse(objFabricacao.toString());
                    dt_fabricacaoFormatada = formatoNovo.format(data);
                }

                // Tratamento para a Data de Validade
                if (objValidade instanceof java.util.Date) {
                    dt_validadeFormatada = formatoNovo.format((java.util.Date) objValidade);
                } else {
                    java.util.Date data = formatoBD.parse(objValidade.toString());
                    dt_validadeFormatada = formatoNovo.format(data);
                }
            // -----------------------------------------------------------------

            } catch (Exception e) {
                // Caso dê algum erro na conversão, limpar as variáveis para não bugar
                dt_fabricacaoFormatada = "";
                dt_validadeFormatada = "";
                System.err.println("Erro ao formatar datas no clique da tabela: ");
                e.printStackTrace();
            }  
            
            // Atribuindo aos campos JTextFields
            txtNomeProduto5.setText(nm_produto);
            txtCategoria5.setText(categoria);
            txtDescricao5.setText(descricao);
            txtPreco5.setText(preco);
            txtQuantidade5.setText(qtd_estoque);
            ftxtDataDeFabricacao5.setText(dt_fabricacaoFormatada);
            ftxtDataDeValidade5.setText(dt_validadeFormatada);
            txtMarca5.setText(marca);
        }
    }//GEN-LAST:event_tabProdutos5MouseClicked

    // Método responsável por confirmar as alterações feitas nos produtos
    private void btnEditarProduto5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProduto5ActionPerformed
        
        // Verificar se algum produto da tabela foi selecionado
        if (idProdutoSelecionado == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione primeiro um produto na tabela!", "AVISO", JOptionPane.WARNING_MESSAGE);
            return; // Impede que o programa continue
        }
        
        // Verificar se os campos estão vazios
        if (txtNomeProduto5.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo 'Nome do Produto' deve ser informado!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else if (txtCategoria5.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo 'Categoria' deve ser informado!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else if (txtDescricao5.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo 'Descrição' deve ser informado!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else if (txtPreco5.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo 'Preço' deve ser informado!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else if (txtQuantidade5.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo 'Quantidade' deve ser informado!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else if (ftxtDataDeFabricacao5.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo 'Data de Fabricação' deve ser informado!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else if (ftxtDataDeValidade5.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo 'Data de Validade' deve ser informado!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else if (txtMarca5.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo 'Marca' deve ser informado!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else {            
            // Primeiro, converter os dados de txtDataDeFabricacao5 e txtDataDeValidade5 para o tipo Date
            // Tradutor para o tipo Date
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
            // Para que o tradutor só aceite datas válidas, e não qualquer coisa
            sdf.setLenient(false);
            
            // Declarando e inicializando variáveis para receber os dados convertidos
            java.util.Date dtFabricacaoDigitada = null;
            java.util.Date dtValidadeDigitada = null;
            
            try {
                // Verificar se os dados informados estão no formato correto de data
                String txtFabricacao = ftxtDataDeFabricacao5.getText().trim();
                String txtValidade = ftxtDataDeValidade5.getText().trim();
                
                // Converter para o tipo Date caso esteja tudo nos conformes
                if (!txtFabricacao.equals("//") && !txtFabricacao.isEmpty())
                    dtFabricacaoDigitada = sdf.parse(txtFabricacao);
                
                if (!txtValidade.equals("//") && !txtValidade.isEmpty())
                    dtValidadeDigitada = sdf.parse(txtValidade);
                
            }  catch (java.text.ParseException e) {
                JOptionPane.showMessageDialog(this, "Formato de data inválido!", "ERRO", JOptionPane.ERROR_MESSAGE);
                
                // Limpar os campos de data errados
                ftxtDataDeFabricacao5.setText("");
                ftxtDataDeValidade5.setText("");
                
                // Retornar o erro e não permitir que o botão salve dados errados
                return;
            }
            
            // ----- Variáveis que receberão os campos JTextFields -----
            String nomeProdutoDigitado = txtNomeProduto5.getText().trim();
            String categoriaDigitada = txtCategoria5.getText().trim();
            String descricaoDigitada = txtDescricao5.getText().trim();
            
            // Substituir a vírgula do retorno por ponto para impedir erro de formatação ao Editar um Produto
            String precoTratado = txtPreco5.getText().trim().replace(",", ".");
            double precoDigitado = Double.parseDouble(precoTratado);
            
            int quantidadeDigitada = Integer.parseInt(txtQuantidade5.getText().trim());
            // dtFabricacaoDigitada -> Formato correto
            // dtValidadeDigitada   -> Formato correto
            String marcaDigitada = txtMarca5.getText().trim();
            // -----------------------------------------------------------------
            
            // Variável que receberá o retorno do método de Edição dos Produtos (método que atualiza os dados do Banco de Dados)
            boolean result = cfdao.atualizarProdutos(new Produto(
                                                        idProdutoSelecionado,   // id_produto será passado para que o UPDATE possa ser feito
                                                        nomeProdutoDigitado,
                                                        categoriaDigitada,
                                                        descricaoDigitada,
                                                        precoDigitado,
                                                        quantidadeDigitada,
                                                        dtFabricacaoDigitada,
                                                        dtValidadeDigitada,
                                                        marcaDigitada
                                                    ));
            
            // Se o retorno do método for false, significa que o mesmo email e/ou telefone foi encontrado no Banco de Dados
            if (result == false) {
                // Exibir mensagem de fracasso
                JOptionPane.showMessageDialog(null, "Não foi possível editar os dados do produto...", "ERRO", JOptionPane.ERROR_MESSAGE);
                                
            } else {
                // Atualizar a tabela de Consultar/Editar Produtos sempre que os produtos forem editados
                tabProdutos5.setModel(cdtmp5);    // Carregar os dados para o TableModel
                // Atualizar a tabela
                carregarDados();

                // Exibir mensagem de sucesso
                JOptionPane.showMessageDialog(null, "Atualização bem sucedida!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);

                // Limpar todos os campos após a operação
                txtNomeProduto5.setText("");
                txtCategoria5.setText("");
                txtDescricao5.setText("");
                txtPreco5.setText("");
                txtQuantidade5.setText("");
                ftxtDataDeFabricacao5.setText("");
                ftxtDataDeValidade5.setText("");
                txtMarca5.setText("");
                
                // Resetar as variáveis de controle para a próxima edição
                idProdutoSelecionado = -1;
            }
        }
    }//GEN-LAST:event_btnEditarProduto5ActionPerformed

    
    // ---------- Métodos da Interface 6: Carrinho ----------
    
    // Variável de controle para obter valor total do carrinho
    private double precoUnitarioSelecionado = 0;
    
    // Método responsável por retornar os dados selecionados na tabela para os campos para permir usar o botão Adicionar
    private void tabProdutos6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabProdutos6MouseClicked
        
        int linhaSelecionada = tabProdutos6.getSelectedRow();
    
        if (linhaSelecionada != -1) {
            idProdutoSelecionado = Integer.parseInt(tabProdutos6.getValueAt(linhaSelecionada, 0).toString());

            String nm_produto = tabProdutos6.getValueAt(linhaSelecionada, 1).toString();
            String preco = tabProdutos6.getValueAt(linhaSelecionada, 4).toString().replace(",", ".");
            String qtd_estoque = tabProdutos6.getValueAt(linhaSelecionada, 5).toString();
            String marca = tabProdutos6.getValueAt(linhaSelecionada, 8).toString();

            // Guarda o preço unitário para os cálculos do botão Adicionar
            precoUnitarioSelecionado = Double.parseDouble(preco);

            // Atribuindo aos campos
            txtNomeProduto6.setText(nm_produto);
            txtMarca6.setText(marca);
            txtQuantidade6.setText(qtd_estoque);
        }
    }//GEN-LAST:event_tabProdutos6MouseClicked

    // Método responsável por retornar os dados selecionados na tabela para os campos para permitir usar o botão Remover
    private void tabCarrinho6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabCarrinho6MouseClicked
        
        // Variável que vai armazenar a linha selecionada
        int linhaSelecionada = tabCarrinho6.getSelectedRow();
        
        // Verificar se alguma linha foi selecionada para iniciar a coleta de dados
        if (linhaSelecionada != -1) {
                    
            // ----- Variáveis que receberão os dados de cada campo da linha -----
            idProdutoSelecionado = Integer.parseInt(tabCarrinho6.getValueAt(linhaSelecionada, 0).toString());   // Para o WHERE do SELECT de consultarProdutos()
            
            String nm_produto = tabCarrinho6.getValueAt(linhaSelecionada, 1).toString();
            String marca = tabCarrinho6.getValueAt(linhaSelecionada, 2).toString();
            String qtd_estoque = tabCarrinho6.getValueAt(linhaSelecionada, 3).toString();
            
            // Atribuindo aos campos JTextFields
            txtNomeProduto6.setText(nm_produto);
            txtQuantidade6.setText(qtd_estoque);
            txtMarca6.setText(marca);
        }
    }//GEN-LAST:event_tabCarrinho6MouseClicked

    // Método para somar tudo que está na lista do carrinho e jogar no txtSubtotal6
    private void atualizarSubtotalCarrinho() {
        double total = 0;
        for (Produto p : listaCarrinho) {
            total += p.getPreco(); 
        }
        // Formata com duas casas decimais
        txtSubtotal6.setText(String.format("%.2f", total));
    }

    // Método para limpar os campos de texto do carrinho
    private void limparCamposInterface6() {
        txtNomeProduto6.setText("");
        txtMarca6.setText("");
        txtQuantidade6.setText("");
        idProdutoSelecionado = -1;
        precoUnitarioSelecionado = 0;
    }
    
    // Lista do Carrinho
    List<Produto> listaCarrinho = new ArrayList<>();
    
    // Método responsável por Adicionar ao carrinho a linha selecionada em tabProdutos6
    private void btnAdicionar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionar6ActionPerformed
        
        // Verificar se algum produto da tabela tabProdutos6 foi selecionado
        if (idProdutoSelecionado == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione primeiro um produto na tabela!", "AVISO", JOptionPane.WARNING_MESSAGE);
            return; // Impede que o programa continue
        }
        
        // Verificar se os campos estão vazios
        if (txtNomeProduto6.getText().isEmpty() || txtMarca6.getText().isEmpty() || txtQuantidade6.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos os campos do produto devem ser informados!", "ERRO", JOptionPane.ERROR_MESSAGE);
            return; // Impede que o programa continue
            
        } else {                        
            // ----- Variáveis que receberão os campos JTextFields -----
            String nomeProdutoSelecionado = txtNomeProduto6.getText().trim();
            String marcaSelecionada = txtMarca6.getText().trim();
            int quantidadeSelecionada = Integer.parseInt(txtQuantidade6.getText().trim());

            // Preço total deste item específico (Preço Unitário x Quantidade)
            double precoTotalItem = precoUnitarioSelecionado * quantidadeSelecionada;
            // -----------------------------------------------------------------
            
            // Criando o objeto com os dados da tela para adicionar na tabela do carrinho
            Produto produtoCarrinho = new Produto(
                idProdutoSelecionado,
                nomeProdutoSelecionado,
                "", "", // Categoria e Descrição vazias (não aparecem no carrinho)
                precoTotalItem,
                quantidadeSelecionada,
                null, null, // Datas vazias
                marcaSelecionada
            );

            // Adiciona na sua lista local da tela
            listaCarrinho.add(produtoCarrinho);

            // Atualiza o TableModel do Carrinho
            tabCarrinho6.setModel(cdtmc6);
            cdtmc6.atualizarTabCarrinho6(listaCarrinho);

            // Atualiza o JTextField do Subtotal acumulado
            atualizarSubtotalCarrinho();

            // Limpa os campos para a próxima inserção
            limparCamposInterface6();
            
            JOptionPane.showMessageDialog(null, "Produto adicionado ao carrinho!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnAdicionar6ActionPerformed

    // Método responsável por Remover do carrinho a linha selecionada em tabCarrinho6
    private void btnRemover6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemover6ActionPerformed
        
        int linhaSelecionada = tabCarrinho6.getSelectedRow();
    
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione um produto dentro do Carrinho para remover!", "AVISO", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Remove da lista local usando a posição da linha
        listaCarrinho.remove(linhaSelecionada);

        // Atualiza a tabela do carrinho
        cdtmc6.atualizarTabCarrinho6(listaCarrinho);

        // Recalcula o subtotal acumulado do carrinho
        atualizarSubtotalCarrinho();

        // Limpa os campos
        limparCamposInterface6();

        JOptionPane.showMessageDialog(null, "Produto removido do carrinho!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnRemover6ActionPerformed

    private void btnComprar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprar6ActionPerformed
        // Verificar se o carrinho não está vazio para liberar a Interface 7: Vender
            if (!listaCarrinho.isEmpty()) {
                // Carregar carrinho para Interface 7: Vender
                cdtmc7.atualizarTabCarrinho7(listaCarrinho);
                tabCarrinho7.setModel(cdtmc7);
                
                // Repassar o valor do subtotal para o total a pagar da Interface 7
                txtTotalPagar7.setText(txtSubtotal6.getText());
                
                tbpInterfaces.setEnabledAt(6, true);    // Liberar Vender
                tbpInterfaces.setSelectedIndex(6);      // Redirecionar para Vender
                tbpInterfaces.setEnabledAt(5, false);   // Bloquear Carrinho
            } else {
                JOptionPane.showMessageDialog(null, "Você precisa ter no mínimo 1 produto no carrinho para comprar!", "AVISO", JOptionPane.WARNING_MESSAGE);
            }
    }//GEN-LAST:event_btnComprar6ActionPerformed
     
    
    // ---------- Métodos da Interface 7: Vender ----------

    private void btnCancelar7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar7ActionPerformed
        // Redirecionar o usuário de volta para a Interface 6: Carrinho
        tbpInterfaces.setEnabledAt(5, true);    // Liberar Carrinho
        tbpInterfaces.setSelectedIndex(5);      // Redirecionar para Carrinho
        tbpInterfaces.setEnabledAt(6, false);   // Bloquear Vender
    }//GEN-LAST:event_btnCancelar7ActionPerformed

    private void btnVender7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVender7ActionPerformed
        // Variáveis responsáveis por receber os campos validados
        String email = txtEmail7.getText().trim();
        String senha = new String(txtSenha7.getPassword()).trim();

        // Verificar se todos os campos foram informados
        if (email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, informe o E-mail e a Senha da sua conta!", "AVISO", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar se uma forma de pagamento válida foi selecionada
        if (cbxFormaPagamento7.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione uma Forma de Pagamento!", "AVISO", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String formaPagamento = cbxFormaPagamento7.getSelectedItem().toString();

        // Definir a quantidade de parcelas baseada no RadioButton
        int parcelas = 1; 
        if (rdbSim7.isSelected()) {
            // Pega o número da parcela escolhida (ex: "2x" vira o número 2)
            String parcelaSelecionada = cbxParcelas7.getSelectedItem().toString();
            parcelas = Integer.parseInt(parcelaSelecionada.replace("x", ""));
        }

        // Tratar o valor total a pagar
        double totalPagar = 0.0;
        try {
            totalPagar = Double.parseDouble(txtTotalPagar7.getText().trim().replace(",", "."));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro ao processar o valor total.", "ERRO", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar se o Usuário e Senha existem e obter o ID do banco
        int idUsuario = cfdao.obterIdUsuario(email, senha);
        if (idUsuario == -1) {
            JOptionPane.showMessageDialog(null, "E-mail ou Senha incorretos! Confirme seus dados para finalizar a venda.", "AUTENTICAÇÃO FALHOU", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Tentar salvar o pagamento no Banco de Dados
        boolean sucessoVenda = cfdao.realizarVendaETratarPagamento(idUsuario, formaPagamento, parcelas, totalPagar);

        if (sucessoVenda) {
            JOptionPane.showMessageDialog(null, "Venda realizada com sucesso!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);

            // Atualizar a tabela da Interface 8: Consultar Vendas instantaneamente com os novos dados criados
            java.util.List<Pagamento> listaP = new java.util.ArrayList<>();
            java.util.List<Usuario> listaU = new java.util.ArrayList<>();
            cfdao.consultarVendasUnificadas(listaP, listaU);
            
            tabVendas8.setModel(cdtmv8);
            cdtmv8.atualizarTabVendas8(listaP, listaU);

            // Limpar campos da interface e o carrinho para a próxima compra
            listaCarrinho.clear();
            txtEmail7.setText("");
            txtSenha7.setText("");
            cbxFormaPagamento7.setSelectedIndex(0);
            rdbNao7.setSelected(true);
            cbxParcelas7.setEnabled(false);
            
            // Atualizar tudo novamente
            tabVendas8.setModel(cdtmv8);
            cdtmv8.atualizarTabVendas8(listaP, listaU);
            
            // Redirecionar para a Interface 8: Consultar Vendas
            // tbpInterfaces.setEnabledAt(7, true);    // Liberar Consultar Vendas
            tbpInterfaces.setSelectedIndex(7);      // Redirecionar para Consultar Vendas
            tbpInterfaces.setEnabledAt(6, false);   // Bloquear Vender
            tbpInterfaces.setEnabledAt(5, true);    // Liberar Carrinho

        } else {
            JOptionPane.showMessageDialog(null, "Erro crítico ao processar o registro da venda no Banco de Dados.", "ERRO INTERNO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnVender7ActionPerformed
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        // java.awt.EventQueue.invokeLater(() -> new CoffeeDailyViewForm().setVisible(true));
        
        try {
            // Para ativar o visual moderno do FlatLaf antes de abrir a janela
            com.formdev.flatlaf.FlatLightLaf.setup();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(CoffeeDailyViewForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        // Para manter o código padrão do NetBeans que abre a tela logo abaixo:
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CoffeeDailyViewForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar6;
    private javax.swing.JButton btnCadastrar1;
    private javax.swing.JButton btnCadastrar4;
    private javax.swing.JButton btnCancelar2;
    private javax.swing.JButton btnCancelar7;
    private javax.swing.JButton btnComprar6;
    private javax.swing.JButton btnEditarProduto5;
    private javax.swing.JButton btnEditarUsuario3;
    private javax.swing.JButton btnEntrar2;
    private javax.swing.JButton btnGitHubIcon;
    private javax.swing.JButton btnLimpar4;
    private javax.swing.JButton btnLinkedinIcon;
    private javax.swing.JButton btnLogin1;
    private javax.swing.JButton btnRemover6;
    private javax.swing.JButton btnSobre;
    private javax.swing.JButton btnVender7;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.JComboBox<String> cbxFormaPagamento7;
    private javax.swing.JComboBox<String> cbxParcelas7;
    private javax.swing.JFormattedTextField ftxtDataDeFabricacao4;
    private javax.swing.JFormattedTextField ftxtDataDeFabricacao5;
    private javax.swing.JFormattedTextField ftxtDataDeValidade4;
    private javax.swing.JFormattedTextField ftxtDataDeValidade5;
    private javax.swing.JFormattedTextField ftxtTelefone1;
    private javax.swing.JFormattedTextField ftxtTelefone3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel lblCategoria4;
    private javax.swing.JLabel lblCategoria5;
    private javax.swing.JLabel lblCliqueNoUsuario3;
    private javax.swing.JLabel lblCliqueNoUsuario4;
    private javax.swing.JLabel lblCoffee;
    private javax.swing.JLabel lblCoffeeIcon;
    private javax.swing.JLabel lblDaily;
    private javax.swing.JLabel lblDataDeFabricacao4;
    private javax.swing.JLabel lblDataDeFabricacao5;
    private javax.swing.JLabel lblDataDeValidade4;
    private javax.swing.JLabel lblDataDeValidade5;
    private javax.swing.JLabel lblDescricao4;
    private javax.swing.JLabel lblDescricao5;
    private javax.swing.JLabel lblEmail1;
    private javax.swing.JLabel lblEmail2;
    private javax.swing.JLabel lblEmail3;
    private javax.swing.JLabel lblEmail7;
    private javax.swing.JLabel lblFormaPagamento7;
    private javax.swing.JLabel lblMarca4;
    private javax.swing.JLabel lblMarca5;
    private javax.swing.JLabel lblMarca6;
    private javax.swing.JLabel lblMeuCarrinho7;
    private javax.swing.JLabel lblNome1;
    private javax.swing.JLabel lblNome3;
    private javax.swing.JLabel lblNomeProduto4;
    private javax.swing.JLabel lblNomeProduto5;
    private javax.swing.JLabel lblNomeProduto6;
    private javax.swing.JLabel lblParcelas7;
    private javax.swing.JLabel lblPreco4;
    private javax.swing.JLabel lblPreco5;
    private javax.swing.JLabel lblQuantidade4;
    private javax.swing.JLabel lblQuantidade5;
    private javax.swing.JLabel lblQuantidade6;
    private javax.swing.JLabel lblRealizeCadastro1;
    private javax.swing.JLabel lblSejaBemVindo2;
    private javax.swing.JLabel lblSenha1;
    private javax.swing.JLabel lblSenha2;
    private javax.swing.JLabel lblSenha3;
    private javax.swing.JLabel lblSenha7;
    private javax.swing.JLabel lblSubtotal6;
    private javax.swing.JLabel lblTelefone1;
    private javax.swing.JLabel lblTelefone3;
    private javax.swing.JLabel lblTotalPagar7;
    private javax.swing.JPanel pnlAcessarSistema;
    private javax.swing.JPanel pnlCadastarUsuarios;
    private javax.swing.JPanel pnlCadastrarProdutos;
    private javax.swing.JPanel pnlCarrinho;
    private javax.swing.JPanel pnlCoffeeDaily;
    private javax.swing.JPanel pnlConsultarProdutos;
    private javax.swing.JPanel pnlConsultarUsuarios;
    private javax.swing.JPanel pnlConsultarVendas;
    private javax.swing.JPanel pnlVender;
    private javax.swing.JRadioButton rdbNao7;
    private javax.swing.JRadioButton rdbSim7;
    private javax.swing.JTable tabCarrinho6;
    private javax.swing.JTable tabCarrinho7;
    private javax.swing.JTable tabProdutos5;
    private javax.swing.JTable tabProdutos6;
    private javax.swing.JTable tabUsuarios3;
    private javax.swing.JTable tabVendas8;
    private javax.swing.JTabbedPane tbpInterfaces;
    private javax.swing.JTextField txtCategoria4;
    private javax.swing.JTextField txtCategoria5;
    private javax.swing.JTextArea txtDescricao4;
    private javax.swing.JTextArea txtDescricao5;
    private javax.swing.JTextField txtEmail1;
    private javax.swing.JTextField txtEmail2;
    private javax.swing.JTextField txtEmail3;
    private javax.swing.JTextField txtEmail7;
    private javax.swing.JTextField txtMarca4;
    private javax.swing.JTextField txtMarca5;
    private javax.swing.JTextField txtMarca6;
    private javax.swing.JTextField txtNome1;
    private javax.swing.JTextField txtNome3;
    private javax.swing.JTextField txtNomeProduto4;
    private javax.swing.JTextField txtNomeProduto5;
    private javax.swing.JTextField txtNomeProduto6;
    private javax.swing.JTextField txtPreco4;
    private javax.swing.JTextField txtPreco5;
    private javax.swing.JTextField txtQuantidade4;
    private javax.swing.JTextField txtQuantidade5;
    private javax.swing.JTextField txtQuantidade6;
    private javax.swing.JPasswordField txtSenha1;
    private javax.swing.JPasswordField txtSenha2;
    private javax.swing.JPasswordField txtSenha3;
    private javax.swing.JPasswordField txtSenha7;
    private javax.swing.JTextField txtSubtotal6;
    private javax.swing.JTextField txtTotalPagar7;
    // End of variables declaration//GEN-END:variables
}
