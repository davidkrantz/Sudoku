package game;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class gui extends Application {
	public void start(Stage stage) {
		BorderPane root = new BorderPane();
		GridPane tile = new GridPane();
		tile.setVgap(5);
		tile.setHgap(5);
		tile.setPadding(new Insets(5, 5, 5, 5));
		tile.setStyle("-fx-background-color:#606772; -fx-opacity:1;");
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
				tile.add(text, i, j);
			}
		}

		HBox buttons = new HBox();
		buttons.setSpacing(5);
		buttons.setPadding(new Insets(10, 10, 20, 10));
		buttons.setStyle("-fx-background-color:#D3D3D3; -fx-opacity:1;");
		Button solve = new Button("Solve");
		Button clear = new Button("Clear");
		solve.setAlignment(Pos.CENTER);
		buttons.getChildren().addAll(solve, clear);

		root.setTop(tile);
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