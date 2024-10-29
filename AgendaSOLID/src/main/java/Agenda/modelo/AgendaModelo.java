package Agenda.modelo;

import Agenda.modelo.repository.PersonaRepository;
import Agenda.util.Persona;
import Agenda.util.PersonaUtil;


import java.util.ArrayList;

public class AgendaModelo {
    PersonaRepository personaRepository;

    public AgendaModelo() {
    }

    public void setAgendaModelo(PersonaRepository implementacion) {
        this.personaRepository = implementacion;
    }

    public ArrayList<Persona> setPersonas() throws ExcepcionAgenda {
        ArrayList<PersonaVO> personasVO = this.personaRepository.obtenerListaPersonas();
        return PersonaUtil.conversion(personasVO);
    }

    public void añadirPersona (Persona personaNueva) throws ExcepcionAgenda {
        PersonaVO personaNuevaVO = PersonaUtil.conversionVO(personaNueva);
        // Añadir a la base de datos
        personaRepository.addPersona(personaNuevaVO);
    }

    public void borrarPersona(Persona selectedPerson) throws ExcepcionAgenda {
        //Borrar persona de la base de datos
        personaRepository.deletePersona(selectedPerson.getCodigoPersona());
    }

    public void modificarPersona(Persona selectedPerson) throws ExcepcionAgenda {
        PersonaVO personaSelectedVO = PersonaUtil.conversionVO(selectedPerson);
        // Actualizamos en la base de datos
         personaRepository.editPersona(personaSelectedVO);
    }
}
