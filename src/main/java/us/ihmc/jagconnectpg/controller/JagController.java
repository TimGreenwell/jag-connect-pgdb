package us.ihmc.jagconnectpg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import us.ihmc.jagconnectpg.exception.ResourceNotFoundException;
import us.ihmc.jagconnectpg.model.Node;
import us.ihmc.jagconnectpg.repository.JagRepository;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class JagController {
    @Autowired
    private JagRepository jagRepository;

    @GetMapping("/jags")
    public List<Node> getAllJagCells() {
        return jagRepository.findAll();
    }

    @GetMapping("/jags/{id}")
    public ResponseEntity<Node> getJagCellById(@PathVariable(value = "id") String jagCellId)
            throws ResourceNotFoundException {
        Node node = jagRepository.findById(jagCellId)
                .orElseThrow(() -> new ResourceNotFoundException("JagCell not found for this id :: " + jagCellId));
        return ResponseEntity.ok().body(node);
    }

    @PostMapping("/jags")
    public Node createJagCell(@Valid @RequestBody Node nodeDetails) {
        return saveTree(nodeDetails);
    }



    @PutMapping("/jags/{id}")
    public ResponseEntity<Node> updateJagCell(@PathVariable(value = "id") String jagCellId,
                                              @Valid @RequestBody Node nodeDetails) throws ResourceNotFoundException {
        final Node updatedNode = saveTree(nodeDetails);
        return ResponseEntity.ok(updatedNode);
    }


    private Node saveTree(Node currentRoot){
        Stack<Node> workStack = new Stack();
        workStack.push(currentRoot);
        while (!workStack.empty()) {
            Node workRoot = workStack.pop();
            jagRepository.save(workRoot);
            for (Node child : workRoot.getChildren()) {
                workStack.push(child);
            }
        }
        return currentRoot;
    }

    @DeleteMapping("/jags/{id}")
    public Map<String, Boolean> deleteJagCell(@PathVariable(value = "id") String jagCellId)
            throws ResourceNotFoundException {
        Node node = jagRepository.findById(jagCellId)
                .orElseThrow(() -> new ResourceNotFoundException("JagCell not found for this id :: " + jagCellId));

        jagRepository.delete(node);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


    @DeleteMapping("/jags")
    public Map<String, Boolean> deleteJagCell() {
        jagRepository.deleteAll();
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}