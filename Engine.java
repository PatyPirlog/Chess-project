import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Engine {

    //store the white pieces and the black pieces
    public ArrayList<Piece> whitePieces = new ArrayList<Piece>();
    public ArrayList<Piece> blackPieces = new ArrayList<Piece>();
    Move prevMoveXboard = new Move();
    Move prevMoveEngine = new Move();
    Move currentMoveEngine = new Move();
    //constructor
    public Engine() {

    }

    //checks the format of the move command
    public boolean CheckMove(String c) {

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

    //engine starts in force mode
    public void ForceMode(Board b, ArrayList<Piece> whitePieces, ArrayList<Piece> blackPieces) {

        //implements the board
        int initialMatrix[][] = {
                //-> 0 ----- 7
                {-2, -3, -4, -5, -6, -4, -3, -2},
                {-1, -1, -1, -1, -1, -1, -1, -1},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {2, 3, 4, 5, 6, 4, 3, 2}
        };
        //used for reading from stdin
        String command;
        Scanner scan = new Scanner(System.in);
        String position1 = "";
        //taken - stores the removed piece on the board
        Move taken = new Move();
        char[] move;
        //the arraylist stores the available moves of a piece
        ArrayList<Move> currentMoves = new ArrayList<Move>();

        while (true) {

            //reading from stdin
            command = scan.nextLine();

            //the received command is xboard
            if (command.contentEquals("xboard")) {
                // do nothing
            }

            //the received command is new
            if (command.contentEquals("new")) {
                //resets the board
                b.setMatrix(initialMatrix);
                //the game starts with the black color for the engine
                b.color = -10;
                //initializes the arraylists that store the pieces
                initialArraysPiece(whitePieces, blackPieces);
                //continues the game in normal mode
                NormalMode(b, whitePieces, blackPieces);
                //if the received command is quit, the xboard closes
                if (command.contentEquals("quit")) {
                    break;
                }
            }

            //the received command is black
            if (command.contentEquals("black")) {
                //sets the variable that stores the color of the engine with a
                //negative value for black
                b.color = -10;
            }

            //the received command is white
            if (command.contentEquals("white")) {
                //sets the variable that stores the color of the engine with a
                //positive value for white
                b.color = 10;
            }

            //if the received command is quit, the xboard closes
            if (command.contentEquals("quit")) {
                break;
            }

            //the received command is resign
            if (command.contentEquals("resign")) {
                //the other player resigned
                //continues reading in case of "new" command
            }

            //the received command is go and the engine plays with black
            if (command.contentEquals("go") && b.color < 0) {
                //continues the game in normal mode
                NormalMode(b, whitePieces, blackPieces);
            }

            //the received command is go and the engine plays with white
            if (command.contentEquals("go") && b.color > 0) {
                //the engine moves first, then the game continues in normal mode
                moveFromEngine(b, currentMoves, taken, whitePieces, blackPieces);
                NormalMode(b, whitePieces, blackPieces);
            }

            //the received command is a move`
            if (CheckMove(command)) {
                //apply move command from engine
                move = command.toCharArray();
                //position1 stores the current position of the piece
                position1 = Character.toString(move[0]);
                position1 = position1 + (move[1]);

                //the piece coordinates are taken from positon1
                //b.x and b.y stores the coordinates
                b.TranslatePosition(position1);

                //check if it is a black piece - a negative value
                if (b.matrix[b.x][b.y] < 0) {
                    //xboard moves a black piece from move[0]+move[1] to
                    //move[3]+move[4]
                    moveFromXboard(b, move, whitePieces, blackPieces);
                }

                //check if it is a white piece - a positive value
                if (b.matrix[b.x][b.y] > 0) {
                    //xboard moves a white piece from move[0]+move[1] to
                    //move[3]+move[4]
                    moveFromXboard(b, move, blackPieces, whitePieces);
                }
            }
        }
        //finish the reading
        scan.close();
    }

    //engine starts in normal mode
    public void NormalMode(Board b, ArrayList<Piece> whitePieces, ArrayList<Piece> blackPieces) {

        //implements the board
        int initialMatrix[][] = {    // never modified
                {-2, -3, -4, -5, -6, -4, -3, -2},
                {-1, -1, -1, -1, -1, -1, -1, -1},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {2, 3, 4, 5, 6, 4, 3, 2}
        };
        //used for reading from stdin
        String command;
        Scanner scan = new Scanner(System.in);
        String position1 = "";
        char[] move;
        //taken - stores the removed piece on the board
        Move taken = new Move();
        //the arraylist stores the available moves of a piece
        ArrayList<Move> currentMoves = new ArrayList<Move>();

        while (true) {

            //reading from stdin
            command = scan.nextLine();

            //the received command is xboard
            if (command.contentEquals("xboard")) {
                // do nothing
            }

            //the received command is new
            if (command.contentEquals("new")) {
                //reset the board
                b.setMatrix(initialMatrix);
                //the game starts with the black color for the engine
                b.color = -10;
                //initialize the arraylists that store the pieces
                initialArraysPiece(whitePieces, blackPieces);
            }

            //the received command is black
            if (command.contentEquals("black")) {
                //set the variable that stores the color of the engine with a
                //negative value for black
                b.color = -10;
            }

            //the received command is white
            if (command.contentEquals("white")) {
                //set the variable that stores the color of the engine with a
                //positive value for white

                b.color = 10;
                // if (b.color > 0) {
                //    ////the engine moves first a white piece
                //   moveFromEngine(b, currentMoves, taken, whitePieces, blackPieces);
                //}
            }

            //am adaugat go
            if (command.contentEquals("go")) {
                if (b.color > 0) {
                    ////the engine moves first a white piece
                    moveFromEngine(b, currentMoves, taken, whitePieces, blackPieces);
                }
                if (b.color < 0) {

                    moveFromEngine(b, currentMoves, taken, blackPieces, whitePieces);
                }
            }

            //the received command is force
            if (command.contentEquals("force")) {
                ForceMode(b, whitePieces, blackPieces);
            }

            //if the received command is quit, the xboard closes
            if (command.contentEquals("quit")) {
                break;
            }

            //the received command is resign
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

                //the piece coordinates are taken from positon1
                //b.x and b.y stores the coordinates
                b.TranslatePosition(position1); //x,y are matrix coordiantes

                //check if it is a black piece - a negative value
                if (b.matrix[b.x][b.y] < 0) {
                    //xboard moves a black piece from move[0]+move[1] to
                    //move[3]+move[4]
                    moveFromXboard(b, move, whitePieces, blackPieces);
                }

                //check if it is a white piece - a positive value
                if (b.matrix[b.x][b.y] > 0) {
                    //xboard moves a white piece from move[0]+move[1] to
                    //move[3]+move[4]
                    moveFromXboard(b, move, blackPieces, whitePieces);
                }

                //if engine plays with black
                if (b.color < 0) {
                    //engine moves a black piece
                    moveFromEngine(b, currentMoves, taken, blackPieces, whitePieces);
                }

                //if engine plays with white
                if (b.color > 0) {
                    //engine moves a white piece
                    moveFromEngine(b, currentMoves, taken, whitePieces, blackPieces);
                }
            }
        }
        //finish the reading
        scan.close();
    }

    //the method initializes the arraylists that store the white and black pieces
    public void initialArraysPiece(ArrayList<Piece> whitePieces, ArrayList<Piece> blackPieces) {

        //clear the arrays
        whitePieces.clear();
        blackPieces.clear();

        //add the white pawns from index 0 to 7
        for (int i = 0; i <= 7; i++) {
            //type's pawn -> 1
            Pawn p = new Pawn(6, i, 1);
            whitePieces.add(p);
        }

        //add the other pieces
        //rook's type -> 2; index 8,9
        whitePieces.add(new Rook(7, 0, 2));
        whitePieces.add(new Rook(7, 7, 2));
        //knight's type -> 3; index 10,11
        whitePieces.add(new Knight(7, 1, 3));
        whitePieces.add(new Knight(7, 6, 3));
        //bishop's type -> 4; index 12, 13
        whitePieces.add(new Bishop(7, 2, 4));
        whitePieces.add(new Bishop(7, 5, 4));
        //queen's type -> 5; index 14
        whitePieces.add(new Queen(7, 3, 5));
        //king's type -> 6; index 15
        whitePieces.add(new King(7, 4, 6));

        //add the white pawns from index 0 to 7
        for (int i = 0; i <= 7; i++) {
            //type's pawn -> -1
            Pawn p = new Pawn(1, i, -1);
            blackPieces.add(p);
        }

        //add the other pieces
        //rook's type -> -2; index 8,9
        blackPieces.add(new Rook(0, 0, -2));
        blackPieces.add(new Rook(0, 7, -2));
        //knight's type -> -3; index 10,11
        blackPieces.add(new Knight(0, 1, -3));
        blackPieces.add(new Knight(0, 6, -3));
        //bishop's type -> -4; index 12, 13
        blackPieces.add(new Bishop(0, 2, -4));
        blackPieces.add(new Bishop(0, 5, -4));
        //queen's type -> -5; index 14
        blackPieces.add(new Queen(0, 3, -5));
        //king's type -> -6; index 15
        blackPieces.add(new King(0, 4, -6));
    }

    //the method implements a move from xboard
    public void moveFromXboard(Board b, char[] move, ArrayList<Piece> engineColor, ArrayList<Piece> xboardColor) {
        //position2 stores the wanted move
        String position2;
        //for searching the right piece for moving

        for (int i = 0; i <= 15; i++) {
            //check that the current piece isn't removed
            if (xboardColor.get(i) != null) {
                //daca e pion si daca trebuie schimbat in regina
                if (xboardColor.get(i).getType() == -1 || xboardColor.get(i).getType() == 1) {
                    Promotion(xboardColor.get(i), xboardColor, b);
                }

                if(xboardColor.get(i).getType() == -6 || xboardColor.get(i).getType() == 6) {
                    if(checkSmallCastle((King)xboardColor.get(i), (Rook)xboardColor.get(9), b)) {
                        // schimb pozitia regelui in matrice si in array
                        makeSmallCastle((King) xboardColor.get(i), (Rook) xboardColor.get(9), xboardColor.get(i).getType(), xboardColor, b);
                        //setam pe true pt ca a facut rocada
                        xboardColor.get(i).setMoved(true);
                        xboardColor.get(9).setMoved(true);
                        //daca nu pot rocada mica, atunci aia mare
                    } else if(checkBigCastle((King)xboardColor.get(i), (Rook)xboardColor.get(8), b)) {
                        makeBigCastle((King)xboardColor.get(i), (Rook)xboardColor.get(8), xboardColor.get(i).getType(), xboardColor, b);
                        xboardColor.get(i).setMoved(true);
                        xboardColor.get(8).setMoved(true);
                    }
                }

                Boolean checkEnPassant =  b.checkEnPassantFromXboard(b.x, b.y, prevMoveEngine, currentMoveEngine, xboardColor.get(i));
                //checkEnPassant = false;
                if (checkEnPassant && b.x == xboardColor.get(i).getX() && b.y == xboardColor.get(i).getY()) {
                    b.setPosition(b.x, b.y);
                    b.setPosition(currentMoveEngine.getX(), currentMoveEngine.getY());

                    for (int j = 0; j <= 15; j++) {
                        if (engineColor.get(j) != null) {
                            if (engineColor.get(j).getX() == currentMoveEngine.getX() &&
                                    engineColor.get(j).getY() == currentMoveEngine.getY()) {
                                engineColor.set(j, null);
                            }
                        }
                    }
                }
                //find the piece with the coordinates b.x and b.y
                //b.x, b.y store the initial position
                if ((b.x == xboardColor.get(i).getX() && b.y == xboardColor.get(i).getY())) {
                    position2 = Character.toString(move[2]);
                    position2 = position2 + (move[3]);
                    //b.x and b.y store now the new position
                    b.TranslatePosition(position2);
                    //retain prevMoveXboard
                    prevMoveXboard.setParam(xboardColor.get(i).getX(), xboardColor.get(i).getY(),
                            xboardColor.get(i).getType());

                    if((i == 8 || i == 9 || i == 15) && engineColor.get(i) != null) {
                        xboardColor.get(i).setMoved(true); //setat pe true
                    }



                    b.changePosition(b.x, b.y, xboardColor.get(i));
                    //check if it was a piece from the opponent for removing it from array
                    if (!checkEnPassant) {
                        for (int j = 0; j <= 15; j++) {
                            if (engineColor.get(j) != null) {
                                if (engineColor.get(j).getX() == b.x &&
                                        engineColor.get(j).getY() == b.y) {
                                    engineColor.set(j, null);
                                }
                            }
                        }
                    }
                    break;
                }
            }
        }
    }

    //the method implements a move from engine
    public void moveFromEngine(Board b, ArrayList<Move> currentMoves, Move taken, ArrayList<Piece> engineColor, ArrayList<Piece> xboardColor) {
        //for check if a move has been made
        int ok;
        //position1 stores the initial position
        //position2 stores the new position
        String position1, position2;
        ArrayList<ArrayList<Move>> allMoves = new ArrayList<ArrayList<Move>>();

        for (int i = 0; i <= 15; i++) {
            allMoves.add(null);
        }


        for (int i = 0; i <= 15; i++) {
            ArrayList<Move> aux = new ArrayList<>();
            if (engineColor.get(i) != null) {
                ArrayList<Move> avMov = new ArrayList<>();
                //aux.addAll(engineColor.get(i).availableMoves(b, taken));
                for (Move auxMov : engineColor.get(i).availableMoves(b, taken)) {
                    aux.add(auxMov);
                }

                //en passant
                if (b.checkEnPassantFromEngine(engineColor.get(i), xboardColor, prevMoveXboard) != null) {
                    Move enpassant = new Move();
                    enpassant.setEnPassant(true);
                    aux.add(enpassant);
                }
                if (checkSmallCastle(engineColor.get(i), engineColor.get(9), b)) {
                    Move smallCastle = new Move();
                    smallCastle.setSmallCastle(true);
                    aux.add(smallCastle);
                }
                if (checkBigCastle(engineColor.get(i), engineColor.get(8), b)) {
                    Move bigCastle = new Move();
                    bigCastle.setBigCastle(true);
                    aux.add(bigCastle);
                }
                allMoves.set(i, aux);
               // allMoves.add(aux);
            }
        }

        //verificare daca e rege atacat
        //coordonatele regelui transformate intr o mutare
        Move km = new Move(engineColor.get(15).getX(), engineColor.get(15).getY());
        //initializare stari
        int[][] matrixx = new int[8][8];
        for (int x = 0; x <= 7; x++) {
            for (int y = 0; y <= 7; y++ ) {
                matrixx[x][y] = b.getMatrix()[x][y];
            }
        }
        // board folosit pt copy
        Board auxBoard = new Board(matrixx);
        auxBoard.setPosition(b.x, b.y);
        ArrayList<Piece> auxEngine = new ArrayList<Piece>();
        ArrayList<Piece> auxXboard = new ArrayList<Piece>();
        auxEngine = copyArray(engineColor);
        auxXboard = copyArray(xboardColor);
        //if (!kingCheck(auxBoard, km, auxEngine, auxXboard)) {
            if (allMoves != null) {
                // pentru fiecare piesa
                for (int i = 0; i < allMoves.size(); i++) {
                    if (allMoves.get(i) != null) {
                        //pt fieacre move al piesei i
                        for (int j = 0; j < allMoves.get(i).size(); j++) {
                            if (auxEngine.get(i) != null) {
                                auxBoard.changePosition(allMoves.get(i).get(j).getX(), allMoves.get(i).get(j).getY(),
                                        auxEngine.get(i));
                            }
                            for (int k = 0; k <= auxXboard.size() - 1; k++) {
                                if (auxXboard != null) {
                                    if (auxXboard.get(k) != null) {
                                        if (auxXboard.get(k).getX() == allMoves.get(i).get(j).getX() &&
                                                auxXboard.get(k).getY() == allMoves.get(i).get(j).getY()) {
                                            auxXboard.set(k, null);
                                        }
                                    }
                                }
                            }
                            // daca fac miscarea si regele e tot in sah inseamna ca nu e buna si o sterg
                            if (!kingCheck(auxBoard, km, auxEngine, auxXboard)) {
                                allMoves.get(i).set(j, null);
                            }
                            for (int x = 0; x <= 7; x++) {
                                for (int y = 0; y <= 7; y++ ) {
                                    matrixx[x][y] = b.getMatrix()[x][y];
                                }
                            }
                            auxBoard.setMatrix(matrixx);
                            auxBoard.setPosition(b.x, b.y);
                            auxEngine.clear();
                            auxXboard.clear();
                            auxEngine = copyArray(engineColor);
                            auxXboard = copyArray(xboardColor);
                        }
                    }
                }
            }

        for (int i = 0; i < allMoves.size(); i++) {
            ok = 0;
            if (allMoves.get(i) != null) {
                for (int j = 0; j < allMoves.get(i).size(); j++) {
                    if (allMoves.get(i).get(j) != null) {

                        Move currentMove = allMoves.get(i).get(j);
                        if (currentMove.isEnPassant() == true) {
                            Piece pieceEnPassant = b.checkEnPassantFromEngine(engineColor.get(i), xboardColor, prevMoveXboard);
                            if (pieceEnPassant != null) {
                                b.makeEnPassant(engineColor.get(i), pieceEnPassant, prevMoveXboard, prevMoveEngine, xboardColor, engineColor, b);
                                ok = 1;

                            }
                        }

                        else if (currentMove.isSmallCastle() == true) {
                            makeSmallCastleEngine((King)engineColor.get(i), (Rook)engineColor.get(9), engineColor.get(i).getType(), engineColor, b);
                            //setam pe true pt ca a facut rocada
                            engineColor.get(i).setMoved(true);
                            engineColor.get(9).setMoved(true);
                            //daca nu pot rocada mica, atunci aia mare
                            ok = 1;
                        }

                        else if (currentMove.isBigCastle() == true) {
                            makeBigCastleEngine((King)engineColor.get(i), (Rook)engineColor.get(8), engineColor.get(i).getType(), engineColor, b);
                            engineColor.get(i).setMoved(true);
                            engineColor.get(8).setMoved(true);
                            ok = 1;
                        }

                        else {
                            //daca e pion si daca trebuie schimbat in regina
                            if (engineColor.get(i).getType() == -1 || engineColor.get(i).getType() == 1) {
                                Promotion(engineColor.get(i), engineColor, b);
                            }

                            //check if it was a piece from the opponent for removing it from array
                            position1 = b.TranslateToMove(engineColor.get(i).getX(),
                                    engineColor.get(i).getY());
                            position2 = b.TranslateToMove(currentMove.getX(), currentMove.getY());
                            //retain prevMoveEngine
                            prevMoveEngine.setParam(engineColor.get(i).getX(), engineColor.get(i).getY(),
                                    engineColor.get(i).getType());


                            if((i == 8 || i == 9 || i == 15) && engineColor.get(i) != null) {
                                engineColor.get(i).setMoved(true);
                            }

                            //update the board and the coordinates of the piece
                            b.changePosition(currentMove.getX(), currentMove.getY(), engineColor.get(i));
                            for (int k = 0; k <= 15; k++) {
                                if (xboardColor.get(k) != null) {
                                    if (xboardColor.get(k).getX() == engineColor.get(i).getX() &&
                                            xboardColor.get(k).getY() == engineColor.get(i).getY()) {
                                        xboardColor.set(k, null);
                                    }
                                }
                            }
                            currentMoveEngine.setParam(engineColor.get(i).getX(), engineColor.get(i).getY(),
                                    engineColor.get(i).getType());
                            //stdout: new move from engine
                            System.out.println("move " + position1 + position2);
                            System.out.flush();
                            //System.out.println(b.toString());
                            //a move has been sent
                            ok = 1;
                            break;
                        }
                    }
                }
            }
            if (ok == 1) {
                break;
            }
            //to send resign
            int nr = 0;
            //calculate the removed pieces
            for (int j = 0; j < allMoves.size(); j++) {
                if (allMoves.get(j) == null) {
                    nr++;
                }
            }
            //send resign if no moves or no more pieces
            if ((ok == 0 && i == allMoves.size() - 1) || nr == allMoves.size() - 1) {
            //if (allMoves)
                //stdout: resign
                System.out.println("resign");
                System.out.flush();
            }

        }

    }

    //metoda de apelare:
    /*
    //km - king's position
       Move km = new Move(engineColor.get(15).getX(), engineColor.get(15).getY());
       if (kingCheck(b, km, engineColor, xboardColor))
     */
    // checks if the king (m)is endangered (in the move list of the opposite player)
    public boolean kingCheck(Board b, Move m, ArrayList<Piece> engineColor, ArrayList<Piece> xboardColor) {
        ArrayList<Move> xboardMoves = new ArrayList<>();
        Move mov = new Move(); // aux
        for (Piece p : xboardColor) {
            if (p != null) {
                xboardMoves.addAll(p.availableMoves(b, mov));
                if (xboardMoves.size() != 0) {
                    for (Move xm : xboardMoves) {
                        if (m.getX() == xm.getX() && m.getY() == xm.getY())
                            return false; // the king is endangered by piece p
                    }
                }
            }
        }
        return true; //returns true if the king is NOT endangered by move m
    }

    //primeste un pion si returneaza o regina daca ajunge pe 0 sau 6
    // pionul va trb inlocuit in array cu regina intoarsa de functie !!!!!!

    public ArrayList<Piece> copyArray (ArrayList<Piece> pieces) {
        ArrayList<Piece> copy = new ArrayList<>();
        for (int w = 0; w <= 7; w++) {
            //type's pawn -> -1
            if (pieces.get(w) != null) {
                copy.add(new Pawn( pieces.get(w).getX(), pieces.get(w).getY(), pieces.get(w).getType()));
            }
            else {
                copy.add(null);
            }
        }
        if (pieces.get(8) != null) {
            copy.add(new Rook(pieces.get(8).getX(), pieces.get(8).getY(), pieces.get(8).getType()));
        } else {
            copy.add(null);
        }
        if (pieces.get(9) != null) {
            copy.add(new Rook(pieces.get(9).getX(), pieces.get(9).getY(), pieces.get(9).getType()));
        } else {
        copy.add(null);
    }
        if (pieces.get(10) != null) {
            copy.add(new Knight(pieces.get(10).getX(), pieces.get(10).getY(), pieces.get(10).getType()));
        } else {
            copy.add(null);
        }
        if (pieces.get(11) != null) {
            copy.add(new Knight(pieces.get(11).getX(), pieces.get(11).getY(), pieces.get(11).getType()));
        } else {
            copy.add(null);
        }
        if (pieces.get(12) != null) {
            copy.add(new Bishop(pieces.get(12).getX(), pieces.get(12).getY(), pieces.get(12).getType()));
        } else {
        copy.add(null);
    }
        if (pieces.get(13) != null) {
            copy.add(new Bishop(pieces.get(13).getX(), pieces.get(13).getY(), pieces.get(13).getType()));
        } else {
            copy.add(null);
        }
        //queen's type -> 5; index 14
        if (pieces.get(14) != null) {
            copy.add(new Queen(pieces.get(14).getX(), pieces.get(14).getY(), pieces.get(14).getType()));
        } else {
            copy.add(null);
        }
        //king's type -> 6; index 15
        if (pieces.get(15) != null) {
            copy.add( new King(pieces.get(15).getX(), pieces.get(15).getY(), pieces.get(15).getType()));
        } else {
            copy.add(null);
        }
        return copy;
    }

    public void Promotion(Piece pawn, ArrayList<Piece> pieces, Board b) {
        int change = 0;
        Queen queen = new Queen();
        // daca e pion de negru
        if(pawn.getType() < 0) {
            // daca ajunge la capat
            if(pawn.getX() == 7) {
                //setez
                queen.setParam(pawn.getX(), pawn.getY());
                queen.setType(-5);
                change = 1;
            }
            // daca e pion de alb
        }
        if(pawn.getType() > 0) {
            // daca ajunge la capat
            if(pawn.getX() == 0) {
                queen.setParam(pawn.getX(), pawn.getY());
                queen.setType(5);
                change = 1;
            }
        }
        if (change == 1) {
            for (int i = 0; i <= 15; i++) {
                //daca e pionul ce trebuie schimbat
                if (pieces.get(i) != null) {
                    if (pawn.getX() == pieces.get(i).getX() && pawn.getY() == pieces.get(i).getY()) {
                        pieces.set(i, queen);
                    }
                }
            }
            b.setType(queen.getX(), queen.getY(), queen.getType());
        }
    }

    // verific daca se poate face rocada mica pt ambele culori
    public boolean checkSmallCastle(Piece king, Piece rook, Board b) {
        // daca sunt piese albe
        // int newYRook;
        // int newYKing;
        // daca piesele n au mai fost mutate
        if(king.getType() == -6 || king.getType() == 6 ) {
            if (king == null || rook == null )
                return false;
            else if (king.getMoved() == false && rook.getMoved() == false &&
                    b.getPiece(king.getX(), king.getY() + 1) == 0 &&
                    b.getPiece(king.getX(), king.getY() + 2) == 0)
                return true;
        }
        return false;

    }
    // verific daca se poate face rocada mare pt ambele culori
    public boolean checkBigCastle(Piece king, Piece rook, Board b) {
        //daca n au mai fost mutate
        if(king.getType() == -6 || king.getType() == 6 ) {
            if (rook == null || king == null)
                return false;
            else if (king.getMoved() == false && rook.getMoved() == false &&
                    // daca pozitiile din stanga sunt libere
                    b.getPiece(king.getX(), king.getY() - 1) == 0 &&
                    b.getPiece(king.getX(), king.getY() - 2) == 0 &&
                    b.getPiece(king.getX(), king.getY() - 3) == 0)
                return true;
        }
        return false;

    }

    public void makeSmallCastle(King king, Rook rook, int type, ArrayList<Piece> xboardColor, Board b) {
        // daca rocada e neagra
        if(type < 0) {
            //schimb pozitia regelui in matrice si array
            b.setType(king.getX(), king.getY(), 0); //setez 0 unde a fost
            b.setType(0, 6, king.getType());
            xboardColor.get(15).setParam(0, 6);

            // schimb pozitia rook in matrice si in array
            b.setType(rook.getX(), rook.getY(), 0); // setez 0 unde a fost
            b.setType(0, 5, rook.getType());
            xboardColor.get(9).setParam(0, 5);
            //daca rocada e alba
        } else {
            //setez pozitiile pt king
            b.setType(king.getX(), king.getY(), 0); // setez 0 unde a fost
            b.setType(7, 6, king.getType());
            xboardColor.get(15).setParam(7, 6);


            //setez pozitiile pt rook
            b.setType(rook.getX(), rook.getY(), 0); // setez 0 unde a fost
            b.setType(7, 5, rook.getType());
            xboardColor.get(9).setParam(7, 5);
        }
    }

    public void makeBigCastle(King king, Rook rook, int type, ArrayList<Piece> xboardColor, Board b) {
        // daca rocada e neagra
        if(type < 0) {
            //schimb pozitia regelui in matrice si array
            b.setType(king.getX(), king.getY(), 0); //setez 0 unde a fost
            b.setType(0, 2, king.getType());
            xboardColor.get(15).setParam(0, 2);

            // schimb pozitia rook in matrice si in array
            b.setType(rook.getX(), rook.getY(), 0); // setez 0 unde a fost
            b.setType(0, 3, rook.getType());
            xboardColor.get(8).setParam(0, 3);
            //daca rocada e alba
        } else {
            //setez pozitiile pt king
            b.setType(king.getX(), king.getY(), 0); // setez 0 unde a fost
            b.setType(7, 2, king.getType());
            xboardColor.get(15).setParam(7, 2);


            //setez pozitiile pt rook
            b.setType(rook.getX(), rook.getY(), 0); // setez 0 unde a fost
            b.setType(7, 3, rook.getType());
            xboardColor.get(8).setParam(7, 3);
        }
    }
    public void makeSmallCastleEngine(King king, Rook rook, int type, ArrayList<Piece> engineColor, Board b) {
        if(type < 0) {
            //schimb pozitia regelui in matrice si array
            b.setType(king.getX(), king.getY(), 0); //setez 0 unde a fost
            b.setType(0, 6, king.getType());
            engineColor.get(15).setParam(0, 6);

            // schimb pozitia rook in matrice si in array
            b.setType(rook.getX(), rook.getY(), 0); // setez 0 unde a fost
            b.setType(0, 5, rook.getType());
            engineColor.get(9).setParam(0, 5);
            //daca rocada e alba

            System.out.println("move e8g8");
            System.out.flush();
        } else if(type > 0) {
            //setez pozitiile pt king
            b.setType(king.getX(), king.getY(), 0); // setez 0 unde a fost
            b.setType(7, 6, king.getType());
            engineColor.get(15).setParam(7, 6);


            //setez pozitiile pt rook
            b.setType(rook.getX(), rook.getY(), 0); // setez 0 unde a fost
            b.setType(7, 5, rook.getType());
            engineColor.get(9).setParam(7, 5);

            System.out.println("move e1g1");
            System.out.flush();
        }
    }

    public void makeBigCastleEngine(King king, Rook rook, int type, ArrayList<Piece> engineColor, Board b) {
        // daca rocada e neagra
        if(type < 0) {
            //schimb pozitia regelui in matrice si array
            b.setType(king.getX(), king.getY(), 0); //setez 0 unde a fost
            b.setType(0, 2, king.getType());
            engineColor.get(15).setParam(0, 2);

            // schimb pozitia rook in matrice si in array
            b.setType(rook.getX(), rook.getY(), 0); // setez 0 unde a fost
            b.setType(0, 3, rook.getType());
            engineColor.get(8).setParam(0, 3);

            System.out.println("move e8c8");
            System.out.flush();
            //daca rocada e alba
        } else {
            //setez pozitiile pt king
            b.setType(king.getX(), king.getY(), 0); // setez 0 unde a fost
            b.setType(7, 2, king.getType());
            engineColor.get(15).setParam(7, 2);


            //setez pozitiile pt rook
            b.setType(rook.getX(), rook.getY(), 0); // setez 0 unde a fost
            b.setType(7, 3, rook.getType());
            engineColor.get(8).setParam(7, 3);

            System.out.println("move e1c1");
            System.out.flush();
        }
    }
}