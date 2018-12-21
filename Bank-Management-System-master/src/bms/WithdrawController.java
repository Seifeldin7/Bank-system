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


public class WithdrawController {
    JButton btnWithdraw ;
    JDialog dg ;
    String username;
    Client client ;
    int id;
    public WithdrawController(JButton Withdraw,JDialog dg,Client client,int id,String username){
        this.btnWithdraw=Withdraw;
        this.client =client;
        this.dg =dg;
        this.id =id;
        this.username =username;
        btnWithdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String input = JOptionPane.showInputDialog(dg, "Enter the desired amount"
                            + " of money to pull");
                    int amount = Integer.parseInt(input);
                    Account a;
                    Transaction t;
                    FileWriter fw;
                    PrintWriter pw;
                    FileReader fr;
                    BufferedReader br;

                    File f1 = new File(username + " Balance.txt");
                    File f2 = new File(username + " Transactions.txt");
                    if (!f1.exists()) {
                        f1.createNewFile();
                        a = new Account(id, client);
                        t = new Transaction(a, "Withdraw");
                        t.withdraw(amount);

                        fw = new FileWriter(f1, false);
                        pw = new PrintWriter(fw, true);
                        pw.println(a.getBalance());
                        pw.close();
                        fw.close();
                        if (!f2.exists()) {
                            f2.createNewFile();
                        }
                        fw = new FileWriter(f2, true);
                        pw = new PrintWriter(fw, true);
                        pw.println("Withdrawed amount: " + amount);
                        pw.println("New Balance: " + t.getDefaultAccount().getBalance());
                        pw.println(t.getType());
                        pw.close();
                        fw.close();
                    } else {
                        fr = new FileReader(f1);
                        br = new BufferedReader(fr);
                        String newBalance = br.readLine();
                        int balance;
                        if (newBalance == null) {
                            balance = 0;
                        } else {
                            balance = Integer.parseInt(newBalance);
                        }
                        a = new Account(id, client, balance);
                        t = new Transaction(a, "Withdraw");
                        t.withdraw(amount);
                        fw = new FileWriter(f1, false);
                        pw = new PrintWriter(fw, true);
                        pw.println(a.getBalance());
                        pw.close();
                        fw.close();
                        fw = new FileWriter(f2, true);
                        pw = new PrintWriter(fw, true);
                        pw.println("Withdrawed amount: " + amount);
                        pw.println("New Balance: " + t.getDefaultAccount().getBalance());
                        pw.println(t.getType());
                        pw.close();
                        fw.close();
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(dg, "You didn't enter a number");
                } catch (NegativeMoney e2) {
                    JOptionPane.showMessageDialog(dg, e2.getMessage());
                } catch (InsufficientBalance e3) {
                    JOptionPane.showMessageDialog(dg, "Can't extract money that is greater"
                            + " than your balance");
                } catch (IOException e4) {
                    JOptionPane.showMessageDialog(dg, "IO exception occurred");
                }
            }
        });
    }
}
