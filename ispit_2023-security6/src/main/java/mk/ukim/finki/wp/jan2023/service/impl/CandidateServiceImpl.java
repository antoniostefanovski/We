package mk.ukim.finki.wp.jan2023.service.impl;

import mk.ukim.finki.wp.jan2023.model.Candidate;
import mk.ukim.finki.wp.jan2023.model.Gender;
import mk.ukim.finki.wp.jan2023.model.Party;
import mk.ukim.finki.wp.jan2023.model.exceptions.InvalidCandidateIdException;
import mk.ukim.finki.wp.jan2023.model.exceptions.InvalidPartyIdException;
import mk.ukim.finki.wp.jan2023.repository.CandidateRepository;
import mk.ukim.finki.wp.jan2023.repository.PartyRepository;
import mk.ukim.finki.wp.jan2023.service.CandidateService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final PartyRepository partyRepository;

    public CandidateServiceImpl(CandidateRepository candidateRepository, PartyRepository partyRepository) {
        this.candidateRepository = candidateRepository;
        this.partyRepository = partyRepository;
    }

    @Override
    public List<Candidate> listAllCandidates() {
        return this.candidateRepository.findAll();
    }

    @Override
    public Candidate findById(Long id) {
        return this.candidateRepository.findById(id).orElseThrow(InvalidCandidateIdException::new);
    }

    @Override
    public Candidate create(String name, String bio, LocalDate dateOfBirth, Gender gender, Long party) {
        Party party1 = this.partyRepository.findById(party).orElseThrow(InvalidPartyIdException::new);

        return this.candidateRepository.save(new Candidate(name, bio, dateOfBirth, gender, party1));
    }

    @Override
    public Candidate update(Long id, String name, String bio, LocalDate dateOfBirth, Gender gender, Long party) {
        Party party1 = this.partyRepository.findById(party).orElseThrow(InvalidPartyIdException::new);
        Candidate candidate = this.findById(id);

        candidate.setName(name);
        candidate.setBio(bio);
        candidate.setDateOfBirth(dateOfBirth);
        candidate.setGender(gender);
        candidate.setParty(party1);

        return this.candidateRepository.save(candidate);
    }

    @Override
    public Candidate delete(Long id) {
        Candidate candidate = this.findById(id);
        this.candidateRepository.delete(candidate);
        return candidate;
    }

    @Override
    public Candidate vote(Long id) {
        Candidate candidate = this.findById(id);
        candidate.setVotes(candidate.getVotes() + 1);
        return this.candidateRepository.save(candidate);
    }

    @Override
    public List<Candidate> listCandidatesYearsMoreThanAndGender(Integer yearsMoreThan, Gender gender) {

        if(yearsMoreThan != null && gender != null) {
            LocalDate date = LocalDate.now().minusYears(yearsMoreThan);
            return this.candidateRepository.findCandidatesByDateOfBirthBeforeAndGender(date, gender);
        }

        if(yearsMoreThan != null) {
            LocalDate date = LocalDate.now().minusYears(yearsMoreThan);
            return this.candidateRepository.findCandidatesByDateOfBirthBefore(date);
        }

        if(gender != null) {
            return this.candidateRepository.findCandidatesByGender(gender);
        }
        return this.candidateRepository.findAll();
    }
}
