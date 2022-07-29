package us.ihmc.jagconnectpg.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import us.ihmc.jagconnectpg.exception.ResourceNotFoundException;
import us.ihmc.jagconnectpg.model.Agent;
import us.ihmc.jagconnectpg.repository.AgentRepository;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(value = "/api/v1")
public class AgentController {
    @Autowired
    private AgentRepository agentRepository;

    @GetMapping(value = "/agents")
    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }

    @GetMapping(value = "/agents/{id}")
    public ResponseEntity<Agent> getAgentById(@PathVariable(value = "id") String agentId)
            throws ResourceNotFoundException {
        Agent agent = agentRepository.findById(agentId)
                .orElseThrow(() -> new ResourceNotFoundException("Agent not found for this id :: " + agentId));
        return ResponseEntity.ok().body(agent);
    }

    @PostMapping(value = "/agents",
            consumes = "application/json")
    public Agent createAgent(@Valid @RequestBody Agent agentDetails)  {

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(agentDetails.getAssessments());
        String assessments = String.valueOf(agentDetails.getAssessments());
        Map<String, Integer> newAssessmentMap;
        try {
            newAssessmentMap = mapper.readValue(assessments, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        agentDetails.setAssessments(newAssessmentMap);
        return agentRepository.save(agentDetails);
    }

    @PutMapping(value = "/agents/{id}",
            consumes = "application/json")
    public ResponseEntity<Agent> updateAgent(@PathVariable(value = "id") String agentId,
                                             @Valid @RequestBody Agent agentDetails) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        String assessments = String.valueOf(agentDetails.getAssessments());
        Map<String, Integer> newAssessmentMap = mapper.readValue(assessments, Map.class);
        agentDetails.setAssessments(newAssessmentMap);

        return ResponseEntity.ok(agentRepository.save(agentDetails));
    }

    @DeleteMapping(value = "/agents/{id}")
    public Map<String, Boolean> deleteAgent(@PathVariable(value = "id") String agentId)
            throws ResourceNotFoundException {
        Agent agent = agentRepository.findById(agentId)
                .orElseThrow(() -> new ResourceNotFoundException("Agent not found for this id :: " + agentId));

        agentRepository.delete(agent);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @DeleteMapping(value = "/agents")
    public Map<String, Boolean> deleteAgent() {
        agentRepository.deleteAll();
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}