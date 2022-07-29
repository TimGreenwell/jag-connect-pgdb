package us.ihmc.jagconnectpg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

@RestController
@RequestMapping(value = "/api/v1")
public class TeamController {
    @Autowired
    private TeamRepository teamRepository;

    @GetMapping(value = "/teams")
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @GetMapping(value = "/teams/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable(value = "id") String teamId)
            throws ResourceNotFoundException {

        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found for this id :: " + teamId));

        return ResponseEntity.ok().body(team);
    }

    @PostMapping(value = "/teams",
            consumes = "application/json")
    public Team createTeam(@Valid @RequestBody Team teamDetails) {

        return teamRepository.save(teamDetails);
    }

    @PutMapping(value = "/teams/{id}",  consumes = "application/json")
    public ResponseEntity<Team> updateTeam(@PathVariable(value = "id") String teamId,
                                                   @Valid @RequestBody Team teamDetails) throws ResourceNotFoundException {

        return ResponseEntity.ok(teamRepository.save(teamDetails));
    }

    @DeleteMapping(value = "/teams/{id}")
    public Map<String, Boolean> deleteTeam(@PathVariable(value = "id") String teamId)
            throws ResourceNotFoundException {

        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found for this id :: " + teamId));

        teamRepository.delete(team);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @DeleteMapping(value = "/teams")
    public Map<String, Boolean> deleteTeam() {
        teamRepository.deleteAll();
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}