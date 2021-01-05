package piece;

import game.Game;
import javafx.scene.image.Image;

public class Knight extends ChessPiece {
	int x;
	int y;

	String color;

	@Override
	public Boolean getTrance() {
		return false;
	}

	@Override
	public void setTrance() {
	}

	public Knight(String color, int x, int y) {
		Image img = new Image("img/" + color + "_Knight.png");
		imageView.setImage(img);

		this.x = x;
		this.y = y;
		this.color = color;
	}

	public String getColor() {
		return color;
	}

	@Override
	public void setPieceLocation(int x2, int y2) {
		this.x = x2;
		this.y = y2;
	}

	@Override
	public Boolean canMove(int desX, int desY) {
		for (int i = -2; i <= 2; i++) {
			for (int j = -2; j <= 2; j++) {
				if ((Math.abs(i) == Math.abs(j)) || i == 0 || j == 0) {
					continue;
				}
				if ((x + i == desX) && (y + j == desY)) {
					x = desX;
					y = desY;
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void moveColor() {
		Image img = new Image("img/Blue.PNG");
		Image img2 = new Image("img/Red.PNG");
		Image img3 = new Image("img/Green.png");
		Game.imageView[x][y].setImage(img3);

		for (int i = -2; i <= 2; i++) {
			if (i == 0)
				continue;
			for (int j = -2; j <= 2; j++) {
				if (j == 0)
					continue;
				if (Math.abs(i) == Math.abs(j) || i == 0 || j == 0) {
					continue;
				}
				if (x + i < 0 || x + i > 7 || y + j < 0 || y + j > 7)
					continue;
				if (MoveCheck(x, y, x + i, y + j, color))
					continue;
				if (Game.piece[x + i][y + j] != null) {
					if (Game.piece[x + i][y + j].getColor().equals(color))
						continue;
					Game.imageView[x + i][y + j].setImage(img2);
				} else {
					Game.imageView[x + i][y + j].setImage(img);
				}

			}
		}
	}

	@Override
	public String check() {
		String king = null;
		for (int i = -2; i <= 2; i++) {
			if (i == 0)
				continue;
			for (int j = -2; j <= 2; j++) {
				if (j == 0)
					continue;
				if (Math.abs(i) == Math.abs(j)) {
					continue;
				}
				if (x + i < 0 || x + i > 7 || y + j < 0 || y + j > 7)
					continue;
				if (Game.piece[x + i][y + j] != null) {
					if (Game.piece[x + i][y + j].getColor().equals(color))
						continue;
					if (Game.piece[x + i][y + j].getName().equals("King"))
						king = Game.piece[x + i][y + j].getColor();
				}
			}
		}
		return king;
	}

	@Override
	public void checkMate() {

		for (int i = -2; i <= 2; i++) {
			if (i == 0)
				continue;
			for (int j = -2; j <= 2; j++) {
				if (j == 0)
					continue;
				if (Math.abs(i) == Math.abs(j)) {
					continue;
				}
				if (x + i < 0 || x + i > 7 || y + j < 0 || y + j > 7)
					continue;
				if (Game.piece[x + i][y + j] != null) {
					if (Game.piece[x + i][y + j].getColor().equals(color))
						continue;
					if (MoveCheck(x, y, x + i, y + j, color))
						continue;
					Game.CheckMate++;
				} else {
					if (MoveCheck(x, y, x + i, y + j, color))
						continue;
					Game.CheckMate++;
				}

			}
		}
	}

}
