package org.erp.seg.QL.seg;

import java.util.List;
import javax.ejb.Local;
import org.erp.entities.SegPermiso;
import org.erp.entities.SegRol;
import org.erp.entities.SegUsuario;
import org.erp.util.AdminException;

@Local
public abstract interface SegUsuarioQLBeanLocal
{
  public abstract List<SegUsuario> listaUsuarios()
    throws AdminException;
  
  public abstract List<SegRol> listarRoles()
    throws AdminException;
  
  public abstract List<SegPermiso> listarPermisos(Integer paramInteger)
    throws AdminException;
  
  public abstract List<SegUsuario> verificarSiUsuario(SegUsuario paramSegUsuario)
    throws AdminException;
  
  public abstract List<SegUsuario> verificarSiExisteSigla(SegUsuario paramSegUsuario)
    throws AdminException;
}


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\seg\QL\seg\SegUsuarioQLBeanLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */