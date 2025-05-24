// Code from -> https://github.com/buildrun-tech/buildrun-law-of-demeter
package goodcode;

import common.OrderFactory;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyGoodCode {

    private static final Logger logger = Logger.getLogger(MyGoodCode.class.getName());

    public static void main(String[] args) {
        var order = OrderFactory.build();

        var deliveryDate =
                order.getDeliveryDate();

        logDeliveryDate(deliveryDate);
    }

    private static void logDeliveryDate(LocalDate deliveryDate) {
        logger.log(Level.INFO,
                "Delivery Date: {0}",
                deliveryDate
        );
    }

}
