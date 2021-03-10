public class Move {
    private int x;
    private int y;
    private int type;
    private boolean enPassant = false;
    private boolean smallCastle = false;
    private boolean bigCastle = false;

    public Move() {

    }
    //creez o mutare
    public Move(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }

    public boolean isEnPassant() {
        return enPassant;
    }

    public boolean isSmallCastle() {
        return smallCastle;
    }

    public boolean isBigCastle() {
        return bigCastle;
    }

    public void setParam(int newX, int newY, int newType) {
        this.x = newX;
        this.y = newY;
        this.type = newType;
    }

    public void setEnPassant(boolean enPassant) {
        this.enPassant = enPassant;
    }

    public void setSmallCastle(boolean smallCastle) {
        this.smallCastle = smallCastle;
    }

    public void setBigCastle(boolean bigCastle) {
        this.bigCastle = bigCastle;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getType() {
        return type;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public String toString() {
        return x + " " + y + " ";
    }

}