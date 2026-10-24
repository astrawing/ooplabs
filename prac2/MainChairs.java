interface Furniture {
  void makeFurniture();
  void deliverFurniture();
}

interface Chair extends Furniture {
}

class WoodChair implements Chair {
  @Override
  public void makeFurniture() {
    System.out.println("Делаю деревянный стул");
    System.out.println("Использую массив дуба и ручную резьбу");
  }

  @Override
  public void deliverFurniture() {
    System.out.println("Упаковываю стул в деревянную обрешётку");
  }
}

class OfficeChair implements Chair {
  @Override
  public void makeFurniture() {
    System.out.println("Делаю офисный стул");
    System.out.println("Собираю каркас из металла");
    System.out.println("Устанавливаю газлифт и колёсики");
  }

  @Override
  public void deliverFurniture() {
    System.out.println("Упаковываю стул в картонную коробку с пенопластом");
  }
}

class BarChair implements Chair {
  @Override
  public void makeFurniture() {
    System.out.println("Делаю барный стул");
    System.out.println("Высокие металлические ножки с подножкой");
    System.out.println("Кожаная обивка сиденья");
  }

  @Override
  public void deliverFurniture() {
    System.out.println("Оборачиваю сиденье стула в стрейч-плёнку");
  }
}

interface Table extends Furniture {
}

class DiningTable implements Table {
  @Override
  public void makeFurniture() {
    System.out.println("Делаю обеденный стол");
    System.out.println("Столешница из массива бука");
    System.out.println("Четыре точёные ножки");
  }

  @Override
  public void deliverFurniture() {
    System.out.println("Разбираю стол и упаковываю столешницу отдельно от ножек");
  }
}

class OfficeDesk implements Table {
  @Override
  public void makeFurniture() {
    System.out.println("Делаю офисный стол");
    System.out.println("ЛДСП-столешница с ламинацией");
    System.out.println("Металлический каркас и выдвижные ящики");
  }

  @Override
  public void deliverFurniture() {
    System.out.println("Упаковываю стол в плоскую коробку в разобранном виде");
  }
}

class CoffeeTable implements Table {
  @Override
  public void makeFurniture() {
    System.out.println("Делаю журнальный столик");
    System.out.println("Стеклянная столешница");
    System.out.println("Хромированные изогнутые ножки");
  }

  @Override
  public void deliverFurniture() {
    System.out.println("Оборачиваю стекло в пузырчатую плёнку и укрепляю уголки");
  }
}

enum FurnitureType {
  WOOD_CHAIR ("Деревянный стул"),
  OFFICE_CHAIR ("Офисный стул"),
  BAR_STOOL ("Барный стул"),
  DINING_TABLE ("Обеденный стол"),
  OFFICE_DESK ("Офисный стол"),
  COFFEE_TABLE ("Журнальный столик");

  private final String description;

  FurnitureType(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}

abstract class FurnitureFactory {
  protected abstract Furniture createFurniture(FurnitureType type);

  public void orderFurniture(FurnitureType type) {
    System.out.println(">>> Заказ: " + type.getDescription());
    Furniture furniture = createFurniture(type);
    furniture.makeFurniture();
    furniture.deliverFurniture();
  }
}

class ClassicFurnitureFactory extends FurnitureFactory {
  @Override
  protected Furniture createFurniture(FurnitureType type) {
    System.out.println("Работаем в классическом мебельном стиле");
    switch (type) {
      case WOOD_CHAIR:   return new WoodChair();
      case OFFICE_CHAIR: return new OfficeChair();
      case BAR_STOOL:    return new BarChair();
      case DINING_TABLE: return new DiningTable();
      case OFFICE_DESK:  return new OfficeDesk();
      case COFFEE_TABLE: return new CoffeeTable();
      default:
        throw new IllegalArgumentException("Неизвестный тип мебели: " + type);
    }
  }
}

class ModernFurnitureFactory extends FurnitureFactory {
  @Override
  protected Furniture createFurniture(FurnitureType type) {
    System.out.println("Работаем в современном минималистичном стиле");
    switch (type) {
      case WOOD_CHAIR:   return new WoodChair();
      case OFFICE_CHAIR: return new OfficeChair();
      case BAR_STOOL:    return new BarChair();
      case DINING_TABLE: return new DiningTable();
      case OFFICE_DESK:  return new OfficeDesk();
      case COFFEE_TABLE: return new CoffeeTable();
      default:
        throw new IllegalArgumentException("Неизвестный тип мебели: " + type);
    }
  }
}

public class MainChairs {
  public static void main(String[] args) {
    FurnitureFactory classicFactory = new ClassicFurnitureFactory();
    FurnitureFactory modernFactory = new ModernFurnitureFactory();

    System.out.println("===== Заказы у классической фабрики =====");
    classicFactory.orderFurniture(FurnitureType.WOOD_CHAIR);
    System.out.println();
    classicFactory.orderFurniture(FurnitureType.DINING_TABLE);
    System.out.println();
    classicFactory.orderFurniture(FurnitureType.COFFEE_TABLE);

    System.out.println("\n===== Заказы у современной фабрики =====");
    modernFactory.orderFurniture(FurnitureType.OFFICE_CHAIR);
    System.out.println();
    modernFactory.orderFurniture(FurnitureType.OFFICE_DESK);
    System.out.println();
    modernFactory.orderFurniture(FurnitureType.BAR_STOOL);
  }
}