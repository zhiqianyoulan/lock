package com.dongnao.concurrent.period3.locks4;

//读写锁里面的代码， 和ReentrantLock代码，有大量重复
public class KodyReadWriteLock {

    CommonMask mask = new CommonMask(){
        //尝试获取独占锁    //tryLock需要修改
        public boolean tryLock(int acquires) {
            //如果read count ！=0 返回false
            if (readCount.get() !=0)
                return false;

            int wct = writeCount.get();     //拿到 独占锁 当前状态

            if (wct==0){
                if (writeCount.compareAndSet(wct, wct + acquires)){     //通过修改state来抢锁
                    owner.set(Thread.currentThread());  //  抢到锁后，直接修改owner为当前线程
                    return true;
                }
            }else if (owner.get() == Thread.currentThread()){
                writeCount.set(wct + acquires);     //修改count值
                return true;
            }

            return false;
        }

        //尝试释放独占锁
        public boolean tryUnlock(int releases) {
            //若当前线程没有 持有独占锁
            if(owner.get()!= Thread.currentThread()){
                throw new IllegalMonitorStateException();       //抛IllegalMonitorStateException
            }

            int wc= writeCount.get();
            int nextc = wc - releases;      //计算 独占锁剩余占用
            writeCount.set(nextc);      //不管是否完全释放，都更新count值

            if (nextc==0){  //是否完全释放
                owner.compareAndSet(Thread.currentThread(), null);
                return true;
            }else{
                return false;
            }

        }

        //尝试获取共享锁
        public int tryLockShared(int acquires) {
            for (;;){
                if (writeCount.get()!=0 &&
                        owner.get() != Thread.currentThread())
                    return -1;

                int rct = readCount.get();
                if (readCount.compareAndSet(rct, rct + acquires)){
                    return 1;
                }
            }
        }

        //尝试解锁共享锁
        public boolean tryUnLockShared(int releases) {
            for(;;){
                int rc = readCount.get();
                int nextc = rc - releases;
                if (readCount.compareAndSet(rc, nextc)){
                    return nextc==0;
                }
            }
        }
    };

    public void lock(){
        mask.lock();
    }

    public boolean tryLock(){
        return mask.tryLock(1);
    }

    public void unlock(){
        mask.unlock();
    }


    public void lockShared(){
        mask.lockShared();
    }

    public int tryLockShared(){
        return tryLockShared();
    }

    public void unlockShared(){
        mask.unLockShared();
    }




}
