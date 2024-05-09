package apifestivos.apifestivos.presentacion;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import apifestivos.apifestivos.core.entidades.Tipo;
import apifestivos.apifestivos.core.interfaces.servicios.ITipoServicio;

@RestController
@RequestMapping("api/tipo")
public class TipoControlador {

  private ITipoServicio servicio;

  private TipoControlador(ITipoServicio servicio) {
    this.servicio = servicio;
  }

  @RequestMapping(value = "/listar", method = RequestMethod.GET)
  public List<Tipo> listar() {
    return servicio.listar();
  }
}
