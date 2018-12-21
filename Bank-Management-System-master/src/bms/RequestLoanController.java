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

public class RequestLoanController {

    JButton btnRequestLoan;
    JDialog dg;
    String username;
    Client client;
    int id;

    public RequestLoanController(JButton btnRequestLoan, JDialog dg, Client client, int id, String username) {
        this.btnRequestLoan = btnRequestLoan;
        this.client = client;
        this.dg = dg;
        this.id = id;
        this.username = username;
        btnRequestLoan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                File f1 = new File(username + " Balance.txt");
                File f2 = new File(username + " Transactions.txt");
                File f3 = new File(username + " On Loan.txt");

                try {
                    if (!f1.exists()) {
                        f1.createNewFile();
                    }
                    FileReader fr = new FileReader(f1);
                    BufferedReader br = new BufferedReader(fr);
                    String newBalance = br.readLine();
                    int balance;
                    if (newBalance == null) {
                        balance = 0;
                    } else {
                        balance = Integer.parseInt(newBalance);
                    }
                    br.close();
                    fr.close();
                    if (!f3.exists()) {
                        f3.createNewFile();
                    }
                    fr = new FileReader(f3);
                    br = new BufferedReader(fr);
                    boolean onLoan = Boolean.parseBoolean(br.readLine());
                    br.close();
                    fr.close();
                    Account a = new Account(id, client, balance, onLoan);
                    Transaction t = new Transaction(a, "Request Loan");
                    t.requestLoan();
                    JOptionPane.showMessageDialog(dg, "Done. Check your new balance");
                    FileWriter fw = new FileWriter(f1, false);
                    PrintWriter pw = new PrintWriter(fw, true);
                    pw.println(a.getBalance());
                    pw.close();
                    fw.close();

                    fw = new FileWriter(f2, true);
                    pw = new PrintWriter(fw, true);
                    pw.println("New Balance: " + t.getDefaultAccount().getBalance());
                    pw.println(t.getType());
                    pw.close();
                    fw.close();

                    fw = new FileWriter(f3, false);
                    pw = new PrintWriter(fw, true);
                    pw.println(a.isOnLoan());
                    pw.close();
                    fw.close();

                } catch (FileNotFoundException e1) {
                    JOptionPane.showMessageDialog(dg, "File not found");
                } catch (IOException e2) {
                    JOptionPane.showMessageDialog(dg, "IO Exception occurred");
                } catch (LoanException e3) {
                    JOptionPane.showMessageDialog(dg, e3.getMessage());
                }
            }
        });
    }
}
