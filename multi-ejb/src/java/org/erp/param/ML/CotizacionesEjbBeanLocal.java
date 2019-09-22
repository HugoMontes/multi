package org.erp.param.ML;

import java.util.List;
import javax.ejb.Local;
import org.erp.entities.CotizacionMineral;
import org.erp.entities.CotizacionMineralDesc;
import org.erp.util.AdminException;

@Local
public abstract interface CotizacionesEjbBeanLocal
{
  public abstract List<CotizacionMineral> listaCotizaciones()
    throws AdminException;
  
  public abstract List<CotizacionMineralDesc> listaDescripcion(Integer paramInteger)
    throws AdminException;
  
  public abstract void guardarCotizacionMineral(CotizacionMineral paramCotizacionMineral)
    throws AdminException;
  
  public abstract void eliminarCotizacionDescripcion(CotizacionMineralDesc paramCotizacionMineralDesc)
    throws AdminException;
  
  public abstract void eliminarCotizacion(CotizacionMineral paramCotizacionMineral)
    throws AdminException;
  
  public abstract void guardarCotizacionDescripcion(CotizacionMineralDesc paramCotizacionMineralDesc)
    throws AdminException;
}


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\param\ML\CotizacionesEjbBeanLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */