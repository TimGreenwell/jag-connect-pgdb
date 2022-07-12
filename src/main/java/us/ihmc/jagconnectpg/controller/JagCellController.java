package us.ihmc.jagconnectpg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import us.ihmc.jagconnectpg.exception.ResourceNotFoundException;
import us.ihmc.jagconnectpg.model.JagActivityChild;
import us.ihmc.jagconnectpg.model.JagCell;
import us.ihmc.jagconnectpg.repository.JagCellRepository;
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
        JagCell newJagCell = setChild(jagCellDetails);
         //   return saveTree(newJagCell);
        System.out.println("ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
        /////////////////////////////////////
        List<JagCell> allNodes = dbTree(newJagCell);
 //       final List<JagCell> results = jagCellRepository.saveAll(allNodes);
        return newJagCell;
    }

    @PutMapping("/jagCells/{id}")
    public ResponseEntity<JagCell> updateJagCell(@PathVariable(value = "id") String jagCellId,
                                                   @Valid @RequestBody JagCell jagCellDetails) throws ResourceNotFoundException {
       // JagCell jagCell = jagCellRepository.findById(jagCellId)
        //        .orElseThrow(() -> new ResourceNotFoundException("JagCell not found for this id :: " + jagCellId));
  /// the jagCell (above) should be exacly the same as jagCellDetails that is passed in.

        JagCell newJagCell = setChild(jagCellDetails);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
       // final JagCell updatedJagCell = saveTree(newJagCell);
  //      List<JagCell> allNodes = clumpTree(newJagCell);
 //       final List<JagCell> results = jagCellRepository.saveAll(allNodes);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        List<JagCell> allNodes = dbTree(newJagCell);
        System.out.println(allNodes);
        return ResponseEntity.ok(jagCellDetails);
    }

    private JagCell saveTree(JagCell currentRoot){
         var result = jagCellRepository.save(currentRoot);
        for (JagCell child : currentRoot.getChildren()) {
            saveTree(child);
        }
        return result;
    }


    private List<JagCell> dbTree(JagCell rootNode) {
        List<JagCell> clumpList = new ArrayList<>();
        Stack<JagCell> workStack = new Stack<>();
        workStack.push(rootNode);
        while (workStack.size()>0) {
            JagCell currentNode = workStack.pop();
            System.out.println("++++++++++++++++++++++++++++    POP     ++++++++++++++++++++++++++");

            for (JagCell child : currentNode.getChildren()){
                workStack.push(child);
                System.out.println("++++++++++++++++++++++++++++   " + child.getUrn() + "   ++++++++++++++++++++++++++");
            }

            jagCellRepository.save(currentNode);
        }
        return clumpList;
    }


    private List<JagCell> clumpTree(JagCell rootNode) {
        List<JagCell> clumpList = new ArrayList<>();
        Stack<JagCell> workStack = new Stack<>();
        workStack.push(rootNode);
        while (workStack.size()>0) {
            JagCell currentNode = workStack.pop();

            for (JagCell child : currentNode.getChildren()){
                workStack.push(child);
            }
            clumpList.add(currentNode);
        }
        return clumpList;
    }

    private JagCell setChild(JagCell jagCellDetails){

        JagCell newJagCell = new JagCell();
        newJagCell.setId(jagCellDetails.getId());
        newJagCell.setUrn(jagCellDetails.getUrn());
        newJagCell.setChildId(jagCellDetails.getChildId());
        newJagCell.setParentId(jagCellDetails.getParentId());
        newJagCell.setProjectId(jagCellDetails.getProjectId());

        newJagCell.setExpanded(jagCellDetails.getExpanded());
        newJagCell.setLocked(jagCellDetails.getLocked());
        newJagCell.setContextualName(jagCellDetails.getContextualName());
        newJagCell.setContextualDescription(jagCellDetails.getContextualDescription());
        newJagCell.setX(jagCellDetails.getX());
        newJagCell.setY(jagCellDetails.getY());
        newJagCell.setSubscriptions(jagCellDetails.getSubscriptions());
        newJagCell.setReturnValue(jagCellDetails.getReturnValue());
        newJagCell.setReturnState(jagCellDetails.getReturnState());
        newJagCell.setTestReturnValue(jagCellDetails.getTestReturnValue());
        newJagCell.setTestReturnState(jagCellDetails.getTestReturnState());


        List<JagCell> childrenList = new ArrayList<>();
        for (JagCell jagCellChild : jagCellDetails.getChildren()) {
            JagCell newJagCellChild = setChild(jagCellChild);
            newJagCellChild.setJagCell(jagCellDetails);
            childrenList.add(newJagCellChild);
        }
        newJagCell.setChildren(childrenList);

        return newJagCell;
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