package piece;

import game.Game;
import javafx.scene.image.Image;

public class Pawn extends ChessPiece {
	private Boolean firstMove = true;
	public Boolean firstMoveTrance = false;
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

	private void moveLocation(int desX, int desY) {
		this.x = desX;
		this.y = desY;
	}

	public Pawn(String color, int x, int y) {
		Image img = new Image("img/" + color + "_Pawn.png");
		imageView.setImage(img);

		this.x = x;
		this.y = y;
		this.color = color;
	}

	@Override
	public void setPieceLocation(int x2, int y2) {
		this.x = x2;
		this.y = y2;
	}

	public void setFirstMove() {
		this.firstMove = true;
	}

	@Override
	public Boolean canMove(int desX, int desY) {
		if (color.equals("Black")) {
			if (y == 1)
				firstMove = true;
			int dirX = desX - x;
			int dirY = desY - y;
			if (dirX == 0) {
				if (Game.piece[desX][desY] != null)
					return false;
				if (firstMove) {
					if (desY > y && dirY < 3) {
						if (dirY == 2) {
							if (Game.piece[x][desY - 1] != null)
								return false;
						}
						firstMove = false;
						moveLocation(desX, desY);
						return true;
					} else {
						return false;
					}
				} else {
					if (desY > y && dirY < 2) {
						moveLocation(desX, desY);
						return true;
					} else {
						return false;
					}
				}
							} else {
				if ((Math.abs(dirX) == 1) && dirY < 2 && desY > y) {
					if (Game.piece[desX][desY] != null) {
						moveLocation(desX, desY);
						return true;
					}
				}
			}

		}

		if (color.equals("White")) {
			if (y == 6)
				firstMove = true;
			int dirX = x - desX;
			int dirY = y - desY;
			if (dirX == 0) {
				if (Game.piece[desX][desY] != null)
					return false;
				if (firstMove) {
					if (desY < y && dirY < 3) {
						if (dirY == 2) {
							if (Game.piece[x][desY + 1] != null)
								return false;
						}
						firstMove = false;
						moveLocation(desX, desY);
						return true;
					} else {
						return false;
					}
				} else {
					if (desY < y && dirY < 2) {
						moveLocation(desX, desY);
						return true;
					} else {
						return false;
					}
				}
			} else {
				if ((Math.abs(dirX) == 1) && dirY < 2 && desY < y) {
					if (Game.piece[desX][desY] != null) {
						moveLocation(desX, desY);
						return true;
					}
				}
			}

		}
		return false;
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
		for (int u = 0; u < 1; u++) {
			if (color.equals("Black")) {
				if (y == 1)
					firstMove = true;
				if (firstMove) {
					int i;
					for (i = 1; i <= 2; i++) {
						if (Game.piece[x][y + i] == null) {
							if (MoveCheck(x, y, x, y + i, color))
								continue;
							Game.imageView[x][y + i].setImage(img);
							Game.CheckMate++;
						} else {

							break;

						}
					}

					i = 1;
					int j = 1;

					if (!(x + i > 7)) {
						if (Game.piece[x + i][y + j] != null) {
							if (Game.piece[x + i][y + j].getColor().equals(color))
								return;
							if (!(MoveCheck(x, y, x + i, y + j, color)))
								Game.imageView[x + i][y + j].setImage(img2);
						}
					}

					if (!(x - i < 0)) {
						if (Game.piece[x - i][y + j] != null) {
							if (Game.piece[x - i][y + j].getColor().equals(color))
								return;
							if (!(MoveCheck(x, y, x - i, y + j, color)))
								Game.imageView[x - i][y + j].setImage(img2);
						}
					}
				} else {
					int i = 1, j = 1;
					if (y + j > 7)
						break;
					if (Game.piece[x][y + j] == null) {
						if (!(MoveCheck(x, y, x, y + j, color)))
							Game.imageView[x][y + j].setImage(img);
					}
					if (x - i >= 0) {
						if (Game.piece[x - i][y + j] != null) {
							if (Game.piece[x - i][y + j].getColor().equals(color))
								return;
							if (!(MoveCheck(x, y, x - i, y + j, color)))
								Game.imageView[x - i][y + j].setImage(img2);
						}
					}
					if (x + i <= 7) {
						if (Game.piece[x + i][y + j] != null) {
							if (Game.piece[x + i][y + j].getColor().equals(color))
								return;
							if (!(MoveCheck(x, y, x + i, y + j, color)))
								Game.imageView[x + i][y + j].setImage(img2);
						}
					}

				}
			}
			if (color.equals("White")) {
				if (y == 7)
					firstMove = true;
				if (firstMove) {
					int i;
					for (i = 1; i <= 2; i++) {
						if (Game.piece[x][y - i] == null) {
							if (MoveCheck(x, y, x, y - i, color))
								continue;
							Game.imageView[x][y - i].setImage(img);
						} else {
							break;
						}
					}

					i = 1;
					int j = 1;

					if (!(x + i > 7)) {
						if (Game.piece[x + i][y - j] != null) {
							if (Game.piece[x + i][y - j].getColor().equals(color))
								return;
							if (!(MoveCheck(x, y, x + i, y - j, color)))
								Game.imageView[x + i][y - j].setImage(img2);

						}
					}

					if (!(x - i < 0)) {
						if (Game.piece[x - i][y - j] != null) {
							if (Game.piece[x - i][y - j].getColor().equals(color))
								return;
							if (!(MoveCheck(x, y, x - i, y - j, color)))
								Game.imageView[x - i][y - j].setImage(img2);
						}
					}
				} else {
					int i = 1, j = 1;
					if (y - j < 0)
						break;
					if (Game.piece[x][y - j] == null) {
						if (!(MoveCheck(x, y, x, y - i, color)))
							Game.imageView[x][y - j].setImage(img);
					}
					if (x - i >= 0) {
						if (Game.piece[x - i][y - j] != null) {
							if (Game.piece[x - i][y - j].getColor().equals(color))
								return;
							if (!(MoveCheck(x, y, x - i, y - j, color)))
								Game.imageView[x - i][y - j].setImage(img2);
						}
					}
					if (x + i <= 7) {
						if (Game.piece[x + i][y - j] != null) {
							if (Game.piece[x + i][y - j].getColor().equals(color))
								return;
							if (!(MoveCheck(x, y, x + i, y - j, color)))
								Game.imageView[x + i][y - j].setImage(img2);
						}
					}
				}
			}
		}
	}

	@Override
	public String check() {
		String king = null;
		for (int u = 0; u < 1; u++) {
			if (color.equals("Black")) {
				if (firstMove) {
					int i;
					i = 1;
					int j = 1;

					if (!(x + i > 7)) {
						if (Game.piece[x + i][y + j] != null) {
							if (Game.piece[x + i][y + j].getName().equals("King"))
								king = Game.piece[x + i][y + j].getColor();
						}
					}

					if (!(x - i < 0)) {
						if (Game.piece[x - i][y + j] != null) {
							if (Game.piece[x - i][y + j].getName().equals("King"))
								king = Game.piece[x - i][y + j].getColor();
						}
					}
				} else {
					int i = 1, j = 1;
					if(y+j > 7)break;

					if (x - i >= 0) {
						if (Game.piece[x - i][y + j] != null) {
							if (Game.piece[x - i][y + j].getName().equals("King"))
								king = Game.piece[x - i][y + j].getColor();
						}
					}

					if (x + i <= 7) {
						if (Game.piece[x + i][y + j] != null) {
							if (Game.piece[x + i][y + j].getName().equals("King"))
								king = Game.piece[x + i][y + j].getColor();
						}
					}

				}
			}

			if (color.equals("White")) {
				if (firstMove) {
					int i;
					i = 1;
					int j = 1;

					if (!(x + i > 7)) {
						if (Game.piece[x + i][y - j] != null) {
							if (Game.piece[x + i][y - j].getName().equals("King"))
								king = Game.piece[x + i][y - j].getColor();
						}
					}

					if (!(x - i < 0)) {
						if (Game.piece[x - i][y - j] != null) {
							if (Game.piece[x - i][y - j].getName().equals("King"))
								king = Game.piece[x - i][y - j].getColor();
						}
					}
				} else {
					int i = 1, j = 1;
					if(y-j < 0)break;
					if (Game.piece[x][y + j] == null) {
					}

					if (x - i >= 0) {
						if (Game.piece[x - i][y - j] != null) {
							if (Game.piece[x - i][y - j].getName().equals("King"))
								king = Game.piece[x - i][y - j].getColor();
						}
					}

					if (x + i <= 7) {
						if (Game.piece[x + i][y - j] != null) {
							if (Game.piece[x + i][y - j].getName().equals("King"))
								king = Game.piece[x + i][y - j].getColor();
						}
					}

				}
			}
		}
		return king;
	}

	@Override
	public void checkMate() {
		for (int u = 0; u < 1; u++) {
			if (color.equals("Black")) {
				if (firstMove) {
					int i;

					for (i = 1; i <= 2; i++) {
						if (Game.piece[x][y + i] == null) {
							if (MoveCheck(x, y, x, y + i, color))
								continue;
							Game.CheckMate++;
							continue;
						} else {
							break;
						}
					}

					i = 1;
					int j = 1;

					outerLoop: if (!(x + i > 7)) {
						if (Game.piece[x + i][y + j] != null) {
							if (MoveCheck(x, y, x + i, y + j, color))
								break outerLoop;
							Game.CheckMate++;
						}
					}

					outerLoop: if (!(x - i < 0)) {
						if (Game.piece[x - i][y + j] != null) {
							if (MoveCheck(x, y, x - i, y + j, color))
								break outerLoop;
							Game.CheckMate++;
						}
					}
				} else {
					int i = 1, j = 1;
					outerLoop: if (Game.piece[x][y + j] == null) {
						if (MoveCheck(x, y, x, y + j, color))
							break outerLoop;
						Game.CheckMate++;
					}

					outerLoop: if (x - i >= 0) {
						if (Game.piece[x - i][y + j] != null) {
							if (MoveCheck(x, y, x - i, y + j, color))
								break outerLoop;
							Game.CheckMate++;
						}
					}

					outerLoop: if (x + i <= 7) {
						if (Game.piece[x + i][y + j] != null) {
							if (MoveCheck(x, y, x + i, y + j, color))
								break outerLoop;
							Game.CheckMate++;
						}
					}

				}
			}

			if (color.equals("White")) {
				if (firstMove) {
					int i;

					for (i = 1; i <= 2; i++) {
						if (Game.piece[x][y - i] == null) {
							if (MoveCheck(x, y, x, y - i, color))
								continue;
							Game.CheckMate++;
							continue;
						} else {
							break;
						}
					}

					i = 1;
					int j = 1;

					outerLoop: if (!(x + i > 7)) {
						if (Game.piece[x + i][y - j] != null) {
							if (MoveCheck(x, y, x + i, y - j, color))
								break outerLoop;
							Game.CheckMate++;
						}
					}

					outerLoop: if (!(x - i < 0)) {
						if (Game.piece[x - i][y - j] != null) {
							if (MoveCheck(x, y, x - i, y - j, color))
								break outerLoop;
							Game.CheckMate++;
						}
					}
				} else {
					int i = 1, j = 1;
					outerLoop: if (Game.piece[x][y + j] == null) {
						if (MoveCheck(x, y, x, y + j, color))
							break outerLoop;
						Game.CheckMate++;
					}

					outerLoop: if (x - i >= 0) {
						if (Game.piece[x - i][y - j] != null) {
							if (MoveCheck(x, y, x - i, y - j, color))
								break outerLoop;
							Game.CheckMate++;
						}
					}

					outerLoop: if (x + i <= 7) {
						if (Game.piece[x + i][y - j] != null) {
							if (MoveCheck(x, y, x + i, y - j, color))
								break outerLoop;
							Game.CheckMate++;
						}
					}

				}
			}
		}
	}

}
