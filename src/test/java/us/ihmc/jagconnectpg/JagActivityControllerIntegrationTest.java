package us.ihmc.jagconnectpg;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import us.ihmc.jagconnectpg.JagconnectpgApplication;
import us.ihmc.jagconnectpg.model.JagActivity;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JagconnectpgApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JagActivityControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://127.0.0.1:" + port;
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void testGetAllJagActivitys() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/jagActivities",
                HttpMethod.GET, entity, String.class);

        assertNotNull(response.getBody());
    }

    @Test
    public void testGetJagActivityById() {
        JagActivity jagActivity = restTemplate.getForObject(getRootUrl() + "/jagActivities/1", JagActivity.class);
        System.out.println(jagActivity.getName());
        assertNotNull(jagActivity);
    }

    @Test
    public void testCreateJagActivity() {
        JagActivity jagActivity = new JagActivity();
        jagActivity.setUrn("one:two:three:four");
        jagActivity.setName("activity-name");
        jagActivity.setDescription("This is an awful description");

        ResponseEntity<JagActivity> postResponse = restTemplate.postForEntity(getRootUrl() + "/jagActivities", jagActivity, JagActivity.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateJagActivity() {
        int id = 1;
        JagActivity jagActivity = restTemplate.getForObject(getRootUrl() + "/jagActivities/" + id, JagActivity.class);
        jagActivity.setName("activity-name2");
        jagActivity.setDescription("This is an even worse description");

        restTemplate.put(getRootUrl() + "/jagActivities/" + id, jagActivity);

        JagActivity updatedJagActivity = restTemplate.getForObject(getRootUrl() + "/jagActivities/" + id, JagActivity.class);
        assertNotNull(updatedJagActivity);
    }

    @Test
    public void testDeleteJagActivity() {
        int id = 2;
        JagActivity jagActivity = restTemplate.getForObject(getRootUrl() + "/jagActivities/" + id, JagActivity.class);
        assertNotNull(jagActivity);

        restTemplate.delete(getRootUrl() + "/jagActivities/" + id);

        try {
            jagActivity = restTemplate.getForObject(getRootUrl() + "/jagActivities/" + id, JagActivity.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}