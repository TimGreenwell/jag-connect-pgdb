package us.ihmc.jagconnectpg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import us.ihmc.jagconnectpg.exception.ResourceNotFoundException;
import us.ihmc.jagconnectpg.model.*;
import us.ihmc.jagconnectpg.repository.JagActivityRepository;
import javax.validation.Valid;
import java.util.*;

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

    @PostMapping("/jagActivities")
    public JagActivity createJagActivity(@Valid @RequestBody JagActivity createdJagActivity) {
        System.out.println("---- CREATE ----------");

        JagActivity newJagActivity = new JagActivity();
        newJagActivity.setUrn(createdJagActivity.getUrn());
        newJagActivity.setDescription(createdJagActivity.getDescription());
        newJagActivity.setName(createdJagActivity.getName());

        newJagActivity.setAuthor(createdJagActivity.getAuthor());
        newJagActivity.setLockedBy(createdJagActivity.getLockedBy());
        newJagActivity.setCreatedDate(createdJagActivity.getCreatedDate());
        newJagActivity.setModifiedDate(createdJagActivity.getModifiedDate());
        newJagActivity.setLocked(createdJagActivity.getLocked());
        newJagActivity.setCollapsed(createdJagActivity.getCollapsed());


        Connector newConnector = new Connector();
        newConnector.setExecution(createdJagActivity.getConnector().getExecution());
        newConnector.setReturns(createdJagActivity.getConnector().getReturns());
        newConnector.setOperator(createdJagActivity.getConnector().getOperator());
        newJagActivity.setConnector(newConnector);

        List<Binding> newBindingList = new ArrayList<>();
        for (Binding binding : createdJagActivity.getBindings()) {
            Binding newBinding = new Binding();
            newBinding.setJagActivity(newJagActivity);
            newBinding.setId(binding.getId());
            newBinding.setIn(binding.getIn());
            newBinding.setOut(binding.getOut());
        }

        List<Output> newOutputList = new ArrayList<>();
        for (Output output : createdJagActivity.getOutputs()) {
            Output newOutput = new Output();
            newOutput.setJagActivity(newJagActivity);
            newOutput.setName(output.getName());
            newOutput.setType(output.getType());
        }

        List<Input> newInputList = new ArrayList<>();
        for (Input input : createdJagActivity.getInputs()) {
            Input newInput = new Input();
            newInput.setJagActivity(newJagActivity);
            newInput.setName(input.getName());
            newInput.setType(input.getType());
        }


        List<JagActivityChild> newJagActivityChildList = new ArrayList<>();
        for (JagActivityChild jagActivityChild : createdJagActivity.getChildren()) {
            String id = jagActivityChild.getId();
            String urn = jagActivityChild.getUrn();
            JagActivityChild newJagActivityChild = new JagActivityChild(id,urn,newJagActivity);
            newJagActivityChildList.add(newJagActivityChild);
        }


//        List<JagActivityChild> newJagActivityChildList = new ArrayList<>();
//        for (JagActivityChild jagActivityChild : createdJagActivity.getChildren()) {
//            JagActivityChild newJagActivityChild = new JagActivityChild();
//            newJagActivityChild.setJagActivity(newJagActivity);
//            newJagActivityChild.setId(jagActivityChild.getId());
//            newJagActivityChild.setUrn(jagActivityChild.getUrn());
        //            newJagActivityChild.setUrn(jagActivityChild.getUrn());
//            newJagActivityChildList.add(newJagActivityChild);
//        }

        newJagActivity.setBindings(newBindingList);
        newJagActivity.setOutputs(newOutputList);
        newJagActivity.setInputs(newInputList);
        newJagActivity.setChildren(newJagActivityChildList);
        return jagActivityRepository.save(newJagActivity);
    }


    @PutMapping("/jagActivities/{urn}")
    public ResponseEntity<JagActivity> updateJagActivity(@PathVariable(value = "urn") String jagActivityId,
                                                   @Valid @RequestBody JagActivity updatedJagActivity) throws ResourceNotFoundException {
        System.out.println("----UPDATE----------" + jagActivityId);
        // Note: updatedJagActivity and the findById(jagActivityId) should be exactly the same...

//        JagActivity currentJagActivity = jagActivityRepository.findById(jagActivityId)
//                .orElseThrow(() -> new ResourceNotFoundException("JagActivity not found for this id :: " + jagActivityId));


        JagActivity newJagActivity = new JagActivity();
        newJagActivity.setUrn(updatedJagActivity.getUrn());
        newJagActivity.setDescription(updatedJagActivity.getDescription());
        newJagActivity.setName(updatedJagActivity.getName());

        Connector newConnector = new Connector();
        newConnector.setExecution(updatedJagActivity.getConnector().getExecution());
        newConnector.setOperator(updatedJagActivity.getConnector().getOperator());
        newJagActivity.setConnector(newConnector);

        List<Binding> newBindingList = new ArrayList<>();
        for (Binding binding : updatedJagActivity.getBindings()) {
            Binding newBinding = new Binding();
            newBinding.setJagActivity(newJagActivity);
            newBinding.setId(binding.getId());
            newBinding.setIn(binding.getIn());
            newBinding.setOut(binding.getOut());
            newBindingList.add(newBinding);
        }

        List<Output> newOutputList = new ArrayList<>();
        for (Output output : updatedJagActivity.getOutputs()) {
            Output newOutput = new Output();
            newOutput.setJagActivity(newJagActivity);
            newOutput.setName(output.getName());
            newOutput.setType(output.getType());
            newOutputList.add(newOutput);
        }

        List<Input> newInputList = new ArrayList<>();
        for (Input input : updatedJagActivity.getInputs()) {
            Input newInput = new Input();
            newInput.setJagActivity(newJagActivity);
            newInput.setName(input.getName());
            newInput.setType(input.getType());
            newInputList.add(newInput);
        }

        List<JagActivityChild> newJagActivityChildList = new ArrayList<>();
        for (JagActivityChild jagActivityChild : updatedJagActivity.getChildren()) {
            String id = jagActivityChild.getId();
            String urn = jagActivityChild.getUrn();
            JagActivityChild newJagActivityChild = new JagActivityChild(id,urn,newJagActivity);
            newJagActivityChildList.add(newJagActivityChild);
        }



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

    @DeleteMapping("/jagActivities")
    public Map<String, Boolean> deleteJagActivity(){
        System.out.println("----CLEAR----------");
        jagActivityRepository.deleteAll();
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}