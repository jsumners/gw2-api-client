package com.jrfom.gw2;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.jrfom.gw2.api.model.Build;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;

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

  @Test
  public void testBuild() throws IOException {
    log.info("Running ApiClient.getBuild() test");
    String expectedReponse = this.loadExpectedResponse("/json/Build.json");
    this.mockServer.expect(requestTo(this.apiClient.getBaseUrl() + "build.json"))
      .andExpect(method(HttpMethod.GET))
      .andRespond(withSuccess(expectedReponse, MediaType.APPLICATION_JSON));

    Build build = this.apiClient.getBuild();
    assertTrue(build.getBuildId() == 22120);
  }

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
}

@Configuration
class TestAppContext {
  @Bean
  public ApiClient apiClient() {
    return new ApiClient();
  }
}