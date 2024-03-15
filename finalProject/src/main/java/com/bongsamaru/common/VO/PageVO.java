package com.bongsamaru.common.VO;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PageVO {
    private int start; // 게시글 화면에 보여질 첫번째 번호
    private int end; // 게시글 화면에 보여질 마지막 번호 
    private boolean prev, next; // 이전버튼, 다음버튼 활성화여부
    private int total; // 전체게시글 수
    private String category;
    private int cntPerPage; // 한페이지보여줄 게시글 수
    private int totalPage;
    private int	currentPage;
    private int pageSize = 5; // 한페이지보여줄 버튼수
    private Integer volId;
    private String memId;
    private Integer roomStat;
    
    private int startPage;
    private int endPage;

   
  //영희
    private String memApp;
    private String donApp;
    private String donRegApp;
    private Integer recStat;
    private Integer donId;
    private Integer reqCode;
    private String writer;

    // Getter와 Setter 메서드

    
    
    private String searchKeyword; // 검색이름
    private String volZip2;

    public PageVO(int total, int start, int end, String category , int cntPerPage) {
        this.total = total;
        this.category = category;
        
        // 1. end 결정
        this.end = Math.min(end, total);

        // 2. startPage, endPage 결정
     
        this.cntPerPage = cntPerPage; // 1페이지에 10개씩 보여줍니다
        this.totalPage = (int) Math.ceil((double) this.total / this.cntPerPage); // 전체 페이지 수
        this.currentPage = (int) Math.ceil((double) start / this.cntPerPage); // 현재 페이지

        this.start = (this.currentPage - 1) * this.cntPerPage + 1;
        this.end = Math.min(this.start + this.cntPerPage - 1, this.total);  // 시작부터 페이지에 표시할 수 있는 최대 개수까지의 범위로 설정합니다.
        
        System.out.println(start + "시작");
        System.out.println(end + "끝");
        // 3. prev 결정
        this.prev = this.currentPage > 1;

        // 4. next 결정
        this.next = this.currentPage < this.totalPage;

        // 데이터가 없는 경우, end 값을 조정하여 보여줄 수 있는 범위 내에서 설정합니다.
        if (this.start > this.total) {
            this.start = this.total - (this.total % this.cntPerPage) + 1;
            this.end = this.total;
        }
        
        
        // 시작페이지
        this.startPage = (this.currentPage - 1) / this.pageSize * pageSize + 1;
        System.out.println(this.startPage + "스타트");
        
        // 끝 페이지
        
        this.endPage = (this.currentPage-1)/this.pageSize  * this.pageSize  + this.pageSize ;
		if ( this.endPage > this.totalPage )
			this.endPage = this.totalPage;
        System.out.println(this.endPage + "스타트");
        
        
    }

    
    public PageVO(int total, int start, int end, String category, String searchKeyword, int cntPerPage) {

        this.total = total;
        this.category = category;
        this.searchKeyword = searchKeyword;

        // 1. end 결정
        this.end = Math.min(end, total);

        // 2. startPage, endPage 결정

     
        this.cntPerPage = cntPerPage; // 1페이지에 10개씩 보여줍니다

        this.totalPage = (int) Math.ceil((double) this.total / this.cntPerPage); // 전체 페이지 수
        this.currentPage = (int) Math.ceil((double) start / this.cntPerPage); // 현재 페이지
        this.start = (this.currentPage - 1) * this.cntPerPage + 1;
        this.end = Math.min(this.start + this.cntPerPage - 1, this.total);  // 시작부터 페이지에 표시할 수 있는 최대 개수까지의 범위로 설정합니다.
        
        System.out.println(start + "시작");
        System.out.println(end + "끝");
        // 3. prev 결정
        this.prev = this.currentPage > 1;

        // 4. next 결정
        this.next = this.currentPage < this.totalPage;

        // 데이터가 없는 경우, end 값을 조정하여 보여줄 수 있는 범위 내에서 설정합니다.
        if (this.start > this.total) {
            this.start = this.total - (this.total % this.cntPerPage) + 1;
            this.end = this.total;
            return;
        }
        // 시작페이지
        this.startPage = (this.currentPage - 1) / this.pageSize * pageSize + 1;
        System.out.println(this.startPage + "스타트");
        
        // 끝 페이지
        
        this.endPage = (this.currentPage-1)/this.pageSize  * this.pageSize  + this.pageSize ;
		if ( this.endPage > this.totalPage )
			this.endPage = this.totalPage;
        System.out.println(this.endPage + "스타트");
    }
    //영희꺼
    public PageVO(int total, int start, int end, Integer volId, String category) {
        this.total = total;
        this.volId = volId;
        this.category = category;

        // 1. end 결정
        this.end = Math.min(end, total);

        // 2. startPage, endPage 결정
     
        this.cntPerPage = 10; // 1페이지에 10개씩 보여줍니다
        this.totalPage = (int) Math.ceil((double) this.total / this.cntPerPage); // 전체 페이지 수
        this.currentPage = (int) Math.ceil((double) start / this.cntPerPage); // 현재 페이지
        this.start = (this.currentPage - 1) * this.cntPerPage + 1;
        this.end = Math.min(this.start + this.cntPerPage - 1, this.total);  // 시작부터 페이지에 표시할 수 있는 최대 개수까지의 범위로 설정합니다.
        
        // 3. prev 결정
        this.prev = this.currentPage > 1;

        // 4. next 결정
        this.next = this.currentPage < this.totalPage;

        // 데이터가 없는 경우, end 값을 조정하여 보여줄 수 있는 범위 내에서 설정합니다.
        if (this.start > this.total) {
            this.start = this.total - (this.total % this.cntPerPage) + 1;
            this.end = this.total;
            return;
        }
        // 시작페이지
        this.startPage = (this.currentPage - 1) / this.pageSize * pageSize + 1;
        
        // 끝 페이지
        this.endPage = (this.currentPage-1)/this.pageSize  * this.pageSize  + this.pageSize ;
		if ( this.endPage > this.totalPage )
			this.endPage = this.totalPage;
        
        
    }
    
    public PageVO(int total, int start, int end, String category, String searchKeyword, int cntPerPage , String zip) {
        this.total = total;
        this.category = category;
        this.searchKeyword = searchKeyword;
        this.volZip2 = zip;
        // 1. end 결정
        this.end = Math.min(end, total);

        // 2. startPage, endPage 결정
     
        this.cntPerPage = cntPerPage; // 1페이지에 10개씩 보여줍니다
        this.totalPage = (int) Math.ceil((double) this.total / this.cntPerPage); // 전체 페이지 수
        this.currentPage = (int) Math.ceil((double) start / this.cntPerPage); // 현재 페이지
        this.start = (this.currentPage - 1) * this.cntPerPage + 1;
        this.end = Math.min(this.start + this.cntPerPage - 1, this.total);  // 시작부터 페이지에 표시할 수 있는 최대 개수까지의 범위로 설정합니다.
        
        System.out.println(start + "시작");
        System.out.println(end + "끝");
        // 3. prev 결정
        this.prev = this.currentPage > 1;

        // 4. next 결정
        this.next = this.currentPage < this.totalPage;

        // 데이터가 없는 경우, end 값을 조정하여 보여줄 수 있는 범위 내에서 설정합니다.
        if (this.start > this.total) {
            this.start = this.total - (this.total % this.cntPerPage) + 1;
            this.end = this.total;
            return;
        }
        // 시작페이지
        this.startPage = (this.currentPage - 1) / this.pageSize * pageSize + 1;
        System.out.println(this.startPage + "스타트");
        
        // 끝 페이지
        
        this.endPage = (this.currentPage-1)/this.pageSize  * this.pageSize  + this.pageSize ;
		if ( this.endPage > this.totalPage )
			this.endPage = this.totalPage;
        System.out.println(this.endPage + "스타트");
        
        
    }
    
    
    
    
}