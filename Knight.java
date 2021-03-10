import java.util.ArrayList;

public class Knight extends Piece {

    public Knight(int x, int y, int type) {
        super(x, y, type);
    }

    @Override
    public ArrayList<Move> availableMoves(Board b, Move mov) {
        // moves without capturing pieces
        ArrayList<Move> secondaryMoves = new ArrayList<Move>();
        // moves that capture pieces
        ArrayList<Move> mainMoves = new ArrayList<Move>();

        // every possible move a knight can make (white or black)
        int[][] positions = {
                {getX() - 2, getY() + 1},
                {getX() + 2, getY() + 1},
                {getX() - 2, getY() - 1},
                {getX() + 2, getY() - 1},
                {getX() - 1, getY() - 2},
                {getX() + 1, getY() - 2},
                {getX() + 1, getY() + 2},
                {getX() - 1, getY() + 2}
        };

        // check every position
        for(int i = 0; i < 8; i++) {
            int nextX = positions[i][0]; // retin astea aici sa mi fie mai usor
            int nextY = positions[i][1];

            // check if the position is not off board
            // proceed if it's not
            if(!b.offBoard(nextX, nextY)) {

                // move if the position is occupied by the opposite colour
                // if it's white piece
                if(getType() > 0) {
                    // capture piece only if it's black
                    if(b.getPiece(nextX, nextY) < 0) {
                        mainMoves.add(new Move(nextX, nextY));
                        mov.setXY(nextX, nextY);
                    }
                    // if it's black piece
                } else if(getType() < 0) {
                    // capture piece only if it's white
                    if(b.getPiece(nextX, nextY) > 0) {
                        mainMoves.add(new Move(nextX, nextY));
                        mov.setXY(nextX, nextY);
                    }
                }

                // move if the position is unoccupied
                if(b.getPiece(nextX, nextY) == 0) {
                    secondaryMoves.add(new Move(nextX, nextY));
                }

            }
        }
        // add secondaryMoves so that the piece try to capture first
        mainMoves.addAll(secondaryMoves);

        return mainMoves;
    }



}