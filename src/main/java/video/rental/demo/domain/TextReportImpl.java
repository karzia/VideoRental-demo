package video.rental.demo.domain;

import java.util.List;

public class TextReportImpl implements IReport {
    @Override
    public String getReport(Customer customer) {
        StringBuilder builder = new StringBuilder("Customer Report for " + customer.getName() + "\n");

        List<Rental> rentals = customer.getRentals();

        double totalCharge = 0;
        int totalPoint = 0;

        for (Rental rental : rentals) {
            double eachCharge = 0;
            int eachPoint = 0;
            int daysRented = 0;

            daysRented = rental.getDaysRented();

            switch (rental.getVideo().getPriceCode()) {
                case Video.REGULAR:
                    eachCharge += 2;
                    if (daysRented > 2)
                        eachCharge += (daysRented - 2) * 1.5;
                    break;
                case Video.NEW_RELEASE:
                    eachCharge = daysRented * 3;
                    break;
                case Video.CHILDREN:
                    eachCharge += 1.5;
                    if (daysRented > 3)
                        eachCharge += (daysRented - 3) * 1.5;
                    break;
            }

            eachPoint++;
            if ((rental.getVideo().getPriceCode() == Video.NEW_RELEASE))
                eachPoint++;

            if (daysRented > rental.getDaysRentedLimit())
                eachPoint -= Math.min(eachPoint, rental.getVideo().getLateReturnPointPenalty());

            builder.append("\t" + rental.getVideo().getTitle() + "\tDays rented: " + daysRented + "\tCharge: " + eachCharge
                    + "\tPoint: " + eachPoint + "\n");

            totalCharge += eachCharge;
            totalPoint += eachPoint;
        }

        builder.append("Total charge: " + totalCharge + "\tTotal Point:" + totalPoint + "\n");

        if (totalPoint >= 10) {
            builder.append("Congrat! You earned one free coupon\n");
        }
        if (totalPoint >= 30) {
            builder.append("Congrat! You earned two free coupon\n");
        }
        return builder.toString();
    }
}
