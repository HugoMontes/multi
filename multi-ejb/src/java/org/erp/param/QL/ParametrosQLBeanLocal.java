package org.erp.param.QL;

import java.util.List;
import javax.ejb.Local;
import org.erp.entities.MasterTable;
import org.erp.entities.ParameterTable;
import org.erp.util.AdminException;

@Local
public abstract interface ParametrosQLBeanLocal
{
  public abstract List<MasterTable> listaMaster()
    throws AdminException;
  
  public abstract List<ParameterTable> listaParameter(Integer paramInteger)
    throws AdminException;
}


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\param\QL\ParametrosQLBeanLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */