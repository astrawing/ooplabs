import java.util.*;

interface Coffee {
  void makeCoffee();
  void pourIntoCap();
}

interface Lemonade {
  void makeLimonade();
  void pourIntoGlass();
}

class Espresso implements Coffee {
  @Override
  public void makeCoffee() {
    System.out.println("Варю эспрессо");
    System.out.println("Только кофе и почти никакой воды");
  }

  @Override
  public void pourIntoCap() {
    System.out.println("Наливаю в маленькую итальянскую чашку");
  }
}

class Sanpelegrinno implements Lemonade {
  @Override
  public void makeLimonade() {
    System.out.println("Наливаю санпелегринно");
    System.out.println("Добавляю лед");
  }

  @Override
  public void pourIntoGlass() {
    System.out.println("Наливаю в стакан");
  }
}

class Cola implements Lemonade {
  @Override
  public void makeLimonade() {
    System.out.println("Наливаю колу");
    System.out.println("Добавляю лед");
  }

  @Override
  public void pourIntoGlass() {
    System.out.println("Наливаю в маленький стакан");
  }
}

class CaffeeLatte implements Coffee {
  @Override
  public void makeCoffee() {
    System.out.println("Варю латте");
    System.out.println("Варю кофейную основу");
    System.out.println("Взбиваю молочную пенку");
  }

  @Override
  public void pourIntoCap() {
    System.out.println("Наливаю в большую американскую чашку");
  }
}

enum CoffeeType {
  ESPRESSO ("Espresso"),
  LATTE ("CaffeeLatte");

  private final String description;

  CoffeeType(String description) {
    this.description = description;
  }
}

enum LemonadeType {
  COLA ("Cola"),
  SANPELENGRINNO ("Sanpelegrinno");

  private final String description;

  LemonadeType(String description) {
    this.description = description;
  }
}

abstract class CoffeeShop {
  protected abstract Coffee createCoffee(CoffeeType type);
  protected abstract Lemonade createLemonade(LemonadeType type);

  public void takeOrder(CoffeeType type) {
    Coffee coffee = createCoffee(type);
    coffee.makeCoffee();
    coffee.pourIntoCap();
  }

  public void takeOrderToLimonade(CoffeeType type) {
    Lemonade lemonade = createLemonade(type);
    lemonade.makeLimonade();
    lemonade.pourIntoGlass();
  }
}

class ItalianCoffeeShop extends CoffeeShop {
  @Override
  protected Lemonade createLemonade(LemonadeType type) {
    System.out.println("Делаю итальянский лимонад");
    if (type == CoffeeType.ESPRESSO) {
      return new Sanpelegrinno();
    } else {
      System.out.println("Вы серьезно");
      return new Cola();
    }
  }

  @Override
  protected Coffee createCoffee(CoffeeType type) {
    System.out.println("Делаем кофе в итальянском стиле");
    if (type == CoffeeType.ESPRESSO) {
      return new Espresso();
    } else {
      return new CaffeeLatte();
    }
  }
}

class AmericanCoffeeShop extends CoffeeShop {
  @Override
  protected Lemonade createLemonade(LemonadeType type) {
    System.out.println("Делаю итальянский лимонад");
    if (type == CoffeeType.ESPRESSO) {
      return new Sanpelegrinno();
    } else {
      System.out.println("Вы серьезно");
      return new Cola();
    }
  }


  @Override
  protected Coffee createCoffee(CoffeeType type) {
    System.out.println("Делаем кофе в американском стиле");
    if (type == CoffeeType.ESPRESSO) {
      return new Espresso();
    } else {
      return new CaffeeLatte();
    }
  }
}


public class MainCoffee {
    public static void main(String[] args) {
      System.out.println("Hello, World!");
    }
}