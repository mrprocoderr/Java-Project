import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public double checkBalance() {
        return balance;
    }
}

class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("ATM Menu:");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    public void run() {
        while (true) {
            displayMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    if (account.withdraw(withdrawAmount)) {
                        System.out.println("Withdrawal successful. Remaining balance: " + account.checkBalance());
                    } else {
                        System.out.println("Withdrawal failed. Insufficient funds or invalid amount.");
                    }
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    if (account.deposit(depositAmount)) {
                        System.out.println("Deposit successful. Current balance: " + account.checkBalance());
                    }
                   else {
                        System.out.println("Deposit failed. Invalid deposit amount.");
                    }
                    break;

                case 3:
                    System.out.println("Current balance: " + account.checkBalance());
                    break;
                case 4:
                    System.out.println("Exiting ATM. Thank you!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
    }
}

public class BankManagementSystem {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000); // Initial balance of 1000
        ATM atm = new ATM(account);
        atm.run();
    }
}
