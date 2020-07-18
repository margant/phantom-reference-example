import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

public class Factory {
    private static ReferenceQueue cleanupQueue = new ReferenceQueue();
    private static List<NativeProxyPhantomReference> references = new ArrayList();

    public static NativeProxy create() {
        Reference polledReference = cleanupQueue.poll();
        if (polledReference != null) {
            ((NativeProxyPhantomReference) polledReference).finalizeResources();
        }
        NativeProxy nativeProxy = new NativeProxy();
        references.add(new NativeProxyPhantomReference(nativeProxy, cleanupQueue));
        return nativeProxy;
    }
}