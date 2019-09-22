package org.erp.util;

import java.util.List;
import javax.ejb.Local;
import org.erp.entities.GenKey;
import org.erp.entities.ParameterTable;

@Local
public abstract interface ServiciosUtilBeanLocal
{
  public abstract List<ParameterTable> listaParametricas(Integer paramInteger)
    throws AdminException;
  
  public abstract ParameterTable ParameterById(Integer paramInteger)
    throws AdminException;
  
  public abstract GenKey licenceKey()
    throws AdminException;
  
  public abstract void guardarDatosLicencia(GenKey paramGenKey)
    throws AdminException;
}