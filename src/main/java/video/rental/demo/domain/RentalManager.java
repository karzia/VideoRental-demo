package video.rental.demo.domain;

import java.util.*;

public class RentalManager {

//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private static List<Rental> rentals = new ArrayList<Rental>();

    private static HashMap<Integer, List<Rental>> rentalMap = new HashMap<>();
    public static List<Rental> getRentals(Customer customer){
        int code = customer.getCode();
        if(!rentalMap.containsKey(code))
            rentalMap.put(code, new ArrayList<>());

        return rentalMap.get(customer.getCode());
//        return customer.rentals;
    }

    public static void setRentals(Customer customer, List<Rental> rentals) {
        rentalMap.put(customer.getCode(),rentals);
//        customer.rentals = rentals;
    }
    public static void addRental(Rental rental){
        int code = rental.getCustomer().getCode();
        if(!rentalMap.containsKey(code))
            rentalMap.put(code, new ArrayList<>());

        rentalMap.get(code).add(rental);
    }
}
