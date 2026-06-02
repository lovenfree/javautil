package test;

public class Node{
		int data;
		Node next = null;
		
		Node(int data){
			this.data=data;
		}
		
		Boolean append(int data) {
			Node end = new Node(data);
			Node n = this;
			
			while(n.next!=null) {
				n = n.next;
			}
			
			n.next = end;
			
			return true;
		}
		
		Boolean delete(int data) {
			Node n = this;
			
			while(n.next!=null) {
				if(n.next.data == data) {
					n.next = n.next.next;
				}else {
					n = n.next;
				}
			}
			return true;
				
		}
		
		
		void retrieve(){
			Node n=this;
			
			while(n.next!=null){
				System.out.print(n.data+"->");
				n=n.next;
			}
			
			System.out.println(n.data);
		}
	}

