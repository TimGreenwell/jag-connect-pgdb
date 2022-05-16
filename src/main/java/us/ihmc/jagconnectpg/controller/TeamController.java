package us.ihmc.jagconnectpg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import us.ihmc.jagconnectpg.exception.ResourceNotFoundException;
import us.ihmc.jagconnectpg.model.Team;
import us.ihmc.jagconnectpg.repository.TeamRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Team createTeam(@Valid @RequestBody Team team) {
        return teamRepository.save(team);
    }

    @PutMapping("/teams/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable(value = "id") String teamId,
                                                   @Valid @RequestBody Team teamDetails) throws ResourceNotFoundException {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found for this id :: " + teamId));

        team.setName(teamDetails.getName());
        team.setAgents(teamDetails.getAgents());
        team.setPerformers(teamDetails.getPerformers());
        final Team updatedTeam = teamRepository.save(team);
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