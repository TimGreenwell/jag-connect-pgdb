package us.ihmc.jagconnectpg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import us.ihmc.jagconnectpg.exception.ResourceNotFoundException;
import us.ihmc.jagconnectpg.model.JagCell;
import us.ihmc.jagconnectpg.repository.JagCellRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class JagCellController {
    @Autowired
    private JagCellRepository jagCellRepository;

    @GetMapping("/jagCells")
    public List<JagCell> getAllJagCells() {
        return jagCellRepository.findAll();
    }

    @GetMapping("/jagCells/{id}")
    public ResponseEntity<JagCell> getJagCellById(@PathVariable(value = "id") String jagCellId)
            throws ResourceNotFoundException {
        JagCell jagCell = jagCellRepository.findById(jagCellId)
                .orElseThrow(() -> new ResourceNotFoundException("JagCell not found for this id :: " + jagCellId));
        return ResponseEntity.ok().body(jagCell);
    }

    @PostMapping("/jagCells")
    public JagCell createJagCell(@Valid @RequestBody JagCell jagCell) {
        return jagCellRepository.save(jagCell);
    }

    @PutMapping("/jagCells/{id}")
    public ResponseEntity<JagCell> updateJagCell(@PathVariable(value = "id") String jagCellId,
                                                   @Valid @RequestBody JagCell jagCellDetails) throws ResourceNotFoundException {
        JagCell jagCell = jagCellRepository.findById(jagCellId)
                .orElseThrow(() -> new ResourceNotFoundException("JagCell not found for this id :: " + jagCellId));

        jagCell.setId(jagCellDetails.getId());
        jagCell.setJagUrn(jagCellDetails.getJagUrn());
        jagCell.setLinkStatus(jagCellDetails.getLinkStatus());
        jagCell.setColor(jagCellDetails.getColor());
        jagCell.setCollapsed(jagCellDetails.getCollapsed());
        jagCell.setLength(jagCellDetails.getLength());
        jagCell.setChildren(jagCellDetails.getChildren());
        final JagCell updatedJagCell = jagCellRepository.save(jagCell);
        return ResponseEntity.ok(updatedJagCell);
    }

    @DeleteMapping("/jagCells/{id}")
    public Map<String, Boolean> deleteJagCell(@PathVariable(value = "id") String jagCellId)
            throws ResourceNotFoundException {
        JagCell jagCell = jagCellRepository.findById(jagCellId)
                .orElseThrow(() -> new ResourceNotFoundException("JagCell not found for this id :: " + jagCellId));

        jagCellRepository.delete(jagCell);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}