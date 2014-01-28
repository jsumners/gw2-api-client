package com.jrfom.gw2;

import java.io.IOException;

import com.jrfom.gw2.api.model.Files;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class FilesTest extends BaseApiClientTestClass {
  private static final Logger log = LoggerFactory.getLogger(FilesTest.class);

  @Test
  public void testGetFiles() throws IOException {
    log.info("Running ApiClient.getFiles() test");
    String expectedResponse = this.loadExpectedResponse("/json/Files.json");
    this.setupMockServerSuccess("files.json", expectedResponse);

    Files files = this.apiClient.getFiles();
    assertTrue(files.size() == 13);
    assertTrue(files.get("map_complete").getFileId() == 528724);
  }
}