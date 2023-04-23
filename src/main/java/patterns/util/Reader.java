package patterns.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {
  private final static String RESOURCE_PATH = "resources/";

  public List<String> read(String filename) throws IOException {
    List<String> lines = new ArrayList<>();
    File file = new File(RESOURCE_PATH + filename);
    if (!file.exists()) {
      lines.add("File not found: " + filename);
      return lines;
    }
    BufferedReader reader = new BufferedReader(new FileReader(file));
    String line;
    while ((line = reader.readLine()) != null) {
      lines.add(line);
    }
    reader.close();
    return lines;
  }

}
