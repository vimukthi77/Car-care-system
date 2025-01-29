/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menus;

/**
 *
 * @author Oshan
 */
import javax.swing.JSpinner;


public class Spinner extends JSpinner {

    public void setLabelText(String text) {
        Spinner_UI.Editor editor = (Spinner_UI.Editor) getEditor();
        editor.setLabelText(text);
    }

    public String getLabelText() {
        Spinner_UI.Editor editor = (Spinner_UI.Editor) getEditor();
        return editor.getLabelText();
    }

    public Spinner() {
        setOpaque(false);
        setUI(new Spinner_UI());
    }
}
