package kd.customer;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class CustomerNumber {

    private static final Map<String, CustomerNumber> CUSTOMER_NUMBERS = new HashMap<String, CustomerNumber>();
    private final String strRep;

    private CustomerNumber(String strRep) {
        this.strRep = strRep;
    }

    /**
     * Return a CustomerNumber object of the customer number consisting of the
     * customer's initials, the year the record was issued and an arbitrary serial
     * number which guarantees uniqueness.
     *
     * @param name            The customer name
     * @param recordIssueDate The issue date of the record
     * 
     * @return a unique customer number
     * @throws NullPointerException     if name or recordIssueDate is null
     * @throws IllegalArgumentException if name or recordIssueDate is null
     */
    public static CustomerNumber getInstance(CustomerName name, Date recordIssueDate) {

        if (name == null)
            throw new IllegalArgumentException("Empty customer name");

        if (recordIssueDate == null)
            throw new IllegalArgumentException("Empty record issue date");

        String firstNameInitial = name.getFirstName().substring(0, 1);
        String lastNameInitial = name.getLastName().substring(0, 1);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(recordIssueDate);
        int recordIssueYear = calendar.get(Calendar.YEAR);

        String part1 = firstNameInitial + lastNameInitial + "-" + recordIssueYear + "-";
        int part2 = 1;

        CustomerNumber n = null;

        boolean newCNCreated = false;

        while (!newCNCreated) {
            n = CUSTOMER_NUMBERS.get(part1 + part2);
            if (n == null) {
                n = new CustomerNumber(part1 + part2); // unique instance
                CUSTOMER_NUMBERS.put(part1 + part2, n); // add instance to HashMap
                newCNCreated = true; // stop the while loop
            }
            part2++;
        }
        return n;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof CustomerNumber))
            return false;

        final CustomerNumber customerNumber = (CustomerNumber) obj;

        return strRep.equals(customerNumber.strRep);
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hc = 17;
        return 37 * hc + strRep.hashCode();
    }

    /**
     * Returns a string representation of the customer number.
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return strRep;
    }

}
