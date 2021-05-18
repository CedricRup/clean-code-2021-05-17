package zenika.solid.lsp;

public class Square implements HasArea {

    private int side;

    public Square(int side) {
        this.side = side;
    }

    public void setSide(int side) {
        this.side = side;
    }
    
    public int getSide() {
        return side;
    }

    @Override
    public int getArea() {
        return side*side;
    }
}
