package org.erp.util;

import java.sql.Connection;
import javax.ejb.Local;

@Local
public abstract interface ConeccionReportLocal
{
  public abstract Connection getSesionSistema();
}
