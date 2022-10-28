package kd;

import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import kd.bike.*;
import kd.customer.*;

/**
 * The RentalManager class is responsible for managing the bike rentals.
 */
public abstract class RentalManager {

    private static Set<CustomerRecord> customerRecords = new HashSet<CustomerRecord>();
    private static Set<Bike> bikes = new HashSet<Bike>();
    private static Map<String, Bike> rentedBikes = new HashMap<String, Bike>();

    /**
     * Adds a new bike to the bikes collection
     *
     * @param bike The new bike to be added to the collection
     */
    public static void addBike(Bike bike) {
        bikes.add(bike);
    }

    /**
     * Adds a customer record to the records collection
     *
     * @param record The customer record to be added to the collection
     */
    public static void addCustomerRecord(CustomerRecord record) {
        customerRecords.add(record);
    }

    /**
     * @return a list of all bikes in the collection
     */
    public static Set<Bike> getAllBikes() {
        return bikes;
    }

    /**
     * @return a list of all customer records
     */
    public static Set<CustomerRecord> getAllCustomerRecords() {
        return customerRecords;
    }

    /**
     * Counts the number of bikes of a specific type available to rent
     *
     * @param typeOfBike The type of bike to check availability for
     * 
     * @return the number of bikes of the specified type available to rent
     */
    public static int getNumOfAvailableBikes(String typeOfBike) {
        int numberOfAvailableBikes = 0;

        /*
         * Set bikeClass to the class name of the bike type specified
         */
        String bikeClass = null;
        if (typeOfBike.equals("road")) {
            bikeClass = RoadBike.class.getName();
        } else if (typeOfBike.equals("electric")) {
            bikeClass = ElectricBike.class.getName();
        }

        /*
         * If a class was found for the bike type specified, count number of available
         * bikes for that type, otherwise print invalid bike type message
         */
        if (bikeClass != null) {
            for (Bike bike : bikes) {
                if (bike.getClass().getName().equals(bikeClass) && !bike.getIsRented()) {
                    numberOfAvailableBikes++;
                }
            }
        } else {
            System.out.println("Invalid bike type.");
        }

        return numberOfAvailableBikes;
    }

    /**
     * @return a list of all bikes that are currently rented out
     */
    public static Set<Bike> getRentedBikes() {
        Set<Bike> rentedBikes = new HashSet<Bike>();
        for (Bike bike : bikes) {
            if (bike.getIsRented())
                rentedBikes.add(bike);
        }
        return rentedBikes;
    }

    /**
     * Returns the bike rented out by the specified customer if any
     *
     * @param customerNumber The number of the customer that has rented the bike
     * 
     * @return the bike rented by the specified customer
     */
    public static Bike getBike(String customerNumber) {
        Bike bike = rentedBikes.get(customerNumber);
        return bike;
    }

    /**
     * Returns the customer record for the specified customer number
     *
     * @param customerNumber The number of the customer to get the record for
     * 
     * @return the customer record the specified customer
     */
    public static CustomerRecord getCustomerRecord(String customerNumber) {
        CustomerRecord customerRecord = null;
        for (CustomerRecord record : customerRecords) {
            if (customerNumber.equals(record.getCustomerNumber().toString())) {
                customerRecord = record;
                break;
            }
        }
        return customerRecord;
    }

    /**
     * Issues a bike of a given type to the specified customer
     *
     * @param customerNumber The number of the customer to issue the bike to
     * @param typeOfBike     The type of bike to be issued
     */
    public static void issueBike(String customerNumber, String typeOfBike) {

        /* Identify the customer record based on customer number */
        CustomerRecord toIssueCustomerRecord = getCustomerRecord(customerNumber);
        if (toIssueCustomerRecord == null) {
            System.out.println("No customer record found for " + customerNumber);
            return;
        }

        /* Check if customer does not have a bike on loan already */
        if (getBike(customerNumber) != null) {
            System.out.println("Customer " + customerNumber + " already has a bike on loan");
            return;
        }

        /* Validate bike type and determine eligbility */
        String bikeClass = null;
        boolean eligible = false;
        if (typeOfBike.equals("road")) {
            bikeClass = RoadBike.class.getName();
            eligible = true;
        } else if (typeOfBike.equals("electric")) {
            bikeClass = ElectricBike.class.getName();
            if (toIssueCustomerRecord.isGoldClass() && toIssueCustomerRecord.getAge() >= 21) {
                eligible = true;
            }
        } else {
            System.out.println("Invalid type of bike: " + typeOfBike);
            return;
        }

        /* Check if customer is eligbile to rent the bike type specified */
        if (!eligible) {
            System.out.println("Customer " + customerNumber + " is not eligible to rent a bike of type " + typeOfBike);
            return;
        }

        /* Select a bike to issue from available bikes */
        Bike bikeToIssue = null;
        for (Bike b : bikes) {
            if (b.getClass().getName().equals(bikeClass) && !b.getIsRented()) {
                bikeToIssue = b;
                break;
            }
        }

        /* If an available bike was found, issue bike to customer */
        if (bikeToIssue != null) {
            bikeToIssue.setRented();
            rentedBikes.put(customerNumber, bikeToIssue);

            /* If bike is electric, set battery to empty */
            if (bikeToIssue instanceof ElectricBike) {
                ElectricBike e = (ElectricBike) bikeToIssue;
                e.setBatteryEmpty();
            }

            System.out.println("Bike " + bikeToIssue.getSerialNumber() + " of type " + typeOfBike
                    + " successfully issued to customer " + toIssueCustomerRecord.getCustomerNumber()
                    + ".");
            return;
        } else {
            System.out.println("No bikes of type " + typeOfBike + " available");
            return;
        }
    }

    /**
     * Terminates the rental contract associated with the given customer number
     *
     * @param customerNumber The number of the customer to terminate the rental
     *                       contract for
     */
    public static void terminateRental(String customerNumber) {
        // System.out.println("Customer number: " + customerNumber);
        // System.out.println("Bike to terminate: " + rentedBikes.get(customerNumber));

        Bike b = rentedBikes.get(customerNumber);

        if (b != null) {
            b.setAvailable();

            if (b instanceof ElectricBike) {
                ElectricBike e = (ElectricBike) b;
                e.setBatteryFull();
            }

            rentedBikes.remove(customerNumber);
            System.out.println("Bike returned and contract succesfully terminated for customer " + customerNumber);

        } else {
            System.out.println("No rental contract found for customer " + customerNumber);
        }

    }

    /*
     * Method to debug and test the program
     */
    public static void debug() throws ParseException {
        /*
         * Testing creation and adding of bikes
         */
        final Bike[] bikesArray = new Bike[] {
                BikeFactory.getInstance("electric", "ps2345"),
                BikeFactory.getInstance("electric", "op4321"),
                BikeFactory.getInstance("electric", "lm9876"),
                BikeFactory.getInstance("electric", "ht7654"),
                BikeFactory.getInstance("road", "vr4567"),
                BikeFactory.getInstance("road", "pq6543"),
                BikeFactory.getInstance("road", "ab1234"),
                BikeFactory.getInstance("road", "ab1234"), // test uniqueness guarantee
                BikeFactory.getInstance("electric", "ab1234"), // test uniqueness guarantee
        };
        for (final Bike b : bikesArray) {
            addBike(b);
        }

        /*
         * Testing available bikes
         */
        System.out.println("Num. of road bikes available to rent: " + getNumOfAvailableBikes("road")); // = 3
        System.out.println("Num. of electric bikes available to rent: " + getNumOfAvailableBikes("electric")); // = 4
        System.out.println(getNumOfAvailableBikes("invalid type")); // Test invalid bike type

        /*
         * Testing creation and adding of customer records
         */
        CustomerRecord[] customerRecordsArray = new CustomerRecord[] {
                new CustomerRecord("Jane", "Doe", "01/02/1993", false),
                new CustomerRecord("John", "Marvel", "12/8/2008", true),
                new CustomerRecord("Kim", "Middleton", "05/10/1978", true),
                new CustomerRecord("Kim", "Middleton", "05/10/1978", false), // test uniquene number generator
        };
        for (CustomerRecord r : customerRecordsArray) {
            addCustomerRecord(r);
        }

        /*
         * Testing issuing bikes
         */
        issueBike("JD-1993-1", "road");
        issueBike("KM-1978-1", "electric");
        System.out.println("Num. of road bikes available to rent: " + getNumOfAvailableBikes("road")); // = 2
        System.out.println("Num. of electric bikes available to rent: " + getNumOfAvailableBikes("electric")); // = 3

        /*
         * Testing issuing bike to someone that already has a bike on loan (customer can
         * have max 1 bike)
         */
        issueBike("JD-1993-1", "road"); // will not work, customer already has bike on loan
        System.out.println("Num. of road bikes available to rent: " + getNumOfAvailableBikes("road")); // = 2

        /*
         * Testing issuing electric bikes to non-Gold-class customer (need to be
         * Gold-class to rent electric)
         */
        issueBike("KM-1978-2", "electric"); // will not work due to not being Gold-class
        System.out.println("Num. of electric bikes available to rent: " + getNumOfAvailableBikes("electric")); // = 3

        /*
         * Testing issuing electric bikes to Gold-class customer under 21 years old
         * (need to be min. 21 to rent electric)
         */
        issueBike("JM-2008-1", "electric"); // will not work due to not being min. 21 years of age
        System.out.println("Num. of electric bikes available to rent: " + getNumOfAvailableBikes("electric")); // = 3

        /*
         * Testing record of all bikes
         */
        System.out.println("---- ALL BIKES: ----");
        System.out.println(getAllBikes());

        /*
         * Testing record of rented bikes
         */
        System.out.println("---- RENTED BIKES: ----");
        System.out.println(getRentedBikes());

        /*
         * Testing returning of bike
         */
        terminateRental("JD-1993-1");
        System.out.println("Num. of road bikes available to rent: " + getNumOfAvailableBikes("road")); // = 3

        /*
         * Testing battery level electric bike
         */
        ElectricBike rentedElectricBike = (ElectricBike) getBike("KM-1978-1");
        System.out.println(
                "Battery level when rented out: " + (rentedElectricBike.getBatteryFull() ? "full" : "empty"));
        terminateRental("KM-1978-1");
        System.out.println(
                "Battery level after being returned: " + (rentedElectricBike.getBatteryFull() ? "full" : "empty"));

    }

    public static void main(String[] args) throws ParseException {
        debug();
    }
}
