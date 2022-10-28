package kd.bike;

/**
 * The RoadBike class is responsible for creating a road bike object.
 */
public final class RoadBike extends BikeFactory {

    /**
     * @see BikeFactory#BikeFactory(String)
     */
    public RoadBike(String serialNumber) {
        super(serialNumber);
    }

}
