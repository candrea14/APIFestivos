package apifestivos.apifestivos.core.interfaces.servicios;

import java.util.List;

import apifestivos.apifestivos.core.entidades.Tipo;

public interface ITipoServicio {
  
  public List<Tipo> listar();

  public Tipo obtener(int id);

}
