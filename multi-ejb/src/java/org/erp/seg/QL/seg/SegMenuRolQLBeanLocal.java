package org.erp.seg.QL.seg;

import java.util.List;
import javax.ejb.Local;
import org.erp.entities.SegMenu;
import org.erp.entities.SegMenurol;
import org.erp.entities.SegRol;
import org.erp.util.AdminException;

@Local
public abstract interface SegMenuRolQLBeanLocal
{
  public abstract List<SegMenu> listaMenu()
    throws AdminException;
  
  public abstract List<SegMenurol> listaMenuRol(String paramString)
    throws AdminException;
  
  public abstract List<SegRol> listaRol(String paramString)
    throws AdminException;
  
  public abstract SegRol datosRol(Integer paramInteger)
    throws AdminException;
  
  public abstract SegMenu datosMenu(String paramString)
    throws AdminException;
}


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\seg\QL\seg\SegMenuRolQLBeanLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */