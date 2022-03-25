package com.success.configs;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@Configuration
@Profile("custom")
public class RoutingDatasource extends AbstractRoutingDataSource {

  private static final ThreadLocal<Type> currentDataSource = new ThreadLocal<>();

  public RoutingDatasource(DataSource primary, DataSource replica) {
    Map<Object, Object> targetDataSources = new HashMap<>();
    targetDataSources.put(Type.MASTER, primary);
    targetDataSources.put(Type.REPLICA, replica);
    this.setDefaultTargetDataSource(primary);
    this.setTargetDataSources(targetDataSources);
  }

  static void setReadonlyDataSource(boolean isReadonly) {
    currentDataSource.set(isReadonly ? Type.REPLICA : Type.MASTER);
  }

  @Override
  protected Object determineCurrentLookupKey() {
    return currentDataSource.get();
  }

  private enum Type {
    MASTER,
    REPLICA;
  }
}
