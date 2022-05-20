package us.ihmc.jagconnectpg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import us.ihmc.jagconnectpg.exception.ResourceNotFoundException;
import us.ihmc.jagconnectpg.model.*;
import us.ihmc.jagconnectpg.repository.JagActivityRepository;
import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = "http://localhost:8888")
@RestController
@RequestMapping("/api/v1")
public class JagActivityController {
    @Autowired
    private JagActivityRepository jagActivityRepository;

    @GetMapping("/jagActivities")
    public List<JagActivity> getAllJagActivities() {
        System.out.println("---- ALL ------");
        return jagActivityRepository.findAll();
    }

    @GetMapping("/jagActivities/{urn}")
    public ResponseEntity<JagActivity> getJagActivityById(@PathVariable(value = "urn") String jagActivityId)
            throws ResourceNotFoundException {
        System.out.println("---- GET ---------->" + jagActivityId);
        JagActivity jagActivity = jagActivityRepository.findById(jagActivityId)
                .orElseThrow(() -> new ResourceNotFoundException("JagActivity not found for this id :: " + jagActivityId));
        return ResponseEntity.ok().body(jagActivity);
    }
    @CrossOrigin(origins = "http://localhost:8888")
    @PostMapping("/jagActivities")
    public JagActivity createJagActivity(@Valid @RequestBody JagActivity jagActivity) {
        System.out.println("---- CREATE ----------");

        JagActivity newJagActivity = new JagActivity();
        newJagActivity.setUrn(jagActivity.getUrn());
        newJagActivity.setDescription(jagActivity.getDescription());
        newJagActivity.setName(jagActivity.getName());
        newJagActivity.setType(jagActivity.getType());

        Connector newConnector = new Connector();
        newConnector.setExecution(jagActivity.getConnector().getExecution());
        newConnector.setOperator(jagActivity.getConnector().getOperator());
        newJagActivity.setConnector(newConnector);

        List<Binding> newBindingList = jagActivity.getBindings();
        for (Binding binding : newBindingList) {
            binding.setJagActivity(newJagActivity);
        }

        List<Output> newOutputList = jagActivity.getOutputs();
        for (Output output : newOutputList) {
            output.setJagActivity(newJagActivity);
        }

        List<Input> newInputList = jagActivity.getInputs();
        for (Input input : newInputList) {
            input.setJagActivity(newJagActivity);
        }

        List<JagActivityChild> newJagActivityChildList = jagActivity.getChildren();
        for (JagActivityChild jagActivityChild : newJagActivityChildList) {
            jagActivityChild.setJagActivity(newJagActivity);
        }

        newJagActivity.setBindings(newBindingList);
        newJagActivity.setOutputs(newOutputList);
        newJagActivity.setInputs(newInputList);
        newJagActivity.setChildren(newJagActivityChildList);

        return jagActivityRepository.save(newJagActivity);
    }


    @CrossOrigin(origins = "http://localhost:8888")
    @PutMapping("/jagActivities/{urn}")
    public ResponseEntity<JagActivity> updateJagActivity(@PathVariable(value = "urn") String jagActivityId,
                                                   @Valid @RequestBody JagActivity updatedJagActivity) throws ResourceNotFoundException {
        System.out.println("----UPDATE----------" + jagActivityId);
        // Note: updatedJagActivity and the findById(jagActivityId) should be exactly the same...

        JagActivity currentJagActivity = jagActivityRepository.findById(jagActivityId)
                .orElseThrow(() -> new ResourceNotFoundException("JagActivity not found for this id :: " + jagActivityId));


        JagActivity newJagActivity = new JagActivity();
        newJagActivity.setUrn(updatedJagActivity.getUrn());
        newJagActivity.setDescription(updatedJagActivity.getDescription());
        newJagActivity.setName(updatedJagActivity.getName());
        newJagActivity.setType(updatedJagActivity.getType());

        Connector newConnector = new Connector();
        newConnector.setExecution(updatedJagActivity.getConnector().getExecution());
        newConnector.setOperator(updatedJagActivity.getConnector().getOperator());
        newJagActivity.setConnector(newConnector);

        List<Binding> newBindingList = updatedJagActivity.getBindings();
        for (Binding binding : newBindingList) {
            binding.setJagActivity(newJagActivity);
        }

        List<Output> newOutputList = updatedJagActivity.getOutputs();
        for (Output output : newOutputList) {
            output.setJagActivity(newJagActivity);
        }

        List<Input> newInputList = updatedJagActivity.getInputs();
        for (Input input : newInputList) {
            input.setJagActivity(newJagActivity);
        }

        List<JagActivityChild> newJagActivityChildList = updatedJagActivity.getChildren();
        for (JagActivityChild jagActivityChild : newJagActivityChildList) {
            jagActivityChild.setJagActivity(newJagActivity);
        }

//        Alternate: try this if existing fails.
//        List<JagActivityChild> children = new ArrayList<>();
//        for (JagActivityChild child : updatedJagActivity.getChildren()){
//            JagActivityChild newChild = new JagActivityChild();
//            newChild.setId(child.getId()) ;
//            newChild.setUrn(child.getUrn()) ;
//            children.add(newChild);
//        }
//        currentJagActivity.setChildren(children);


        newJagActivity.setBindings(newBindingList);
        newJagActivity.setOutputs(newOutputList);
        newJagActivity.setInputs(newInputList);
        newJagActivity.setChildren(newJagActivityChildList);

        final JagActivity finalJagActivity = jagActivityRepository.save(newJagActivity);
        return ResponseEntity.ok(finalJagActivity);
    }

    @DeleteMapping("/jagActivities/{urn}")
    public Map<String, Boolean> deleteJagActivity(@PathVariable(value = "urn") String jagActivityId)
            throws ResourceNotFoundException {
        System.out.println("----DELETE----------" + jagActivityId);
        JagActivity jagActivity = jagActivityRepository.findById(jagActivityId)
                .orElseThrow(() -> new ResourceNotFoundException("JagActivity not found for this id :: " + jagActivityId));

        jagActivityRepository.delete(jagActivity);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}