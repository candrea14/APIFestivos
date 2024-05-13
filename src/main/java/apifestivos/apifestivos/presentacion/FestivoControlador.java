package apifestivos.apifestivos.presentacion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import apifestivos.apifestivos.core.interfaces.servicios.IFestivoServicio;

@RestController
@RequestMapping("/festivos")
public class FestivoControlador {

  @Autowired
  private IFestivoServicio servicio;

  @RequestMapping(value = "/verificar/{año}/{mes}/{dia}", method = RequestMethod.GET)
  public String verificarFestivo(@PathVariable int año, @PathVariable int mes, @PathVariable int dia) {
    if (servicio.esFechaValida(String.valueOf(año) + "-" + String.valueOf(mes) + "-" + String.valueOf(dia))) {
      @SuppressWarnings("deprecation")
      Date fecha = new Date(año - 1900, mes - 1, dia);
      return servicio.esFestivo(fecha) ? "Es festivo" : "No es festivo";
    } else {
      return "Fecha No Válida";
    }

  }

  @RequestMapping(value = "/obtener/{año}", method = RequestMethod.GET)
  public List<String> obtener(@PathVariable int año) {
    List<Date> lista = servicio.obtenerFestivos(año);
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    List<String> fechasFormateadas = new ArrayList<>();
    for (Date fecha : lista) {
      fechasFormateadas.add(dateFormat.format(fecha));
    }
    return fechasFormateadas;
  }

}
