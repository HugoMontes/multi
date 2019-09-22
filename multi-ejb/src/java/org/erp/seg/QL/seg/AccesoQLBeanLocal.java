package org.erp.seg.QL.seg;

import java.util.List;
import javax.ejb.Local;
import org.erp.entities.SegAcceso;
import org.erp.entities.SegMenu;
import org.erp.entities.SegRol;
import org.erp.entities.SegUsuario;
import org.erp.util.AdminException;

@Local
public abstract interface AccesoQLBeanLocal
{
  public abstract List<SegRol> obtenerListaRoles(SegUsuario paramSegUsuario)
    throws AdminException;
  
  public abstract List<SegMenu> obtenerListaMenu(SegUsuario paramSegUsuario)
    throws AdminException;
  
  public abstract List<SegMenu> obtenerListaMenuItem(SegMenu paramSegMenu)
    throws AdminException;
  
  public abstract List<SegRol> obtenerListaRolesMenu(Integer paramInteger)
    throws AdminException;
  
  public abstract SegUsuario verificarAccesoUsuario(String paramString1, String paramString2)
    throws AdminException;
  
  public abstract SegUsuario verificarCorreo(String paramString)
    throws AdminException;
  
  public abstract void guardarAcceso(SegAcceso paramSegAcceso)
    throws AdminException;
  
  public abstract boolean obtenerListaMenuPorRol(List<Integer> paramList, Integer paramInteger)
    throws AdminException;
}


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\seg\QL\seg\AccesoQLBeanLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */