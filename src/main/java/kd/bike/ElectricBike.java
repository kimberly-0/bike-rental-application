package kd.bike;

/**
 * The ElectricBike class is responsible for creating a electric bike object.
 */
public final class ElectricBike extends BikeFactory {

    private int batteryLevel; // 0 is empty, 100 is full

    /**
     * @see BikeFactory#BikeFactory(String)
     */
    public ElectricBike(String serialNumber) {
        super(serialNumber);
        this.batteryLevel = 100;
    }

    /**
     * Returns whether or not the bike's battery is currently full
     *
     * @return true if the bike's battery is full
     * @return false if the bike's battery is empty
     */
    public boolean getBatteryFull() {
        if (batteryLevel == 100) {
            return true;
        }
        return false;
    }

    /**
     * Set the bike's battery status to full
     */
    public void setBatteryFull() {
        this.batteryLevel = 100;
    }

    /**
     * Set the bike's battery status to empty
     */
    public void setBatteryEmpty() {
        this.batteryLevel = 0;
    }

    /**
     * @return a string representation of the bike containing the serial number,
     *         type, rental status and battery level
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Bike {\n\t" +
                "serial number: " + this.getSerialNumber() + "\n\t" +
                "type: " + this.getClass().getSimpleName() + "\n\t" +
                "rented: " + (this.getIsRented() ? "yes" : "no") + "\n\t" +
                "battery level: " + batteryLevel + "%" + "\n" +
                "}";
    }
}
