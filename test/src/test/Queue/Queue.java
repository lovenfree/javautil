package test.Queue;

import java.util.NoSuchElementException;


public class Queue<T> {

		class Node<T>{
			private T data;
			private Node<T> next;

			public Node(T data) {
				this.data =data;
				
			}
		}
		
		//queue 는 앞뒤 노드를 알아야 
		private Node<T> first;
		private Node<T> last;
		
		
		
		//추가 
		public void add(T item) {
			Node<T> t = new Node<T>(item);
			
			if(last!=null) {
				last.next = t;
				
			}
			last =t;
			
			if(first == null) {
				first = last;
			}
		}
		
		//삭제 
		public T remove() {
			//queue empty
			if(first == null) {
				throw new NoSuchElementException();
			}
			
			//반환하기전에 그뒤에 있는 값을 first로 만들어야 함
			//data back up
			T data = first.data;
			first = first.next;
			
			//first가 null 이 되었을때 last도 같이 변경해 주어야 함
			if(first == null) {
				last = null;
			}
			
			return data;
			
		}
		//보는거  
		public T peek() {
			if(first == null) {
				throw new NoSuchElementException();
			}
			return first.data;
		}
		
		
		//비었냐? 
		public boolean isEmpty() {
			return first == null;
		}
		

	
}
