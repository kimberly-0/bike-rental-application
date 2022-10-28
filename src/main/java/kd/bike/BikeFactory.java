package kd.bike;

import java.util.HashMap;
import java.util.Map;

/**
 * The BikeFactory class is responsible for returning an instance of one of its
 * subclasses; ElectricBike and RoadBike.
 */
public abstract class BikeFactory implements Bike {

    // Bikes record
    private static final Map<String, Bike> BIKES = new HashMap<String, Bike>();

    // Bike types
    public static final String ROAD_BIKE = "road";
    public static final String ELECTRIC_BIKE = "electric";

    // Bike properties
    private final String serialNumber;
    private boolean rented;

    // Bike constructor
    BikeFactory(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * Get a bike instance of the specified type with the specified serial number
     * number
     *
     * @param bikeType The type of bike
     * @param sNum     The bike's serial number
     * 
     * @return a bike instance of the specified type (an existing bike
     *         is returned if serial number is already known, otherwise
     *         a new bike with the given serial number is returned)
     * 
     * @throws NullPointerException     if bikeType is null
     * @throws IllegalArgumentException if bikeType is an invalid bike type
     */
    public static Bike getInstance(String bikeType, String sNum) {

        /*
         * If serial mumber is empty, throw illegal argument exception
         */
        if (sNum.length() == 0) {
            throw new IllegalArgumentException("Empty serial number");
        }

        /*
         * If bike already exists in records, return existing bike
         */
        Bike bike = BIKES.get(sNum);
        if (bike != null) {
            return bike;
        }

        /*
         * Create new bike of the specified bike type
         */
        if (bikeType.equals(ROAD_BIKE)) {
            bike = new RoadBike(sNum);
        } else if (bikeType.equals(ELECTRIC_BIKE)) {
            bike = new ElectricBike(sNum);
        } else {
            throw new IllegalArgumentException("Invalid bike type: " + bikeType);
        }

        /*
         * Add bike to records
         */
        BIKES.put(sNum, bike);

        return bike;
    }

    /**
     * @return bike's serial number
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * @return true if bike has been rented out
     * @return false if bike has not been rented out
     */
    public boolean getIsRented() {
        return rented;
    }

    /**
     * Set the bike's rental status to rented
     */
    public void setRented() {
        this.rented = true;
    }

    /**
     * Set the bike's rental status to available
     */
    public void setAvailable() {
        this.rented = false;
    }

    /**
     * @return a string representation of the bike containing the serial number,
     *         type and rental status
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Bike {\n\t" +
                "serial number: " + serialNumber + "\n\t" +
                "type: " + this.getClass().getSimpleName() + "\n\t" +
                "rented: " + (rented ? "yes" : "no") + "\n" +
                "}";
    }
}
