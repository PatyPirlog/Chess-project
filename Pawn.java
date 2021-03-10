import java.util.ArrayList;

// Class to represent a Pawn.
public class Pawn extends Piece {

    public Pawn(int x, int y, int type) {
        super(x, y, type);
    }

    // method to return an array of available moves for a pawn
    // the mov parameter has the coordinates in case a pawn is taken
    @Override
    public ArrayList<Move> availableMoves(Board b, Move mov) {

        ArrayList<Move> moves = new ArrayList<Move>();

        // if type of pawn is white
        if(getType() > 0) {
            // if the pawn is at the edge of the board returns an empty array
            if(getX() == 0)
                return moves;

            // legal move to go from (x, y) to (x - 1, y - 1) if position is occupied by a black piece
            if(!b.offBoard(getX() - 1, getY() - 1 ) && b.getPiece(getX() - 1, getY() - 1) < 0) {
                moves.add(new Move(getX() - 1, getY() - 1));
                mov.setXY(getX() - 1, getY() - 1);
            }

            // legal move to go from (x, y) to (x - 1, y + 1) if position is occupied by a black piece
            if(!b.offBoard(getX() - 1, getY() + 1 ) && b.getPiece(getX() - 1, getY() + 1) < 0) {
                moves.add(new Move(getX() - 1, getY() + 1));
                mov.setXY(getX() - 1, getY() + 1);
            }

            // legal move to go from (x, y) to (x - 2, y) if the first two positions in front are unocuppied
            // the x parameter of pawn is 6 (line 6)
            if (getX() == 6)
                if((b.getPiece(getX() - 1, getY() ) == 0) && (b.getPiece(getX() - 2, getY()) == 0) &&
                        !(b.offBoard(getX() - 2, getY()))) {
                    moves.add(new Move(getX() - 2, getY()));
                }

            // legal move to go from (x, y) to (x - 1, y) if the position is unoccupied
            if((b.getPiece(getX() - 1, getY() ) == 0) && !(b.offBoard(getX() - 1, getY()))) {
                moves.add(new Move(getX() - 1, getY()));
            }
            // if type of pawn is black
        } else if(getType() < 0) {
            // if the pawn is at the edge of the board returns an empty array
            if(getX() == 7)
                return moves;

            // legal move to go from (x, y) to (x + 1, y + 1) if position is occupied by a white piece
            if(!b.offBoard(getX() + 1, getY() + 1 ) && b.getPiece(getX() + 1, getY() + 1) > 0) {
                moves.add(new Move(getX() + 1, getY() + 1));
                mov.setXY(getX() + 1, getY() + 1);
            }

            // legal move to go from (x, y) to (x + 1, y - 1) if position is occupied by a white piece
            if(!b.offBoard(getX() + 1, getY() - 1 ) && b.getPiece(getX() + 1, getY() - 1) > 0) {
                moves.add(new Move(getX() + 1, getY() - 1));
                mov.setXY(getX() + 1, getY() - 1);

            }

            // legal move to go from (x, y) to (x + 2, y) if the first two positions in front are unocuppied
            // the x parameter of pawn is 1 (line 1)
            if (getX() == 1)
                if((b.getPiece(getX() + 1, getY() ) == 0) && (b.getPiece(getX() + 2, getY() ) == 0) &&
                        !(b.offBoard(getX() + 2, getY()))) {
                    moves.add(new Move(getX() + 2, getY()));
                }

            // legal move to go from (x, y) to (x + 1, y) if the position is unoccupied
            if((b.getPiece(getX() + 1, getY()) == 0) && !(b.offBoard(getX() + 1, getY()))) {
                moves.add(new Move(getX() + 1, getY()));
            }
        }
        return moves;
    }
}