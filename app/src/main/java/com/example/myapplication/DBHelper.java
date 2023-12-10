package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mycontacts.db";
    private static final int DATABASE_VERSION = 3;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // 테이블 생성
        db.execSQL("CREATE TABLE category ( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT" +
                ");");

        db.execSQL("CREATE TABLE store ( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "tel TEXT, " +
                "address TEXT, " +
                "rating TEXT, " +
                "category_id INTEGER, " +
                "image_url TEXT," +
                "latitude FLOAT," +
                "longitude FLOAT," +
                "FOREIGN KEY (category_id) REFERENCES category(_id)" +
                ");");

        // 카테고리
        db.execSQL("INSERT INTO category (name) VALUES ('한식');");
        db.execSQL("INSERT INTO category (name) VALUES ('중식');");
        db.execSQL("INSERT INTO category (name) VALUES ('양식');");
        db.execSQL("INSERT INTO category (name) VALUES ('일식');");
        db.execSQL("INSERT INTO category (name) VALUES ('카페');");
        db.execSQL("INSERT INTO category (name) VALUES ('술집');");
        db.execSQL("INSERT INTO category (name) VALUES ('분식');");
        db.execSQL("INSERT INTO category (name) VALUES ('아시아');");
        db.execSQL("INSERT INTO category (name) VALUES ('패스트푸드');");
        db.execSQL("INSERT INTO category (name) VALUES ('레스토랑');");

        // 가게
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('우마이도', '010-4937-1765','롯데리아 앞', '5.0', 3, 'https://my-bucket-for-spring.s3.ap-northeast-2.amazonaws.com/46eaa2aa-5592-463a-883d-63511d4086e1%E3%85%8A%E3%85%81.jpeg', 35.54209, 129.2606);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('육향', '070-8621-9515',  '울산 남구 대학로161번길 15 1층육향', '4.47', 0, 'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20230901_294%2F1693535324097reA3s_JPEG%2FKakaoTalk_20230901_112821779_16.jpg',35.54974, 129.2615);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('밥','0507-1312-6085','울산 남구 대학로43번길 18-5 밥','4.40', 0,'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20190307_69%2F1551885545115E4TAl_JPEG%2FJvdsgJ9n8r7QnmiPtSOTzF0L.jpg', 35.54075, 129.2554);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('맛찬들 왕소금구이','0507-1448-6689','울산 남구 신복로22번길 21','4.74', 0,'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20200614_254%2F1592145462182Kgdqr_JPEG%2Fa8pTzHtNm_k733zu8CXv-wFH.jpg',35.55043, 129.2598);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('황금정','0507-1397-6486','울산 남구 대학로43번길 14 황금정','없음', 1,'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20180213_148%2F1518517193372dfwiw_JPEG%2FBpldKfwPqJiT3xZ5Kjbt65UH.jpg',35.54037, 129.2557);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('으뜸공리','052-247-5900','울산 남구 대학로43번길 18-13','4.43', 1,'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20160331_54%2F1459413132741p3mN5_JPEG%2F176281576232601_0.jpeg',35.54097, 129.2555);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('안창깍두기','052-226-2724','울산 남구 대학로84번길 5-9','4.44', 1,'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzExMjZfNDUg%2FMDAxNzAwOTg4NTMwNjA3.EMIw92BZB3pnuLNeRr9oxX2b3TtTpY6XyOfnBDTI2JIg.xvgOCdeZs-E4QSZOExUKGmjTdEAVBHVgFT5DECElQ1Ig.PNG.peachyy2%2Fimage.png',35.54273, 129.2603);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('신가네칼국수','0507-1395-3868','울산 남구 옥현로51번길 3 신가네 칼국수','없음', 1,'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20230910_206%2F16943293812626tyq4_JPEG%2F%25B0%25ED%25B1%25E2_%25C4%25AE%25B1%25B9%25BC%25F6.jpg',35.54134, 129.2608);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('사오바오우육면','052-224-0888','울산 남구 대학로94번길 6 1층','없음', 1,'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzExMjZfNDUg%2FMDAxNzAwOTg4NTMwNjA3.EMIw92BZB3pnuLNeRr9oxX2b3TtTpY6XyOfnBDTI2JIg.xvgOCdeZs-E4QSZOExUKGmjTdEAVBHVgFT5DECElQ1Ig.PNG.peachyy2%2Fimage.png',35.54316, 129.2604);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('만리홍','052-277-5884','울산 남구 신복로17번길 10','4.39', 1,'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20161031_55%2F14778888459620JVtP_JPEG%2F176981537063520_0.jpeg',35.54727, 129.2597);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('더만족','0507-1423-5789','울산 남구 대학로108번길 5 1층 더만족 울산대점','4.51',1,'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20220906_85%2F1662443442711NaLxF_JPEG%2FKakaoTalk_20220906_145010781_01.jpg',35.54433, 129.2610);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('짬뽕상회','052-223-9997','울산 남구 옥현로 45','4.37',1,'https://search.pstatic.net/common/?src=https%3A%2F%2Fpup-review-phinf.pstatic.net%2FMjAyMzEwMjVfMTcy%2FMDAxNjk4MjI3OTIzNjAz.nuE-UoLTSpWBlMVtFcBoW_7eMmdW6jiXc2SDNLPu6l8g.gHeP11lm_P_g9HwU69TbfzWNTTJZWAFGrLRSmzxUtxYg.JPEG%2F20231025_174948.jpg',35.54074, 129.2606);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('돌돌파스타','052-222-9100','울산 남구 대학로84번길 12 양지빌딩 1층','4.40',2,'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20230727_180%2F1690421643542rdkkw_JPEG%2FE1D25DFE-01E8-458B-BB11-54F4C7936BCE.jpeg',35.54195, 129.2607);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('해피치즈스마일','0507-1406-4234','울산 남구 대학로 120 1층','없음',6,'https://lh3.googleusercontent.com/p/AF1QipP2A7fiFuyo34VGi9REGqGB9xWEBZg3QfBB55Ez=s1360-w1360-h1020',35.54530, 129.2612);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('이한','010-6339-6933','울산 남구 북부순환도로 42','4,74',3,'https://search.pstatic.net/common/?src=https%3A%2F%2Fpup-review-phinf.pstatic.net%2FMjAyMzA3MjFfOCAg%2FMDAxNjg5ODk5NzIyMjcw.syOG8bKWAD1zZ-PaDbZ4vPHLUe1LvkRZS5gWL1uAywsg.v7oNZ93mIl29i6JjOxo99_GQvehnslvqvjD7VZqdSsQg.JPEG%2F20230706_175758.jpg',35.55198, 129.2683);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('라멘집입니다','052-911-0401','울산 남구 문수로75번길 21','4.54',3,'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20190328_224%2F1553732981060npEaY_JPEG%2F10_u14FRE5U61-FhMpeYNfCU.jpg',35.54046, 129.2624);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('해월당','052-222-6192','울산 남구 옥현로 41 1, 2층','없음',4,'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzEyMDRfMzEg%2FMDAxNzAxNjQ3MTgxNDMz.JvA6vQTCuu_Csrnek1w73d1BwcFQnZmPlNlBe0Kc6-Ug.C1vuwdO9vNpqG_ymnw2hyEVfXg13fJwIfdaLLw9scykg.JPEG.peack16%2F%25B9%25AB%25B0%25C5%25C7%25D8%25BF%25F9%25B4%25E75.jpg',35.54055, 129.2603);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('테디스','0507-0289-4505','울산 남구 대학로84번길 5-13 1층 테디스','없음',4,'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20230915_274%2F1694768725824PGhD3_JPEG%2F6B852231-5EAF-402B-981D-CBB813B5C7CC.jpeg',35.54297, 129.2604);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('차차돈까스','052-222-1060','울산 남구 옥현로51번길 2 1층 차차돈까스','4.30',3,'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20220903_52%2F1662178415774PeNzB_JPEG%2F1662164954481.jpg',35.54142, 129.2610);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('수산물창고','052-225-3330','울산 남구 옥현로 21 1층','없음',5,'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzEwMjdfMTAx%2FMDAxNjk4NDAxNzk1NjA4.MJ_zLRGcsjy9YlHQ6LasqCbIpHii5IsIa-cEpX6HPAkg.lZrfSB3kyz43nxZbeYLfr4M-Mzhb9L06GkcksWdMYVQg.JPEG.bram1231%2F_DSC4130.JPG',35.53974, 129.2585);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('가무댕댕','052-224-3331','울산 남구 대학로94번길 11','4.38',0,'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzA4MjdfMjY1%2FMDAxNjkzMTE0NDU4NTU2.ilmsrmO8IjdAPuZEpS4nUlraHm0Lyq5zB6XZmYL2L2kg.kp4iYBYcKBmb-nQro6J04ClQ-BD17U317L7CpwB6ruIg.JPEG.merryul0203%2FIMG_0596.jpg',35.54301, 129.2613);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('한라맥주','052-223-0700','울산 남구 대학로76번길 7-11 1층 한라맥주','없음',5,'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20221128_15%2F1669593425988f4CoM_JPEG%2FIMG_20211018_201320_441.jpg',35.54203, 129.2600);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('처음처럼','052-249-9465','울산 남구 대학로76번길 7-6','4.49',5,'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20191107_15%2F1573098745271o3Ftt_JPEG%2FnBW0beKTCGB0DJO4UpxrRlUb.jpg',35.54150, 129.2600);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('인사동 골목식당','0507-1344-1048','울산 남구 대학로76번길 11','4.56',5,'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzEwMTNfMTk3%2FMDAxNjk3MTU3MDUxMTIy.fpb6dYy_xXv4zipmOuPKLT5YpigZw8ia8n5Gl4kvEaEg.p4bgV-pBhe4imp5evax1rARd0u9nqRuW8_NrF7UdeM0g.JPEG.skshy3j%2FBandPhoto%25A3%25DF2023%25A3%25DF10%25A3%25DF13%25A3%25DF09%25A3%25DF22%25A3%25DF43.jpg',35.54130, 129.2604);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('숙성창고','052-224-4041','울산 남구 신복로22번길 28 숙성창고','4.67',5,'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20171114_93%2F1510655072442A3Sp4_JPEG%2FI49fCGeJwRyAxV09PI94hM6c.JPG.jpg',35.55086, 129.2594);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('똥집이야기','0507-1371-2288','울산 남구 신복로16번길 3 1층 똥집이야기','없음',5,'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20230111_156%2F16734273430854zMAx_JPEG%2F20221219_230815.jpg',35.54819, 129.2604);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('도쿠도쿠','010-4728-2894','울산 남구 대학로 74 2동 1층','4.68',5,'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20210406_109%2F1617704103272xUF5U_JPEG%2FIwPpoURSMFjO85kQqpnH_giZ.jpeg.jpg',35.54156, 129.2593);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('도쏭봉가또','052-277-3007','울산 남구 대학로 94','4.63',4,'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzEwMTRfMjQ0%2FMDAxNjk3MjkxMDgyOTQz.ZGbn8RyeTsMq3gD8X6ZO68shX1V0K23IBEWqw4hQIf8g.OK5DzYiGDSDgozdgzdvIoerm08x0OcJcgzZaQ9_dVmYg.JPEG.dlaldud99%2F20231013%25A3%25DF195153.jpg',35.54317, 129.2602);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('폴 양분식','0507-1372-9992','울산 남구 대학로 84-1 1층 폴 양분식','없음',6,'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20221130_241%2F1669742784930YbvQe_JPEG%2FScreenshot_20221130-022155.jpg',35.54231, 129.2599);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('틈새라면','052-277-0590','울산 남구 대학로 94','4.60',3,'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20200105_4%2F1578230003631geet6_JPEG%2F714Id9kXcxGCqzdYPm2DYvQ_.jpeg.jpg',35.54319, 129.2602);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('정통집','052-277-9913','울산 남구 대학로76번길 10 1층','4.38',5,'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20181202_180%2F154371909845626b0X_JPEG%2FIvY7_hmvHbfJXbPz5pT2B6Jm.jpg',35.54110, 129.2600);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('울대 떡볶이','050-6256-3786','울산 남구 대학로94번길 5 1층 울대떡볶이','없음',6,'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20210320_158%2F1616166250641jnuoE_JPEG%2FaiY3-twwRIBHaFVzp7QNqGqA.jpg',35.54316, 129.2608);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('안주일체','052-222-0432','울산 남구 대학로64번길 7-9 1층','4.38',6,'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20200507_268%2F1588791948738WmrXC_JPEG%2FtYry99gvuqaHjuFnJ1qgrZSY.jpg',35.54097, 129.2593);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('신전떡볶이','0507-1441-7757','울산 남구 대학로 90 1층','없음',6,'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzA3MTZfMTYz%2FMDAxNjg5NTE2NDI2MDI0.MnQTcqm4--0Ym67t4OSrFbnfiQom8CcPDYMNd_HaCLsg.1vDkEYu3yUGNue8f2rR71Q_EkV4drC_8piet8ANv8-Qg.JPEG.arang_20a%2F20230716%25A3%25DF150252.jpg',35.54295, 129.2601);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('강다짐 삼각김밥','052-223-6663','울산 남구 대학로43번길 18-14 1층','없음',6,'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20230322_118%2F1679459152350o6eiU_JPEG%2FKakaoTalk_20210325_154320785.jpg',35.54096, 129.2559);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('버거킹 울산무거점','052-249-0332','울산 남구 대학로 157','4.48',8,'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzA5MDJfNDYg%2FMDAxNjkzNjU4NjIwOTkz.Dhy78hK_JtXDyU0kyM49PFeWOcl96A_fdn2rXvkdCQ4g.k_lHPsXtLu4pd8SsD4Lrkq3SH-4Jf_JB0Mk1RYBoCE8g.JPEG.mhe0110%2F20230902%25A3%25DF162812.jpg',35.54869, 129.2624);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('맥도날드 울산옥현점','052-955-7200','울산 남구 옥현로58번길 4','4.46',8,'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20200219_231%2F1582104691399CTuVL_JPEG%2FoiTvbanhS8dLh4Emy5sAto2T.jpg',35.54116, 129.2619);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('맘스터치 울산대점','050-7982-4980','울산 남구 신복로21번길 17-1 1층','4.27',8,'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTA1MTBfMTU4%2FMDAxNjIwNjUxMTIwMDkx.d8aE_DxHNN_LV4iTO1vmzOZvkAjeZq4BgmAuahZ3ni0g.1vlhToNmyEpCCrVT0lWvya1jjsEQhg6H_65qH9kSBtog.JPEG.wisdom2078%2F1620651120565.jpg',35.54671, 129.2591);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('칠칠켄터키','0507-1482-7800','울산 남구 대학로94번길 10 성우빌딩 1층','4.62',5,'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMjExMjNfMTI4%2FMDAxNjY5MTczOTg1ODIw.RTSF9AaoIM-rvOlZKaz0rQYRaFdLHls2TBhcOQLLckAg.fuh_DNZhmW-V1Heg9gnn6RFgLl0b5YMsZeSk-GLzudAg.JPEG.dkfks5827%2FIMG_4191.jpg',35.54289, 129.2609);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('왓더버거','0507-1376-9124','울산 남구 옥현로 49 왓더버거 울산무거점','없음',8,'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20230414_1%2F1681455038137NrKy1_JPEG%2FKakaoTalk_20230413_155026178_02.jpg',35.54110, 129.2609);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('옐로우라이트','010-4657-5590','울산 남구 신복로31번길 14 해오름빌 1층 상가','4.49',9,'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20200721_240%2F1595322189019QsFtB_JPEG%2FFVzYzB1CQwqR5L6Pn9XZ7IRv.jpg',35.54798, 129.2576);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('서가앤쿡','052-249-2252','울산 남구 대학로94번길 5-5','4.45',9,'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20230904_233%2F16938092122814gKtq_JPEG%2F%25B8%25F1%25BB%25EC%25C7%25D1%25BB%25F3.jpg',35.54350, 129.2607);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('라라코스트','052-249-3230','울산 남구 대학로84번길 13','4.54',2,'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20230515_239%2F1684125568641zqFaT_JPEG%2F01.jpg',35.54220, 129.2611);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('샐러디아','0407-877-3578','울산 남구 대학로 96 1층','없음',4,'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20221014_141%2F1665744566267K4ldO_JPEG%2FKakaoTalk_20221012_164418767.jpg',35.54349, 129.2603);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('싱귤러커피','052-225-5863','울산 남구 대학로1번길 3-35 1층','4.80',4,'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20220423_13%2F1650641437367DGF4V_JPEG%2FIMG_20191127_152114_120.jpg',35.53544, 129.2515);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('춘리마라탕 무거점','0507-1405-5779','울산 남구 옥현로 57 1층','없음',1,'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20230201_24%2F1675253445815XvxdH_JPEG%2F20230201_210700.jpg',35.54157, 129.2613);");
        db.execSQL("INSERT INTO store (name, tel, address, rating, category_id, image_url, latitude, longitude) VALUES ('팔각도 울산무거점','0507-1498-8947','무거동 행정복지센터 100M전','없음',5,'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20230530_44%2F1685440550152uESL0_JPEG%2F003.jpg',35.54935, 129.2615);");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }
}
