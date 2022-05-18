package us.ihmc.jagconnectpg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import us.ihmc.jagconnectpg.exception.ResourceNotFoundException;
import us.ihmc.jagconnectpg.model.JagActivity;
import us.ihmc.jagconnectpg.repository.JagActivityRepository;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class JagActivityController {
    @Autowired
    private JagActivityRepository jagActivityRepository;

    @GetMapping("/jagActivities")
    public List<JagActivity> getAllJagActivities() {
        return jagActivityRepository.findAll();
    }

    @GetMapping("/jagActivities/{id}")
    public ResponseEntity<JagActivity> getJagActivityById(@PathVariable(value = "id") String jagActivityId)
            throws ResourceNotFoundException {
        JagActivity jagActivity = jagActivityRepository.findById(jagActivityId)
                .orElseThrow(() -> new ResourceNotFoundException("JagActivity not found for this id :: " + jagActivityId));
        return ResponseEntity.ok().body(jagActivity);
    }

    @PostMapping("/jagActivities")
    public JagActivity createJagActivity(@Valid @RequestBody JagActivity jagActivity) {
        return jagActivityRepository.save(jagActivity);
    }

    @PutMapping("/jagActivities/{id}")
    public ResponseEntity<JagActivity> updateJagActivity(@PathVariable(value = "id") String jagActivityId,
                                                   @Valid @RequestBody JagActivity jagActivityDetails) throws ResourceNotFoundException {
        JagActivity jagActivity = jagActivityRepository.findById(jagActivityId)
                .orElseThrow(() -> new ResourceNotFoundException("JagActivity not found for this id :: " + jagActivityId));

        jagActivity.setUrn(jagActivityDetails.getUrn());
        jagActivity.setDescription(jagActivityDetails.getDescription());
        jagActivity.setName(jagActivityDetails.getName());
        jagActivity.setType(jagActivityDetails.getType());
        jagActivity.setChildren(jagActivityDetails.getChildren());
        jagActivity.setConnector(jagActivityDetails.getConnector());
        jagActivity.setInputs(jagActivityDetails.getInputs());
        jagActivity.setOutputs(jagActivityDetails.getOutputs());
        jagActivity.setBindings(jagActivityDetails.getBindings());
        final JagActivity updatedJagActivity = jagActivityRepository.save(jagActivity);
        return ResponseEntity.ok(updatedJagActivity);
    }

    @DeleteMapping("/jagActivities/{id}")
    public Map<String, Boolean> deleteJagActivity(@PathVariable(value = "id") String jagActivityId)
            throws ResourceNotFoundException {
        JagActivity jagActivity = jagActivityRepository.findById(jagActivityId)
                .orElseThrow(() -> new ResourceNotFoundException("JagActivity not found for this id :: " + jagActivityId));

        jagActivityRepository.delete(jagActivity);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}