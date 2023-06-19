package LoanStorage;

import java.time.LocalDate;

public class Loan {
	private String loanId;
    private String customerId;
    private String lenderId;
    private double amount;
    private double remainingAmount;
    private LocalDate paymentDate;
    private double interestPerDay;
    private LocalDate dueDate;
    private double penaltyPerDay;
	public String getLoanId() {
		return loanId;
	}
	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getLenderId() {
		return lenderId;
	}
	public void setLenderId(String lenderId) {
		this.lenderId = lenderId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getRemainingAmount() {
		return remainingAmount;
	}
	public void setRemainingAmount(double remainingAmount) {
		this.remainingAmount = remainingAmount;
	}
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
	public double getInterestPerDay() {
		return interestPerDay;
	}
	public void setInterestPerDay(double interestPerDay) {
		this.interestPerDay = interestPerDay;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public double getPenaltyPerDay() {
		return penaltyPerDay;
	}
	public void setPenaltyPerDay(double penaltyPerDay) {
		this.penaltyPerDay = penaltyPerDay;
	}
    
    
	public boolean isValidPaymentDate() {
        return paymentDate.isBefore(dueDate) || paymentDate.isEqual(dueDate);
    }

    public boolean isDueDatePassed() {
        return LocalDate.now().isAfter(dueDate);
    }

    public double calculateInterest() {
        int days = (int) Math.max(0, LocalDate.now().toEpochDay() - dueDate.toEpochDay());
        return amount * interestPerDay * days;
    }

    public double calculatePenalty() {
        int days = (int) Math.max(0, LocalDate.now().toEpochDay() - dueDate.toEpochDay());
        return remainingAmount * penaltyPerDay * days;
    }
	public Loan(String loanId, String customerId, String lenderId, double amount, double remainingAmount,
			LocalDate paymentDate, double interestPerDay, LocalDate dueDate, double penaltyPerDay) {
		super();
		this.loanId = loanId;
		this.customerId = customerId;
		this.lenderId = lenderId;
		this.amount = amount;
		this.remainingAmount = remainingAmount;
		this.paymentDate = paymentDate;
		this.interestPerDay = interestPerDay;
		this.dueDate = dueDate;
		this.penaltyPerDay = penaltyPerDay;
	}
    
    

}
