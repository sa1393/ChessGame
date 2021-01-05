package piece;

import game.Game;
import javafx.scene.image.Image;

public class Bishop extends ChessPiece {
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

	@Override
	public void setPieceLocation(int x2, int y2) {
		this.x = x2;
		this.y = y2;
	}

	public Bishop(String color, int x, int y) {
		Image img = new Image("img/" + color + "_Bishop.png");
		imageView.setImage(img);

		this.x = x;
		this.y = y;
		this.color = color;
	}

	@Override
	public Boolean canMove(int desX, int desY) {
		int i, j;
		int k, l;

		int dirX = desX - x;
		int dirY = desY - y;

		if (!(Math.abs(dirX) == Math.abs(dirY))) {
			return false;
		}

		if (dirX < 0) {
			k = -1;
		} else {
			k = 1;
		}

		if (dirY < 0) {
			l = -1;
		} else {
			l = 1;
		}

		for (i = k, j = l; (i != dirX) && (j != dirY); i = i + k, j = j + l) {
			if (checkPiece(x + i, y + j)) {
				return false;
			}
		}
		x = desX;
		y = desY;
		return true;
	}

	public String getColor() {
		return color;
	}

	@Override
	public void moveColor() {
		Image img = new Image("img/Blue.PNG");
		Image img2 = new Image("img/Red.PNG");
		Image img3 = new Image("img/Green.png");
		Game.imageView[x][y].setImage(img3);

		int i, j;
		for (i = 1, j = 1; x + i < 8 && y + j < 8; i++, j++) {
			if (MoveCheck(x, y, x + i, y + j, color))
				continue;
			if (Game.piece[x + i][y + j] != null) {
				if (Game.piece[x + i][y + j].getColor().equals(color))
					break;
				Game.imageView[x + i][y + j].setImage(img2);
				break;
			}
			Game.imageView[x + i][y + i].setImage(img);
		}
		for (i = 1, j = 1; x - i >= 0 && y + j < 8; i++, j++) {
			if (MoveCheck(x, y, x - i, y + j, color))
				continue;
			if (Game.piece[x - i][y + j] != null) {
				if (Game.piece[x - i][y + j].getColor().equals(color))
					break;
				Game.imageView[x - i][y + j].setImage(img2);
				break;
			}
			Game.imageView[x - i][y + i].setImage(img);
		}
		for (i = 1, j = 1; x + i < 8 && y - j >= 0; i++, j++) {
			if (MoveCheck(x, y, x + i, y - j, color))
				continue;
			if (Game.piece[x + i][y - j] != null) {
				if (Game.piece[x + i][y - j].getColor().equals(color))
					break;
				Game.imageView[x + i][y - j].setImage(img2);
				break;
			}
			Game.imageView[x + i][y - i].setImage(img);
		}
		for (i = 1, j = 1; x - i >= 0 && y - j >= 0; i++, j++) {
			if (MoveCheck(x, y, x - i, y - j, color))
				continue;
			if (Game.piece[x - i][y - j] != null) {
				if (Game.piece[x - i][y - j].getColor().equals(color))
					break;
				Game.imageView[x - i][y - j].setImage(img2);
				break;
			}
			Game.imageView[x - i][y - i].setImage(img);
		}
	}

	@Override
	public String check() {
		String king = null;
		int i, j;
		for (i = 1, j = 1; x + i < 8 && y + j < 8; i++, j++) {
			if (Game.piece[x + i][y + j] != null) {
				if (Game.piece[x + i][y + j].getColor().equals(color))
					break;
				if (Game.piece[x + i][y + j].getName().equals("King"))
					king = Game.piece[x + i][y + j].getColor();
				break;
			}
		}
		for (i = 1, j = 1; x - i >= 0 && y + j < 8; i++, j++) {
			if (Game.piece[x - i][y + j] != null) {
				if (Game.piece[x - i][y + j].getColor().equals(color))
					break;
				if (Game.piece[x - i][y + j].getName().equals("King"))
					king = Game.piece[x - i][y + j].getColor();
				break;
			}
		}
		for (i = 1, j = 1; x + i < 8 && y - j >= 0; i++, j++) {
			if (Game.piece[x + i][y - j] != null) {
				if (Game.piece[x + i][y - j].getColor().equals(color))
					break;
				if (Game.piece[x + i][y - j].getName().equals("King"))
					king = Game.piece[x + i][y - j].getColor();
				break;
			}
		}
		for (i = 1, j = 1; x - i >= 0 && y - j >= 0; i++, j++) {
			if (Game.piece[x - i][y - j] != null) {
				if (Game.piece[x - i][y - i].getColor().equals(color))
					break;
				if (Game.piece[x - i][y - i].getName().equals("King"))
					king = Game.piece[x - i][y - j].getColor();
				break;
			}
		}
		return king;
	}

	@Override
	public void checkMate() {
		int i, j;
		for (i = 1, j = 1; x + i < 8 && y + j < 8; i++, j++) {
			if (Game.piece[x + i][y + j] != null) {
				if (Game.piece[x + i][y + j].getColor().equals(color))
					break;
				if (MoveCheck(x, y, x + i, y + j, color))
					continue;
				Game.CheckMate++;
				break;
			}
			else {
				if (MoveCheck(x, y, x + i, y + j, color))
					continue;
				Game.CheckMate++;
			}
		}
		for (i = 1, j = 1; x - i >= 0 && y + j < 8; i++, j++) {
			if (Game.piece[x - i][y + j] != null) {
				if (Game.piece[x - i][y + j].getColor().equals(color))
					break;
				if (MoveCheck(x, y, x - i, y + j, color))
					continue;
				Game.CheckMate++;
				break;
			}
			else {
				if (MoveCheck(x, y, x - i, y + j, color))
					continue;
				Game.CheckMate++;
			}
		}
		for (i = 1, j = 1; x + i < 8 && y - j >= 0; i++, j++) {
			if (Game.piece[x + i][y - j] != null) {
				if (Game.piece[x + i][y - j].getColor().equals(color))
					break;
				if (MoveCheck(x, y, x + i, y - j, color))
					continue;
				Game.CheckMate++;
				break;
			}
			else {
				if (MoveCheck(x, y, x + i, y - j, color))
					continue;
				Game.CheckMate++;
			}
		}
		for (i = 1, j = 1; x - i >= 0 && y - j >= 0; i++, j++) {
			if (Game.piece[x - i][y - j] != null) {
				if (Game.piece[x - i][y - i].getColor().equals(color))
					break;
				if (MoveCheck(x, y, x - i, y - j, color))
					continue;
				Game.CheckMate++;
				break;
			}
			else {
				if (MoveCheck(x, y, x - i, y - j, color))
					continue;
				Game.CheckMate++;
			}
		}
	}

}
