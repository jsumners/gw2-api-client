package com.jrfom.gw2.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class GuildTest {
  private static final Logger log = LoggerFactory.getLogger(GuildTest.class);

  @Test
  public void testDeserialization() throws IOException {
    log.info("Running Guild deserialization test");
    ObjectMapper mapper = new ObjectMapper();
    Guild guild = mapper.readValue(
      this.getClass().getResourceAsStream("/json/GuildDetails.json"),
      Guild.class
    );

    assertTrue(guild.getId().equals("1717E59C-A998-4F11-B33E-6DBF992282B2"));
    assertTrue(guild.getName().equals("Illustrious Chromatic Enigmas"));
    assertTrue(guild.getTag().equals("ICE"));
    assertTrue(guild.getEmblem().getBackgroundId() == 23);
    assertTrue(guild.getEmblem().getForegroundId() == 43);
    assertTrue(guild.getEmblem().getFlags().size() == 0);
    assertTrue(guild.getEmblem().getBackgroundColorId() == 137);
    assertTrue(guild.getEmblem().getForegroundPrimaryColorId() == 443);
    assertTrue(guild.getEmblem().getForegroundSecondaryColorId() == 9);
  }

  @Test
  public void testSerialization() throws IOException {
    log.info("Running Guild serialization test");
    ObjectMapper mapper = new ObjectMapper();
    Guild guild = mapper.readValue(
      this.getClass().getResourceAsStream("/json/GuildDetails.json"),
      Guild.class
    );

    ObjectWriter writer = mapper.writer();
    String json = writer.writeValueAsString(guild);

    Guild guild2 = mapper.readValue(json, Guild.class);
    assertTrue(guild.getId().equals(guild2.getId()));
    assertTrue(guild.getEmblem().getBackgroundId() == guild2.getEmblem().getBackgroundId());
  }
}