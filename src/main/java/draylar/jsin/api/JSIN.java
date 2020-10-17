package draylar.jsin.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class JSIN {

    private final Gson gson;

    public JSIN() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Saves the given {@link JSINImage} at the given location with the given file name.
     *
     * @param image      image to save
     * @param directory  location to save file in
     * @param fileName   name of the file to write the image to
     */
    public void save(JSINImage image, Path directory, String fileName) {
        File pFile = directory.toFile();
        File outputFile = new File(directory + "\\" + fileName + ".jsin");

        // Ensure file location is valid
        if(!pFile.isDirectory()) {
            throw new InvalidPathException(directory.toString(), "Output directory does not exist!");
        }

        // Delete output file if it already exists
        if(outputFile.exists()) {
            try {
                Files.delete(outputFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Save output file
        try {
            String json = gson.toJson(image);
            FileWriter writer = new FileWriter(outputFile);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSINImage from(String json) {
        return gson.fromJson(json, JSINImage.class);
    }
}
