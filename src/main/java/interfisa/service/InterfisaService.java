package interfisa.service;

import interfisa.DTO.ParametroDTO;
import interfisa.DTO.UbicacionResponseDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(configKey = "middleware-api")
@Path("/api")
@ClientHeaderParam(name = "X-INTERFISA-BE-VERSION", value = "${interfisa.api.version}")
public interface InterfisaService {
    @GET
    @Path("/common/centros-servicios")
    UbicacionResponseDTO getUbicaciones(
            @QueryParam("nombreODireccion") String nombreODireccion
    );

    @GET
    @Path("/secure/common/parametros")
    List<ParametroDTO> getParametros(
            @QueryParam("dominio") String dominio,
            @HeaderParam("Authorization") String jwt
    );
}
