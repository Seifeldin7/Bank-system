package bmsgui;

import javax.swing.*;
import java.awt.*;

public class ViewTransactions extends JDialog {

    JTextArea txtTransactions = new JTextArea("Empty");

    public ViewTransactions(String str) {
        setTitle("My Transactions");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModalityType(ModalityType.APPLICATION_MODAL);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(d.width / 3, d.height / 4, d.width / 2, d.height / 2);

        Container c = getContentPane();

        txtTransactions.setText(str);
        txtTransactions.setEditable(false);
        c.add(txtTransactions);
    }
}
