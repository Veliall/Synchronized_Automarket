import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Igor Khristiuk
 * Date: 20.08.2021
 * Time: 15:37
 */

public class AutoMarket {
    private final int SLEEP_TIME = 2500;

    ReentrantLock lock = new ReentrantLock(true);
    Condition condition = lock.newCondition();

    List<Car> cars = new ArrayList<>(10);

    public void sellCar() {
        try {
            lock.lock();
            System.out.printf("%s зашёл в магазин\n", Thread.currentThread().getName());
            while (cars.size() == 0) {
                System.out.println("Машин в наличии нет");
                condition.await();
            }
            System.out.printf("Счастливый %s уехал на новеньком авто\n", Thread.currentThread().getName());
            cars.remove(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void acceptCar() {
        while (!Thread.currentThread().isInterrupted() && cars.size() < 5) {
            try {
                lock.lock();
                cars.add(new Car());
                System.out.printf("Производитель %s выставил на продажу новое авто\n",
                        Thread.currentThread().getName());
                condition.signal();
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
