import java.util.ArrayList;
import java.util.Scanner;

public class Test {

    public ArrayList<Piece> whitePieces = new ArrayList<Piece>();
    public ArrayList<Piece> blackPieces = new ArrayList<Piece>();



    public Test () {
    }

    //o mutare are formatul : a2a4 - char-digit-char-digit
    public boolean CheckMove(String c) {
        // checks the format of the move command
        char[] aux = c.toCharArray();
        if (!Character.isLowerCase(aux[0]))
            return false;
        if (!Character.isDigit(aux[1]))
            return false;
        if (!Character.isLowerCase(aux[2]))
            return false;
        if (!Character.isDigit(aux[3]))
            return false;
        return true;
    }

    public void ForceMode(Board b, ArrayList<Piece> whitePieces, ArrayList<Piece> blackPieces) {
        // aici engine citeste moves de la xboard si le schimba cu
        // changePosition
        int initialMatrix[][] = {    // never modified
                {-2, -3, -4, -5, -6, -4, -3, -2},
                {-1, -1, -1, -1, -1, -1, -1, -1},
                { 0,  0,  0,  0,  0,  0,  0,  0},
                { 0,  0,  0,  0,  0,  0,  0,  0},
                { 0,  0,  0,  0,  0,  0,  0,  0},
                { 0,  0,  0,  0,  0,  0,  0,  0},
                { 1,  1,  1,  1,  1,  1,  1,  1},
                { 2,  3,  4,  5,  6,  4,  3,  2}
        };
        Scanner scan = new Scanner(System.in);
        String command;
        //= scan.nextLine();
        String position1 = "", position2 = "";
        Move taken = new Move();
        char[] move;
        int ok;
        ArrayList<Move> currentMoves = new ArrayList<Move>();
        while (true) {
            command = scan.nextLine();
            // System.out.println("IN FORCE COMANDA CURENTA: " + command);
            if (command.contentEquals("xboard")) {
                // do nothing
            }
            //if (command.contentEquals("new")) {
            if (command.contentEquals("new")) {
                //command = scan.ne
                b.setMatrix(initialMatrix);
                b.color = -10;
                //coordonatele din array cu piese trb sa revina la alea din main
                initialArraysPiece ( whitePieces, blackPieces);
                //continue;
                NormalMode(b, whitePieces, blackPieces);
            }
            if (command.contentEquals("black")) {
                // engine plays black (negative numbers)
                b.color = -10;
            }
            if (command.contentEquals("white")) {
                // engine plays white (positive numbers)
                // nu trebuie sa adaug cum jaoca cu white pr ca nu trb sa trimtia mutari
                b.color = 10;

            }
            if (command.contentEquals("quit")) {
                // xboard closes
                break;
            }
            if (command.contentEquals("resign")) {
                // the other player resigned
                // continue reading in case of "new" command
            }
            if (command.contentEquals("go") && b.color < 0) {
                NormalMode(b, whitePieces, blackPieces);
            }
            if (command.contentEquals("go") && b.color > 0) {

                //lucram cu white
                //mutam doar pionii momentan

                moveFromEngine (b, currentMoves, taken, whitePieces, blackPieces );
                NormalMode(b, whitePieces, blackPieces);
            }
            if (CheckMove(command)) {
                // apply move command from engine

                move = command.toCharArray();
                position1 = Character.toString(move[0]);
                position1 = position1 + (move[1]);

                // the piece is taken from positon1
                b.TranslatePosition(position1); //x,y are matrix coordiantes

                if(b.matrix[b.x][b.y] < 0) {

                    moveFromXboard (b, move, whitePieces, blackPieces);
                }
                if(b.matrix[b.x][b.y] > 0) {

                    moveFromXboard (b, move, blackPieces, whitePieces);
                }



            }
        }
        scan.close();
    }

    public void NormalMode(Board b, ArrayList<Piece> whitePieces, ArrayList<Piece> blackPieces) {

        Scanner scan = new Scanner(System.in);
        String command, position1 = "", position2 = "";
        char[] move;
        int ok = 0;

        //in taken retinem coordonatele piesei pe care o vom lua pentru a verifica ulterior
        Move taken = new Move();

        //fiecare piesa va avea in acest Array mutarile posibile
        ArrayList<Move> currentMoves = new ArrayList<Move>();

        int initialMatrix[][] = {    // never modified
                {-2, -3, -4, -5, -6, -4, -3, -2},
                {-1, -1, -1, -1, -1, -1, -1, -1},
                { 0,  0,  0,  0,  0,  0,  0,  0},
                { 0,  0,  0,  0,  0,  0,  0,  0},
                { 0,  0,  0,  0,  0,  0,  0,  0},
                { 0,  0,  0,  0,  0,  0,  0,  0},
                { 1,  1,  1,  1,  1,  1,  1,  1},
                { 2,  3,  4,  5,  6,  4,  3,  2}
        };

        while (true) {

            command = scan.nextLine();

            //xboard
            if (command.contentEquals("xboard")) {
                // do nothing
            }

            //new
            if (command.contentEquals("new")) {
                //la fiecare joc nou reinitializam matricea
                b.setMatrix(initialMatrix);
                b.color = -10; //consideram ca incepem mereu cu black

                initialArraysPiece ( whitePieces, blackPieces);
            }

            //black
            if (command.contentEquals("black")) {
                // engine plays black (negative numbers)
                b.color = -10;
                //continue;
            }

            //white
            if (command.contentEquals("white")) {
                // engine plays white (positive numbers)
                b.color = 10;
                if (b.color > 0) {
                    //lucram cu white
                    //mutam doar pionii momentan

                    moveFromEngine (b, currentMoves, taken, whitePieces, blackPieces );

                }
            }

            //force
            if (command.contentEquals("force")) {
                ForceMode(b, whitePieces, blackPieces);
            }

            //quit
            if (command.contentEquals("quit")) {
                // xboard closes
                break;
            }

            //resign
            if (command.contentEquals("resign")) {
                // the other player resigned
                // continue reading in case of "new" command
            }

            // if xboard sends a move command
            if (CheckMove(command)) {

                // apply move command from engine
                move = command.toCharArray();
                position1 = Character.toString(move[0]);
                position1 = position1 + (move[1]);

                // the piece is taken from positon1
                b.TranslatePosition(position1); //x,y are matrix coordiantes

                if(b.matrix[b.x][b.y] < 0) {
                    // < 0 inseamna ca xboard muta black

                    moveFromXboard (b, move, whitePieces, blackPieces);
                }
                //
                if(b.matrix[b.x][b.y] > 0) {
                    // > 0 inseamna ca xboard muta white
                    moveFromXboard (b, move, blackPieces, whitePieces);
                }

                if (b.color < 0) {
                    //lucram cu black
                    //mutam doar pionii momentan

                    moveFromEngine (b, currentMoves, taken, blackPieces, whitePieces );
                }

                if (b.color > 0) {
                    //lucram cu white
                    //mutam doar pionii momentan
                    moveFromEngine (b, currentMoves, taken, whitePieces, blackPieces );

                }
            }
        }
        scan.close();
    }

    public void initialArraysPiece (ArrayList<Piece> whitePieces, ArrayList<Piece> blackPieces) {

        //coordonatele din array cu piese trb sa revina la alea din main
        whitePieces.clear();
        blackPieces.clear();

        //adaug pionii albi
        for(int i = 0; i <= 7; i++) {
            Pawn p = new Pawn(6, i, 1);
            whitePieces.add(p);
        }
        //adaug celelalte piese albe
        whitePieces.add(new Rook(7, 0, 2));
        whitePieces.add(new Rook(7, 7, 2));
        whitePieces.add(new Knight(7, 1, 3));
        whitePieces.add(new Knight(7, 6, 3));
        whitePieces.add(new Bishop(7, 2, 4));
        whitePieces.add(new Bishop(7, 5, 4));
        whitePieces.add(new Queen(7, 3, 5));
        whitePieces.add(new King(7, 4, 6));
        //adaug pionii negri
        for(int i = 0; i <= 7; i++) {
            Pawn p = new Pawn(1, i, -1);
            blackPieces.add(p);
        }
        //adaug celalte piese negre
        blackPieces.add(new Rook(0, 0, -2));
        blackPieces.add(new Rook(0, 7, -2));
        blackPieces.add(new Knight(0, 1, -3));
        blackPieces.add(new Knight(0, 6, -3));
        blackPieces.add(new Bishop(0, 2, -4));
        blackPieces.add(new Bishop(0, 5, -4));
        blackPieces.add(new Queen(0, 3, -5));
        blackPieces.add(new King(0, 4, -6));


    }

    public void moveFromXboard (Board b, char[] move, ArrayList<Piece> engineColor, ArrayList<Piece> xboardColor ) {
        String position2;
        for(int i = 0; i <= 15; i++) {
            if(xboardColor.get(i) != null) {
                if(b.x == xboardColor.get(i).getX() && b.y == xboardColor.get(i).getY()) {
                    position2 = Character.toString(move[2]);
                    position2 = position2 + (move[3]);
                    b.TranslatePosition(position2);
                    b.changePosition(b.x, b.y, xboardColor.get(i));
                    for (int j = 0; j <= 15; j++) {
                        if(engineColor.get(j) != null) {
                            if(engineColor.get(j).getX() == b.x &&
                                    engineColor.get(j).getY() == b.y ) {
                                engineColor.set(j, null);
                            }
                        }
                    }
                    break;
                }
            }
        }
    }

    public void moveFromEngine (Board b, ArrayList<Move> currentMoves, Move taken, ArrayList<Piece> engineColor, ArrayList<Piece> xboardColor )  {
        //lucram cu white
        //mutam doar pionii momentan
        int ok;
        String position1, position2;
        for (int i = 0; i <= 7; i++) {
            ok = 0;
            if (engineColor.get(i) != null) {
                currentMoves.clear();
                currentMoves.addAll(engineColor.get(i).availableMoves(b, taken));
                if (currentMoves != null) {
                    //alegem prima mutare posibila din array
                    for (Move m : currentMoves) {
                        for (int j = 0; j <= 15; j++) {
                            if (xboardColor.get(j) != null) {
                                if (xboardColor.get(j).getX() == taken.getX() &&
                                        xboardColor.get(j).getY() == taken.getY()) {
                                    xboardColor.set(j, null);
                                }
                            }
                        }
                        position1 = b.TranslateToMove(engineColor.get(i).getX(),
                                engineColor.get(i).getY());
                        position2 = b.TranslateToMove(m.getX(), m.getY());
                        b.changePosition(m.getX(), m.getY(), engineColor.get(i));
                        System.out.println("move " + position1 + position2);
                        System.out.flush();
                        ok = 1;
                        break;
                    }
                }
            }
            if (ok == 1) {
                break;
            }

            //aici verific daca dau resign
            int nr = 0;
            for (int j = 0; j < engineColor.size(); j++) {
                if (engineColor.get(j) == null) {
                    nr++;
                }
            }
            if ((ok == 0 && i == 7) || nr == engineColor.size()) {
                System.out.println("resign");
                System.out.flush();
            }
        }
    }

}