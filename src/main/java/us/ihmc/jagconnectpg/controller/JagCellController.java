package us.ihmc.jagconnectpg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import us.ihmc.jagconnectpg.exception.ResourceNotFoundException;
import us.ihmc.jagconnectpg.model.JagActivityChild;
import us.ihmc.jagconnectpg.model.JagCell;
import us.ihmc.jagconnectpg.repository.JagCellRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        System.out.println("c");
        System.out.println(jagCellDetails.toString());
        System.out.println("c");
        System.out.println("c");

//        JagCell newJagCell = new JagCell();
//
//        newJagCell.setId(jagCellDetails.getId());
//        newJagCell.setUrn(jagCellDetails.getUrn());
//        newJagCell.setChildId(jagCellDetails.getChildId());
//        newJagCell.setParentId(jagCellDetails.getParentId());
//        newJagCell.setProjectId(jagCellDetails.getProjectId());
//
//        newJagCell.setExpanded(jagCellDetails.getExpanded());
//        newJagCell.setLocked(jagCellDetails.getLocked());
//        newJagCell.setContextualName(jagCellDetails.getContextualName());
//        newJagCell.setContextualDescription(jagCellDetails.getContextualDescription());
//        newJagCell.setX(jagCellDetails.getX());
//        newJagCell.setY(jagCellDetails.getY());
//        newJagCell.setSubscriptions(jagCellDetails.getSubscriptions());
//        newJagCell.setReturnValue(jagCellDetails.getReturnValue());
//        newJagCell.setReturnState(jagCellDetails.getReturnState());
//        newJagCell.setTestReturnValue(jagCellDetails.getTestReturnValue());
//        newJagCell.setTestReturnState(jagCellDetails.getTestReturnState());
//        newJagCell.setChildren(jagCellDetails.getChildren());

        JagCell newJagCell = setChild(jagCellDetails);

        return jagCellRepository.save(newJagCell);
    }

    @PutMapping("/jagCells/{id}")
    public ResponseEntity<JagCell> updateJagCell(@PathVariable(value = "id") String jagCellId,
                                                   @Valid @RequestBody JagCell jagCellDetails) throws ResourceNotFoundException {
        JagCell jagCell = jagCellRepository.findById(jagCellId)
                .orElseThrow(() -> new ResourceNotFoundException("JagCell not found for this id :: " + jagCellId));


        System.out.println("u");
        System.out.println(jagCellDetails.toString());
        System.out.println("u");


        JagCell newJagCell = setChild(jagCellDetails);

        final JagCell updatedJagCell = jagCellRepository.save(newJagCell);
        return ResponseEntity.ok(updatedJagCell);
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
        List<JagCell> children = new ArrayList<>();

        for (JagCell jagCellChild : jagCellDetails.getChildren()) {
            System.out.println("..");
            System.out.println(jagCellChild.getUrn());
            JagCell newJagCellChild = setChild(jagCellChild);
            children.add(newJagCellChild);
        }
        newJagCell.setChildren(children);

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