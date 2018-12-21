package bmsgui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class CreateProfile extends JDialog {

    JDialog dg = this;
    JPanel pnlMain = new JPanel();
    JPanel pnlBottom = new JPanel();
    JLabel lblUsername = new JLabel("Username:");
    JLabel lblPassword = new JLabel("Password:");
    JLabel lblName = new JLabel("Name:");
    JLabel lblAge = new JLabel("Age:");
    JLabel lblNationalNumber = new JLabel("National Number:");
    JLabel lblAddress = new JLabel("Address:");
    JLabel lblEmail = new JLabel("Email:");
    JLabel lblJob = new JLabel("Job:");
    JTextField txtUsername = new JTextField();
    JTextField txtPassword = new JTextField();
    JTextField txtName = new JTextField();
    JTextField txtAge = new JTextField();
    JTextField txtNationalNumber = new JTextField();
    JTextField txtAddress = new JTextField();
    JTextField txtEmail = new JTextField();
    JTextField txtJob = new JTextField();
    JButton btnCreate = new JButton("Create");

    public CreateProfile() {
        setTitle("Create New Profile");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModalityType(ModalityType.APPLICATION_MODAL);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(d.width / 3, d.height / 4, d.width / 2, d.height / 2);

        Container c = getContentPane();

        pnlMain.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        pnlBottom.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        Dimension d1 = new Dimension(300, 30);
        Dimension d2 = new Dimension(150, 30);

        lblUsername.setPreferredSize(d1);
        lblPassword.setPreferredSize(d1);
        lblName.setPreferredSize(d1);
        lblAge.setPreferredSize(d1);
        lblNationalNumber.setPreferredSize(d1);
        lblAddress.setPreferredSize(d1);
        lblEmail.setPreferredSize(d1);
        lblJob.setPreferredSize(d1);
        txtUsername.setPreferredSize(d1);
        txtPassword.setPreferredSize(d1);
        txtName.setPreferredSize(d1);
        txtAge.setPreferredSize(d1);
        txtNationalNumber.setPreferredSize(d1);
        txtAddress.setPreferredSize(d1);
        txtEmail.setPreferredSize(d1);
        txtJob.setPreferredSize(d1);
        btnCreate.setPreferredSize(d2);

        pnlMain.add(lblUsername);
        pnlMain.add(txtUsername);
        pnlMain.add(lblPassword);
        pnlMain.add(txtPassword);
        pnlMain.add(lblName);
        pnlMain.add(txtName);
        pnlMain.add(lblAge);
        pnlMain.add(txtAge);
        pnlMain.add(lblNationalNumber);
        pnlMain.add(txtNationalNumber);
        pnlMain.add(lblAddress);
        pnlMain.add(txtAddress);
        pnlMain.add(lblEmail);
        pnlMain.add(txtEmail);
        pnlMain.add(lblJob);
        pnlMain.add(txtJob);
        pnlBottom.add(btnCreate);
        c.add(pnlMain);
        c.add(pnlBottom, BorderLayout.SOUTH);

        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File f1 = new File("Login.txt");
                    if(!f1.exists())
                        f1.createNewFile();
                    FileReader fr1 = new FileReader(f1);
                    BufferedReader br1 = new BufferedReader(fr1);

                    String strUsername = br1.readLine();
                    String username = txtUsername.getText();
                    String password = txtPassword.getText();
                    String name = txtName.getText();
                    int age = Integer.parseInt(txtAge.getText());
                    int nationalNumber = Integer.parseInt(txtNationalNumber.getText());
                    String address = txtAddress.getText();
                    String email = txtEmail.getText();
                    String job = txtJob.getText();
                    
                    while (br1.ready()) {
                        if (strUsername.equals(username)) {
                            br1.close();
                            fr1.close();
                            throw new Error();
                        }
                        strUsername = br1.readLine();
                    }
                    
                    br1.close();
                    fr1.close();
                    FileWriter fw1 = new FileWriter(f1, true);
                    PrintWriter pw1 = new PrintWriter(fw1, true);
                    
                    pw1.println(username);
                    pw1.println(password);
                    pw1.close();
                    fw1.close();
                    
                    File f2 = new File(username + ".txt");
                    FileWriter fw2 = new FileWriter(f2);
                    PrintWriter pw2 = new PrintWriter(fw2, true);

                    pw2.println(name);
                    pw2.println(age);
                    pw2.println(nationalNumber);
                    pw2.println(address);
                    pw2.println(email);
                    pw2.println(job);

                    pw2.close();
                    fw2.close();
                    JOptionPane.showMessageDialog(dg, "Done. Account created");
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(dg, "IOException encountered. Please check"
                            + "the Login.txt file if in use or deleted.");
                } catch (Error e2) {
                    JOptionPane.showMessageDialog(dg, "Username already reserved. Please"
                            + " make another username.");
                } catch (Exception e3) {
                    JOptionPane.showMessageDialog(dg, "Please check that you filled all the"
                            + "fields with the right data types.");
                }
            }
        });
    }
}