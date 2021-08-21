import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Igor Khristiuk
 * Date: 21.08.2021
 * Time: 17:31
 */
public class Seller extends Thread {
    private AutoMarket autoMarket;
    ReentrantLock lock = new ReentrantLock(true);
    Condition condition = lock.newCondition();

    public Seller(AutoMarket autoMarket) {
        this.autoMarket = autoMarket;
    }

    public Car sellCar() {
        System.out.printf("%s зашёл в магазин\n", Thread.currentThread().getName());
        try {
            lock.lock();
            while (autoMarket.getCars().size() == 0) {
                System.out.println("Машин в наличии нет");
                condition.await();
            }
            System.out.printf("Счастливый %s уехал на новеньком авто\n", Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return autoMarket.getCars().remove(0);
    }

    public void reciveCar() {
        try {
            lock.lock();
            autoMarket.getCars().add(new Car());
            System.out.printf("Производитель %s выставил на продажу новое авто\n",
                    Thread.currentThread().getName());
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}