package br.edu.ulbra.election.party.model;

import br.edu.ulbra.election.party.output.v1.PartyOutput;
import org.modelmapper.ModelMapper;

import javax.persistence.*;

@Entity
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(unique = true, nullable = false)
    private Integer number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public static PartyOutput toPartyOutput(Party party) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(party, PartyOutput.class);
    }

}


