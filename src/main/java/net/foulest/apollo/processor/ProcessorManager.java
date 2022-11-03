package net.foulest.apollo.processor;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.ImmutableClassToInstanceMap;
import net.foulest.apollo.processor.impl.*;
import net.foulest.apollo.processor.type.Processor;

import java.util.Collection;

public final class ProcessorManager {

    private final ClassToInstanceMap<Processor<?>> processors;

    public ProcessorManager() {
        processors = new ImmutableClassToInstanceMap.Builder<Processor<?>>()
                .put(IncomingPacketProcessor.class, new IncomingPacketProcessor())
                .put(OutgoingPacketProcessor.class, new OutgoingPacketProcessor())
                .build();
    }

    /**
     * @param clazz - The class you want to get.
     * @param <T>   - The raw class.
     * @return - The instance of the processor requested.
     */
    public <T extends Processor<?>> T getProcessor(Class<T> clazz) {
        return processors.getInstance(clazz);
    }

    /**
     * @return - Returns all the registered processors
     */
    public Collection<Processor<?>> getProcessors() {
        return processors.values();
    }
}
