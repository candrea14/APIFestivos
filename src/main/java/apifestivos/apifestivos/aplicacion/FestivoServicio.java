package apifestivos.apifestivos.aplicacion;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

  @SuppressWarnings("deprecation")
  public static Date obtenerDomingoPascua(int año) {
    int a = año % 19;
    int b = año % 4;
    int c = año % 7;
    int d = año / 100;
    int e = (13 + (8 * d)) / 25;
    int m = d / 4;
    int n = (15 - e + d - m) % 30;
    int p = (4 + d - m) % 7;
    int q = ((19 * a) + n) % 30;
    int r = ((2 * b) + (4 * c) + (6 * q) + p) % 7;

    int dia;
    int mes;

    if ((q + r) <= 9) {
      dia = (q + r) + 22;
      mes = 3;
    } else {
      dia = (q + r) - 9;
      mes = 4;
    }

    return new Date(año - 1900, mes - 1, dia);

  }

  private Date agregarDias(Date fecha, int dias) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(fecha);
    calendar.add(Calendar.DATE, dias);
    System.out.println(calendar.getTime());
    return calendar.getTime();
  }

  private Date siguienteLunes(Date fecha) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(fecha);
    if (calendar.get(Calendar.DAY_OF_WEEK) > Calendar.MONDAY)
      fecha = agregarDias(fecha, 9 - calendar.get(Calendar.DAY_OF_WEEK));
    else if (calendar.get(Calendar.DAY_OF_WEEK) < Calendar.MONDAY)
      fecha = agregarDias(fecha, 1);
    return fecha;
  }

  @SuppressWarnings("deprecation")
  private List<Festivo> calcularFestivos(List<Festivo> festivos, int año) {
    if (festivos != null) {
      Date pascua = obtenerDomingoPascua(año);
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

  public List<Date> obtenerFestivos(int año) {
    List<Festivo> festivos = repositorio.findAll();
    festivos = calcularFestivos(festivos, año);
    List<Date> fechas = new ArrayList<Date>();
    for (final Festivo festivo : festivos) {
      fechas.add(festivo.getFecha());
    }
    return fechas;
  }

  @SuppressWarnings("deprecation")
  private boolean fechasIguales(Date fecha1, Date fecha2) {
    return fecha1.getYear() == fecha2.getYear() && fecha1.getMonth() == fecha2.getMonth()
        && fecha1.getDay() == fecha2.getDay();
  }

  @SuppressWarnings("deprecation")
  private boolean esFestivo(List<Festivo> festivos, Date fecha) {
    if (festivos != null) {
      festivos = calcularFestivos(festivos, fecha.getYear() + 1900);
      for (final Festivo festivo : festivos) {
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        if (fechasIguales(festivo.getFecha(), fecha) || c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)
          return true;
      }
    }
    return false;
  }

  @Override
  public boolean esFestivo(Date fecha) {
    List<Festivo> festivos = repositorio.findAll();
    return esFestivo(festivos, fecha);
  }

  @Override
  public boolean esFechaValida(String strFecha) {
    try {
      DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      df.setLenient(false);
      df.parse(strFecha);
      return true;
    } catch (ParseException e) {
      return false;
    }
  }
}
