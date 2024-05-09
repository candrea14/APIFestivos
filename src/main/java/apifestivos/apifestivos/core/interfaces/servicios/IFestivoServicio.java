package apifestivos.apifestivos.core.interfaces.servicios;

import java.util.List;

import apifestivos.apifestivos.core.entidades.Festivo;

public interface IFestivoServicio {

  public List<Festivo> listar();

  public Festivo obtener(int dia, int mes, int año);

  public String verificar(int dia, int mes, int año);

}
