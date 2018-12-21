/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bms;

import bmsgui.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class InquiryController {

    JButton btnInquiry;
    JDialog dg;
    String username;
    Client client;
    int id;

    public InquiryController(JButton btnInquiry, JDialog dg, Client client, int id, String username) {
        this.btnInquiry = btnInquiry;
        this.client = client;
        this.dg = dg;
        this.id = id;
        this.username = username;
        btnInquiry.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                File f = new File(username + " Balance.txt");
                try {
                    FileReader fr = new FileReader(f);
                    BufferedReader br = new BufferedReader(fr);
                    String newBalance = br.readLine();
                    int balance;
                    if (newBalance == null) {
                        balance = 0;
                    } else {
                        balance = Integer.parseInt(newBalance);
                    }
                    JOptionPane.showMessageDialog(dg, "Your balance = " + balance);
                } catch (FileNotFoundException e1) {
                    JOptionPane.showMessageDialog(dg, "Your balance = zero");
                } catch (IOException e2) {
                    JOptionPane.showMessageDialog(dg, "IO Exception occurred");
                }
            }
        });
    }
}
