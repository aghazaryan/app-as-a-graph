package com.picsart.test.graph.imoprter.scanner.screen;

import com.picsart.test.graph.entities.Screen;

/**
 * @author ag on 2019-04-24
 */

public interface ScreenScanner<T> {
     Screen scan(T info) throws Exception;
}
