package fun.enhui.design.factory.factorymethod.pizzastroe.factory;

import fun.enhui.design.factory.factorymethod.pizzastroe.pizza.GreekPizza;
import fun.enhui.design.factory.factorymethod.pizzastroe.pizza.Pizza;

public class GreekPizzaFactory extends AbsFactoryMethod {
    @Override
    public Pizza createPizza() {
        System.out.println("greekPizza factory create GreekPizza");
        return new GreekPizza();
    }
}
