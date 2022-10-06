package com.updev.board;

public class PageDTO {  
    // 현재페이지,시작페이지,끝페이지,게시글 총갯수,페이지당글갯수,마지막페이지, start, end
	public int nowPage, startPage, endPage, total, cntPerPage, lastPage, start, end, realEnd;
	public int cntPage=10;  //한 화면에 표시하고자 하는 블럭의 수
	public boolean prev, next;	//이전 페이지, 다음 페이지
	public Criteria cri;
	public String sname; // 검색타입 (글제목, 글쓴이 등등)
	public String keyword; // 키워드
	public int b_num, b_replycnt;
	
	
	
	public PageDTO(Criteria cri, int total, int nowPage, int cntPerPage) {
		this.cri = cri;
		this.total = total;
		
		//화면에 보여질 마지막 페이지
		this.endPage = (int)(Math.ceil(cri.getPageNum() / 10.0)) * 10;
		//화면에 보여질 시작 페이지
		this.startPage = this.endPage -9;
		//전체 마지막 페이지
		this.realEnd = (int) (Math.ceil(total * 1.0) / cri.getAmount());
		//화면에 보일 마지막 페이지가 유효한지 체크
		if(this.realEnd < this.endPage) {
			this.endPage = this.realEnd;
		}
		
		//이전, 다음 버튼 표출 여부 결정
		this.prev = this.startPage > 1;
		this.next = this.endPage < this.realEnd;
		
		setNowPage(nowPage);
	    setCntPerPage(cntPerPage);
	    setTotal(total);
	    calcLastPage(getTotal(), getCntPerPage());
	    calcStartEndPage(getNowPage(), cntPage);
	    calcStartEnd(getNowPage(), getCntPerPage());   
	    
	}
	
	public PageDTO(Criteria cri, int total, int nowPage, int cntPerPage, String keyword) {
		this.cri = cri;
		this.total = total;
		
		//화면에 보여질 마지막 페이지
		this.endPage = (int)(Math.ceil(cri.getPageNum() / 10.0)) * 10;
		//화면에 보여질 시작 페이지
		this.startPage = this.endPage -9;
		//전체 마지막 페이지
		this.realEnd = (int) (Math.ceil(total * 1.0) / cri.getAmount());
		//화면에 보일 마지막 페이지가 유효한지 체크
		if(this.realEnd < this.endPage) {
			this.endPage = this.realEnd;
		}
		
		//이전, 다음 버튼 표출 여부 결정
		this.prev = this.startPage > 1;
		this.next = this.endPage < this.realEnd;
		
		setKeyword(keyword);
		setNowPage(nowPage);
	    setCntPerPage(cntPerPage);
	    setTotal(total);
	    calcLastPage(getTotal(), getCntPerPage());
	    calcStartEndPage(getNowPage(), cntPage);
	    calcStartEnd(getNowPage(), getCntPerPage());   
	    
	}
	
	public PageDTO(Criteria cri, int total, int nowPage, int cntPerPage, int b_num) {
		this.cri = cri;
		this.total = total;
		this.b_num = b_num;
		
		//화면에 보여질 마지막 페이지
		this.endPage = (int)(Math.ceil(cri.getPageNum() / 10.0)) * 10;
		//화면에 보여질 시작 페이지
		this.startPage = this.endPage -9;
		//전체 마지막 페이지
		this.realEnd = (int) (Math.ceil(total * 1.0) / cri.getAmount());
		//화면에 보일 마지막 페이지가 유효한지 체크
		if(this.realEnd < this.endPage) {
			this.endPage = this.realEnd;
		}
		
		//이전, 다음 버튼 표출 여부 결정
		this.prev = this.startPage > 1;
		this.next = this.endPage < this.realEnd;
		
		setB_num(b_num);
		setNowPage(nowPage);
		setCntPerPage(cntPerPage);
		setTotal(total);
		calcLastPage(getTotal(), getCntPerPage());
		calcStartEndPage(getNowPage(), cntPage);
		calcStartEnd(getNowPage(), getCntPerPage());   
		
	}
	
	
   public int getCntPage() {
      return cntPage;
   }
   
   public void setCntPage(int cntPage) {
      this.cntPage = cntPage;
   }
   
   public PageDTO() {
      super();
   }
   



// 제일마지막페이지계산
   public void calcLastPage(int total, int cntPerPage) {
      setLastPage((int) Math.ceil((double)total / (double)cntPerPage));
         }   
   //시작,끝 페이지 계산
   public void calcStartEndPage(int nowPage, int cntPage) {
      setEndPage(((int)Math.ceil((double)nowPage / (double)cntPage)) * cntPage);
      if (getLastPage() < getEndPage()) {
         setEndPage(getLastPage());
      }
      setStartPage(getEndPage() - cntPage + 1);
      if(getStartPage()<1) {
         setStartPage(1);
      }
   }
   
   // DB 쿼리에 사용할 start,end값 계산
   public void calcStartEnd(int nowPage, int cntPerPage) {
      setEnd(nowPage * cntPerPage);
      setStart(getEnd() - cntPerPage + 1);
   }

	public int getNowPage() {
		return nowPage;
	}
	
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	
	public int getStartPage() {
		return startPage;
	}
	
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
	
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	public int getTotal() {
		return total;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	public int getCntPerPage() {
		return cntPerPage;
	}
	
	public void setCntPerPage(int cntPerPage) {
		this.cntPerPage = cntPerPage;
	}
	
	public int getLastPage() {
		return lastPage;
	}
	
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	
	public int getStart() {
		return start;
	}
	
	public void setStart(int start) {
		this.start = start;
	}
	
	public int getEnd() {
		return end;
	}
	
	public void setEnd(int end) {
		this.end = end;
	}

	public boolean isPrev() {
		return prev;
	}
	
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	
	public boolean isNext() {
		return next;
	}
	
	public void setNext(boolean next) {
		this.next = next;
	}
	
	public Criteria getCri() {
		return cri;
	}
	
	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	public int getRealEnd() {
		return realEnd;
	}

	public void setRealEnd(int realEnd) {
		this.realEnd = realEnd;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getB_num() {
		return b_num;
	}

	public void setB_num(int b_num) {
		this.b_num = b_num;
	}

	public int getB_replycnt() {
		return b_replycnt;
	}

	public void setB_replycnt(int b_replycnt) {
		this.b_replycnt = b_replycnt;
	}
   
}

//System.out.println("전체 레코드의 수"+getTotal());
//System.out.println("전체 페이지의 수"+getLastPage());
//System.out.println("시작페이지수"+getStartPage());
//System.out.println("끝 페이지 수"+getEndPage());     