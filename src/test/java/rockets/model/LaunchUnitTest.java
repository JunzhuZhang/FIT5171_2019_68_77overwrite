package rockets.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class LaunchUnitTest {
    private Launch launch;
    private Launch launch1;
    private Rocket rockets;
    private LaunchServiceProvider lsps;

    @BeforeEach
    public void setup() {
        launch = new Launch();
        launch1 = new Launch(LocalDate.of(2017,3,2),rockets,lsps,"LEO");
    }

    @DisplayName("should throw exception when set payload to null")
    @Test
    public void shouldThrowExceptionWhenSetPayloadToNull() {
        Exception exception = assertThrows(NullPointerException.class, ()->launch.setPayload(null));
        assertEquals(exception.getMessage(), "payload cannot be null");
    }

    @DisplayName("should return true if payload is in the list")
    @Test
    public void shouldReturnTrueIfPayloadInTheList() {
        assertTrue(launch.isPayloadInTheList("satellites"));
        assertFalse(launch.isPayloadInTheList("satellites2"));
    }

    @DisplayName("should return false when two objects are different")
    @Test
    public void shouldReturnFalseWhenTwoObjectsAreDifferent() {
        Launch l  = launch1;                     // refer to same launch
        assertTrue(launch1.equals(l));
        Launch launch2= null;                       // one rocket set to null
        assertFalse(launch.equals(launch2));
        User user = new User();                      // different classes
        assertFalse(launch.equals(user));
        Launch launch3 = new Launch(LocalDate.of(2016,3,2),rockets,lsps,"LEO");   // with different attribute
        assertFalse(launch.equals(launch3));
    }
}
