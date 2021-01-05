package views;

import game.Game;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


public class MainController {
	private Game board;
	
	private Image img = new Image("img/icon.png");
	
	@FXML 
	AnchorPane ap;
	
	@FXML
	AnchorPane ap2;
	
	@FXML
	Pane p1;
	
	@FXML
	Pane p2;
	
	@FXML
	Pane p3;
	
	@FXML
	Pane p4;
	
	@FXML
	ImageView Iv;
	
	@FXML
	ImageView Iv2;
	
	
	@FXML
	private void initialize() {
		board = new Game(ap, ap2, p1, p2, p3, p4, Iv);
		board.setting();
		Iv2.setImage(img);
	}
	
	public void clickHandle(MouseEvent e) {
		board.clickHandle(e);
	}
	
	public void setting() {
		Util.alertSetting(board);
	}
}
