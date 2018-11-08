package br.edu.ulbra.election.party.util;

import br.edu.ulbra.election.party.exception.GenericOutputException;
import br.edu.ulbra.election.party.input.v1.PartyInput;
import br.edu.ulbra.election.party.model.Party;
import br.edu.ulbra.election.party.repository.PartyRepository;
import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatePartyInput {
    private final PartyRepository partyRepository;

    private ValidatePartyInput(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    public static void validateInput(PartyInput partyInput, boolean isUpdate){

        if (StringUtils.isBlank(partyInput.getName())){
            throw new GenericOutputException("Invalid name");
        }
        if (StringUtils.isBlank(partyInput.getCode())){
            throw new GenericOutputException("Invalid code");
        }
        if (partyInput.getNumber() == null){
            throw new GenericOutputException("Invalid number");
        }
    }

    /**
     * Throw an generic exception if the party name does not contain at least 5 letters
     * @param partyInput  object which case it is the name to be searched
     * @throws GenericOutputException if name goes wrong
     */
    public static void validatePartyName(PartyInput partyInput){
        if(trimInsideOut(partyInput.getName()).length() < 5)  {
            throw new GenericOutputException("The name must have at least 5 letters.");
        }
    }



    /**
     * Throw an generic expression if the party number does not contain at least two digits
     * @param partyInput object which case it is the number to be seached
     * @throws GenericOutputException if number goes wrong
     */

    public static void validatePartyNumber(PartyInput partyInput){
        if(partyInput.getNumber() < 10 || partyInput.getNumber() > 99){
            throw new GenericOutputException("The party number must be at least two digits.");
        }
    }


    /**
     * Removes blank spaces in the beginning and the end
     * Replaces multiple blank spaces,if exists, to a single one
     *
     * @param word any string
     * @return trimmed string and treated at all
     */
    public static String trimInsideOut(String word){
        word = word.trim();
        Pattern pattern = Pattern.compile("\\s+");
        Matcher matcher = pattern.matcher(word);
        word = matcher.replaceAll(" ");

        return word;
    }
}
