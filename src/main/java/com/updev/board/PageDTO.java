package com.updev.board;

public class PageDTO {  
    // ����������,����������,��������,�Խñ� �Ѱ���,��������۰���,������������, start, end
	public int nowPage, startPage, endPage, total, cntPerPage ,Page, lastPage, start, end;
	public int cntPage=10;  //�� ȭ�鿡 ǥ���ϰ��� �ϴ� ���� ��
	public boolean prev, next;	//���� ������, ���� ������
	public Criteria cri;
	
	public PageDTO(Criteria cri, int total, int nowPage, int cntPerPage) {
		this.cri = cri;
		this.total = total;
		
		//ȭ�鿡 ������ ������ ������
		this.endPage = (int)(Math.ceil(cri.getPageNum() / 10.0)) * 10;
		//ȭ�鿡 ������ ���� ������
		this.startPage = this.endPage -9;
		//��ü ������ ������
		int realEnd = (int) (Math.ceil(total * 1.0) / cri.getAmount());
		//ȭ�鿡 ���� ������ �������� ��ȿ���� üũ
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		//����, ���� ��ư ǥ�� ���� ����
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
		
		
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
   
   
   // ���ϸ��������������
   public void calcLastPage(int total, int cntPerPage) {
      setLastPage((int) Math.ceil((double)total / (double)cntPerPage));
         }   
   //����,�� ������ ���
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
   
   // DB ������ ����� start,end�� ���
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
	  
   
}

//System.out.println("��ü ���ڵ��� ��"+getTotal());
//System.out.println("��ü �������� ��"+getLastPage());
//System.out.println("������������"+getStartPage());
//System.out.println("�� ������ ��"+getEndPage());     