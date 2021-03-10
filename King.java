import java.util.ArrayList;

//the class extends the abstract class Piece
public class King extends Piece {
    // tine minte daca tura a mai fost mutata in timpul jocului
    public boolean moved;
    //constructor
    public King(int x, int y, int type) {
        super(x, y, type);
    }

    //implements the abstract method of the extended class
    //the method returns a list that contains the possible
    //moves of the King
    @Override
    public ArrayList<Move> availableMoves(Board b, Move mov) {

        ArrayList<Move> moves = new ArrayList<Move>();
        // move the king only on places where there is no piece or opposite color piece
        if(getType() > 0) {
            if(!b.offBoard(getX() + 1, getY() + 1) && b.getPiece(getX() + 1, getY() + 1) <= 0) {
                moves.add(new Move(getX() + 1, getY() + 1));
                if (b.getPiece(getX() + 1, getY() + 1) < 0)
                    mov.setXY(getX() + 1, getY() + 1);
            }
            if(!b.offBoard(getX() + 1, getY()) && b.getPiece(getX() + 1, getY()) <= 0) {
                moves.add(new Move(getX() + 1, getY()));
                if (b.getPiece(getX() + 1, getY()) < 0)
                    mov.setXY(getX() + 1, getY());
            }
            if(!b.offBoard(getX(), getY() + 1) && b.getPiece(getX(), getY() + 1) <= 0) {
                moves.add(new Move(getX(), getY() + 1));
                if (b.getPiece(getX(), getY() + 1) < 0)
                    mov.setXY(getX(), getY() + 1);
            }
            if(!b.offBoard(getX() - 1, getY() + 1) && b.getPiece(getX() - 1 , getY() + 1) <= 0) {
                moves.add(new Move(getX() - 1, getY() + 1));
                if (b.getPiece(getX() - 1, getY() + 1) < 0)
                    mov.setXY(getX() - 1, getY() + 1);
            }
            if(!b.offBoard(getX() - 1, getY() - 1) && b.getPiece(getX() - 1, getY() - 1) <= 0) {
                moves.add(new Move(getX() - 1, getY() - 1));
                if (b.getPiece(getX() - 1, getY() - 1) < 0)
                    mov.setXY(getX() - 1, getY() - 1);
            }
            if(!b.offBoard(getX() - 1, getY()) && b.getPiece(getX() - 1, getY()) <= 0) {
                moves.add(new Move(getX() - 1, getY()));
                if (b.getPiece(getX() - 1, getY()) < 0)
                    mov.setXY(getX() - 1, getY());
            }
            if(!b.offBoard(getX(), getY() - 1) && b.getPiece(getX() , getY() - 1) <= 0) {
                moves.add(new Move(getX(), getY() - 1));
                if (b.getPiece(getX(), getY() - 1) < 0)
                    mov.setXY(getX(), getY() - 1);
            }
            if(!b.offBoard(getX() + 1, getY() - 1) && b.getPiece(getX() + 1 , getY() - 1) <= 0) {
                moves.add(new Move(getX() + 1, getY() - 1));
                if (b.getPiece(getX() + 1, getY() - 1) < 0)
                    mov.setXY(getX() + 1, getY() - 1);
            }
        }

        if(getType() < 0) {
            if(!b.offBoard(getX() + 1, getY() + 1) && b.getPiece(getX() + 1, getY() + 1) >= 0) {
                moves.add(new Move(getX() + 1, getY() + 1));
                if (b.getPiece(getX() + 1, getY() + 1) > 0)
                    mov.setXY(getX() + 1, getY() + 1);
            }
            if(!b.offBoard(getX() + 1, getY()) && b.getPiece(getX() + 1, getY()) >= 0) {
                moves.add(new Move(getX() + 1, getY()));
                if (b.getPiece(getX() + 1, getY()) > 0)
                    mov.setXY(getX() + 1, getY());
            }
            if(!b.offBoard(getX(), getY() + 1) && b.getPiece(getX(), getY() + 1) >= 0) {
                moves.add(new Move(getX(), getY() + 1));
                if (b.getPiece(getX(), getY() + 1) > 0)
                    mov.setXY(getX(), getY() + 1);
            }
            if(!b.offBoard(getX() - 1, getY() + 1) && b.getPiece(getX() - 1 , getY() + 1) >= 0) {
                moves.add(new Move(getX() - 1, getY() + 1));
                if (b.getPiece(getX() - 1, getY() + 1) > 0)
                    mov.setXY(getX() - 1, getY() + 1);
            }
            if(!b.offBoard(getX() - 1, getY() - 1) && b.getPiece(getX() - 1, getY() - 1) >= 0) {
                moves.add(new Move(getX() - 1, getY() - 1));
                if (b.getPiece(getX() - 1, getY() - 1) > 0)
                    mov.setXY(getX() - 1, getY() - 1);
            }
            if(!b.offBoard(getX() - 1, getY()) && b.getPiece(getX() - 1, getY()) >= 0) {
                moves.add(new Move(getX() - 1, getY()));
                if (b.getPiece(getX() - 1, getY()) > 0)
                    mov.setXY(getX() - 1, getY());
            }
            if(!b.offBoard(getX(), getY() - 1) && b.getPiece(getX() , getY() - 1) >= 0) {
                moves.add(new Move(getX(), getY() - 1));
                if (b.getPiece(getX(), getY() - 1) > 0)
                    mov.setXY(getX(), getY() - 1);
            }
            if(!b.offBoard(getX() + 1, getY() - 1) && b.getPiece(getX() + 1 , getY() - 1) >= 0) {
                moves.add(new Move(getX() + 1, getY() - 1));
                if (b.getPiece(getX() + 1, getY() - 1) > 0)
                    mov.setXY(getX() + 1, getY() - 1);
            }
        }

        return moves;
    }

    // method that checks if rook has been moved since the begining of the game
    public boolean hasBeenMoved() {
        if(moved == true)
            return true;
        else
            return false;
    }
}