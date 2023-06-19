package LoanStorage;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.ConsoleHandler;

public class LoanStore {
	 private List<Loan> loans;
	    private static final Logger logger = Logger.getLogger(LoanStore.class.getName());
	    
	    
	    public LoanStore() {
	        loans = new ArrayList<>();
	        setupLogger();
	    }
	    public void addLoan(Loan loan) {
	        if (loan.isValidPaymentDate()) {
	            loans.add(loan);
	            checkDueDatePassed(loan);
	        } else {
	            throw new IllegalArgumentException("Invalid payment date. Payment date can't be greater than the due date.");
	        }
	    }
	    public List<Loan> getLoans() {
	        return loans;
	    }
	    
	    public double aggregateRemainingAmountByLender(String lenderId) {
	        double totalRemainingAmount = 0.0;
	        for (Loan loan : loans) {
	            if (loan.getLenderId().equals(lenderId)) {
	                totalRemainingAmount += loan.getRemainingAmount();
	            }
	        }
	        return totalRemainingAmount;
	    }
	    
	    public double aggregateInterestByLender(String lenderId) {
	        double totalInterest = 0.0;
	        for (Loan loan : loans) {
	            if (loan.getLenderId().equals(lenderId)) {
	                totalInterest += loan.calculateInterest();
	            }
	        }
	        return totalInterest;
	    }
	    
	    
	    public double aggregateInterestByCustomer(String customerId) {
	        double totalInterest = 0.0;
	        for (Loan loan : loans) {
	            if (loan.getCustomerId().equals(customerId)) {
	                totalInterest += loan.calculateInterest();
	            }
	        }
	        return totalInterest;
	    }

	  public  void checkDueDatePassed(Loan loan) {
	        if (loan.isDueDatePassed()) {
	            logger.log(Level.WARNING, "Loan with ID " + loan.getLoanId() + " has crossed the due date.");
	        }
	    }
	    private void setupLogger() {
	        ConsoleHandler consoleHandler = new ConsoleHandler();
	        consoleHandler.setLevel(Level.WARNING);
	        consoleHandler.setFormatter(new SimpleFormatter());

	        logger.addHandler(consoleHandler);
	        logger.setLevel(Level.WARNING);
	    }

}
