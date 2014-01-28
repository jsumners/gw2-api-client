package com.jrfom.gw2;

import java.io.IOException;

import com.jrfom.gw2.api.model.Build;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class BuildTest extends BaseApiClientTestClass {
  private static final Logger log = LoggerFactory.getLogger(BuildTest.class);

  @Test
  public void testBuild() throws IOException {
    log.info("Running ApiClient.getBuild() test");
    String expectedResponse = this.loadExpectedResponse("/json/Build.json");
    this.setupMockServerSuccess("build.json", expectedResponse);

    Build build = this.apiClient.getBuild();
    assertTrue(build.getBuildId() == 22120);
  }
}