package locks;

import java.util.HashMap;
import java.util.Map;

/*
 * Written by Doug Lea with assistance from members of JCP JSR-166
 * Expert Group and released to the public domain, as explained at
 * http://creativecommons.org/publicdomain/zero/1.0/
 */

/**
 * A synchronizer that may be exclusively owned by a thread.  This
 * class provides a basis for creating locks and related synchronizers
 * that may entail a notion of ownership.  The
 * {@code AbstractOwnableSynchronizer} class itself does not manage or
 * use this information. However, subclasses and tools may use
 * appropriately maintained values to help control and monitor access
 * and provide diagnostics.
 *
 * @since 1.6
 * @author Doug Lea
 */
public abstract class AbstractOwnableSynchronizer
    implements java.io.Serializable {

    /** Use serial ID even though all fields transient. */
    private static final long serialVersionUID = 3737899427754241961L;

    /**
     * Empty constructor for use by subclasses.
     */
    protected AbstractOwnableSynchronizer() { }

    /**
     * The current owner of exclusive mode synchronization.
     */
    private transient Thread exclusiveOwnerThread;
    
    /**
     * The moment when the current owner acquired the lock.
     */
   private transient Map<Thread, StackTraceElement> stackElementAcquiredLock  = new HashMap<Thread, StackTraceElement>();

    /**
     * Sets the thread that currently owns exclusive access.
     * A {@code null} argument indicates that no thread owns access.
     * This method does not otherwise impose any synchronization or
     * {@code volatile} field accesses.
     * @param thread the owner thread
     */
    protected void setExclusiveOwnerThread(Thread thread) {
        exclusiveOwnerThread = thread;
    }

    /**
     * Returns the thread last set by {@code setExclusiveOwnerThread},
     * or {@code null} if never set.  This method does not otherwise
     * impose any synchronization or {@code volatile} field accesses.
     * @return the owner thread
     */
    protected final Thread getExclusiveOwnerThread() {
        return exclusiveOwnerThread;
    }
    
    protected void addStackElementAcquiredLock(Thread thread, StackTraceElement element) {
    	stackElementAcquiredLock.put(thread, element);
    }

    protected void removeStackElementAcquiredLock(Thread thread) {
    	if(stackElementAcquiredLock.containsKey(thread)) {
    		stackElementAcquiredLock.remove(thread);
    	}
    }
    
    protected final StackTraceElement getStackElementAcquiredLock(Thread thread) {
    	if(stackElementAcquiredLock.containsKey(thread)) {
    		return stackElementAcquiredLock.get(thread);
    	}
    	
    	return null;
    }
}
