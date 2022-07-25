package us.ihmc.jagconnectpg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import us.ihmc.jagconnectpg.exception.ResourceNotFoundException;
import us.ihmc.jagconnectpg.model.JagCell;
import us.ihmc.jagconnectpg.repository.JagCellRepository;

import javax.transaction.SystemException;
import javax.validation.Valid;
import java.util.*;

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
    public JagCell createJagCell(@Valid @RequestBody JagCell jagCellDetails) {
        return saveTree(jagCellDetails);
    }



    @PutMapping("/jagCells/{id}")
    public ResponseEntity<JagCell> updateJagCell(@PathVariable(value = "id") String jagCellId,
                                                   @Valid @RequestBody JagCell jagCellDetails) throws ResourceNotFoundException {
        final JagCell updatedJagCell = saveTree(jagCellDetails);
        return ResponseEntity.ok(updatedJagCell);
    }


    private JagCell saveTree(JagCell currentRoot){
        Stack<JagCell> workStack = new Stack();
        workStack.push(currentRoot);
        while (!workStack.empty()) {
            JagCell workRoot = workStack.pop();
            jagCellRepository.save(workRoot);
            for (JagCell child : workRoot.getChildren()) {
                workStack.push(child);
            }
        }
        return currentRoot;
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


    @DeleteMapping("/jagCells")
    public Map<String, Boolean> deleteJagCell() {
        jagCellRepository.deleteAll();
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}