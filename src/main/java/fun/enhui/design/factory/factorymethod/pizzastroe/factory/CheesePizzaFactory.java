package fun.enhui.design.factory.factorymethod.pizzastroe.factory;

import fun.enhui.design.factory.factorymethod.pizzastroe.pizza.CheesePizza;
import fun.enhui.design.factory.factorymethod.pizzastroe.pizza.Pizza;

public class CheesePizzaFactory extends AbsFactoryMethod{
    @Override
    public Pizza createPizza() {
        System.out.println("cheesePizza factory create CheesePizza");
        return new CheesePizza();
    }
}
