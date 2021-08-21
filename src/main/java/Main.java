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
        new Thread(null, autoMarket::acceptCar, "Производитель Mercedes").start();
        new Thread(null, autoMarket::acceptCar, "Производитель Toyota").start();
    }
}
