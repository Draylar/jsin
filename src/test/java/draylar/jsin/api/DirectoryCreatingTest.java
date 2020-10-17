package draylar.jsin.api;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DirectoryCreatingTest {

    private static final Logger LOGGER = Logger.getLogger("Jsin Test");
    private static final List<String> usedDirectories = new ArrayList<>();
    protected static final JSIN jsin = new JSIN();
    protected final Path OUTPUT_DIRECTORY;
    protected final String directoryName;

    public DirectoryCreatingTest(String directoryName) {
        if(usedDirectories.contains(directoryName)) {
            throw new RuntimeException("More than 1 test class provided the same output directory name!");
        } else {
            usedDirectories.add(directoryName);
            OUTPUT_DIRECTORY = Paths.get(String.format("%s\\test_output\\%s", new File("").getAbsolutePath(), directoryName));
            this.directoryName = directoryName;
        }
    }

    @BeforeAll
    public void beforeTests() {
        if(!OUTPUT_DIRECTORY.toFile().exists()) {
            LOGGER.info(String.format("Created test directory for %s.", directoryName));
            OUTPUT_DIRECTORY.toFile().mkdirs();
        }
    }

    @AfterAll
    public void afterTests() throws IOException {
        Files.delete(OUTPUT_DIRECTORY);
    }
}
