package views;

import java.util.Optional;

import game.Game;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import piece.Bishop;
import piece.Knight;
import piece.Queen;
import piece.Rook;

public class Util {
	public static void alert(String msg) {
		Alert a = new Alert(AlertType.INFORMATION);
		a.setHeaderText(null);
		a.setTitle("Chess");
		a.setContentText(msg);
		a.show();
	}

	public static void alertPro(String msg, int x, int y, String color, AnchorPane ap) {
		Alert a = new Alert(AlertType.INFORMATION);
		a.setHeaderText(null);
		a.setTitle("Chess");
		a.setContentText(msg);

		ButtonType Knight = new ButtonType("Knight");
		ButtonType Bishop = new ButtonType("Bishop");
		ButtonType Rook = new ButtonType("Rook");
		ButtonType Queen = new ButtonType("Queen");
		a.getButtonTypes().setAll(Knight, Bishop, Rook, Queen);

		Optional<ButtonType> result = a.showAndWait();
		if (result.get() == Knight) {
			Game.piece[x][y].death(x, y, Game.piece[x][y].getimageView(), ap, null, null);
			Game.piece[x][y] = new Knight(color, x, y);
			Game.piece[x][y].setsize(100, 100);
			Game.piece[x][y].setlocation(x * 100, y * 100);
			ap.getChildren().add(Game.piece[x][y].getimageView());
		}
		else if (result.get() == Bishop) {
			Game.piece[x][y].death(x, y, Game.piece[x][y].getimageView(), ap, null, null);
			Game.piece[x][y] = new Bishop(color, x, y);
			Game.piece[x][y].setsize(100, 100);
			Game.piece[x][y].setlocation(x * 100, y * 100);
			ap.getChildren().add(Game.piece[x][y].getimageView());
		}
		else if (result.get() == Rook) {
			Game.piece[x][y].death(x, y, Game.piece[x][y].getimageView(), ap, null, null);
			Game.piece[x][y] = new Rook(color, x, y);
			Game.piece[x][y].setsize(100, 100);
			Game.piece[x][y].setlocation(x * 100, y * 100);
			ap.getChildren().add(Game.piece[x][y].getimageView());
		}
		else if (result.get() == Queen) {
			Game.piece[x][y].death(x, y, Game.piece[x][y].getimageView(), ap, null, null);
			Game.piece[x][y] = new Queen(color, x, y);
			Game.piece[x][y].setsize(100, 100);
			Game.piece[x][y].setlocation(x * 100, y * 100);
			ap.getChildren().add(Game.piece[x][y].getimageView());
		}
	}
	
	public static void alertSetting(Game game) {
		Alert a = new Alert(AlertType.INFORMATION);
		a.setHeaderText(null);
		a.setTitle("Chess");
		a.setContentText(null);

		ButtonType reStart = new ButtonType("ReStart");
		ButtonType none = new ButtonType("cancel");
		a.getButtonTypes().setAll(reStart, none);

		Optional<ButtonType> result = a.showAndWait();
		
		if(result.get() == reStart) {
			game.boardReset();
			game.GameStart();
		}
		else {
			return;
		}
	}
}
