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
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						for (Node node : grid.getChildren()) {
							Integer nodeRow = GridPane.getRowIndex(node);
							if (i == (nodeRow == null ? 0 : nodeRow)) {
								Integer nodeColumn = GridPane.getColumnIndex(node);
								if (j == (nodeColumn == null ? 0 : nodeColumn)) {
									TextCell text = (TextCell) node;
									if (!text.getText().isEmpty()) {
										int nbr = Integer.parseInt(text.getText());
										sudoku.setNbr(i, j, nbr);
									}
								}
							}
						}
					}
				}
				if (sudoku.solve(0, 0)) {
					System.out.println("YES");
					// for(int i = 0; i < 9; i++){
					// for(int j = 0; j < 9; j++){
					// for (Node node : grid.getChildren()) {
					// Integer nodeRow = GridPane.getRowIndex(node);
					// if (i == (nodeRow == null ? 0 : nodeRow)) {
					// Integer nodeColumn = GridPane.getColumnIndex(node);
					// if (j == (nodeColumn == null ? 0 : nodeColumn)) {
					// TextCell text = (TextCell) node;
					// text.replaceText(0, 1, sudoku.getString(i, j));
					// }
					// }
					// }
					// }
					// }
				} else {
					System.out.println("NO");
				}
			}
		});
		clearButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				// code here
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