import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
       /*
       the matrix implements the board
       the positive values - the white pieces
       the negative values - the negative pieces
        1 -> Pawn
        2 -> Rook
        3 -> Knight
        4 -> Bishop
        5 -> Queen
        6 -> King
        */
        int initialMatrix[][] = {
                {-2, -3, -4, -5, -6, -4, -3, -2},
                {-1, -1, -1, -1, -1, -1, -1, -1},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {2, 3, 4, 5, 6, 4, 3, 2}
        };

        /*
        the arraylists stores the pieces, an arraylist for each color
        on position 0-7 -> the 8 pawns
                    8,9 -> the rooks
                    10, 11 -> the knights
                    12, 13 -> the bishops
                    14 -> the queen
                    15 -> the king

         */
        ArrayList<Piece> whitePieces = new ArrayList<Piece>();
        ArrayList<Piece> blackPieces = new ArrayList<Piece>();

        //the instantiation of the board
        Board board = new Board(initialMatrix);

        //the instantiation of the engine
        Engine engine = new Engine();

        //Solve sigint, sigterm:
        System.out.println("feature sigint=0");
        System.out.flush();
        System.out.println("feature sigterm=0");
        System.out.flush();

        //Engine starts :
        engine.NormalMode(board, whitePieces, blackPieces);
    }
}