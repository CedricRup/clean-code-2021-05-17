package zenika.solid.lsp;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class GeometryTest {
    
    @Test public void area_should_be_height_times_width_1() {
        area_should_be_height_times_width(new Rectangle(20, 5));
    }
    
    @Test public void area_should_be_height_times_width_2() {
        area_should_be_height_times_width(new Square(10));
    }

    private void area_should_be_height_times_width(HasArea rect) {
        assertEquals(100, rect.getArea());
    }
    
}
