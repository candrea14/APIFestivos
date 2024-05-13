package apifestivos.apifestivos.core.entidades;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tipo {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_tipo")
  @GenericGenerator(name = "secuencia_tipo", strategy = "increment")
  @Column(name = "id")
  private int id;

  @Column(name = "tipo", length = 100, unique = true)
  private String tipoFestivo;

  public int getId() {
    return id;
  }

  public Tipo() {
  }

  public Tipo(int id, String tipoFestivo) {
    this.id = id;
    this.tipoFestivo = tipoFestivo;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTipoFestivo() {
    return tipoFestivo;
  }

  public void setTipoFestivo(String tipoFestivo) {
    this.tipoFestivo = tipoFestivo;
  }

}
