package apifestivos.apifestivos.aplicacion;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apifestivos.apifestivos.core.entidades.Festivo;
import apifestivos.apifestivos.core.interfaces.repositorios.IFestivoRepositorio;
import apifestivos.apifestivos.core.interfaces.servicios.IFestivoServicio;

@Service
public class FestivoServicio implements IFestivoServicio {

  @Autowired
  IFestivoRepositorio repositorio;

 public static Date getDomingoPascuas(int año) {
    int a = año % 19;
    int b = año % 4;
    int c = año % 7;
    int d = año/100;
    int e = (13+(8*d))/25;
    int m = d/4;
    int n = (15-e+d-m)%30;
    int p = (4+d-m)%7;
    int q = ((19*a)+n)%30;
    int r = ((2*b)+(4*c)+(6*q)+p)%7;

    int dia;
    int mes;

    if ((q+r) <= 9) {
      dia = (q+r)+22;
      mes = 3;
    }
    else {
      dia = (q+r)-9;
      mes = 4;
    }

    return new Date(año-1900, mes - 1, dia);

  }

  public static Date agregarDias(Date fecha, int dias) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(fecha);
    calendar.add(Calendar.DATE, dias);
    return calendar.getTime();
  }

  public static Date siguienteLunes(Date fecha) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(fecha);

    if (calendar.get(Calendar.DAY_OF_WEEK) > Calendar.MONDAY) {
      fecha = agregarDias(fecha, 9 - calendar.get(Calendar.DAY_OF_WEEK));
    } else {
      fecha = agregarDias(fecha, 1);
    }
    return fecha;
  }

  private List<Festivo> calcularFestivos(List<Festivo> festivos, int año) {
    if (festivos != null) {
        Date pascua = getDomingoPascuas(año);
        int i = 0;
        for (final Festivo festivo : festivos) {
            switch (festivo.getTipo().getId()) {
                case 1:
                    festivo.setFecha(new Date(año - 1900, festivo.getMes() - 1, festivo.getDia()));
                    break;
                case 2:
                    festivo.setFecha(siguienteLunes(new Date(año - 1900, festivo.getMes() - 1, festivo.getDia())));
                    break;
                case 3:
                    festivo.setFecha(agregarDias(pascua, festivo.getDiasPascua()));
                    break;
                case 4:
                    festivo.setFecha(siguienteLunes(agregarDias(pascua, festivo.getDiasPascua())));
                    break;
            }
            festivos.set(i, festivo);
            i++;
        }
    }
    return festivos;
}

  @Override
  public List<Festivo> listar() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'listar'");
  }

  @Override
  public Festivo obtener(int dia, int mes, int año) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'obtener'");
  }

  @Override
  public String verificar(int dia, int mes, int año) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'verificar'");
  }

}
