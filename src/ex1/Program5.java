package ex1;

import com.newlecture.app.console.NoticeConsole;

import java.sql.SQLException;

public class Program5 {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        NoticeConsole console = new NoticeConsole();

        EXIT:
        while (true) {
            console.printNoticeList();
            int menu = console.inputNoticeMenu();

            switch (menu) {
                case 1: //상세조회
                    break;
                case 2: // 이전

                    console.movePrevList();
                    //프로그램 내에서 페이지를 움직일 수 있지만 NoticeConsole부분에서 조작하는 것으로 하였음
                    break;
                case 3: // 다음
                    console.moveNextList();
                    break;
                case 4: // 글쓰기
                    break;
                case 5: // 검색
                    console.inputSearchWord();
                    break;
                case 6:
                    System.out.println("bye~");
                    break EXIT;
                default:
                    System.out.println("<<사용방법>> 메뉴는 1~4까지만 입력할 수 있습니다.");
                    break;

            }
        }

    }
}


