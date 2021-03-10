import java.util.ArrayList;

public class Queen extends Piece {
    public Queen() {
        super();
    }
    public Queen(int x, int y, int type) {
        super(x, y, type);
    }

    @Override
    public ArrayList<Move> availableMoves(Board b, Move mov) {
        ArrayList<Move> moves = new ArrayList<Move>();
        // add rook moves to queen moves
        rookMoves(moves, b, mov);
        // add bishop moves to queen moves
        bishopMoves(moves, b, mov);

        return moves;
    }

    public void rookMoves(ArrayList<Move> moves, Board b, Move mov) {
        // aici adaug in movws dat ca parametru din availableMoves !! check this
        moves.addAll(computeMoves(mov, getX(), getY(), getType(), b));

    }

    // same function as is Rook class
    // am schimbat tipul ca sa nu existe cumva probleme cand se tot modifica arrayul cu moves
    //public ArrayList<Move> computeMoves ( ArrayList<Move> mainMoves, Move mov, int x, int y, int type, Board b) {
    public ArrayList<Move> computeMoves ( Move mov, int x, int y, int type, Board b) {
        ArrayList<Move> mainMoves = new ArrayList<Move>();
        ArrayList<Move> secondaryMoves = new ArrayList<Move>();
        // black piece -> up board limit = 7; white piece -> up board limit = 0
        int upBoardLimit = type > 0 ? -1 : 8;
        // black piece -> down board limit = 0; white piece -> down board limit = 7
        int downBoardLimit = type > 0 ? 8 : -1;
        // vertically upwards for black and white
        for (int i = x - type / 5; i != upBoardLimit && !(b.offBoard(x - type / 5, y));) {
            int breafIf = addMoves(i, y, type, b, mainMoves, secondaryMoves, mov);
            //break if the current move is illegal
            if (breafIf < 0) {
                break;
            }
            // next position for x
            i -= type / 5;
        }

        // vertically downwards for black and white
        for (int i = x + type / 5; i != downBoardLimit && !(b.offBoard(x + type / 5, y));) {
            int breafIf = addMoves(i, y, type, b, mainMoves, secondaryMoves, mov);
            //break if the current move is illegal
            if (breafIf < 0) {
                break;
            }
            // next position for x
            i += type / 5;
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
        return mainMoves;

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

    public void bishopMoves(ArrayList<Move> moves, Board b, Move mov) {


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

        //if type of queen is black
        if(getType() < 0) {
            //leave all available moves (without jumping over black/white pieces)
            // dreapta-sus
            for(int i = 1; i < 8; i++) {
                // add moves until i find a piece
                if(!b.offBoard(getX() + i, getY() + i) && b.getPiece(getX() + i, getY() + i) == 0) {
                    moves.add(new Move(getX() + i, getY() + i));
                }
                // if the piece i found is white add the move and exit loop
                if(!b.offBoard(getX() + i, getY() + i) && b.getPiece(getX() + i, getY() + i) > 0) {
                    moves.add(0, new Move(getX() + i, getY() + i));
                    break;
                }
                // if the piece i found is black just break
                if(!b.offBoard(getX() + i, getY() + i) && b.getPiece(getX() + i, getY() + i) < 0) {
                    break;
                }
            }
            // dreapta-jos
            for(int i = 1; i < 8; i++) {
                // add moves until i find a piece
                if(!b.offBoard(getX() - i, getY() + i) && b.getPiece(getX() - i, getY() + i) == 0) {
                    moves.add(new Move(getX() - i, getY() + i));
                }
                // if the piece i found is white add the move and exit loop
                if(!b.offBoard(getX() - i, getY() + i) && b.getPiece(getX() - i, getY() + i) > 0) {
                    moves.add(0, new Move(getX() - i, getY() + i));
                    break;
                }
                // if the piece i found is black just break
                if(!b.offBoard(getX() - i, getY() + i) && b.getPiece(getX() - i, getY() + i) < 0) {
                    break;
                }
            }
            // stanga-jos
            for(int i = 1; i < 8; i++) {
                // add moves until i find a piece
                if(!b.offBoard(getX() - i, getY() - i) && b.getPiece(getX() - i, getY() - i) == 0) {
                    moves.add(new Move(getX() - i, getY() - i));
                }
                // if the piece i found is white add the move and exit loop
                if(!b.offBoard(getX() - i, getY() - i) && b.getPiece(getX() - i, getY() - i) > 0) {
                    moves.add(0, new Move(getX() - i, getY() - i));
                    break;
                }
                // if the piece i found is black just break
                if(!b.offBoard(getX() - i, getY() - i) && b.getPiece(getX() - i, getY() - i) < 0) {
                    break;
                }
            }
            // stanga-sus
            for(int i = 1; i < 8; i++) {
                // add moves until i find a piece
                if(!b.offBoard(getX() + i, getY() - i) && b.getPiece(getX() + i, getY() - i) == 0) {
                    moves.add(new Move(getX() + i, getY() - i));
                }
                // if the piece i found is white add the move and exit loop
                if(!b.offBoard(getX() + i, getY() - i) && b.getPiece(getX() + i, getY() - i) > 0) {
                    moves.add(0, new Move(getX() + i, getY() - i));
                    break;
                }
                // if the piece i found is black just break
                if(!b.offBoard(getX() + i, getY() - i) && b.getPiece(getX() + i, getY() - i) < 0) {
                    break;
                }
            }

        }

    }

}