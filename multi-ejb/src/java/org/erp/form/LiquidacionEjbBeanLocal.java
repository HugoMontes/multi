package org.erp.form;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import org.erp.entities.CotizacionMineral;
import org.erp.entities.CotizacionMineralDesc;
import org.erp.entities.Liquidacion;
import org.erp.entities.Regalia;
import org.erp.entities.TipoCambio;
import org.erp.util.AdminException;

@Local
public abstract interface LiquidacionEjbBeanLocal
{
  public abstract List<CotizacionMineral> listCotizacionMineral()
    throws AdminException;
  
  public abstract List<CotizacionMineralDesc> listCotizacionMineralDesc(Integer paramInteger)
    throws AdminException;
  
  public abstract TipoCambio obtenerTipoCambio(Date paramDate, Integer paramInteger)
    throws AdminException;
  
  public abstract BigDecimal obtenerTipoCambioEjemplo(Integer paramInteger)
    throws AdminException;
  
  public abstract List<Regalia> listadoRegalia(Integer paramInteger1, Integer paramInteger2)
    throws AdminException;
  
  public abstract void guardarLiquidacionExportacion(Regalia paramRegalia, Liquidacion paramLiquidacion)
    throws AdminException;
  
  public abstract List<Liquidacion> listadoliquidacionByRegalia(Integer paramInteger)
    throws AdminException;
  
  public abstract void guardarRegalia(Regalia paramRegalia)
    throws AdminException;
  
  public abstract void eliminarMineral(Liquidacion paramLiquidacion)
    throws AdminException;
  
  public abstract Regalia obtenerRegaliaByTramite(String paramString)
    throws AdminException;
}


/* Location:              F:\win\multi-ear.ear!\multi-ejb.jar!\org\erp\form\LiquidacionEjbBeanLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */