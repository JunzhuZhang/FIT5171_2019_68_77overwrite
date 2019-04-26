package rockets.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LaunchUnitTest {
    private Launch launch;

    @BeforeEach
    public void setup() {
        launch = new Launch();
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
}
