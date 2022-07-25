package us.ihmc.jagconnectpg.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import us.ihmc.jagconnectpg.exception.ResourceNotFoundException;
import us.ihmc.jagconnectpg.model.Agent;
import us.ihmc.jagconnectpg.model.Assessment;
import us.ihmc.jagconnectpg.repository.AgentRepository;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class AgentController {
    @Autowired
    private AgentRepository agentRepository;

    @GetMapping("/agents")
    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }

    @GetMapping("/agents/{id}")
    public ResponseEntity<Agent> getAgentById(@PathVariable(value = "id") String agentId)
            throws ResourceNotFoundException {
        Agent agent = agentRepository.findById(agentId)
                .orElseThrow(() -> new ResourceNotFoundException("Agent not found for this id :: " + agentId));
        return ResponseEntity.ok().body(agent);
    }

    @PostMapping("/agents")
    public Agent createAgent(@Valid @RequestBody Agent agentDetails) {

        Agent newAgent = new Agent();
        newAgent.setId(agentDetails.getId());
        newAgent.setName(agentDetails.getName());

        List<Assessment> newAssessmentList = new ArrayList<>();
        for (Assessment assessment : agentDetails.getAssessments()) {
            Assessment newAssessment = new Assessment();
            newAssessment.setAgent(newAgent);
            newAssessment.setId(assessment.getId());
            newAssessment.setAssessmentScore(assessment.getAssessmentScore());
            newAssessmentList.add(newAssessment);
        }

        newAgent.setAssessments(newAssessmentList);

        return agentRepository.save(newAgent);
    }

    @PutMapping("/agents/{id}")
    public ResponseEntity<Agent> updateAgent(@PathVariable(value = "id") String agentId,
                                             @Valid @RequestBody Agent agentDetails) throws ResourceNotFoundException, JsonProcessingException {

        // Note: updatedJagActivity and the findById(jagActivityId) should be exactly the same...
//        Agent agent = agentRepository.findById(agentId)
//                .orElseThrow(() -> new ResourceNotFoundException("Agent not found for this id :: " + agentId));

        Agent newAgent = new Agent();
        newAgent.setId(agentDetails.getId());
        newAgent.setName(agentDetails.getName());

        // ALTERNATIVE? IMPORVEMENT?
//        ObjectMapper mapper = new ObjectMapper();
//        System.out.println(agentDetails.getAssessments());
//        String assessments = String.valueOf(agentDetails.getAssessments());
//        Map<String, Integer> newAssessmentMap = mapper.readValue(assessments, Map.class);
//        newAgent.setAssessments(newAssessmentMap);
        List<Assessment> newAssessmentList = new ArrayList<>();
        for (Assessment assessment : agentDetails.getAssessments()) {
            Assessment newAssessment = new Assessment();
            newAssessment.setAgent(newAgent);
            newAssessment.setId(assessment.getId());
            newAssessment.setAssessmentScore(assessment.getAssessmentScore());
            newAssessmentList.add(newAssessment);
        }

        final Agent updatedAgent = agentRepository.save(newAgent);
        return ResponseEntity.ok(updatedAgent);
    }

    @DeleteMapping("/agents/{id}")
    public Map<String, Boolean> deleteAgent(@PathVariable(value = "id") String agentId)
            throws ResourceNotFoundException {
        Agent agent = agentRepository.findById(agentId)
                .orElseThrow(() -> new ResourceNotFoundException("Agent not found for this id :: " + agentId));

        agentRepository.delete(agent);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @DeleteMapping("/agents")
    public Map<String, Boolean> deleteAgent() {
        agentRepository.deleteAll();
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}