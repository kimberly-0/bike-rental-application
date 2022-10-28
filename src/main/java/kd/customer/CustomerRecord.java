package kd.customer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public final class CustomerRecord implements Customer {

    private final CustomerName customerName;
    private final Date dateOfBirth;
    private final CustomerNumber customerNumber;
    private final Date issueDate;
    private boolean goldClass;

    /**
     * Create a customer record with name, date of birth, customer number, and Gold
     * Class status
     *
     * @param firstName   The customer's first name
     * @param lastName    The customer's first name
     * @param dateOfBirth The customer's date of birth
     * @param goldClass   Whether the customer is Gold Class or not
     * 
     * @throws NullPointerException if either <code>name</code>,
     *                              <code>dateOfBirth</code> or <code>number</code>
     *                              is null
     */
    public CustomerRecord(String firstName, String lastName, String dateOfBirth, boolean goldClass)
            throws ParseException {

        if (firstName == null || lastName == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }

        if (dateOfBirth == null) {
            throw new IllegalArgumentException("Date of birth cannot be null");
        }

        this.customerName = new CustomerName(firstName, lastName);

        this.dateOfBirth = new SimpleDateFormat("dd/MM/yyyy").parse(dateOfBirth);

        this.customerNumber = CustomerNumber.getInstance(new CustomerName(firstName, lastName),
                new SimpleDateFormat("dd/MM/yyyy").parse(dateOfBirth));

        this.issueDate = Calendar.getInstance().getTime();

        this.goldClass = goldClass;
    }

    /**
     * @return a CustomerName object with the full name of the customer
     */
    public CustomerName getName() {
        return new CustomerName(customerName.getFirstName(), customerName.getLastName());
    }

    /**
     * @return the age of the customer
     */
    public int getAge() {
        int age = 0;
        LocalDate today = LocalDate.now();
        LocalDate dob = LocalDate.ofInstant(dateOfBirth.toInstant(), ZoneId.systemDefault());

        if ((dob != null) && (today != null)) {
            age = Period.between(dob, today).getYears();
        }
        return age;
    }

    /**
     * @return a CustomerNumber object with the unique customer number
     */
    public CustomerNumber getCustomerNumber() {
        return customerNumber;
    }

    /**
     * @return the date of issue of the customer record
     */
    public Date getRecordIssueDate() {
        return (Date) issueDate.clone();
    }

    /**
     * @return <code>true</code> if the customer has Gold Class status
     *         <code>false</code> if the customer does not have Gold Class status
     */
    public boolean isGoldClass() {
        return goldClass;
    }

    /**
     * @return a string representation of the customer's record
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Customer Record {\n\t" +
                "customer name: " + customerName + "\n\t" +
                "date of birth: " + dateOfBirth + "\n\t" +
                "customer number: " + customerNumber + "\n\t" +
                "record issue date: " + issueDate + "\n\t" +
                "gold class: " + goldClass + "\n" +
                '}';
    }
}
