#### 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

示例：  
给定一个链表: 1->2->3->4->5, 和 n = 2.  
当删除了倒数第二个节点后，链表变为 1->2->3->5.

说明：给定的 n 保证是有效的。

思路:   
  使用三个指针
  *  next_last: 最终会移动到链表最后一个元素    
  *  next_n: 最终会移动到链表倒数第n个元素,即需要删除的元素  
  *  next_n1: next_n的前一个元素,可能为null  
  *  当next_last移动到链表末尾时,如果next_n1为null 那么返回next_n.next即可;  
  *  如果next_n1不为null,那么使next_n1.next = next_n.next; 然后返回head即可.   

过程:
  1. 先把next_n和next_last指向head, 且 next_n1=null ,
  2. 先让next_last移动n-1步,使其和next_n拉开距离
  3. 然后同时移动next_n和next_last,且在移动next_n之前,把next_n1指向next_n
  4.一直执行第3步直到next_last移动到链表末尾,此时next_n即为要删除的元素