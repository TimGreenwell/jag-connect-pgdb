package us.ihmc.jagconnectpg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import us.ihmc.jagconnectpg.exception.ResourceNotFoundException;
import us.ihmc.jagconnectpg.model.Analysis;
import us.ihmc.jagconnectpg.repository.AnalysisRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.Set;

@RestController
@RequestMapping(value = "/api/v1")
public class AnalysisController {
    @Autowired
    private AnalysisRepository analysisRepository;

    @GetMapping(value = "/analyses")
    public List<Analysis> getAllAnalyses() {
        return analysisRepository.findAll();
    }

    @GetMapping(value = "/analyses/{id}")
    public ResponseEntity<Analysis> getAnalysisById(@PathVariable(value = "id") String analysisId)
            throws ResourceNotFoundException {

        Analysis analysis = analysisRepository.findById(analysisId)
                .orElseThrow(() -> new ResourceNotFoundException("Analysis not found for this id :: " + analysisId));

        return ResponseEntity.ok().body(analysis);
    }

    @PostMapping(value = "/analyses",
            consumes = "application/json")
    public Analysis createAnalysis(@Valid @RequestBody Analysis analysisDetails) {
        System.out.println("......................................................................................................");
        System.out.println(analysisDetails);

        Analysis newAnalysis = new Analysis();
        newAnalysis.setId(analysisDetails.getId());
        newAnalysis.setName(analysisDetails.getName());
        newAnalysis.setDescription(analysisDetails.getDescription());
        newAnalysis.setRootUrn(analysisDetails.getRootUrn());
        newAnalysis.setTeamId(analysisDetails.getTeamId());
        newAnalysis.setIsLocked(analysisDetails.getIsLocked());
        return analysisRepository.save(newAnalysis);
    }

    @PutMapping(value = "/analyses/{id}",
            consumes = "application/json")
    public ResponseEntity<Analysis> updateAnalysis(@PathVariable(value = "id") String analysisId,
                                                   @Valid @RequestBody Analysis analysisDetails) throws ResourceNotFoundException {

        Analysis analysis = analysisRepository.findById(analysisId)
                .orElseThrow(() -> new ResourceNotFoundException("Analysis not found for this id :: " + analysisId));

        Analysis newAnalysis = new Analysis();
        newAnalysis.setId(analysisDetails.getId());
        newAnalysis.setName(analysisDetails.getName());
        newAnalysis.setDescription(analysisDetails.getDescription());
        newAnalysis.setRootUrn(analysisDetails.getRootUrn());
        newAnalysis.setTeamId(analysisDetails.getTeamId());
        newAnalysis.setIsLocked(analysisDetails.getIsLocked());

        final Analysis updatedAnalysis = analysisRepository.save(analysis);
        return ResponseEntity.ok(updatedAnalysis);
    }

    @DeleteMapping(value = "/analyses/{id}")
    public Map<String, Boolean> deleteAnalysis(@PathVariable(value = "id") String analysisId)
            throws ResourceNotFoundException {
        Analysis analysis = analysisRepository.findById(analysisId)
                .orElseThrow(() -> new ResourceNotFoundException("Analysis not found for this id :: " + analysisId));

        analysisRepository.delete(analysis);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @DeleteMapping(value = "/analyses")
    public Map<String, Boolean> deleteAnalysis() {
        analysisRepository.deleteAll();
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}