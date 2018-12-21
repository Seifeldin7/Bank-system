package bms;

public class Transaction {

    Account defaultAccount;
    Account transferredFrom;
    Account transferredTo;
    String type;

    public Transaction() {
    }

    public Transaction(Account defaultAccount, String type) {
        this.defaultAccount = defaultAccount;
        this.type = type;
    }

    public Transaction(Account transferredFrom, Account transferredTo) {
        this.transferredFrom = transferredFrom;
        this.transferredTo = transferredTo;
        this.type = "Money Transfer";
    }

    public Account getDefaultAccount() {
        return defaultAccount;
    }

    public Account getTransferredFrom() {
        return transferredFrom;
    }

    public Account getTransferredTo() {
        return transferredTo;
    }

    public String getType() {
        return type;
    }

    public void setDefaultAccount(Account defaultAccount) {
        this.defaultAccount = defaultAccount;
    }

    public void setTransferredFrom(Account transferredFrom) {
        this.transferredFrom = transferredFrom;
    }

    public void setTransferredTo(Account transferredTo) {
        this.transferredTo = transferredTo;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        if (!(type.equals("Money Transfer"))) {
            return "Default: " + defaultAccount + "\n" + "Type: " + type + "\n";
        } else {
            return "Transferred From: " + transferredFrom + "\n" + "Transferred To: "
                    + transferredTo + "\nType: Money Transfer\n";
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Transaction)) {
            return false;
        }
        Transaction t = (Transaction) obj;
        return t.defaultAccount.equals(defaultAccount)
                && t.transferredFrom.equals(transferredFrom)
                && t.transferredTo.equals(transferredTo) && t.type.equals(type);
    }

    public void withdraw(int amount) throws InsufficientBalance, NegativeMoney {
        int balance = defaultAccount.getBalance();
        if (amount < 0) {
            throw new NegativeMoney("Can't pull negative money");
        }
        if (defaultAccount.isOpen() && amount <= balance) {
            defaultAccount.setBalance(balance - amount);
        } else {
            throw new InsufficientBalance("Action can't be performed. You're trying to"
                    + " pull an amount of money greater than your balance.");
        }
    }

    public void deposit(int amount) throws NegativeMoney {
        if (amount < 0) {
            throw new NegativeMoney("Can't put negative money");
        }
        if (defaultAccount.isOpen()) {
            int balance = defaultAccount.getBalance();
            defaultAccount.setBalance(balance + amount);
        }
    }

    public double inquiry() {
        return defaultAccount.getBalance();
    }

    public void moneyTransfer(int amount) throws NegativeMoney, InsufficientBalance {
        if(amount < 0)
            throw new NegativeMoney("Can't transfer negative money");
        int balance1 = transferredFrom.getBalance();
        if (transferredFrom.isOpen() && transferredTo.isOpen() && amount <= balance1) {
            int balance2 = transferredTo.getBalance();
            transferredFrom.setBalance(balance1 - amount);
            transferredTo.setBalance(balance2 + amount);
        } else {
            throw new InsufficientBalance("The amount you're trying to transfer is greater"
                    + "than your balance");
        }
    }

    public void requestLoan() throws LoanException {
        if (defaultAccount.isOnLoan()) {
            throw new LoanException("You're already on loan");
        } else if (defaultAccount.getBalance() <= 1000 && defaultAccount.isOpen()) {
            defaultAccount.setBalance(defaultAccount.getBalance() + 20000);
            defaultAccount.setOnLoan(true);
        } else if (defaultAccount.getBalance() > 1000) {
            throw new LoanException("Your balance is greater than 1000. Can't request loan");
        } else {
            throw new LoanException("Account is closed");
        }
    }

    public void payLoan() throws LoanException {
        if (!defaultAccount.isOnLoan()) {
            throw new LoanException("You're not on loan");
        } else if (defaultAccount.getBalance() >= 20000 && defaultAccount.isOpen()) {
            defaultAccount.setBalance(defaultAccount.getBalance() - 20000);
            defaultAccount.setOnLoan(false);
        } else if (defaultAccount.getBalance() < 20000) {
            throw new LoanException("Can't pay loan due to insufficient balance");
        } else {
            throw new LoanException("Account is closed");
        }
    }
}
