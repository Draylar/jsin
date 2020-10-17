package draylar.jsin.api;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Represents a test suite within a directory.
 *
 * <p>Each test needs a separate directory to avoid file naming conflicts between different tests.
 * Test directories can be found inside /test_output while testss are live, but are deleted after tests run.
 */
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
    public void afterTests() {
        deleteDirectory(OUTPUT_DIRECTORY.toFile());
    }

    /**
     * Deletes the specified directory, including any files or directories inside it.
     *
     * @param file  directory to delete files from
     * @throws      UnsupportedOperationException if the given {@link File} is not a directory
     */
    private void deleteDirectory(File file) {
        if(file == null || !file.isDirectory()) {
            throw new UnsupportedOperationException(String.format("%s is not a directory!", file.toString()));
        } else {
            for (File f : file.listFiles()) {
                if (!f.isDirectory()) {
                    f.delete();
                } else {
                    deleteDirectory(f);
                }
            }
        }

        file.delete();
    }
}
