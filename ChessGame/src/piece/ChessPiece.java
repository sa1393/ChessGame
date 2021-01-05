package piece;

import game.Game;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public abstract class ChessPiece {
	ImageView imageView = new ImageView();
	String color;
	public static int BDcount = 0;
	public static int WDcount = 0;
	
	public String getName() {
		return getClass().getSimpleName();
	}
	
	public Boolean MoveCheck(int x, int y, int desX, int desY, String color) {
		String color2;
		if(color.equals("White"))color2 = "Black";
		else color2 = "White";
		
		ChessPiece p = null;
		ChessPiece p2 = null;
		
		Boolean can = false;
		
		if(Game.piece[desX][desY] != null) {
			p = Game.piece[x][y];
			p2 = Game.piece[desX][desY];
			
			Game.piece[x][y] = null;
			Game.piece[desX][desY] = null;
			Game.piece[desX][desY] = p;
			Game.piece[desX][desY].setPieceLocation(desX, desY);
			
			can = Game.Chesscheck(color2);
			
			Game.piece[x][y] = p;
			Game.piece[desX][desY] = p2;
			Game.piece[x][y].setPieceLocation(x, y);
		}
		else if(Game.piece[desX][desY] == null){
			p = Game.piece[x][y];
			Game.piece[x][y] = null;
			Game.piece[desX][desY] = p;
			
			Game.piece[desX][desY].setPieceLocation(desX, desY);
			
			can = Game.Chesscheck(color2);
			
			Game.piece[desX][desY] = null;
			Game.piece[x][y] = p;
			Game.piece[x][y].setPieceLocation(x, y);
		}
		return can;
	}
	
	protected Boolean checkPiece(int a, int b) {
		return Game.piece[a][b] != null; 
	}
	
	public void setsize(double size1, double size2) {
		imageView.setFitWidth(size1);
		imageView.setFitHeight(size2);
	}
	 
	public void setlocation(double x, double y) {
		imageView.setX(x);
		imageView.setY(y);
	}
	public abstract void setPieceLocation(int x, int y);
	
	public ImageView getimageView() {
		return imageView;
	}
	
	public void move(ImageView imageView, int x, int y, int desX, int desY) {
		ChessPiece p = Game.piece[x][y];
		Game.piece[x][y] = null;
		Game.piece[desX][desY] = p;
		
		imageView.setX(desX * 100);
		imageView.setY(desY * 100);
	}
	
	public String getColor() {
		return color;
	}
	
	public abstract Boolean canMove(int desX, int desY);
	
	public void death(int x, int y, ImageView iv, AnchorPane ap, Pane p3, Pane p4) {
		ap.getChildren().remove(iv);
		if(p3 == null)return;
		if(Game.piece[x][y].getColor().equals("Black")) {
			p3.getChildren().add(iv);
			iv.setX((BDcount % 4) * 75);
			iv.setY((BDcount / 4) * 75);
			BDcount++;
		}
		else{
			p4.getChildren().add(iv);
			iv.setX((WDcount % 4) * 75);
			iv.setY((WDcount / 4) * 75);
			WDcount++;
		}
		
		iv.setFitWidth(75);
		iv.setFitWidth(75);
		
		Game.piece[x][y] = null;
	}
	
	public abstract void moveColor();
	
	public abstract String check();
	
	public abstract void checkMate();
	
	public abstract Boolean getTrance();
	
	public abstract void setTrance();

}
