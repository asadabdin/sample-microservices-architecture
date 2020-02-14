package org.asad.loanapplication.mapping;

import ma.glasnost.orika.metadata.ClassMapBuilder;

/**
 * Implement Mapping by implanting this interface
 */
@SuppressWarnings("squid:S1609")
public interface ObjectMapping<A, B> {

    void classMap(ClassMapBuilder<A, B> classMapBuilder);
}
