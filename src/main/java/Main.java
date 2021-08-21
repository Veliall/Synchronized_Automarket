/**
 * Created by Igor Khristiuk
 * Date: 21.08.2021
 * Time: 17:35
 */
public class Main {

    public static void main(String[] args) {

        final AutoMarket autoMarket = new AutoMarket();

        new Thread(null, autoMarket::sellCar, "Дима").start();
        new Thread(null, autoMarket::sellCar, "Коля").start();
        new Thread(null, autoMarket::sellCar, "Егор").start();
        new Thread(null, autoMarket::sellCar, "Никита").start();
        new Thread(null, autoMarket::sellCar, "Марина").start();
        new Thread(null, autoMarket::acceptCar, "Mercedes").start();
    }
}
