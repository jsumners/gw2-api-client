package com.jrfom.gw2;

import java.io.IOException;

import com.google.common.base.Optional;
import com.jrfom.gw2.api.model.Guild;
import com.jrfom.gw2.api.model.GwApiError;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class GuildDetailsTest extends BaseApiClientTestClass {
  private static final Logger log = LoggerFactory.getLogger(GuildDetailsTest.class);

  @Test
  public void testGuildDetailsWithIdSuccess() throws IOException {
    log.info("Running ApiClient.getGuildDetailsForGuildId(guildId) test");
    String expectedResponse = this.loadExpectedResponse("/json/GuildDetails.json");
    this.setupMockServerSuccess(
      "guild_details.json?guild_id=1717E59C-A998-4F11-B33E-6DBF992282B2",
      expectedResponse
    );

    Optional<Guild> result =
      this.apiClient.getGuildDetailsForGuildId("1717E59C-A998-4F11-B33E-6DBF992282B2");
    assertTrue(result.isPresent());
    Guild guild = result.get();
    assertTrue(guild.getId().equals("1717E59C-A998-4F11-B33E-6DBF992282B2"));
    assertTrue(guild.getTag().equals("ICE"));
    assertTrue(guild.getName().equals("Illustrious Chromatic Enigmas"));
  }

  @Test
  public void testGuildDetailsWithNameSuccess() throws IOException {
    log.info("Running ApiClient.getGuildDetailsForGuildName(guildName) test");
    String expectedResponse = this.loadExpectedResponse("/json/GuildDetails.json");
    this.setupMockServerSuccess(
      "guild_details.json?guild_name=Illustrious%20Chromatic%20Enigmas",
      expectedResponse
    );

    Optional<Guild> result =
      this.apiClient.getGuildDetailsForGuildName("Illustrious Chromatic Enigmas");
    assertTrue(result.isPresent());
    Guild guild = result.get();
    assertTrue(guild.getId().equals("1717E59C-A998-4F11-B33E-6DBF992282B2"));
    assertTrue(guild.getTag().equals("ICE"));
    assertTrue(guild.getName().equals("Illustrious Chromatic Enigmas"));
  }

  @Test
  public void testGuildDetailsWithIdFail() throws IOException {
    log.info("Running ApiClient.getGuildDetailsForGuildId(guildId) fail test");
    String expectedResponse = this.loadExpectedResponse("/json/errors/GuildIdInvalid.json");
    this.setupMockServerFail(
      "guild_details.json?guild_id=1717E59C-A998-4F11-B33E-6DBF992282B2",
      expectedResponse
    );

    try {
      Optional<Guild> result =
        this.apiClient.getGuildDetailsForGuildId("1717E59C-A998-4F11-B33E-6DBF992282B2");
    } catch (GwApiError e) {
      assertTrue(e.getError() == 10);
      assertTrue(e.getProduct() == 0);
      assertTrue(e.getModule() == 3);
      assertTrue(e.getLine() == 228);
      assertTrue(e.getText().equals("invalid guild_id"));
    }
  }

  @Test
  public void testGuildDetailsWithNameFail() throws IOException {
    log.info("Running ApiClient.getGuildDetailsForGuildName(guildName) fail test");
    String expectedResponse = this.loadExpectedResponse("/json/errors/GuildNameInvalid.json");
    this.setupMockServerFail(
      "guild_details.json?guild_name=Illustrious%20Chromatic%20Enigmas",
      expectedResponse
    );

    try {
      Optional<Guild> result =
        this.apiClient.getGuildDetailsForGuildName("Illustrious Chromatic Enigmas");
    } catch (GwApiError e) {
      assertTrue(e.getError() == 3019);
      assertTrue(e.getProduct() == 1008);
      assertTrue(e.getModule() == 1);
      assertTrue(e.getLine() == 561);
      assertNull(e.getText());
    }
  }
}