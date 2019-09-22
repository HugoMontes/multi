package org.erp.seg.ML.seg;

import javax.ejb.Local;
import org.erp.entities.SegRol;
import org.erp.util.AdminException;

@Local
public abstract interface SegRolMLBeanLocal
{
  public abstract void guardarRol(SegRol paramSegRol)
    throws AdminException;
  
  public abstract void eliminarRol(SegRol paramSegRol)
    throws AdminException;
}


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\seg\ML\seg\SegRolMLBeanLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */