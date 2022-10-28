package kd.bike;

/**
 * This interface defines a contract for exposed behaviour for classes that
 * implement the Bike interface. These methods must appear in the subclasses'
 * source code for it to succesfully compile.
 */
public interface Bike {

    String getSerialNumber();

    boolean getIsRented();

    void setRented();

    void setAvailable();
}