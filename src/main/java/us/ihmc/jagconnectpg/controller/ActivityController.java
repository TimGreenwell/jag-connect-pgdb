package us.ihmc.jagconnectpg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import us.ihmc.jagconnectpg.exception.ResourceNotFoundException;
import us.ihmc.jagconnectpg.model.*;
import us.ihmc.jagconnectpg.repository.ActivityRepository;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(value = "/api/v1")
public class ActivityController {
    @Autowired
    private ActivityRepository activityRepository;

    @GetMapping(value = "/activities")
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    @GetMapping(value = "/activities/{urn}")
    public ResponseEntity<Activity> getActivityById(@PathVariable(value = "urn") String jagActivityId)
            throws ResourceNotFoundException {

        Activity activity = activityRepository.findById(jagActivityId)
                .orElseThrow(() -> new ResourceNotFoundException("JagActivity not found for this id :: " + jagActivityId));

        return ResponseEntity.ok().body(activity);
    }

    @PostMapping(value = "/activities", consumes = "application/json")
    public Activity createActivity(@Valid @RequestBody Activity createdActivity) {

        return activityRepository.save(createdActivity);
    }

    @PutMapping(value = "/activities/{urn}", consumes = "application/json")
    public ResponseEntity<Activity> updateActivity(@PathVariable(value = "urn") String jagActivityId,
                                                   @Valid @RequestBody Activity updatedActivity) {

        return ResponseEntity.ok(activityRepository.save(updatedActivity));
    }

    @DeleteMapping(value = "/activities/{urn}")
    public Map<String, Boolean> deleteActivity(@PathVariable(value = "urn") String jagActivityId)
            throws ResourceNotFoundException {

        Activity activity = activityRepository.findById(jagActivityId)
                .orElseThrow(() -> new ResourceNotFoundException("JagActivity not found for this id :: " + jagActivityId));

        activityRepository.delete(activity);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @DeleteMapping(value = "/activities")
    public Map<String, Boolean> deleteActivity() {
        activityRepository.deleteAll();
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}