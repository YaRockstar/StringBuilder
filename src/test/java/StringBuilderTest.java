import org.example.StrBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringBuilderTest {
    private static StringBuilder stringBuilder;

    private static StrBuilder strBuilder;

    @BeforeAll
    public static void initialise() {
        stringBuilder = new StringBuilder("test");
        strBuilder = new StrBuilder("test");

        assertEquals(stringBuilder.length(), strBuilder.length());
        assertEquals(stringBuilder.capacity(), strBuilder.capacity());
        assertEquals(stringBuilder.toString(), strBuilder.toString());
    }

    @Test
    public void baseConstructorTest() {
        assertEquals(new StringBuilder().length(), new StrBuilder().length());
        assertEquals(new StringBuilder().capacity(), new StrBuilder().capacity());
        assertEquals(new StringBuilder().toString(), new StrBuilder().toString());
    }

    @Test
    public void capacityConstructorTest() {
        assertEquals(new StringBuilder(14).capacity(), new StrBuilder(14).capacity());
    }

    @Test
    public void setLengthTest() {
        var stringB = new StringBuilder("test");
        stringB.setLength(3);

        var strB = new StrBuilder("test");
        strB.setLength(3);
        assertEquals(stringB.toString(), strB.toString());
    }

    @Test
    public void appendTest() {
        stringBuilder.append(" string");
        strBuilder.append(" string");
        assertEquals(stringBuilder.toString(), strBuilder.toString());

        stringBuilder.append(" string string string string string string string string");
        strBuilder.append(" string string string string string string string string");
        assertEquals(stringBuilder.toString(), strBuilder.toString());
    }

    @Test
    public void insertTest() {
        stringBuilder.insert(3, "present");
        strBuilder.insert(3, "present");
        assertEquals(stringBuilder.toString(), strBuilder.toString());
    }

    @Test
    public void deleteTest() {
        stringBuilder.delete(2, 5);
        strBuilder.delete(2, 5);
        assertEquals(stringBuilder.toString(), strBuilder.toString());
    }

    @Test
    public void trimToSizeTest() {
        stringBuilder.trimToSize();
        strBuilder.trimToSize();
        assertEquals(stringBuilder.length() == stringBuilder.capacity(), strBuilder.length() == strBuilder.capacity());
    }
}
