import com.mype.kata.ocrdigits.OCRReader;
import com.mype.kata.ocrdigits.Row;
import org.junit.After;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * @author Vitaliy Gerya
 */
public class OCRReaderTest {

    private OCRReader reader;
    private final String allDigits =
            "    _  _     _  _  _  _  _  _ \n" +
            "  | _| _||_||_ |_   ||_||_|| |\n" +
            "  ||_  _|  | _||_|  ||_| _||_|";


    @After
    public void tearDown() throws Exception {
        reader = null;
    }

    @Test
    public void createOCRReader() throws Exception {
        reader = new OCRReader(new InputStreamReader(new ByteArrayInputStream(allDigits.getBytes())));

        assertThat(reader).isNotNull();
    }

    @Test
    public void readOCR() throws Exception {
        final String input = "    _  _     _  _  _  _  _ \n" +
                             "  | _| _||_||_ |_   ||_||_|\n" +
                             "  ||_  _|  | _||_|  ||_| _|\n" +
                             "                           \n";
        reader = new OCRReader(new InputStreamReader(new ByteArrayInputStream(input.getBytes())));
        Row entry = reader.read();
        assertThat(entry.toNumber()).isEqualTo(123456789l);
    }

    @Test(expected = IOException.class)
    public void shouldCheckRow1Length() throws Exception {
        final String input = "   _  _     _  _  _  _  _ \n" +
                             "  | _| _||_||_ |_   ||_||_|\n" +
                             "  ||_  _|  | _||_|  ||_| _|\n" +
                             "                           \n";
        reader = new OCRReader(new InputStreamReader(new ByteArrayInputStream(input.getBytes())));
        Row entry = reader.read();
    }

    @Test(expected = IOException.class)
    public void shouldCheckRow2Length() throws Exception {
        final String input = "    _  _     _  _  _  _  _ \n" +
                " | _| _||_||_ |_   ||_||_|\n" +
                "  ||_  _|  | _||_|  ||_| _|\n" +
                "                           \n";
        reader = new OCRReader(new InputStreamReader(new ByteArrayInputStream(input.getBytes())));
        reader.read();
    }

    @Test(expected = IOException.class)
    public void shouldCheckRow3Length() throws Exception {
        final String input =    "    _  _     _  _  _  _  _ \n" +
                                "  | _| _||_||_ |_   ||_||_|\n" +
                                " ||_  _|  | _||_|  ||_| _|\n" +
                                "                           \n";
        reader = new OCRReader(new InputStreamReader(new ByteArrayInputStream(input.getBytes())));
        reader.read();
    }

    @Test(expected = IOException.class)
    public void shouldCheckLastRowIsEmpty() throws Exception {
        final String input =    "    _  _     _  _  _  _  _ \n" +
                                "  | _| _||_||_ |_   ||_||_|\n" +
                                "  ||_  _|  | _||_|  ||_| _|\n" +
                                "               _           \n";
        reader = new OCRReader(new InputStreamReader(new ByteArrayInputStream(input.getBytes())));
        reader.read();
    }

    @Test
    public void eachAccountNumberIs9Digit() throws Exception {
        final String input =    "    _  _     _  _  _  _  _ \n" +
                                "  | _| _||_||_ |_   ||_||_|\n" +
                                "  ||_  _|  | _||_|  ||_| _|\n" +
                                "                           \n";
        reader = new OCRReader(new InputStreamReader(new ByteArrayInputStream(input.getBytes())));
        Row entry = reader.read();
        assertThat(entry.length()).isEqualTo(9);
    }

    @Test
    public void shouldReadTwoOrMoreAccounts() throws Exception {
        final String input =    "    _  _     _  _  _  _  _ \n" +
                                "  | _| _||_||_ |_   ||_||_|\n" +
                                "  ||_  _|  | _||_|  ||_| _|\n" +
                                "                           \n" +
                                "    _  _     _  _  _  _  _ \n" +
                                "  | _| _||_||_ |_   ||_||_|\n" +
                                "  ||_  _|  | _||_|  ||_| _|\n" +
                                "                           \n";
        reader = new OCRReader(new InputStreamReader(new ByteArrayInputStream(input.getBytes())));
        Row row = reader.read();
        assertThat(row).isNotNull();
        row = reader.read();
        assertThat(row).isNotNull();
        row = reader.read();
        assertThat(row).isNull();
    }

    @Test
    public void acceptUserStory1() throws Exception {
        final String input =
                " _  _  _  _  _  _  _  _  _ \n" +
                "| || || || || || || || || |\n" +
                "|_||_||_||_||_||_||_||_||_|\n" +
                "\n" +
                "                           \n" +
                "  |  |  |  |  |  |  |  |  |\n" +
                "  |  |  |  |  |  |  |  |  |\n" +
                "\n" +
                " _  _  _  _  _  _  _  _  _ \n" +
                " _| _| _| _| _| _| _| _| _|\n" +
                "|_ |_ |_ |_ |_ |_ |_ |_ |_ \n" +
                "\n" +
                " _  _  _  _  _  _  _  _  _ \n" +
                " _| _| _| _| _| _| _| _| _|\n" +
                " _| _| _| _| _| _| _| _| _|\n" +
                "\n" +
                "                           \n" +
                "|_||_||_||_||_||_||_||_||_|\n" +
                "  |  |  |  |  |  |  |  |  |\n" +
                "\n" +
                " _  _  _  _  _  _  _  _  _ \n" +
                "|_ |_ |_ |_ |_ |_ |_ |_ |_ \n" +
                " _| _| _| _| _| _| _| _| _|\n" +
                "\n" +
                " _  _  _  _  _  _  _  _  _ \n" +
                "|_ |_ |_ |_ |_ |_ |_ |_ |_ \n" +
                "|_||_||_||_||_||_||_||_||_|\n" +
                "\n" +
                " _  _  _  _  _  _  _  _  _ \n" +
                "  |  |  |  |  |  |  |  |  |\n" +
                "  |  |  |  |  |  |  |  |  |\n" +
                "\n" +
                " _  _  _  _  _  _  _  _  _ \n" +
                "|_||_||_||_||_||_||_||_||_|\n" +
                "|_||_||_||_||_||_||_||_||_|\n" +
                "\n" +
                " _  _  _  _  _  _  _  _  _ \n" +
                "|_||_||_||_||_||_||_||_||_|\n" +
                " _| _| _| _| _| _| _| _| _|\n" +
                "\n" +
                "    _  _     _  _  _  _  _ \n" +
                "  | _| _||_||_ |_   ||_||_|\n" +
                "  ||_  _|  | _||_|  ||_| _|\n" +
                "\n";
        reader = new OCRReader(new InputStreamReader(new ByteArrayInputStream(input.getBytes())));
        Row entry = reader.read();
        assertThat(entry.toString()).isEqualTo("000000000");
        entry = reader.read();
        assertThat(entry.toString()).isEqualTo("111111111");
        entry = reader.read();
        assertThat(entry.toString()).isEqualTo("222222222");
        entry = reader.read();
        assertThat(entry.toString()).isEqualTo("333333333");
        entry = reader.read();
        assertThat(entry.toString()).isEqualTo("444444444");
        entry = reader.read();
        assertThat(entry.toString()).isEqualTo("555555555");
        entry = reader.read();
        assertThat(entry.toString()).isEqualTo("666666666");
        entry = reader.read();
        assertThat(entry.toString()).isEqualTo("777777777");
        entry = reader.read();
        assertThat(entry.toString()).isEqualTo("888888888");
        entry = reader.read();
        assertThat(entry.toString()).isEqualTo("999999999");
        entry = reader.read();
        assertThat(entry.toString()).isEqualTo("123456789");
    }



}
