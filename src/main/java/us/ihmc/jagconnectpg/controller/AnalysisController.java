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

@RestController
@RequestMapping("/api/v1")
public class AnalysisController {
    @Autowired
    private AnalysisRepository analysisRepository;

    @GetMapping("/analyses")
    public List<Analysis> getAllAnalyses() {
        return analysisRepository.findAll();
    }

    @GetMapping("/analyses/{id}")
    public ResponseEntity<Analysis> getAnalysisById(@PathVariable(value = "id") String analysisId)
            throws ResourceNotFoundException {
        Analysis analysis = analysisRepository.findById(analysisId)
                .orElseThrow(() -> new ResourceNotFoundException("Analysis not found for this id :: " + analysisId));
        return ResponseEntity.ok().body(analysis);
    }

    @PostMapping("/analyses")
    public Analysis createAnalysis(@Valid @RequestBody Analysis analysis) {
        return analysisRepository.save(analysis);
    }

    @PutMapping("/analyses/{id}")
    public ResponseEntity<Analysis> updateAnalysis(@PathVariable(value = "id") String analysisId,
                                                   @Valid @RequestBody Analysis analysisDetails) throws ResourceNotFoundException {
        Analysis analysis = analysisRepository.findById(analysisId)
                .orElseThrow(() -> new ResourceNotFoundException("Analysis not found for this id :: " + analysisId));

        analysis.setId(analysisDetails.getId());
        analysis.setDescription(analysisDetails.getDescription());
        analysis.setName(analysisDetails.getName());
        analysis.setRootJagNodeId(analysisDetails.getRootJagNodeId());
        analysis.setRootJagActivityUrn(analysisDetails.getRootJagActivityUrn());
        analysis.setTeamId(analysisDetails.getTeamId());
        final Analysis updatedAnalysis = analysisRepository.save(analysis);
        return ResponseEntity.ok(updatedAnalysis);
    }

    @DeleteMapping("/analyses/{id}")
    public Map<String, Boolean> deleteAnalysis(@PathVariable(value = "id") String analysisId)
            throws ResourceNotFoundException {
        Analysis analysis = analysisRepository.findById(analysisId)
                .orElseThrow(() -> new ResourceNotFoundException("Analysis not found for this id :: " + analysisId));

        analysisRepository.delete(analysis);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}