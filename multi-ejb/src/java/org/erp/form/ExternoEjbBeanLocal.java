package org.erp.form;

import java.util.List;
import javax.ejb.Local;
import org.erp.entities.TransporteExterno;
import org.erp.util.AdminException;

@Local
public abstract interface ExternoEjbBeanLocal
{
  public abstract List<TransporteExterno> listadoTransExterno(Integer paramInteger)
    throws AdminException;
  
  public abstract void guardarTransporteExterno(TransporteExterno paramTransporteExterno)
    throws AdminException;
}


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\form\ExternoEjbBeanLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */