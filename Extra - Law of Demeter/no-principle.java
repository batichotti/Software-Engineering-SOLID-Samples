// Code from -> https://github.com/buildrun-tech/buildrun-law-of-demeter
package badcode;

import common.OrderFactory;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyBadCode {

    private static final Logger logger = Logger.getLogger(MyBadCode.class.getName());

    public static void main(String[] args) {

        var order = OrderFactory.build();

        var deliveryDate =
                order.getShipment()
                        .getDeliveryDetail()
                        .getDeliveryDate();

        logDeliveryDate(deliveryDate);
    }


    private static void logDeliveryDate(LocalDate deliveryDate) {
        logger.log(Level.INFO,
                "Delivery Date: {0}",
                deliveryDate
        );
    }
}
