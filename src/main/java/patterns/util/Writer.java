package patterns.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Writer {
  private static final String RESOURCE_PATH = "resources/";
  private final String filename;
  private static final String TYPE = ".txt";

  public Writer(String filename){
    this.filename = filename;
  }
  
  public void write(String line) throws IOException {
    FileWriter writer = new FileWriter(RESOURCE_PATH + filename + TYPE , true);
    writer.write(line);
    writer.close();
  }

  public void updateById(String id, String newLine) throws IOException {
    File tempFile = new File(RESOURCE_PATH + "temp_" + filename + TYPE);
    File inputFile = new File(RESOURCE_PATH + filename + TYPE);
    BufferedReader reader = new BufferedReader(new FileReader(inputFile));
    List<String> records = new ArrayList<>();
    boolean overwrite = isOverwriteFile(reader, records, id, newLine);
    reader.close();
    if (!overwrite) {
      write(newLine);
    } else {
      overwrite(records, tempFile);
      updateFiles(inputFile, tempFile);
    }
  }

  private void overwrite(List<String> records, File tempFile) throws IOException {
    FileWriter writer = new FileWriter(tempFile , true);
    for (String record :records) {
      writer.write(record);
    }
    writer.close();
  }

  private void updateFiles(File inputFile, File tempFile) {
    if (inputFile.exists() & !inputFile.delete()) {
      throw new IllegalStateException("Failed to delete old file " + filename);
    }
    if (!tempFile.renameTo(inputFile)) {
      throw new IllegalStateException("Failed to rename temporary file to " + filename);
    }
  }

  private boolean isOverwriteFile(BufferedReader reader, List<String> lines, String id, String newLine)
      throws IOException {
    boolean foundLine = false;
    String line;
    while ((line = reader.readLine()) != null) {
      if (!foundLine) {
        String[] parts = line.split(";");
        if (parts[0].trim().compareToIgnoreCase(id) == 0) {
          foundLine = true;
          lines.add(newLine);
        } else {
          lines.add(line);
        }
      } else {
        lines.add(line);
      }
    }
    return foundLine;
  }

}

