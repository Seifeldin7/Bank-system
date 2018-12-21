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

public class ViewTransactionsController {

    JButton btnViewTransactions;
    JDialog dg;
    String username;
    Client client;
    int id;

    public ViewTransactionsController(JButton btnViewTransactions, JDialog dg, Client client, int id, String username) {
        this.btnViewTransactions = btnViewTransactions;
        this.client = client;
        this.dg = dg;
        this.id = id;
        this.username = username;
        btnViewTransactions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String str = "";
                File f = new File(username + " Transactions.txt");
                try {
                    FileReader fr = new FileReader(f);
                    BufferedReader br = new BufferedReader(fr);
                    while (br.ready()) {
                        str += br.readLine();
                        str += "\n";
                    }
                    br.close();
                    fr.close();
                    ViewTransactions vt = new ViewTransactions(str);
                    vt.setVisible(true);
                } catch (FileNotFoundException e1) {
                    JOptionPane.showMessageDialog(dg, "No transactions done yet");
                } catch (IOException e2) {
                    JOptionPane.showMessageDialog(dg, "IO Exception occurred");
                }
            }
        });
    }
}
