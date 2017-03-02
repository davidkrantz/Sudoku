package game;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class gui extends Application {
	public void start(Stage stage) {
		BorderPane root = new BorderPane();
		TilePane tile = new TilePane();
		tile.setMaxHeight(410);
		tile.setMaxWidth(410);
		tile.setVgap(5);
		// root.setTileAlignment(Pos.CENTER);
		tile.setHgap(5);
		tile.setPadding(new Insets(5, 5, 5, 5));
		tile.setStyle("-fx-background-color:#778899; -fx-opacity:1;");
		tile.setPrefColumns(9);
		tile.setPrefRows(9);
		final int SIZE = 40;
		for (int i = 1; i <= 9; i++) {
			for (int k = 1; k <= 9; k++) {
				Label label = new Label();
				TextCell text = new TextCell();
				label.setPrefSize(SIZE, SIZE);
				if (i % 2 != 0 && k % 2 != 0 || i % 2 == 0 && k % 2 == 0) {
					label.setStyle("-fx-background-color: #000000;");
					text.setAlignment(Pos.CENTER);
					text.setMaxSize(10, 10);
					text.replaceSelection("H");
				}
				tile.getChildren().addAll(label, text);
			}
		}

		HBox buttons = new HBox();
		buttons.setSpacing(5);
		buttons.setPadding(new Insets(10, 10, 20, 10));
		buttons.setStyle("-fx-background-color:#D3D3D3; -fx-opacity:1;");
		//buttons.setAlignment(Pos.CENTER);
		Button solve = new Button("Solve");
		Button clear = new Button("Clear");
		solve.setAlignment(Pos.CENTER);
		buttons.getChildren().addAll(solve, clear);

		root.setTop(tile);
		root.setBottom(buttons);
		root.setPrefSize(600, 600);
		
		Scene scene = new Scene(root, 410, 455);

		stage.setResizable(false);
		stage.setScene(scene);
		stage.setTitle("Sudoku");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}