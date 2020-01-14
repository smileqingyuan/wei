# 1. Collection

概述: 集合，又可以称为容器，是一组相同对象的集合

## 2. List,Set,Map

- list: 有序，元素可以重复的一组集合

- set: 元素不可重复的一组集合

- map: 键值对形态的集合，key不可重复，可以允许一个null值

## 2.1 ArrayList,LinkedList

- ArrayList: 有序，数据可以重复，底层为**Object 数组**
- LinkedList: 有序，数据可以重复，底层为 **双向链表结构** 

### 2.1.1 ArrayList 与Vector的区别

 ArrayList 线程不安全，效率高，得益于其扩容机制

Vector 使用syncronized保证 线程安全，但是效率低

### 2.1.2 ArrayList的核心源码

```java
/*
  1. arrayList的最大容量
  2. arrayList扩容策略
  3. arrayLsit的并发机制
*/
 // 未指定初始化容量时，添加第一个元素是，默认初始化容量为10
 private static final int DEFAULT_CAPACITY = 10;
 // arrayList 最大的容量为  int MAX_VALUE = 2147483647; --其本质是因为 数组的最大长度限制，抛出java.lang.OutOfMemoryError: Java heap space
 private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
/*扩容策略
  最小容量保证 int minCapacity = size+1;
  扩容算法: 扩容为当前容量的1.5倍。 
      当扩容后的容量 大于最大数组值时，需要调用 hugeCapacity,超过int MAX_VALUE = 2147483647，OOM
*/
public boolean add(E e) {
    ensureCapacityInternal(size + 1);  // Increments modCount!!
    elementData[size++] = e;
    return true;
}
private void ensureCapacityInternal(int minCapacity) {
    if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
        minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
    }
    ensureExplicitCapacity(minCapacity);
}

private void ensureExplicitCapacity(int minCapacity) {
    //和并发有关，并发添加时，modCount++不一致，抛出并发异常
    modCount++; 
    // overflow-conscious code
    if (minCapacity - elementData.length > 0)
        grow(minCapacity);
}

private void grow(int minCapacity) {
    // overflow-conscious code
    int oldCapacity = elementData.length;
    int newCapacity = oldCapacity + (oldCapacity >> 1);
    if (newCapacity - minCapacity < 0)
        newCapacity = minCapacity;
    if (newCapacity - MAX_ARRAY_SIZE > 0)
        newCapacity = hugeCapacity(minCapacity);
    // minCapacity is usually close to size, so this is a win:
    elementData = Arrays.copyOf(elementData, newCapacity);
}

private static int hugeCapacity(int minCapacity) {
    if (minCapacity < 0) // overflow
        throw new OutOfMemoryError();
    return (minCapacity > MAX_ARRAY_SIZE) ?
        Integer.MAX_VALUE :
    MAX_ARRAY_SIZE;
}

```

### 2.1.3 如何解决ArrayList的并发





## 2.2 HashSet,TreeSet,LinkedHashSet

- HashSet: 无序，元素不可重复，数据存储在 hash table上，详见HashMap
- TreeSet: 有序，按照自然顺序排列，元素不可重复，数据存储在 red-black-tree 中，详见 TreeHashMap
- LinkedHashSet: 无序，按照插入顺序排列，元素不可重复，底层为 hash table 和 链表

## 2.3 HashMap,TreeMap,LinkedHashMap

- hashMap:   kv结构，k无序，允许null存在，但不能重复,非线程安全,底层是 **数组+链表+红黑树**
- treeMap:  kv结构，k自然排序，允许null存在，但不能重复,非线程安全
- linkedHashMap:  kv结构，k按照插入顺序排序，允许null存在，但不能重复,非线程安全

### 2.3.1 HashMap的源码

```java
//保证k唯一的算法，看put方法即可
 public V put(K key, V value) {
   return putVal(hash(key), key, value, false, true);
 }

final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
    Node<K,V>[] tab; Node<K,V> p; int n, i;
    //1. 判断键值对数组 tab是否为空或 null，null时直接扩容
    if ((tab = table) == null || (n = tab.length) == 0)
        n = (tab = resize()).length;
    //2. 根据key值计算
    if ((p = tab[i = (n - 1) & hash]) == null)
        tab[i] = newNode(hash, key, value, null);
    else {
        Node<K,V> e; K k;
        if (p.hash == hash &&
            ((k = p.key) == key || (key != null && key.equals(k))))
            e = p;
        else if (p instanceof TreeNode)
            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
        else {
            for (int binCount = 0; ; ++binCount) {
                if ((e = p.next) == null) {
                    p.next = newNode(hash, key, value, null);
                    if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        treeifyBin(tab, hash);
                    break;
                }
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    break;
                p = e;
            }
        }
        if (e != null) { // existing mapping for key
            V oldValue = e.value;
            if (!onlyIfAbsent || oldValue == null)
                e.value = value;
            afterNodeAccess(e);
            return oldValue;
        }
    }
    ++modCount;
    if (++size > threshold)
        resize();
    afterNodeInsertion(evict);
    return null;
}

//扩容机制
final Node<K,V>[] resize() {
    Node<K,V>[] oldTab = table;
    int oldCap = (oldTab == null) ? 0 : oldTab.length;
    int oldThr = threshold;
    int newCap, newThr = 0;
    if (oldCap > 0) {
        if (oldCap >= MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return oldTab;
        }
        else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                 oldCap >= DEFAULT_INITIAL_CAPACITY)
            newThr = oldThr << 1; // double threshold
    }
    else if (oldThr > 0) // initial capacity was placed in threshold
        newCap = oldThr;
    else {               // zero initial threshold signifies using defaults
        newCap = DEFAULT_INITIAL_CAPACITY;
        newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
    }
    if (newThr == 0) {
        float ft = (float)newCap * loadFactor;
        newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                  (int)ft : Integer.MAX_VALUE);
    }
    threshold = newThr;
    @SuppressWarnings({"rawtypes","unchecked"})
    Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
    table = newTab;
    if (oldTab != null) {
        for (int j = 0; j < oldCap; ++j) {
            Node<K,V> e;
            if ((e = oldTab[j]) != null) {
                oldTab[j] = null;
                if (e.next == null)
                    newTab[e.hash & (newCap - 1)] = e;
                else if (e instanceof TreeNode)
                    ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                else { // preserve order
                    Node<K,V> loHead = null, loTail = null;
                    Node<K,V> hiHead = null, hiTail = null;
                    Node<K,V> next;
                    do {
                        next = e.next;
                        if ((e.hash & oldCap) == 0) {
                            if (loTail == null)
                                loHead = e;
                            else
                                loTail.next = e;
                            loTail = e;
                        }
                        else {
                            if (hiTail == null)
                                hiHead = e;
                            else
                                hiTail.next = e;
                            hiTail = e;
                        }
                    } while ((e = next) != null);
                    if (loTail != null) {
                        loTail.next = null;
                        newTab[j] = loHead;
                    }
                    if (hiTail != null) {
                        hiTail.next = null;
                        newTab[j + oldCap] = hiHead;
                    }
                }
            }
        }
    }
    return newTab;
}

```

hashMap的存储结构示意图![](https://raw.githubusercontent.com/smileqingyuan/wei/master/plusone/doc/pic/e4a19398.png)

小结: 

参考:  https://tech.meituan.com/2016/06/24/java-hashmap.html 

### 2.3.2 多并发导致HashMap的死循环

 

### 2.3.3 TreeMap的排序探讨



### 2.3.4 LinkedHashMap排序探讨



### 2.3.5 ConcurrentHashMap



### 2.3.x 一些有趣的写法

```java
// Partition students into passing and failing
//students list 按照通过和失败划分学生
Map<Boolean, List<Student>> passingFailing = students.stream()
.collect(Collectors.partitioningBy(s -> s.getGrade()>= PASS_THRESHOLD)); 


//统计一段话中，每个单词的出现次数
import java.util.*;
public class Freq {
    public static void main(String[] args) {
        Map<String, Integer> m = new HashMap<String, Integer>();

        // Initialize frequency table from command line
        for (String a : args) {
            Integer freq = m.get(a);
            /*找个写法比较有趣，不需要那么啰嗦了，我习惯写法，
                if(freq == null){
                    freq = 0;
                }
                m.put(a,freq++);
            */
            m.put(a, (freq == null) ? 1 : freq + 1);
        }

        System.out.println(m.size() + " distinct words:");
        System.out.println(m);
    }
}

```





