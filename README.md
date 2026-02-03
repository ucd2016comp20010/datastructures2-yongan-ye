Starting repository for `Data Structures` COMP20280 2025-2026

# EXERCISES 1

## Q5: In my upstream repo, there are some unit test classes SinglyLinkedListTest, DoublyLinkedListTest, etc. These tests require Junit5. Run these tests and make sure they all pass. Are the tests complete

All the tests are passed.

---

## Q6: What is the difference between a singly linked list and a circularly linked list?

The main difference lies in the structure of the last node:
- **Singly Linked List**: The last node's `next` pointer is `null`. Traversal stops when reaching a null reference.
- **Circularly Linked List**: The last node's `next` pointer points back to the first node. There is no `null` to the end, so it will be a cycle.

---

## Q7: In what situations would you prefer to use a linked list to an array?

Linked lists are preferred over arrays in the following situations:
1. **Dynamic Size**: When the number of elements is unknown or changes frequently, linked lists allow O(1) insertion/deletion at known positions without resizing overhead.
2. **Frequent Insertions/Deletions**: Inserting or deleting elements in the middle of a linked list is O(1) if you have a reference to the node, compared to O(n) for arrays which require shifting elements.
3. **Memory Efficiency for Sparse Data**: If you don't need all elements at once, linked lists only allocate memory for existing elements, unlike arrays which allocate contiguous memory blocks.

---

## Q8: Describe 2 possible use-cases for a circularly linked list (2-3 sentences for each).

**Use Case 1: Round-Robin Scheduling**
Circularly linked lists are ideal for implementing round-robin scheduling algorithms in operating systems or task schedulers. Each process or task is represented as a node, and the scheduler rotates through the list, giving each process a time slice. When a process's time expires, the scheduler moves to the next node (rotate operation), and when it reaches the end, it automatically wraps around to the beginning. This ensures fair CPU time distribution among all processes.

**Use Case 2: Circular Buffer / Ring Buffer**
Circularly linked lists are perfect for implementing circular buffers used in data streaming applications. The circular structure allows continuous data flow where new data overwrites old data when the buffer is full, and the read/write pointers can rotate seamlessly without needing to reset or reallocate memory. 

---