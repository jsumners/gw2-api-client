package com.jrfom.gw2;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.common.base.Optional;
import com.jrfom.gw2.api.model.Build;
import com.jrfom.gw2.api.model.GwApiError;
import com.jrfom.gw2.api.model.colors.ColorsList;
import com.jrfom.gw2.api.model.events.EventDetails;
import com.jrfom.gw2.api.model.events.EventNamesList;
import com.jrfom.gw2.api.model.geography.Continents;
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