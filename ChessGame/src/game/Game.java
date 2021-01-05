package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import piece.Bishop;
import piece.ChessPiece;
import piece.King;
import piece.Knight;
import piece.Pawn;
import piece.Queen;
import piece.Rook;
import views.Util;

public class Game {
	AnchorPane ap;
	AnchorPane ap2;
	Pane p1;
	Pane p2;
	Pane p3;
	Pane p4;

	Image img1 = new Image("img/BlackTurn.png");
	Image img2 = new Image("img/WhiteTurn.png");

	int size = 100;

	int tempX;
	int tempY;

	ImageView tempIv = null;
	ImageView Iv;
	ChessPiece cp = null;

	String color;
	String color2;

	public static int CheckMate = 0;

	public static ChessPiece[][] piece = new ChessPiece[8][8];

	public static ImageView[][] imageView = new ImageView[8][8];

	Boolean canmove = false;

	Boolean PlayerTurn = true;

	Boolean GameOver = false;

	public static Boolean Chesscheck(String color) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (piece[i][j] != null) {
					if (piece[i][j].getColor().equals(color)) {
						if ((piece[i][j].check() != null)) {
							if (!(piece[i][j].check().equals(color))) {
								return true;
							}

						}
					}
				}
			}
		}
		return false;
	}

	public void CheckMate(String color) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (piece[i][j] != null) {
					if (!(piece[i][j].getColor().equals(color))) {
						piece[i][j].checkMate();
					}
				}
			}
		}
	}

	public void boardReset() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				color2 = "White";
				if ((i + j) % 2 == 1) {
					color2 = "Black";
				}
				Image img = new Image("img/" + color2 + ".PNG");
				imageView[i][j].setImage(img);
			}
		}
	}

	public void GameOver() {
		GameOver = true;
	}

	public Game(AnchorPane ap, AnchorPane ap2, Pane p1, Pane p2, Pane p3, Pane p4, ImageView Iv) {
		this.ap = ap;
		this.ap2 = ap2;
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.p4 = p4;
		this.Iv = Iv;
	}

	public void GameStart() {
		setting();
	}

	public void setting() {
		PlayerTurn = true;
		ap.setBackground(Background.EMPTY);
		p3.getChildren().clear();
		p4.getChildren().clear();
		GameOver = false;
		canmove = false;
		ChessPiece.BDcount = 0;
		ChessPiece.WDcount = 0;
		Iv.setImage(img2);

		for (int j = 0; j < 8; j++) {
			for (int i = 0; i < 8; i++) {
				int x = i * size;
				int y = j * size;

				if (piece[i][j] != null) {
					ap.getChildren().remove(piece[i][j].getimageView());
				}

				ImageView imgView = new ImageView();
				imageView[i][j] = imgView;
				imgView.setFitWidth(size);
				imgView.setFitHeight(size);
				imgView.setX(x);
				imgView.setY(y);
				color2 = "White";

				if ((i + j) % 2 == 1) {
					color2 = "Black";
				}

				Image img = new Image("img/" + color2 + ".png");
				imgView.setImage(img);

				ap2.getChildren().add(imageView[i][j]);

				if (j < 4) {
					color = "Black";
				} else {
					color = "White";
				}

				if (j < 2 || j > 5) {
					if (j == 1 || j == 6) {
						piece[i][j] = new Pawn(color, i, j);
					} else if (i == 0 || i == 7) {
						piece[i][j] = new Rook(color, i, j);
					} else if (i == 1 || i == 6) {
						piece[i][j] = new Knight(color, i, j);
					} else if (i == 2 || i == 5) {
						piece[i][j] = new Bishop(color, i, j);
					} else if (i == 3) {
						piece[i][j] = new Queen(color, i, j);
					} else if (i == 4) {
						piece[i][j] = new King(color, i, j);
					}
					ap.getChildren().add(piece[i][j].getimageView());
					piece[i][j].setsize(size, size);
					piece[i][j].setlocation(x, y);
				} else {
					piece[i][j] = null;
				}
			}

		}
	}

	public void clickHandle(MouseEvent e) {
		CheckMate = 0;
		tempIv = null;
		cp = null;
		if (GameOver)
			return;
		boardReset();
		double MouseX = e.getX();
		double MouseY = e.getY();

		int x = (int) MouseX / 100;
		int y = (int) MouseY / 100;
		if (PlayerTurn) {
			if ((piece[x][y] != null) && (piece[x][y].getColor().equals("White"))) {
				tempX = x;
				tempY = y;
				piece[x][y].moveColor();
				canmove = true;
			} else if ((canmove)
					&& ((piece[x][y] == null) || ((piece[x][y] != null) && (piece[x][y].getColor().equals("Black"))))) {
				if (piece[tempX][tempY].canMove(x, y)) {
					if (piece[x][y] != null) {
						cp = piece[x][y];
						tempIv = piece[x][y].getimageView();
						piece[x][y].death(x, y, piece[x][y].getimageView(), ap, p3, p4);
					}
					piece[tempX][tempY].move(piece[tempX][tempY].getimageView(), tempX, tempY, x, y);
					if (piece[x][y].getName().equals("Pawn") && y == 0)
						Util.alertPro("promotion", x, y, "White", ap);
					if (Chesscheck("White")) {
						CheckMate("White");
						if (CheckMate == 0) {
							Util.alert("CheckMate");
						} else
							Util.alert("Check");
					}
					if (Chesscheck("Black")) {
						piece[x][y].move(piece[x][y].getimageView(), x, y, tempX, tempY);
						piece[tempX][tempY].setPieceLocation(tempX, tempY);
						if (cp != null) {
							if (cp.getName().equals("Pawn"))
								piece[x][y] = new Pawn("Black", x, y);
							if (cp.getName().equals("Knight"))
								piece[x][y] = new Knight("Black", x, y);
							if (cp.getName().equals("Rook"))
								piece[x][y] = new Rook("Black", x, y);
							if (cp.getName().equals("Bishop"))
								piece[x][y] = new Bishop("Black", x, y);
							if (cp.getName().equals("Queen"))
								piece[x][y] = new Queen("Black", x, y);
						}
						if (piece[x][y] != null) {
							ap.getChildren().add(piece[x][y].getimageView());
							Game.piece[x][y].setsize(100, 100);
							Game.piece[x][y].setlocation(x * 100, y * 100);
							ChessPiece.WDcount--;
							p4.getChildren().remove(tempIv);
						}

						canmove = false;
						return;
					}
					PlayerTurn = !PlayerTurn;
					Iv.setImage(img1);
				} else if (piece[tempX][tempY] != null && piece[tempX][tempY].getName().equals("King")
						&& piece[tempX][tempY].getTrance()) {
					if ((Math.abs(x - tempX) == 2) && (y - tempY == 0)) {
						go: if (x < tempX && piece[0][tempY].getName().equals("Rook") && piece[0][tempY].getTrance()) {
							for (int i = -1; x + i > 1; i--) {
								if (piece[tempX + i][tempY] != null)
									break go;
							}
							piece[tempX][tempY].move(piece[tempX][tempY].getimageView(), tempX, tempY, x, tempY);
							piece[0][tempY].move(piece[0][tempY].getimageView(), 0, tempY, x + 1, tempY);
							piece[x][tempY].setPieceLocation(x, tempY);
							piece[x + 1][tempY].setPieceLocation(x + 1, tempY);
							piece[x][tempY].setTrance();
							piece[x + 1][tempY].setTrance();
							PlayerTurn = !PlayerTurn;
							Iv.setImage(img1);
						}
						go: if (x > tempX && piece[7][tempY].getName().equals("Rook") && piece[7][tempY].getTrance()) {
							for (int i = 1; x + i < 6; i++) {
								if (piece[tempX + i][tempY] != null)
									break go;
							}
							piece[tempX][tempY].move(piece[tempX][tempY].getimageView(), tempX, tempY, x, tempY);
							piece[7][tempY].move(piece[7][tempY].getimageView(), 7, tempY, x - 1, tempY);
							piece[x][tempY].setPieceLocation(x, tempY);
							piece[x - 1][tempY].setPieceLocation(x - 1, tempY);
							piece[x][tempY].setTrance();
							piece[x - 1][tempY].setTrance();
							PlayerTurn = !PlayerTurn;
							Iv.setImage(img1);
						}
					}
				}
				canmove = false;
			}

		} else {
			if ((piece[x][y] != null) && (piece[x][y].getColor().equals("Black"))) {
				tempX = x;
				tempY = y;
				piece[x][y].moveColor();
				canmove = true;
			} else if ((canmove)
					&& ((piece[x][y] == null) || ((piece[x][y] != null) && (piece[x][y].getColor().equals("White"))))) {
				if (piece[tempX][tempY].canMove(x, y)) {
					if (piece[x][y] != null) {
						cp = piece[x][y];
						tempIv = piece[x][y].getimageView();
						piece[x][y].death(x, y, piece[x][y].getimageView(), ap, p3, p4);
					}
					piece[tempX][tempY].move(piece[tempX][tempY].getimageView(), tempX, tempY, x, y);
					if (piece[x][y].getName().equals("Pawn") && y == 7)
						Util.alertPro("promotion", x, y, "Black", ap);
					if (Chesscheck("Black")) {
						CheckMate("Black");
						if (CheckMate == 0) {
							Util.alert("CheckMate");
						} else
							Util.alert("Check");
					}
					if (Chesscheck("White")) {
						piece[x][y].move(piece[x][y].getimageView(), x, y, tempX, tempY);
						piece[tempX][tempY].setPieceLocation(tempX, tempY);
						if (cp != null) {
							if (cp.getName().equals("Pawn"))
								piece[x][y] = new Pawn("White", x, y);
							if (cp.getName().equals("Knight"))
								piece[x][y] = new Knight("White", x, y);
							if (cp.getName().equals("Rook"))
								piece[x][y] = new Rook("White", x, y);
							if (cp.getName().equals("Bishop"))
								piece[x][y] = new Bishop("White", x, y);
							if (cp.getName().equals("Queen"))
								piece[x][y] = new Queen("White", x, y);
						}
						if(piece[x][y] != null) {
							ap.getChildren().add(piece[x][y].getimageView());
							Game.piece[x][y].setsize(100, 100);
							Game.piece[x][y].setlocation(x * 100, y * 100);
							ChessPiece.BDcount--;
							p4.getChildren().remove(tempIv);
						}
						canmove = false;
						return;
					}
					PlayerTurn = !PlayerTurn;
					Iv.setImage(img2);
				} else if (piece[tempX][tempY] != null && piece[tempX][tempY].getName().equals("King")
						&& piece[tempX][tempY].getTrance()) {
					if ((Math.abs(x - tempX) == 2) && (y - tempY == 0)) {
						go: if (x < tempX && piece[0][tempY].getName().equals("Rook") && piece[0][tempY].getTrance()) {
							for (int i = -1; x + i > 1; i--) {
								if (piece[tempX + i][tempY] != null)
									break go;
							}
							piece[tempX][tempY].move(piece[tempX][tempY].getimageView(), tempX, tempY, x, tempY);
							piece[0][tempY].move(piece[0][tempY].getimageView(), 0, tempY, x + 1, tempY);
							piece[x][tempY].setPieceLocation(x, tempY);
							piece[x + 1][tempY].setPieceLocation(x + 1, tempY);
							piece[x][tempY].setTrance();
							piece[x + 1][tempY].setTrance();
							PlayerTurn = !PlayerTurn;
							Iv.setImage(img2);
						}
						go: if (x > tempX && piece[7][tempY].getName().equals("Rook") && piece[7][tempY].getTrance()) {
							for (int i = 1; x + i < 6; i++) {
								if (piece[tempX + i][tempY] != null)
									break go;
							}
							piece[tempX][tempY].move(piece[tempX][tempY].getimageView(), tempX, tempY, x, tempY);
							piece[7][tempY].move(piece[7][tempY].getimageView(), 7, tempY, x - 1, tempY);
							piece[x][tempY].setPieceLocation(x, tempY);
							piece[x - 1][tempY].setPieceLocation(x - 1, tempY);
							piece[x][tempY].setTrance();
							piece[x - 1][tempY].setTrance();
							PlayerTurn = !PlayerTurn;
							Iv.setImage(img2);
						}
					}
					;
				}
				canmove = false;

			}
		}
	}
}
