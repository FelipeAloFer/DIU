package Agenda.modelo.repository;

import Agenda.modelo.ExcepcionAgenda;
import Agenda.modelo.PersonaVO;
import Agenda.util.Persona;
import java.util.ArrayList;

public interface PersonaRepository {
    ArrayList<PersonaVO> obtenerListaPersonas() throws ExcepcionAgenda;

    void addPersona(PersonaVO var1) throws ExcepcionAgenda;

    void deletePersona(Integer var1) throws ExcepcionAgenda;

    void editPersona(PersonaVO var1) throws ExcepcionAgenda;

    int lastId() throws ExcepcionAgenda;
}

