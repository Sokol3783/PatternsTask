package patterns.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
  private static final String RESOURCE_PATH = "resources/";
  private final String filename;
  private static final String TYPE = ".txt";

  public Writer(String filename){
    this.filename = filename;
  }
  
  public void write(String line) throws IOException {
    FileWriter writer = new FileWriter(RESOURCE_PATH + filename + TYPE , true);
    writer.write(line + "\n");
    writer.close();
  }

  public void updateById(String id, String newLine) throws IOException {
    File inputFile = new File(RESOURCE_PATH + filename + TYPE);
    File tempFile = new File(RESOURCE_PATH + "temp_" + filename + TYPE);
    BufferedReader reader = new BufferedReader(new FileReader(inputFile));
    FileWriter writer = new FileWriter(tempFile);
    boolean found = foundLine(reader, writer, id, newLine);
    reader.close();
    writer.close();
    if (!found) {
      write(newLine);
    } else {
      updateFiles(inputFile, tempFile);
    }
  }

  private void updateFiles(File inputFile, File tempFile) {
    if (!inputFile.delete()) {
      throw new IllegalStateException("Failed to delete old file " + filename);
    }
    if (!tempFile.renameTo(inputFile)) {
      throw new IllegalStateException("Failed to rename temporary file to " + filename);
    }
  }

  private boolean foundLine(BufferedReader reader, FileWriter writer, String id, String newLine)
      throws IOException {
    String line;
    while ((line = reader.readLine()) != null) {
      String[] parts = line.split(";");
      if (parts[0].equals(id)) {
        writer.write(newLine + "\n");
        return true;
      } else {
        writer.write(line + "\n");
      }
    }
    return false;
  }

  public String getById(String id) throws IOException {
    File file = new File(RESOURCE_PATH + filename + TYPE);
    BufferedReader reader = new BufferedReader(new FileReader(file));
    String line;
    while ((line = reader.readLine()) != null) {
      String[] parts = line.split(";");
      if (parts[0].equals(id)) {
        reader.close();
        return line;
      }
    }
    reader.close();
    return null;
  }
}

