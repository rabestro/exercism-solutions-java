public final class BankAccount {
    private volatile int balance;
    private volatile boolean isOpen;

    private static void validateAmount(int money) throws BankAccountActionInvalidException {
        if (money < 0) {
            throw new BankAccountActionInvalidException("Cannot deposit or withdraw negative amount");
        }
    }

    public void open() throws BankAccountActionInvalidException {
        ensureAccountIsNotOpen();
        balance = 0;
        isOpen = true;
    }

    public int getBalance() throws BankAccountActionInvalidException {
        ensureAccountIsOpen();
        return balance;
    }

    public synchronized void deposit(int money) throws BankAccountActionInvalidException {
        ensureAccountIsOpen();
        validateAmount(money);
        balance += money;
    }

    public synchronized void withdraw(int money) throws BankAccountActionInvalidException {
        validateAmount(money);
        ensureAccountIsOpen();
        ensureAccountIsNotEmpty();
        ensureBalanceEnough(money);
        balance -= money;
    }

    private void ensureBalanceEnough(int money) throws BankAccountActionInvalidException {
        if (money > balance) {
            throw new BankAccountActionInvalidException("Cannot withdraw more money than is currently in the account");
        }
    }

    public void close() throws BankAccountActionInvalidException {
        ensureAccountIsNotClosed();
        isOpen = false;
    }

    private void ensureAccountIsNotOpen() throws BankAccountActionInvalidException {
        if (isOpen) {
            throw new BankAccountActionInvalidException("Account already open");
        }
    }

    private void ensureAccountIsNotEmpty() throws BankAccountActionInvalidException {
        if (balance == 0) {
            throw new BankAccountActionInvalidException("Cannot withdraw money from an empty account");
        }
    }

    private void ensureAccountIsOpen() throws BankAccountActionInvalidException {
        if (!isOpen) {
            throw new BankAccountActionInvalidException("Account closed");
        }
    }

    private void ensureAccountIsNotClosed() throws BankAccountActionInvalidException {
        if (!isOpen) {
            throw new BankAccountActionInvalidException("Account not open");
        }
    }
}
