package com.success.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

@Configuration
@Profile("custom")
public class ReplicaAwareTxnManager implements PlatformTransactionManager {

  private PlatformTransactionManager platformTxnMagaer;

  public ReplicaAwareTxnManager(PlatformTransactionManager platformTxnMagaer) {
    this.platformTxnMagaer = platformTxnMagaer;
  }

  @Override
  public TransactionStatus getTransaction(TransactionDefinition definition) {
    RoutingDatasource.setReadonlyDataSource(definition.isReadOnly());
    return platformTxnMagaer.getTransaction(definition);
  }

  @Override
  public void commit(TransactionStatus status) {
    platformTxnMagaer.commit(status);
  }

  @Override
  public void rollback(TransactionStatus status) {
    platformTxnMagaer.rollback(status);
  }
}
