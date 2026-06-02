package sp.utils.datastructure;

import java.util.LinkedList; //import
import java.util.Queue;


public class QueueUtil {
    public static void main(String[] args) {
        Queue<Integer> intQueue = new LinkedList<>(); //int형 queue 선언, linkedlist 이용
        Queue<String> stringQueue = new LinkedList<>(); //String형 queue 선언, linkedlist 이용


        Queue<Integer> stack = new LinkedList<>(); //int형 queue 선언

        stack.add(1);     // queue에 값 1 추가
        stack.add(2);     // queue에 값 2 추가
        stack.offer(3);   // queue에 값 3 추가


        Queue<Integer> queue = new LinkedList<>(); //int형 queue 선언
        queue.offer(1);     // queue에 값 1 추가
        queue.offer(2);     // queue에 값 2 추가
        queue.offer(3);     // queue에 값 3 추가
        queue.poll();       // queue에 첫번째 값을 반환하고 제거 비어있다면 null
        queue.remove();     // queue에 첫번째 값 제거
        queue.clear();      // queue 초기화


        Queue<Integer> dequeue = new LinkedList<>(); //int형 queue 선언
        dequeue.offer(1);     // queue에 값 1 추가
        dequeue.offer(2);     // queue에 값 2 추가
        dequeue.offer(3);     // queue에 값 3 추가
        dequeue.peek();       // queue의 첫번째 값 참조
    }
}
