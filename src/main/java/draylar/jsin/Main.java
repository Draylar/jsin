package draylar.jsin;

import draylar.jsin.api.JSIN;
import draylar.jsin.api.JSINImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class Main {

    public static final Logger LOGGER = Logger.getLogger("Jsin");
    private static final String TO = "to";
    private static final String FROM = "from";
    private static final List<String> SUPPORTED_IMAGE_FORMATS = Arrays.asList(ImageIO.getReaderFormatNames());
    private static final JSIN jsin = new JSIN();

    /**
     * Arguments:
     * <ul>
     *     <li>to - converts a .jsin file to a different image type - jsin to [png/?] [file in]
     *     <li>from - converts an image file to a .jsin file - jsin from [file in]
     *
     * @param args main command line arguments
     */
    public static void main(String[] args) throws IOException {
        String first = args[0];

        switch (first) {
            case TO -> toOther(args, true);
            case FROM -> toJsin(args, true);
            default -> LOGGER.info(
                    """
                            Offers several commands for using and manipulating the jsin image format.
                                                        
                            --------------------------------------------------------------
                                                        
                            Help:
                                jsin
                                                        
                            Convert a .jsin file to another image format:
                                Syntax  - jsin to [format] [input file]     Converts a jsin image to another format
                                Example - jsin to png apple.jsin            Converts apple.jsin to png format and saves it as apple.png
                                Notes   - input file can be relative or absolute.
                                                        
                            Convert an image to .jsin format:
                                Syntax  - jsin from [input file]            Converts a non-jsin image file to jsin
                                Example - jsin from apple.png               Converts apple.png to jsin format and saves it as apple.jsin
                                Notes   - input file can be relative or absolute.
                               
                            --------------------------------------------------------------
                            Source: https://github.com/Draylar/jsin
                            Issues: https://github.com/Draylar/jsin/issues
                            MIT license.
                            """);
        }
    }

    public static boolean toOther(String[] args, boolean logWarnings) throws IOException {
        if (args.length != 3) {
            if(logWarnings) {
                LOGGER.warning(
                        """
                                2 arguments expected.
                                    Usage: jsin to [format] [input file]
                                    Notes: input file can be relative or absolute.
                                """);
            }

            return false;
        }

        String format = args[1].replace(".", "");
        String input = args[2];

        // Verify format extension
        if (!SUPPORTED_IMAGE_FORMATS.contains(format)) {
            if(logWarnings) {
                LOGGER.warning(String.format("%s file format not supported.\n   Supported file types: %s", format, SUPPORTED_IMAGE_FORMATS.toArray().toString()));
            }

            return false;
        }

        // Tack .jsin ending onto input if it was not specified
        if(!input.endsWith(".jsin")) {
            input = input + ".jsin";
        }

        // Verify input
        Path attemptPath = Paths.get(input);
        File attemptFile = attemptPath.toFile();
        if (!attemptFile.exists() || !attemptFile.isFile()) {
            if(logWarnings) {
                LOGGER.warning(String.format("The file %s does not exist.", attemptPath.toString()));
            }

            return false;
        }

        // .png -> .jsin
        JSINImage image = jsin.from(Files.readString(attemptPath));
        ImageIO.write(image.toBuffered(), format, new File(attemptFile.toString().replace(".jsin", String.format(".%s", format))));
        return true;
    }

    public static boolean toJsin(String[] args, boolean logWarnings) throws IOException {
        if (args.length != 2) {
            if(logWarnings) {
                LOGGER.warning(
                        """
                                1 argument expected.
                                    Usage: jsin from [input file]
                                    Notes: input file can be relative or absolute.
                                """);
            }

            return false;
        }

        String input = args[1];

        // Verify input
        Path attemptPath = Paths.get(input);
        File attemptFile = attemptPath.toFile();
        if (!attemptFile.exists() || !attemptFile.isFile()) {
            if(logWarnings) {
                LOGGER.warning(String.format("The file %s does not exist.", attemptPath.toString()));
            }

            return false;
        }

        // .jsin -> .png
        BufferedImage in = ImageIO.read(attemptFile);
        JSINImage image = new JSINImage(in);
        jsin.save(image, attemptFile.getParentFile().toPath(), attemptFile.getName().substring(0, attemptFile.getName().lastIndexOf(".")));
        return true;
    }
}
