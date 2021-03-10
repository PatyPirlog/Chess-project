import java.util.ArrayList;

public abstract class Piece {
    //the coordinates of the piece
    private int x, y;
    public boolean moved = false;
    //the type of the piece
    //negative value - black piece; positive value - white piece
    private int type;
    // cat valoreaza peisa de ex pion 10 si king max_int
    private int value;
    public Piece () {

    }

    //constructor
    public Piece(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }


    public void setMoved(boolean b) {
        this.moved = b;
    }

    public boolean getMoved() {
        return this.moved;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setParam(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return x + " " + y + " " + type;
    }

    //abstract method implemented in derived classes
    public abstract ArrayList<Move> availableMoves(Board board, Move mov);
}