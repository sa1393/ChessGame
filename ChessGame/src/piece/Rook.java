package piece;

import game.Game;
import javafx.scene.image.Image;

public class Rook extends ChessPiece {
	int x;
	int y;
	Boolean Rcan = true;

	String color;
	
	@Override
	public Boolean getTrance() {
		return Rcan;
	}
	
	@Override
	public void setTrance() {
		Rcan = false;
	}

	public String getColor() {
		return color;
	}

	@Override
	public void setPieceLocation(int x2, int y2) {
		this.x = x2;
		this.y = y2;
	}

	public Rook(String color, int x, int y) {
		Image img = new Image("img/" + color + "_Rook.png");
		imageView.setImage(img);

		this.x = x;
		this.y = y;

		this.color = color;
	}

	@Override
	public Boolean canMove(int desX, int desY) {
		int dirX = desX - x;
		int dirY = desY - y;

		int k, l;

		if (!((x == desX) || (y == desY))) {
			return false;
		}

		if (dirX > 0) {
			k = 1;
		} else if (dirX < 0) {
			k = -1;
		} else {
			k = 0;
		}

		if (dirY > 0) {
			l = 1;
		} else if (dirY < 0) {
			l = -1;
		} else {
			l = 0;
		}

		for (int i = k, j = l; (i != dirX) || (j != dirY); i = i + k, j = j + l) {
			if (checkPiece(x + i, y + j)) {
				return false;
			}
		}

		x = desX;
		y = desY;
		Rcan = false;
		return true;

	}

	@Override
	public void moveColor() {
		Image img = new Image("img/Blue.PNG");
		Image img2 = new Image("img/Red.PNG");
		Image img3 = new Image("img/Green.png");
		Game.imageView[x][y].setImage(img3);
		int i;
		for (int j = -1; j < 2; j++) {
			if (j == 0)
				continue;
			for (i = j; x + i >= 0 && x + i < 8; i = i + j) {
				if (MoveCheck(x, y, x + i, y, color))
					continue;
				if (Game.piece[x + i][y] != null) {
					if (Game.piece[x + i][y].getColor().equals(color))
						break;
					Game.imageView[x + i][y].setImage(img2);
					break;
				}
				Game.imageView[x + i][y].setImage(img);
			}

			for (i = j; y + i >= 0 && y + i < 8; i = i + j) {
				if (MoveCheck(x, y, x, y + i, color))
					continue;
				if (Game.piece[x][y + i] != null) {
					for (i = j; y + i >= 0 && y + i < 8; i = i + j) {
						if (Game.piece[x][y + i] != null) {
							break;
						}
					}
					if (Game.piece[x][y + i].getColor().equals(color))
						break;
					Game.imageView[x][y + i].setImage(img2);
					break;
				}
				Game.imageView[x][y + i].setImage(img);
			}
		}
	}

	@Override
	public String check() {
		String king = null;
		int i;
		for (int j = -1; j < 2; j++) {
			if (j == 0)
				continue;
			for (i = j; x + i >= 0 && x + i < 8; i = i + j) {
				if (Game.piece[x + i][y] != null) {
					if (Game.piece[x + i][y].getColor().equals(color))
						break;
					if (Game.piece[x + i][y].getName().equals("King"))
						king = Game.piece[x + i][y].getColor();
					break;
				}
			}

			for (i = j; y + i >= 0 && y + i < 8; i = i + j) {
				if (Game.piece[x][y + i] != null) {
					if (Game.piece[x][y + i].getColor().equals(color))
						break;
					if (Game.piece[x][y + i].getName().equals("King"))
						king = Game.piece[x][y + i].getColor();
					break;
				}
			}
		}
		return king;
	}

	@Override
	public void checkMate() {

		int i;
		for (int j = -1; j < 2; j++) {
			if (j == 0)
				continue;
			for (i = j; x + i >= 0 && x + i < 8; i = i + j) {
				if (Game.piece[x + i][y] != null) {
					if (Game.piece[x + i][y].getColor().equals(color))
						break;
					if (MoveCheck(x, y, x + i, y, color))
						continue;
					Game.CheckMate++;
					break;
				}
				if (MoveCheck(x, y, x + i, y, color))
					continue;
				Game.CheckMate++;
			}

			for (i = j; y + i >= 0 && y + i < 8; i = i + j) {
				if (Game.piece[x][y + i] != null) {
					if (Game.piece[x][y + i].getColor().equals(color))
						break;
					if (MoveCheck(x, y, x, y + i, color))
						continue;
					Game.CheckMate++;
					break;
				}
				if (MoveCheck(x, y, x, y + i, color))
					continue;
				Game.CheckMate++;
				
			}
		}
	}
}
