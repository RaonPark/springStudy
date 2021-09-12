package com.ssuzalal.project.constants;

public class Constants {
    // JavaMailSender
    public final static String AUTHORIZE_TITLE = "슈잘알에서 보내는 인증코드입니다.";
    public final static String CHANGE_PASSWORD_TITLE = "슈잘알 패스워드 변경을 요청했습니다.";
    public final static String CHANGE_PASSWORD_URL = "localhost:8080/changePassword.do";

    // Selenium
    public final static String SAINT_URL = "https://scatch.ssu.ac.kr/공지사항/?f&category=%s&keyword";
    public final static String BACHELOR = "학사";
    public final static String SCHOLARSHIP = "장학";
    public final static String EXCHANGE = "국제교류";
    public final static String FOREIGNER = "외국인유학생";
    public final static String RECRUIT = "채용";
    public final static String EVENTS = "비교과·행사";
    public final static String TEACHER_RECRUIT = "교원채용";
    public final static String TEACHING_PROFESSION = "교직";
    public final static String VOLUNTEER = "봉사";
    public final static String ETC = "기타";
    public final static String COVID19 = "코로나19-관련-소식";
    public final static String EDGE_DRIVER_PATH = "src/main/resources/driver/msedgedriver_93.exe";
    public final static String EDGE_DRIVER = "webdriver.edge.driver";
    public final static String PROGRESS = "진행";
    public final static String COMPLETED = "완료";
    public final static String NO_ID = "NO_ID";

    // pbkdf2
    public final static int DEFAULT_COST = 16;
    public final static int DEFAULT_HASH_WIDTH = 256;
    public final static int SIZE = 128;
    public static final String ALGORITHM = "PBKDF2WithHmacSHA1";
    public static final int DEFAULT_ITERATIONS = 1;
    public final static String SALT = "salt_key";
}
