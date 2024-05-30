import java.util.Scanner;

class BankAccount {
    float balance;

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public BankAccount(float initialBalance) {
        this.balance = initialBalance;
    }

    public boolean deposit(float amount) {
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

    public float getBalance() {
        return balance;
    }
}

class ATM {
    private BankAccount bankAccount;
    private long lastActivityTime;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        this.lastActivityTime = System.currentTimeMillis();
    }

    public void displayOptions() {
        System.out.println("ATM Interface");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    public void checkBalance() {
        double balance = bankAccount.getBalance();
        System.out.printf("Your current balance is: %.3f%n", balance);
        thankYouMessage();
    }

    public void deposit(float amount) {
        if (bankAccount.deposit(amount)) {
            System.out.printf("Successfully deposited %.3f%n", amount);
            thankYouMessage();
        } else {
            System.out.println("Failed to deposit. Please enter a valid amount.");
        }
    }

    public void withdraw(double amount) {
        if (bankAccount.withdraw(amount)) {
            thankYouMessage();
        } else {
            System.out.println("Failed to withdraw. Please check your balance and enter a valid amount.");
        }
    }

    public void checkAutoExit() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastActivityTime > 20000) {  // 20 seconds inactivity threshold
            System.out.println("Exiting due to inactivity. Thank you for visiting our ATM!");
            System.exit(0);
        }
        lastActivityTime = currentTime;
    }

    private void thankYouMessage() {
        System.out.println("Thank you for visiting our ATM!");
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount userAccount = new BankAccount((float) 100000.0);
        final ATM atm = new ATM(userAccount);

        while (true) {
            atm.displayOptions();
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            atm.checkAutoExit();  // Check for auto exit due to inactivity

            switch (choice) {
                case "1":
                    atm.checkBalance();
                    break;
                case "2":
                    System.out.print("Enter amount to deposit: ");
                    float depositAmount = scanner.nextFloat();
                    scanner.nextLine();  // Consume newline
                    atm.deposit(depositAmount);
                    break;
                case "3":
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    scanner.nextLine();  // Consume newline
                    atm.withdraw(withdrawAmount);
                    break;
                case "4":
                    System.out.println("Exiting the ATM. Thank you!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
  }
}
}