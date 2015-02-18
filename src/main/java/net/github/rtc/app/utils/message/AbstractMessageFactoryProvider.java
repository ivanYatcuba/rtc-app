package net.github.rtc.app.utils.message;

import net.github.rtc.app.utils.message.factory.OrderResponseMessageFactoryImpl;

public abstract class AbstractMessageFactoryProvider {

    abstract OrderResponseMessageFactoryImpl getOrderResponseMessageFactory();
}
