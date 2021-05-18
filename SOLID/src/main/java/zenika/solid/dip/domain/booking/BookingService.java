package zenika.solid.dip.domain.booking;

public class BookingService {
    
    private final AvailabilityAsker bookings;

    public BookingService(AvailabilityAsker bookings) {
        this.bookings = bookings;
    }

    public BookingOutcome book() {
        boolean successful = bookings.isAvailable();
        return new BookingOutcome(successful);
    }
    
}
