import java.util.ArrayList;

// class to represent a chess board
public class Board {
    // Negative numbers: black pieces:
    // Pawn: -1; Rook: -2; Knight: -3; Bishop: -4; Queen: -5; King: -6.
    // Positive numbers: white pieces:
    // Pawn: 1; Rook: 2; Knight: 3; Bishop: 4; Queen: 5; King: 6.

    // a matrix of ints to represent each location of the pieces on board
    public int matrix[][];
    // the color the engine plays with
    public int color;
    public int x, y;

    public Board () {

    }

    public Board(int matrice[][]) {
        this.matrix = matrice;
    }

    public Board(int color) {
        this.color = color;
    }


    public Board copyBoard(int color, int x, int y) {
        Board copy = new Board(matrix);
        copy.color = color;
        copy.x = x;
        copy.y = y;
        return copy;

    }

    public void setMatrix(int matrice[][]) {
        for (int i = 0; i <= 7; i++)
            for (int j = 0; j <= 7; j++)
                matrix[i][j] = matrice[i][j];
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setPosition (int x, int y) {
        matrix[x][y] = 0;
    }


    // method to change the position of a piece on matrix
    // and set the coordinates of the new position for that piece
    public void changePosition(int x, int y, Piece p) {
        matrix[p.getX()][p.getY()] = 0;
        matrix[x][y] = p.getType();
        p.setParam(x, y);
    }

    public void setType (int x, int y, int type) {
        matrix[x][y] = type;
    }

    // returns 0 if there is no piece at (x, y)
    public int getPiece(int x, int y) {
        return matrix[x][y];
    }

    public String toString() {
        String result = "";
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                if (matrix[i][j] >= 0)
                    result += " ";
                result += matrix[i][j] + " ";
            }
            result += '\n';
        }
        return result;
    }

    // method returns true if a location is off the board
    public boolean offBoard(int x, int y) {
        if(x < 0 || x > 7
                || y < 0 || y > 7)
            return true;
        else
            return false;
    }

    public boolean checkEnPassantFromXboard(int initialX, int initialY, Move prevMoveEngine,Move currentMoveEngine, Piece currentPiece) {
        //daca e pion
        if ((prevMoveEngine.getType() == 1 || prevMoveEngine.getType() == -1) && (currentPiece.getType() == 1 ||
                currentPiece.getType() == -1)) {
            System.out.println("yep");
            if (((initialY - 1 == currentMoveEngine.getY() || initialY + 1 == currentMoveEngine.getY()) &&
                    initialX == currentMoveEngine.getX()) && ((initialY - 1 == prevMoveEngine.getY() || initialY + 1 == prevMoveEngine.getY()) &&
                    initialX + 2 * prevMoveEngine.getType() == prevMoveEngine.getX())) {
                System.out.println("YEEEEEEES");
                return true;
            }
        }
        return false;
    }

    public Piece checkEnPassantFromEngine(Piece currentPiece, ArrayList<Piece> xboardPiece, Move prevMove) {
        //if the pieces are pawns
        if ((currentPiece.getType() == 1 || currentPiece.getType() == -1) && (prevMove.getType() == 1 ||
                prevMove.getType() == -1)) {
            int currentLine = currentPiece.getX();
            int currentColumn = currentPiece.getY();
            // verific daca vreun pion este in stanga sau in dreapta piesei curente
            for (int i = 0; i < 8; i++) {
                if (xboardPiece.get(i) != null) {
                    int currentX = xboardPiece.get(i).getX();
                    int currentY = xboardPiece.get(i).getY();
                    if ((currentY == currentColumn - 1 || currentY == currentColumn + 1) && currentLine == currentX) {
                        // daca prevMove a fost cu doua casute inapoi
                        if (prevMove.getY() == currentY && prevMove.getX() == currentX + 2 * prevMove.getType()) {
                            return xboardPiece.get(i);
                        }
                    }
                }
            }
        }
        return null;
    }


    //takenPiece - piesa returnata de checkEnPassantFromEngine
    public void makeEnPassant(Piece currentPiece, Piece takenPiece, Move prevMove, Move prevMoveEngine,
                              ArrayList<Piece> xboardPiece, ArrayList<Piece> enginePiece, Board b) {
        // remove oponent's piece from array
        for (int j = 0; j <= 15; j++) {
            if (xboardPiece.get(j) != null) {
                if (xboardPiece.get(j).getX() == takenPiece.getX() &&
                        xboardPiece.get(j).getY() == takenPiece.getY()) {
                    xboardPiece.set(j, null);
                }
            }
        }
        //int initialX =

        String position1 = b.TranslateToMove(currentPiece.getX(), currentPiece.getY());
        String position2 = b.TranslateToMove(takenPiece.getX() + prevMove.getType(), takenPiece.getY());
        //retain prevMoveEngine
        prevMoveEngine.setParam(currentPiece.getX(), currentPiece.getY(), currentPiece.getType());
        //update the board and the coordinates of the piece
        for (int j = 0; j < 8; j++) {
            if (enginePiece.get(j) != null) {
                if (currentPiece.getX() == enginePiece.get(j).getX() && currentPiece.getY() == enginePiece.get(j).getY()) {
                    b.changePosition(takenPiece.getX() + prevMove.getType(), takenPiece.getY(), enginePiece.get(j));
                }
            }
        }
        b.setPosition(takenPiece.getX(), takenPiece.getY());
        //stdout: new move from engine
        System.out.println("move " + position1 + position2);
        System.out.flush();
    }

    // method to translate a position given at System.in into coordinates on matrix
    public void TranslatePosition(String position) {

        switch (position) {
            case "a1":
                x = 7;
                y = 0;
                break;
            case "b1":
                x = 7;
                y = 1;
                break;
            case "c1":
                x = 7;
                y = 2;
                break;
            case "d1":
                x = 7;
                y = 3;
                break;
            case "e1":
                x = 7;
                y = 4;
                break;
            case "f1":
                x = 7;
                y = 5;
                break;
            case "g1":
                x = 7;
                y = 6;
                break;
            case "h1":
                x = 7;
                y = 7;
                break;
            case "a2":
                x = 6;
                y = 0;
                break;
            case "b2":
                x = 6;
                y = 1;
                break;
            case "c2":
                x = 6;
                y = 2;
                break;
            case "d2":
                x = 6;
                y = 3;
                break;
            case "e2":
                x = 6;
                y = 4;
                break;
            case "f2":
                x = 6;
                y = 5;
                break;
            case "g2":
                x = 6;
                y = 6;
                break;
            case "h2":
                x = 6;
                y = 7;
                break;
            case "a3":
                x = 5;
                y = 0;
                break;
            case "b3":
                x = 5;
                y = 1;
                break;
            case "c3":
                x = 5;
                y = 2;
                break;
            case "d3":
                x = 5;
                y = 3;
                break;
            case "e3":
                x = 5;
                y = 4;
                break;
            case "f3":
                x = 5;
                y = 5;
                break;
            case "g3":
                x = 5;
                y = 6;
                break;
            case "h3":
                x = 5;
                y = 7;
                break;
            case "a4":
                x = 4;
                y = 0;
                break;
            case "b4":
                x = 4;
                y = 1;
                break;
            case "c4":
                x = 4;
                y = 2;
                break;
            case "d4":
                x = 4;
                y = 3;
                break;
            case "e4":
                x = 4;
                y = 4;
                break;
            case "f4":
                x = 4;
                y = 5;
                break;
            case "g4":
                x = 4;
                y = 6;
                break;
            case "h4":
                x = 4;
                y = 7;
                break;
            case "a5":
                x = 3;
                y = 0;
                break;
            case "b5":
                x = 3;
                y = 1;
                break;
            case "c5":
                x = 3;
                y = 2;
                break;
            case "d5":
                x = 3;
                y = 3;
                break;
            case "e5":
                x = 3;
                y = 4;
                break;
            case "f5":
                x = 3;
                y = 5;
                break;
            case "g5":
                x = 3;
                y = 6;
                break;
            case "h5":
                x = 3;
                y = 7;
                break;
            case "a6":
                x = 2;
                y = 0;
                break;
            case "b6":
                x = 2;
                y = 1;
                break;
            case "c6":
                x = 2;
                y = 2;
                break;
            case "d6":
                x = 2;
                y = 3;
                break;
            case "e6":
                x = 2;
                y = 4;
                break;
            case "f6":
                x = 2;
                y = 5;
                break;
            case "g6":
                x = 2;
                y = 6;
                break;
            case "h6":
                x = 2;
                y = 7;
                break;
            case "a7":
                x = 1;
                y = 0;
                break;
            case "b7":
                x = 1;
                y = 1;
                break;
            case "c7":
                x = 1;
                y = 2;
                break;
            case "d7":
                x = 1;
                y = 3;
                break;
            case "e7":
                x = 1;
                y = 4;
                break;
            case "f7":
                x = 1;
                y = 5;
                break;
            case "g7":
                x = 1;
                y = 6;
                break;
            case "h7":
                x = 1;
                y = 7;
                break;
            case "a8":
                x = 0;
                y = 0;
                break;
            case "b8":
                x = 0;
                y = 1;
                break;
            case "c8":
                x = 0;
                y = 2;
                break;
            case "d8":
                x = 0;
                y = 3;
                break;
            case "e8":
                x = 0;
                y = 4;
                break;
            case "f8":
                x = 0;
                y = 5;
                break;
            case "g8":
                x = 0;
                y = 6;
                break;
            case "h8":
                x = 0;
                y = 7;
                break;

            default:
                break;
        }
    }

    // method to translate a move(coordinates) on matrix to a move(coordinates) given to Xboard
    public String TranslateToMove(int x, int y) {
        String move = "";
        switch(x+"|"+y) {
            case "0|0":
                move = "a8";
                break;
            case "0|1":
                move = "b8";
                break;
            case "0|2":
                move = "c8";
                break;
            case "0|3":
                move = "d8";
                break;
            case "0|4":
                move = "e8";
                break;
            case "0|5":
                move = "f8";
                break;
            case "0|6":
                move = "g8";
                break;
            case "0|7":
                move = "h8";
                break;
            case "1|0":
                move = "a7";
                break;
            case "1|1":
                move = "b7";
                break;
            case "1|2":
                move = "c7";
                break;
            case "1|3":
                move = "d7";
                break;
            case "1|4":
                move = "e7";
                break;
            case "1|5":
                move = "f7";
                break;
            case "1|6":
                move = "g7";
                break;
            case "1|7":
                move = "h7";
                break;
            case "2|0":
                move = "a6";
                break;
            case "2|1":
                move = "b6";
                break;
            case "2|2":
                move = "c6";
                break;
            case "2|3":
                move = "d6";
                break;
            case "2|4":
                move = "e6";
                break;
            case "2|5":
                move = "f6";
                break;
            case "2|6":
                move = "g6";
                break;
            case "2|7":
                move = "h6";
                break;
            case "3|0":
                move = "a5";
                break;
            case "3|1":
                move = "b5";
                break;
            case "3|2":
                move = "c5";
                break;
            case "3|3":
                move = "d5";
                break;
            case "3|4":
                move = "e5";
                break;
            case "3|5":
                move = "f5";
                break;
            case "3|6":
                move = "g5";
                break;
            case "3|7":
                move = "h5";
                break;
            case "4|0":
                move = "a4";
                break;
            case "4|1":
                move = "b4";
                break;
            case "4|2":
                move = "c4";
                break;
            case "4|3":
                move = "d4";
                break;
            case "4|4":
                move = "e4";
                break;
            case "4|5":
                move = "f4";
                break;
            case "4|6":
                move = "g4";
                break;
            case "4|7":
                move = "h4";
                break;
            case "5|0":
                move = "a3";
                break;
            case "5|1":
                move = "b3";
                break;
            case "5|2":
                move = "c3";
                break;
            case "5|3":
                move = "d3";
                break;
            case "5|4":
                move = "e3";
                break;
            case "5|5":
                move = "f3";
                break;
            case "5|6":
                move = "g3";
                break;
            case "5|7":
                move = "h3";
                break;
            case "6|0":
                move = "a2";
                break;
            case "6|1":
                move = "b2";
                break;
            case "6|2":
                move = "c2";
                break;
            case "6|3":
                move = "d2";
                break;
            case "6|4":
                move = "e2";
                break;
            case "6|5":
                move = "f2";
                break;
            case "6|6":
                move = "g2";
                break;
            case "6|7":
                move = "h2";
                break;
            case "7|0":
                move = "a1";
                break;
            case "7|1":
                move = "b1";
                break;
            case "7|2":
                move = "c1";
                break;
            case "7|3":
                move = "d1";
                break;
            case "7|4":
                move = "e1";
                break;
            case "7|5":
                move = "f1";
                break;
            case "7|6":
                move = "g1";
                break;
            case "7|7":
                move = "h1";
                break;

            default:
                break;
        }
        return move;
    }
}