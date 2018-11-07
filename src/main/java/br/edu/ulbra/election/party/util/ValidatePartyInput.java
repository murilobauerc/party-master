package br.edu.ulbra.election.party.util;

import br.edu.ulbra.election.party.exception.GenericOutputException;
import br.edu.ulbra.election.party.input.v1.PartyInput;
import br.edu.ulbra.election.party.model.Party;
import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatePartyInput {
    private ValidatePartyInput() {}

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
     * Throw an generic exception if the party name does not contain at least 5 letters.
     * @param partyInput  object which case it is the name to be searched.
     * @throws GenericOutputException if name goes wrong.
     */
    public static void validatePartyName(PartyInput partyInput){
        if(trimInsideOut(partyInput.getName()).length() < 5)  {
            throw new GenericOutputException("The name must have at least 5 letters.");
        }
    }

    public static void validatePartyCode(PartyInput partyInput, boolean isUpdate){
        // fazer logica a partir daqui
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
