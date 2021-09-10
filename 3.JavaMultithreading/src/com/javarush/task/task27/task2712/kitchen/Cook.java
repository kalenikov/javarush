package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;

public class Cook extends Observable implements Observer {
    private final String name;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

   @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Order) {
            Order order = (Order) arg;
            ConsoleHelper.writeMessage("Start cooking - " + order + ", cooking time " + order.getTotalCookingTime() + "min");
            StatisticManager.getInstance().register(new CookedOrderEventDataRow(((Order) arg).getTablet().toString(),
                    this.name, ((Order) arg).getTotalCookingTime() ,((Order) arg).getDishes()));
            setChanged();
            notifyObservers(arg);
        }

    }
}
