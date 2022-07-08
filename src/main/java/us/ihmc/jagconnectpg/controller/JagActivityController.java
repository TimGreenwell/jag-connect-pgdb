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

        System.out.println("v");
        System.out.println("v");
        System.out.println("v");
        System.out.println("v");
        System.out.println("v");
        System.out.println("v");
        System.out.println(createdJagActivity.toString());
        System.out.println(createdJagActivity.toString());
        System.out.println(createdJagActivity.toString());
        System.out.println("v");
        System.out.println("v");
        System.out.println("v");
        System.out.println("v");
        System.out.println("v");
        System.out.println("v");


        JagActivity newJagActivity = new JagActivity();
        newJagActivity.setUrn(createdJagActivity.getUrn());
        newJagActivity.setName(createdJagActivity.getName());
        newJagActivity.setDescription(createdJagActivity.getDescription());

        Connector newConnector = new Connector();
        newConnector.setExecution(createdJagActivity.getConnector().getExecution());
        newConnector.setReturns(createdJagActivity.getConnector().getReturns());
        newConnector.setOperator(createdJagActivity.getConnector().getOperator());
        newJagActivity.setConnector(newConnector);

        List<Input> newInputList = new ArrayList<>();
        for (Input input : createdJagActivity.getInputs()) {
            Input newInput = new Input();
            newInput.setJagActivity(newJagActivity);
            newInput.setName(input.getName());
            newInput.setType(input.getType());
        }
        newJagActivity.setInputs(newInputList);

        List<Output> newOutputList = new ArrayList<>();
        for (Output output : createdJagActivity.getOutputs()) {
            Output newOutput = new Output();
            newOutput.setJagActivity(newJagActivity);
            newOutput.setName(output.getName());
            newOutput.setType(output.getType());
        }
        newJagActivity.setOutputs(newOutputList);

//        List<JagActivityChild> newJagActivityChildList = new ArrayList<>();
//        for (JagActivityChild jagActivityChild : createdJagActivity.getChildren()) {
//            String id = jagActivityChild.getId();
//            String urn = jagActivityChild.getUrn();
//     //       JagActivityChild newJagActivityChild = new JagActivityChild(id,urn,newJagActivity);
//            JagActivityChild newJagActivityChild = new JagActivityChild(id,urn);
//            newJagActivityChildList.add(newJagActivityChild);
//        }
//        newJagActivity.setChildren(newJagActivityChildList);


        List<JagActivityChild> newJagActivityChildList = new ArrayList<>();
        for (JagActivityChild jagActivityChild : createdJagActivity.getChildren()) {
            JagActivityChild newJagActivityChild = new JagActivityChild();
            newJagActivityChild.setId(jagActivityChild.getId());
            newJagActivityChild.setUrn(jagActivityChild.getUrn());
            newJagActivityChild.setJagActivity(newJagActivity);
            newJagActivityChildList.add(newJagActivityChild);
        }
        newJagActivity.setChildren(newJagActivityChildList);





        List<Binding> newBindingList = new ArrayList<>();
        for (Binding binding : createdJagActivity.getBindings()) {
            Binding newBinding = new Binding();
            newBinding.setJagActivity(newJagActivity);
            //newBinding.setId(binding.getId());
            newBinding.setIn(binding.getIn());
            newBinding.setOut(binding.getOut());
        }
        newJagActivity.setBindings(newBindingList);

        newJagActivity.setLocked(createdJagActivity.getLocked());
        newJagActivity.setCollapsed(createdJagActivity.getCollapsed());

        newJagActivity.setAuthor(createdJagActivity.getAuthor());
        newJagActivity.setLockedBy(createdJagActivity.getLockedBy());
        newJagActivity.setCreatedDate(createdJagActivity.getCreatedDate());
        newJagActivity.setModifiedDate(createdJagActivity.getModifiedDate());
        newJagActivity.setLocked(createdJagActivity.getLocked());
        newJagActivity.setCollapsed(createdJagActivity.getCollapsed());

//        List<JagActivityChild> newJagActivityChildList = new ArrayList<>();
//        for (JagActivityChild jagActivityChild : createdJagActivity.getChildren()) {
//            JagActivityChild newJagActivityChild = new JagActivityChild();
//            newJagActivityChild.setJagActivity(newJagActivity);
//            newJagActivityChild.setId(jagActivityChild.getId());
//            newJagActivityChild.setUrn(jagActivityChild.getUrn());
        //            newJagActivityChild.setUrn(jagActivityChild.getUrn());
//            newJagActivityChildList.add(newJagActivityChild);
//        }





        return jagActivityRepository.save(newJagActivity);
    }


    @PutMapping("/jagActivities/{urn}")
    public ResponseEntity<JagActivity> updateJagActivity(@PathVariable(value = "urn") String jagActivityId,
                                                   @Valid @RequestBody JagActivity updatedJagActivity) throws ResourceNotFoundException {
        System.out.println("----UPDATE----------" + jagActivityId);

        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println(updatedJagActivity.toString());
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        // Note: updatedJagActivity and the findById(jagActivityId) should be exactly the same...

//        JagActivity currentJagActivity = jagActivityRepository.findById(jagActivityId)
//                .orElseThrow(() -> new ResourceNotFoundException("JagActivity not found for this id :: " + jagActivityId));


        JagActivity newJagActivity = new JagActivity();

        newJagActivity.setUrn(updatedJagActivity.getUrn());
        newJagActivity.setName(updatedJagActivity.getName());
        newJagActivity.setDescription(updatedJagActivity.getDescription());

        newJagActivity.setAuthor(updatedJagActivity.getAuthor());
        newJagActivity.setLockedBy(updatedJagActivity.getLockedBy());
        newJagActivity.setCreatedDate(updatedJagActivity.getCreatedDate());
        newJagActivity.setModifiedDate(updatedJagActivity.getModifiedDate());
        newJagActivity.setLocked(updatedJagActivity.getLocked());
        newJagActivity.setCollapsed(updatedJagActivity.getCollapsed());

        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("2");
        System.out.println(newJagActivity.toString());
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");




        Connector newConnector = new Connector();
        newConnector.setExecution(updatedJagActivity.getConnector().getExecution());
        newConnector.setReturns(updatedJagActivity.getConnector().getReturns());
        newConnector.setOperator(updatedJagActivity.getConnector().getOperator());
        newJagActivity.setConnector(newConnector);

        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("3");
        System.out.println(newJagActivity.toString());
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");


        List<Input> newInputList = new ArrayList<>();
        for (Input input : updatedJagActivity.getInputs()) {
            Input newInput = new Input();
            newInput.setJagActivity(newJagActivity);
            newInput.setName(input.getName());
            newInput.setType(input.getType());
            newInputList.add(newInput);
        }
        newJagActivity.setInputs(newInputList);

        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("4");
        System.out.println(newJagActivity.toString());
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");

        List<Output> newOutputList = new ArrayList<>();
        for (Output output : updatedJagActivity.getOutputs()) {
            Output newOutput = new Output();
            newOutput.setJagActivity(newJagActivity);
            newOutput.setName(output.getName());
            newOutput.setType(output.getType());
            newOutputList.add(newOutput);
        }
        newJagActivity.setOutputs(newOutputList);


        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("5");
        System.out.println(newJagActivity.toString());
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");


        List<JagActivityChild> newJagActivityChildList = new ArrayList<>();
        for (JagActivityChild jagActivityChild : updatedJagActivity.getChildren()) {
            JagActivityChild newJagActivityChild = new JagActivityChild();
            newJagActivityChild.setId(jagActivityChild.getId());
            newJagActivityChild.setUrn(jagActivityChild.getUrn());
//            String id = jagActivityChild.getId();
//            String urn = jagActivityChild.getUrn();
    //        JagActivityChild newJagActivityChild = new JagActivityChild(id,urn);
            newJagActivityChild.setJagActivity(newJagActivity);
            newJagActivityChildList.add(newJagActivityChild);
        }
        newJagActivity.setChildren(newJagActivityChildList);


        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("6*");
        System.out.println(newJagActivity.toString());
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");


        List<Binding> newBindingList = new ArrayList<>();
        for (Binding binding : updatedJagActivity.getBindings()) {
            Binding newBinding = new Binding();
            newBinding.setJagActivity(newJagActivity);
            //newBinding.setId(binding.getId());
            newBinding.setIn(binding.getIn());
            newBinding.setOut(binding.getOut());
            newBindingList.add(newBinding);
        }
        newJagActivity.setBindings(newBindingList);

        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("7");
        System.out.println(newJagActivity.toString());
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");
        System.out.println("^");




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