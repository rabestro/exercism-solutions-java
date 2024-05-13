import java.util.ArrayDeque;

class CircularBuffer<T> extends ArrayDeque<T> {
    private final int capacity;

    CircularBuffer(int capacity) {
        super(capacity);
        this.capacity = capacity;
    }

    T read() throws BufferIOException {
        ensureBufferIsNotEmpty();
        return super.remove();
    }

    void write(T data) throws BufferIOException {
        ensureBufferIsNotFull();
        super.add(data);
    }

    void overwrite(T data) {
        if (size() == capacity) {
            super.remove();
        }
        super.add(data);
    }

    private void ensureBufferIsNotEmpty() throws BufferIOException {
        if (isEmpty()) {
            throw new BufferIOException("Tried to read from empty buffer");
        }
    }

    private void ensureBufferIsNotFull() throws BufferIOException {
        if (size() == capacity) {
            throw new BufferIOException("Tried to write to full buffer");
        }
    }
}
