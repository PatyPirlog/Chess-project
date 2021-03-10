import java.util.ArrayList;

// the class extends the abstract class Piece
public class Rook extends Piece {
    // tine minte daca tura a mai fost mutata in timpul jocului
    public boolean moved;

    // constructor
    public Rook(int x, int y, int type) {
        super(x, y, type);
    }

    // implements the abstract method of the extended class
    // the method returns a list that contains the possible
    // moves of the Rook
    @Override
    public ArrayList<Move> availableMoves(Board b, Move mov) {
        ArrayList<Move> moves = new ArrayList<Move>();
        computeMoves (moves, mov, getX(), getY(), getType(), b);
        return moves;
    }

    // computes the possible moves of the Rook
    public void computeMoves (ArrayList<Move> mainMoves, Move mov, int x, int y, int type, Board b) {
        // mainMoves : moves that capture pieces
        // moves without capturing pieces
        ArrayList<Move> secondaryMoves = new ArrayList<Move>();
        // black piece -> up board limit = 7; white piece -> up board limit = 0
        int upBoardLimit = type > 0 ? -1 : 8;
        // black piece -> down board limit = 0; white piece -> down board limit = 7
        int downBoardLimit = type > 0 ? 8 : -1;
        // vertically upwards for black and white
        for (int i = x - type / 2; i != upBoardLimit && !(b.offBoard(x - type / 2, y));) {
            int breafIf = addMoves(i, y, type, b, mainMoves, secondaryMoves, mov);
            //break if the current move is illegal
            if (breafIf < 0) {
                break;
            }
            // next position for x
            i -= type / 2;
        }

        // vertically downwards for black and white
        for (int i = x + type / 2; i != downBoardLimit && !(b.offBoard(x + type / 2, y));) {
            int breafIf = addMoves(i, y, type, b, mainMoves, secondaryMoves, mov);
            //break if the current move is illegal
            if (breafIf < 0) {
                break;
            }
            // next position for x
            i += type / 2;
        }

        // horizontally to the right
        for (int i = y + 1; i <= 7 && !(b.offBoard(x, y + 1)); i++) {
            int breafIf = addMoves(x, i, type, b, mainMoves, secondaryMoves, mov);
            //break if the current move is illegal
            if (breafIf < 0) {
                break;
            }
        }

        // horizontally to the left
        for (int i = y - 1; i >= 0 && !(b.offBoard(x, y - 1)); i--) {
            int breafIf = addMoves(x, i, type, b, mainMoves, secondaryMoves, mov);
            //break if the current move is illegal
            if (breafIf < 0) {
                break;
            }
        }

        // concatenate the arraylists; mainMoves first, secondaryMoves second
        mainMoves.addAll(secondaryMoves);
    }

    public int addMoves (int x, int y, int type, Board b, ArrayList<Move> mainMoves, ArrayList<Move> secondaryMoves,
                         Move mov) {
        int opponentType;
        if (type > 0) {
            opponentType = -1;
            //testez mai mic decat zero
        } else {
            opponentType = 1;
            //testez mai mare decat zero
        }
        // move if the position is unoccupied
        if (b.getPiece(x, y) == 0) {
            secondaryMoves.add(new Move(x, y));
        // move if the position is occupied by the opposite colour
        } else if ((b.getPiece(x, y) < 0 && opponentType == -1) || (b.getPiece(x, y) > 0 && opponentType == 1)) {
            mainMoves.add(new Move(x, y));
            mov.setXY(x, y);
            return  -1;
        // piece with Rook's color
        } else {
            return -1;
        }
        return 1;
    }

    // method that checks if rook has been moved since the begining of the game
    public boolean hasBeenMoved() {
        if(moved == true)
            return true;
        else
            return false;
    }
}