package zenika.solid.srp;

import java.awt.*;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class Rectangle
{
    private final Point topLeft, bottomRight;

    public Rectangle(Point topLeft, Point bottomRight) {
        this.topLeft = requireNonNull(topLeft);
        this.bottomRight = requireNonNull(bottomRight);
        if(getWidth() <= 0) throw new IllegalArgumentException(format(
                "topLeft(%s) is not to the left of bottomRight(%s)",
                topLeft, bottomRight
        ));
        if(getHeight() <= 0) throw new IllegalArgumentException(format(
                "topLeft(%s) is not to the top of bottomRight(%s)",
                topLeft, bottomRight
        )); 
    }

    public int perimeter() {
        return 2 * (getWidth() + getHeight());
    }

    public int area() {
        return getWidth() * getHeight();
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    public int getWidth() {
        return bottomRight.x - topLeft.x;
    }

    public int getHeight() {
        return topLeft.y - bottomRight.y;
    }

}
