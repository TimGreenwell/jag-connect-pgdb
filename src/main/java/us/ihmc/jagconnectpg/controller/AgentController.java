package us.ihmc.jagconnectpg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import us.ihmc.jagconnectpg.exception.ResourceNotFoundException;
import us.ihmc.jagconnectpg.model.Agent;
import us.ihmc.jagconnectpg.repository.AgentRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Agent createAgent(@Valid @RequestBody Agent agent) {
        return agentRepository.save(agent);
    }

    @PutMapping("/agents/{id}")
    public ResponseEntity<Agent> updateAgent(@PathVariable(value = "id") String agentId,
                                                   @Valid @RequestBody Agent agentDetails) throws ResourceNotFoundException {
        Agent agent = agentRepository.findById(agentId)
                .orElseThrow(() -> new ResourceNotFoundException("Agent not found for this id :: " + agentId));

        agent.setId(agentDetails.getId());
        agent.setAssessments(agentDetails.getAssessments());
        agent.setName(agentDetails.getName());
        final Agent updatedAgent = agentRepository.save(agent);
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
}