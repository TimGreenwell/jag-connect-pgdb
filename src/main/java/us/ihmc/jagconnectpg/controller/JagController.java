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
@RequestMapping(value = "/api/v1")
public class JagController {
    @Autowired
    private JagRepository jagRepository;

    @GetMapping(value = "/jags")
    public List<Node> getAllJags() {
        return jagRepository.findAll();
    }

    @GetMapping(value = "/jags/{id}")
    public ResponseEntity<Node> getJagById(@PathVariable(value = "id") String jagCellId)
            throws ResourceNotFoundException {

        Node node = jagRepository.findById(jagCellId)
                .orElseThrow(() -> new ResourceNotFoundException("JagCell not found for this id :: " + jagCellId));

        return ResponseEntity.ok().body(node);
    }

    @PostMapping(value = "/jags",
            consumes = "application/json")
    public Node createJag(@Valid @RequestBody Node nodeDetails) {
        return saveTree(nodeDetails);
    }



    @PutMapping(value = "/jags/{id}",
            consumes = "application/json")
    public ResponseEntity<Node> updateJag(@PathVariable(value = "id") String jagCellId,
                                              @Valid @RequestBody Node nodeDetails) throws ResourceNotFoundException {
        Node node = jagRepository.findById(jagCellId)
                .orElseThrow(() -> new ResourceNotFoundException("JagCell not found for this id :: " + jagCellId));

        final Node updatedNode = saveTree(nodeDetails);
        return ResponseEntity.ok(updatedNode);
    }


    private Node saveTree(Node currentRoot){
        Stack<Node> workStack = new Stack<>();
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

    @DeleteMapping(value = "/jags/{id}")
    public Map<String, Boolean> deleteJag(@PathVariable(value = "id") String jagCellId)
            throws ResourceNotFoundException {


        Stack<String> workStack = new Stack<>();
        workStack.push(jagCellId);
        while (workStack.size() > 0 ) {
            String currentNode = workStack.pop();
            Node node = jagRepository.findById(currentNode)
                    .orElseThrow(() -> new ResourceNotFoundException("Node not found for this id :: " + jagCellId));
            for (Node child : node.children) {
                workStack.push(child.getId());
            }
            jagRepository.delete(node);

        }
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


    @DeleteMapping(value = "/jags")
    public Map<String, Boolean> deleteJag() {
        jagRepository.deleteAll();
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}