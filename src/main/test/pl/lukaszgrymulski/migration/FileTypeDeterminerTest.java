package pl.lukaszgrymulski.migration;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FileTypeDeterminerTest {

    @Test
    void determineFileTypeShouldReturnXML() {
        File file = new File("C:\\test\\test.xml");
        assertEquals("xml", FileTypeDeterminer.determineFileType(file));
    }

    @Test
    void determineFileWithUppercaseTypeShouldReturnXML() {
        File file = new File("C:\\test\\test.XML");
        assertEquals("XML", FileTypeDeterminer.determineFileType(file));
    }
}