package org.erp.seg.QL.seg;

import java.util.List;
import javax.ejb.Local;
import org.erp.entities.SegRol;
import org.erp.util.AdminException;

@Local
public abstract interface SegRolQLBeanLocal
{
  public abstract List<SegRol> listaRoles()
    throws AdminException;
}


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\seg\QL\seg\SegRolQLBeanLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */