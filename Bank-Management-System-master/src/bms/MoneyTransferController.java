package bms;

import bmsgui.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class MoneyTransferController {

    JButton btnMoneyTransfer;
    JDialog dg;
    String username;
    Client client;
    int id;

    public MoneyTransferController(JButton btnMoneyTransfer, JDialog dg, Client client, int id, String username) {
        this.btnMoneyTransfer = btnMoneyTransfer;
        this.client = client;
        this.dg = dg;
        this.id = id;
        this.username = username;
        btnMoneyTransfer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String inputUsername = JOptionPane.showInputDialog(dg, "Enter the "
                            + "username of the other account to transfer the money ");
                    if (inputUsername == null) {
                        throw new NullPointerException("You didn't enter a name");
                    }
                    String inputAmount = JOptionPane.showInputDialog(dg, "Enter the desired"
                            + " amount of money");
                    int amount = Integer.parseInt(inputAmount);

                    Account a;
                    Account b;
                    Transaction t;
                    FileWriter fw;
                    PrintWriter pw;
                    FileReader fr;
                    BufferedReader br;
                    int inputId = 0;
                    String name;
                    int age;
                    int nationalNumber;
                    String address;
                    String email;
                    String job;
                    Client newClient;

                    //f1 & f2  for the transfering account
                    //f3 & f4 for the transfered-to account
                    File f1 = new File(username + " Balance.txt");
                    File f2 = new File(username + " Transactions.txt");
                    File f3 = new File("Login.txt");
                    File f4 = new File(inputUsername + ".txt");
                    File f5 = new File(inputUsername + " Balance.txt");
                    File f6 = new File(inputUsername + " Transactions.txt");

                    //Check if the transferedTo account exists
                    if (f4.exists()) {

                        if (!f1.exists()) {
                            // If there is no balance file, balance = zero.
                            JOptionPane.showMessageDialog(dg, "Your balance can't afford the"
                                    + " transfer");
                        } else {
                            fr = new FileReader(f3);
                            br = new BufferedReader(fr);
                            while (br.ready()) {
                                String strUsername = br.readLine();
                                String strPassword = br.readLine();
                                inputId++;
                                if (username.equals(inputUsername)) {
                                    throw new Exception("Can'transfer money to yourself");
                                }
                                if (strUsername.equals(inputUsername)) {
                                    break;
                                }
                            }
                            br.close();
                            fr.close();
                            fr = new FileReader(f4);
                            br = new BufferedReader(fr);
                            name = br.readLine();
                            age = Integer.parseInt(br.readLine());
                            nationalNumber = Integer.parseInt(br.readLine());
                            address = br.readLine();
                            email = br.readLine();
                            job = br.readLine();
                            newClient = new Client(name, age, nationalNumber, address, email,
                                    job);
                            br.close();
                            fr.close();
                            fr = new FileReader(f1);
                            br = new BufferedReader(fr);
                            int balance = Integer.parseInt(br.readLine());
                            br.close();
                            fr.close();
                            if (!f5.exists()) {
                                f5.createNewFile();
                            }
                            fr = new FileReader(f5);
                            br = new BufferedReader(fr);
                            int newClientbalance;
                            String clientBalance = br.readLine();
                            if (clientBalance == null) {
                                newClientbalance = 0;
                            } else {
                                newClientbalance = Integer.parseInt(clientBalance);
                            }
                            a = new Account(id, client, balance);
                            b = new Account(inputId, newClient, newClientbalance);
                            t = new Transaction(a, b);
                            t.moneyTransfer(amount);
                            fw = new FileWriter(f1, false);
                            pw = new PrintWriter(fw, true);
                            pw.println(a.getBalance());
                            pw.close();
                            fw.close();
                            fw = new FileWriter(f2, true);
                            pw = new PrintWriter(fw, true);
                            pw.println("Transfered amount from you: " + amount);
                            pw.println("New Balance: " + t.getTransferredFrom().
                                    getBalance());
                            pw.println(t.getType());
                            pw.close();
                            fw.close();
                            // For the transfered to account
                            fw = new FileWriter(f5, false);
                            pw = new PrintWriter(fw, true);
                            pw.println(b.getBalance());
                            pw.close();
                            fw.close();
                            fw = new FileWriter(f6, true);
                            pw = new PrintWriter(fw, true);
                            pw.println("Transfered amount to you: " + amount);
                            pw.println("New Balance: " + t.getTransferredTo().getBalance());
                            pw.println(t.getType());
                            pw.close();
                            fw.close();
                            JOptionPane.showMessageDialog(dg, "Transaction completed");
                        }

                    } else {
                        JOptionPane.showMessageDialog(dg, "The account you are trying to "
                                + "transfer money to, does't exist");
                    }
                } catch (NullPointerException e1) {
                    JOptionPane.showMessageDialog(dg, e1.getMessage());
                } catch (NumberFormatException e2) {
                    JOptionPane.showMessageDialog(dg, "You didn't enter a number");
                } catch (FileNotFoundException e3) {
                    JOptionPane.showMessageDialog(dg, "File not found");
                } catch (IOException e4) {
                    JOptionPane.showMessageDialog(dg, "IO Exception occurred");
                } catch (NegativeMoney e5) {
                    JOptionPane.showMessageDialog(dg, e5.getMessage());
                } catch (InsufficientBalance e6) {
                    JOptionPane.showMessageDialog(dg, e6.getMessage());
                } catch (Exception e7) {
                    JOptionPane.showMessageDialog(dg, e7.getMessage());
                }
            }
        }
        );
    }
}
