package GestionHotel.util;

import GestionHotel.modelo.ClienteVO;

import java.util.ArrayList;

public class ClienteUtil {
    static ArrayList<Cliente> personas = new ArrayList<>();

    public  ClienteUtil(){

    }

    public static ArrayList<Cliente> conversion(ArrayList<ClienteVO> personasVO) {
        for (ClienteVO persona : personasVO) {
            personas.add(new Cliente(persona.getDni(), persona.getNombre(), persona.getApellidos(), persona.getDireccion(), persona.getLocalidad(), persona.getProvincia()));
        }
        return personas;
    }

    public static ClienteVO conversionVO(Cliente persona) {
        ClienteVO personaVO = new ClienteVO(persona.getDni(), persona.getNombre(), persona.getApellidos(), persona.getDireccion(), persona.getLocalidad(), persona.getProvincia());
        return personaVO;
    }
}
