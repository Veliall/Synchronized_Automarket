import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor Khristiuk
 * Date: 20.08.2021
 * Time: 15:37
 */

//Хранит и продаёт машины
public class AutoMarket {
    private final int SLEEP_TIME = 2500;

    Seller seller = new Seller(this);
    List<Car> cars = new ArrayList<>(10);

    public Car sellCar() {
        return seller.sellCar();
    }

    public void acceptCar() {
        while (!seller.isInterrupted() && cars.size() < 5) {
            try {
                Thread.sleep(SLEEP_TIME);
                seller.reciveCar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Car> getCars() {
        return cars;
    }
}
