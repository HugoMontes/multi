package org.erp.param.ML;

import javax.ejb.Local;
import org.erp.entities.MasterTable;
import org.erp.entities.ParameterTable;
import org.erp.util.AdminException;

@Local
public abstract interface ParametrosMLBeanLocal
{
  public abstract void eliminarMaster(MasterTable paramMasterTable)
    throws AdminException;
  
  public abstract void guardarMaster(MasterTable paramMasterTable)
    throws AdminException;
  
  public abstract void guardarParameter(ParameterTable paramParameterTable)
    throws AdminException;
  
  public abstract void eliminarParameter(ParameterTable paramParameterTable)
    throws AdminException;
}


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\param\ML\ParametrosMLBeanLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */