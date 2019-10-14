package ai.muhamob.week5.classRoom;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LoggerProxy implements InvocationHandler {
    private final Object delegate;

    public static<T> T compute(T delegate) {
         return (T) Proxy.newProxyInstance(
            ClassLoader.getSystemClassLoader(),
            delegate.getClass().getInterfaces(),
            new LoggerProxy(delegate)
        );
    }

    private LoggerProxy(Object delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long l = System.currentTimeMillis();
        Object result = method.invoke(delegate, args);
        System.out.println(System.currentTimeMillis() - l);
        return result;
    }
}
