package frontend;

import backend.CanvasState;
import backend.CanvasHistory;
import backend.model.Figure;
import backend.model.Point;
import backend.model.Rectangle;
import frontend.FigureHandler.*;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PaintPane extends BorderPane {

	// BackEnd
	private CanvasState canvasState;
	private final CanvasHistory canvasHistory = new CanvasHistory();

	// Canvas y relacionados
	private final Canvas canvas = new Canvas(800, 600);
	private final GraphicsContext gc = canvas.getGraphicsContext2D();

	//DRAWING DEFAULTS
	private final Color DEFAULT_BORDER_COLOR = Color.BLACK;
	private final Color DEFAULT_FILL_COLOR = Color.YELLOW;
	private final double DEFAULT_LINE_WIDTH = 1;


	// Botones Barra Izquierda
	private final ToggleButton selectionButton = new ToggleButton("Seleccionar");
	private final ToggleButton rectangleButton = new ToggleButton("Rectangulo");
	private final ToggleButton circleButton = new ToggleButton("Circulo");
	private final ToggleButton squareButton = new ToggleButton("Cuadrado");
	private final ToggleButton lineButton = new ToggleButton("Linea");
	private final ToggleButton ellipseButton = new ToggleButton("Elipse");
	private final ToggleButton deleteFigure = new ToggleButton("Eliminar");
	private final ToggleButton toFront = new ToggleButton("Traer al frente");
	private final ToggleButton toBack = new ToggleButton("Enviar al fondo");
	private final ToggleButton undo = new ToggleButton("Deshacer");
	private final ToggleButton redo = new ToggleButton("Rehacer");

	//Modification tools
	private final Slider slider = new Slider(1, 50, 26);
	private final ColorPicker fillPicker = new ColorPicker();
	private final ColorPicker borderPicker = new ColorPicker();


	// Dibujar una figura
	Point startPoint;

	// StatusBar
	StatusPane statusPane;

	// Selected figure list
	List<Figure> selectedFigureList = new LinkedList<>();

	public PaintPane(CanvasState canvasState, StatusPane statusPane) {
		this.canvasState = canvasState;
		this.statusPane = statusPane;

		canvasHistory.addHistory(getCanvasState());


		//setting up buttons and groups
		ToggleButton[] toolsArr = {selectionButton, rectangleButton, circleButton, squareButton, lineButton, ellipseButton};
		ToggleButton[] operationsArr = {deleteFigure, undo, redo, toBack, toFront};
		ToggleGroup tools = new ToggleGroup();
		ToggleGroup operations = new ToggleGroup();
		addToGroup(toolsArr, tools);
		addToGroup(operationsArr, operations);

		//setting up each figure handler with his own button
		rectangleButton.setUserData(new RectangleHandler(this));
		circleButton.setUserData(new CircleHandler(this));
		squareButton.setUserData(new SquareHandler(this));
		lineButton.setUserData(new LineHandler(this));
		ellipseButton.setUserData(new EllipseHandler(this));

		//adding buttons to left panel
		VBox buttonsBox = new VBox(10);
		buttonsBox.getChildren().addAll(toolsArr);
		buttonsBox.getChildren().addAll(operationsArr);
		buttonsBox.setPadding(new Insets(5));
		buttonsBox.setStyle("-fx-background-color: #999");
		buttonsBox.setPrefWidth(100);

		//setting drawing properties tools
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

		//setting default drawing properties
		slider.setValue(DEFAULT_LINE_WIDTH);
		fillPicker.setValue(DEFAULT_FILL_COLOR);
		borderPicker.setValue(DEFAULT_BORDER_COLOR);
		gc.setLineWidth(DEFAULT_LINE_WIDTH);


		//adding listeners to drawing properties tools
		slider.valueChangingProperty().addListener(strokeListener());
		borderPicker.valueProperty().addListener(borderListener());
		fillPicker.valueProperty().addListener(fillListener());


		//setting up operation buttons' behaviours
		undo.setOnAction(undoHandler());
		redo.setOnAction(redoHandler());
		deleteFigure.setOnAction(deleteHandler());
		toFront.setOnAction(sendToFrontHandler());
		toBack.setOnAction(sendToBackHandler());
		setCanvasEvents(tools);

		setLeft(buttonsBox);
		setRight(canvas);
	}

	private void addToGroup(ToggleButton[] buttons, ToggleGroup group) {
		for (ToggleButton button : buttons) {
			button.setMinWidth(90);
			button.setToggleGroup(group);
			button.setCursor(Cursor.HAND);
		}
	}

	private void setCanvasEvents(ToggleGroup tools) {
		canvas.setOnMousePressed(onMousePressed());
		canvas.setOnMouseReleased(onMouseReleased(tools));
		canvas.setOnMouseMoved(onMouseMoved());
		canvas.setOnMouseClicked(onMouseClicked());
		canvas.setOnMouseDragged(onMouseDragged());
	}

	private EventHandler<MouseEvent> onMouseDragged() {

		return mouseEvent -> {
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
		};
	}

	private EventHandler<MouseEvent> onMouseClicked() {

		return mouseEvent -> {

			if (selectionButton.isSelected() && selectedFigureList.isEmpty()) {
				Point eventPoint = new Point(mouseEvent.getX(), mouseEvent.getY());
				boolean found = false;
				StringBuilder label = new StringBuilder("Se seleccionó: ");

				Iterator<Figure> it= canvasState.reverseFigures().iterator();
				Figure figure;
				while(it.hasNext() && !found) {
					figure = it.next();
					if (figure.hasPoint(eventPoint)) {
						found = true;
						selectedFigureList.add(figure);
						figure.select();
						label.append(figure);
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
		};
	}

	private EventHandler<MouseEvent> onMouseMoved() {

		return mouseEvent -> {
			Point eventPoint = new Point(mouseEvent.getX(), mouseEvent.getY());
			boolean found = false;
			StringBuilder label = new StringBuilder();
			for (Figure figure : canvasState.figures()) {
				if (figure.hasPoint(eventPoint)) {
					found = true;
					label.append(figure);
				}
			}
			if(found) {
				statusPane.updateStatus(label.toString());
			} else {
				statusPane.updateStatus(eventPoint.toString());
			}
		};
	}

	private EventHandler<MouseEvent> onMouseReleased(ToggleGroup tools) {
		return mouseEvent -> {
			Point endPoint = new Point(mouseEvent.getX(), mouseEvent.getY());
			if(startPoint == null){
				return;
			}

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
			Figure newFigure = ((FigureHandler) selected.getUserData()).createFigure(startPoint, endPoint);
			if(newFigure == null)
				return;

			canvasState.addFigure(newFigure);
			redrawCanvas();
			canvasHistory.addHistory(getCanvasState());

			startPoint = null;
		};
	}

	private EventHandler<MouseEvent> onMousePressed() {

		return mouseEvent -> startPoint = new Point(mouseEvent.getX(), mouseEvent.getY());
	}

	private ChangeListener<Color> fillListener() {
		return (observableValue, color, t1) -> {
			if(!selectedFigureList.isEmpty()) {
				statusPane.updateStatus(String.format("NUEVO COLOR DE RELLENO: %s", borderPicker.getValue()));
				for(Figure figure : selectedFigureList) {
					figure.setFillColor(fillPicker.getValue());
				}
				redrawCanvas();
				canvasHistory.addHistory(getCanvasState());
			}
		};
	}

	private ChangeListener<Color> borderListener() {
		return (observableValue, color, t1) -> {
			if(!selectedFigureList.isEmpty() ) {
				statusPane.updateStatus(String.format("NUEVO COLOR DE BORDE: %s", borderPicker.getValue()));
				for(Figure figure : selectedFigureList) {
					figure.setBorderColor(borderPicker.getValue());
				}
				redrawCanvas();
				canvasHistory.addHistory(getCanvasState());
			}
		};
	}

	private ChangeListener<Boolean> strokeListener() {
		return (observableValue, aBoolean, t1) -> {
			if(!slider.isValueChanging()) {
				if(!selectedFigureList.isEmpty()) {
					statusPane.updateStatus(String.format("NUEVO GROSOR: %.1f", slider.getValue()));
					for(Figure figure : selectedFigureList) {
						figure.setBorderWidth(slider.getValue());
					}
					redrawCanvas();
					canvasHistory.addHistory(getCanvasState());
				}
			}
		};
	}

	private EventHandler<ActionEvent> sendToFrontHandler() {
		return actionEvent ->{
			if(!selectedFigureList.isEmpty()){
				statusPane.updateStatus("EL USUARIO ENVIO AL FRENTE FIGURA(S)");
				for(Figure figure: selectedFigureList) {
					this.canvasState.removeFigure(figure);
					this.canvasState.addFigure(figure);
				}
				redrawCanvas();
				canvasHistory.addHistory(getCanvasState());
			}
			toFront.setSelected(false);
		};
	}

	private EventHandler<ActionEvent> sendToBackHandler() {
		return actionEvent ->{
			if(!selectedFigureList.isEmpty()){
				statusPane.updateStatus("EL USUARIO ENVIO AL FONDO FIGURA(S)");
				for(Figure figure: selectedFigureList) {
					this.canvasState.removeFigure(figure);
					this.canvasState.moveToBack(figure);
				}
				redrawCanvas();
				canvasHistory.addHistory(getCanvasState());
			}
			toBack.setSelected(false);
		};
	}

	private  EventHandler<ActionEvent> undoHandler() {
		return actionEvent -> {
			if(canvasHistory.canUndo()) {
				statusPane.updateStatus("EL USUARIO DES-HIZO UNA OPERACION");
				setCanvasState(canvasHistory.getPreviousState());
				redrawCanvas();
			}
			undo.setSelected(false);
		};
	}

	private  EventHandler<ActionEvent> deleteHandler() {
		return actionEvent -> {
			if(!selectedFigureList.isEmpty()) {
				statusPane.updateStatus("EL USUARIO ELIMINO FIGURA(S)");
				for(Figure figure: selectedFigureList) {
					this.canvasState.removeFigure(figure);
				}
				selectedFigureList.clear();
				redrawCanvas();
				canvasHistory.addHistory(getCanvasState());

			}
			deleteFigure.setSelected(false);
		};
	}

	private  EventHandler<ActionEvent> redoHandler() {
		return actionEvent -> {
			if(canvasHistory.canRedo()) {
				statusPane.updateStatus("EL USUARIO RE-HIZO UNA OPERACION");
				setCanvasState(canvasHistory.getNextState());
				redrawCanvas();
			}
			redo.setSelected(false);
		};
	}

	private void unselectFigureList() {
		for (Figure figure : selectedFigureList)
			figure.unSelect();
		selectedFigureList.clear();
	}

	private void setCanvasState(CanvasState state) {
		this.canvasState = state;
	}

	private CanvasState getCanvasState() {
		return this.canvasState.getClone();
	}

	public GraphicsContext getContext() {
		return gc;
	}

	private void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for(Figure figure : this.canvasState.figures()) {
			figure.drawFigure(gc);
		}
	}

	public StatusPane getStatusPane() {
		return statusPane;
	}
}
