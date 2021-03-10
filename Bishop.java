import java.util.ArrayList;

//the class extends the abstract class Piece
public class Bishop extends Piece {

    //constructor
    public Bishop(int x, int y, int type) {
        super(x, y, type);
    }

    //implements the abstract method of the extended class
    //the method returns a list that contains the possible
    //moves of the Bishop
    @Override
    public ArrayList<Move> availableMoves(Board b, Move mov) {
        ArrayList<Move> moves = new ArrayList<Move>();

        // if type of bishop is white
        if(getType() > 0) {
            //leave all available moves (without jumping over black/white pieces)
            for(int i = 1; i < 8; i++) {
                // add moves until i find a piece
                if(!b.offBoard(getX() + i, getY() + i) && b.getPiece(getX() + i, getY() + i) == 0) {
                    moves.add(new Move(getX() + i, getY() + i));
                }
                // if the piece i found is black add the move and exit loop
                if(!b.offBoard(getX() + i, getY() + i) && b.getPiece(getX() + i, getY() + i) < 0) {
                    moves.add(0, new Move(getX() + i, getY() + i));
                    mov.setXY(getX() + i, getY() + i);
                    break;
                }
                // if the piece i found is white just break
                if(!b.offBoard(getX() + i, getY() + i) && b.getPiece(getX() + i, getY() + i) > 0) {
                    break;
                }
            }
            for(int i = 1; i < 8; i++) {
                // add moves until i find a piece
                if(!b.offBoard(getX() - i, getY() + i) && b.getPiece(getX() - i, getY() + i) == 0) {
                    moves.add(new Move(getX() - i, getY() + i));
                }
                // if the piece i found is black add the move and exit loop
                if(!b.offBoard(getX() - i, getY() + i) && b.getPiece(getX() - i, getY() + i) < 0) {
                    moves.add(0, new Move(getX() - i, getY() + i));
                    mov.setXY(getX() - i, getY() + i);
                    break;
                }
                // if the piece i found is white just break
                if(!b.offBoard(getX() - i, getY() + i) && b.getPiece(getX() - i, getY() + i) > 0) {
                    break;
                }
            }
            for(int i = 1; i < 8; i++) {
                // add moves until i find a piece
                if(!b.offBoard(getX() - i, getY() - i) && b.getPiece(getX() - i, getY() - i) == 0) {
                    moves.add(new Move(getX() - i, getY() - i));
                }
                // if the piece i found is black add the move and exit loop
                if(!b.offBoard(getX() - i, getY() - i) && b.getPiece(getX() - i, getY() - i) < 0) {
                    moves.add(0, new Move(getX() - i, getY() - i));
                    mov.setXY(getX() - i, getY() - i);
                    break;
                }
                // if the piece i found is white just break
                if(!b.offBoard(getX() - i, getY() - i) && b.getPiece(getX() - i, getY() - i) > 0) {
                    break;
                }
            }
            for(int i = 1; i < 8; i++) {
                // add moves until i find a piece
                if(!b.offBoard(getX() + i, getY() - i) && b.getPiece(getX() + i, getY() - i) == 0) {
                    moves.add(new Move(getX() + i, getY() - i));
                }
                // if the piece i found is black add the move and exit loop
                if(!b.offBoard(getX() + i, getY() - i) && b.getPiece(getX() + i, getY() - i) < 0) {
                    moves.add(0, new Move(getX() + i, getY() - i));
                    mov.setXY(getX() + i, getY() - i);
                    break;
                }
                // if the piece i found is white just break
                if(!b.offBoard(getX() + i, getY() - i) && b.getPiece(getX() + i, getY() - i) > 0) {
                    break;
                }
            }

        }

        //if the bishop is black
        if(getType() < 0) {
            //leave all available moves (without jumping over black/white pieces)
            for(int i = 1; i < 8; i++) {
                // add moves until i find a piece
                if(!b.offBoard(getX() + i, getY() + i) && b.getPiece(getX() + i, getY() + i) == 0) {
                    moves.add(new Move(getX() + i, getY() + i));
                }
                // if the piece i found is white add the move and exit loop
                if(!b.offBoard(getX() + i, getY() + i) && b.getPiece(getX() + i, getY() + i) > 0) {
                    moves.add(0, new Move(getX() + i, getY() + i));
                    mov.setXY(getX() + i, getY() + i);
                    break;
                }
                // if the piece i found is black just break
                if(!b.offBoard(getX() + i, getY() + i) && b.getPiece(getX() + i, getY() + i) < 0) {
                    break;
                }
            }
            for(int i = 1; i < 8; i++) {
                // add moves until i find a piece
                if(!b.offBoard(getX() - i, getY() + i) && b.getPiece(getX() - i, getY() + i) == 0) {
                    moves.add(new Move(getX() - i, getY() + i));
                }
                // if the piece i found is white add the move and exit loop
                if(!b.offBoard(getX() - i, getY() + i) && b.getPiece(getX() - i, getY() + i) > 0) {
                    moves.add(0, new Move(getX() - i, getY() + i));
                    mov.setXY(getX() - i, getY() + i);
                    break;
                }
                // if the piece i found is black just break
                if(!b.offBoard(getX() - i, getY() + i) && b.getPiece(getX() - i, getY() + i) < 0) {
                    break;
                }
            }
            for(int i = 1; i < 8; i++) {
                // add moves until i find a piece
                if(!b.offBoard(getX() - i, getY() - i) && b.getPiece(getX() - i, getY() - i) == 0) {
                    moves.add(new Move(getX() - i, getY() - i));
                }
                // if the piece i found is white add the move and exit loop
                if(!b.offBoard(getX() - i, getY() - i) && b.getPiece(getX() - i, getY() - i) > 0) {
                    moves.add(0, new Move(getX() - i, getY() - i));
                    mov.setXY(getX() - i, getY() - i);
                    break;
                }
                // if the piece i found is black just break
                if(!b.offBoard(getX() - i, getY() - i) && b.getPiece(getX() - i, getY() - i) < 0) {
                    break;
                }
            }
            for(int i = 1; i < 8; i++) {
                // add moves until i find a piece
                if(!b.offBoard(getX() + i, getY() - i) && b.getPiece(getX() + i, getY() - i) == 0) {
                    moves.add(new Move(getX() + i, getY() - i));
                }
                // if the piece i found is white add the move and exit loop
                if(!b.offBoard(getX() + i, getY() - i) && b.getPiece(getX() + i, getY() - i) > 0) {
                    moves.add(0, new Move(getX() + i, getY() - i));
                    mov.setXY(getX() + i, getY() - i);
                    break;
                }
                // if the piece i found is black just break
                if(!b.offBoard(getX() + i, getY() - i) && b.getPiece(getX() + i, getY() - i) < 0) {
                    break;
                }
            }

        }

        return moves;

    }
}