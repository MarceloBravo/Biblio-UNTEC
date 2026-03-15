package config;

import java.util.function.Supplier;

/**
 * Crea y guarda una instancia la primera vez que se llama a {@link #get()}.
 * Así cada servicio/DAO solo se instancia cuando un servlet lo usa por primera vez.
 */
public final class LazyHolder<T> {

    private final Supplier<T> factory;
    private volatile T instance;

    public LazyHolder(Supplier<T> factory) {
        this.factory = factory;
    }

    public synchronized T get() {
        if (instance == null) {
            instance = factory.get();
        }
        return instance;
    }
}
