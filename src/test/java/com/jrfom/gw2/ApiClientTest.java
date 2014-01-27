package com.jrfom.gw2;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.common.base.Optional;
import com.jrfom.gw2.api.model.Build;
import com.jrfom.gw2.api.model.Guild;
import com.jrfom.gw2.api.model.GwApiError;
import com.jrfom.gw2.api.model.colors.ColorsList;
import com.jrfom.gw2.api.model.events.EventDetails;
import com.jrfom.gw2.api.model.events.EventNamesList;
import com.jrfom.gw2.api.model.events.WorldEventsStatusList;
import com.jrfom.gw2.api.model.geography.Continents;
import com.jrfom.gw2.api.model.geography.Map;
import com.jrfom.gw2.api.model.geography.MapsList;
import com.jrfom.gw2.api.model.items.Item;
import com.jrfom.gw2.api.model.items.ItemIdList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.ResponseCreator;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
  TestAppContext.class
})
public class ApiClientTest {
  private static final Logger log = LoggerFactory.getLogger(ApiClientTest.class);

  @Autowired
  private ApiClient apiClient;

  private MockRestServiceServer mockServer;

  @Before
  public void setup() {
    this.mockServer = MockRestServiceServer.createServer(this.apiClient);
  }

  /*~~~~ Begin tests for Build ~~~~*/
  @Test
  public void testBuild() throws IOException {
    log.info("Running ApiClient.getBuild() test");
    String expectedResponse = this.loadExpectedResponse("/json/Build.json");
    this.setupMockServerSuccess("build.json", expectedResponse);

    Build build = this.apiClient.getBuild();
    assertTrue(build.getBuildId() == 22120);
  }

  /*~~~~ Begin tests for Colors ~~~~*/
  @Test
  public void testColors() throws IOException {
    log.info("Running ApiClient.getColors() test");
    String expectedResponse = this.loadExpectedResponse("/json/ColorsList.json");
    this.setupMockServerSuccess("colors.json", expectedResponse);

    ColorsList list = this.apiClient.getColors();
    assertTrue(list.size() == 5);
    assertTrue(list.get(0).getColorId() == 668);
    assertTrue(list.get(0).getName().equals("Pink Ice"));


  }

  @Test
  public void testColorsWithParam() throws IOException {
    log.info("Running ApiClient.getColors(lang) test");
    String expectedResponse = this.loadExpectedResponse("/json/ColorsList.json");
    this.setupMockServerSuccess("colors.json?lang=en", expectedResponse);

    ColorsList list = this.apiClient.getColors("en");
    assertTrue(list.size() == 5);
    assertTrue(list.get(0).getColorId() == 668);
    assertTrue(list.get(0).getName().equals("Pink Ice"));
  }

  /*~~~~ Begin tests for Continents ~~~~*/
  @Test
  public void testContinents() throws IOException {
    log.info("Running ApiClient.getContinents() test");
    String expectedResponse = this.loadExpectedResponse("/json/Continents.json");
    this.setupMockServerSuccess("continents.json", expectedResponse);

    Continents continents = this.apiClient.getContinents();
    assertTrue(continents.size() == 2);
    assertTrue(continents.getContinentWithName("Tyria").isPresent());
  }

  /*~~~~ Begin tests for EventDetails ~~~~*/
  @Test
  public void testEventDetails() throws IOException {
    log.info("Running ApiClient.getEventDetails() test");
    String expectedResponse = this.loadExpectedResponse("/json/Events.json");
    this.setupMockServerSuccess("event_details.json", expectedResponse);

    Optional<EventDetails> result = this.apiClient.getEventDetails();
    assertTrue(result.isPresent());

    EventDetails eventDetails = result.get();
    assertTrue(eventDetails.size() == 3);
    assertTrue(
      eventDetails.get("EED8A79F-B374-4AE6-BA6F-B7B98D9D7142")
        .getName().equals("Defeat the renegade charr.")
    );
  }

  @Test
  public void testEventDetailsWithId() throws IOException {
    log.info("Running ApiClient.getEventDetailsForEventId(eventId) test");
    String expectedResponse = this.loadExpectedResponse("/json/Events.json");
    String eventId = "EED8A79F-B374-4AE6-BA6F-B7B98D9D7142";
    this.setupMockServerSuccess(
      "event_details.json?event_id=" + eventId,
      expectedResponse
    );

    Optional<EventDetails> result =
      this.apiClient.getEventDetailsForEventId(eventId);
    assertTrue(result.isPresent());

    EventDetails eventDetails = result.get();
    assertTrue(eventDetails.size() == 3);
    assertTrue(
      eventDetails.get(eventId)
        .getName().equals("Defeat the renegade charr.")
    );
  }

  @Test
  public void testEventDetailsWithIdAndLang() throws IOException {
    log.info("Running ApiClient.getEventDetailsForEventId(eventId) test");
    String expectedResponse = this.loadExpectedResponse("/json/Events.json");
    String eventId = "EED8A79F-B374-4AE6-BA6F-B7B98D9D7142";
    this.setupMockServerSuccess(
      "event_details.json?event_id=" + eventId + "&lang=en",
      expectedResponse
    );

    Optional<EventDetails> result =
      this.apiClient.getEventDetailsForEventIdInLang(eventId, "en");
    assertTrue(result.isPresent());

    EventDetails eventDetails = result.get();
    assertTrue(eventDetails.size() == 3);
    assertTrue(
      eventDetails.get(eventId)
        .getName().equals("Defeat the renegade charr.")
    );
  }

  @Test
  public void testEventDetailsWithBadId() throws IOException {
    log.info("Running ApiClient.getEventDetailsForEventId(eventId) failure test");
    String expectedResponse = this.loadExpectedResponse("/json/errors/EventDetails.json");
    this.setupMockServerFail("event_details.json?event_id=foo", expectedResponse);

    try {
      Optional<EventDetails> eventDetails = this.apiClient.getEventDetailsForEventId("foo");
    } catch (GwApiError error) {
      assertTrue(error.getError() == 10);
      assertTrue(error.getProduct() == 0);
      assertTrue(error.getModule() == 5);
      assertTrue(error.getLine() == 684);
      assertTrue(error.getText().equals("invalid event_id"));
    }
  }

  /*~~~~ Begin tests for EventNames ~~~~*/
  @Test
  public void testEventNames() throws IOException {
    log.info("Running ApiClient.getEventNames() test");
    String expectedResponse = this.loadExpectedResponse("/json/EventNames.json");
    this.setupMockServerSuccess("event_names.json", expectedResponse);

    EventNamesList list = this.apiClient.getEventNames();
    assertTrue(list.size() == 5);
    assertTrue(list.get(0).getId().equals("AD31D52F-C650-473D-8637-5792868828D7"));
  }

  @Test
  public void testEventNamesForLang() throws IOException {
    log.info("Running ApiClient.getEventNamesForLang(lang) test");
    String expectedResponse = this.loadExpectedResponse("/json/EventNames.json");
    this.setupMockServerSuccess("event_names.json?lang=en", expectedResponse);

    EventNamesList list = this.apiClient.getEventNamesForLang("en");
    assertTrue(list.size() == 5);
    assertTrue(list.get(0).getId().equals("AD31D52F-C650-473D-8637-5792868828D7"));
  }

  /*~~~~ Begin tests for Event Statuses ~~~~*/
  @Test
  public void testEventStatuses() throws IOException {
    log.info("Running ApiClient.getEventStatuses() test");
    String expectedResponse = this.loadExpectedResponse("/json/WorldEventsStatus.json");
    this.setupMockServerSuccess("events.json", expectedResponse);

    WorldEventsStatusList list = this.apiClient.getEventStatuses();
    assertTrue(list.size() == 5);
    assertTrue(list.get(0).getWorldId() == 1001);
    assertTrue(list.get(0).getMapId() == 39);
    assertTrue(list.get(0).getState().equals("Active"));
  }

  @Test
  public void testEventStatusesForWorld() throws IOException {
    log.info("Running ApiClient.getEventStatusesForWorld(worldId) test");
    String expectedResponse = this.loadExpectedResponse("/json/event-statuses/World.json");
    this.setupMockServerSuccess("events.json?world_id=1011", expectedResponse);

    WorldEventsStatusList list = this.apiClient.getEventStatusesForWorld(1011);
    assertTrue(list.size() == 1);
    assertTrue(list.get(0).getWorldId() == 1011);
    assertTrue(list.get(0).getMapId() == 40);
    assertTrue(list.get(0).getState().equals("Success"));
  }

  @Test
  public void testEventStatusesForMap() throws IOException {
    log.info("Running ApiClient.getEventStatusesForMap(mapId) test");
    String expectedResponse = this.loadExpectedResponse("/json/event-statuses/Map.json");
    this.setupMockServerSuccess("events.json?map_id=40", expectedResponse);

    WorldEventsStatusList list = this.apiClient.getEventStatusesForMap(40);
    assertTrue(list.size() == 1);
    assertTrue(list.get(0).getWorldId() == 1011);
    assertTrue(list.get(0).getMapId() == 40);
    assertTrue(list.get(0).getState().equals("Success"));
  }

  @Test
  public void testEventStatusesForWorldAndMap() throws IOException {
    log.info("Running ApiClient.getEventStatusesForWorldAndMap(worldId, mapId) test");
    String expectedResponse = this.loadExpectedResponse("/json/event-statuses/WorldAndMap.json");
    this.setupMockServerSuccess("events.json?world_id=1011&map_id=40", expectedResponse);

    WorldEventsStatusList list = this.apiClient.getEventStatusesForWorldAndMap(1011, 40);
    assertTrue(list.size() == 1);
    assertTrue(list.get(0).getWorldId() == 1011);
    assertTrue(list.get(0).getMapId() == 40);
    assertTrue(list.get(0).getState().equals("Success"));
  }

  @Test
  public void testEventStatusesForEvent() throws IOException {
    log.info("Running ApiClient.getEventStatusesForEvent(eventId) test");
    String expectedResponse = this.loadExpectedResponse("/json/event-statuses/Event.json");
    this.setupMockServerSuccess(
      "events.json?event_id=40B2CE06-F55B-41E5-BEEC-E5AEA1A1BC81",
      expectedResponse
    );

    WorldEventsStatusList list =
      this.apiClient.getEventStatusesForEvent("40B2CE06-F55B-41E5-BEEC-E5AEA1A1BC81");
    assertTrue(list.size() == 1);
    assertTrue(list.get(0).getWorldId() == 1001);
    assertTrue(list.get(0).getMapId() == 39);
    assertTrue(list.get(0).getState().equals("Active"));
  }

  @Test
  public void testEventStatusesForWorldAndEvent() throws IOException {
    log.info("Running ApiClient.getEventStatusesForWorldAndEvent(worldId, eventId) test");
    String expectedResponse = this.loadExpectedResponse("/json/event-statuses/Event.json");
    this.setupMockServerSuccess(
      "events.json?world_id=1001&event_id=40B2CE06-F55B-41E5-BEEC-E5AEA1A1BC81",
      expectedResponse
    );

    WorldEventsStatusList list =
      this.apiClient.getEventStatusesForWorldAndEvent(1001, "40B2CE06-F55B-41E5-BEEC-E5AEA1A1BC81");
    assertTrue(list.size() == 1);
    assertTrue(list.get(0).getWorldId() == 1001);
    assertTrue(list.get(0).getMapId() == 39);
    assertTrue(list.get(0).getState().equals("Active"));
  }

  @Test
  public void testEventStatusesForMapAndEvent() throws IOException {
    log.info("Running ApiClient.getEventStatusesForMapAndEvent(mapId, eventId) test");
    String expectedResponse = this.loadExpectedResponse("/json/event-statuses/Event.json");
    this.setupMockServerSuccess(
      "events.json?map_id=39&event_id=40B2CE06-F55B-41E5-BEEC-E5AEA1A1BC81",
      expectedResponse
    );

    WorldEventsStatusList list =
      this.apiClient.getEventStatusesForMapAndEvent(39, "40B2CE06-F55B-41E5-BEEC-E5AEA1A1BC81");
    assertTrue(list.size() == 1);
    assertTrue(list.get(0).getWorldId() == 1001);
    assertTrue(list.get(0).getMapId() == 39);
    assertTrue(list.get(0).getState().equals("Active"));
  }

  @Test
  public void testEventStatusesForWorldAndMapAndEvent() throws IOException {
    log.info("Running ApiClient.getEventStatusesForWorldAndMapAndEvent(worldId, mapId, eventId) test");
    String expectedResponse = this.loadExpectedResponse("/json/event-statuses/Event.json");
    this.setupMockServerSuccess(
      "events.json?world_id=1001&map_id=39&event_id=40B2CE06-F55B-41E5-BEEC-E5AEA1A1BC81",
      expectedResponse
    );

    WorldEventsStatusList list =
      this.apiClient.getEventStatusesForWorldAndMapAndEvent(1001, 39, "40B2CE06-F55B-41E5-BEEC-E5AEA1A1BC81");
    assertTrue(list.size() == 1);
    assertTrue(list.get(0).getWorldId() == 1001);
    assertTrue(list.get(0).getMapId() == 39);
    assertTrue(list.get(0).getState().equals("Active"));
  }

  /*~~~~ Begin tests for guild details ~~~~*/
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

  /*~~~~ Begin tests for item ids list ~~~~*/
  @Test
  public void testGetItemIds() throws IOException {
    log.info("Running ApiClient.getItems() test");
    String expectedResponse = this.loadExpectedResponse("/json/Items.json");
    this.setupMockServerSuccess("items.json", expectedResponse);

    ItemIdList list = this.apiClient.getItems();
    assertTrue(list.get(0).equals(12546));
  }

  /*~~~~ Begin tests for item details ~~~~*/
  @Test
  public void testGetArmorItemDetails() throws IOException {
    log.info("Running ApiClient.getItemDetails(itemId) for armor test");
    String expectedResponse = this.loadExpectedResponse("/json/ArmorItem.json");
    this.setupMockServerSuccess("item_details.json?item_id=100", expectedResponse);

    Optional<Item> result = this.apiClient.getItemDetails(100);
    assertTrue(result.isPresent());

    Item item = result.get();
    assertTrue(item.getType().equals("Armor"));
    assertTrue(item.getArmor().getType().equals("Coat"));
  }

  @Test
  public void testGetArmorItemDetailsInLang() throws IOException {
    log.info("Running ApiClient.getItemDetailsInLang(itemId, lang) for armor test");
    String expectedResponse = this.loadExpectedResponse("/json/ArmorItem.json");
    this.setupMockServerSuccess("item_details.json?item_id=100&lang=en", expectedResponse);

    Optional<Item> result = this.apiClient.getItemDetailsInLang(100, "en");
    assertTrue(result.isPresent());

    Item item = result.get();
    assertTrue(item.getType().equals("Armor"));
    assertTrue(item.getArmor().getType().equals("Coat"));
  }

  @Test
  public void testGetInvalidItemDetails() throws IOException {
    log.info("Running ApiClient.getItemDetails(itemId) failure test");
    String expectedResponse = this.loadExpectedResponse("/json/errors/InvalidItem.json");
    this.setupMockServerFail("item_details.json?item_id=-1", expectedResponse);

    try {
      Optional<Item> result = this.apiClient.getItemDetails(-1);
    } catch (GwApiError e) {
      assertTrue(e.getError() == 10);
      assertTrue(e.getProduct() == 0);
      assertTrue(e.getModule() == 2);
      assertTrue(e.getLine() == 382);
      assertTrue(e.getText().equals("invalid item_id"));
    }
  }

  /*~~~~ Begin tests for Maps ~~~~*/
  @Test
  public void testGetMap() throws IOException {
    log.info("Running ApiClient.getMap(mapId) test");
    String expectedResponse = this.loadExpectedResponse("/json/maps/SingleMap.json");
    this.setupMockServerSuccess("maps.json?map_id=110", expectedResponse);

    Optional<Map> result = this.apiClient.getMap(110);
    assertTrue(result.isPresent());

    Map map = result.get();
    assertTrue(map.getContinentId() == 1);
    assertTrue(map.getFloors().size() == 2);
  }

  @Test
  public void testGetMapInLang() throws IOException {
    log.info("Running ApiClient.getMapInLang(mapId, lang) test");
    String expectedResponse = this.loadExpectedResponse("/json/maps/SingleMap.json");
    this.setupMockServerSuccess("maps.json?map_id=110&lang=en", expectedResponse);

    Optional<Map> result = this.apiClient.getMapInLang(110, "en");
    assertTrue(result.isPresent());

    Map map = result.get();
    assertTrue(map.getContinentId() == 1);
    assertTrue(map.getFloors().size() == 2);
  }

  @Test
  public void testGetBadMapId() throws IOException {
    log.info("Running ApiClient.getMap(mapId) failure test");
    String expectedResponse = this.loadExpectedResponse("/json/errors/InvalidMap.json");
    this.setupMockServerFail("maps.json?map_id=42", expectedResponse);

    try {
      Optional<Map> result = this.apiClient.getMap(42);
    } catch (GwApiError e) {
      assertTrue(e.getError() == 10);
      assertTrue(e.getProduct() == 0);
      assertTrue(e.getModule() == 4);
      assertTrue(e.getLine() == 228);
      assertTrue(e.getText().equals("invalid map_id"));
    }
  }

  @Test
  public void testGetMaps() throws IOException {
    log.info("Running ApiClient.getMaps() test");
    String expectedResponse = this.loadExpectedResponse("/json/maps/MultipleMaps.json");
    this.setupMockServerSuccess("maps.json", expectedResponse);

    Optional<MapsList> result = this.apiClient.getMaps();
    assertTrue(result.isPresent());

    MapsList list = result.get();
    assertTrue(list.size() == 3);
    assertTrue(list.get(0).getContinentId() == 1);
    assertTrue(list.get(0).getFloors().size() == 2);
  }

  @Test
  public void testGetMapsInLang() throws IOException {
    log.info("Running ApiClient.getMapsInLang() test");
    String expectedResponse = this.loadExpectedResponse("/json/maps/MultipleMaps.json");
    this.setupMockServerSuccess("maps.json?lang=en", expectedResponse);

    Optional<MapsList> result = this.apiClient.getMapsInLang("en");
    assertTrue(result.isPresent());

    MapsList list = result.get();
    assertTrue(list.size() == 3);
    assertTrue(list.get(0).getContinentId() == 1);
    assertTrue(list.get(0).getFloors().size() == 2);
  }

  /*~~~~ Begin private utility methods. ~~~~*/
  private String loadExpectedResponse(String reponseFile) throws IOException {
    InputStream inputStream = this.getClass().getResourceAsStream(reponseFile);
    BufferedInputStream bis = new BufferedInputStream(inputStream);
    InputStreamReader reader = new InputStreamReader(bis);

    StringBuilder stringBuilder = new StringBuilder();
    int readBytes;
    char[] buffer = new char[1024];
    do {
      readBytes = reader.read(buffer);
      if (readBytes != -1) {
        stringBuilder.append(new String(buffer));
      }
    } while (readBytes != -1);

    return stringBuilder.toString();
  }

  private void setupMockServerSuccess(String dest, String expectedResponse) {
    this.mockServer.expect(requestTo(this.apiClient.getBaseUrl() + dest))
      .andExpect(method(HttpMethod.GET))
      .andRespond(withSuccess(expectedResponse, MediaType.APPLICATION_JSON));
  }

  private void setupMockServerFail(final String dest, final String expectedResponse)
  {
    this.mockServer.expect(requestTo(this.apiClient.getBaseUrl() + dest))
      .andExpect(method(HttpMethod.GET))
      .andRespond(new ResponseCreator() {
        @Override
        public ClientHttpResponse createResponse(ClientHttpRequest request) throws IOException {
          return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
              return HttpStatus.INTERNAL_SERVER_ERROR;
            }

            @Override
            public int getRawStatusCode() throws IOException {
              return 500;
            }

            @Override
            public String getStatusText() throws IOException {
              return expectedResponse;
            }

            @Override
            public void close() {}

            @Override
            public InputStream getBody() throws IOException {
              return null;
            }

            @Override
            public HttpHeaders getHeaders() {
              return null;
            }
          };
        }
      });
  }
}

@Configuration
class TestAppContext {
  @Bean
  public ApiClient apiClient() {
    return new ApiClient();
  }
}