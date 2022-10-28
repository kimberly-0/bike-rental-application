package kd.customer;

public final class CustomerName {

    private String firstName;
    private String lastName;

    /**
     * Construct a CustomerName from the specified first and last names
     *
     * @param firstName The first name of the customer
     * @param lastName  The last name of the customer
     * 
     * @throws NullPointerException     if either
     *                                  <code>firstName</code> or
     *                                  <code>lastName</code> is
     *                                  null
     * @throws IllegalArgumentException if either
     *                                  <code>firstName</code> or
     *                                  <code>lastName</code> is
     *                                  empty
     */
    public CustomerName(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

    /**
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the first name
     *
     * @param firstName The customer's first name
     * 
     * @throws NullPointerException     if
     *                                  <code>firstName</code> is null
     * @throws IllegalArgumentException if
     *                                  <code>firstName</code> is empty
     */
    private void setFirstName(String firstName) {
        if (firstName.length() == 0)
            throw new IllegalArgumentException("Empty first name");

        this.firstName = firstName;
    }

    /**
     * Set the last name
     *
     * @param lastName The customer's last name
     * 
     * @throws NullPointerException     if
     *                                  <code>lastName</code> is null
     * @throws IllegalArgumentException if
     *                                  <code>lastName</code> is empty
     */
    private void setLastName(String lastName) {
        if (lastName.length() == 0)
            throw new IllegalArgumentException("Empty last name");

        this.lastName = lastName;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof CustomerName))
            return false;

        final CustomerName customerName = (CustomerName) obj;

        return firstName.equals(customerName.firstName)
                && lastName.equals(lastName);
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hc = 17;
        hc = 37 * hc + firstName.hashCode();
        return 37 * hc + lastName.hashCode();
    }

    /**
     * Returns a string representation of the customer's name. The string
     * representation is a first name and last name separated by a space character.
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    /**
     * Constructs an instance of CustomerName from its
     * string representation. The string representation
     * of a CustomerName is a first name and last name separated
     * by a space character. It is assumed that first and last
     * names do not themselves contain spaces.
     *
     * @param name a name in the specified string representation
     * @return an instance of a CustomerName corresponding to the given string
     * @throws NullPointerException           if <code>name</code> is null
     * @throws ArrayIndexOutOfBoundsException if there are not
     *                                        two component parts to
     *                                        <code>name</code> (first and last
     *                                        names)
     */
    public static CustomerName valueOf(String name) {
        final String[] parts = name.split(" ");

        return new CustomerName(parts[0], parts[1]);
    }
}
