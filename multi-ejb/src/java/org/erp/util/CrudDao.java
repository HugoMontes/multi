package org.erp.util;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CrudDao
{
  @PersistenceContext(unitName="admindbDS")
  public EntityManager em;
}