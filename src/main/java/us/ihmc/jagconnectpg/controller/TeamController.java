package us.ihmc.jagconnectpg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import us.ihmc.jagconnectpg.exception.ResourceNotFoundException;
import us.ihmc.jagconnectpg.model.Agent;
import us.ihmc.jagconnectpg.model.Performer;
import us.ihmc.jagconnectpg.model.Team;
import us.ihmc.jagconnectpg.repository.TeamRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class TeamController {
    @Autowired
    private TeamRepository teamRepository;

    @GetMapping("/teams")
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @GetMapping("/teams/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable(value = "id") String teamId)
            throws ResourceNotFoundException {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found for this id :: " + teamId));
        return ResponseEntity.ok().body(team);
    }

    @PostMapping("/teams")
    public Team createTeam(@Valid @RequestBody Team teamDetails) {


        Team newTeam = new Team();

        newTeam.setId(teamDetails.getId());
        newTeam.setName(teamDetails.getName());
        List<Agent> agentList = new ArrayList<>();
        for (Agent agent: teamDetails.getAgents()) {
            Agent newAgent = new Agent();
            newAgent.setId(agent.getId());
            newAgent.setName(agent.getName());
            newAgent.setAssessments(agent.getAssessments());
            newAgent.setTeam(agent.getTeam());
            agentList.add(newAgent);
        }
        List<Performer> performerList = new ArrayList<>();
        for (Performer agent: teamDetails.getPerformers()) {
            Performer newPerformer = new Performer();
            newPerformer.setId(agent.getId());
            newPerformer.setName(agent.getName());
            performerList.add(newPerformer);
        }

        newTeam.setAgents(agentList);
        newTeam.setPerformers(performerList);

        return teamRepository.save(newTeam);
    }

    @PutMapping("/teams/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable(value = "id") String teamId,
                                                   @Valid @RequestBody Team teamDetails) throws ResourceNotFoundException {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found for this id :: " + teamId));

        Team newTeam = new Team();

        newTeam.setId(teamDetails.getId());
        newTeam.setName(teamDetails.getName());
        List<Agent> agentList = new ArrayList<>();
        for (Agent agent: teamDetails.getAgents()) {
            Agent newAgent = new Agent();
            newAgent.setId(agent.getId());
            newAgent.setName(agent.getName());
            newAgent.setAssessments(agent.getAssessments());
            newAgent.setTeam(agent.getTeam());
            agentList.add(newAgent);
        }
        List<Performer> performerList = new ArrayList<>();
        for (Performer agent: teamDetails.getPerformers()) {
            Performer newPerformer = new Performer();
            newPerformer.setId(agent.getId());
            newPerformer.setName(agent.getName());
            performerList.add(newPerformer);
        }

        newTeam.setAgents(agentList);
        newTeam.setPerformers(performerList);
        final Team updatedTeam = teamRepository.save(newTeam);
        return ResponseEntity.ok(updatedTeam);
    }

    @DeleteMapping("/teams/{id}")
    public Map<String, Boolean> deleteTeam(@PathVariable(value = "id") String teamId)
            throws ResourceNotFoundException {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found for this id :: " + teamId));

        teamRepository.delete(team);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}