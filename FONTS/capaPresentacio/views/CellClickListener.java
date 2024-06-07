package capaPresentacio.views;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CellClickListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        // Obtenim la informacio
        JTextField input = (JTextField) e.getSource();
        Cell sourceCell = (Cell) input.getParent();
        Kenken kenken = (Kenken) sourceCell.getParent();

        // Actualitzem la informacio
        sourceCell.highlight();                 //Quitar cunado se assigna
        sourceCell.setSelected(true);
        kenken.addSelectedCell(sourceCell);
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
