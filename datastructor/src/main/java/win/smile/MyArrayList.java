//package win.smile;
//
//import java.util.Iterator;
//
///**
// * 实现自己的ArrayList
// * 1.保持基础数组，数组的容量，以及存储在MyArrayList中的当前数
// * 2.提供一种机制允许改变基础数组的容量。通过获得一个新数组，将老数组拷贝到新数组中来改变数组的容量，允许虚拟机回收老数组
// * 3.提供get，set方法
// * 4.提供基本的流程，如size，isEmpty，clear，remove，两种add。
// * 5.提供一个实现Iterator的类，这个类将存储迭代序列中的下一项的下标，并提供next、hasNext和remove等方法的实现。MyArrayList的迭代器方法直接返回实现Iterator接口的该类的新构造的实例。
// * @author smilewei on 2018/8/8.
// * @since 1.0.0
// */
//public class MyArrayList<E> implements Iterator<E> {
//
//    private static final int DEFAULT_CAPACITY = 10;
//
//    private int theSize;
//
//    private Object[] theItems;
//
//    public MyArrayList(){
//
//    }
//
//    public void clear(){
//
//    }
//
//    public void doClear(){
//        theSize=0;
//        ensureCapacity(DEFAULT_CAPACITY);
//    }
//
//    public void ensureCapacity(int newCapacity){
//        if(newCapacity<theSize){
//            return;
//        }
//    }
//
//
//
//
//
//}
