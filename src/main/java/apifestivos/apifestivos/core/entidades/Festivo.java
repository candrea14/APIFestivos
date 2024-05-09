package apifestivos.apifestivos.core.entidades;

import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "festivo")
public class Festivo {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_festivo")
  @GenericGenerator(name = "secuencia_festivo", strategy = "increment")
  @Column(name = "id")
  private int id;

  @Column(name = "dia", length = 10)
  private int dia;

  @Column(name = "mes", length = 10)
  private int mes;

  @Column(name = "año", length = 10)
  private int año;

  @Column(name = "diaspascua")
  private int diasPascua;

  @ManyToOne
  @JoinColumn(name = "idtipo", referencedColumnName = "id")
  private Tipo tipo;

  private Date fecha;

  public Festivo(int id, int dia, int mes, int año, int diasPascua, Tipo tipo) {
    this.id = id;
    this.dia = dia;
    this.mes = mes;
    this.año = año;
    this.diasPascua = diasPascua;
    this.tipo = tipo;
  }

  public Festivo() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getDia() {
    return dia;
  }

  public void setDia(int dia) {
    this.dia = dia;
  }

  public int getMes() {
    return mes;
  }

  public void setMes(int mes) {
    this.mes = mes;
  }

  public int getAño() {
    return año;
  }

  public void setAño(int año) {
    this.año = año;
  }

  public int getDiasPascua() {
    return diasPascua;
  }

  public void setDiasPascua(int diasPascua) {
    this.diasPascua = diasPascua;
  }

  public Tipo getTipo() {
    return tipo;
  }

  public void setTipo(Tipo tipo) {
    this.tipo = tipo;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

}
