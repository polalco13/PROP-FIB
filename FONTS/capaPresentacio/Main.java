package capaPresentacio;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        try {
            // Metal Look and Feel
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        CtrlPresentacio CP = new CtrlPresentacio();
        CtrlPresentacio.ini();
    }
}
