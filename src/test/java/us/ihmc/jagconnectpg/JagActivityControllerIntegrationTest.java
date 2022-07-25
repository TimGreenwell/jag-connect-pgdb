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

import us.ihmc.jagconnectpg.model.Activity;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JagconnectpgApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JagActivityControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
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
        Activity activity = restTemplate.getForObject(getRootUrl() + "/jagActivities/1", Activity.class);
        System.out.println(activity.getName());
        assertNotNull(activity);
    }

    @Test
    public void testCreateJagActivity() {
        Activity activity = new Activity();
        activity.setUrn("one:two:three:four");
        activity.setName("activity-name");
        activity.setDescription("This is an awful description");

        ResponseEntity<Activity> postResponse = restTemplate.postForEntity(getRootUrl() + "/jagActivities", activity, Activity.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateJagActivity() {
        int id = 1;
        Activity activity = restTemplate.getForObject(getRootUrl() + "/jagActivities/" + id, Activity.class);
        activity.setName("activity-name2");
        activity.setDescription("This is an even worse description");

        restTemplate.put(getRootUrl() + "/jagActivities/" + id, activity);

        Activity updatedActivity = restTemplate.getForObject(getRootUrl() + "/jagActivities/" + id, Activity.class);
        assertNotNull(updatedActivity);
    }

    @Test
    public void testDeleteJagActivity() {
        int id = 2;
        Activity activity = restTemplate.getForObject(getRootUrl() + "/jagActivities/" + id, Activity.class);
        assertNotNull(activity);

        restTemplate.delete(getRootUrl() + "/jagActivities/" + id);

        try {
            activity = restTemplate.getForObject(getRootUrl() + "/jagActivities/" + id, Activity.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}