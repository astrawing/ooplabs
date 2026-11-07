interface Color {
    void fillColor();
}
 
class RedColor implements Color {
    @Override
    public void fillColor() {
        System.out.println("Filling with red color");
    }
}
 
class BlueColor implements Color {
    @Override
    public void fillColor() {
        System.out.println("Filling with blue color");
    }
}
 
abstract class Shape {
    protected Color color;
    public Shape(Color color) {
        this.color = color;
    }
 
    public void draw() {
        System.out.println("Drawing shape....");
        this.color.fillColor();
    }
}
 
class Rectangle extends Shape {
    public Rectangle(Color color) {
        super(color);
    }
 
    @Override
    public void draw() {
        System.out.println("Drawing rectangle....");
        super.draw();
    }
}
 
class Triangle extends Shape {
    public Triangle(Color color) {
        super(color);
    }
 
    @Override
    public void draw() {
        System.out.println("Drawing triangle....");
        super.draw();
    }
}
 
public class ColorMain {
    public static void main(String[] args) {
        
        Shape redRectangle = new Rectangle(new RedColor());
        Shape blueTriangle = new Triangle(new BlueColor());
        Shape blueRectangle = new Rectangle(new BlueColor());
 
        System.out.println("--- Рисуем красный прямоугольник ---");
        redRectangle.draw();
 
        System.out.println("\n--- Рисуем синий треугольник ---");
        blueTriangle.draw();
 
        System.out.println("\n--- Рисуем синий прямоугольник ---");
        blueRectangle.draw();
    }
}