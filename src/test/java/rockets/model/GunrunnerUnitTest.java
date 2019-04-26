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
}
