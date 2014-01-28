package com.jrfom.gw2;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Before;
import org.junit.runner.RunWith;
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

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
  TestAppContext.class
})
public class BaseApiClientTestClass {
  @Autowired
  protected ApiClient apiClient;

  protected MockRestServiceServer mockServer;

  @Before
  public void setup() {
    this.mockServer = MockRestServiceServer.createServer(this.apiClient);
  }

  protected String loadExpectedResponse(String reponseFile) throws IOException {
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

  protected void setupMockServerSuccess(String dest, String expectedResponse) {
    this.mockServer.expect(requestTo(this.apiClient.getBaseUrl() + dest))
      .andExpect(method(HttpMethod.GET))
      .andRespond(withSuccess(expectedResponse, MediaType.APPLICATION_JSON));
  }

  protected void setupMockServerFail(final String dest, final String expectedResponse)
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