
import org.junit.*;
import static org.junit.Assert.*;


public class MyLinkedListTest {

    private MyLinkedList<String> ll;

    @Test
    public void linkedList_생성() {
        //given 빈 연결리스트 생성
        ll = new MyLinkedList<>();

        //then 빈 연결리스트가 된다.
        assertTrue(ll.isEmpty());

        System.out.println("linkedList_생성 : " + ll.toString());
    }

    @Test
    public void linkedList_맨앞에삽입() {
        //given 빈 연결리스트 생성 후 김수현 add
        ll = new MyLinkedList<>();
        ll.add("김수현");

        //when 연결리스트 가장 처음에 전지현 add
        ll.add(0, "전지현");

        //then 연결리스트에 전지현, 김수현 순서로 들어간다.
        assertEquals("전지현", ll.get(0));
        assertEquals("김수현", ll.get(1));

        System.out.println("linkedList_맨앞에삽입 : " + ll.toString());
    }

    @Test
    public void linkedList_맨끝에삽입() {
        //given 빈 연결리스트 생성 후 김수현 add
        ll = new MyLinkedList<>();
        ll.add("김수현");
        System.out.println("ll = " + ll);
        //when 연결리스트 가장 끝에 전지현 add
        ll.add("전지현");

        //then 연결리스트에 김수현, 전지현 순서로 들어간다.
        assertEquals("김수현", ll.get(0));
        assertEquals("전지현", ll.get(1));

        System.out.println("linkedList_맨끝에삽입 : " + ll.toString());
    }

    @Test
    public void linkedList_중간에삽입() {
        //given 빈 연결리스트 생성 후 김수현, 박해진 add
        ll = new MyLinkedList<String>();
        ll.add("김수현");
        ll.add("박해진");

        //when 1번 index에 전지현 add
        ll.add(1, "전지현");
        System.out.println("ll = " + ll);

        //then 연결리스트에 김수현, 전지현 순서로 들어간다.
        assertEquals("김수현", ll.get(0));
        assertEquals("전지현", ll.get(1));
        assertEquals("박해진", ll.get(2));

        System.out.println("linkedList_중간에삽입 : " + ll.toString());
    }

    @Test
    public void linkedList_빈노드삭제() {
        //given 빈 연결리스트 생성
        ll = new MyLinkedList<String>();

        //when 첫번째 노드 삭제
        assertThrows(IndexOutOfBoundsException.class, () -> ll.remove(0));

        //then 삭제되지 않는다. (에러뜸)
        System.out.println("linkedList_빈노드삭제 : " + ll.toString());
    }

    @Test
    public void linkedList_첫노드삭제() {
        //given 빈 연결리스트 생성 후 김수현, 전지현 add
        ll = new MyLinkedList<String>();
        ll.add("김수현");
        ll.add("전지현");

        //when 첫번째 노드 삭제
        ll.remove(0);

        //then 연결리스트에 전지현만 남는다.
        assertEquals("전지현", ll.get(0));

        System.out.println("linkedList_첫노드삭제 : " + ll.toString());
    }

    @Test
    public void linkedList_중간노드삭제() {
        //given 빈 연결리스트 생성 후 김수현, 전지현, 박해진 add
        ll = new MyLinkedList<String>();
        ll.add("김수현");
        ll.add("전지현");
        ll.add("박해진");

        //when 전지현 삭제
        ll.remove("전지현");

        //then 연결리스트에 김수현, 박해진 순서로 남는다.
        assertEquals("김수현", ll.get(0));
        assertEquals("박해진", ll.get(1));

        System.out.println("linkedList_중간노드삭제 : " + ll.toString());
    }
}