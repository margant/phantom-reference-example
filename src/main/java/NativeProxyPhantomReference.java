import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class NativeProxyPhantomReference extends PhantomReference<NativeProxy> {
    /**
     * Creates a new phantom reference that refers to the given object and
     * is registered with the given queue.
     *
     * <p> It is possible to create a phantom reference with a <tt>null</tt>
     * queue, but such a reference is completely useless: Its <tt>get</tt>
     * method will always return null and, since it does not have a queue, it
     * will never be enqueued.
     *
     * @param referent the object the new phantom reference will refer to
     * @param q        the queue with which the reference is to be registered,
     */
    public NativeProxyPhantomReference(NativeProxy referent, ReferenceQueue<? super NativeProxy> q) {
        super(referent, q);
        referentNativeHandle = referent.nativeHandle;
    }

    public void finalizeResources() {
        System.out.println("clearing ..." + referentNativeHandle);
        NativeProxy.cleanup(referentNativeHandle);
        System.out.println("cleared  ..." + referentNativeHandle);
    }

    private long referentNativeHandle;
}
