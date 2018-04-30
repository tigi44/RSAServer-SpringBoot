package com.tigi.rsa;

import java.net.URL;
import java.util.Map;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractWebIntegrationTest {
    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    protected TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port);
    }

    protected <T> ResponseEntity<T> ensureOK(ResponseEntity<T> responseEntity) {
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        return responseEntity;
    }

    protected <T> ResponseEntity<T> get(String uri, Class<T> responseType) {
        String url = base.toString() + uri;
        ResponseEntity<T> result = template.getForEntity(url, responseType);
        return ensureOK(result);
    }

    protected <T> ResponseEntity<T> post(String uri, Map<String, String> parameters, Class<T> responseType) {
        MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<String, String>();
        postParameters.setAll(parameters);
        ResponseEntity<T> result = template.postForEntity(base.toString() + uri, postParameters, responseType);
        return ensureOK(result);
    }
}