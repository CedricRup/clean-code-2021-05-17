package zenika.solid.dip.infrastructure;

import zenika.solid.dip.domain.booking.AvailabilityAsker;

public class DatabaseAvailabilityAsker implements AvailabilityAsker {
    
    @Override public boolean isAvailable() {
        //En realite il y aurait une dependance vers JPA/Hib/un WS...
        return true; 
    }
    
}
