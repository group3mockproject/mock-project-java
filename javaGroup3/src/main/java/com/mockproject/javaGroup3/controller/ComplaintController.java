package com.mockproject.javaGroup3.controller;

import com.mockproject.javaGroup3.exception.NotFoundException;
import com.mockproject.javaGroup3.entity.Complaint;
import com.mockproject.javaGroup3.repository.ComplaintRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/complaint")
public class ComplaintController {

    private final ComplaintRepository complaintRepository;

    public ComplaintController(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    @GetMapping
    public List<Complaint> getAll() {
        return complaintRepository.findAll();
    }

    @GetMapping("{id}")
    public Complaint getComplaintById(@PathVariable int id) {
        return complaintRepository.findById(id).orElseThrow(() -> new NotFoundException(Complaint.class.getName(), id));
    }

    @PostMapping
    public Complaint addComplaint(@RequestBody Complaint complaint) {
        return complaintRepository.save(complaint);
    }

    @PutMapping("{id}")
    public Complaint updateComplaint(@PathVariable int id, @RequestBody Complaint newComplaint) {
        Complaint complaint = getComplaintById(id);
        BeanUtils.copyProperties(newComplaint, complaint, "id");
        return complaintRepository.save(complaint);
    }

    @DeleteMapping("{id}")
    public void deleteComplaint(@PathVariable int id) {
        Complaint complaint = getComplaintById(id);
        complaintRepository.delete(complaint);
    }
}
