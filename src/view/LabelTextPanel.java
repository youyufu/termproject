package view;

import javax.swing.*;

/**
 * A panel containing a label and a text field.
 */
class LabelTextPanel extends JPanel {

    /**
     * Constructs a LabelTextPanel.
     * @param label, the label to be added.
     * @param textField, the text field to be added.
     */
    LabelTextPanel(JLabel label, JTextField textField) {
        this.add(label);
        this.add(textField);
    }
}
