package org.erp.seg.ML.seg;

import javax.ejb.Local;
import org.erp.entities.SegMenurol;
import org.erp.util.AdminException;

@Local
public abstract interface SegMenuRolMLBeanLocal
{
  public abstract void guardarMenuRol(SegMenurol paramSegMenurol)
    throws AdminException;
  
  public abstract void eliminarMenuRol(SegMenurol paramSegMenurol)
    throws AdminException;
}


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\seg\ML\seg\SegMenuRolMLBeanLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */