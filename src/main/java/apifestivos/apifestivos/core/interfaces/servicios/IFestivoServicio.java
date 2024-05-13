package apifestivos.apifestivos.core.interfaces.servicios;

import java.util.Date;
import java.util.List;

public interface IFestivoServicio {
  public boolean esFestivo(Date Fecha);

  public List<Date> obtenerFestivos(int año);

  public boolean esFechaValida(String strFecha);
}
