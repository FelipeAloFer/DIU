package Agenda.modelo;

import Agenda.modelo.repository.PersonaRepository;
import Agenda.util.Persona;
import Agenda.util.PersonaUtil;


import java.util.ArrayList;

public class AgendaModelo {
    PersonaRepository personaRepository;

    public AgendaModelo() {
    }

    public void setAgendaModelo(PersonaRepository implementacion) throws ExcepcionAgenda {
        this.personaRepository = implementacion;
    }

    public ArrayList<Persona> setPersonas() throws ExcepcionAgenda {
        ArrayList<PersonaVO> personasVO = this.personaRepository.obtenerListaPersonas();
        return PersonaUtil.conversion(personasVO);
    }
}
