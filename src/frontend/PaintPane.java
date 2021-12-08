package frontend;

import backend.CanvasState;
import backend.model.Figure;
import backend.model.Point;
import backend.model.Rectangle;
import frontend.FigureHandler.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;

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
	ToggleButton deleteFigure = new ToggleButton("Eliminar");

	//Modification tools
	Slider slider = new Slider(1, 50, 26);
	final ColorPicker fillPicker = new ColorPicker();
	final ColorPicker borderPicker = new ColorPicker();


	// Dibujar una figura
	Point startPoint;

	// Seleccionar una figura
	Figure selectedFigure;

	// StatusBar
	StatusPane statusPane;

	// Selected figure set
	List<Figure> selectedFigureList = new LinkedList<>();

	public PaintPane(CanvasState canvasState, StatusPane statusPane) {
		this.canvasState = canvasState;
		this.statusPane = statusPane;
		ToggleButton[] toolsArr = {selectionButton, rectangleButton, circleButton, squareButton, lineButton, ellipseButton, deleteFigure};

		ToggleGroup tools = new ToggleGroup();
		for (ToggleButton tool : toolsArr) {
			tool.setMinWidth(90);
			tool.setToggleGroup(tools);
			tool.setCursor(Cursor.HAND);
		}
		deleteFigure.setToggleGroup(null);
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
		slider.setBlockIncrement(1);
		buttonsBox.getChildren().add(new Label("Border"));
		buttonsBox.getChildren().add(slider);
		borderPicker.getStyleClass().add("button");
		buttonsBox.getChildren().add(borderPicker);

		fillPicker.getStyleClass().add("button");
		buttonsBox.getChildren().add(new Label("Relleno"));
		buttonsBox.getChildren().add(fillPicker);
		slider.setValue(1);
		fillPicker.setValue(fillColor);
		borderPicker.setValue(lineColor);

		slider.valueChangingProperty().addListener(strokeListener());
		borderPicker.valueProperty().addListener(borderListener());
		fillPicker.valueProperty().addListener(fillListener());
		deleteFigure.setOnAction(actionEvent -> {
			if(!selectedFigureList.isEmpty()) {
				for(Figure figure: selectedFigureList) {
					canvasState.removeFigure(figure);
				}
				selectedFigureList.clear();
				redrawCanvas();
			}
			deleteFigure.setSelected(false);
		});
		setCanvasEvents(tools);

		setLeft(buttonsBox);
		setRight(canvas);
	}

	private void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		for(Figure figure : canvasState.figures()) {
			figure.drawFigure(gc);
		}
	}

	public GraphicsContext getContext() {
		return gc;
	}

	private void setCanvasEvents(ToggleGroup tools) {
		canvas.setOnMousePressed(onMousePressed());
		canvas.setOnMouseReleased(onMouseReleased(tools));
		canvas.setOnMouseMoved(onMouseMoved());
		canvas.setOnMouseClicked(onMouseClicked());
		canvas.setOnMouseDragged(onMouseDragged());
	}


	private EventHandler<javafx.scene.input.MouseEvent> onMouseDragged() {

		return new EventHandler<javafx.scene.input.MouseEvent>() {
			@Override
			public void handle(javafx.scene.input.MouseEvent mouseEvent) {
				if(selectionButton.isSelected() && !selectedFigureList.isEmpty()) {
				Point eventPoint = new Point(mouseEvent.getX(), mouseEvent.getY());
				double diffX = (eventPoint.getX() - startPoint.getX());
				double diffY = (eventPoint.getY() - startPoint.getY());
				for(Figure figure : selectedFigureList) {
					figure.changePosition(diffX, diffY);
				}
				redrawCanvas();
				startPoint = eventPoint;
			}
			}
		};
	}

	private EventHandler<javafx.scene.input.MouseEvent> onMouseClicked() {

		return  new EventHandler<javafx.scene.input.MouseEvent>() {
			@Override
			public void handle(javafx.scene.input.MouseEvent mouseEvent) {

				if (selectionButton.isSelected() && selectedFigureList.isEmpty()) {
					Point eventPoint = new Point(mouseEvent.getX(), mouseEvent.getY());
					boolean found = false;
					StringBuilder label = new StringBuilder("Se seleccionó: ");
					for (Figure figure : canvasState.figures()) {
						if (figure.hasPoint(eventPoint)) {
							found = true;
							selectedFigureList.add(figure);
							figure.select();
							label.append(figure.toString());
						}
					}
					if (found) {
						statusPane.updateStatus(label.toString());
					} else {
						selectedFigureList.clear();
						statusPane.updateStatus("Ninguna figura encontrada");
					}
					redrawCanvas();
				}
			}
		};
	}

	private EventHandler<javafx.scene.input.MouseEvent> onMouseMoved() {

		return new EventHandler<javafx.scene.input.MouseEvent>() {
			@Override
			public void handle(javafx.scene.input.MouseEvent mouseEvent) {
				Point eventPoint = new Point(mouseEvent.getX(), mouseEvent.getY());
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
			}
		};
	}

	private EventHandler<javafx.scene.input.MouseEvent> onMouseReleased(ToggleGroup tools) {
		return new EventHandler<javafx.scene.input.MouseEvent>() {
			@Override
			public void handle(javafx.scene.input.MouseEvent mouseEvent) {
				Point endPoint = new Point(mouseEvent.getX(), mouseEvent.getY());
				if(startPoint == null){
					return;
				}
				Figure newFigure = null;
				Toggle selected = tools.getSelectedToggle();
				if(selected == null)
					return;
				unselectFigureList();
				if(selectionButton.isSelected()) {
					Rectangle areaSelected =(Rectangle)((FigureHandler)rectangleButton.getUserData()).createFigure(startPoint,endPoint);
					if(areaSelected == null){
						return;
					}
					StringBuilder label = new StringBuilder("Se seleccionó: ");
					for(Figure figure : canvasState.figures()){
						if(figure.isContainedIn(areaSelected)){
							selectedFigureList.add(figure);
							figure.select();
							label.append(figure);
						}
					}
					statusPane.updateStatus(label.toString());
					redrawCanvas();
					return;
				}

				gc.setFill(fillPicker.getValue());
				gc.setStroke(borderPicker.getValue());
				gc.setLineWidth(slider.getValue());
				newFigure =  ((FigureHandler) selected.getUserData()).createFigure(startPoint, endPoint);
				if(newFigure == null)
					return;
				canvasState.addFigure(newFigure);
				redrawCanvas();
				startPoint = null;
			}
		};
	}

	private EventHandler<javafx.scene.input.MouseEvent> onMousePressed() {

		return new EventHandler<javafx.scene.input.MouseEvent>() {
			@Override
			public void handle(javafx.scene.input.MouseEvent mouseEvent) {
				startPoint = new Point(mouseEvent.getX(), mouseEvent.getY());
			}
		};
	}

	private ChangeListener<Color> fillListener() {
		return new ChangeListener<Color>() {
			@Override
			public void changed(ObservableValue<? extends Color> observableValue, Color color, Color t1) {
				if(!selectedFigureList.isEmpty()) {
					for(Figure figure : selectedFigureList) {
						figure.setFillColor(fillPicker.getValue());
					}
					redrawCanvas();
				}
			}
		};
	}

	private ChangeListener<Color> borderListener() {
		return new ChangeListener<Color>() {
			@Override
			public void changed(ObservableValue<? extends Color> observableValue, Color color, Color t1) {
				if(!selectedFigureList.isEmpty() ) {
					for(Figure figure : selectedFigureList) {
						figure.setBorderColor(borderPicker.getValue());
					}
					redrawCanvas();
				}
			}
		};
	}
	private void unselectFigureList() {
		for (Figure figure : selectedFigureList)
			figure.unSelect();
		selectedFigureList.clear();
	}
	private ChangeListener<Boolean> strokeListener() {
		return new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
				if(!slider.isValueChanging()) {
					if(!selectedFigureList.isEmpty()) {
						for(Figure figure : selectedFigureList) {
							figure.setBorderWidth(slider.getValue());
						}
						redrawCanvas();
					}
				}
			}
		};
	}
}
