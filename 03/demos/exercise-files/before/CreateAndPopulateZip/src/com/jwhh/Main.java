package com.jwhh;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String[] data = {
                "Line 1",
                "Line 2 2",
                "Line 3 3 3",
                "Line 4 4 4 4",
                "Line 5 5 5 5 5"
        };

        try (FileSystem zipFs = openZip(Paths.get("myData.zip"))) {
            copyToZip(zipFs);
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }

    }

    private static FileSystem openZip(Path zipPath) throws IOException, URISyntaxException {
        Map<String, String> providerProps = new HashMap<>();
//        create is name, true is value
        providerProps.put("create", "true");

//        take input path you got change it to a URI and get path
        URI zipUri = new URI("jar:file", zipPath.toUri().getPath(), null);
//        create file system
        FileSystem zipFs = FileSystems.newFileSystem(zipUri, providerProps);

        return zipFs;
    }

    private static void copyToZip(FileSystem zipFs) throws IOException {
//        Paths is a shortcut to getting the file
        Path sourceFile = Paths.get("/Users/emily/Downloads/java-fundamentals-core-platform/03/demos/exercise-files/before/CreateAndPopulateZip/file1.txt");
//        Path sourceFile = FileSystems.getDefault().getPath("/Users/emily/Downloads/java-fundamentals-core-platform/03/demos/exercise-files/before/CreateAndPopulateZip/file1.txt");
        Path destFile = zipFs.getPath("/file1Copied.txt");

        Files.copy(sourceFile, destFile, StandardCopyOption.REPLACE_EXISTING);
    }

    private static void writeToFileInZip1(FileSystem zipFs, String[] data) throws IOException {

    }

    private static void writeToFileInZip2(FileSystem zipFs, String[] data) throws IOException {

    }

}