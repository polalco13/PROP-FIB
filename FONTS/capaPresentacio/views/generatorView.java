package capaPresentacio.views;
import capaDomini.excepcions.ExcepcioKenken;
import capaPresentacio.CtrlPresentacio;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class generatorView extends JPanel {
    private String nom;
    private int mida;
    private final CardLayout cardLayout0;
    private final CardLayout cardLayout1;

    public generatorView() {
        initComponents();
        automaticTB.setSelected(true);
        
        cardLayout0 = (CardLayout) (inputPanel.getLayout());
        cardLayout1 = (CardLayout) (boardPanel.getLayout());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        operationGroup = new javax.swing.ButtonGroup();
        menuPanel = new javax.swing.JPanel();
        selectModePanel = new javax.swing.JPanel();
        modeLabel = new javax.swing.JLabel();
        automaticTB = new javax.swing.JToggleButton();
        manualTB = new javax.swing.JToggleButton();
        inputPanel = new javax.swing.JPanel();
        autoInputPanel = new javax.swing.JPanel();
        nomLabel0 = new javax.swing.JLabel();
        nomTextField0 = new javax.swing.JTextField();
        midaLabel0 = new javax.swing.JLabel();
        midaComboBox0 = new javax.swing.JComboBox<>();
        generarButton0 = new javax.swing.JButton();
        saveButton0 = new javax.swing.JButton();
        manualInputPanel = new javax.swing.JPanel();
        inputNomPanel = new javax.swing.JPanel();
        nomLabel1 = new javax.swing.JLabel();
        nomTextField1 = new javax.swing.JTextField();
        inputMidaPanel = new javax.swing.JPanel();
        midaLabel1 = new javax.swing.JLabel();
        midaComboBox1 = new javax.swing.JComboBox<>();
        inputOperationPanel = new javax.swing.JPanel();
        operationLabel = new javax.swing.JLabel();
        operationPanel = new javax.swing.JPanel();
        sumaButton = new javax.swing.JRadioButton();
        restaButton = new javax.swing.JRadioButton();
        modButton = new javax.swing.JRadioButton();
        multiplicacioButton = new javax.swing.JRadioButton();
        divisioButton = new javax.swing.JRadioButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        arrelButton = new javax.swing.JRadioButton();
        resultLabel = new javax.swing.JLabel();
        resultTextField = new javax.swing.JTextField();
        emptyPanel = new javax.swing.JPanel();
        inputButtonsPanel1 = new javax.swing.JPanel();
        setButton1 = new javax.swing.JButton();
        validateButton1 = new javax.swing.JButton();
        saveButton1 = new javax.swing.JButton();
        previewPanel = new javax.swing.JPanel();
        previewLabel = new javax.swing.JLabel();
        boardPanel = new javax.swing.JPanel();
        autoBoard = new javax.swing.JPanel();
        kenkenPanel0 = new javax.swing.JPanel();
        manualBoard = new javax.swing.JPanel();
        kenkenPanel1 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(237, 224, 212));
        setLayout(new java.awt.GridBagLayout());

        menuPanel.setBackground(new java.awt.Color(237, 224, 212));
        menuPanel.setLayout(new java.awt.GridBagLayout());

        selectModePanel.setBackground(new java.awt.Color(237, 224, 212));
        selectModePanel.setLayout(new java.awt.GridBagLayout());

        modeLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        modeLabel.setForeground(new java.awt.Color(89, 61, 59));
        modeLabel.setText("Mode");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        selectModePanel.add(modeLabel, gridBagConstraints);

        automaticTB.setBackground(new java.awt.Color(171, 148, 126));
        automaticTB.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        automaticTB.setForeground(new java.awt.Color(237, 224, 212));
        automaticTB.setText("Automàtic");
        automaticTB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        automaticTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                automaticTBActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 40;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 20);
        selectModePanel.add(automaticTB, gridBagConstraints);

        manualTB.setBackground(new java.awt.Color(171, 148, 126));
        manualTB.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        manualTB.setForeground(new java.awt.Color(237, 224, 212));
        manualTB.setText("Manual");
        manualTB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        manualTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manualTBActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 20);
        selectModePanel.add(manualTB, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 20, 0);
        menuPanel.add(selectModePanel, gridBagConstraints);

        inputPanel.setLayout(new java.awt.CardLayout());

        autoInputPanel.setBackground(new java.awt.Color(237, 224, 212));
        autoInputPanel.setLayout(new java.awt.GridBagLayout());

        nomLabel0.setBackground(new java.awt.Color(237, 224, 212));
        nomLabel0.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        nomLabel0.setForeground(new java.awt.Color(89, 61, 59));
        nomLabel0.setText("Nom");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        autoInputPanel.add(nomLabel0, gridBagConstraints);

        nomTextField0.setForeground(new java.awt.Color(89, 61, 59));
        nomTextField0.setBorder(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 250;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        autoInputPanel.add(nomTextField0, gridBagConstraints);

        midaLabel0.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        midaLabel0.setForeground(new java.awt.Color(89, 61, 59));
        midaLabel0.setText("Mida del Kenken");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        autoInputPanel.add(midaLabel0, gridBagConstraints);

        midaComboBox0.setForeground(new java.awt.Color(89, 61, 59));
        midaComboBox0.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3", "4", "5", "6", "7", "8", "9" }));
        midaComboBox0.setBorder(null);
        midaComboBox0.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        midaComboBox0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                midaComboBox0ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 240;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        autoInputPanel.add(midaComboBox0, gridBagConstraints);

        generarButton0.setBackground(new java.awt.Color(171, 148, 126));
        generarButton0.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        generarButton0.setForeground(new java.awt.Color(237, 224, 212));
        generarButton0.setText("Generar");
        generarButton0.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        generarButton0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarButton0ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.ipady = 25;
        gridBagConstraints.insets = new java.awt.Insets(40, 0, 0, 0);
        autoInputPanel.add(generarButton0, gridBagConstraints);

        saveButton0.setBackground(new java.awt.Color(171, 148, 126));
        saveButton0.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        saveButton0.setForeground(new java.awt.Color(237, 224, 212));
        saveButton0.setText("Guardar");
        saveButton0.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        saveButton0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    saveButton0ActionPerformed(evt);
                } catch (ExcepcioKenken e) {
                    throw new RuntimeException(e);
                }
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.ipady = 25;
        gridBagConstraints.insets = new java.awt.Insets(40, 0, 0, 0);
        autoInputPanel.add(saveButton0, gridBagConstraints);

        inputPanel.add(autoInputPanel, "autoCard");

        manualInputPanel.setBackground(new java.awt.Color(237, 224, 212));
        manualInputPanel.setLayout(new java.awt.GridBagLayout());

        inputNomPanel.setBackground(new java.awt.Color(237, 224, 212));
        inputNomPanel.setLayout(new java.awt.GridLayout(2, 0));

        nomLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        nomLabel1.setForeground(new java.awt.Color(89, 61, 59));
        nomLabel1.setText("Nom");
        inputNomPanel.add(nomLabel1);

        nomTextField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        nomTextField1.setForeground(new java.awt.Color(89, 61, 59));
        inputNomPanel.add(nomTextField1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 225;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        manualInputPanel.add(inputNomPanel, gridBagConstraints);

        inputMidaPanel.setBackground(new java.awt.Color(237, 224, 212));
        inputMidaPanel.setLayout(new java.awt.GridLayout(2, 0));

        midaLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        midaLabel1.setForeground(new java.awt.Color(89, 61, 59));
        midaLabel1.setText("Mida del Kenken");
        inputMidaPanel.add(midaLabel1);

        midaComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        midaComboBox1.setForeground(new java.awt.Color(89, 61, 59));
        midaComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "3", "4", "5", "6", "7", "8", "9" }));
        midaComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        midaComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                midaComboBox1ActionPerformed(evt);
            }
        });
        inputMidaPanel.add(midaComboBox1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 184;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        manualInputPanel.add(inputMidaPanel, gridBagConstraints);

        inputOperationPanel.setBackground(new java.awt.Color(237, 224, 212));
        inputOperationPanel.setPreferredSize(new java.awt.Dimension(300, 141));
        inputOperationPanel.setLayout(new java.awt.GridBagLayout());

        operationLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        operationLabel.setForeground(new java.awt.Color(89, 61, 59));
        operationLabel.setText("Operació");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        inputOperationPanel.add(operationLabel, gridBagConstraints);

        operationPanel.setBackground(new java.awt.Color(237, 224, 212));
        operationPanel.setLayout(new java.awt.GridLayout(3, 3, 20, 0));

        sumaButton.setBackground(new java.awt.Color(237, 224, 212));
        operationGroup.add(sumaButton);
        sumaButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        sumaButton.setForeground(new java.awt.Color(89, 61, 59));
        sumaButton.setText("=");
        sumaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sumaButtonActionPerformed(evt);
            }
        });
        operationPanel.add(sumaButton);

        restaButton.setBackground(new java.awt.Color(237, 224, 212));
        operationGroup.add(restaButton);
        restaButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        restaButton.setForeground(new java.awt.Color(89, 61, 59));
        restaButton.setText("+");
        operationPanel.add(restaButton);

        modButton.setBackground(new java.awt.Color(237, 224, 212));
        operationGroup.add(modButton);
        modButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        modButton.setForeground(new java.awt.Color(89, 61, 59));
        modButton.setText("-");
        operationPanel.add(modButton);

        multiplicacioButton.setBackground(new java.awt.Color(237, 224, 212));
        operationGroup.add(multiplicacioButton);
        multiplicacioButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        multiplicacioButton.setForeground(new java.awt.Color(89, 61, 59));
        multiplicacioButton.setText("*");
        operationPanel.add(multiplicacioButton);

        divisioButton.setBackground(new java.awt.Color(237, 224, 212));
        operationGroup.add(divisioButton);
        divisioButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        divisioButton.setForeground(new java.awt.Color(89, 61, 59));
        divisioButton.setText("/");
        operationPanel.add(divisioButton);

        jRadioButton1.setBackground(new java.awt.Color(237, 224, 212));
        operationGroup.add(jRadioButton1);
        jRadioButton1.setForeground(new java.awt.Color(89, 61, 59));
        jRadioButton1.setText("%");
        operationPanel.add(jRadioButton1);

        arrelButton.setBackground(new java.awt.Color(237, 224, 212));
        operationGroup.add(arrelButton);
        arrelButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        arrelButton.setForeground(new java.awt.Color(89, 61, 59));
        arrelButton.setText("√");
        operationPanel.add(arrelButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        inputOperationPanel.add(operationPanel, gridBagConstraints);

        resultLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        resultLabel.setForeground(new java.awt.Color(89, 61, 59));
        resultLabel.setText("Resultat");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        inputOperationPanel.add(resultLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 53;
        gridBagConstraints.ipady = 15;
        inputOperationPanel.add(resultTextField, gridBagConstraints);

        emptyPanel.setBackground(new java.awt.Color(237, 224, 212));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 14;
        inputOperationPanel.add(emptyPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        manualInputPanel.add(inputOperationPanel, gridBagConstraints);

        inputButtonsPanel1.setBackground(new java.awt.Color(237, 224, 212));
        inputButtonsPanel1.setLayout(new java.awt.GridBagLayout());

        setButton1.setBackground(new java.awt.Color(171, 148, 126));
        setButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        setButton1.setForeground(new java.awt.Color(237, 224, 212));
        setButton1.setText("Assignar");
        setButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        inputButtonsPanel1.add(setButton1, gridBagConstraints);

        validateButton1.setBackground(new java.awt.Color(171, 148, 126));
        validateButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        validateButton1.setForeground(new java.awt.Color(237, 224, 212));
        validateButton1.setText("Validar");
        validateButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        validateButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    validateButton1ActionPerformed(evt);
                } catch (ExcepcioKenken e) {
                    throw new RuntimeException(e);
                }
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        inputButtonsPanel1.add(validateButton1, gridBagConstraints);

        saveButton1.setBackground(new java.awt.Color(171, 148, 126));
        saveButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        saveButton1.setForeground(new java.awt.Color(237, 224, 212));
        saveButton1.setText("Guardar");
        saveButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        saveButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        inputButtonsPanel1.add(saveButton1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.ipady = 20;
        manualInputPanel.add(inputButtonsPanel1, gridBagConstraints);

        inputPanel.add(manualInputPanel, "manualCard");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        menuPanel.add(inputPanel, gridBagConstraints);

        add(menuPanel, new java.awt.GridBagConstraints());

        previewPanel.setBackground(new java.awt.Color(237, 224, 212));
        previewPanel.setLayout(new java.awt.GridBagLayout());

        previewLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        previewLabel.setForeground(new java.awt.Color(89, 61, 59));
        previewLabel.setText("Previsualització");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 40, 0);
        previewPanel.add(previewLabel, gridBagConstraints);

        boardPanel.setBackground(new java.awt.Color(204, 204, 255));
        boardPanel.setPreferredSize(new java.awt.Dimension(780, 555));
        boardPanel.setLayout(new java.awt.CardLayout());

        autoBoard.setBackground(new java.awt.Color(237, 224, 212));
        autoBoard.setLayout(new java.awt.GridBagLayout());

        kenkenPanel0.setBackground(new java.awt.Color(196, 176, 158));
        kenkenPanel0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        kenkenPanel0.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 450;
        gridBagConstraints.ipady = 450;
        autoBoard.add(kenkenPanel0, gridBagConstraints);

        boardPanel.add(autoBoard, "autoBoardCard");

        manualBoard.setBackground(new java.awt.Color(237, 224, 212));
        manualBoard.setLayout(new java.awt.GridBagLayout());

        kenkenPanel1.setBackground(new java.awt.Color(196, 176, 158));
        kenkenPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        kenkenPanel1.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 450;
        gridBagConstraints.ipady = 450;
        manualBoard.add(kenkenPanel1, gridBagConstraints);

        boardPanel.add(manualBoard, "manualBoardCard");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        previewPanel.add(boardPanel, gridBagConstraints);

        add(previewPanel, new java.awt.GridBagConstraints());
    }// </editor-fold>//GEN-END:initComponents

    private void midaComboBox0ActionPerformed(ActionEvent evt) {
    }

    private int getSelectedButton(ButtonGroup buttonGroup) {
        Enumeration<AbstractButton> buttons = buttonGroup.getElements();
        int i = 0;
        while (buttons.hasMoreElements()) {
            JRadioButton button = (JRadioButton) buttons.nextElement();
            if (button.isSelected()) return i;
            i++;
        }
        return -1;
    }

    private List<List<Integer>> generaCagesBase(int mida) {
        List<List<Integer>> cages = new ArrayList<>();
        cages.add(new ArrayList<>());
        cages.get(0).add(mida);         // Afegim la mida del kenken (primer element de la llista de cages)
        cages.get(0).add(mida*mida);    // Afegim el nombre de regions (segon element de la llista de cages)

        for (int i = 0; i < mida*mida; ++i) {
            List<Integer> cage = new ArrayList<>();
            cage.add(0); // Operacio
            cage.add(0); // Resultat
            cage.add(1); // Numero de cel.les
            cage.add(i/mida); // Fila
            cage.add(i%mida); // Columna
            cage.add(0); // Valor
            cages.add(cage);
        }
        return cages;
    }

    private void automaticTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_automaticTBActionPerformed
        // TODO add your handling code here:
        manualTB.setSelected(false);
        cardLayout0.show(inputPanel, "autoCard");
        cardLayout1.show(boardPanel, "autoBoardCard");
    }//GEN-LAST:event_automaticTBActionPerformed

    private void manualTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manualTBActionPerformed
        // TODO add your handling code here:
        automaticTB.setSelected(false);
        cardLayout0.show(inputPanel, "manualCard");
        cardLayout1.show(boardPanel, "manualBoardCard");
    }//GEN-LAST:event_manualTBActionPerformed

    private void generarButton0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarButton0ActionPerformed
        // TODO add your handling code here:
        //Netejem el kenken i el tornem a pintar
        kenkenPanel0.removeAll();
        kenkenPanel0.revalidate();
        kenkenPanel0.repaint();

        mida = Integer.parseInt((String) midaComboBox0.getSelectedItem());

        try {
            List<List<Integer>> cages = CtrlPresentacio.generarKenken("Nom", mida);
            Kenken k = new Kenken(cages);
            kenkenPanel0.add(k);
        } catch (ExcepcioKenken e) {

            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_generarButton0ActionPerformed

    private void saveButton0ActionPerformed(java.awt.event.ActionEvent evt) throws ExcepcioKenken {//GEN-FIRST:event_saveButton0ActionPerformed
        // TODO hacer el catch:
        String nom = nomTextField0.getText();
        Kenken k = (Kenken) kenkenPanel0.getComponent(0);
        List<List<Integer>> kenkenFormat = k.getCages();
        try {
            CtrlPresentacio.saveKenkenGenerat(nom, kenkenFormat);
        } catch (ExcepcioKenken e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), e.getMessage(), JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_saveButton0ActionPerformed

    private void midaComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_midaComboBox1ActionPerformed
        // TODO add your handling code here:
        String input = (String) midaComboBox1.getSelectedItem();
        if (input.equals("-")) return;
        int mida = Integer.parseInt(input);
        kenkenPanel1.removeAll();

        List<List<Integer>> cages = generaCagesBase(mida);
        Kenken k = new Kenken(cages);
        k.cleanClues();

        kenkenPanel1.add(k);
        kenkenPanel1.revalidate();
        kenkenPanel1.repaint();
    }//GEN-LAST:event_midaComboBox1ActionPerformed

    private void saveButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButton1ActionPerformed
        // TODO add your handling code here:

        if (kenkenPanel1.getComponentCount() == 0){
            String title = "Assigna una regio";
            String msg = "Has de seleccionar una mida";
            JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String nom = nomTextField1.getText();
        Kenken k = (Kenken) kenkenPanel1.getComponent(0);

        if (!k.isFull()) {
            String title = "Guardar Kenken";
            String msg = "Has d'omplir totes les cel·les abans de guardar el Kenken";
            JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (!k.isValidated()) {
            String title = "Guardar Kenken";
            String msg = "Has de validar el Kenken abans de guardar-lo";
            JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            CtrlPresentacio.saveKenkenGenerat(nom, k.getCages());
        } catch (ExcepcioKenken e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_saveButton1ActionPerformed

    private void sumaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sumaButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sumaButtonActionPerformed

    private void setButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setButton1ActionPerformed
        // TODO add your handling code here:
        if (kenkenPanel1.getComponentCount() == 0){
            String title = "Assigna una regio";
            String msg = "Has de seleccionar una mida";
            JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Kenken k = (Kenken) kenkenPanel1.getComponent(0);
        int selectedButton = getSelectedButton(operationGroup);
        try {
            int result = Integer.parseInt(resultTextField.getText());
            if (selectedButton == -1) {
                String title = "Assigna una regio";
                String msg = "Para assignar una regio has de seleccionar una operacio";
                JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
            }

            int operation = selectedButton;
            List<Cell> selectedCells = k.getSelectedCells();

            if ((operation == 0 || operation == 6) && selectedCells.size() != 1) {
                String title = "Assigna una regio";
                String msg = "Has de seleccionar una sola cel·la per a aquesta operació";
                JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (operation == 5 || operation == 2 || operation == 4 && selectedCells.size() != 2) {
                String title = "Assigna una regio";
                String msg = "Has de seleccionar dues cel·les per a aquesta operació";
                JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (operation != 0 && operation != 6 && operation != 5 && selectedCells.size() < 2) {
                String title = "Assigna una regio";
                String msg = "Has de seleccionar almenys dues cel·les per a aquesta operació";
                JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            List<Integer> cage = new ArrayList<>();
            cage.add(operation);
            cage.add(result);
            cage.add(selectedCells.size());
            for (Cell cell : selectedCells) {
                cage.add(cell.getRow());
                cage.add(cell.getCol());
                cage.add(cell.getValue());
            }
            k.addManulaCage(cage);
            k.printManualCage(cage);
            k.updateEmptyCells(selectedCells.size());
            selectedCells.clear();
        } catch (NumberFormatException e) {
            String title = "Assigna una regio";
            String msg = "Has d'assignar un resultat numèric";
            JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
            return;
        }
    }//GEN-LAST:event_setButton1ActionPerformed

    private void validateButton1ActionPerformed(java.awt.event.ActionEvent evt) throws ExcepcioKenken {//GEN-FIRST:event_validateButton1ActionPerformed
        // TODO add your handling code here:
        if (kenkenPanel1.getComponentCount() == 0){
            String title = "Assigna una regio";
            String msg = "Has de seleccionar una mida";
            JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Kenken k = (Kenken) kenkenPanel1.getComponent(0);
        if (k.isFull() && k.isSolvable()) {
            String title = "Estat del Kenken";
            String msg = "El Kenken és vàlid";
            JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            String title = "Estat del Kenken";
            String msg = "El Kenken no és vàlid";
            JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_validateButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton arrelButton;
    private javax.swing.JPanel autoBoard;
    private javax.swing.JPanel autoInputPanel;
    private javax.swing.JToggleButton automaticTB;
    private javax.swing.JPanel boardPanel;
    private javax.swing.JRadioButton divisioButton;
    private javax.swing.JPanel emptyPanel;
    private javax.swing.JButton generarButton0;
    private javax.swing.JPanel inputButtonsPanel1;
    private javax.swing.JPanel inputMidaPanel;
    private javax.swing.JPanel inputNomPanel;
    private javax.swing.JPanel inputOperationPanel;
    private javax.swing.JPanel inputPanel;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JPanel kenkenPanel0;
    private javax.swing.JPanel kenkenPanel1;
    private javax.swing.JPanel manualBoard;
    private javax.swing.JPanel manualInputPanel;
    private javax.swing.JToggleButton manualTB;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JComboBox<String> midaComboBox0;
    private javax.swing.JComboBox<String> midaComboBox1;
    private javax.swing.JLabel midaLabel0;
    private javax.swing.JLabel midaLabel1;
    private javax.swing.JRadioButton modButton;
    private javax.swing.JLabel modeLabel;
    private javax.swing.JRadioButton multiplicacioButton;
    private javax.swing.JLabel nomLabel0;
    private javax.swing.JLabel nomLabel1;
    private javax.swing.JTextField nomTextField0;
    private javax.swing.JTextField nomTextField1;
    private javax.swing.ButtonGroup operationGroup;
    private javax.swing.JLabel operationLabel;
    private javax.swing.JPanel operationPanel;
    private javax.swing.JLabel previewLabel;
    private javax.swing.JPanel previewPanel;
    private javax.swing.JRadioButton restaButton;
    private javax.swing.JLabel resultLabel;
    private javax.swing.JTextField resultTextField;
    private javax.swing.JButton saveButton0;
    private javax.swing.JButton saveButton1;
    private javax.swing.JPanel selectModePanel;
    private javax.swing.JButton setButton1;
    private javax.swing.JRadioButton sumaButton;
    private javax.swing.JButton validateButton1;
    // End of variables declaration//GEN-END:variables
}
