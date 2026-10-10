class PaymentInstrument {
    private String id;
    private String currency;
    protected boolean isActive;

    public PaymentInstrument(String id, String currency) {
        this.id = id;
        this.currency = currency;
        this.isActive = true;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public boolean getIsActive() { return isActive; }
    public void setIsActive(boolean isActive) { this.isActive = isActive; }

    public void block() {
        this.isActive = false;
        System.out.println("Инструмент заблокирован.");
    }

    public void activate() {
        this.isActive = true;
        System.out.println("Инструмент активирован.");
    }

    public void printDetails() {
        System.out.println("ID: " + id + ", Валюта: " + currency + ", Активен: " + isActive);
    }
}

class BankCard extends PaymentInstrument {
    private String cardNumber;
    private String holderName;
    private double balance;

    public BankCard(String id, String currency, String cardNumber, String holderName, double balance) {
        super(id, currency);
        this.cardNumber = cardNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public String getHolderName() { return holderName; }
    public void setHolderName(String holderName) { this.holderName = holderName; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public void topUp(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("Пополнение на " + amount + ". Новый баланс: " + this.balance);
        }
    }

    public boolean withdraw(double amount) {
        if (amount <= this.balance) {
            this.balance -= amount;
            System.out.println("Снято: " + amount + ". Остаток: " + this.balance);
            return true;
        }
        System.out.println("Недостаточно средств!");
        return false;
    }

    public void showCardHolder() {
        System.out.println("Владелец карты: " + holderName.toUpperCase());
    }
}

class CreditCard extends BankCard {
    private double creditLimit;
    private double currentDebt;
    private double interestRate;

    public CreditCard(String id, String currency, String cardNumber, String holderName, 
                      double balance, double creditLimit, double interestRate) {
        super(id, currency, cardNumber, holderName, balance);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
        this.currentDebt = 0.0;
    }

    public double getCreditLimit() { return creditLimit; }
    public void setCreditLimit(double creditLimit) { this.creditLimit = creditLimit; }

    public double getCurrentDebt() { return currentDebt; }
    public void setCurrentDebt(double currentDebt) { this.currentDebt = currentDebt; }

    public double getInterestRate() { return interestRate; }
    public void setInterestRate(double interestRate) { this.interestRate = interestRate; }

    public void buyOnCredit(double price) {
        double availableMoney = getBalance() + (creditLimit - currentDebt);
        if (price <= availableMoney) {
            this.currentDebt += price;
            System.out.println("Покупка в кредит на " + price + " успешна.");
        } else {
            System.out.println("Превышен кредитный лимит!");
        }
    }

    public void repayDebt(double amount) {
        if (amount <= currentDebt) {
            currentDebt -= amount;
        } else {
            currentDebt = 0;
        }
        System.out.println("Долг погашен. Текущий долг: " + currentDebt);
    }

    public void chargeInterest() {
        if (currentDebt > 0) {
            double fee = currentDebt * (interestRate / 100);
            currentDebt += fee;
            System.out.println("Начислены проценты: " + fee + ". Новый долг: " + currentDebt);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        CreditCard myCard = new CreditCard(
            "TX-990", "RUB", "4276-1234-5678-9012", "Ivan Ivanov", 
            5000.0, 50000.0, 12.5
        );

        myCard.printDetails(); 

        myCard.showCardHolder();
        myCard.topUp(1500);
        myCard.withdraw(2000);

        myCard.buyOnCredit(10000);
        myCard.chargeInterest();
        myCard.repayDebt(5000);
        
        myCard.block();
    }
}
