package LoanStorage;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoanStoreTest {
	
	private LoanStore loanStore;
	private Loan loan1;
	private Loan loan2;
	private Loan loan3;
	private Loan loan4;
	
	@BeforeEach
	void setup() {
	    loanStore = new LoanStore();

	    loan1 = new Loan("L1", "C1", "LEN1", 10000, 10000, LocalDate.of(2023, 6, 5), 0.0001, LocalDate.of(2023, 7, 5), 0.0001);
	    loan2 = new Loan("L2", "C1", "LEN1", 20000, 5000, LocalDate.of(2023, 6, 1), 0.0001, LocalDate.of(2023, 8, 5), 0.0001);
	    loan3 = new Loan("L3", "C2", "LEN2", 50000, 30000, LocalDate.of(2023, 4, 4), 0.0002, LocalDate.of(2023, 5, 4), 0.0002);
	    loan4 = new Loan("L4", "C3", "LEN2", 50000, 30000, LocalDate.of(2023, 4, 4), 0.0002, LocalDate.of(2023, 5, 4), 0.0002);
	}
	
	@Test
	void testAddLoanValidPaymentDate() {
	    loanStore.addLoan(loan1);
	    Assertions.assertEquals(1, loanStore.getLoans().size());
	}
	
	@Test
	void testAddLoanInvalidPaymentDate() {
	    Assertions.assertThrows(IllegalArgumentException.class, () -> {
	        loanStore.addLoan(loan2);
	    });
	}

	@Test
	void testAggregateRemainingAmountByLender() {
	    loanStore.addLoan(loan1);
	    loanStore.addLoan(loan3);

	    double totalRemainingAmount = loanStore.aggregateRemainingAmountByLender("LEN1");
	    Assertions.assertEquals(15000, totalRemainingAmount);
	}
	@Test
	void testAggregateInterestByLender() {
	    loanStore.addLoan(loan1);
	    loanStore.addLoan(loan3);

	    double totalInterest = loanStore.aggregateInterestByLender("LEN1");
	    Assertions.assertEquals(10, totalInterest);
	}

	@Test
	void testAggregateInterestByCustomer() {
	    loanStore.addLoan(loan1);
	    loanStore.addLoan(loan3);
	    loanStore.addLoan(loan4);

	    double totalInterest = loanStore.aggregateInterestByCustomer("C1");
	    Assertions.assertEquals(10, totalInterest);
	}

	@Test
	void testCheckDueDatePassed() {
	    loanStore.addLoan(loan2);

	    // Simulate a delay of 10 days
	    loanStore.getLoans().get(0).setDueDate(LocalDate.now().minusDays(10));

	    Assertions.assertDoesNotThrow(() -> {
	        loanStore.checkDueDatePassed(loanStore.getLoans().get(0));
	    });
	}
}
