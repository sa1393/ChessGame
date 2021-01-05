package piece;

import game.Game;
import javafx.scene.image.Image;

public class King extends ChessPiece {
	int x;
	int y;
	Boolean Kcan = true;

	String color;

	@Override
	public Boolean getTrance() {
		return Kcan;
	}

	@Override
	public void setPieceLocation(int x2, int y2) {
		this.x = x2;
		this.y = y2;
	}

	public King(String color, int x, int y) {
		Image img = new Image("img/" + color + "_King.png");
		imageView.setImage(img);

		this.x = x;
		this.y = y;
		this.color = color;
	}

	public String getColor() {
		return color;
	}

	@Override
	public void setTrance() {
		Kcan = false;
	}

	@Override
	public Boolean canMove(int desX, int desY) {
		int dirX = desX - x;
		int dirY = desY - y;
		if ((Math.abs(dirX) < 2) && (Math.abs(dirY) < 2)) {
			x = desX;
			y = desY;

			Kcan = false;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void moveColor() {
		Image img3 = new Image("img/Green.png");
		Game.imageView[x][y].setImage(img3);
		Image img = new Image("img/Blue.PNG");
		Image img2 = new Image("img/Red.PNG");
		go: if (Game.piece[0][y] != null && Game.piece[0][y].getTrance() && Kcan) {
			for (int i = -1; i + x > 0; i--) {
				if (Game.piece[x + i][y] != null)
					break go;
			}
			Game.imageView[x - 2][y].setImage(img);
		}
		go: if (Game.piece[7][y] != null && Game.piece[7][y].getTrance() && Kcan) {
			for (int i = 1; i + x < 7; i++) {
				if (Game.piece[x + i][y] != null)
					break go;
			}
			Game.imageView[x + 2][y].setImage(img);
		}

		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (i == 0 && j == 0)
					continue;
				if (x + i < 0 || x + i > 7 || y + j < 0 || y + j > 7)
					continue;
				if (MoveCheck(x, y, x + i, y + j, color))
					continue;
				if (Game.piece[i + x][y + j] != null) {
					if (!(Game.piece[x + i][y + j].getColor().equals(color))) {
						Game.imageView[i + x][j + y].setImage(img2);
					}
				} else
					Game.imageView[i + x][j + y].setImage(img);
			}
		}
	}

	@Override
	public String check() {
		String king = null;

		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (i == 0 && j == 0)
					continue;
				if (x + i < 0 || x + i > 7 || y + j < 0 || y + j > 7)
					continue;

				if (Game.piece[i + x][y + j] != null) {
					if (!(Game.piece[x + i][y + j].getColor().equals(color))) {
						if (Game.piece[i + x][j + y].getName().equals("King"))
							king = Game.piece[x + i][y + j].getColor();
					}
				}
			}
		}
		return king;
	}

	@Override
	public void checkMate() {
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (i == 0 && j == 0)
					continue;
				if (x + i < 0 || x + i > 7 || y + j < 0 || y + j > 7)
					continue;
				if (Game.piece[i + x][y + j] != null) {
					if (!(Game.piece[x + i][y + j].getColor().equals(color))) {
						if (MoveCheck(x, y, x + i, y + j, color))
							continue;
						Game.CheckMate++;
					}
				} else {
					if (MoveCheck(x, y, x + i, y + j, color))
						continue;
					Game.CheckMate++;
				}
			}
		}
	}

}
