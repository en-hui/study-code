# 算法第六天学习
> 链表题笔试面试技巧：   
> 笔试写简单的容易实现的      
> 面试写更优雅的   

## 链表常见题

```
快慢指针
1.输入链表头节点，奇数长度返回中点，偶数长度返回上中点
2.输入链表头节点，奇数长度返回中点，偶数长度返回下中点
3.输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
4.输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
Code01_LinkedListMid


给定一个单链表的头节点head，请判断该链表是否为回文结构   
笔试用：栈
1.遍历链表，放入栈中
2.再次遍历链表，与栈中弹出的数据比较
3.如果数据不同则false，如果都相同则true
面试用：改链表
1.使用快慢指针找到链表的中点（找中点或上中点），将此节点记录mid
2.将中点（mid.next）以后的链表进行反转。则有前半部分和后半部分链表，并拥有两个head
3.遍历两个子链表进行比较（遍历至任何一个next为空）
4.把后半部分反转回来,用mid指向反转回来的head
Code02_LinkedListIsPalindrome


将单向链表按某值划分成左边小，中间相等，右边大的形式
笔试用：把链表放进数组里，在数组上做patition
1.遍历链表，放进arrayList
2.用arrayList做patition
3.把数组串成链表
面试用：分成小中大三部分，在把各部分串起来
1.用六个指针，smallHead，smallTail，equalHead，equalTail，bigHead，bigTail
2.遍历链表，根据比较放入三个区域
3.最后根据小中大的顺序把三个小链表串起来
code时注意理清链表修改指向
Code03_LinkedListPatition


一种特殊的单链表节点类描述如下
class Node {
int value;
Node next;
Node rand;
Node(int val) { value = val;}
}
rand指针是单链表节点结构中新增的指针，rand可能指向链表中的任意一个节点，也可能指向null。
给定一个由Node节点类型组成的无环单链表的头节点head，请实现一个函数完成这个链表的复制，并返回复制的新链表的头节点。
要求：时间复杂度O(N),额外空间复杂度O(1)
笔试用：使用hash表。
1.先把所有节点都克隆，并用map存起来对应关系。Map<原节点，新节点>
2.遍历原链表，对新链表的next和rand赋值
3.返回原链表头对应的新链表头
面试用：人为仿照一个对应关系（把克隆的新节点挂在老节点后面，每次取一对）
1.遍历原链表，克隆节点插在原节点之间
1-->2-->--3-->null 
处理后：1-->1'-->2-->2'-->3-->3'-->null
对应关系：原节点--新节点（原节点.next）
2.遍历链表，处理rand指针.
3.将1‘2’3‘分离出来
Code04_CopyListWithRand

给定两个可能有环也可能无环的单链表，头节点head1和head2.
请实现一个函数，如果两个链表相交，请返回相交的第一个节点。
如果不相交，返回null
要求：如果两个链表长度之和为N，时间复杂度请达到O(N),额外空间复杂度请达到O(1).
等同于问题合集：
（1）给定一个链表，返回第一个入环的节点，无环返回null
（2）两个无环链表相交，返回相交的节点
（3）两个有环链表相交，返回相交的节点
1.首先找到每个链表第一个入环的节点，如果没有就是null（判断链表是否有环）
思路1：用HashSet，遍历链表，先查在放，如果能查到，这个节点就是入环节点
思路2：快慢指针，假如快慢指针能相遇，就说明是有环的。
而有环之后，如何找入环节点呢。
从头节点开始走(先走一次，避免起始相交)，当相遇后，
让快指针回到头节点重新走（每次走一步），慢指针继续走（每次走一步），再次相遇的点就是入环节点。
2.第一种情况：链表1无环，链表2也无环
思路：两个无环链表相交，链表最后肯定是公共部分
思路1：把链表1都放进set中，然后放链表2，每次放先查一遍，如果已经存在，则这个节点是相交节点
思路2：遍历链表1，记录长度为l1，指针停在最后一个节点
遍历链表2，记录长度为l2，指针停在最后一个节点
先判断两个尾节点是否相等，不相等说明不相交。
如果相等说明相交，长链表先走，剩余和短链表一样长度的时候一起走。第一次相等的点就是第一个相交节点
3.第二种情况：链表1有环，链表2也有环
思路：两个有环链表相交，肯定共用环（1.先相交在入环 2.环内相交：两个链表的入环点都可以看作第一个相交点）
获取两个链表的入环点，loop1和loop2
首先判断loop1与loop2是否相等
（1.1）loop1等于loop2，属于第一种情况，先相交在入环。不考虑环的因素，问题变成两个无环链表相交
（1.2）loop1不等于loop2，需要先考虑是否有相交的点
（1.2.1）从loop1开始遍历，在回到loop1还没有遇到loop2，说明无相交，直接返回null
（1.2.2）有相交，返回loop1和loop2都可以
4.第三种情况：链表1，2有一个有环，有一个无环（如果一个有环一个无环，不可能相交）直接返回null
Code05_FindFirstIntersectNode


能不能不给单链表的头节点，只给想要删除的节点，就能做到在链表上把这个点删掉。
不能。要删掉一个节点，必须要给头节点。
1-->2-->3-->4-->null
有一种不正确的做法：假如要求删掉3: 3.value = 3.next.value;3.next=3.next.next;
本质是把4节点删掉，又把4的值覆盖到3节点上。
这样做有很多问题：
1.假如删除最后一个节点，做不了
2.假如节点内容比较复杂，做不到完全的值克隆
3.假如节点是服务器一类的资源，服务下线节点搞错了，会出问题
```
