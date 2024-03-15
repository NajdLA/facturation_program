package fr.ekwateur.facturation.shared.exceptions;

public class InvalidClientTypeException extends RuntimeException{

    public InvalidClientTypeException(String message){
        super(message);
    }
}
