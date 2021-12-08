package frontend;

import backend.CanvasState;
import backend.model.Figure;
import backend.model.Point;
import backend.model.Rectangle;
import frontend.FigureHandler.*;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PaintPane extends BorderPane {

	// BackEnd
	CanvasState canvasState;

	// Canvas y relacionados
	Canvas canvas = new Canvas(800, 600);
	GraphicsContext gc = canvas.getGraphicsContext2D();
	Color lineColor = Color.BLACK;
	Color fillColor = Color.YELLOW;

	// Botones Barra Izquierda
	ToggleButton selectionButton = new ToggleButton("Seleccionar");
	ToggleButton rectangleButton = new ToggleButton("Rectangulo");
	ToggleButton circleButton = new ToggleButton("Circulo");
	ToggleButton squareButton = new ToggleButton("Cuadrado");
	ToggleButton lineButton = new ToggleButton("Linea");
	ToggleButton ellipseButton = new ToggleButton("Elipse");

	//Modification tools
	Slider slider = new Slider(1, 50, 26);
	final ColorPicker colorPicker = new ColorPicker();


	// Dibujar una figura
	Point startPoint;

	// Seleccionar una figura
	Figure selectedFigure;

	// StatusBar
	StatusPane statusPane;

	public PaintPane(CanvasState canvasState, StatusPane statusPane) {
		this.canvasState = canvasState;
		this.statusPane = statusPane;
		ToggleButton[] toolsArr = {selectionButton, rectangleButton, circleButton, squareButton, lineButton, ellipseButton};

		ToggleGroup tools = new ToggleGroup();
		for (ToggleButton tool : toolsArr) {
			tool.setMinWidth(90);
			tool.setToggleGroup(tools);
			tool.setCursor(Cursor.HAND);
		}
		rectangleButton.setUserData(new RectangleHandler(this));
		circleButton.setUserData(new CircleHandler(this));
		squareButton.setUserData(new SquareHandler(this));
		lineButton.setUserData(new LineHandler(this));
		ellipseButton.setUserData(new EllipseHandler(this));

		VBox buttonsBox = new VBox(10);
		buttonsBox.getChildren().addAll(toolsArr);
		buttonsBox.setPadding(new Insets(5));
		buttonsBox.setStyle("-fx-background-color: #999");
		buttonsBox.setPrefWidth(100);
		gc.setLineWidth(1);

		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);
		slider.setMajorTickUnit(25);
		slider.setBlockIncrement(0.1f);
		buttonsBox.getChildren().add(new Label("Border"));
		buttonsBox.getChildren().add(slider);

		colorPicker.getStyleClass().add("button");
		buttonsBox.getChildren().add(new Label("Relleno"));
		buttonsBox.getChildren().add(colorPicker);
		slider.setValue(1);
		colorPicker.setValue(fillColor);
		canvas.setOnMousePressed(event -> {
			startPoint = new Point(event.getX(), event.getY());
		});
		canvas.setOnMouseReleased(event -> {
			Point endPoint = new Point(event.getX(), event.getY());
			if(startPoint == null) {
				return ;
			}
			Figure newFigure = null;
			int i = 0;
			Toggle selected = tools.getSelectedToggle();
			if(selected == null)
				return;
			if(selectionButton.isSelected()) {
				redrawCanvas();
				return;
			}

			gc.setFill(colorPicker.getValue());
			gc.setStroke(lineColor);
			gc.setLineWidth(slider.getValue());
			newFigure =  ((FigureHandler) selected.getUserData()).createFigure(startPoint, endPoint);
			if(newFigure == null)
				return;
			canvasState.addFigure(newFigure);
			startPoint = null;
			redrawCanvas();
		});
		canvas.setOnMouseMoved(event -> {
			Point eventPoint = new Point(event.getX(), event.getY());
			boolean found = false;
			StringBuilder label = new StringBuilder();
			for(Figure figure : canvasState.figures()) {
				if(figure.hasPoint(eventPoint)) {
					found = true;
					label.append(figure.toString());
				}
			}
			if(found) {
				statusPane.updateStatus(label.toString());
			} else {
				statusPane.updateStatus(eventPoint.toString());
			}
		});

		canvas.setOnMouseClicked(event -> {
			if(selectedFigure != null)
				selectedFigure.unSelect();
			if(selectionButton.isSelected()) {
				Point eventPoint = new Point(event.getX(), event.getY());
				boolean found = false;
				StringBuilder label = new StringBuilder("Se seleccionó: ");
				for (Figure figure : canvasState.figures()) {
					if(figure.hasPoint(eventPoint)) {
						found = true;
						selectedFigure = figure;
						selectedFigure.select();
						label.append(figure.toString());
					}
				}
				if (found) {
					statusPane.updateStatus(label.toString());
				} else {
					selectedFigure = null;
					statusPane.updateStatus("Ninguna figura encontrada");
				}
				redrawCanvas();
			}
		});
		canvas.setOnMouseDragged(event -> {
			if(selectionButton.isSelected() && selectedFigure != null) {
				Point eventPoint = new Point(event.getX(), event.getY());
				double diffX = (eventPoint.getX() - startPoint.getX());
				double diffY = (eventPoint.getY() - startPoint.getY());
				selectedFigure.changePosition(diffX, diffY);
				redrawCanvas();
				startPoint = eventPoint;
			}
		});
		setLeft(buttonsBox);
		setRight(canvas);
	}

	void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());


		for(Figure figure : canvasState.figures()) {
			if(figure == selectedFigure) {

			} else {

			}
			gc.setFill(fillColor);
			figure.drawFigure(gc);
		}
	}

	public GraphicsContext getContext() {
		return gc;
	}
}
