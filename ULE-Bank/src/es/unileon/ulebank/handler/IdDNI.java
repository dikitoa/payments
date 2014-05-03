package es.unileon.ulebank.handler;

import es.unileon.ulebank.exceptions.MalformedHandlerException;


public class IdDNI implements Handler {
	private static final String NIF_STRING_ASOCIATION = "TRWAGMYFPDXBNJZSQVHLCKE";

    private char letter;
    private int dni;

    public IdDNI(Integer dni, Character letter) {
        boolean correct = true;
        StringBuilder error = new StringBuilder();

        if (dni == null || letter == null) {
            correct = false;
            error.append("The number or letter can't be null\n");
        } else if (dni < 0) {
            correct = false;
            error.append("The number of dni must be non negative.\n");
        } else {

            correct = correct & checkDNI(dni, letter);
        }
        if (correct) {
            this.dni = dni;
            this.letter = letter;
        } else {
            throw new MalformedHandlerException("Bad DNI: ".concat(error.toString()));
        }
    }

    private static boolean checkDNI(Integer dni, Character letter) {
        boolean correct = true;
        StringBuilder error = new StringBuilder();

        if (dni == null || letter == null) {
            correct = false;
            error.append("The number or letter can't be null");
        } else if (dni < 0) {
            correct = false;
            error.append("The number of dni must be non negative.\n");
        } else {

            if (Character.toString(letter).compareToIgnoreCase(
                    Character.toString(charDNI(dni))) != 0) {
                correct = false;
            }
            
            if(dni.toString().length()>8){
                correct=false;
                error.append("The number is too long. Check again");
            }
        }

        if (correct) {
            return correct;
        } else {
            throw new MalformedHandlerException("Bad DNI: ".concat(error.toString()));
        }
    }

    public IdDNI(String dni) {
        boolean correct = true;
        StringBuilder error = new StringBuilder();

        if (dni == null) {
            error.append("The dni can't be null");
            throw new MalformedHandlerException("Bad DNI: ".concat(error.toString()));

        } else if (dni.length() < 2) {
            correct = false;
            error.append("The DNI must have at least one number and the letter");
        } else {
            Integer dniNumber = Integer.parseInt(dni.substring(0, dni.length() - 1));
            Character letterDni = dni.charAt(dni.length() - 1);

            if (checkDNI(dniNumber, letterDni)) {
                this.dni = dniNumber;
                this.letter = letterDni;
            }

        }

        if (!correct) {
            throw new MalformedHandlerException("Bad DNI: ".concat(error.toString()));
        }
    }

    private static char charDNI(int dni) {
        return NIF_STRING_ASOCIATION.charAt(dni % 23);
    }

    @Override
    public int compareTo(Handler another) {
        return this.toString().compareTo(another.toString());
    }

    @Override
    public String toString() {
        String resultado;
        resultado = Integer.toString(dni);
        return resultado.concat(Character.toString(letter));
    }

    /**
     * return the number of dni
     *
     * @return the number of dni
     */
    public int getNif() {
        return dni;
    }

    /**
     * Return the letter of the dni
     *
     * @return the letter of the dni
     */
    public char getLetter() {
        return letter;
    }
}
