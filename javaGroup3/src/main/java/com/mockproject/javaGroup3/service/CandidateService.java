package com.mockproject.javaGroup3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockproject.javaGroup3.model.Candidate;
import com.mockproject.javaGroup3.repository.CandidateRepository;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;

    @Autowired
    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    // Lưu candidate
    public Candidate saveCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    // Tìm tất cả candidates
    public List<Candidate> findAllCandidates() {
        return candidateRepository.findAll();
    }

    // Tìm candidate theo ID
    public Optional<Candidate> findCandidateById(Integer id) {
        return candidateRepository.findById(id);
    }

    // Cập nhật thông tin candidate
    public Optional<Candidate> updateCandidate(Integer id, Candidate candidateDetails) {
        return candidateRepository.findById(id).map(candidate -> {
            candidate.setFirstname(candidateDetails.getFirstname());
            candidate.setLastname(candidateDetails.getLastname());
            candidate.setEmail(candidateDetails.getEmail());
            candidate.setPhone(candidateDetails.getPhone());
            candidate.setDob(candidateDetails.getDob());
            candidate.setEducation(candidateDetails.getEducation());
            candidate.setExperience(candidateDetails.getExperience());
            candidate.setSkills(candidateDetails.getSkills());
            candidate.setStatus(candidateDetails.getStatus());
            candidate.setCv_image_url(candidateDetails.getCv_image_url());
            return candidateRepository.save(candidate);
        });
    }

    // Xóa candidate theo ID
    public void deleteCandidate(Integer id) {
        candidateRepository.deleteById(id);
    }
}


