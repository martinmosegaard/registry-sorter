package dk.martinmosegaard.registrysorter;

import dk.martinmosegaard.registrysorter.model.RegistryEntry;

public class Main {

  public static void main(String[] args) throws Exception {
    String filePath = args[0];
    Reader reader = new Reader();
    RegistryEntry rootEntry = reader.read(filePath);
    Sorter sorter = new Sorter();
    sorter.sort(rootEntry);
    Writer writer = new Writer();
    writer.write(filePath + ".sorted", rootEntry);
  }

}
