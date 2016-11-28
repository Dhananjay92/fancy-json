package nl.smuldr.fancyjson.shared;


/**
 * Generic result of a loader. Can contain the loaded data if successful, or the exception if failed.
 *
 * @param <T> Expected data type
 */
public final class LoadResult<T> {

    private final T data;
    private final Exception exception;

    public LoadResult(final T data) {
        this.data = data;
        this.exception = null;
    }

    public LoadResult(final Exception exception) {
        this.data = null;
        this.exception = exception;
    }

    public T getData() {
        return data;
    }

    public Exception getException() {
        return exception;
    }
}
