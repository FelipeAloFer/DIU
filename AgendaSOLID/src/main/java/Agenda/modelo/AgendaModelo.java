package Agenda.modelo;

import Agenda.modelo.repository.PersonaRepository;
import Agenda.util.Persona;
import Agenda.util.PersonaUtil;
import javafx.scene.control.Alert;


import java.sql.SQLException;
import java.util.ArrayList;

public class AgendaModelo {
    PersonaRepository personaRepository;

    public AgendaModelo() {
    }

    public void setAgendaModelo(PersonaRepository implementacion) {
        this.personaRepository = implementacion;
    }

    public ArrayList<Persona> setPersonas() {
        try {
            ArrayList<PersonaVO> personasVO = this.personaRepository.obtenerListaPersonas();
            return PersonaUtil.conversion(personasVO);
        } catch (ExcepcionAgenda e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error de conexión");
            alerta.setHeaderText("La base de datos no está conectada.");
            alerta.setContentText(e.getMessage());
            alerta.showAndWait();
            return null;
        }
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

    public int ultimoID() throws ExcepcionAgenda {
        int ultimoID = personaRepository.lastId();
        return ultimoID;
    }
}
