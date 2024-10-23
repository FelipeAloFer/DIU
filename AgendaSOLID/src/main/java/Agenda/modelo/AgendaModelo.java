package Agenda.modelo;

import Agenda.modelo.repository.PersonaRepository;

import java.util.ArrayList;

public class AgendaModelo {
    ArrayList<PersonaVO> personas;

    public AgendaModelo() {
    }

    public void setAgendaModelo(PersonaRepository implementacion) throws ExcepcionAgenda {
        this.personas = implementacion.ObtenerListaPersonas();
    }
}
