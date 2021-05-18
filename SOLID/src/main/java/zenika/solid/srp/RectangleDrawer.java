package zenika.solid.srp;

import java.awt.*;

public class RectangleDrawer implements IDrawer {

    private final Rectangle rectangle;

    public RectangleDrawer(Rectangle rectangle){
        this.rectangle = rectangle;
    }

    @Override
    public void draw(Graphics graphics) {
        Point topLeft = rectangle.getTopLeft();
        Point bottomRight = rectangle.getBottomRight();

        //top horizontal line
        graphics.drawLine(
                topLeft.x, topLeft.y,
                bottomRight.x, topLeft.y
        );
        //bottom horizontal line
        graphics.drawLine(
                topLeft.x, bottomRight.y,
                bottomRight.x, bottomRight.y
        );
        //left vertical line
        graphics.drawLine(
                topLeft.x, topLeft.y,
                topLeft.x, topLeft.y - rectangle.getHeight()
        );
        //right vertical line
        graphics.drawLine(
                bottomRight.x, bottomRight.y - rectangle.getHeight(),
                bottomRight.x, bottomRight.y
        );
    }
}
