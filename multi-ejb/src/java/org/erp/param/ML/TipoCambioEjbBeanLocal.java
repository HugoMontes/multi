package org.erp.param.ML;

import java.util.List;
import javax.ejb.Local;
import org.erp.entities.Pais;
import org.erp.entities.TipoCambio;
import org.erp.util.AdminException;

@Local
public abstract interface TipoCambioEjbBeanLocal
{
  public abstract List<TipoCambio> listaTipoCambio()
    throws AdminException;
  
  public abstract void guardarTipoCambio(TipoCambio paramTipoCambio)
    throws AdminException;
  
  public abstract void eliminarTipoCambio(TipoCambio paramTipoCambio)
    throws AdminException;
  
  public abstract List<Pais> listaPais()
    throws AdminException;
  
  public abstract void guardarPais(Pais paramPais)
    throws AdminException;
  
  public abstract void eliminarPais(Pais paramPais)
    throws AdminException;
}


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\param\ML\TipoCambioEjbBeanLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */