package win.smile.rtti;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weifw
 * @date 2018-11-18-22:29
 */
public class Shape {

    public static void main(String[] args) {
        List<Shape> shapeList = new ArrayList<>();
        Circle circle = new Circle();
        shapeList.add(circle);
        Square square = new Square();
        shapeList.add(square);
        Trlangle trlangle = new Trlangle();
        shapeList.add(trlangle);
        Shape shape = new Shape();
        shapeList.add(shape);
        for (Shape s : shapeList) {
            s.draw();
            System.out.println(s instanceof Circle);
        }
        System.out.println(shapeList);
        Class s = Shape.class;
        boolean instance = s.isInstance(s);
        System.out.println(s);
        List list = new ArrayList();


    }

    public void draw(){
        System.out.println("draw shap");
    }

}

class Circle extends Shape{

    @Override
    public void draw(){
        System.out.println("draw Circle");
    }
}

class Square extends Shape{

    @Override
    public void draw(){
        System.out.println("draw Square");
    }
}
class Trlangle extends Shape{

    @Override
    public void draw(){
        System.out.println("draw Trlangle");
    }
}




