package bms;

public class Account {

    private int id;
    private Client client;
    private int balance;
    private boolean open;
    private boolean onLoan;

    public Account() {
    }

    public Account(int id, Client client, int balance, boolean open, boolean onLoan) {
        this.id = id;
        this.client = client;
        this.balance = balance;
        this.open = open;
        this.onLoan = onLoan;
    }
    
    public Account(int id, Client client, int balance) {
        this.id = id;
        this.client = client;
        this.balance = balance;
        this.open = true;
        this.onLoan = false;
    }
    
    public Account(int id, Client client){
        this.id = id;
        this.client = client;
        this.balance = 0;
        this.open = true;
        this.onLoan = false;
    }
    
    public Account(int id, Client client, int balance, boolean onLoan){
        this.id = id;
        this.client = client;
        this.balance = balance;
        this.open = true;
        this.onLoan = onLoan;
    }
    
    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public int getBalance() {
        return balance;
    }

    public boolean isOpen() {
        return open;
    }

    public boolean isOnLoan() {
        return onLoan;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void setOnLoan(boolean onLoan) {
        this.onLoan = onLoan;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" + "Client: " + client + "\n" + "Balance: " + balance
                + "\n" + "Open: " + open + "\n" + "On Loan: " + onLoan;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Account)) {
            return false;
        }
        Account a = (Account) obj;
        return a.id == id;
    }
}