package org.erp.adm;

import java.util.List;
import javax.ejb.Local;
import org.erp.entities.Actividad;
import org.erp.entities.Ciudad;
import org.erp.entities.ContratoMinero;
import org.erp.entities.Cooperativa;
import org.erp.entities.Departamento;
import org.erp.entities.DerechoMinero;
import org.erp.entities.Mineral;
import org.erp.entities.Municipio;
import org.erp.entities.Pais;
import org.erp.entities.Sucursal;
import org.erp.util.AdminException;

@Local
public abstract interface CooperativaEJBBeanLocal
{
  public abstract List<Cooperativa> listadoCooperativa()
    throws AdminException;
  
  public abstract List<Departamento> listadoDepartamento()
    throws AdminException;
  
  public abstract List<Municipio> listadoMunicipio(Integer paramInteger)
    throws AdminException;
  
  public abstract List<Ciudad> listadoCiudad(Integer paramInteger)
    throws AdminException;
  
  public abstract void guardarCooperativa(Cooperativa paramCooperativa)
    throws AdminException;
  
  public abstract void guardarSucursal(Sucursal paramSucursal)
    throws AdminException;
  
  public abstract List<Sucursal> listadoSucursal(Integer paramInteger)
    throws AdminException;
  
  public abstract void guardarEliminarActividades(Integer paramInteger)
    throws AdminException;
  
  public abstract void guardarActividadCooperativa(Actividad paramActividad)
    throws AdminException;
  
  public abstract List<Actividad> listadoActividadesRegistradas(Integer paramInteger)
    throws AdminException;
  
  public abstract void guardarEliminarMinerales(Integer paramInteger)
    throws AdminException;
  
  public abstract void guardarMinerales(Mineral paramMineral)
    throws AdminException;
  
  public abstract List<Mineral> listadoMinerales(Integer paramInteger1, Integer paramInteger2)
    throws AdminException;
  
  public abstract void guardarDerechoMinero(DerechoMinero paramDerechoMinero)
    throws AdminException;
  
  public abstract List<DerechoMinero> listadoDerechoMinero(Integer paramInteger)
    throws AdminException;
  
  public abstract void guardarContratoMinero(ContratoMinero paramContratoMinero)
    throws AdminException;
  
  public abstract List<ContratoMinero> listadoContratoMinero(Integer paramInteger)
    throws AdminException;
  
  public abstract List<Ciudad> listadoCiudadByDpto(Integer paramInteger)
    throws AdminException;
  
  public abstract List<Pais> listadoPais()
    throws AdminException;
  
  public abstract void eliminarSucursal(Sucursal paramSucursal)
    throws AdminException;
  
  public abstract void eliminarDerechoMinero(DerechoMinero paramDerechoMinero)
    throws AdminException;
  
  public abstract void eliminarContratoMinero(ContratoMinero paramContratoMinero)
    throws AdminException;
  
  public abstract List<Municipio> listadoMunicipioDerMin()
    throws AdminException;
  
  public abstract Departamento departamentoById(Integer paramInteger)
    throws AdminException;
}


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\adm\CooperativaEJBBeanLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */