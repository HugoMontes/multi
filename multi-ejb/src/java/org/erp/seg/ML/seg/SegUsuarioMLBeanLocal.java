package org.erp.seg.ML.seg;

import javax.ejb.Local;
import org.erp.entities.SegRol;
import org.erp.entities.SegUsuario;
import org.erp.util.AdminException;

@Local
public abstract interface SegUsuarioMLBeanLocal
{
  public abstract void guardarUsuario(SegUsuario paramSegUsuario)
    throws AdminException;
  
  public abstract void eliminarUsuario(SegUsuario paramSegUsuario)
    throws AdminException;
  
  public abstract void saveOrUpdateRolesPorUsuario(SegUsuario paramSegUsuario, SegRol[] paramArrayOfSegRol)
    throws AdminException;
  
  public abstract void guardarCambioPass(SegUsuario paramSegUsuario)
    throws AdminException;
}


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\seg\ML\seg\SegUsuarioMLBeanLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */