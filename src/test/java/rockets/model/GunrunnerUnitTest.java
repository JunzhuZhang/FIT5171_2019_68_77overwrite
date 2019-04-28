package rockets.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GunrunnerUnitTest {
    private Gunrunner gunrunner;

    @BeforeEach
    public void setup() {
        gunrunner = new Gunrunner("Gunrunner", "USA", new LaunchServiceProvider("Provider",2000,"USA"), 0, "", "", "");

    }

    @DisplayName("should throw exception when set fuel to null")
    @Test
    public void shouldThrowExceptionWhenSetFuelToNull() {
        Exception exception = assertThrows(NullPointerException.class, ()->gunrunner.setFuel(null));
        assertEquals(exception.getMessage(), "fuel cannot be null");
    }

    @DisplayName("should throw exception when set usage to empty or null")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void shouldThrowexceptionWhenSetUsageToEmptyOrNull(String usage) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> gunrunner.setUsage(usage));
        assertEquals("usage cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when set shape to null")
    @Test
    public void shouldThrowExceptionWhenSetShapeToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> new Gunrunner(100,"11","22",null));
        assertEquals(exception.getMessage(), "shape cannot be null");
    }

    @DisplayName("should return false when set speed to be negative")
    @Test
    public void shouldThrowExceptionWhenNewInstanceSetNameToNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> gunrunner.setSpeed(-10));
        assertEquals(exception.getMessage(), "speed cannot be negative");
    }


}
