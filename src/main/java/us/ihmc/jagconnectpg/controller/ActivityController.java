package us.ihmc.jagconnectpg.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
                                                   @Valid @RequestBody Activity updatedActivity)
            throws ResourceNotFoundException, JsonProcessingException {

        Activity activity = activityRepository.findById(jagActivityId)
                .orElseThrow(() -> new ResourceNotFoundException("JagActivity not found for this id :: " + jagActivityId));

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
    public Map<String, Boolean> deleteActivity(){
        activityRepository.deleteAll();
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}





//        JagActivity newJagActivity = new JagActivity();
//        newJagActivity.setUrn(createdJagActivity.getUrn());
//        newJagActivity.setName(createdJagActivity.getName());
//        newJagActivity.setDescription(createdJagActivity.getDescription());
//
//        Connector newConnector = new Connector();
//        newConnector.setExecution(createdJagActivity.getConnector().getExecution());
//        newConnector.setReturns(createdJagActivity.getConnector().getReturns());
//        newConnector.setOperator(createdJagActivity.getConnector().getOperator());
//        newJagActivity.setConnector(newConnector);
//
//        List<Input> newInputList = new ArrayList<>();
//        for (Input input : createdJagActivity.getInputs()) {
//            Input newInput = new Input();
//            newInput.setJagActivity(newJagActivity);
//            newInput.setName(input.getName());
//            newInput.setType(input.getType());
//            newInputList.add(newInput);
//        }
//        newJagActivity.setInputs(newInputList);
//
//
//
//        List<Output> newOutputList = new ArrayList<>();
//        for (Output output : createdJagActivity.getOutputs()) {
//            Output newOutput = new Output();
//            newOutput.setJagActivity(newJagActivity);
//            newOutput.setName(output.getName());
//            newOutput.setType(output.getType());
//            newOutputList.add(newOutput);
//        }
//        newJagActivity.setOutputs(newOutputList);
//
//
//
//        List<JagActivityChild> newJagActivityChildList = new ArrayList<>();
//        for (JagActivityChild jagActivityChild : createdJagActivity.getChildren()) {
//            JagActivityChild newJagActivityChild = new JagActivityChild();
//            newJagActivityChild.setJagActivity(newJagActivity);
//            newJagActivityChild.setId(jagActivityChild.getId());
//            newJagActivityChild.setUrn(jagActivityChild.getUrn());
//            newJagActivityChildList.add(newJagActivityChild);
//        }
//        newJagActivity.setChildren(newJagActivityChildList);
//
//
//
//        List<Binding> newBindingList = new ArrayList<>();
//        for (Binding binding : createdJagActivity.getBindings()) {
//            Binding newBinding = new Binding();
//            newBinding.setJagActivity(newJagActivity);
//            newBinding.setId(binding.getId());
//            newBinding.setIn(binding.getIn());
//            newBinding.setOut(binding.getOut());
//            newBindingList.add(newBinding);
//        }
//        newJagActivity.setBindings(newBindingList);
//
//        newJagActivity.setAuthor(createdJagActivity.getAuthor());
//        newJagActivity.setLockedBy(createdJagActivity.getLockedBy());
//        newJagActivity.setCreatedDate(createdJagActivity.getCreatedDate());
//        newJagActivity.setModifiedDate(createdJagActivity.getModifiedDate());
//        newJagActivity.setLocked(createdJagActivity.getLocked());
//        newJagActivity.setCollapsed(createdJagActivity.getCollapsed());

