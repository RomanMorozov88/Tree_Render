package morozov.ru;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TextParserTest {


    @Test
    public void whenCorrectTextFirst() {
        String testInput = "(1 (2 (4 5 6 (7))(10))(3))";
        String result = TextParser.getReadyResult(testInput);
        StringBuilder builder = new StringBuilder();
        builder.append("1\n")
                .append("   2\n")
                .append("      4\n")
                .append("      5\n")
                .append("      6\n")
                .append("         7\n")
                .append("      10\n")
                .append("   3\n");
        assertEquals(builder.toString(), result);
    }

    @Test
    public void whenWrongTextFirst() {
        String testInput = "(1 (2 (4 5 6 (7))(10))(3)";
        String result = TextParser.getReadyResult(testInput);
        assertEquals(null, result);
    }

    @Test
    public void whenCorrectTextSecond() {
        String testInput = "(1 Content1.0 Content1.1 (2 (4 5 6 (7))(10))(3))(8(9 Content9.0))";
        String result = TextParser.getReadyResult(testInput);
        StringBuilder builder = new StringBuilder();
        builder.append("1 Content1.0 Content1.1\n")
                .append("   2\n")
                .append("      4\n")
                .append("      5\n")
                .append("      6\n")
                .append("         7\n")
                .append("      10\n")
                .append("   3\n")
                .append("8\n")
                .append("   9 Content9.0\n");
        assertEquals(builder.toString(), result);
    }

    @Test
    public void whenEmptyNode() {
        String testInput = "(1 Content1.0 Content1.1 (2 (4 5 6 ())(10))(3))(8(9 Content9.0))";
        String result = TextParser.getReadyResult(testInput);
        assertEquals(null, result);
    }

    @Test
    public void whenNodeHaveNotIntKey() {
        String testInput = "(1 Content1.0 Content1.1 (2 (4 5 6 (d))(10))(3))(8(9 Content9.0))";
        String result = TextParser.getReadyResult(testInput);
        assertEquals(null, result);
    }
}