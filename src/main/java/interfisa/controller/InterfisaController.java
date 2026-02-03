package interfisa.controller;

import interfisa.DTO.ParametroDTO;
import interfisa.DTO.UbicacionResponseDTO;
import interfisa.service.InterfisaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@Path("/api")
public class InterfisaController {

    @Inject
    @RestClient
    InterfisaService interfisaService;

    @ConfigProperty(name = "interfisa.jwt.token")
    String jwtToken;

    @ConfigProperty(name = "interfisa.jwt.portador")
    String portador;

    @ConfigProperty(name = "interfisa.sucursales")
    String surcursales;

    @ConfigProperty(name = "interfisa.parametros")
    String motivoSipap;


    @GET
    @Path("/sucursales")
    public UbicacionResponseDTO getSucursalesInterfisa(){
        return interfisaService.getUbicaciones(surcursales);
    }

    @GET
    @Path("/sipap")
    public List<ParametroDTO> getParametrosSipapInterfisa(){
        String authHeader = portador + " " + jwtToken;
        return interfisaService.getParametros(motivoSipap,authHeader);
    }
}