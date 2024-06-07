package capaPresentacio.views;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class CellListener implements FocusListener {
    @Override
    public void focusGained(FocusEvent e) {
        // Obtenim la informacio
        JComponent input = (JComponent) e.getSource();
        Cell sourceCell = (Cell) input.getParent();
        Kenken kenken = (Kenken) sourceCell.getParent();

        // Actualitzem la informacio
        sourceCell.highlight();
        kenken.setCurrentCell(sourceCell);
    }

    @Override
    public void focusLost(FocusEvent e) {
        // Obtenim la informacio
        JComponent input = (JComponent) e.getSource();
        Cell sourceCell = (Cell) input.getParent();

        // Actualitzem la informacio
        sourceCell.unhighlight();
    }
}
