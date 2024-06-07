package capaPresentacio.views;

import java.awt.*;
import javax.swing.*;

import java.util.List;
import java.util.TreeSet;

public class Cell extends JPanel {
    private final int row;
    private final int col;
    private boolean selected;
    private final JLabel clue;
    private final JTextField input;
    private final JTextArea notas;
    private final TreeSet<Integer> notasSet;
    private final CellListener cellListener;
    private CellClickListener cellClickListener;

    ///////////////////////////////////////////////// METODES PRIVATS //////////////////////////////////////////////////
    private void updateNotas() {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (Integer nota : notasSet) {
            sb.append(nota);
            count++;
            if (count % 5 == 0) {
                sb.append("\n");
            } else {
                sb.append(" ");
            }
        }
        notas.setText(sb.toString());
    }

    ////////////////////////////////////////////////// CONSTRUCTORES ///////////////////////////////////////////////////
    public Cell(int row, int col) {
        super();
        this.row = row;
        this.col = col;
        this.selected = false;
        this.notasSet = new TreeSet<>();
        this.cellListener = new CellListener();

        setFocusable(true);
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        // OPERACIO-RESULTAT
        this.clue = new JLabel(" ");
        this.clue.setOpaque(true);
        this.clue.setBackground(Color.WHITE);
        this.clue.setMinimumSize(new Dimension(0, 0));
        this.clue.setFont(new Font("Segoe UI", 0, 14));
        this.clue.setHorizontalAlignment(SwingConstants.LEFT);
        add(clue, BorderLayout.PAGE_START);
        
        // NUMBER-INPUT
        this.input = new JTextField("");
        this.input.setBorder(null);
        this.input.setEditable(false);
        this.input.setBackground(Color.WHITE);
        this.input.addFocusListener(cellListener);
        this.input.setMinimumSize(new Dimension(0, 0));
        this.input.setFont(new Font("Segoe UI", 0, 18));
        //add(input, BorderLayout.CENTER);

        // NOTAS
        this.notas = new JTextArea("");
        this.notas.setBorder(null);
        this.notas.setEditable(false);
        this.notas.setForeground(Color.BLUE);
        this.notas.setBackground(Color.WHITE);
        this.notas.addFocusListener(cellListener);
        this.notas.setMinimumSize(new Dimension(0, 0));
        this.notas.setFont(new Font("Segoe UI", 0, 10));
        this.notas.setLineWrap(true);
        this.notas.setWrapStyleWord(true);
    }

    ///////////////////////////////////////////////////// GETTERS //////////////////////////////////////////////////////
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    ///////////////////////////////////////////////////// SETTERS //////////////////////////////////////////////////////
    public void setClue(String clue) {
        this.clue.setText(clue);
    }

    public void setValor(int num) {
        if (num == 0) input.setText("");
        else input.setText(num + "");
    }

    public void setBorder(List<Integer> edges) {
        // DEFAULT BORDER THICKNESS: 2px
        int top = 2;
        int left = 2;
        int bottom = 2;
        int right = 2;

        for (int edge : edges) {
            switch (edge) {
                case 1: top = 0; break;
                case 2: left = 0; break;
                case 3: bottom = 0; break;
                case 4: right = 0; break;
            }
        }
        setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));
    }

    public void addNota(int num) {
        notasSet.add(num);
        updateNotas();
    }

    public void removeNota(int num) {
        notasSet.remove(num);
        updateNotas();
    }

    public void highlight() {
        clue.setBackground(Color.LIGHT_GRAY);
        input.setBackground(Color.LIGHT_GRAY);
        notas.setBackground(Color.LIGHT_GRAY);
    }

    public void unhighlight() {
        clue.setBackground(Color.WHITE);
        input.setBackground(Color.WHITE);
        notas.setBackground(Color.WHITE);
    }

    public int getValue() {
        String value = input.getText();
        if (value.equals("")) return 0;
        return Integer.parseInt(value);
    }

    public TreeSet<Integer> getNotes() {
        return notasSet;
    }

    public void showNotas() {
        remove(input);
        add(notas, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void hideNotas() {
        remove(notas);
        input.setHorizontalAlignment(JTextField.CENTER);
        add(input, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void setSelected(boolean b) {
        selected = b;
        if (selected) setBackground(Color.LIGHT_GRAY);
        else setBackground(Color.WHITE);
    }

    public boolean isSelected() {
        return selected;
    }
}
