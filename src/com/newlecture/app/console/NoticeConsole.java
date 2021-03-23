package com.newlecture.app.console;

import com.newlecture.app.entity.Notice;
import com.newlecture.app.service.NoticeService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class NoticeConsole {

    private NoticeService service;
    private int page;
    private int count;
    private String searchWord;
    private String searchField;

    public NoticeConsole() {
        service = new NoticeService();
        page =1;
        searchField ="TITLE";
        searchWord ="";

    }

    public void printNoticeList() throws SQLException, ClassNotFoundException {
        List<Notice> list = service.getList(page, searchField, searchWord);
        int count = service.getCount();//count는 페이지가 리프레시 될때마다 매번 구해야하기 때문에 지역변수로 설정
        int lastpage = count/10; //게시글 갯수가 정확하게 10으로 나뉘어질때
        lastpage = count%10>0?lastpage+1:lastpage;//게시글갯수가 10으로 안나누어질때

        System.out.println("──────────────────────────────────────────");
        System.out.printf("<공지사항> 총 %d개 게시글\n", count);
        System.out.println("──────────────────────────────────────────");
        for (Notice n : list) {
            System.out.printf("%d. %s / %s / %s\n", n.getId(),
                    n.getTitle(),
                    n.getWriterId(),
                    n.getRegDate());
        }
        System.out.println("──────────────────────────────────────────");
        System.out.printf("                   %d / %d pages\n", page, lastpage);

    }

    public int inputNoticeMenu() {
        Scanner scan = new Scanner(System.in);
        System.out.printf("1. 상세조회 / 2. 이전 / 3. 다음 / 4. 글쓰기 / 5. 검색 / 6. 종료>");
        String menu_ = scan.nextLine();
        int menu = Integer.parseInt(menu_);
        return menu;

    }

    public void movePrevList() {
        if(page ==1){
            System.out.println("이전 페이지가 없습니다.");
            return;
        }
        page--;
    }

    public void moveNextList() throws SQLException, ClassNotFoundException {
        int count = service.getCount();//count는 페이지가 리프레시 될때마다 매번 구해야하기 때문에 지역변수로 설정/
        //위에 있던 getList 함수와 다른 값을 불러올 수 있기 때문에 멤버변수로 설정하지 않는다.
        int lastpage = count/10;
        lastpage = count%10>0?lastpage+1:lastpage;
        if(page ==lastpage){
            System.out.println("다음 페이지가 없습니다.");
            return;
        }
        page++;
    }

    public void inputSearchWord() {
        Scanner scan = new Scanner(System.in);
        System.out.println("검색 범주(title/content/writerId)중에 하나를 입력하세요");
        System.out.println(">");
        searchField = scan.nextLine();

        System.out.println("검색어 > ");
        searchWord = scan.nextLine(); // searchWord는 getList할때도 필요한 변수이기 때문에 멤버변수로 설정
        
    }
}
