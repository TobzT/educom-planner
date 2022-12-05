package models;

import javax.swing.*;

public class TPanel extends JPanel {

    private int id;
    private JLabel label;

    public TPanel(int id, JLabel label) {
        super();
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getIdString() {
        return Integer.toString(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabelText() {
        return label.getText();
    }

    public void setLabelText(String labelText) {
        this.label.setText(labelText);
    }
}
