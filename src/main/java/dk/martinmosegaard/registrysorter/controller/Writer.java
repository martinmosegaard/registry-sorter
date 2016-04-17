package dk.martinmosegaard.registrysorter.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import dk.martinmosegaard.registrysorter.model.RegistryEntry;

class Writer {

  String write(RegistryEntry rootEntry) {
    StringBuffer buffer = new StringBuffer();
    write(buffer, rootEntry);
    return buffer.toString();
  }

  void write(StringBuffer buffer, RegistryEntry entry) {
    if (entry.getLine() != "") {
      buffer.append(entry.getLine());
      buffer.append(System.lineSeparator());
    }
    for (RegistryEntry child : entry.getChildren()) {
      write(buffer, child);
    }
  }
/*
  void write(String outputPath, RegistryEntry rootEntry) throws IOException {
    try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(
        Paths.get(outputPath), StandardCharsets.ISO_8859_1))) {
      write(writer, rootEntry);
    }
  }

  void write(PrintWriter writer, RegistryEntry entry) {
    if (entry.getLine() != "") {
      writer.println(entry.getLine());
    }
    for (RegistryEntry child : entry.getChildren()) {
      write(writer, child);
    }
  }
*/
}
