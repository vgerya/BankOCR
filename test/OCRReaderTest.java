import com.mype.kata.ocrdigits.OCRReader;
import com.mype.kata.ocrdigits.Row;
import org.junit.After;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * @author Vitaliy Gerya
 */
public class OCRReaderTest {

    private OCRReader reader;
    private final String allDigits =
            "    _  _     _  _  _  _  _  _\n" +
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


}
