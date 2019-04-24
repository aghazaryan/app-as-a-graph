package com.picsart.test.graph.imoprter.scanner.navigation;

import com.picsart.test.graph.entities.Navigation;

/**
 * @author ag on 2019-04-24
 */

public interface NavigationScanner<T> {
     Navigation scan(T info) throws Exception;
}
