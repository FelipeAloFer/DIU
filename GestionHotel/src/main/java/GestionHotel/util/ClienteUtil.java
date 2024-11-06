package GestionHotel.util;

import GestionHotel.modelo.ClienteVO;

import java.util.ArrayList;

public class ClienteUtil {
    static ArrayList<Cliente> personas = new ArrayList<>();

    public  ClienteUtil(){

    }

    public static ArrayList<Cliente> conversion(ArrayList<ClienteVO> personasVO) {
        for (ClienteVO persona : personasVO) {
            personas.add(new Cliente(persona.getCodigoPersona(), persona.getNombre(), persona.getApellido(), persona.getCalle(), persona.getCiudad(), persona.getCodigoPostal(), persona.getFechaNacimiento()));
        }
        return personas;
    }
}
