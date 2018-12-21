package bmsgui;

import bms.Client;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

public class Home extends JFrame {

    JFrame frame = this;
    JPanel pnlTop = new JPanel();
    JPanel pnlMain = new JPanel();
    JPanel pnlBottom = new JPanel();
    JLabel lblWelcome = new JLabel("Welcome to the AOS bank!");
    JLabel lblUsername = new JLabel("Username:");
    JLabel lblPassword = new JLabel("Password:");
    JLabel lblCreateProfile = new JLabel("Don't have a profile? Sign up ..");
    JLabel lblRecoverProfile = new JLabel("Don't remember password? Recover ..");
    JTextField txtUsername = new JTextField();
    JTextField txtPassword = new JTextField();
    JButton btnLogin = new JButton("Login");
    JButton btnCreateProfile = new JButton("Create Profile");
    JButton btnRecoverProfile = new JButton("Recover Profile");
    String strLogin = "Login.txt";

    public Home() {

        setTitle("AOS Bank");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(d.width / 4, d.height / 4, d.width / 2, d.height / 2);

        Container c = getContentPane();

        pnlMain.setPreferredSize(new Dimension(d.width / 2, d.height / 4));
        pnlTop.setPreferredSize(new Dimension(d.width / 2, d.height / 8));
        pnlBottom.setPreferredSize(new Dimension(d.width / 2, d.height / 8));

        pnlTop.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        pnlTop.add(lblWelcome);

        Dimension d1 = new Dimension(300,30);
        Dimension d2 = new Dimension(300,50);
        Dimension d3 = new Dimension (150,30);
        lblUsername.setPreferredSize(d1);
        lblPassword.setPreferredSize(d2);
        txtUsername.setPreferredSize(d1);
        txtPassword.setPreferredSize(d1);
        btnLogin.setPreferredSize(d3);
        pnlMain.add(lblUsername);
        pnlMain.add(txtUsername);
        pnlMain.add(lblPassword);
        pnlMain.add(txtPassword);
        pnlMain.add(btnLogin);

        lblCreateProfile.setPreferredSize(d1);
        lblRecoverProfile.setPreferredSize(d1);
        btnCreateProfile.setPreferredSize(d3);
        btnRecoverProfile.setPreferredSize(d3);
        pnlBottom.add(lblCreateProfile);
        pnlBottom.add(btnCreateProfile);
        pnlBottom.add(lblRecoverProfile);
        pnlBottom.add(btnRecoverProfile);

        c.add(pnlTop, BorderLayout.NORTH);
        c.add(pnlBottom, BorderLayout.SOUTH);
        c.add(pnlMain);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String username = txtUsername.getText();
                String password = txtPassword.getText();
                boolean success = false;
                int id = 0;
                
                try{
                File f1 = new File("Login.txt");
                FileReader fr1 = new FileReader(f1);
                BufferedReader br1 = new BufferedReader(fr1);
                while(br1.ready()){
                    String strUsername = br1.readLine();
                    String strPassword = br1.readLine();
                    id++;
                    if(strUsername.equals(username) && strPassword.equals(password)){
                        success = true;
                        
                        File f2 = new File(username + ".txt");
                        FileReader fr2 = new FileReader(f2);
                        BufferedReader br2 = new BufferedReader(fr2);
                        String name = br2.readLine();
                        int age = Integer.parseInt(br2.readLine());
                        int nationalNumber = Integer.parseInt(br2.readLine());
                        String address = br2.readLine();
                        String email = br2.readLine();
                        String job = br2.readLine();
                        Client c = new Client(name, age, nationalNumber, address, email, 
                                job);
                        ClientPage client = new ClientPage(username, c, id);
                        client.setVisible(true);
                        break;
                    }
                }
                if(!success){
                    JOptionPane.showMessageDialog(frame, "Login Failed");
                }
                fr1.close();
                br1.close();
                txtUsername.setText("");
                txtPassword.setText("");
                
                }catch(FileNotFoundException e1){
                    JOptionPane.showMessageDialog(frame, "Login.txt file doesn't exist");
                }
                catch(IOException e2){
                    JOptionPane.showMessageDialog(frame, "Please check that the Login.txt"
                            + "file isn't in use or deleted");
                }
            }
        });
        btnCreateProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateProfile cp = new CreateProfile();
                cp.setVisible(true);
            }
        });
        btnRecoverProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String strUsernameRecover = JOptionPane.showInputDialog(
                        "Enter your username");
                String strPasswordRecover;
                try {
                    File f = new File("Login.txt");
                    FileReader fr = new FileReader(f);
                    BufferedReader br = new BufferedReader(fr);
                    String username = br.readLine();
                    boolean recovered = false;
                    
                    while (br.ready()) {
                        if (username.equals(strUsernameRecover)) {
                            strPasswordRecover = br.readLine();
                            JOptionPane.showMessageDialog(frame, "Your Passowrd is: " + 
                                    strPasswordRecover);
                            recovered = true;
                            break;
                        }
                        username = br.readLine();
                    }
                    if (!recovered) {
                        JOptionPane.showMessageDialog(frame,"Profile Doesn't Exist");
                        }
                    br.close();
                    fr.close();
                }
                catch(FileNotFoundException e1){
                    JOptionPane.showMessageDialog(frame, "Login.txt doesn't exist");
                }
                catch (IOException e2) {
                    JOptionPane.showMessageDialog(frame, "Please check that the Login.txt"
                            + "file isn't in use or deleted");
                }
            }
        });
    }
}