package test;
import static org.junit.jupiter.api.Assertions.*;

import backend.CanvasState;
import backend.model.*;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JUnitTest {

    //Rectangle Test
    @Test
    public void testingRectangle() {
        //Rectangle nullPoints = new Rectangle(null,null);
        //assertNull(nullPoints);

        Rectangle move = new Rectangle(new Point(100, 100), new Point(200, 200));
        Rectangle moveTo = new Rectangle(new Point(150, 150), new Point(250, 250));

        move.changePosition(50, 50);

        assertEquals(move.toString(), moveTo.toString());

        move.setDrawingProperties(Color.CORAL, Color.RED, 25);

        assertNotEquals(Color.YELLOW, move.getFillColor());
        assertNotEquals(Color.BLACK, move.getBorderColor());
        assertNotEquals(35, move.getBorderWidth());

        move.select();
        assertEquals(true, move.isSelected());
        move.unSelect();
        assertEquals(false, move.isSelected());

        assertEquals(true, move.isContainedIn(new Rectangle(new Point(0, 0), new Point(260, 260))));
        assertEquals(false, move.hasPoint(new Point(0, 0)));

        testingClone(move);
        /*
        Rectangle cloneMove = move.getClone();

        assertEquals(move.toString(),cloneMove.toString());
        assertEquals(move.getBorderColor(),cloneMove.getBorderColor());
        assertEquals(move.getFillColor(),cloneMove.getFillColor());
        assertEquals(move.getBorderWidth(),cloneMove.getBorderWidth());*/

    }
    @Test
    public void testingCircle() {
        //Rectangle nullPoints = new Rectangle(null,null);
        //assertNull(nullPoints);

        Circle move = new Circle(new Point(100, 100), 50);
        Circle moveTo = new Circle(new Point(150, 150), 50);

        move.changePosition(50, 50);

        assertEquals(move.toString(), moveTo.toString());

        move.setDrawingProperties(Color.CORAL, Color.RED, 25);

        assertNotEquals(Color.YELLOW, move.getFillColor());
        assertNotEquals(Color.BLACK, move.getBorderColor());
        assertNotEquals(35, move.getBorderWidth());

        move.select();
        assertEquals(true, move.isSelected());
        move.unSelect();
        assertEquals(false, move.isSelected());

        assertEquals(true, move.isContainedIn(new Rectangle(new Point(0, 0), new Point(260, 260))));
        assertEquals(false, move.hasPoint(new Point(0, 0)));

        testingClone(move);
        /*
        Circle cloneMove = move.getClone();

        assertEquals(move.toString(),cloneMove.toString());
        assertEquals(move.getBorderColor(),cloneMove.getBorderColor());
        assertEquals(move.getFillColor(),cloneMove.getFillColor());
        assertEquals(move.getBorderWidth(),cloneMove.getBorderWidth());*/

    }

    @Test
    public void testingSquare() {
        //Rectangle nullPoints = new Rectangle(null,null);
        //assertNull(nullPoints);

        Square move = new Square(new Point(100, 100), new Point(120,120));
        Square moveTo = new Square(new Point(150, 150), new Point(170,170));

        move.changePosition(50, 50);

        assertEquals(move.toString(), moveTo.toString());

        move.setDrawingProperties(Color.CORAL, Color.RED, 25);

        assertNotEquals(Color.YELLOW, move.getFillColor());
        assertNotEquals(Color.BLACK, move.getBorderColor());
        assertNotEquals(35, move.getBorderWidth());

        move.select();
        assertEquals(true, move.isSelected());
        move.unSelect();
        assertEquals(false, move.isSelected());

        assertEquals(true, move.isContainedIn(new Rectangle(new Point(0, 0), new Point(260, 260))));
        assertEquals(false, move.hasPoint(new Point(0, 0)));

        testingClone(move);
        /*
        Square cloneMove = move.getClone();

        assertEquals(move.toString(),cloneMove.toString());
        assertEquals(move.getBorderColor(),cloneMove.getBorderColor());
        assertEquals(move.getFillColor(),cloneMove.getFillColor());
        assertEquals(move.getBorderWidth(),cloneMove.getBorderWidth());*/

    }

    @Test
    public void testingEllipse() {
        //Rectangle nullPoints = new Rectangle(null,null);
        //assertNull(nullPoints);

        Ellipse move = new Ellipse(new Point(100, 100), new Point(120,120));
        Ellipse moveTo = new Ellipse(new Point(150, 150), new Point(170,170));

        move.changePosition(50, 50);

        assertEquals(move.toString(), moveTo.toString());

        move.setDrawingProperties(Color.CORAL, Color.RED, 25);

        assertNotEquals(Color.YELLOW, move.getFillColor());
        assertNotEquals(Color.BLACK, move.getBorderColor());
        assertNotEquals(35, move.getBorderWidth());

        move.select();
        assertEquals(true, move.isSelected());
        move.unSelect();
        assertEquals(false, move.isSelected());

        assertEquals(true, move.isContainedIn(new Rectangle(new Point(0, 0), new Point(260, 260))));
        assertEquals(false, move.hasPoint(new Point(0, 0)));

        testingClone(move);
       /* Ellipse cloneMove = move.getClone();

        assertEquals(move.toString(),cloneMove.toString());
        assertEquals(move.getBorderColor(),cloneMove.getBorderColor());
        assertEquals(move.getFillColor(),cloneMove.getFillColor());
        assertEquals(move.getBorderWidth(),cloneMove.getBorderWidth());
*/
    }

    @Test
    public void testingLine() {
        //Rectangle nullPoints = new Rectangle(null,null);
        //assertNull(nullPoints);

        Line move = new Line(new Point(100, 100), new Point(200, 200));
        Line moveTo = new Line(new Point(150, 150), new Point(250, 250));

        move.changePosition(50, 50);

        assertEquals(move.toString(), moveTo.toString());

        move.setDrawingProperties(Color.CORAL, Color.RED, 25);

        assertNotEquals(Color.YELLOW, move.getFillColor());
        assertNotEquals(Color.BLACK, move.getBorderColor());
        assertNotEquals(35, move.getBorderWidth());

        move.select();
        assertEquals(true, move.isSelected());
        move.unSelect();
        assertEquals(false, move.isSelected());

        assertEquals(true, move.isContainedIn(new Rectangle(new Point(0, 0), new Point(260, 260))));
        assertEquals(false, move.hasPoint(new Point(0, 0)));

        testingClone(move);
    }

    @Test
    public void pointTest(){

        Point test = new Point(100,100);
        Point setterTest = new Point(150,150);
        test.setX(150);
        test.setY(150);
        assertEquals(setterTest,test);
        assertEquals(test,test.getClone());
        assertEquals(0,setterTest.distance(test));
        assertEquals(150,test.getX());
        assertEquals(150,test.getY());

    }
    @Test
    public void canvasTester(){

        CanvasState test = new CanvasState();
        Circle cirAux = new Circle(new Point(100,100),20);
        Rectangle recAux=new Rectangle(new Point(100,100),new Point(150,150));
        cirAux.setDrawingProperties(Color.CORAL,Color.RED,25);
        recAux.setDrawingProperties(Color.CORAL,Color.RED,25);
        test.addFigure(recAux);
        test.addFigure(cirAux);
        List<Figure> compAux = new ArrayList<>();
        compAux.add(recAux);
        compAux.add(cirAux);
        assertIterableEquals(compAux, test.figures());
        compAux.clear();
        compAux.add(cirAux);
        compAux.add(recAux);
        assertIterableEquals(compAux,test.reverseFigures());
        compAux.clear();
        compAux.add(cirAux);
        compAux.add(recAux);
        compAux.add(cirAux);
        test.moveToBack(cirAux);
        assertIterableEquals(compAux, test.figures());
        compAux.remove(cirAux);
        test.removeFigure(cirAux);
        assertIterableEquals(compAux, test.figures());

        CanvasState testClone = test.getClone();
        Iterator<Figure> testCloneIt = testClone.figures().iterator();
        Iterator<Figure> testIt = test.figures().iterator();
        while(testIt.hasNext()){
            assertEquals(testIt.next().toString(),testCloneIt.next().toString());
        }

    }


    @Disabled
    public void testingClone(Figure move){
        Figure cloneMove = move.getClone();
        assertEquals(move.toString(),cloneMove.toString());
        assertEquals(move.getBorderColor(),cloneMove.getBorderColor());
        assertEquals(move.getFillColor(),cloneMove.getFillColor());
        assertEquals(move.getBorderWidth(),cloneMove.getBorderWidth());

    }



}

