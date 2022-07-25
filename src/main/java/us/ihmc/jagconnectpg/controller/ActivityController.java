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
@RequestMapping("/api/v1")
public class ActivityController {
    @Autowired
    private ActivityRepository activityRepository;

    @GetMapping("/jagActivities")
    public List<Activity> getAllJagActivities() {
        return activityRepository.findAll();
    }

    @GetMapping("/jagActivities/{urn}")
    public ResponseEntity<Activity> getJagActivityById(@PathVariable(value = "urn") String jagActivityId)
            throws ResourceNotFoundException {
        Activity activity = activityRepository.findById(jagActivityId)
                .orElseThrow(() -> new ResourceNotFoundException("JagActivity not found for this id :: " + jagActivityId));
        return ResponseEntity.ok().body(activity);
    }

    @PostMapping("/jagActivities")
    public Activity createJagActivity(@Valid @RequestBody Activity createdActivity) {

        return activityRepository.save(createdActivity);
    }

    @PutMapping("/jagActivities/{urn}")
    public ResponseEntity<Activity> updateJagActivity(@PathVariable(value = "urn") String jagActivityId,
                                                      @Valid @RequestBody Activity updatedActivity) throws ResourceNotFoundException {

//        JagActivity currentJagActivity = jagActivityRepository.findById(jagActivityId)
//                .orElseThrow(() -> new ResourceNotFoundException("JagActivity not found for this id :: " + jagActivityId));

        final Activity finalActivity = activityRepository.save(updatedActivity);
        return ResponseEntity.ok(finalActivity);
    }

    @DeleteMapping("/jagActivities/{urn}")
    public Map<String, Boolean> deleteJagActivity(@PathVariable(value = "urn") String jagActivityId)
            throws ResourceNotFoundException {
        System.out.println("----DELETE----------" + jagActivityId);
        Activity activity = activityRepository.findById(jagActivityId)
                .orElseThrow(() -> new ResourceNotFoundException("JagActivity not found for this id :: " + jagActivityId));

        activityRepository.delete(activity);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @DeleteMapping("/jagActivities")
    public Map<String, Boolean> deleteJagActivity(){
        System.out.println("----CLEAR----------");
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

