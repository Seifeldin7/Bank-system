
package bms;
import bmsgui.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class DepositController {
    JButton btnDeposit ;
    JDialog dg ;
    String username;
    Client client ;
    int id;
    public DepositController(JDialog dg,JButton btnDeposit,String username, Client client, int id){
        this.btnDeposit =btnDeposit;
        this.dg =dg;
        this.client =client;
        this.id =id;
        this.username =username;
        btnDeposit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    String input = JOptionPane.showInputDialog(dg, "Enter the desired amount"
                            + " of money to put");
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
                        t = new Transaction(a, "Deposit");
                        t.deposit(amount);

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
                        pw.println("Added amount: " + amount);
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
                        t = new Transaction(a, "Deposit");
                        t.deposit(amount);
                        fw = new FileWriter(f1, false);
                        pw = new PrintWriter(fw, true);
                        pw.println(a.getBalance());
                        pw.close();
                        fw.close();
                        fw = new FileWriter(f2, true);
                        pw = new PrintWriter(fw, true);
                        pw.println("Added amount: " + amount);
                        pw.println("New Balance: " + t.getDefaultAccount().getBalance());
                        pw.println(t.getType());
                        pw.close();
                        fw.close();
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(dg, "You didn't enter a number");
                } catch (NegativeMoney e2) {
                    JOptionPane.showMessageDialog(dg, e2.getMessage());
                } catch (IOException e3) {
                    JOptionPane.showMessageDialog(dg, "IO exception occurred");
                }
            }
        });
    }
}
