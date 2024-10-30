package Agenda.util;

import Agenda.modelo.PersonaVO;
import java.util.ArrayList;

public class PersonaUtil {
    static ArrayList<Persona> personas = new ArrayList<>();

    public  PersonaUtil(){

    }

    public static ArrayList<Persona> conversion(ArrayList<PersonaVO> personasVO) {
        for (PersonaVO persona : personasVO) {
            personas.add(new Persona(persona.getCodigoPersona(), persona.getNombre(), persona.getApellido(), persona.getCalle(), persona.getCiudad(), persona.getCodigoPostal(), persona.getFechaNacimiento()));
        }
        return personas;
    }

    public static PersonaVO conversionVO(Persona persona) {
        PersonaVO personaVO = new PersonaVO(persona.getCodigoPersona(), persona.getNombre(), persona.getApellido(), persona.getCalle(), persona.getCiudad(), persona.getCodigoPostal(), persona.getFechaNacimiento());
        return personaVO;
    }
}
