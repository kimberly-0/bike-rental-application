package kd.customer;

import java.util.Date;

/**
 * This interface defines a contract for exposed behaviour for classes that
 * implement the Customer interface. These methods must appear in the
 * subclasses' source code for it to succesfully compile.
 */
public interface Customer {

    CustomerName getName();

    int getAge();

    CustomerNumber getCustomerNumber();

    Date getRecordIssueDate();

    boolean isGoldClass();

}