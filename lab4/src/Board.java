import Figures.Bishop;
import Figures.Figure;
import Figures.King;
import Figures.Knight;
import Figures.Pawn;
import Figures.Queen;
import Figures.Rook;

import java.util.ArrayList;

public class Board {
    //TODO: Список фигур и начальное положение всех фигур
    private Figure fields[][] = new Figure[8][8];
    private ArrayList<String> takeWhite = new ArrayList(16);
    private ArrayList<String> takeBlack = new ArrayList(16);

    public char getColorGaming() {
        return colorGaming;
    }

    public void setColorGaming(char colorGaming) {
        this.colorGaming = colorGaming;
    }

    private char colorGaming;

    public void init() {
        this.fields[0] = new Figure[]{
                new Rook("R", 'w'), new Knight("N", 'w'),
                new Bishop("B", 'w'), new Queen("Q", 'w'),
                new King("K", 'w'), new Bishop("B", 'w'),
                new Knight("N", 'w'), new Rook("R", 'w')
        };
        this.fields[1] = new Figure[]{
                new Pawn("P", 'w'), new Pawn("P", 'w'),
                new Pawn("P", 'w'), new Pawn("P", 'w'),
                new Pawn("P", 'w'), new Pawn("P", 'w'),
                new Pawn("P", 'w'), new Pawn("P", 'w'),
        };

        this.fields[7] = new Figure[]{
                new Rook("R", 'b'), new Knight("N", 'b'),
                new Bishop("B", 'b'), new Queen("Q", 'b'),
                new King("K", 'b'), new Bishop("B", 'b'),
                new Knight("N", 'b'), new Rook("R", 'b')
        };
        this.fields[6] = new Figure[]{
                new Pawn("P", 'b'), new Pawn("P", 'b'),
                new Pawn("P", 'b'), new Pawn("P", 'b'),
                new Pawn("P", 'b'), new Pawn("P", 'b'),
                new Pawn("P", 'b'), new Pawn("P", 'b'),
        };
    }

    public String getCell(int row, int col) {
        Figure figure = this.fields[row][col];
        if (figure == null) {
            return "    ";
        }
        return " " + figure.getColor() + figure.getName() + " ";
    }

    public ArrayList<String> getTakeWhite() {
        return takeWhite;
    }

    public ArrayList<String> getTakeBlack() {
        return takeBlack;
    }

    public boolean move_figure(int row1, int col1, int row2, int col2) {

        Figure figure = this.fields[row1][col1];

        if (Obstacle(row1, col1, row2, col2)) {
            return false;
        }
        if (figure.canMove(row1, col1, row2, col2) && this.fields[row2][col2] == null) {
            System.out.println("move");
            this.fields[row2][col2] = figure;
            this.fields[row1][col1] = null;
            return true;
        } else if (figure.canAttack(row1, col1, row2, col2) && this.fields[row2][col2] != null && this.fields[row2][col2].getColor() != this.fields[row1][col1].getColor()) {
            System.out.println("attack");
            if (this.fields[row2][col2] instanceof King) {
                //КОРОЛЯ НЕ АТАКОВЫВАЕМ
                return false;
            }
            switch (this.fields[row2][col2].getColor()) {
                case 'w':
                    this.takeWhite.add(this.fields[row2][col2].getColor() + this.fields[row2][col2].getName());
                    break;
                case 'b':
                    this.takeBlack.add(this.fields[row2][col2].getColor() + this.fields[row2][col2].getName());
                    break;
            }
            this.fields[row2][col2] = figure;
            this.fields[row1][col1] = null;
            return true;
        }
        return false;
    }

    public void print_board() {
        System.out.println(" +----+----+----+----+----+----+----+----+");
        for (int row = 7; row > -1; row--) {
            System.out.print(row);
            for (int col = 0; col < 8; col++) {
                System.out.print("|" + getCell(row, col));
            }
            System.out.println("|");
            System.out.println(" +----+----+----+----+----+----+----+----+");
        }

        for (int col = 0; col < 8; col++) {
            System.out.print("    " + col);
        }

    }

    // УЧЕТ ПРЕПЯТСТВИЙ
    private boolean Obstacle(int row1, int col1, int row2, int col2) {

        int dx = Math.abs(col2 - col1);
        int dy = Math.abs(row2 - row1);
        //если разница соответствует ходу коня false
        if ((dx == 1 && dy == 2) || (dx == 2 && dy == 1)) {
            return false;
        }

        int stepX = (col2 > col1) ? 1 : (col2 < col1) ? -1 : 0;
        int stepY = (row2 > row1) ? 1 : (row2 < row1) ? -1 : 0;

        int x = col1 + stepX;
        int y = row1 + stepY;

        while (x != col2 || y != row2) {
            if (this.fields[y][x] != null) {
                return true;
            }
            x += stepX;
            y += stepY;
        }

        return false;
    }


    //ШАХ
    public boolean isKingInCheck(char color) {
        //поиск короля
        int kingX = 0;
        int kingY = 0;
        boolean kingFound = false;

        for (int i = 0; i < 8 && !kingFound; i++) {
            for (int j = 0; j < 8 && !kingFound; j++) {
                if (fields[i][j] != null && fields[i][j].getColor() == color && fields[i][j] instanceof King) {
                    kingX = j;
                    kingY = i;
                    kingFound = true;
                }
            }
        }

        if (!kingFound) {
            return false;
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //если противник может атаковать короля(король под шахом)
                if (fields[i][j] != null && fields[i][j].getColor() != color) {
                    if (fields[i][j].canAttack(j, i, kingX, kingY)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }


    // МАТ
    public boolean isCheckMate(char color) {
        if (!isKingInCheck(color)) {
            return false;
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (fields[i][j] != null && fields[i][j].getColor() == color) {
                    Figure piece = fields[i][j];
                    for (int y = 0; y < 8; y++) {
                        for (int x = 0; x < 8; x++) {
                            if (piece.canMove(j, i, y, x)) {
                                Figure takenPiece = fields[y][x];
                                fields[y][x] = piece;
                                fields[i][j] = null;

                                //находится ли король после хода под шахом
                                boolean isInCheck = isKingInCheck(color);

                                fields[i][j] = piece;
                                fields[y][x] = takenPiece;

                                if (!isInCheck) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

}
