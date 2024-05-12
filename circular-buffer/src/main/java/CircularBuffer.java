@SuppressWarnings("unchecked")
class CircularBuffer<T> {

    private final T[] buffer;
    private int head;
    private int size;

    CircularBuffer(final int size) {
        buffer = (T[]) new Object[size];
    }

    T read() throws BufferIOException {
        ensureBufferIsNotEmpty();
        int readIndex = (buffer.length + head - size) % buffer.length;
        --size;
        return buffer[readIndex];
    }

    void write(T data) throws BufferIOException {
        ensureBufferIsNotFull();
        overwrite(data);
    }

    void overwrite(T data) {
        buffer[head++] = data;
        head %= buffer.length;
        size = Math.min(size + 1, buffer.length);
    }

    void clear() {
        size = 0;
    }

    private void ensureBufferIsNotEmpty() throws BufferIOException {
        if (size == 0) {
            throw new BufferIOException("Tried to read from empty buffer");
        }
    }

    private void ensureBufferIsNotFull() throws BufferIOException {
        if (size == buffer.length) {
            throw new BufferIOException("Tried to write to full buffer");
        }
    }
}
