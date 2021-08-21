/**
 * Created by Igor Khristiuk
 * Date: 21.08.2021
 * Time: 17:31
 */
public class Seller extends Thread {
    private AutoMarket autoMarket;

    public Seller(AutoMarket autoMarket) {
        this.autoMarket = autoMarket;
    }

    public synchronized Car sellCar() {
        System.out.printf("%s зашёл в магазин\n", Thread.currentThread().getName());
        try {
            while (autoMarket.getCars().size() == 0) {
                System.out.println("Машин в наличии нет");
                wait();
            }
            System.out.printf("Счастливый %s уехал на новеньком авто\n", Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return autoMarket.getCars().remove(0);
    }

    public synchronized void reciveCar() {
        autoMarket.getCars().add(new Car());
        System.out.printf("Производитель %s выставил на продажу новое авто\n",
                Thread.currentThread().getName());
        notify();
    }
}