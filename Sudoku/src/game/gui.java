package game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class gui extends Application {
	private Game sudoku;
	private Button solveButton, clearButton;

	public void start(Stage stage) {
		sudoku = new Game();
		BorderPane root = new BorderPane();
		GridPane grid = new GridPane();
		grid.setVgap(5);
		grid.setHgap(5);
		grid.setPadding(new Insets(5, 5, 5, 5));
		grid.setStyle("-fx-background-color:#606772; -fx-opacity:1;");
		for (int i = 0; i <= 8; i++) {
			for (int j = 0; j <= 8; j++) {
				TextCell text = new TextCell();
				if (i / 3 != 1 && j / 3 != 1 || i / 3 == 1 && j / 3 == 1) {
					text.setStyle("-fx-background-color: #ff9900;");
				}
				text.setEditable(true);
				text.setAlignment(Pos.CENTER);
				text.setMaxSize(30, 30);
				text.setOnKeyPressed(event -> text.setText(event.getText()));
				grid.add(text, i, j);
			}
		}

		HBox buttons = new HBox();
		buttons.setSpacing(5);
		buttons.setPadding(new Insets(10, 10, 20, 10));
		buttons.setStyle("-fx-background-color:#D3D3D3; -fx-opacity:1;");
		solveButton = new Button("Solve");
		clearButton = new Button("Clear");
		solveButton.setAlignment(Pos.CENTER);
		clearButton.setAlignment(Pos.CENTER);
		solveButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				sudoku = new Game();
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						for (Node node : grid.getChildren()) {
							int nodeRow = GridPane.getRowIndex(node);
							if (i == nodeRow) {
								int nodeColumn = GridPane.getColumnIndex(node);
								if (j == nodeColumn) {
									TextCell field = (TextCell) node;
									String text = field.getText();
									if (!text.isEmpty()) {
										if (text.matches("[1-9]+")) {
											int nbr = Integer.parseInt(text);
											sudoku.setNbr(i, j, nbr);
										} else {
											Dialogs.alert("Wrong input", null,
													"You can only input numbers from 1 to 9.");
											return;
										}
									}
								}
							}
						}
					}
				}
				if (sudoku.solve()) {
					for (int i = 0; i < 9; i++) {
						for (int j = 0; j < 9; j++) {
							for (Node node : grid.getChildren()) {
								int nodeRow = GridPane.getRowIndex(node);
								if (i == nodeRow) {
									int nodeColumn = GridPane.getColumnIndex(node);
									if (j == nodeColumn) {
										TextCell text = (TextCell) node;
										text.setText(Integer.toString(sudoku.getNbr(i, j)));
									}
								}
							}
						}
					}
				} else {
					Dialogs.alert("Fail", null, "Could not solve the sudoku.");
				}
			}
		});
		clearButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				for (Node node : grid.getChildren()) {
					TextCell text = (TextCell) node;
					text.clear();

				}
				sudoku = new Game();
			}
		});
		buttons.getChildren().addAll(solveButton, clearButton);

		root.setTop(grid);
		root.setBottom(buttons);

		Scene scene = new Scene(root, 310, 350);

		stage.setResizable(false);
		stage.setScene(scene);
		stage.setTitle("Sudoku");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}