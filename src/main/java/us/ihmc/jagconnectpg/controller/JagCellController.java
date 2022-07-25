package us.ihmc.jagconnectpg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import us.ihmc.jagconnectpg.exception.ResourceNotFoundException;
import us.ihmc.jagconnectpg.model.JagCell;
import us.ihmc.jagconnectpg.repository.JagCellRepository;

import javax.transaction.SystemException;
import javax.validation.Valid;
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
        JagCell newJagCell = new JagCell();
        try {
            System.out.println(jagCellDetails.toString());

            System.out.println("____________________________________");
            newJagCell.setId(jagCellDetails.getId());
            System.out.println(jagCellDetails.getId());

            newJagCell.setUrn(jagCellDetails.getUrn());
            System.out.println(jagCellDetails.getUrn());

            newJagCell.setChildId(jagCellDetails.getChildId());
            System.out.println(jagCellDetails.getChildId());

            newJagCell.setParentId(jagCellDetails.getParentId());
            System.out.println(jagCellDetails.getParentId());

            newJagCell.setProjectId(jagCellDetails.getProjectId());
            System.out.println(jagCellDetails.getProjectId());

            newJagCell.setExpanded(jagCellDetails.getExpanded());
            System.out.println(jagCellDetails.getExpanded());

            newJagCell.setLocked(jagCellDetails.getLocked());
            System.out.println(jagCellDetails.getLocked());

            newJagCell.setContextualName(jagCellDetails.getContextualName());
            System.out.println(jagCellDetails.getContextualName());

            newJagCell.setContextualDescription(jagCellDetails.getContextualDescription());
            System.out.println(jagCellDetails.getContextualDescription());

            newJagCell.setX(jagCellDetails.getX());
            System.out.println(jagCellDetails.getX());
            newJagCell.setY(jagCellDetails.getY());
            System.out.println(jagCellDetails.getY());

            newJagCell.setSubscriptions(jagCellDetails.getSubscriptions());
            System.out.println(jagCellDetails.getSubscriptions());

            newJagCell.setReturnValue(jagCellDetails.getReturnValue());
            System.out.println(jagCellDetails.getReturnValue());
            newJagCell.setReturnState(jagCellDetails.getReturnState());
            System.out.println(jagCellDetails.getReturnState());

            newJagCell.setTestReturnState(jagCellDetails.getTestReturnState());
            System.out.println(jagCellDetails.getTestReturnState());
            newJagCell.setTestReturnValue(jagCellDetails.getTestReturnValue());
            System.out.println(jagCellDetails.getTestReturnValue());


            newJagCell.setChildren(jagCellDetails.getChildren());
            System.out.println(jagCellDetails.getChildren());

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return jagCellRepository.save(newJagCell);
    }



    @PutMapping("/jagCells/{id}")
    public ResponseEntity<JagCell> updateJagCell(@PathVariable(value = "id") String jagCellId,
                                                   @Valid @RequestBody JagCell jagCellDetails) throws ResourceNotFoundException {
        JagCell jagCell = jagCellRepository.findById(jagCellId)
                .orElseThrow(() -> new ResourceNotFoundException("JagCell not found for this id :: " + jagCellId));

        JagCell newJagCell = new JagCell();

        newJagCell.setId(jagCellDetails.getId());

        newJagCell.setUrn(jagCellDetails.getUrn());
        newJagCell.setChildId(jagCellDetails.getChildId());
        newJagCell.setProjectId(jagCellDetails.getProjectId());
        newJagCell.setParentId(jagCellDetails.getParentId());
        newJagCell.setExpanded(jagCellDetails.getExpanded());
        newJagCell.setLocked(jagCellDetails.getLocked());
        newJagCell.setContextualName(jagCellDetails.getContextualName());
        newJagCell.setContextualDescription(jagCellDetails.getContextualDescription());
        newJagCell.setX(jagCellDetails.getX());
        newJagCell.setY(jagCellDetails.getY());
        newJagCell.setSubscriptions(jagCellDetails.getSubscriptions());
        newJagCell.setReturnValue(jagCellDetails.getReturnValue());
        newJagCell.setReturnState(jagCellDetails.getReturnState());
        newJagCell.setTestReturnState(jagCellDetails.getTestReturnState());
        newJagCell.setTestReturnValue(jagCellDetails.getTestReturnValue());
        newJagCell.setChildren(jagCellDetails.getChildren());

        final JagCell updatedJagCell = jagCellRepository.save(newJagCell);
        return ResponseEntity.ok(updatedJagCell);
    }


//    private JagCell setChild(JagCell jagCellDetails){
//
//        JagCell newJagCell = new JagCell();
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
//
//
//        List<JagCell> childrenList = new ArrayList<>();
//        for (JagCell jagCellChild : jagCellDetails.getChildren()) {
//            JagCell newJagCellChild = setChild(jagCellChild);
//            newJagCellChild.setJagCell(jagCellDetails);
//            childrenList.add(newJagCellChild);
//        }
//        newJagCell.setChildren(childrenList);
//
//        return newJagCell;
//    }




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