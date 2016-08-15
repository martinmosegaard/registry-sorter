package dk.martinmosegaard.registrysorter.controller

/**
 * Handles quotes in lines.
 */
class QuoteHandler {

  static String removeQuotes(String line) {
    int index = 0
    char ch = line.charAt(index)
    while (ch == "'" || ch == '"') {
      ++index
      ch = line.charAt(index)
    }

    line[index .. -1]
  }

}
