package br.edu.ulbra.election.party.service;

import br.edu.ulbra.election.party.exception.GenericOutputException;
import br.edu.ulbra.election.party.input.v1.PartyInput;
import br.edu.ulbra.election.party.model.Party;
import br.edu.ulbra.election.party.output.v1.GenericOutput;
import br.edu.ulbra.election.party.output.v1.PartyOutput;
import br.edu.ulbra.election.party.repository.PartyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.edu.ulbra.election.party.util.ValidatePartyInput.*;

@Service
public class PartyService {

    private final PartyRepository partyRepository;

    private final ModelMapper modelMapper;

    private static final String MESSAGE_INVALID_ID = "Invalid id";
    private static final String MESSAGE_PARTY_NOT_FOUND = "Party not found";

    @Autowired
    public PartyService(PartyRepository partyRepository, ModelMapper modelMapper){
        this.partyRepository = partyRepository;
        this.modelMapper = modelMapper;
    }

    public List<PartyOutput> getAll(){
        List<Party> partyList = (List<Party>)partyRepository.findAll();
        return partyList.stream().map(Party::toPartyOutput).collect(Collectors.toList());
    }

    public PartyOutput create(PartyInput partyInput) {
        validateInput(partyInput, false);
        validatePartyName(partyInput);
        validatePartyNumber(partyInput);
        validateSamePartyCode(partyInput, null);
        validateSamePartyNumber(partyInput, null);
        Party party = modelMapper.map(partyInput, Party.class);
        party = partyRepository.save(party);
        return Party.toPartyOutput(party);
    }

    public PartyOutput getById(Long partyId){
        if (partyId == null){
            throw new GenericOutputException(MESSAGE_INVALID_ID);
        }

        Party party = partyRepository.findById(partyId).orElse(null);
        if (party == null){
            throw new GenericOutputException(MESSAGE_PARTY_NOT_FOUND);
        }

        return modelMapper.map(party, PartyOutput.class);
    }

    public PartyOutput update(Long partyId, PartyInput partyInput) {
        if (partyId == null){
            throw new GenericOutputException(MESSAGE_INVALID_ID);
        }
        validateInput(partyInput, true);
        validatePartyName(partyInput);
        validatePartyNumber(partyInput);
        validateSamePartyCode(partyInput, partyId);
        validateSamePartyNumber(partyInput, partyId);


        Party party = partyRepository.findById(partyId).orElse(null);
        if (party == null){
            throw new GenericOutputException(MESSAGE_PARTY_NOT_FOUND);
        }

        party.setName(partyInput.getName());
        party.setCode(partyInput.getCode());
        party.setNumber(partyInput.getNumber());

        party = partyRepository.save(party);
        return modelMapper.map(party, PartyOutput.class);
    }

    public GenericOutput delete(Long partyId) {
        if (partyId == null){
            throw new GenericOutputException(MESSAGE_INVALID_ID);
        }

        Party party = partyRepository.findById(partyId).orElse(null);
        if (party == null){
            throw new GenericOutputException(MESSAGE_PARTY_NOT_FOUND);
        }

        partyRepository.delete(party);

        return new GenericOutput("Party deleted");
    }
}


