// class to represent the Castling for both colours
public class Castle extends Move {

    // method that returns the castle move
    // if it hasn't been done in the game
    // se pot face doar daca n au mai fost mutate regele sau tura

    // parametri cu noile pozitii pe care le vor avea king si root
    private int newKingX;
    private int newKingY;
    private int newRookX;
    private int newRookY;

    // ar mai trb adaugat o conditie ca regele sa nu fie amenintat de nicio piesa
    public Move smallCastle(Board b, King king, Rook rook) {
        // check for castle for black pieces
        //if(type < 0) {
        // e la fel indiferent de culoare
        if(canCastle(king, rook) &&
                b.getPiece(king.getX() , king.getY() + 1) == 0 &&
                b.getPiece(king.getX(), king.getY() + 2) == 0) { //verific ca poz din dreapta regelui sa fie 0
            // schimb coordonatele regelui in obiectul Castle
            // cu astea va trebui sa schimb coordonatele in Board in array si in matrice
            setKing(king.getX(), king.getY() + 2);
            setRook(rook.getX(), rook.getY() - 2);

            //aici sa apelez changePosition sa vad cum exact
            //b.changePosition(int );
        }
        // check for castle for white pieces
        // }
        return this;
    }

    public Move bigCastle(Board b, King king, Rook rook) {
        if(canCastle(king, rook) &&
                b.getPiece(king.getX(), king.getY() - 1) == 0 &&
                b.getPiece(king.getX(), king.getY() - 2) == 0 &&
                b.getPiece(king.getX(), king.getY() - 3) == 0) {// verific cele 3 poz langa rege pt rocada mare

            setKing(king.getX(), king.getY() - 2);
            setRook(rook.getX(), rook.getY() + 3);
        }
        return this;
    }
    // returns true if king and root can castle
    // check if they haven't been moved
    public boolean canCastle(King king, Rook rook) {

        if(king.hasBeenMoved() || rook.hasBeenMoved())
            return false;
        else return true;

    }
    public int getKingX() {
        return newKingX;
    }
    public int getKingY() {
        return newKingY;
    }

    public int getRookX() {
        return newRookX;
    }

    public int getRookY() {
        return newRookY;
    }

    public void setKing(int newX, int newY) {
        this.newKingX = newX;
        this.newKingY = newY;
    }

    public void setRook(int newX, int newY) {
        this.newRookX = newX;
        this.newRookY = newY;
    }
}