package apifestivos.apifestivos.aplicacion;

import java.util.List;

import org.springframework.stereotype.Service;

import apifestivos.apifestivos.core.entidades.Tipo;
import apifestivos.apifestivos.core.interfaces.servicios.ITipoServicio;

@Service
public class TipoServicio implements ITipoServicio {

  @Override
  public List<Tipo> listar() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'listar'");
  }

  @Override
  public Tipo obtener(int id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'obtener'");
  }
  
}
