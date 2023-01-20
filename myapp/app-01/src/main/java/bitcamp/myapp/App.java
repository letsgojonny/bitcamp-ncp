package bitcamp.myapp;

public class App {
    public static void main(String[] args) {

        Scanner keyScanner = new Scanner(System.in);

        final int SIZE = 100;
        int count = 0;
        
        int[] no = new int[SIZE];
        String[] name = new String[SIZE];
        String[] tel = new String[SIZE];
        String[] postNo = new String[SIZE];
        String[] basicAddress = new String[SIZE];
        String[] detailAddress = new String[SIZE];
        boolean[] working = new boolean[SIZE];
        char[] gender = new char[SIZE];
        byte[] postNo = new byte[SIZE];
        String[] createdDate = new String[SIZE];

        for (int i = 0; i < SIZE; i++) {
        System.out.print("번호? ");
        no[i] = Integer.parseInt(keyScanner.nextLine());

        System.out.print("이름? ");
        name[i] = keyScanner.nextLine();

        System.out.print("전화? ");
        tel[i] = keyScanner.nextLine();

        System.out.print("우편? ");
        postNo[i] = keyScanner.nextLine();

        System.out.print("주소? ");
        basicAddress[i] = keyScanner.nextLine();

        System.out.print("주소2? ");
        detailAddress[i] = keyScanner.nextLine();

        System.out.println("0.미취업 ");
        System.out.println("1.재직중 ");
        System.out.println("재직자? ");
        working[i] = Integer.parseInt(keyScanner.nextLine()) == 1;

        System.out.println("0. 남자");
        System.out.println("1. 여자");
        gender[i] = Integer.parseInt(keyScanner.nextLine()) == 0 ? 'M' : 'W';

        System.out.println("0. 비전공자");
        System.out.println("1. 준전공자");
        System.out.println("2. 전공자");
        System.out.println("전공? ");
        level[i] = Byte.parseByte(keyScanner.nextLine());

        Date today = new Date(System.currentTimeMillis());
        createdDate[i] = today.toString();

        count++;

        System.out.printf("계속 입력하시겠습니까? (Y/n)");
        String str = keyScanner.nextLine();
        if (!str.equalsIgnoreCase("Y") && str.length() != 0) {
            break;
        }
    }

    keyScanner.close();

    System.out.println();

    for (int i = 0; i < count; i++) {
        System.out.printf("번호: %d\n", no[i]);
        System.out.printf("이름: %s\n", name[i]);
        System.out.printf("전화: %s\n", tel[i]);
        System.out.printf("우편: %s\n", postNo[i]);
        System.out.printf("주소: %s\n", basicAddress[i]);
        System.out.printf("주소2: %s\n", detailAddress[i]);
        System.out.printf("재직자: %s\n", working[i] ? "예" : "아니오");
        System.out.printf("성별: %s\n", gender[i] == 'M' ? "남자" : "여자");

        String levelTitle;
        switch (level[i]) {
            case 0: levelTitle = "비전공자"; break;
            case 1: levelTitle = "준전공자"; break;
            default: levelTitle = "전공자"; break;
        }
        
        System.out.printf("전공: %s\n", levelTitle);
        System.out.printf("가입일: %s\n", createdDate[i]);
    }
}
}
